package jext.sourcecode.project.info;

import jext.name.Name;
import jext.name.Named;
import jext.name.PathName;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.util.SourceInfo;
import jext.util.MapUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InfoSource implements Source {

    private final InfoModule module;
    private final Map<String, Object> info;
    private final Name name;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public InfoSource(InfoModule module, Map<String, Object> info) {
        if (info == null)
            throw new NullPointerException();
        this.module = module;
        this.info = info;
        this.name = PathName.of(MapUtils.get(info, "fullname"));
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return MapUtils.get(info, "id");
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public String getPath() {
        return MapUtils.get(info, "path");
    }

    @Override
    public File getFile() {
        return new File(module.getProject().getProjectHome(), getPath());
    }

    @Override
    public Module getModule() {
        return module;
    }

    @Override
    public Project getProject() {
        return module.getProject();
    }

    @Override
    public String getDigest() {
        return MapUtils.getString(info, "digest");
    }

    @Override
    public String getMimeType() {
        return MapUtils.get(info, "mimeType");
    }

    @Override
    public SourceInfo getSourceInfo() {
        SourceInfo sinfo = new SourceInfo();

        sinfo.version = MapUtils.get(info, "sourceInfo", "version");
        sinfo.count = MapUtils.getLong(info, "sourceInfo", "count");
        sinfo.bytes = MapUtils.getLong(info, "sourceInfo", "bytes");
        sinfo.totalLines = MapUtils.getLong(info, "sourceInfo", "totalLines");
        sinfo.blankLines = MapUtils.getLong(info, "sourceInfo", "blankLines");
        sinfo.codeLines = MapUtils.getLong(info, "sourceInfo", "codeLines");

        return sinfo;
    }

    @Override
    public Optional<String> getSourceRoot() {
        // return Optional.ofNullable(MapUtils.get(info, "sourceRoot"));
        return Optional.empty();
    }

    @Override
    public String getLanguage() {
        return MapUtils.get(info, "language");
    }

    @Override
    public Set<RefType> getTypes() {
        List<String> types = MapUtils.get(info, "types");
        return types.stream()
            .map(typeName -> new InfoType(this, typeName))
            .collect(Collectors.toSet());
    }

    @Override
    public Set<RefType> getUsedTypes() {
        return Collections.emptySet();
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Source that = (Source) obj;
        return getName().equals(that.getName());
    }

    @Override
    public int compareTo(Named o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return String.format("InfoSource[%s]", getName().getName());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
