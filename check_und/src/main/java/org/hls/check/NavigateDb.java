package org.hls.check;

import com.scitools.understand.UnderstandException;
import jext.cache.CacheManager;
import jext.util.logging.Logger;
import jext.scitools.und.Ent;
import jext.scitools.und.Ref;
import jext.scitools.UndDatabase;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NavigateDb {

    public static void main(String[] args) throws UnderstandException, IOException {
        Logger.configure();
        CacheManager.configure();
        File hibernateProject =
                // new File("D:\\Projects.github\\other_projects\\hibernate-orm")
                new File("D:\\Projects.github\\java_projects\\check_java_syntax")
                ;
        File hibernateUnddb = new File(hibernateProject, "scitools.und");

        Set<String> tents = new HashSet<>();
        Set<String> kinds = new HashSet<>();

        try(UndDatabase db  = UndDatabase.database(hibernateUnddb).open()) {

            Ent[] ents = db.ents("File");
            for (Ent ent : ents) {
                tents.add(ent.name());

                System.out.printf("File: %s\n", ent.longname());

                Ref[] refs = ent.refs("", "Java Package,Public Class,Public Interface");
                for(Ref ref : refs) {
                    System.out.printf("... %s: %s\n", ref.ent().kind().name(), ref.ent().name());
                }

                String kind = ent.kind().name();
                String[] labels = kind.split(" ");
                for (String label : labels)
                    kinds.add(label);
            }

            System.out.println(ents.length);
            System.out.println(kinds);
        }
    }
}
