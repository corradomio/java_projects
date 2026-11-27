package jext.eclipse.shapeeditor.editors.csv;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class CsvFileEditorInput implements IEditorInput {

    private final File file;

    public CsvFileEditorInput(File file) {
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
        if (!(obj instanceof CsvFileEditorInput)) return false;
        CsvFileEditorInput other = (CsvFileEditorInput) obj;
        return file != null && file.equals(other.file);
    }

    @Override
    public int hashCode() {
        return file == null ? 0 : file.hashCode();
    }
}
