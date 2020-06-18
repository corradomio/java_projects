package jext.buildtools.scan.rules;

import jext.buildtools.scan.util.PatternSet;
import jext.logging.Logger;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ModuleScan {

    private static Logger logger = Logger.getLogger(ModuleScan.class);

    private PatternSet isExcluded = new PatternSet();
    private IsModule isModule = new IsModule();

    public void configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        isExcluded.configure(selected, "exclude");
        isModule.configure(selected, "module");
    }

    public boolean isExcluded(File file) {
        return isExcluded.accept(file.getName());
    }

    public boolean isModule(File directory) {
        return isModule.accept(directory);
    }

    public List<File> getModuleDirs(File baseDir) {
        List<File> moduleDirs = new ArrayList<>();
        // Scan the current directory
        try {
            Files.walkFileTree(baseDir.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    File directory = dir.toFile();
                    if (isExcluded(directory))
                        return FileVisitResult.SKIP_SUBTREE;

                    if (baseDir.equals(directory))
                        return FileVisitResult.CONTINUE;

                    if (isModule(directory))
                        moduleDirs.add(directory);

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            logger.error(e, e);
        }

        return moduleDirs;
    }

}
