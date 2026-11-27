package jext.eclipse.shapeeditor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import jext.eclipse.shapeeditor.actions.OpenConfigurationAction;

public class ShapeEditorActionBarAdvisor extends ActionBarAdvisor {

    private OpenShapeFileAction openAction;
    private AddRootDirectoryAction addRootDirectoryAction;
    private OpenConfigurationAction configurationAction;

    public ShapeEditorActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    @Override
    protected void makeActions(IWorkbenchWindow window) {
        openAction = new OpenShapeFileAction(window);
        register(openAction);

        addRootDirectoryAction = new AddRootDirectoryAction(window);
        register(addRootDirectoryAction);

        configurationAction = new OpenConfigurationAction(window);
        register(configurationAction);
    }

    @Override
    protected void fillMenuBar(IMenuManager menuBar) {
        MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
        fileMenu.add(addRootDirectoryAction);
        fileMenu.add(openAction);
        fileMenu.add(new Separator());
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null) {
            fileMenu.add(org.eclipse.ui.actions.ActionFactory.QUIT.create(window));
        }
        menuBar.add(fileMenu);

        MenuManager editMenu = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
        editMenu.add(configurationAction);
        menuBar.add(editMenu);
    }
}
