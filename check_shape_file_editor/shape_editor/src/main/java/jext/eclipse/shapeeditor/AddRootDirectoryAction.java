package jext.eclipse.shapeeditor;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import jext.eclipse.shapeeditor.views.DirectoryTreeView;

public class AddRootDirectoryAction extends Action {

    public static final String ID = "jext.eclipse.shapeeditor.actions.addRootDirectory";

    private final IWorkbenchWindow window;

    public AddRootDirectoryAction(IWorkbenchWindow window) {
        this.window = window;
        setId(ID);
        setText("&Add Root Directory...");
        setToolTipText("Add a root directory to the directory tree panel");
    }

    @Override
    public void run() {
        DirectoryDialog dialog = new DirectoryDialog(window.getShell(), SWT.OPEN);
        dialog.setText("Select Root Directory");
        dialog.setMessage("Select a directory to add as a root entry in the tree panel.");

        String path = dialog.open();
        if (path == null) {
            return;
        }

        File dir = new File(path);
        if (!dir.isDirectory()) {
            MessageDialog.openError(window.getShell(), "Add Root Directory",
                    "The selected path is not a directory:\n" + path);
            return;
        }

        try {
            IWorkbenchPage page = window.getActivePage();
            DirectoryTreeView view = (DirectoryTreeView) page.showView(DirectoryTreeView.ID);
            view.addRoot(dir);
        } catch (PartInitException ex) {
            MessageDialog.openError(window.getShell(), "Add Root Directory",
                    "Failed to open directory tree view:\n" + ex.getMessage());
        }
    }
}
