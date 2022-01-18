
Und is the command line interface for Understand. It can be run in
three modes.

  1) Processing a text file (this is the same as the process command):
     This will run all the commands in a text file. For example, both

       und processThis.txt
       und processThis.txt forMyDatabase.und

     would run all the commands listed in processThis.txt. For more details
     see the help for the process command. (und help process)

  2) Running a set of commands:
     This runs a set of commands and then exits und. A database must be
     provided each time, either as the last argument, or by using the -db
     switch. Multiple commands are supported except for "help" and "list."
     They are run in the order they appear on the command line and all
     switches and arguments for a particular subcommand must appear
     between that subcommand and the next. For example, running

       und create -db myDb.und -languages c++ add @myFiles.txt analyze -all
       und create -languages c++ add @myFiles.txt analyze -all myDb.und

     is the equivalent of running this set of commands in interactive mode:

       create -db myDb.und -languages c++ / create -langauges c++ myDb.und
       add @myFiles.txt
       analyze -all

     It would create the database, add the files, analyze all, and then exit.

  3) Interactive:
     This mode will be entered if neither a text file nor a command is
     given. While running, it will remember settings such as the last
     database used. For more information, run: (und help interactive).

Und supports the following commands:

     Global Options (can be added to any command)
       -db                    sets the database
       -quiet                 only prints errors
       -verbose               print extra info
       -ini Name              use a configuration file with the given name.
                                Name should not have a file extension. Note
                                that this does not affect named roots.

     Commands
       add                    adds files, directories and roots
       analyze                analyzes the project files
       codecheck              runs CodeCheck
       convert                convert a .udb project to a .und project
       create                 creates an empty database
       export                 exports settings, dependencies, macros,
                                includes or arches
       help                   gives more help information for a command
       import                 imports project settings and arches
       license                gives various license information
       list                   lists information about the project
       metrics                generates project metrics
       process                runs all the commands in a text file
       projectinfo            gives various project information
       purge                  purges the database
       remove                 removes files, directories, roots, and
                                architectures
       report                 generates project reports
       settings               sets project settings, overrides
       uperl                  runs perl scripts
       version                gives the current version

Each of these commands is run in the format [command] [options] [arguments]
For example the add command could be ran like this:

  und add -file somefile.cpp myDb.und

Help is provided for each command and can be accessed using (und help Command)
like this:

  und help add
  und help analyze

and so on.

=============================================================================
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

=============================================================================
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

