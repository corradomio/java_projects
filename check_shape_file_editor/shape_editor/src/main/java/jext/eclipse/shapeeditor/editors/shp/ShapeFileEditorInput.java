package jext.eclipse.shapeeditor.editors.shp;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class ShapeFileEditorInput implements IEditorInput {

    private final File file;

    public ShapeFileEditorInput(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return ImageDescriptor.getMissingImageDescriptor();
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return file.getAbsolutePath();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (adapter == File.class) {
            return (T) file;
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ShapeFileEditorInput)) return false;
        ShapeFileEditorInput other = (ShapeFileEditorInput) obj;
        return this.file != null && this.file.equals(other.file);
    }

    @Override
    public int hashCode() {
        return file == null ? 0 : file.hashCode();
    }
}
