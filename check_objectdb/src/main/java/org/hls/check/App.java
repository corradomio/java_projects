package org.hls.check;

import com.objectdb.Utilities;
import jext.cache.CacheManager;
import jext.logging.Logger;
import org.hls.check.data.Address;
import org.hls.check.data.Person;

import javax.jdo.JDOException;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

public class App {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        // System.out.println("Start parsing ...");
        // ParseResult<CompilationUnit> pr =  JavaParserPool.getPool().parse(new File(
        //     // "G:\\_Projects\\Projects.github\\apache_gitbox\\hbase\\hbase-thrift\\src\\main\\java\\org\\apache\\hadoop\\hbase\\thrift\\generated\\Hbase.java"
        //     "G:\\_Projects\\Projects.github\\apache.gitbox\\commons-collections\\src\\main\\java\\org\\apache\\commons\\collections4\\bloomfilter\\ArrayCountingBloomFilter.java"
        // ));
        // if (!pr.isSuccessful()) {
        //     System.out.println(pr.getProblems());
        //     System.exit(1);
        // }
        // System.out.println("done!");

        // CompilationUnit cu = pr.getResult().get();
        // System.out.println(cu);

        // Properties props = PropertiesUtils.load("config/objectsb.properties");
        // PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(props);
        // PersistenceManager pm = pmf.getPersistenceManager();

        Person person = new Person("George", "Bush");
        Address address = new Address("The White House");
        person.setAddress(address);

        PersistenceManager pm = Utilities.getPersistenceManager("local.odb");
        Transaction tr = pm.currentTransaction();
        try {
            tr.begin();

            // CompilationUnit cu = pr.getResult().get();
            // pm.makePersistent(cu);
            pm.makePersistent(person);

            tr.commit();
        }
        catch (JDOException e) {
            e.printStackTrace();
        }
        finally {
            if (tr.isActive())
                tr.rollback();
            if (!pm.isClosed())
                pm.close();
        }

    }
}
