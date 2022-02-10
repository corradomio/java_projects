package org.hls.check;

import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectDifferences;
import jext.sourcecode.project.ProjectFactory;
import jext.sourcecode.project.ProjectComparator;
import jext.util.PropertiesUtils;

import java.io.File;
import java.io.IOException;

public class CompareProjects {

    public static void main(String[] args) throws IOException {

        Project psrc = ProjectFactory.newProject(
                new File("D:\\Projects.github\\java_projects\\check_java_syntax\\.spl\\b9ee0f37-source-project-r00.json"),
                PropertiesUtils.empty());

        Project pdst = ProjectFactory.newProject(
                new File("D:\\Projects.github\\java_projects\\check_java_syntax\\.spl\\b9ee0f37-source-project-r01.json"),
                PropertiesUtils.empty());

        // ProjectComparator pcomp = ProjectComparator.compare(psrc, pdst);
        ProjectComparator pcomp = ProjectComparator.compare(null, psrc);

        File jsonFile = new File("test.json");
        pcomp.save(jsonFile);
        ProjectComparator pcmp2 = ProjectComparator.load(jsonFile);

    }
}
