package rcp.ide;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public final class Application implements IApplication {
    @Override
    public Object start(IApplicationContext context) {
        Display display = PlatformUI.createDisplay();
        try {
            int result = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
            return result == PlatformUI.RETURN_RESTART ? EXIT_RESTART : EXIT_OK;
        } finally {
            display.dispose();
        }
    }

    @Override
    public void stop() {
        if (!PlatformUI.isWorkbenchRunning()) return;
        Display display = PlatformUI.getWorkbench().getDisplay();
        if (!display.isDisposed()) display.syncExec(() -> {
            if (!display.isDisposed()) PlatformUI.getWorkbench().close();
        });
    }
}
