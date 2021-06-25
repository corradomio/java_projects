package org.hls.check;

import jext.sourcecode.project.ProjectDifferences;

import java.io.File;

public class CheckDifferences {

    public static void main(String[] args) {
        ProjectDifferences pd = new ProjectDifferences();
        // pd.compareProjects(new File("project-info-0000.json"), new File("project-info.json"));
        // pd.compareProjects(new File("project-info-0001.json"), new File("project-info.json"));
        // pd.compareProjects(new File("project-info-0001.json"), new File("project-info-0000.json"));
        pd.compareProjects(new File("project-info-0002.json"), new File("project-info.json"));
        // pd.compareProjects(new File("project-info.json"), new File("project-info-0000.json"));
        // pd.compareProjects(
        //     new File("D:\\Projects.github\\java_projects\\check_modules\\.spl\\project-info-0000.json"),
        //     new File("D:\\Projects.github\\java_projects\\check_modules\\.spl\\project-info.json"));
        pd.dump();
    }
}