=============================================================================
Help For CodeCheck
  The codecheck command runs codecheck on all the database files
  printing the log to the screen, and saving the violation information
  in an output directory. The command takes two arguments: the configuration
  to use and the output directory. So, the most basic command could
  look like this:

    und codecheck myConfiguration C:\OutputDir

  The configuration can be the name of a CodeCheck configuration that already
  exists in the project, or the path to an exported configuration file.
  Use 'local' or 'shared' after the configuration name to specify the location.

  There are several switches available for the CodeCheck command. They are:

    -files        This takes in a list file with all the files to run
                  codecheck on. The files must be in the project. The
                  list file does not have to start with @.
                  Additionally, a comma delimited list of line numbers
                  and ranges (5,10,12-30) can be specified after the file name
                  to limit the results being shown to just those lines
    -gitfiles     This switch instructs CodeCheck to only inspect
                  uncommitted changed files. Optionally, the git id
                  (first 8 characters, or entire thing) can be passed
                  as a parameter to only inspect files in that specified
                  revision. To only inspect files after that specified
                  revision, include 'after' following the git id. For example:
                  und codecheck -gitfiles 1ff2406b after Sandbox c:\test\output -db c:\test\util.und
    -changedfiles This switch instructs CodeCheck to use only files
                  that have changed since the last Analysis.
    -showignored  The presence of this switch causes codecheck to show
                  violations including the ignored ones. Sometimes
                  this feature is referred to as "Show False Positive"
    -showexcluded The presence of this switch causes codecheck to show
                  violations including the excluded ones in the detailed log and
                  in a separate section in the generated html report.
    -showchecktimes The presence of this switch causes codecheck to show
                  accumulated check times. Individual check run times
                  are also shown in the detailed log.
    -ignoredonlyresults export only the ignored (false positive) results
                  Does not work with -showignored.
    -showbaselineignored  The presence of this switch causes codecheck to show
                  all the violations set as baseline ignored working in conjunction with -showignored.
                  The baseline ignored violations will not be shown without -showignore set.
    -setbaselineignored  The presence of this switch causes codecheck to set
                  all the violations currently not ignored to ignored.
    -clearbaselineignored  The presence of this switch causes codecheck to clear
                  all the baseline ignores.
    -exportignored  This exports the current Ignored Violations to the
                  specified file. The export file will be created but
                  codecheck will not be run. The file does not have to start
                  with @. Note that even though codecheck will not run, the
                  output directory still needs to be specified.
    -exportignoredall  This exports the current Ignored Violations including
                  ignoreds given in comments to the specified file. Codecheck
                  will run according to the other provided arguments.
    -exportchecks  This exports the current checks to the
                  specified file. The export file will be created but
                  codecheck will not be run. The file does not have to start
                  with @. Note that even though codecheck will not run, the
                  output directory still needs to be specified.
    -addignored   This adds to the current Ignored Violations from the
                  specified file. The file does not have to start with @.
                  *** When importing Ignored Violations, database
                  uniquenames are being used. Note that analyzing the
                  project can alter the uniquename in certain circumstances.
                  This can result in incorrect Ignored Violation assignments.
                  We recommend using relative paths or named roots to
                  minimize incorrect assignments. ***
    -removedignored  This removes the Ignored Violations from the specified
                  file. The file does not have to start with @.
    -flattentree  The presence of this switch causes the files to be simply
                  listed instead of presented in a directory tree format in
                  the output.
    -exitstatus   The presence of this switch causes the CodeCheck to return
                  the number of errors as the error level.
    -showdetaillog   The verbose logging is enabled.
    -requireexplicitcheckid   Automatic/Inline checkIDs are required (* for all).
    -html         The presence of this switch causes the HTML version of the
                  output to be generated in addition to the .csv output.

    -htmlsnippets The presence of this switch causes the HTML version of the
                  output to be generated with the code snippets.

    -reportnameformat The presence of this switch causes the codecheckreportbytable
                  output to be generated with the designated file name format.
                  Valid arguments include: absolute/relative/short.
                  Default or no argument is absolute.

  The switches must appear before the arguments, so a command with switches
  could look like this

    codecheck -html -files filesListedHere.txt weeklyChecks C:\OutputDir
      (Interactive Mode)


=============================================================================
Help For Convert
  The convert command takes a .udb project and creates a new .und project.
  If no new projectName.und path and file is provided,
  the existing .udb path and name will be used.
  -override will override the new .und project if exists.
  -deleteudb will delete the old .udb project file if convert is successful.
    und convert legacyDatabaseName.udb newDatabaseName(.und)

=============================================================================
Help For Create
  The create command creates a new database. It can also take in settings
  exactly like the settings command (und help settings).It can take the
  name of the new database as the parameter for the -db switch or as the
  last parameter, so it could look like this

    und create -db newDatabase.und -languages c++ c# plm ...

  To create a database for a specific git commit, use -gitcommit:

    und create -db newDatabase.und -gitcommit githash -languages ...

  When creating a database at a gitcommit, two additional options are
  available:

    -refdb dbname.und Copy settings from the reference database instead
                      of creating an empty database. Also sets the new
                      database as a comparison project in the referenced
                      database.
    -gitrepo path     Use the provided directory as the git repository.
                      If this is not provided and a reference database
                      is provided, the reference database's repository is
                      used. If a reference database is not available, this
                      defaults to the directory of the new database.

