package jext.eclipse.shapeeditor;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import jext.eclipse.shapeeditor.views.DirectoryTreeView;

public class ShapeEditorPerspective implements IPerspectiveFactory {

    public static final String ID = "jext.eclipse.shapeeditor.perspective";

    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(true);

        layout.addStandaloneView(DirectoryTreeView.ID,false, IPageLayout.LEFT, 0.25f, editorArea);

        IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.72f, editorArea);
        right.addView(IPageLayout.ID_PROP_SHEET);
    }
}
