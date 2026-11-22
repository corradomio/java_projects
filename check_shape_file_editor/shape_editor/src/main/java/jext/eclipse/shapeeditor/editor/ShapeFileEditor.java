package jext.eclipse.shapeeditor.editor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class ShapeFileEditor extends EditorPart {

    public static final String ID = "jext.eclipse.shapeeditor.editor";

    private FigureCanvas canvas;
    private ShapeViewerFigure figure;

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof ShapeFileEditorInput)) {
            throw new PartInitException("Invalid editor input: " + (input == null ? "null" : input.getName()));
        }
        setSite(site);
        setInput(input);
        setPartName(input.getName());
    }

    @Override
    public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
        canvas = new FigureCanvas(parent, SWT.NONE);

        figure = new ShapeViewerFigure();
        canvas.setContents(figure);

        loadShapeFile();

        canvas.addListener(SWT.MouseWheel, e -> {
            double factor = e.count > 0 ? 1.2 : 1 / 1.2;
            figure.zoomBy(factor);
        });

        final int[] lastPosition = new int[] { -1, -1 };
        canvas.addListener(SWT.MouseDown, e -> {
            if (e.button == 1) {
                lastPosition[0] = e.x;
                lastPosition[1] = e.y;
            }
        });
        canvas.addListener(SWT.MouseMove, e -> {
            if ((e.stateMask & SWT.BUTTON1) != 0 && lastPosition[0] >= 0) {
                figure.panBy(e.x - lastPosition[0], e.y - lastPosition[1]);
                lastPosition[0] = e.x;
                lastPosition[1] = e.y;
            }
        });
        canvas.addListener(SWT.MouseUp, e -> {
            lastPosition[0] = -1;
            lastPosition[1] = -1;
        });

        setPartName(getEditorInput().getName());
    }

    private void loadShapeFile() {
        ShapeFileEditorInput input = (ShapeFileEditorInput) getEditorInput();
        try {
            ShapeFileModel model = new ShapeFileModel();
            model.load(input.getFile());

            List<org.locationtech.jts.geom.Geometry> geometries = model.getGeometries();
            figure.setGeometries(geometries);
        } catch (Exception ex) {
            MessageDialog.openError(
                    getSite().getShell(),
                    "Open Shape File",
                    "Failed to open '" + input.getName() + "':\n" + message(ex));
        }
    }

    @Override
    public void setFocus() {
        if (canvas != null && !canvas.isDisposed()) {
            canvas.setFocus();
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
        if (ex == null) return "Unknown error";
        Throwable root = ex;
        while (root.getCause() != null && root.getCause() != root) {
            root = root.getCause();
        }
        String m = root.getMessage();
        return m == null || m.isEmpty() ? root.getClass().getSimpleName() : m;
    }
}
