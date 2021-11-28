package jext.scitools.und;

import com.scitools.understand.Understand;
import com.scitools.understand.UnderstandException;
import jext.scitools.und.java.UndJavaDatabase;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeoutException;

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


public class UndDatabase implements AutoCloseable {

    public static final String JAVA = "java";

    public static UndDatabase database(File undDatabase, String language, int version) {
        if (JAVA.equals(language))
            return new UndJavaDatabase(undDatabase, version);
        else
            return new UndDatabase(undDatabase, language, version);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected static final String UND_APP = "und";
    protected static final int MAX_FILES_INLINE = 20;

    protected File undDatabase;
    protected String language;
    protected int languageVersion;
    protected com.scitools.understand.Database database;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected UndDatabase(File undDatabase, String language, int languageVersion) {
        this.undDatabase = undDatabase;
        this.language = language;
        this.languageVersion = languageVersion;
    }

    // ----------------------------------------------------------------------
    // Database create/write
    // ----------------------------------------------------------------------

    public boolean exists() {
        return undDatabase.exists();
    }

    /**
     * Delete the 'und' database
     *
     * @throws IOException
     */
    public void delete() throws IOException {
        if (!exists())
            return;

        Files.walkFileTree(undDatabase.toPath(), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /*
    Help For Create
      The create command creates a new database. It can also take in settings
      exactly like the settings command (und help settings).It can take the
      name of the new database as the parameter for the -db switch or as the
      last parameter, so it could look like this

        und create -db newDatabase.und -languages c++ c# plm ...
     */
    public void create() throws IOException {
        if (exists())
            throw new IOException("Database '" + undDatabase.getAbsolutePath() + "' already existent");

        try {
            new ProcessExecutor()
                .command(UND_APP, "create",
                    "-db", undDatabase.getAbsolutePath(),
                    "-languages", language)
                .redirectOutput(new OutputStream() {
                    @Override
                    public void write(int b) {
                        System.out.write(b);
                    }
                })
                .execute();
        } catch (InterruptedException | TimeoutException e) {
            throw new IOException(e);
        }
    }

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
        try {
            if (fileOrDirectory.isDirectory()) {
                new ProcessExecutor()
                    .command(UND_APP,
                        "add", fileOrDirectory.getAbsolutePath(),
                        undDatabase.getAbsolutePath())
                    .redirectOutput(new OutputStream() {
                        @Override
                        public void write(int b) {
                            System.out.write(b);
                        }
                    })
                    .execute();
            }
            else {
                new ProcessExecutor()
                    .command(UND_APP,
                        "add", "-file", fileOrDirectory.getAbsolutePath(),
                        undDatabase.getAbsolutePath()
                    )
                    .redirectOutput(new OutputStream() {
                        @Override
                        public void write(int b) {
                            System.out.write(b);
                        }
                    })
                    .execute();
            }
        } catch (InterruptedException | TimeoutException e) {
            throw new IOException(e);
        }
    }

    public void addSources(List<File> files) throws IOException{
        try {
            File tempFile = createSourcesListFile(files);

            new ProcessExecutor()
                .command(UND_APP,
                    "add", "@"+tempFile.getAbsolutePath(),
                    undDatabase.getAbsolutePath()
                )
                .execute();

            if(!tempFile.delete())
                System.out.println();

        } catch (InterruptedException | TimeoutException e) {
            throw new IOException(e);
        }
    }

    private File createSourcesListFile(List<File> files) throws IOException {
        File tempFile = Files.createTempFile(UND_APP, ".txt").toFile();
        try(Writer writer = new FileWriter(tempFile)) {
            files.forEach(file -> {
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
            throw new IOException("Database '" + undDatabase.getAbsolutePath() + "' not existent");

        try {
            new ProcessExecutor()
                .command(UND_APP,
                    "analyze", update ? "-changed" : "-all",
                    undDatabase.getAbsolutePath()
                )
                .redirectOutput(new OutputStream() {
                    @Override
                    public void write(int b) {
                        System.out.write(b);
                    }
                })
                .execute();
        } catch (InterruptedException | TimeoutException e) {
            throw new IOException(e);
        }
    }

    public void addLibraries(Collection<File> files) throws IOException {
        throw new UnsupportedOperationException();
    }

    // ----------------------------------------------------------------------
    // Database read
    // ----------------------------------------------------------------------

    public UndDatabase open() throws UnderstandException {
        String unddb = undDatabase.getAbsolutePath();
        database = Understand.open(unddb);
        return this;
    }

    public String language() {
        return database.language()[0];
    }

    public String name() {
        return database.name();
    }

    public Entity[] ents(String s) {
        return Entity.of(database.ents(s));
    }

    public Entity lookup_uniquename(String s) {
        return Entity.of(database.lookup_uniquename(s));
    }

    @Override
    public void close() {
        if (database != null) {
            database.close();
            database = null;
        }
    }
}
