package xml_editor;

import org.eclipse.ui.application.WorkbenchAdvisor;

public class XMLWorkbenchAdvisor extends WorkbenchAdvisor {
    @Override
    public String getInitialWindowPerspectiveId() {
        return "XMLPerspective.perspective";
    }
}
