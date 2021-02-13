package jext.sourcecode.resources.libraries;

import jext.name.Name;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.RefType;
import jext.util.FileUtils;
import jext.util.JarUtils;

import java.io.File;
import java.util.List;
import java.util.Set;

public class AarLibrary extends JarLibrary {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private File aarFile;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public AarLibrary(File aarFile, Module module) {
        super(toJarFile(aarFile), module);
        this.aarFile = aarFile;
    }

    private static File toJarFile(File aarFile) {
        String name = FileUtils.getNameWithoutExt(aarFile);
        return new File(aarFile.getParentFile(), name + ".jar");
    }

    @Override
    public String getDigest() {
        extractClasses();
        return super.getDigest();
    }

    @Override
    public List<File> getFiles() {
        extractClasses();
        return super.getFiles();
    }

    @Override
    public Set<RefType> getTypes() {
        extractClasses();
        return super.getTypes();
    }

    @Override
    public boolean contains(Name typeName) {
        extractClasses();
        return super.contains(typeName);
    }

    private synchronized void extractClasses() {
        if (!libraryFile.exists())
            JarUtils.extractJarFromAar(aarFile);
    }

}
