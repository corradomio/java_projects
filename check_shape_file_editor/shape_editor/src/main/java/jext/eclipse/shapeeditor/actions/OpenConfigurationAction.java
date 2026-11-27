package jext.eclipse.shapeeditor.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import jext.eclipse.shapeeditor.dialogs.ConfigurationDialog;

public class OpenConfigurationAction extends Action {

    public static final String ID = "jext.eclipse.shapeeditor.actions.configuration";

    private final IWorkbenchWindow window;

    public OpenConfigurationAction(IWorkbenchWindow window) {
        this.window = window;
        setId(ID);
        setText("&Configuration...");
        setToolTipText("Open configuration dialog");
    }

    @Override
    public void run() {
        ConfigurationDialog dialog = new ConfigurationDialog(window.getShell());
        dialog.open();
    }
}