=============================================================================
Help For Export
The export command can be ran in four main ways:

  1) Export Project Settings
     This is the default. It exports the project settings to a backup
     XML file. It requires the file name to export to and has no
     switches. It is run in the format (und export [filename] [database])
     So, it could be run like this:

       und export toHere.xml myProject.und
       und -db myProject.und export toHere.xml
       export toHere.xml    (Interactive mode)

     Note: the database is the very last argument, so this would NOT work:

       und export myProject.und toHere.xml

  2) Export Architecture
     This exports an architecture to an XML file. It requires the name of the
     architecture to export and the file to export to and it also has no switches.
     It is run in the format (und export -arch [archname] [filename] [database]).
     So, it could be run like this:

       und export -arch "Directory Structure" toHere.xml myProject.und
       und -db myProject.und export -arch "Directory Structure" toHere.xml
       export -arch "DirectoryStructure" tohere.xml (Interactive mode)

  3) Export Dependencies
     File, architecture, and class (kind) dependencies can all be exported and they
     can each be exported as a CSV, matrix, or Cytoscape (type). The CSV and
     matrix both generate excel documents (.csv). For more information on
     Cytoscape, see http://cytoscape.org. The general format of the command
     is (export -dependencies [options] kind type outputFile). For example:

       und export -dependencies file csv output.csv myProject.und

     would export the file dependencies in myProject.und to output.csv and

       und export -dependencies class matrix output.csv myProject.und

     would export the class dependencies to output.csv in matrix format. The
     architecture export requires the additional argument of an architecture name,
     which should appear right after "arch," so it would look like this:

       und export -dependencies arch myArch csv output.csv myProject.und

     There are several switches which can be used with this command. Note:
     the switches do not apply for Cytoscape. The switches are:

     -col    The five columns in the output file are "From File," "To File,"
             "References," "From Entities," and "To Entities." The last three
             columns can be specified using <none,refs,froments,toents,all>
             for files, architecture, and classes. For architecture, the first two
             columns can also be specified using <fromfiles,tofiles>. By
             default, all columns are included. So, using -col refs would
             have only the "ToFile," "From File," and "References"
             columns.
     -format This determines the format of the filenames as seen in the
             output file. The options are <short,long,longnoroot> with
             the default as longnoroot
     -sort   This determines whether entries are sorted by the "To File" or
             "From File" columns. <to,from> The default is from.
     -group  By default, there is no grouping. Each file-file dependency has
             has it's own entry. The -group option allows the entries to be
             grouped by either the "To File" or "From File" columns. So,
             grouping by the from column would list all the files that have
             dependencies, but instead of listing each "to" file separately,
             it would give the total number of "to" files found. The command
             takes the parameters <to,from,none>, with the default as none.

     Switches appear before the arguments, so a command with switches in it
     might look like this

       und export -dependencies -col refs froments -sort to -format short file
       csv output.csv myProject.und

  4) Export Macros / Includes
     This prints a list of macros or includes to the screen. It has the
     format und export -[macros/includes]. For example,

       export -macros (Interactive Mode)
       export -includes (Interactive Mode)

     It has one available switch:

     -lang   Specify the language to export macros/includes for. If this
             switch is not provided, all macros/includes will be listed.

=============================================================================
Help For Import
The import command can be run in two main ways:

  1) Import Project Settings
     This is the default. It overrides all the settings, including files
     in the database to match the settings in the XML file. In general,
     this is used when creating a new database. The command could look
     like this

       und create import settings.xml myNewProject.und
       und -db newProject.und import settings.xml (If project already exists)
       import settings.xml (Interactive Mode)

  2) Import Architectures
     This imports architectures into the project. It is invoked using the -arch
     switch. It could look like this

       und import -arch myArch.xml myProject.und
       import -arch myArch.xml (Interactive Mode)


