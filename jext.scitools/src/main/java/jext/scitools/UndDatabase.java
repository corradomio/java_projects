package jext.scitools;

import com.scitools.understand.Understand;
import com.scitools.understand.UnderstandException;
import jext.scitools.und.Ent;
import jext.xml.XPathUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
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

    protected static final int MAX_FILES_INLINE = 20;

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

        SciTools.und().exec("create",
            "-languages", language,
            "-db", undPath.getAbsolutePath()
        );

        SciTools.und().exec("settings",
            "-JavaVersion", "10-18",
            "-db", undPath.getAbsolutePath()
        );
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
    public void addSource(File fileOrDirectory) throws IOException {
        applyOnSource(ADD, fileOrDirectory);
    }

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
            SciTools.und().exec(action, fileOrDirectory.getAbsolutePath(),
                    undPath.getAbsolutePath());
        }
        else {
            SciTools.und().exec(action, /* "-file", */ fileOrDirectory.getAbsolutePath(),
                    undPath.getAbsolutePath());
        }
    }

    private void applyOnSources(String action, List<File> files) throws IOException {
        File tempFile = createSourcesListFile(files);
        SciTools.und().exec(action, "@"+tempFile.getAbsolutePath(),
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
        // if (!"java".equals(this.language))
        //     throw new UnsupportedOperationException("Unsupported language");


        // save the list of files in an external file
        File librariesFile = createLibrariesListFile(libraryFiles);

        // DESN'T WORK!! WHY???
        // {
        //     List<String> command = new ArrayList<>();
        //     command.add("settings");
        //     command.add("-JavaClassPathsAdd");
        //     command.add(String.format("@%s", librariesFile.getAbsolutePath()));
        //     SciTools.und().exec(command);
        // }

        // configure SciTools DB with the list of libraries
        // Note: the list is splitted in chunks of MAX_FILES_INLINE (20) files
        // because it is not possible to pass a lot of files on the command line

        List<File> lfiles = new ArrayList<>(libraryFiles);
        int n = lfiles.size();
        int ssize = MAX_FILES_INLINE;

        for (int i=0; i<n; i += ssize) {
            int l = Math.min(i + ssize, n);
            List<File> subList = lfiles.subList(i, l);

            List<String> command = new ArrayList<>();
            command.add("settings");
            command.add("-JavaClassPathsAdd");
            subList.forEach(libraryFile -> {
                command.add(libraryFile.getAbsolutePath());
            });

            command.add(undPath.getAbsolutePath());

            SciTools.und().exec(command);
        }
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
    public void analyze(boolean update) throws IOException {
        if (!exists())
            throw new IOException("Database '" + undPath.getAbsolutePath() + "' not existent");

        SciTools.und().exec("-verbose", "analyze", update ? "-changed" : "-all",
                undPath.getAbsolutePath());
    }

    // ----------------------------------------------------------------------
    // Database read
    // ----------------------------------------------------------------------

    public UndDatabase open() throws UnderstandException {
        String unddb = undPath.getAbsolutePath();
        database = Understand.open(unddb);
        return this;
    }

    public String language() {
        String[] languages = database.language();
        if (languages != null && languages.length > 0)
            return languages[0];
        else
            return NO_LANGUAGE;
    }

    public String name() {
        return database.name();
    }

    public Ent[] ents(String s) {
        return Ent.of(database.ents(s));
    }

    public Ent lookup_uniquename(String s) {
        return Ent.of(database.lookup_uniquename(s));
    }

    @Override
    public void close() {
        if (database != null) {
            database.close();
            database = null;
        }
    }
}
