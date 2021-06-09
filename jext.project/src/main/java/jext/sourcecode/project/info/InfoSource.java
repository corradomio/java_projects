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
import java.util.stream.Collectors;

public class InfoSource implements Source {

    private InfoModule module;
    private Map<String, Object> info;

    InfoSource(InfoModule module, Map<String, Object> info) {
        this.module = module;
        this.info = info;
    }

    @Override
    public String getId() {
        return MapUtils.get(info, "id");
    }

    @Override
    public Name getName() {
        return new PathName(MapUtils.get(info, "fullname"));
    }

    @Override
    public String getPath() {
        return MapUtils.get(info, "path");
    }

    @Override
    public File getFile() {
        return new File(module.getModuleHome(), getPath());
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
    public String getModuleId() {
        return module.getId();
    }

    @Override
    public String getDigest() {
        return MapUtils.get(info, "digest");
    }

    @Override
    public String getMimeType() {
        return MapUtils.get(info, "mimeType");
    }

    @Override
    public SourceInfo getSourceInfo() {
        SourceInfo sinfo = new SourceInfo();
        return sinfo;
    }

    @Override
    public Optional<String> getSourceRoot() {
        return Optional.ofNullable(MapUtils.get(info, "sourceRoot"));
    }

    @Override
    public String getLanguage() {
        return MapUtils.get(info, "language");
    }

    @Override
    public List<? extends Type> getTypes() {
        List<String> types = MapUtils.get(info, "types");
        return types.stream()
            .map(typeName -> new InfoType(this, typeName))
            .collect(Collectors.toList());
    }

    @Override
    public List<? extends RefType> getUsedTypes() {
        return Collections.emptyList();
    }

    @Override
    public int compareTo(Named o) {
        return getName().compareTo(o.getName());
    }
}