=============================================================================
Help For List
The list command takes in one argument: the thing to list. There are
four different arguments available:

  1) Files
     This lists all the files in the project. It is run like this

       und list files myProject.und

     It has one switch, -tree. The switch causes the files to be
     displayed in a tree instead of in a list. It could look like
     this

       und list -tree files myProject.und

  2) Arches
     This lists all the custom architectures in the project. It has no
     switches and could be run like this

       und list arches myProject.und

  3) Roots
     This lists all the named roots in the project. It has two available
     switches:

       -unmapped      List files with an undefined named root.
       -unportable    List files that are not portable.

     The command could be run like this

       und list -unmapped -unportable roots myProject.und

  4) Settings
     By itself, this command will list the general settings of
     the project (languages, file types, file options). It also has
     switches available for specific settings. They are:

       -all           List all the settings for the project.
       -metrics       List the metric settings and show available metrics.
       -report        List the report settings and show available reports.
       -import        List import (cmake, visual studio, etc) files and options.
       -lang          List all the language specific settings. It
                      can take in a particular language to show the
                      the settings for, or if no parameter is given,
                      it will show the settings for all project languages.
       -override      List the overrides for a particular file or directory.
                      It takes as a parameter a list of files or directories to
                      print the overrides for. It accepts a text file listing
                      one file or directory per line, comments starting with #.
       -terse         Displays only the yes/no options with no lists
       -macros        List only the macros for the languages given with -lang
                      switch instead of all language settings. If the -lang
                      switch is not given, all project macros are listed.
       -includes      List only the includes for the languages given with -lang
                      switch instead of all language settings. If the -lang
                      switch is not given, all project includes are listed.
                      This can appear with the -macro switch so includes and macros
                      are listed.

     The command could look like this

       und -db myProject.und list -override file1.cpp file2.java settings
       und list -override @listfile.txt myProject.und
       list -metrics -reports settings     (Interactive Mode)
       list -all settings     (Interactive Mode)
       list -lang C++ fortran -macros -includes settings
       list -lang C++ settings     (Interactive Mode)


=============================================================================
Help For Metrics
The metrics command can be run in several ways:
  1) Generate Project Metrics
     This is the default. It generates the project metrics. It does not take
     any arguments. It uses the current metrics settings, which can be viewed
     using the list command(und list -metrics settings) and changed using the
     settings command (see und help settings). It could look like this

       und metrics myProject.und
       metrics   (Interactive Mode)

  2) Generate Architecture Metrics
     This generates metrics for a specific architecture. It takes in the
     name of an architecture, and the output file name (optional). The general format
     is und metrics -arch outputFile archName. If an output file is not
     specified, the output file will be in the directory where the database is
     and be named archname_metrics.csv. The command could look like this

       und metrics -arch myArch
       metrics outputFile.csv myArch    (Interactive Mode)

  3) Generate HTML Metrics
     Generate an HTML report of the metrics. By default, all architectures
     are listed in the report. Multiple architectures may be specified.
     The name(s) of any desired architectures are optional. The output
     directory name is also optional. The general format is und metrics -html
     archName1 archName2 outDir. If an output directory is not specified,
     the output directory will be in the directory where the database is and be
     named projname_metrics.
     The command could look like this

       und metrics -html archName1 archName2 c:\Directory
       metrics -html outputdirectory    (Interactive Mode)


=============================================================================
Help For projectinfo
  This command prints out the current version and date.
  As well as where the project folders are located.


=============================================================================
Help For Purge
The purge command removes all parsed data from the Understand database
leaving only the project definition and significantly shrinking the und
project size. Running the analyze command will repopulate the project. This
is ideal for sharing the und project or backing it up.

The purge command has no switches or parameters. It looks like this

  und purge myDataBase.und
  purge myDataBase.und (Interactive mode)
However an open database is not allowed with the purge command.


=============================================================================
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


=============================================================================
Help For Report
  The report command generates reports for the project. It uses the
  current report settings, which can be viewed by using the list command
  (und list -reports settings) and changed using the settings command (see
  und help settings). The command could look like this

    und report myProject.und
    report  (Interactive Mode)


=============================================================================
Help For Settings
The 'settings' command is use to modify the settings in an Understand project.
The names for each setting can be found via 'und list -all settings project.und'

