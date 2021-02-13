package jext.sourcecode.resources;

import jext.sourcecode.resources.java.JavaSourceCode;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.util.SourceInfo;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;

public abstract class SourceCode extends ResourceFile implements Source {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final String EXT_JAVA = ".java";
    private static final String EXT_SCALA = ".scala";
    private static final String EXT_ASPECT = ".aj";

    public static Source newSource(File sourceFile, Module module) {
        String name = sourceFile.getName();

        if (name.endsWith(EXT_JAVA))
            return new JavaSourceCode(sourceFile, module);
        // if (name.endsWith(EXT_SCALA))
        //     return new ScalaSourceCode(sourceFile, module);
        // if (name.endsWith(EXT_ASPECT))
        //     return new AspectSourceCode(sourceFile, module);
        else
            return null;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // private Module module;
    // private File file;
    private String language;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    protected SourceCode(File file, Module module) {
        super(file, module);

        // this.module = module;
        // this.file = file;
        //
        // String rpath = FileUtils.relativePathNoExt(module.getDirectory(), getFile());
        // setName(new PathName(rpath));
        //

        String name = file.getName();
        this.language = name.substring(name.lastIndexOf(".")+1);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // @Override
    // public String getPath() {
    //     return PathUtils.normalize(file.getAbsolutePath());
    // }

    // @Override
    // public File getFile() {
    //     return file;
    // }

    // @Override
    // public Module getModule() {
    //     return module;
    // }

    // @Override
    // public String getDigest() {
    //     return FileUtils.digest(file);
    // }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public SourceInfo getSourceInfo() {
        SourceInfo info = new SourceInfo();

        List<String> lines = FileUtils.toStrings(file);

        info.count = 1;
        info.bytes = file.length();
        info.totalLines = lines.size();
        info.blankLines = lines
            .stream()
            .map(String::trim)
            .filter(String::isEmpty)
            .count();
        info.codeLines = info.totalLines - info.blankLines;

        return info;
    }

}
