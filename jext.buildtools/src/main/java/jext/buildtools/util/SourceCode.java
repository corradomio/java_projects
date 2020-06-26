package jext.buildtools.util;

import jext.buildtools.Source;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.buildtools.Module;

import java.io.File;

public abstract class SourceCode extends NamedObject implements Source {

    private static final String EXT_JAVA = ".java";
    // private static final String EXT_SCALA = ".scala";

    public static Source newSource(File sourceFile, Module module) {
        String name = sourceFile.getName();

        if (name.endsWith(EXT_JAVA))
            return new JavaSourceCode(sourceFile, module);
            // if (name.endsWith(EXT_SCALA))
            //     return new ScalaSourceCode(sourceFile, module);
        else {
            Logger.getLogger(SourceCode.class).errorf("Unsupported source code %s", sourceFile);
            return null;
        }
    }


    private Logger logger = Logger.getLogger(getClass());

    private Module module;
    private File file;
    private String path;
    private String language;

    protected SourceCode(File file, Module module) {
        super(PathName.empty());

        this.module = module;
        this.file = file;
        this.path = PathUtils.normalize(file.getAbsolutePath());

        String rpath = PathUtils.relativePath(module.getPath(), getPath());
        setName(new PathName(rpath));

        String name = file.getName();
        this.language = name.substring(name.lastIndexOf(".")+1);
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Module getModule() {
        return module;
    }

    @Override
    public String getDigest() {
        return FileUtils.digest(file);
    }

    @Override
    public String getLanguage() {
        return language;
    }

    // @Override
    public String getId() {
        return file.getAbsolutePath();
    }

    @Override
    public boolean isValid() {
        return file.exists() && file.isFile();
    }
}
