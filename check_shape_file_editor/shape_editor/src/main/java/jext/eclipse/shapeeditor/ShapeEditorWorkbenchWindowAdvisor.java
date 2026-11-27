package jext.eclipse.shapeeditor;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ShapeEditorWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ShapeEditorWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    @Override
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1200, 800));
        configurer.setShowMenuBar(true);
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(true);
        configurer.setTitle("Shape File Editor");
    }

    @Override
    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ShapeEditorActionBarAdvisor(configurer);
    }

    @Override
    public void postWindowOpen() {
        super.postWindowOpen();
        IWorkbenchWindow window = getWindowConfigurer().getWindow();
        if (window == null) {
            return;
        }
        IWorkbenchPage page = window.getActivePage();
        if (page == null) {
            return;
        }
        try {
            page.showView(jext.eclipse.shapeeditor.views.DirectoryTreeView.ID);
        } catch (Exception ignored) {
        }
    }
}
