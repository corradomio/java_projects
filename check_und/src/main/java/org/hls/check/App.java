package org.hls.check;

import com.scitools.understand.Database;
import com.scitools.understand.Entity;
import com.scitools.understand.Understand;
import com.scitools.understand.UnderstandException;

public class App {

    public static void main(String[] args) throws UnderstandException {

        Database db = Understand.open("D:\\Projects.github\\other_projects\\hibernate-orm\\hibernate-orm.und");

        Entity[] ents = db.ents("Method");
        for(Entity ent : ents)
            System.out.println(ent.longname());

        db.close();
    }
}
