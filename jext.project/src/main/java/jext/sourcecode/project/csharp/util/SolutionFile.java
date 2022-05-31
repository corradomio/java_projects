package jext.sourcecode.project.csharp.util;

import jext.util.FileUtils;

import javax.naming.ldap.HasControls;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * ".sln" file
 *
 * Custom syntax
 */
/*
Microsoft Visual Studio Solution File, Format Version 12.00
# comments ...
VisualStudioVersion = 16.0.29806.167
MinimumVisualStudioVersion = 15.0.26730.8

Project("{2150E333-8FDC-42A3-9474-1A3956D46DE8}") = "dotnet", "dotnet", "{8CA61D33-3590-4024-A304-7B1F75B50653}"
	ProjectSection(SolutionItems) = preProject
		src\dotnet\Directory.Build.props = src\dotnet\Directory.Build.props
	EndProjectSection
EndProject

Project("{2150E333-8FDC-42A3-9474-1A3956D46DE8}") = ".build", ".build", "{4016BDAB-6C33-4D1E-9439-57B416EA45D5}"
	ProjectSection(SolutionItems) = preProject
		azure-pipelines.yml = azure-pipelines.yml
		build = build
		build.bat = build.bat
		build.ps1 = build.ps1
		.build\runbuild.ps1 = .build\runbuild.ps1
		.build\dependencies.props = .build\dependencies.props
		.build\nuget.props = .build\nuget.props
		.build\release.targets = .build\release.targets
		.build\TestReferences.Common.targets = .build\TestReferences.Common.targets
		TestTargetFramework.props = TestTargetFramework.props
	EndProjectSection
EndProject

Project("{2150E333-8FDC-42A3-9474-1A3956D46DE8}") = "Solution Items", "Solution Items", "{4DF0A2A1-B9C7-4EE5-BAF0-BEEF53E34220}"
	ProjectSection(SolutionItems) = preProject
		.asf.yaml = .asf.yaml
		.editorconfig = .editorconfig
		.rat-excludes = .rat-excludes
		CHANGES.txt = CHANGES.txt
		CONTRIBUTING.md = CONTRIBUTING.md
		Directory.Build.props = Directory.Build.props
		Directory.Build.targets = Directory.Build.targets
		LICENSE.txt = LICENSE.txt
		NOTICE.txt = NOTICE.txt
		NuGet.config = NuGet.config
		README.md = README.md
	EndProjectSection
EndProject

Project("{9A19103F-16F7-4668-BE54-9A1E7A4F7556}") = "Lucene.Net.Analysis.Common", "src\Lucene.Net.Analysis.Common\Lucene.Net.Analysis.Common.csproj", "{3D0366A8-515D-44F0-835F-4118853CFA14}"
EndProject

Global
	GlobalSection(SolutionConfigurationPlatforms) = preSolution
		Debug|Any CPU = Debug|Any CPU
		Release|Any CPU = Release|Any CPU
	EndGlobalSection
	GlobalSection(ProjectConfigurationPlatforms) = postSolution
		{3A0AA37E-2B7B-4416-B528-DA4E0E6A6706}.Debug|Any CPU.ActiveCfg = Debug|Any CPU
		...
	EndGlobalSection
	GlobalSection(SolutionProperties) = preSolution
		HideSolutionNode = FALSE
	EndGlobalSection
	GlobalSection(NestedProjects) = preSolution
		{05CE3A39-40D4-452D-AFE0-E57E536A08C6} = {4016BDAB-6C33-4D1E-9439-57B416EA45D5}
		...
	EndGlobalSection
	GlobalSection(ExtensibilityGlobals) = postSolution
		SolutionGuid = {9F2179CC-CFD2-4419-AB74-D72856931F36}
	EndGlobalSection
EndGlobal

 */
public class SolutionFile {

    public static class Project {
        public String name;
        public String csproj;
    }

    private File slnFile;
    private Map<String, Project> projects = new TreeMap<>();
    private Properties properties = new Properties();

    public SolutionFile(File slnFile) {
        this.slnFile = slnFile;
        loadContent();
    }

    private void loadContent() {
        List<String> lines = FileUtils.toStrings(slnFile);
        // states:
        //  0: main
        //  1: Project / EndProject
        //  2: ProjectSection / EndProjectSection
        int state = 0; //
        for(String line : lines) {
            line = line.trim();

            // skip comments
            if (line.startsWith("#"))
                continue;

            if (state == 0) {
                // 'Microsoft Visual Studio ...'
                if (line.startsWith("Microsoft Visual Studio")) {
                    properties.put("Version", line);
                }

                // 'VisualStudioVersion ...'
                else if (line.startsWith("VisualStudioVersion")) {
                    splitOnEquals(properties, line);
                }

                // 'MinimumVisualStudioVersion ...'
                else if (line.startsWith("MinimumVisualStudioVersion")) {
                    splitOnEquals(properties, line);
                }

                // 'Project'
                else if (line.startsWith("Project(")) {
                    parseProject(line);
                    state = 1;
                }

            }

        }
    }

    /*
        Project("{2150E333-8FDC-42A3-9474-1A3956D46DE8}") = "azure-templates", "azure-templates", "{05CE3A39-40D4-452D-AFE0-E57E536A08C6}"
        Project("{9A19103F-16F7-4668-BE54-9A1E7A4F7556}") = "Lucene.Net", "src\Lucene.Net\Lucene.Net.csproj", "{3A0AA37E-2B7B-4416-B528-DA4E0E6A6706}"
        Project("{9A19103F-16F7-4668-BE54-9A1E7A4F7556}") = "Lucene.Net.Analysis.Stempel", "src\Lucene.Net.Analysis.Stempel\Lucene.Net.Analysis.Stempel.csproj", "{F8293D73-AB75-4603-BBF6-3F3D093E934E}"
     */
    private void parseProject(String line) {
        int pos = line.indexOf('=');
        if (pos == -1)
            return;

        // Project("{9A19103F-16F7-4668-BE54-9A1E7A4F7556}") = "Lucene.Net", "src\Lucene.Net\Lucene.Net.csproj", "{3A0AA37E-2B7B-4416-B528-DA4E0E6A6706}"

        String[] parts = line.substring(pos+1).split(",");
        if (parts.length != 3)
            return;

        Project p = new Project();

        // "Lucene.Net"
        {
            p.name = parts[0].trim();
            p.name = p.name.substring(1, p.name.length()-1);
        }

        // "src\Lucene.Net\Lucene.Net.csproj"
        {
            p.csproj = parts[1].trim();
            p.csproj = p.csproj.substring(1, p.csproj.length()-1);
        }

        projects.put(p.name, p);
    }

    private static void splitOnEquals(Properties props, String line) {
        int pos = line.indexOf('=');
        if (pos == -1)
            return;

        String left = line.substring(0, pos).trim();
        String right = line.substring(pos + 1).trim();
        props.put(left, right);
    }
}
