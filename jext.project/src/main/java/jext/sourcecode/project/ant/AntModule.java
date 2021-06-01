package jext.sourcecode.project.ant;

import jext.sourcecode.project.Project;
import jext.sourcecode.project.ant.util.IvyFile;
import jext.sourcecode.project.util.BaseModule;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class AntModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private List<IvyFile> ivyFiles;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    AntModule(File moduleHome, Project project) {
        super(moduleHome, project);
        findIvyFile();
    }

    private void findIvyFile() {
        ivyFiles = FileUtils.asList(getModuleHome().listFiles((dir, name) -> name.startsWith("ivy")))
            .stream()
            .map(IvyFile::new)
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // @Override
    // protected void collectIvyLibraries(Set<Library> collectedLibraries) {
    //     if (ivyFiles.isEmpty())
    //         return;
    //
    //     List<MavenCoords> coordList = new ArrayList<>();
    //     ivyFiles.forEach(ivyFile -> {
    //         coordList.addAll(ivyFile.getDependencyCoords());
    //     });
    //
    //     MavenDownloader md = project.getLibraryDownloader();
    //     // md.checkArtifacts(coordList);
    //     // coordList.sort(Comparator.naturalOrder());
    //
    //     coordList.stream()
    //         .map(lcoords -> new MavenLibrary(lcoords, md, project))
    //         .forEach(collectedLibraries::add);
    // }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}