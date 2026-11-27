package jext.eclipse.shapeeditor.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class ConfigurationDialog extends Dialog {

    private Text serverUrlText;
    private Text usernameText;
    private Text passwordText;
    private Button enableLoggingButton;
    private Button enableAutoSaveButton;
    private Text maxFileSizeText;
    private Button enableBackupButton;
    private Text saveDirectoryText;
    private Text loadDirectoryText;
    private Button compressFilesButton;

    public ConfigurationDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout(1, false));

        CTabFolder tabFolder = new CTabFolder(container, SWT.BORDER);
        tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tabFolder.setSimple(false);
        tabFolder.setUnselectedCloseVisible(false);

        createConfigurationTab(tabFolder);
        createAdvancedTab(tabFolder);
        createLoadSaveTab(tabFolder);
        createOffsetsTab(tabFolder);

        tabFolder.setSelection(0);

        return container;
    }

    private void createConfigurationTab(CTabFolder tabFolder) {
        CTabItem configurationTab = new CTabItem(tabFolder, SWT.NONE);
        configurationTab.setText("Configuration");

        Composite configComposite = new Composite(tabFolder, SWT.NONE);
        configComposite.setLayout(new GridLayout(2, false));
        configComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        createLabel(configComposite, "Server URL:");
        serverUrlText = new Text(configComposite, SWT.BORDER);
        serverUrlText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        serverUrlText.setText("http://localhost:8080");

        createLabel(configComposite, "Username:");
        usernameText = new Text(configComposite, SWT.BORDER);
        usernameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        createLabel(configComposite, "Password:");
        passwordText = new Text(configComposite, SWT.BORDER | SWT.PASSWORD);
        passwordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        configurationTab.setControl(configComposite);
    }

    private void createAdvancedTab(CTabFolder tabFolder) {
        CTabItem advancedTab = new CTabItem(tabFolder, SWT.NONE);
        advancedTab.setText("Advanced");

        Composite advancedComposite = new Composite(tabFolder, SWT.NONE);
        advancedComposite.setLayout(new GridLayout(1, false));
        advancedComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Group loggingGroup = new Group(advancedComposite, SWT.NONE);
        loggingGroup.setText("Logging");
        {
            loggingGroup.setLayout(new GridLayout(1, false));
            loggingGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

            enableLoggingButton = new Button(loggingGroup, SWT.CHECK);
            enableLoggingButton.setText("Enable detailed logging");
        }

        Group autoSaveGroup = new Group(advancedComposite, SWT.NONE);
        autoSaveGroup.setText("Auto-Save");
        {
            autoSaveGroup.setLayout(new GridLayout(2, false));
            autoSaveGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

            enableAutoSaveButton = new Button(autoSaveGroup, SWT.CHECK);
            enableAutoSaveButton.setText("Enable auto-save");
            enableAutoSaveButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

            createLabel(autoSaveGroup, "Maximum file size (MB):");
            maxFileSizeText = new Text(autoSaveGroup, SWT.BORDER);
            maxFileSizeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            maxFileSizeText.setText("100");
        }

        Group backupGroup = new Group(advancedComposite, SWT.NONE);
        backupGroup.setText("Backup");
        backupGroup.setLayout(new GridLayout(1, false));
        backupGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        enableBackupButton = new Button(backupGroup, SWT.CHECK);
        enableBackupButton.setText("Create backup before saving");

        advancedTab.setControl(advancedComposite);
    }

    private void createLoadSaveTab(CTabFolder tabFolder) {
        CTabItem loadSaveTab = new CTabItem(tabFolder, SWT.NONE);
        loadSaveTab.setText("Load&Save");

        Composite loadSaveComposite = new Composite(tabFolder, SWT.NONE);
        loadSaveComposite.setLayout(new GridLayout(2, false));
        loadSaveComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Group directoriesGroup = new Group(loadSaveComposite, SWT.NONE);
        directoriesGroup.setText("Directories");
        directoriesGroup.setLayout(new GridLayout(2, false));
        directoriesGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

        createLabel(directoriesGroup, "Load directory:");
        loadDirectoryText = new Text(directoriesGroup, SWT.BORDER);
        loadDirectoryText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        createLabel(directoriesGroup, "Save directory:");
        saveDirectoryText = new Text(directoriesGroup, SWT.BORDER);
        saveDirectoryText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Group optionsGroup = new Group(loadSaveComposite, SWT.NONE);
        optionsGroup.setText("File Options");
        optionsGroup.setLayout(new GridLayout(1, false));
        optionsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

        compressFilesButton = new Button(optionsGroup, SWT.CHECK);
        compressFilesButton.setText("Compress files when saving");

        loadSaveTab.setControl(loadSaveComposite);
    }

    public static class OffsetRow {
        private String distance;
        private String offset;

        public OffsetRow() {
            this("", "");
        }

        public OffsetRow(String distance, String offset) {
            this.distance = distance;
            this.offset = offset;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getOffset() {
            return offset;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }
    }

    private final List<OffsetRow> offsetRows = new ArrayList<>();
    private TableViewer offsetTableViewer;

    private void createOffsetsTab(CTabFolder tabFolder) {
        CTabItem offsetsTab = new CTabItem(tabFolder, SWT.NONE);
        offsetsTab.setText("Offsets");

        Composite offsetsComposite = new Composite(tabFolder, SWT.NONE);
        offsetsComposite.setLayout(new GridLayout(2, false));
        offsetsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        offsetTableViewer = new TableViewer(offsetsComposite,
                SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        Table table = offsetTableViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        TableViewerColumn distanceCol = new TableViewerColumn(offsetTableViewer, SWT.NONE);
        TableColumn distanceSwtCol = distanceCol.getColumn();
        distanceSwtCol.setText("distance");
        distanceSwtCol.setWidth(150);
        distanceCol.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((OffsetRow) element).getDistance();
            }
        });
        distanceCol.setEditingSupport(new EditingSupport(offsetTableViewer) {
            @Override
            protected CellEditor getCellEditor(Object element) {
                return createTabAwareCellEditor(table, (OffsetRow) element, 0);
            }

            @Override
            protected boolean canEdit(Object element) {
                return true;
            }

            @Override
            protected Object getValue(Object element) {
                String v = ((OffsetRow) element).getDistance();
                return v == null ? "" : v;
            }

            @Override
            protected void setValue(Object element, Object value) {
                ((OffsetRow) element).setDistance(value == null ? "" : value.toString());
                offsetTableViewer.update(element, null);
            }
        });

        TableViewerColumn offsetCol = new TableViewerColumn(offsetTableViewer, SWT.NONE);
        TableColumn offsetSwtCol = offsetCol.getColumn();
        offsetSwtCol.setText("offset");
        offsetSwtCol.setWidth(150);
        offsetCol.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((OffsetRow) element).getOffset();
            }
        });
        offsetCol.setEditingSupport(new EditingSupport(offsetTableViewer) {
            @Override
            protected CellEditor getCellEditor(Object element) {
                return createTabAwareCellEditor(table, (OffsetRow) element, 1);
            }

            @Override
            protected boolean canEdit(Object element) {
                return true;
            }

            @Override
            protected Object getValue(Object element) {
                String v = ((OffsetRow) element).getOffset();
                return v == null ? "" : v;
            }

            @Override
            protected void setValue(Object element, Object value) {
                ((OffsetRow) element).setOffset(value == null ? "" : value.toString());
                offsetTableViewer.update(element, null);
            }
        });

        offsetTableViewer.setContentProvider(new IStructuredContentProvider() {
            @Override
            public Object[] getElements(Object inputElement) {
                return offsetRows.toArray();
            }
        });
        offsetTableViewer.setInput(offsetRows);

        Composite buttonsComposite = new Composite(offsetsComposite, SWT.NONE);
        buttonsComposite.setLayout(new GridLayout(1, false));
        buttonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));

        Button addButton = new Button(buttonsComposite, SWT.PUSH);
        addButton.setText("Add Row");
        addButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        addButton.addListener(SWT.Selection, e -> addOffsetRow());

        Button deleteButton = new Button(buttonsComposite, SWT.PUSH);
        deleteButton.setText("Delete Row");
        deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        deleteButton.addListener(SWT.Selection, e -> deleteCurrentOffsetRow());

        offsetsTab.setControl(offsetsComposite);
    }

    private CellEditor createTabAwareCellEditor(Table table, OffsetRow row, int columnIndex) {
        TextCellEditor editor = new TextCellEditor(table);
        Control control = editor.getControl();
        control.addTraverseListener((TraverseEvent e) -> {
            if (e.detail == SWT.TRAVERSE_TAB_NEXT && (e.stateMask & SWT.SHIFT) == 0) {
                e.doit = false;
                e.detail = SWT.TRAVERSE_NONE;
                // Commit the editor value into the model so the next cell starts fresh.
                Object value = editor.getValue();
                if (columnIndex == 0) {
                    row.setDistance(value == null ? "" : value.toString());
                } else {
                    row.setOffset(value == null ? "" : value.toString());
                }
                offsetTableViewer.update(row, null);
                // Close the editor on the UI thread, then advance focus.
                control.getDisplay().asyncExec(() -> {
                    if (offsetTableViewer.isCellEditorActive()) {
                        offsetTableViewer.applyEditorValue();
                    }
                    advanceFromCell(row, columnIndex);
                });
            }
        });
        return editor;
    }

    private void advanceFromCell(OffsetRow currentRow, int currentColumn) {
        if (currentColumn == 0) {
            // distance -> offset on the same row.
            offsetTableViewer.editElement(currentRow, 1);
        } else {
            // offset -> distance on the next row; create it if needed.
            int idx = offsetRows.indexOf(currentRow);
            OffsetRow nextRow;
            if (idx >= 0 && idx + 1 < offsetRows.size()) {
                nextRow = offsetRows.get(idx + 1);
            } else {
                nextRow = new OffsetRow();
                if (idx >= 0) {
                    offsetRows.add(idx + 1, nextRow);
                } else {
                    offsetRows.add(nextRow);
                }
                offsetTableViewer.refresh();
            }
            offsetTableViewer.editElement(nextRow, 0);
        }
    }

    private void addOffsetRow() {
        OffsetRow newRow = new OffsetRow();
        IStructuredSelection sel = offsetTableViewer.getStructuredSelection();
        Object first = sel.getFirstElement();
        if (first instanceof OffsetRow) {
            int idx = offsetRows.indexOf(first);
            if (idx >= 0) {
                offsetRows.add(idx + 1, newRow);
            } else {
                offsetRows.add(newRow);
            }
        } else {
            offsetRows.add(newRow);
        }
        offsetTableViewer.refresh();
        offsetTableViewer.editElement(newRow, 0);
    }

    private void deleteCurrentOffsetRow() {
        IStructuredSelection sel = offsetTableViewer.getStructuredSelection();
        if (sel.isEmpty()) {
            return;
        }
        for (Object o : sel.toArray()) {
            offsetRows.remove(o);
        }
        offsetTableViewer.refresh();
    }

    private Label createLabel(Composite parent, String text) {
        Label label = new Label(parent, SWT.NONE);
        label.setText(text);
        label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        return label;
    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Configuration");
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, OK, "OK", true);
        createButton(parent, CANCEL, "Cancel", false);
    }

    @Override
    protected boolean isResizable() {
        return true;
    }

    @Override
    protected int getDialogBoundsStrategy() {
        return DIALOG_PERSISTSIZE;
    }
}
