package jext.sourcecode.project.python.libraries;

import jext.name.PathName;
import jext.sourcecode.project.LibraryType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PythonRTLibrary extends PythonLibrary {

    public PythonRTLibrary(String name, File libraryDirectory) {
        super(libraryDirectory);
        setNameWithId(PathName.of(name));
        libraryType = LibraryType.RUNTIME;
    }

    @Override
    public List<File> getFiles() {
        List<File> files = new ArrayList<>();
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
            addFile(files,
                new File(libraryPythonDirectory, "lib"),
                new File(libraryPythonDirectory, "lib/site-packages")
            );
        }

        return files;
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
        File[] dirs = libraryDirectory.listFiles(File::isDirectory);
        if (dirs == null)
            return libraryDirectory;

        for (File dir : dirs)
            if (dir.getName().startsWith("python"))
                return dir;

        return libraryDirectory;
    }
}