Basic Settings:
  Setting a value works under a "mush" strategy. e.g.
  und settings -CategoryNameOfSetting true

 So, to change a setting
    1) Type a "-"
    2) Type in the setting heading (like report, metrics, C++, and so on)
       Do not include subheadings (-c++ignore not -c++includesignore).
    3) Type in the name (as it appears in und list settings) of the setting
       to change without any spaces or quotes
    4) Type the new value(s) for that setting.

  For example:
      settings -ReportDisplayCreationDate on
      settings -ReportDisplayParameters on
      settings -ReportFileNameDisplayMode full

  All project settings can be viewed with the command:
     und list -all settings project.und

Lists (such as includes, macros, or selected metrics):
  By default, a switch changing a list will clear the previous list.
  If you only wish to add to the list without clearing the previous list
  append the word "add" onto the end of the switch.
  Any list command can take in a list file (@fileWithArgs.txt).
  For example this would enable ONLY these two reports:
    settings  -ReportReports "Data Dictionary"  "File Contents"
  And the Add keyword would append the list already enabled:
     settings -ReportReportsAdd "Data Dictionary"

     settings -C++MacrosAdd MYLONG="Long Text"

Custom Values:
  Some options allow for custom values, like the HTML number of pages, which
  can be single, alphabetic, or every N entities where N is a custom value.
  In this case, the value for that setting can be shown either as NEntity=Value
  or simply as Value.

      settings -ReportNumberOfPages NEntity=250
      settings -ReportNumberOfPages 250

General Commands:
  General commands do not have to be prepended with "general," so, for example
  both -generalLanguages and -languages would set the project languages.

Override Commands:
  Override commands must have the file or directory they are applied to as the
  first argument following the switch. For example,

    und settings -override_c++Includes forThisFile.cpp include1 include2 ...

  would be a valid format. There are two options for overriding multiple files:
    1) A text file may be used where each line starts with the file to be
       overridden and is followed by all the override switches for that file.
        Lines starting with # (not -c#) are ignored. The file could look like this

         # Some comment that is ignored
         file1 -override_c++_includes inc1 inc2 -override_c++_macros ...
         file2 -override_c++_includes inc3 inc1 -override_file_encoding UTF-8
         directory -file_types .h=c++ # Comments here are okay.

       The file should start with @ so the command could look like this

         und settings -override @overrideFile.txt myProject.und

    2) The command can take a list of files followed by == and then the
       arguments for the override switch. The arguments will be applied to each
       file. So, for example

         -override_c++Includes f1.c f2.c == i1 i2 -override_c++_macros f3.c ...

       would be equivalent to

         und settings -override_c++Includes f1.c i1 i2
         und settings -override_c++Includes f2.c i1 i2
         und settings -override_c++_macros f3.c ...

