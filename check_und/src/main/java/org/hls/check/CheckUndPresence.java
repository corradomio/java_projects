package org.hls.check;

import jext.cache.CacheManager;
import jext.util.logging.Logger;
import jext.scitools.SciTools;
import jext.scitools.UndDatabase;

import java.io.File;

public class CheckUndPresence {

    public static void main(String[] args) {
        try {
            Logger.configure();
            CacheManager.configure();

            System.out.println(SciTools.isAvailable());

            File upath = new File("D:\\SPLGroup\\spl-workspaces\\sample-projects\\cocome-maven-project\\.spl\\scitools.und");

            UndDatabase udb1 = UndDatabase.database(upath);
            UndDatabase udb2 = UndDatabase.database(upath);
            UndDatabase udb3 = UndDatabase.database(upath);

            udb1.open();
            udb2.open();
            udb3.open();

            udb3.close();
            udb2.close();
            udb1.close();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
