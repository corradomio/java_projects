package org.hls.example;

import jext.xml.XPathUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CheckXML {

    public static void main(String[] args) {
        Path startPath = Paths.get("D:\\Projects.ebtic\\project.ftth.data\\EbticDATA");

        try (Stream<Path> stream = Files.walk(startPath)) {
            stream
                .filter(Files::isRegularFile) // Optional: exclude directories
                .filter(f ->  f.toString().endsWith(".xml"))
                .filter(f -> !f.toString().endsWith(".shp.xml"))
                .forEach(CheckXML::checkFile);
        } catch (IOException e) {

        }
    }

    private static final Set<String> tags = new HashSet<>();

    private static void scanDocument(String parent, Element element) {
        String name = parent + element.getNodeName();
        if (!tags.contains(name)) {
            System.out.println("   " + name);
            tags.add(name);
        }

        parent = name + "/";

        NodeList children = element.getChildNodes();
        for(int i=0; i< children.getLength(); ++i) {
            Node n = children.item(i);
            if (n instanceof Element)
                scanDocument(parent, (Element) n);
        }
    }

    private static void checkFile(Path path) {
        String body = null;
        Exception e1 = null;
        Exception e2 = null;
        Document doc = null;

        if (path.toFile().length() == 0)
            return;

        if(doc == null) {
            try {
                doc = XPathUtils.parse(path.toFile());
            } catch (Exception e) {
                e1 = e;
            }
        }
        if (doc == null || doc.getDocumentElement() == null) {
            try {
                body = SecureFileHandler.readEncryptedFile(path.toAbsolutePath().toString());
                doc = XPathUtils.parse(body);
            } catch (Exception e) {
                e2 = e;
            }
        }
        if (doc == null) {
            System.err.println(path);
            if (e1 != null) {
                System.err.println("    Error/1: " + e1.getMessage());
                e1.printStackTrace();
            }
            if (e2 != null) {
                System.err.println("    Error/2: " + e2.getMessage());
                e2.printStackTrace();
            }
            return;
        }

        Element root = doc.getDocumentElement();
        if (root == null) {
            System.err.println(path);
            System.err.println("    Error/3: XML root element = null");
            return;
        }

        scanDocument("", root);

        // String rootName = root.getTagName();
        // if (!tags.contains(rootName)) {
        //     System.out.println(path);
        //     System.out.printf("    %s\n", rootName);
        //     tags.add(rootName);
        // }

        // NodeList children = root.getChildNodes();
        // for(int i=0; i< children.getLength(); ++i) {
        //     Node n = children.item(i);
        //     if (!(n instanceof Element)) continue;
        //
        //     String elementName = ((Element)n).getTagName();
        //     if (!tags.contains(elementName)) {
        //         System.out.println(path);
        //         System.out.printf("    ... %s/%s\n", rootName, elementName);
        //         tags.add(elementName);
        //     }
        // }

        // try (Stream<String> body = Files.lines(path, StandardCharsets.UTF_8)) {
        //     Object[] lines = body.toArray();
        //     String l0 = lines[0].toString();
        //     String l1 = lines[1].toString();
        //     String ll;
        //     if (l0.startsWith("<?") && l1.length() < 100) {
        //         ll = l1;
        //     }
        //     else if (l0.startsWith("<?")) {
        //         ll = l1.substring(0, 100);
        //     }
        //     else if (l0.length() > 100) {
        //         ll = l0.substring(0, 100);
        //     }
        //     else {
        //         ll = l0;
        //     }
        //
        //     if (HEADERS.contains(ll))
        //         return;
        //
        //     System.out.println(ll);
        //     HEADERS.add(ll);
        //
        //     return;
        // } catch (Throwable e) {
        //     // System.err.println("ERROR/1: " + path.toAbsolutePath().toString());
        //     // e.printStackTrace();
        // }
        //
        // try {
        //     String body = SecureFileHandler.readEncryptedFile(path.toAbsolutePath().toString());
        //     String[] lines = body.split("\n");
        //
        //     String l0 = lines[0].trim();
        //     String l1 = lines[1].trim();
        //     String ll;
        //     if (l0.startsWith("<?") && l1.length() < 15) {
        //         ll = l1;
        //     }
        //     else if (l0.startsWith("<?")) {
        //         ll = l1.substring(0, 15);
        //     }
        //     else if (l0.length() > 15) {
        //         ll = l0.substring(0, 15);
        //     }
        //     else {
        //         ll = l0;
        //     }
        //
        //     if (HEADERS.contains(ll))
        //         return;
        //
        //     System.out.println(ll);
        //     HEADERS.add(ll);
        // } catch (Exception e) {
        //     // throw new RuntimeException(e);
        //     System.err.println("ERROR/2: " + path.toAbsolutePath().toString());
        //     e.printStackTrace();
        // }

    }
}
