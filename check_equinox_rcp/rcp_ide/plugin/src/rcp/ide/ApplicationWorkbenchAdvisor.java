package rcp.ide;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.*;

public final class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
    @Override
    public String getInitialWindowPerspectiveId() { return "rcp.ide.perspective"; }

    @Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new WorkbenchWindowAdvisor(configurer) {
            @Override
            public void preWindowOpen() {
                configurer.setTitle("RCP File Explorer");
                configurer.setInitialSize(new Point(1100, 750));
                configurer.setShowMenuBar(true);
                configurer.setShowCoolBar(true);
                configurer.setShowStatusLine(true);
            }

            @Override
            public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer bars) {
                return new ApplicationActionBarAdvisor(bars);
            }

            @Override
            public boolean preWindowShellClose() {
                var page = configurer.getWindow().getActivePage();
                var view = page == null ? null : page.findView(ExplorerView.ID);
                return !(view instanceof ExplorerView) || ((ExplorerView) view).confirmSave();
            }
        };
    }
}
