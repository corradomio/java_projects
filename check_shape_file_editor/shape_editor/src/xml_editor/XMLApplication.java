package xml_editor;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class XMLApplication implements IApplication {
    @Override
    public Object start(IApplicationContext iApplicationContext) throws Exception {

        Display display = PlatformUI.createDisplay();

        try {
            int returnCode = PlatformUI.createAndRunWorkbench(display, new XMLWorkbenchAdvisor());
            if (returnCode == PlatformUI.RETURN_RESTART)
                return IApplication.EXIT_RESTART;
            else
                return IApplication.EXIT_OK;
        }
        catch (Throwable t1) {
            return IApplication.EXIT_OK;
        }
        finally {
            display.dispose();
        }
    }

    @Override
    public void stop() {

    }
}
