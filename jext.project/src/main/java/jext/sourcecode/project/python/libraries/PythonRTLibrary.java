package jext.sourcecode.project.python.libraries;

import jext.name.PathName;
import jext.sourcecode.project.LibraryType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PythonRTLibrary extends PythonLibrary {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PythonRTLibrary(String name, String version, File libraryDirectory) {
        super(PathName.of(name));
        this.version = version;
        this.libraryType = LibraryType.RUNTIME;
        this.libraryFile = libraryDirectory;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public List<File> getFiles() {
        if (libraryFiles == null)
            populate();
        return libraryFiles;
    }

    private void populate() {

        libraryFiles = new ArrayList<>();
        // Windows
        // {
        //     addFile(files,
        //         new File(libraryDirectory, "Lib"),
        //         new File(libraryDirectory, "Lib/site-packages")
        //     );
        // }
        // Note: in Linux & Mac the Python installation directory
        // contains a subdirectory "pythonX.Y", NOT present in Windows
        // That is, the filesystem layou is:
        //
        // Windows:
        //      [PythonInstallationDir]
        //          Lib
        //              site-packages
        //
        // Linux & Mac
        //      [PythonInstallationDir]
        //          pythonX.Y
        //              lib
        //                  site-packages
        //
        // Note also that in Windows, 'lib' and 'Lib' represents the SAME
        // file/directory
        //
        File libraryPythonDirectory = findPythonLibraryDirectory();
        {
            addFile(libraryFiles,
                    new File(libraryPythonDirectory, "lib"),
                    new File(libraryPythonDirectory, "lib/site-packages")
            );
        }

    }

    private static void addFile(List<File> files, File... filesToAdd) {
        for (File file : filesToAdd)
            if (file.exists())
                files.add(file);
    }

    /**
     * Linux:
     *
     *  [PYTHON_HOME]
     *      bin
     *      lib
     *          python3.x
     *              site-packages
     *
     * @return
     */
    private File findPythonLibraryDirectory() {
        File[] dirs = libraryFile.listFiles(File::isDirectory);
        if (dirs == null)
            return libraryFile;

        for (File dir : dirs)
            if (dir.getName().startsWith("python"))
                return dir;

        return libraryFile;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
