package jext.eclipse.shapeeditor.views;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import jext.eclipse.shapeeditor.editors.csv.CsvFileEditor;
import jext.eclipse.shapeeditor.editors.csv.CsvFileEditorInput;
import jext.eclipse.shapeeditor.editors.dbf.DbfFileEditor;
import jext.eclipse.shapeeditor.editors.dbf.DbfFileEditorInput;
import jext.eclipse.shapeeditor.editors.shp.ShapeFileEditor;
import jext.eclipse.shapeeditor.editors.shp.ShapeFileEditorInput;
import jext.eclipse.shapeeditor.editors.xml.XmlFileEditor;
import jext.eclipse.shapeeditor.editors.xml.XmlFileEditorInput;

public class DirectoryTreeView extends ViewPart {

    public static final String ID = "jext.eclipse.shapeeditor.views.directoryTree";

    private static DirectoryTreeView instance;

    private TreeViewer viewer;
    private final List<File> roots = new ArrayList<>();

    public DirectoryTreeView() {
        instance = this;
    }

    public static DirectoryTreeView getInstance() {
        return instance;
    }

    @Override
    public void createPartControl(Composite parent) {
        viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        viewer.setUseHashlookup(true);
        viewer.setContentProvider(new FileTreeContentProvider());
        viewer.setLabelProvider(new FileTreeLabelProvider());
        viewer.setInput(roots);

        hookContextMenu();
        hookDoubleClick();
    }

    @Override
    public void setFocus() {
        if (viewer != null && !viewer.getControl().isDisposed()) {
            viewer.getControl().setFocus();
        }
    }

    @Override
    public void dispose() {
        if (instance == this) {
            instance = null;
        }
        super.dispose();
    }

    public void addRoot(File dir) {
        if (dir == null || !dir.isDirectory()) {
            return;
        }
        for (File f : roots) {
            if (f.getAbsolutePath().equalsIgnoreCase(dir.getAbsolutePath())) {
                return;
            }
        }
        roots.add(dir);
        viewer.refresh();
        viewer.expandToLevel(dir, 1);
    }

    private void removeRoot(File dir) {
        roots.removeIf(f -> f.getAbsolutePath().equalsIgnoreCase(dir.getAbsolutePath()));
        viewer.refresh();
    }

    private void hookContextMenu() {
        viewer.getTree().addListener(SWT.MenuDetect, event -> {
            Point point = viewer.getTree().toControl(event.x, event.y);
            TreeItem item = viewer.getTree().getItem(point);
            if (item != null && item.getData() instanceof File) {
                viewer.setSelection(new StructuredSelection(item.getData()));
            }
        });

        MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(manager -> {
            ITreeSelection sel = (ITreeSelection) viewer.getSelection();
            if (sel.isEmpty()) {
                return;
            }
            Object first = sel.getFirstElement();
            if (!(first instanceof File)) {
                return;
            }

            File file = (File) first;
            if (file.isDirectory()) {
                addDirectoryMenuActions(manager, file);
            } else {
                addFileMenuActions(manager, file);
            }
        });
        Menu menu = menuMgr.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
    }

    private void addDirectoryMenuActions(IMenuManager manager, File directory) {
        manager.add(createCopyAction("Copy Directory Name", displayName(directory)));
        manager.add(createCopyAction("Copy Directory Full Path", directory.getAbsolutePath()));
        manager.add(new Separator());
        manager.add(new Action("Expand") {
            @Override
            public void run() {
                viewer.setExpandedState(directory, true);
            }
        });
        manager.add(new Action("Expand Recursively") {
            @Override
            public void run() {
                viewer.expandToLevel(directory, TreeViewer.ALL_LEVELS);
            }
        });
        manager.add(new Action("Collapse") {
            @Override
            public void run() {
                viewer.setExpandedState(directory, false);
            }
        });

        if (isRoot(directory)) {
            manager.add(new Separator());
            manager.add(new Action("Remove Root") {
                @Override
                public void run() {
                    removeRoot(directory);
                }
            });
        }
    }

    private void addFileMenuActions(IMenuManager manager, File file) {
        manager.add(createCopyAction("Copy File Name", displayName(file)));
        manager.add(createCopyAction("Copy File Full Path", file.getAbsolutePath()));
        if (isSupportedFile(file)) {
            manager.add(new Separator());
            manager.add(new Action("Open in Editor") {
                @Override
                public void run() {
                    openFile(file);
                }
            });
        }
    }

    private Action createCopyAction(String label, String text) {
        return new Action(label) {
            @Override
            public void run() {
                Clipboard clipboard = new Clipboard(viewer.getControl().getDisplay());
                try {
                    clipboard.setContents(
                            new Object[] { text },
                            new Transfer[] { TextTransfer.getInstance() });
                } finally {
                    clipboard.dispose();
                }
            }
        };
    }

    private static String displayName(File file) {
        String name = file.getName();
        return name == null || name.isEmpty() ? file.getAbsolutePath() : name;
    }

