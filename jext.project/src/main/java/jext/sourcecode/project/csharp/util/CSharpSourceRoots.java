package jext.sourcecode.project.csharp.util;

import jext.sourcecode.project.csharp.CSharpConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CSharpSourceRoots implements Iterable<File> {

    private static class SourcesCount {
        int pyCount;
        int extCount;

        void count(File file) {
            String name = file.getName();
            if (name.endsWith(CSharpConstants.CSHARP_EXT))
                pyCount++;
            else
                extCount++;
        }

        boolean hasSources() {
            return pyCount > 0 && extCount > 0 && pyCount > extCount
                || pyCount > 0 && extCount == 0;
        }
    }

    private static class HierarchicalSet extends AbstractSet<File> {
        private List<File> members = new ArrayList<>();

        @Override
        public Iterator<File> iterator() {
            return members.iterator();
        }

        @Override
        public int size() {
            return members.size();
        }

        public boolean add(File e) {
            String path = e.getAbsolutePath();

            // 1) check if a prefix of e is not already present
            for(File p : members)
                if (path.startsWith(p.getAbsolutePath()))
                    return false;

            // 2) remove all suffixes than e
            for(Iterator<File> it = members.iterator(); it.hasNext(); ) {
                String m = it.next().getAbsolutePath();
                if (m.startsWith(path))
                    it.remove();
            }

            members.add(e);
            return true;
        }
    }

    private Set<File> sourceRoots = new HierarchicalSet();
    private Map<File, SourcesCount> srcCounts = new HashMap<>();

    public void scan(File directory) {
        if (!directory.exists())
            return;

        try {
            Files.walkFileTree(directory.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path pdir, BasicFileAttributes attrs) throws IOException {
                    File fdir = pdir.toFile();
                    srcCounts.put(fdir, new SourcesCount());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    File file = path.toFile();
                    File parent = file.getParentFile();

                    srcCounts.get(parent).count(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path pdir, IOException exc) throws IOException {
                    File fdir = pdir.toFile();

                    if (srcCounts.get(fdir).hasSources()) {
                        add(fdir);
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {

        }

    }

    @Override
    public Iterator<File> iterator() {
        return sourceRoots.iterator();
    }

    private void add(File directory) {
        if (hasParentRoot(directory))
            return;

        sourceRoots.add(directory);
    }

    private boolean hasParentRoot(File sourceRoot) {
        String path = sourceRoot.getAbsolutePath();
        for (File sr : sourceRoots) {
            String parent = sr.getAbsolutePath();
            if (path.startsWith(parent))
                return true;
        }
        return false;
    }

    public void dump() {
        sourceRoots.forEach(System.out::println);
    }
}
