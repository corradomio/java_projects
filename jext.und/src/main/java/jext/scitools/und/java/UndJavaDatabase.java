package jext.scitools.und.java;

import jext.scitools.und.UndDatabase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.zeroturnaround.exec.ProcessExecutor;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

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

    // private Document openSettings() throws IOException {
    //     File settingsFile = new File(undDatabase, "settings.xml");
    //     Document doc;
    //     try {
    //         doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(settingsFile);
    //         return doc;
    //     } catch (SAXException | ParserConfigurationException e) {
    //         throw new IOException(e);
    //     }
    // }

    // private void saveSettings(Document doc) throws IOException {
    //     File settingsFile = new File(undDatabase, "settings.xml");
    //
    //     try (FileWriter out = new FileWriter(settingsFile)) {
    //         Transformer transformer = TransformerFactory.newInstance().newTransformer();
    //         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    //         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
    //         transformer.transform(new DOMSource(doc), new StreamResult(out));
    //     } catch (TransformerException e) {
    //         throw new IOException(e);
    //     }
    // }

    // private Element selectClassPaths(Document doc) {
    //     Element javaOptions = selectJavaOptions(doc);
    //     Element classPaths = getElementByTagName(javaOptions, "class_paths");
    //     if (classPaths == null) {
    //         classPaths = doc.createElement("class_paths");
    //         javaOptions.appendChild(classPaths);
    //     }
    //     return classPaths;
    // }

    // private Element selectJavaOptions(Document doc) {
    //     Element root = doc.getDocumentElement();
    //     Element javaOptions = getElementByTagName(root, "java_options");
    //     if (javaOptions == null) {
    //         javaOptions = doc.createElement("java_options");
    //         root.appendChild(javaOptions);
    //         javaOptions.setAttribute("count_javadoc_in_metrics", "true");
    //         javaOptions.setAttribute("count_standard_in_max_inheritance", "false");
    //         javaOptions.setAttribute("jni_kni_include_package_name", "true");
    //         javaOptions.setAttribute("jni_kni_linkage_prepend_text", "Java_");
    //         javaOptions.setAttribute("save_comments_associated_with_entities", "true");
    //         javaOptions.setAttribute("version", "Java10");
    //     }
    //     return javaOptions;
    // }

    // private static Element getElementByTagName(Element elt, String tagName) {
    //     NodeList nl = elt.getElementsByTagName(tagName);
    //     if (nl.getLength() == 0)
    //         return null;
    //     else
    //         return (Element) nl.item(0);
    // }
}
