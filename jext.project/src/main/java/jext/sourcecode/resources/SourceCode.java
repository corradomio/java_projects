package jext.sourcecode.resources;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.util.SourceInfo;
import jext.sourcecode.resources.java.JavaSourceCode;
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
    private SourceInfo sinfo;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    protected SourceCode(File file, Module module) {
        super(file, module);

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
        if (sinfo != null)
            return sinfo;

        sinfo = new SourceInfo();

        List<String> lines = FileUtils.toStrings(file);

        sinfo.count = 1;
        sinfo.bytes = file.length();
        sinfo.totalLines = lines.size();
        sinfo.blankLines = lines
            .stream()
            .map(String::trim)
            .filter(String::isEmpty)
            .count();
        sinfo.codeLines = sinfo.totalLines - sinfo.blankLines;

        return sinfo;
    }

}
