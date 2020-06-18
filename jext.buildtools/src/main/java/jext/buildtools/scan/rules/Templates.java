package jext.buildtools.scan.util;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Templates {

    private Template defaultTemplate = new Template();
    private List<Template> templates = new ArrayList<>();

    public void configure(Element elt, String xpath) {
        XPathUtils.selectNodes(elt, "module")
                .forEach(templ ->{
                    Template template = new Template();
                    template.configure(templ);
                    if (!template.getName().isEmpty())
                        templates.add(template);
                    else
                        defaultTemplate = template;
                });
    }

    public Template getTemplate(String name){
        for(Template template : templates)
            if (template.getName().equals(name))
                return template.merge(defaultTemplate);
        return defaultTemplate;
    }

    public List<Template> getMissingModules(Set<String> moduleNames) {
        return templates.stream()
                .filter(template -> !moduleNames.contains(template.getName()))
                .collect(Collectors.toList());
    }
}