Text Files:
  A text file can be read in containing all the settings. The '#' sign
  designates comments unless prepended by -c (because then it refers to
  the C# language. The '#' sign can be included as a value by prepending
  a '\'. Whitespace is ignored unless in quotes. Literal quotes can be
  added by prepending a '\'. The switches and parameters should appear
  the same as they would on the command line.
  For example, a file could look like this

    -ReportDisplayCreationDate on -ReportReports report1
    report2 # Comments and whitespace are ignored
    report3 -anotherSwitch moreParameters ...

  The file should be prepended with @, and could be run like this

    und settings @fileWithSettings.txt myProject.und


=============================================================================
Help For uperl
The uperl command runs Perl scripts from the command line. Scripts that
use the $ent->draw command are not supported
The general format of the command is (und uperl scriptName scriptArgs)
The arguments passed on to Perl will not include 'und' or any arguments
that und strips out such as -quiet or -db. For example,

  und uperl myScript.pl -quiet "arg1 space" arg2 myProject.und

would run the script myScript with the following arguments:

  0  uperl
  1  myScript.pl
  2  arg1 space
  3  arg2

From the scripts, the Understand::CommandLine::db() function will return
the current database used in und.

=============================================================================
Help For Version
  This command just prints out the current version.
The switch -dbversion  will print out the database creation and modified build numbers.
It could look like this

      und version
 or
      und version -dbversion someDatabase.und


=============================================================================
=============================================================================

and:   A B
not:  ~A
wilcards:    * ? ~

---------------------
- Java Entity Kinds -
---------------------

Below are listed the general categories of Java entity kinds. When these categories are used literally, as filters, the full kind names that match have been listed beneath them.

File
   Java File
   Java File Jar

Method
   Java Static Generic Method Protected Member
   Java Static Generic Method Public Member
   Java Static Method Public Main Member
   Java Unknown Method Member
   Java Method Default Member
   Java Method Lambda
   Java Method Private Member
   Java Unresolved Method
   Java Method Protected Member
   Java Unresolved External Final Method Default Member
   Java Method Public Member
   Java Unresolved External Final Method Private Member
   Java Implicit Method Public Member
   Java Unresolved External Final Method Protected Member
   Java Generic Method Default Member
   Java Abstract Method Default Member
   Java Unresolved External Final Method Public Member
   Java Generic Method Private Member
   Java Abstract Method Protected Member
   Java Unresolved External Method Default Member
   Java Generic Method Protected Member
   Java Abstract Method Public Member
   Java Unresolved External Method Private Member
   Java Generic Method Public Member
   Java Abstract Generic Method Default Member
   Java Unresolved External Method Protected Member
   Java Abstract Generic Method Protected Member
   Java Unresolved External Method Public Member
   Java Abstract Generic Method Public Member
   Java Unresolved External Static Final Method Default Member
   Java Unresolved External Static Final Method Private Member
   Java Unresolved Extermal Static Final Method Protected Member
   Java Unresolved External Static Final Method Public Member
   Java Unresolved External Static Method Default Member
   Java Unresolved External Static Method Private Member
   Java Unresolved External Static Method Protected Member
   Java Unresolved External Static Method Public Member
   Java Unresolved External Static Method Public Main Member
   Java Method Constructor Member Default
   Java Method Constructor Member Private
   Java Method Constructor Member Protected
   Java Method Constructor Member Public
   Java Static Final Method Default Member
   Java Static Final Method Private Member
   Java Static Final Method Protected Member
   Java Static Final Method Public Member
   Java Static Final Generic Method Default Member
   Java Static Final Generic Method Private Member
   Java Static Final Generic Method Protected Member
   Java Final Method Default Member
   Java Static Final Generic Method Public Member
   Java Final Method Private Member
   Java Final Method Protected Member
   Java Final Method Public Member
   Java Generic Final Method Default Member
   Java Static Method Default Member
   Java Generic Final Method Private Member
   Java Static Method Private Member
   Java Final Generic Method Protected Member
   Java Final Generic Method Public Member
   Java Static Method Protected Member
   Java Static Method Public Member
   Java Static Generic Method Default Member
   Java Static Generic Method Private Member

Module
   Java Unresolved Module
   Java Module
   Java Unknown Module

Package
   Java Unknown Package
   Java Unresolved Package
   Java Package
   Java Package Unnamed

Parameter
   Java Catch Parameter
   Java Parameter

Type
   Java Static Abstract Class Type Default Member
   Java Static Abstract Class Type Private Member
   Java Static Abstract Class Type Protected Member
   Java Static Abstract Class Type Public Member
   Java Static Abstract Generic Class Type Default Member
   Java Static Abstract Generic Class Type Private Member
   Java Static Abstract Generic Class Type Protected Member
   Java Static Abstract Generic Class Type Public Member
   Java Static Class Type Default Member
   Java Static Class Type Private Member
   Java Static Class Type Protected Member
   Java Static Class Type Public Member
   Java Static Generic Class Type Default Member
   Java Static Class Generic Type Private Member
   Java Static Generic Class Type Protected Member
   Java Static Generic Class Type Public Member
   Java Static Final Class Type Default Member
   Java Static Final Class Type Private Member
   Java Static Final Class Type Protected Member
   Java Static Final Class Type Public Member
   Java Static Final Generic Class Type Default Member
   Java Static Final Generic Class Type Private Member
   Java Static Final Generic Class Type Protected Member
   Java Static Final Generic Class Type Public Member
   Java Abstract Class Type Default Member
   Java Abstract Class Type Private Member
   Java Abstract Class Type Protected Member
   Java Abstract Class Type Public Member
   Java Abstract Generic Class Type Default Member
   Java Abstract Generic Class Type Private Member
   Java Class Type TypeVariable
   Java Abstract Generic Class Type Protected Member
   Java Unknown Class Type Member
   Java Abstract Generic Class Type Public Member
   Java Abstract Enum Type Default Member
   Java Abstract Enum Type Private Member
   Java Abstract Enum Type Protected Member
   Java Abstract Enum Type Public Member
   Java Annotation Interface Type Default
   Java Annotation Interface Type Private
   Java Annotation Interface Type Protected
   Java Annotation Interface Type Public
   Java Class Type Anonymous Member
   Java Class Type Default Member
   Java Class Type Private Member
   Java Class Type Protected Member
   Java Class Type Public Member
   Java Generic Class Type Default Member
   Java Generic Class Type Private Member
   Java Generic Class Type Protected Member
   Java Generic Class Type Public Member
   Java Unresolved Type
   Java Enum Class Type Default Member
   Java Enum Class Type Private Member
   Java Enum Class Type Protected Member
   Java Enum Class Type Public Member
   Java Final Class Type Default Member
   Java Final Class Type Private Member
   Java Final Class Type Protected Member
   Java Final Class Type Public Member
   Java Final Generic Class Type Default Member
   Java Final Generic Class Type Private Member
   Java Final Generic Class Type Protected Member
   Java Final Generic Class Type Public Member
   Java Interface Type Default
   Java Interface Type Private
   Java Interface Type Protected
   Java Interface Type Public
   Java Generic Interface Type Default
   Java Generic Interface Type Private
   Java Generic Interface Type Protected
   Java Generic Interface Type Public

Variable
   Java Unknown Variable Member
   Java Variable EnumConstant Public Member
   Java Static Final Variable Default Member
   Java Static Final Variable Private Member
   Java Static Final Variable Protected Member
   Java Static Final Variable Public Member
   Java Unresolved Variable
   Java Variable Default Member
   Java Variable Local
   Java Variable Private Member
   Java Variable Protected Member
   Java Variable Public Member
   Java Static Variable Default Member
   Java Final Variable Default Member
   Java Static Variable Private Member
   Java Final Variable Local
   Java Static Variable Protected Member
   Java Final Variable Private Member
   Java Static Variable Public Member
   Java Final Variable Protected Member
   Java Final Variable Public Member
   Java Implicit Final Variable Public Member

------------------------
- Java Reference Kinds -
------------------------

Below are listed the general categories of Java reference kinds, both forward and inverse relations. When these
categories are used literally, as filters, the full kind names that match have been listed beneath them.

Call (Callby)
   Java Call
   Java Call Nondynamic

Cast (Castby)
   Java Cast

Contain (Containin)
   Java Contain

Couple (Coupleby)
   Java Extend Couple
   Java Couple
   Java Extend Couple External
   Java Extend Couple Implicit External
   Java Extend Couple Implicit
   Java Implement Couple

Create (Createby)
   Java Create

Declare (Declarein)
   Java Declare

Define (Definein)
   Java Define
   Java Define Implicit

DotRef (DotRefby)
   Java DotRef

End (Endby)
   Java End

Export (Exportby)
   Java Export

Import (Importby)
   Java Import
   Java Import Demand

Modify (Modifyby)
   Java Modify Deref Partial
   Java Modify

ModuleUse (ModuleUseby)
   Java ModuleUse

Provide (Provideby)
   Java Provide

Require (Requireby)
   Java Require

Set (Setby)
   Java Set Deref Partial
   Java Set
   Java Set Init

Typed (Typedby)
   Java Typed

Use (Useby)
   Java Use
   Java Use Deref Partial
   Java Use Ptr
   Java Use Return

Open (Openby)
   Java Open

Overrides (Overriddenby)
   Java Overrides

Throw (Throwby)
   Java Throw