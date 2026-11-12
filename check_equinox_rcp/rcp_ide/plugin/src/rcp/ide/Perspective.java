package rcp.ide;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public final class Perspective implements IPerspectiveFactory {
    @Override
    public void createInitialLayout(IPageLayout layout) {
        layout.setEditorAreaVisible(false);
        layout.addStandaloneView(ExplorerView.ID, false, IPageLayout.LEFT, 1.0f, layout.getEditorArea());
        layout.getViewLayout(ExplorerView.ID).setCloseable(false);
        layout.getViewLayout(ExplorerView.ID).setMoveable(false);
    }
}
