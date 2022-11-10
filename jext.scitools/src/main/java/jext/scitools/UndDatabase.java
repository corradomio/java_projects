package jext.scitools;

import com.scitools.understand.Understand;
import com.scitools.understand.UnderstandException;
import jext.exception.InvalidValueException;
import jext.scitools.und.Ent;
import jext.util.Assert;
import jext.xml.XPathUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.zeroturnaround.exec.StartedProcess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
     Commands
       help                   gives more help information for a command
       settings               sets project settings, overrides

       add                    adds files, directories and roots
       remove                 removes files, directories, roots, and architectures

       analyze                analyzes the project files
       codecheck              runs CodeCheck
       convert                convert a .udb project to a .und project
       create                 creates an empty database
       purge                  purges the database

       export                 exports settings, dependencies, macros, includes or arches
       import                 imports project settings and arches

       license                gives various license information
       list                   lists information about the project

       metrics                generates project metrics

       process                runs all the commands in a text file
       projectinfo            gives various project information
       report                 generates project reports
       uperl                  runs perl scripts
       version                gives the current version
 */

/**
 * Class used to create/update/delete the SciTools "und" database
 */
public class UndDatabase implements AutoCloseable {

    private static final String NO_LANGUAGE = "";
    private static Logger logger = LogManager.getLogger(UndDatabase.class);

