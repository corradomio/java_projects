package jext.scitools.und.java;

import jext.scitools.und.UndDatabase;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class UndJavaDatabase extends UndDatabase {

    public UndJavaDatabase(File undDatabase, int version) {
        super(undDatabase, JAVA, version);
    }

    public void create() throws IOException {
        super.create();
        // setJavaVersion();
    }

    /*
        Java5/Java6/Java7/Java8/Java13/Java14

        und settings -JavaVersion 8  hibernate-orm.und
        und settings -JavaVersion 10-15  hibernate-orm.und
     */
    // private void setJavaVersion() throws IOException {
    //     Document doc = openSettings();
    //     Element javaOptions = selectJavaOptions(doc);
    //     if (languageVersion <= 8) {
    //         javaOptions.setAttribute("version", "Java8");
    //     }
    //     else if (languageVersion <= 13) {
    //         javaOptions.setAttribute("version", "Java13");
    //     }
    //     else {
    //         javaOptions.setAttribute("version", "Java14");
    //     }
    //     saveSettings(doc);
    // }

    /*
        In 'theory', a list of 'external library' can be added using:

            und settings -JavaClassPaths lib1 lib2 ... [project.und]

        Because this list can be very long, and to exceed the limits of the
        number of parameters passed in the command line, the alternative "trick"
        is to modify directly the file

            [project.und]/settings.xml

        adding the tags:

            <project>
                ...
                <java_options
                    count_javadoc_in_metrics="true"
                    count_standard_in_max_inheritance="false"
                    jni_kni_include_package_name="true"
                    jni_kni_linkage_prepend_text="Java_"
                    save_comments_associated_with_entities="true"
                    version="Java10">
                    <class_paths>
                        <class_path path="...path1..."/>
                        <class_path path="...path1..."/>
                    </class_paths>
                </java_options>
            </project>
     */
    public void addLibraries(Collection<File> libraryFiles) throws IOException {
        List<File> lfiles = new ArrayList<>(libraryFiles);
        int n = lfiles.size();
        int ssize = 20;

        for (int i=0; i<n; i += ssize) {
            int l = Math.min(i + ssize, n);
            List<File> subList = lfiles.subList(i, l);

            try {
                List<String> command = new ArrayList<>();
                command.add(UND_APP);
                command.add("settings");
                command.add("-JavaClassPathsAdd");

                subList.forEach(libraryFile -> {
                    command.add(libraryFile.getAbsolutePath());
                });

                command.add(undDatabase.getAbsolutePath());

                new ProcessExecutor()
                    .command(command)
                    .redirectOutput(new OutputStream() {
                        @Override
                        public void write(int b) {
                            System.out.write(b);
                        }
                    }).execute();
            } catch (InterruptedException | TimeoutException e) {
                throw new IOException(e);
            }
        }
    }

}