    private void hookDoubleClick() {
        viewer.addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(DoubleClickEvent event) {
                ITreeSelection sel = (ITreeSelection) event.getSelection();
                Object first = sel.getFirstElement();
                if (!(first instanceof File)) {
                    return;
                }
                File f = (File) first;
                if (f.isDirectory()) {
                    boolean expanded = viewer.getExpandedState(f);
                    viewer.setExpandedState(f, !expanded);
                    return;
                }
                openFile(f);
            }
        });
    }

    private void openFile(File f) {
        if (isShapeFile(f)) {
            openInShapeEditor(f);
        } else if (isXmlFile(f)) {
            openInXmlEditor(f);
        } else if (isDbfFile(f)) {
            openInDbfEditor(f);
        } else if (isCsvFile(f)) {
            openInCsvEditor(f);
        } else {
            MessageDialog.openError(
                    viewer.getControl().getShell(),
                    "Unsupported File Type",
                    "The file type is not supported:\n" + f.getName()
                            + "\n\nOnly .shp, .xml, .dbf and .csv files can be opened.");
        }
    }

    private static boolean isSupportedFile(File f) {
        return isShapeFile(f) || isXmlFile(f) || isDbfFile(f) || isCsvFile(f);
    }

    private static boolean isShapeFile(File f) {
        return hasExtension(f, "shp");
    }

    private static boolean isXmlFile(File f) {
        return hasExtension(f, "xml");
    }

    private static boolean isDbfFile(File f) {
        return hasExtension(f, "dbf");
    }

    private static boolean isCsvFile(File f) {
        return hasExtension(f, "csv");
    }

    private static boolean hasExtension(File f, String extension) {
        String name = f.getName();
        int dot = name.lastIndexOf('.');
        return dot >= 0 && extension.equalsIgnoreCase(name.substring(dot + 1));
    }

    private void openInShapeEditor(File f) {
        try {
            IWorkbenchPage page = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage();
            ShapeFileEditorInput input = new ShapeFileEditorInput(f);
            page.openEditor(input, ShapeFileEditor.ID);
        } catch (Exception ex) {
            MessageDialog.openError(
                    viewer.getControl().getShell(),
                    "Open Shape File",
                    "Failed to open '" + f.getName() + "':\n"
                            + (ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage()));
        }
    }

    private void openInXmlEditor(File f) {
        try {
            IWorkbenchPage page = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage();
            XmlFileEditorInput input = new XmlFileEditorInput(f);
            page.openEditor(input, XmlFileEditor.ID);
        } catch (Exception ex) {
            MessageDialog.openError(
                    viewer.getControl().getShell(),
                    "Open XML File",
                    "Failed to open '" + f.getName() + "':\n"
                            + (ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage()));
        }
    }

    private void openInDbfEditor(File f) {
        try {
            IWorkbenchPage page = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage();
            DbfFileEditorInput input = new DbfFileEditorInput(f);
            page.openEditor(input, DbfFileEditor.ID);
        } catch (Exception ex) {
            MessageDialog.openError(
                    viewer.getControl().getShell(),
                    "Open DBF File",
                    "Failed to open '" + f.getName() + "':\n"
                            + (ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage()));
        }
    }

    private void openInCsvEditor(File f) {
        try {
            IWorkbenchPage page = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage();
            CsvFileEditorInput input = new CsvFileEditorInput(f);
            page.openEditor(input, CsvFileEditor.ID);
        } catch (Exception ex) {
            MessageDialog.openError(
                    viewer.getControl().getShell(),
                    "Open CSV File",
                    "Failed to open '" + f.getName() + "':\n"
                            + (ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage()));
        }
    }

    private boolean isRoot(File f) {
        for (File r : roots) {
            if (r.getAbsolutePath().equalsIgnoreCase(f.getAbsolutePath())) {
                return true;
            }
        }
        return false;
    }

    private static class FileTreeContentProvider implements ITreeContentProvider {

        @Override
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof List) {
                return ((List<?>) inputElement).toArray();
            }
            return new Object[0];
        }

        @Override
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof File) {
                File dir = (File) parentElement;
                if (dir.isDirectory()) {
                    File[] files = dir.listFiles();
                    if (files == null) {
                        return new Object[0];
                    }
                    Arrays.sort(files, Comparator
                            .comparing(File::isFile)
                            .thenComparing(File::getName, String.CASE_INSENSITIVE_ORDER));
                    return files;
                }
            }
            return new Object[0];
        }

        @Override
        public Object getParent(Object element) {
            if (element instanceof File) {
                return ((File) element).getParentFile();
            }
            return null;
        }

        @Override
        public boolean hasChildren(Object element) {
            if (element instanceof File) {
                File f = (File) element;
                if (f.isDirectory()) {
                    File[] files = f.listFiles();
                    return files != null && files.length > 0;
                }
            }
            return false;
        }

        @Override
        public void dispose() {
        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }

    private static class FileTreeLabelProvider implements ILabelProvider {

        private final List<ILabelProviderListener> listeners = new ArrayList<>();

        @Override
        public String getText(Object element) {
            if (element instanceof File) {
                return displayName((File) element);
            }
            return String.valueOf(element);
        }

        @Override
        public Image getImage(Object element) {
            ISharedImages shared = PlatformUI.getWorkbench().getSharedImages();
            if (element instanceof File) {
                File f = (File) element;
                if (f.isDirectory()) {
                    return shared.getImage(ISharedImages.IMG_OBJ_FOLDER);
                }
                return shared.getImage(ISharedImages.IMG_OBJ_FILE);
            }
            return null;
        }

        @Override
        public void addListener(ILabelProviderListener listener) {
            listeners.add(listener);
        }

        @Override
        public void removeListener(ILabelProviderListener listener) {
            listeners.remove(listener);
        }

        @Override
        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        @Override
        public void dispose() {
            listeners.clear();
        }
    }
}
