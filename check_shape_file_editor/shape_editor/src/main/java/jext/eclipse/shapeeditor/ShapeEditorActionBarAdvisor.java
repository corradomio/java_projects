package jext.eclipse.shapeeditor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ShapeEditorActionBarAdvisor extends ActionBarAdvisor {

    private OpenShapeFileAction openAction;

    public ShapeEditorActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    @Override
    protected void makeActions(IWorkbenchWindow window) {
        openAction = new OpenShapeFileAction(window);
        register(openAction);
    }

    @Override
    protected void fillMenuBar(IMenuManager menuBar) {
        MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
        fileMenu.add(openAction);
        fileMenu.add(new Separator());
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null) {
            fileMenu.add(org.eclipse.ui.actions.ActionFactory.QUIT.create(window));
        }
        menuBar.add(fileMenu);
    }
}
