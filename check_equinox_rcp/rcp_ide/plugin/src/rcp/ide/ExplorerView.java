package rcp.ide;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;

public final class ExplorerView extends ViewPart {
    public static final String ID = "rcp.ide.explorer";
    private static final long MAX_TEXT_BYTES = 8 * 1024 * 1024;
    private TreeViewer tree;
    private TabFolder tabs;
    private TabItem textTab;
    private StyledText editor;
    private Path currentFile;
    private boolean dirty;

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new FillLayout());
        SashForm split = new SashForm(parent, SWT.HORIZONTAL);
        split.setData("name", "explorer.split");
        tree = new TreeViewer(split, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
        tree.getTree().setData("name", "explorer.tree");
        tree.setContentProvider(new FileTreeProvider());
        tree.setLabelProvider(new LabelProvider() {
            @Override public String getText(Object element) {
                File file = (File) element;
                return file.getName().isEmpty() ? file.getPath() : file.getName();
            }
        });
        tree.setInput(File.listRoots());
        tree.addDoubleClickListener(event -> {
            Object selected = tree.getStructuredSelection().getFirstElement();
            if (selected instanceof File) {
                File file = (File) selected;
                if (file.isDirectory()) tree.setExpandedState(file, !tree.getExpandedState(file));
                else openPath(file.toPath());
            }
        });

        tabs = new TabFolder(split, SWT.NONE);
        tabs.setData("name", "explorer.tabs");
        textTab = new TabItem(tabs, SWT.NONE);
        textTab.setText("Text Editor");
        editor = new StyledText(tabs, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        editor.setData("name", "explorer.editor");
        editor.setEditable(false);
        editor.addModifyListener(event -> {
            if (currentFile != null) { dirty = true; updateTitle(); }
        });
        editor.addListener(SWT.KeyDown, event -> {
            if ((event.stateMask & SWT.MOD1) != 0 && (event.keyCode == 's' || event.keyCode == 'S')) {
                event.doit = false;
                save();
            }
        });
        textTab.setControl(editor);
        createCanvas();
        split.setWeights(new int[] { 28, 72 });
        status("Double-click a file or choose File > Open. Save text with Ctrl+S.");
    }

    private void createCanvas() {
        TabItem graphicTab = new TabItem(tabs, SWT.NONE);
        graphicTab.setText("Graphic Canvas");
        Canvas canvas = new Canvas(tabs, SWT.BORDER | SWT.DOUBLE_BUFFERED);
        canvas.setData("name", "explorer.canvas");
        canvas.setBackground(canvas.getDisplay().getSystemColor(SWT.COLOR_WHITE));
        canvas.setToolTipText("Drag with the left mouse button to draw. Right-click to clear.");
        List<List<Point>> strokes = new ArrayList<>();
        List<Point> active = new ArrayList<>();
        canvas.addListener(SWT.MouseDown, event -> {
            if (event.button == 3) { strokes.clear(); active.clear(); canvas.redraw(); }
            if (event.button == 1) {
                active.clear();
                active.add(new Point(event.x, event.y));
                strokes.add(new ArrayList<>(active));
                canvas.redraw();
            }
        });
        canvas.addListener(SWT.MouseMove, event -> {
            if ((event.stateMask & SWT.BUTTON1) != 0 && !active.isEmpty()) {
                active.add(new Point(event.x, event.y));
                strokes.get(strokes.size() - 1).add(new Point(event.x, event.y));
                canvas.redraw();
            }
        });
        canvas.addListener(SWT.MouseUp, event -> active.clear());
        canvas.addPaintListener(event -> {
            event.gc.setAntialias(SWT.ON);
            event.gc.setForeground(canvas.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
            event.gc.setLineWidth(2);
            for (List<Point> stroke : strokes) {
                Point previous = stroke.get(0);
                event.gc.drawPoint(previous.x, previous.y);
                for (int i = 1; i < stroke.size(); i++) {
                    Point point = stroke.get(i);
                    event.gc.drawLine(previous.x, previous.y, point.x, point.y);
                    previous = point;
                }
            }
        });
        graphicTab.setControl(canvas);
    }

    public void openFile() {
        FileDialog dialog = new FileDialog(getSite().getShell(), SWT.OPEN);
        dialog.setText("Open UTF-8 text file");
        Object selected = tree.getStructuredSelection().getFirstElement();
        if (selected instanceof File) {
            File file = (File) selected;
            dialog.setFilterPath(file.isDirectory() ? file.getPath() : file.getParent());
            if (file.isFile()) dialog.setFileName(file.getName());
        }
        String filename = dialog.open();
        if (filename != null) openPath(Path.of(filename));
    }

    private void openPath(Path path) {
        if (currentFile != null && currentFile.equals(path.toAbsolutePath())) {
            tabs.setSelection(textTab);
            editor.setFocus();
            return;
        }
        try {
            if (Files.size(path) > MAX_TEXT_BYTES) throw new IOException("Files larger than 8 MiB are not supported.");
            String text = Files.readString(path, StandardCharsets.UTF_8);
            if (text.indexOf('\0') >= 0) throw new IOException("This appears to be a binary file. Select a UTF-8 text file.");
            if (!confirmSave()) return;
            currentFile = path.toAbsolutePath();
            editor.setText(text);
            editor.setEditable(true);
            dirty = false;
            updateTitle();
            tabs.setSelection(textTab);
            editor.setFocus();
            status(currentFile.toString());
        } catch (IOException | SecurityException exception) {
            error("Cannot open file", exception);
        }
    }

    public void closeFile() {
        if (!confirmSave()) return;
        currentFile = null;
        editor.setText("");
        editor.setEditable(false);
        dirty = false;
        updateTitle();
        status("File closed.");
    }

    public boolean confirmSave() {
        if (!dirty) return true;
        MessageDialog dialog = new MessageDialog(getSite().getShell(), "Unsaved changes", null,
                "Save changes to " + currentFile.getFileName() + "?", MessageDialog.QUESTION,
                new String[] { "Save", "Discard", "Cancel" }, 0);
        int answer = dialog.open();
        return answer == 0 ? save() : answer == 1;
    }

    private boolean save() {
        if (currentFile == null || !dirty) return true;
        try {
            Files.writeString(currentFile, editor.getText(), StandardCharsets.UTF_8);
            dirty = false;
            updateTitle();
            status("Saved " + currentFile);
            return true;
        } catch (IOException | SecurityException exception) {
            error("Cannot save file", exception);
            return false;
        }
    }

    private void updateTitle() {
        textTab.setText("Text Editor" + (currentFile == null ? "" : " - " + currentFile.getFileName())
                + (dirty ? " *" : ""));
    }

    private void error(String title, Exception exception) {
        MessageDialog.openError(getSite().getShell(), title,
                exception.getMessage() == null ? exception.toString() : exception.getMessage());
    }

    private void status(String message) { getViewSite().getActionBars().getStatusLineManager().setMessage(message); }

    @Override public void setFocus() { if (editor != null) editor.setFocus(); }

    private static final class FileTreeProvider implements ITreeContentProvider {
        @Override public Object[] getElements(Object input) { return (File[]) input; }
        @Override public Object getParent(Object element) { return ((File) element).getParentFile(); }
        @Override public boolean hasChildren(Object element) { return ((File) element).isDirectory(); }
        @Override public Object[] getChildren(Object element) {
            // Enumerate only expanded directories; inaccessible directories appear empty.
            File[] children;
            try { children = ((File) element).listFiles(); }
            catch (SecurityException exception) { return new Object[0]; }
            if (children == null) return new Object[0];
            Arrays.sort(children, Comparator.comparing(File::isFile)
                    .thenComparing(File::getName, String.CASE_INSENSITIVE_ORDER));
            return children;
        }
    }
}
