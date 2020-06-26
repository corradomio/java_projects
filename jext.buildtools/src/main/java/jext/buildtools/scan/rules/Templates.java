package jext.buildtools.scan.rules;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Templates {

    private Template defaultTemplate = new Template();
    private Map<String, Template> namedTemplates = new HashMap<>();
    private Map<String, Template> starTemplates = new HashMap<>();

    public void configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        if (selected == null) return;
        XPathUtils.selectNodes(selected, "module")
                .forEach(templ -> {
                    String name = XPathUtils.getValue(templ, "@name", "");
                    Template template = newTemplate(name);
                    template.configure(templ);
                    putTemplate(template);
                });
    }

    private Template newTemplate(String name) {
        Template template;
        if (name.isEmpty() || "*".equals(name))
            template = defaultTemplate;
        else if (name.startsWith("*."))
            template = starTemplates.getOrDefault(name, null);
        else
            template = namedTemplates.getOrDefault(name, null);
        if (template == null)
            template = new Template();
        return template;
    }

    private void putTemplate(Template template) {
        String name = template.getName();
        if (name.isEmpty() || "*".equals(name))
            defaultTemplate = template;
        else if(name.startsWith("*."))
            starTemplates.put(name, template);
        else
            namedTemplates.put(name, template);
    }

    public Template getTemplate(String name){
        Template template = namedTemplates.getOrDefault(name, null);
        if (template == null)
            return defaultTemplate;
        else
            return template.merge(defaultTemplate);
    }

    public List<Template> getMissingModules(Set<String> moduleNames) {
        return namedTemplates.values().stream()
                .filter(template -> !moduleNames.contains(template.getName()))
                .collect(Collectors.toList());
    }
}
