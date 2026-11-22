package jext.eclipse.shapeeditor;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class ShapeEditorPerspective implements IPerspectiveFactory {

    public static final String ID = "jext.eclipse.shapeeditor.perspective";

    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(true);

        IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.72f, editorArea);
        right.addView(IPageLayout.ID_PROP_SHEET);
    }
}
