package jext.sourcecode.project.util;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Source;

import java.io.File;
import java.util.AbstractList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class SourceRoots extends AbstractList<SourceRoot> {

    private Module module;
    private Map<Integer, SourceRoot> rootsPos = new HashMap<>();
    private Map<File, SourceRoot> rootsMap = new HashMap<>();

    public SourceRoots(Module module) {
        this.module = module;
    }

    @Override
    public SourceRoot get(int index) {
        throw new UnsupportedOperationException();
    }

    public boolean add(Source source) {
        Optional<File> optSourceRoot = source.getSourceRoot();
        if (!optSourceRoot.isPresent())
            return false;

        File sourceRoot = optSourceRoot.get();
        if (!rootsMap.containsKey(sourceRoot)) {
            SourceRoot sr = new SourceRoot(module, sourceRoot);
            rootsMap.put(sourceRoot, sr);
            rootsPos.put(rootsPos.size(), sr);
        }

        rootsMap.get(sourceRoot).add(source);

        return true;
    }

    @Override
    public int size() {
        int size = 0;
        for (SourceRoot sourceRoot : rootsMap.values())
            size += sourceRoot.size();
        return size;
    }

    @Override
    public Iterator<SourceRoot> iterator() {
        return rootsMap.values().iterator();
    }
}
