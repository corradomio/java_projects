package jext.buildtools.scan.rules;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Templates {

    private Template defaultTemplate = new Template();
    private List<Template> namedTemplates = new ArrayList<>();
    private List<Template> starTmplates = new ArrayList<>();

    public void configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        XPathUtils.selectNodes(selected, "module")
                .forEach(templ ->{
                    Template template = new Template();
                    template.configure(templ);
                    String name = template.getName();
                    if (name.isEmpty() || "*".equals(name))
                        defaultTemplate = template;
                    else if(name.contains("*"))
                        starTmplates.add(template);
                    else
                        namedTemplates.add(template);
                });
    }

    public Template getTemplate(String name){
        for(Template template : namedTemplates)
            if (template.getName().equals(name))
                return template.merge(defaultTemplate);
        return defaultTemplate;
    }

    public List<Template> getMissingModules(Set<String> moduleNames) {
        return namedTemplates.stream()
                .filter(template -> !moduleNames.contains(template.getName()))
                .collect(Collectors.toList());
    }
}
