package org.hls.check;

import com.scitools.understand.UnderstandException;
import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.scitools.und.Ent;
import jext.scitools.und.Ref;
import jext.scitools.und.UndDatabase;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectUtils;
import jext.util.PropertiesUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckUndDb {

    public static void main(String[] args) throws UnderstandException, IOException {
        Logger.configure();
        CacheManager.configure();
        File hibernateProject =
                // new File("D:\\Projects.github\\other_projects\\hibernate-orm")
                // new File("D:\\Projects.github\\java_projects\\check_java_syntax")
                // new File("D:\\SPLGroup\\spl-workspaces\\sample-projects\\ForSalwa")
                new File("D:\\SPLGroup\\spl-workspaces\\sample-projects\\cocome-maven-project")
                ;
        File hibernateUnddb = new File(hibernateProject, "scitools.und");
        UndDatabase udb = UndDatabase.database(hibernateUnddb, "java", 8);

        udb.delete();
        udb.create();

        Project project = Projects.newProject(hibernateProject, PropertiesUtils.empty());
        List<File> sources  = ProjectUtils.getSourceFiles(project);
        Set<File> libraries = ProjectUtils.getLibraryFiles(project);

        udb.addSources(sources);
        udb.addLibraries(libraries);
        udb.analyze(false);

        Set<String> kinds = new HashSet<>();

        try(UndDatabase db  = UndDatabase.database(hibernateUnddb, "java", 8).open()) {

            Ent[] ents = db.ents("");
            for (Ent ent : ents) {
                System.out.println(ent.longname());

                Ref[] refs = ent.refs("", "");
                for(Ref ref : refs) {
                    System.out.print("... ");
                    System.out.printf("%s: %s\n", ref.scope().name(), ref.ent().name());
                }

                String kind = ent.kind().name();
                String[] labels = kind.split(" ");
                for (String label : labels)
                    kinds.add(label);
            }

            System.out.println(ents.length);
            System.out.println(kinds);
        }

        udb.close();
    }
}