    /**
     * Factory method used to to access the SciTools "und' database
     *
     * @param undPath database full path
     * @return
     */
    public static UndDatabase database(File undPath) {
        return new UndDatabase(undPath);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String SETTINGS = "settings";

    protected File undPath;
    protected String language;
    protected File tempPath;
    protected String refId;
    protected com.scitools.understand.Database database;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected UndDatabase(File undPath) {
        this.undPath = undPath;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public void setTempInfo(File tempPath, String refId) {
        this.tempPath = tempPath;
        this.refId = refId;
    }

    public File getPath() {
        return undPath;
    }

    // ----------------------------------------------------------------------
    // Database create/delete/exists
    // ----------------------------------------------------------------------

    /**
     * Check if the database exists
     * @return true if the db exists, false otherwise
     */
    public boolean exists() {
        return undPath.exists();
    }

    /**
     * Delete the database
     *
     */
    public void delete() {
        if (!exists())
            return;

        File[] content = undPath.listFiles();
        if (content != null)
            for (File f : content)
                delete(f);

        delete(undPath);
    }

    private static void delete(File f) {
        if (f != null && !f.delete())
           logger.warn(String.format("Unable to delete %s", f.getAbsolutePath()));
    }

    /*
    Help For Create
      The create command creates a new database. It can also take in settings
      exactly like the settings command (und help settings).It can take the
      name of the new database as the parameter for the -db switch or as the
      last parameter, so it could look like this

        und create -db newDatabase.und -languages c++ c# plm ...
     */
    public void create(String language) throws IOException {
        if (exists())
            throw new IOException("Database '" + undPath.getAbsolutePath() + "' already existent");

        if ("csharp".equals(language))
            language = "c#";
        else if ("cplusplus".equals(language))
            language = "c++'";

        this.language = language;

        SciTools.und().exec("create",
            "-languages", language,
            "-db", undPath.getAbsolutePath()
        );

        if ("java".equals(language)) {
            SciTools.und().exec(SETTINGS,
                "-JavaVersion", "10-18",
                "-db", undPath.getAbsolutePath()
            );
        }
        else if ("python".equals(language)) {
            SciTools.und().exec(SETTINGS,
                "-PythonSetVersion", "Python3",
                "-db", undPath.getAbsolutePath()
            );
            SciTools.und().exec(SETTINGS,
                    "-PythonExe", SciTools.upythonApp ,
                    "-db", undPath.getAbsolutePath()
            );
            SciTools.und().exec(SETTINGS,
                    "-UseInstalledStandard", "ON",
                    "-db", undPath.getAbsolutePath()
            );
        }
        else if ("csharp".equals(language) || "c#".equals(language)) {
            Assert.nop();
        }

        else {
            throw new InvalidValueException("language", language);
        }
    }

    // ----------------------------------------------------------------------
    // Add/remove source files
    // ----------------------------------------------------------------------

    private static final String ADD = "add";
    private static final String REMOVE = "remove";

    /*
    Help for Add
    The add command can be used to add files, directories, or visual
    studio files to a project. The add command can also be used to add
    compiler log files or CMake databases (e.g. compile_commands.json files). Additionally,
    add can be used to specify named roots. The type being added does not need to be
    specified, but the -file, -vs, and -root switches are available to force the arguments
    to be treated as a file, visual studio file, or named root respectively.
    Directory is the default when the argument's type cannot be determined.

      1) Adding Directories
         This is the most common use for the command. It can be run like this

           und add thisDirectory myProject.und

         This adds "thisDirectory" to myProject.und. The directory can be
         absolute or relative to the current directory. The following switches
         are available for adding directories:

         -watch     This determines whether a directory is watched or not. By
                    default, -watch is on. It can be given <on,off>.
         -subdir    This determines whether or not to add all the subdirectories.
                    This is on by default, and can be given <on,off> as parameters.
         -exclude   This takes in a list of wildcards separated by a comma. Any
                    files or directories that match are ignored.
                    By default, it is .*
         -filter    This takes in a list of wildcards. Any files or directories
                    that match are included. By default, there are none.
         -lang      This specifies specific languages to add files for. By default,
                    all project languages are included.

         The switches should appear before the arguments, so a command with
         switches might look like this

           und add -watch off -exclude "*.java","*.cpp" thisDirectory

      2) Adding Import Files
         Understand can add visual studio projects and solutions, xcode projects, and
         cmake compile_commands.json files to the database. It supports .csproj .dsp .dsw
         .vbproj .vcp .vcproj .vfproj .vcxproj .vcw .xcodeproj and compile_commands.json
         files. Visual studio files and xcode projects require a configuration given with
         the -config switch. The following switches are available:

         -cmake     optional, tell und the file is a cmake compile_commands.json
         -config    the configuration to use with the project
         -exclude   list of wild card excludes separated by semicolon.
         -onetime   disable project sync
         -vs        optional, tell und the file is a visual studios file
         -xcode     optional, tell und the file is an xcode project

         A command mightlook like this

           add -config "Debug|Win32" file.vcproj  (Interactive mode)
           und add -exclude "test*" -cmake compile_commands.json myProject.und

      3) Adding Files
         There are no switches available for adding files. This command simply adds
         a file to the project. If a file is currently excluded, adding it will
         remove the exclude flag. It takes in a list of files, or a text file containing a
         single file per line with comments starting with #. If a text file is given
         as the parameter, it should begin with @. So, the command might look like
         this

           und add file1.cpp file2.cpp myProject.und
           und -db myProject.und add -file file1.cpp file2.cpp
           add @myFiles.txt    (Interactive Mode)

      4) Adding Named Roots
         For information on named roots, use (und help roots). Multiple roots can be
         added on a line with root=value root2=value2 and so on. The command can
         also take in a text file listing one root=value per line. The text file
         should begin with @. The command for adding named roots could look like
         this

           und add -root root=value root2=value2
           add @myRoots.txt   (Interactive Mode)

      5) Adding Log Files
         A log file is recognized by the extension "log" and has two available
         switches.
         -cc        specifies the c compiler
         -cxx       specifies the c++ compiler

      6) Merging another database
         The add command can be used to add all the files and their override
         settings of one database to another database. If the files are already
         in the "to" database, their override settings will be changed to
         match that of the "from" database. The command looks like this

           und add -db todb.und fromdb.und fromdb2.und
           und add fromdb.und todb.und

         Note that both fromdb and todb must be specified, even in interactive mode.
    */

    /*
    Help For Remove
    The remove command removes files, directories, visual studio files,
    roots, and architectures from the project. It will detect whether the argument
    is a file, directory, visual studio file, root or architecture. It will default to
    directory in case of a conflict and can be forced to treat the argument
    as a file, visual studio file, root, or architecture using the switches -file, -vs,
    -root, and -arch respectively. The command could look like this

        und remove someFile.cpp myProject.und
        und remove C:\SomeDirectory myProject.und
        und -db myProject.und remove vsFile1.vcproj vsFile2.vcproj
        remove thisArch  (Interactive Mode)
        remove NAMED_ROOT:  (Interactive Mode)

    The command can take in a text file listing all the files, directories,
    visual studio files, roots, OR architectures to delete, with one per line, # lines
    ignored. The file is shown as a list file by @, so the command could look like
    this

    remove -file @listfile.txt (Interactive Mode)

     */
    public void removeSource(File fileOrDirectory) throws IOException {
        applyOnSource(REMOVE, fileOrDirectory);
    }

    public void addSources(List<File> files) throws IOException {
        applyOnSources(ADD, files);
    }

    public void removeSources(List<File> files) throws IOException {
        applyOnSources(REMOVE, files);
    }

    // ----------------------------------------------------------------------

    private void applyOnSource(String action, File fileOrDirectory) throws IOException {
        if (fileOrDirectory.isDirectory()) {
            SciTools.und().exec(
                action,
                    fileOrDirectory.getAbsolutePath(),
                    "-db",
                    undPath.getAbsolutePath());
        }
        else {
            SciTools.und().exec(
                action, /* "-file", */
                    fileOrDirectory.getAbsolutePath(),
                    "-db",
                    undPath.getAbsolutePath());
        }
    }

    private void applyOnSources(String action, List<File> files) throws IOException {
        File sourcesFile = createSourcesListFile(files);
        SciTools.und().exec(
            action,
                "@" + sourcesFile.getAbsolutePath(),
                "-db",
                undPath.getAbsolutePath());
    }

    private File createSourcesListFile(Collection<File> files) throws IOException {
        File tempFile = createTempFile("scitools-sources");
        return writeListFile(tempFile, files);
    }

    private File createLibrariesListFile(Collection<File> files) throws IOException {
        File tempFile = createTempFile("scitools-libraries");
        return writeListFile(tempFile, files);
    }

    private File createTempFile(String prefix) throws IOException {
        File tempFile;
        if (tempPath == null)
            tempFile = Files.createTempFile(prefix, ".txt").toFile();
        else {
            String tempName = String.format("%s-%s.txt", refId, prefix);
            tempFile = new File(tempPath, tempName);
        }
        return tempFile;
    }

    private File writeListFile(File tempFile, Collection<File> files) throws IOException {
        Set<File> ordered = new TreeSet<>(files);
        try(Writer writer = new FileWriter(tempFile)) {
            ordered.forEach(file -> {
                try {
                    writer.write(file.getAbsolutePath());
                    writer.write("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return tempFile;
    }

    // ----------------------------------------------------------------------
    // Add external libraries
    // ----------------------------------------------------------------------

    public void addLibraries(Collection<File> libraryFiles) throws IOException {
        if (language == null)
            retrieveLanguage();

        if (language.isEmpty())
            addJavaLibraries(libraryFiles);
        else if ("java".equals(language))
            addJavaLibraries(libraryFiles);
        else if ("python".equals(language))
            addPythonLibraries(libraryFiles);
        else if ("csharp".equals(language) || "c#".equals(language))
            addCSharpLibraries(libraryFiles);
        else
            throw new IOException("Unsupported language " + language);
    }

    private void addJavaLibraries(Collection<File> libraryFiles) throws IOException {
        // save the list of files in an external file
        File librariesFile = createLibrariesListFile(libraryFiles);

        List<String> command = Arrays.asList(
            SETTINGS,
            "-JavaClassPathsAdd",
            "@" + librariesFile.getAbsolutePath(),
            "-db",
            undPath.getAbsolutePath());

        SciTools.und().exec(command);
    }

    private void addPythonLibraries(Collection<File> libraryFiles) throws IOException {
        // save the list of files in an external file
        File librariesFile = createLibrariesListFile(libraryFiles);

        // configure SciTools DB with the list of libraries
        // Note: the list is splitted in chunks of MAX_FILES_INLINE (20) files
        // because it is not possible to pass a lot of files on the command line

        List<String> command = Arrays.asList(
            SETTINGS,
            "-PythonImportPathsAdd",
            "@" + librariesFile.getAbsolutePath(),
            "-db",
            undPath.getAbsolutePath());

        SciTools.und().exec(command);
    }

    private void addCSharpLibraries(Collection<File> libraryFiles) throws IOException {
        // save the list of files in an external file
        File librariesFile = createLibrariesListFile(libraryFiles);

        // configure SciTools DB with the list of libraries
        // Note: the list is splitted in chunks of MAX_FILES_INLINE (20) files
        // because it is not possible to pass a lot of files on the command line

        List<String> command = Arrays.asList(
            SETTINGS,
            "-C#ReferenceFileAliasListAdd",
            "@" + librariesFile.getAbsolutePath(),
            "-db",
            undPath.getAbsolutePath());

        SciTools.und().exec(command);
    }

    private void retrieveLanguage() {
        File settings = new File(undPath, "settings.xml");
        try {
            Element root = XPathUtils.parse(settings).getDocumentElement();
            this.language = XPathUtils.getValue(root, "/project/languages/language/@name").toLowerCase();
        } catch (Exception e) {
            this.language = NO_LANGUAGE;
        }
    }

    // ----------------------------------------------------------------------
    // Analyze database content
    // ----------------------------------------------------------------------

    /*
    Help For Analyze
     The analyze command analyzes project files. By default, it analyzes
     all files. So, the command:

       und analyze myProject.und

     would analyze all files in myProject.und. There are several
     switches available. They are:

       -all     Analyze all the files in the project, default.
       -changed Analyze only files that have changed since the last analyze.
       -files   Analyze only these files. Takes a list of files as an argument or
                a text file containing a list of all the files to add. The text
                file should have one file name per line, and start with a @.
                For example:

                  und analyze -files @someFile.txt

                would analyze all the files contained in someFile.txt. Lines in
                the file starting with # will be ignored.
     Some examples of the command are:

       und analyze myProject.und
       und -db myProject.und analyze -changed
       analyze -files file1.cpp file2.cpp file3.cpp    (Interactive mode)
     */
    public StartedProcess analyze(boolean update) throws IOException {
        if (!exists())
            throw new IOException("Database '" + undPath.getAbsolutePath() + "' not existent");

        return SciTools.und().start(
            "-verbose",
                "analyze", update ? "-changed" : "-all",
                "-db",
                undPath.getAbsolutePath());
    }

    // ----------------------------------------------------------------------
    // Database read
    // ----------------------------------------------------------------------

    public UndDatabase open() throws UnderstandException {
        String unddb = undPath.getAbsolutePath();
        database = Understand.open(unddb);

        String[] languages = database.language();
        if (languages != null && languages.length > 0)
            this.language = languages[0];
        else
            this.language = NO_LANGUAGE;

        return this;
    }

    public String language() {
        return language;
    }

    public String name() {
        return database.name();
    }

    public Ent[] ents(String s) {
        return Ent.of(database.ents(s));
    }

    @Override
    public void close() {
        if (database != null) {
            database.close();
            database = null;
        }
    }
}
