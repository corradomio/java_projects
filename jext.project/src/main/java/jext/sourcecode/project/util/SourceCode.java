package jext.sourcecode.project.util;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Source;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;

public abstract class SourceCode extends ResourceFile implements Source {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    protected String language;
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