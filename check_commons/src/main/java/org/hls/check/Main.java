package org.hls.check;

import jext.logging.Logger;
import jext.name.Name;
import jext.name.Named;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.none.NoneProject;
import jext.sourcecode.project.util.SourceInfo;
import jext.sourcecode.resources.java.JavaSourceCode;
import jext.util.TimeUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Logger.configure();

        DependencyAnalyzer da = new DependencyAnalyzer();
        DependencyReport dr = new DependencyReport(da);

        dr.analysisTime("pippo", new JavaSourceCode(new File("ciccio.java"), NoneProject.module()), TimeUtils.parse("14:35"));
        dr.analysisTime("pluto", new JavaSourceCode(new File("ciccio.java"), NoneProject.module()), TimeUtils.parse("6:35"));

        dr.save(1);

        long t = TimeUtils.parse("100000m");
        long ts = System.currentTimeMillis();

    }
}
