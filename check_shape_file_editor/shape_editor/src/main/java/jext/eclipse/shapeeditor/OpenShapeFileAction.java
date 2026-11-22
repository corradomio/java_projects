package jext.eclipse.shapeeditor;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

import jext.eclipse.shapeeditor.editor.ShapeFileEditorInput;

public class OpenShapeFileAction extends Action {

    public static final String ID = "jext.eclipse.shapeeditor.actions.open";

    private final IWorkbenchWindow window;

    public OpenShapeFileAction(IWorkbenchWindow window) {
        this.window = window;
        setId(ID);
        setText("&Open...");
        setToolTipText("Open a shape file");
        setAccelerator(SWT.CTRL | 'O');
    }

    @Override
    public void run() {
        FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
        dialog.setText("Open Shape File");
        dialog.setFilterExtensions(new String[] { "*.shp", "*.*" });
        dialog.setFilterNames(new String[] { "Shape Files (*.shp)", "All Files (*.*)" });

        String path = dialog.open();
        if (path == null) {
            return;
        }

        try {
            IWorkbenchPage page = window.getActivePage();
            ShapeFileEditorInput input = new ShapeFileEditorInput(new File(path));
            page.openEditor(input, jext.eclipse.shapeeditor.editor.ShapeFileEditor.ID);
        } catch (Exception ex) {
            MessageDialog.openError(
                    window.getShell(),
                    "Open Shape File",
                    "Failed to open '" + path + "':\n"
                            + (ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage()));
        }
    }
}
