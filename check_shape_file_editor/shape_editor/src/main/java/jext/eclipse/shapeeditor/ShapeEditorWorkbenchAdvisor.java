package jext.eclipse.shapeeditor;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ShapeEditorWorkbenchAdvisor extends WorkbenchAdvisor {
    @Override
    public String getInitialWindowPerspectiveId() {
        return "jext.eclipse.shapeeditor.perspective";
    }

    @Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ShapeEditorWorkbenchWindowAdvisor(configurer);
    }
}
