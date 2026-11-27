package jext.eclipse.shapeeditor.editors.dbf;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class DbfFileEditor extends EditorPart {

    public static final String ID = "jext.eclipse.shapeeditor.editors.dbf.DbfFileEditor";

    private Table table;

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof DbfFileEditorInput)) {
            throw new PartInitException("Invalid editor input: " + (input == null ? "null" : input.getName()));
        }
        setSite(site);
        setInput(input);
        setPartName(input.getName());
    }

    @Override
    public void createPartControl(Composite parent) {
        table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        loadDbfFile();
    }

    private void loadDbfFile() {
        DbfFileEditorInput input = (DbfFileEditorInput) getEditorInput();
        try {
            DbfFileModel model = new DbfFileModel();
            model.load(input.getFile());

            for (String name : model.getColumnNames()) {
                TableColumn column = new TableColumn(table, SWT.LEFT);
                column.setText(name);
            }

            for (Object[] row : model.getRows()) {
                TableItem item = new TableItem(table, SWT.NONE);
                for (int i = 0; i < row.length; i++) {
                    item.setText(i, displayValue(row[i]));
                }
            }

            for (TableColumn column : table.getColumns()) {
                column.pack();
                column.setWidth(Math.max(80, Math.min(column.getWidth(), 400)));
            }
        } catch (Exception ex) {
            table.removeAll();
            for (TableColumn column : table.getColumns()) {
                column.dispose();
            }
            MessageDialog.openError(
                    getSite().getShell(),
                    "Open DBF File",
                    "Failed to open '" + input.getName() + "':\n" + message(ex));
        }
    }

    private static String displayValue(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    @Override
    public void setFocus() {
        if (table != null && !table.isDisposed()) {
            table.setFocus();
        }
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
    }

    @Override
    public void doSaveAs() {
    }

    private static String message(Exception ex) {
        String message = ex.getMessage();
        return message == null || message.isEmpty() ? ex.getClass().getSimpleName() : message;
    }
}
