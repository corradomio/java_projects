Good day Salwa,
Good day Ahmed,

Thank you for the speedy signing of our Letter of Intent.

Here is your first 6-month Understand development licence:
Licence Key: MDsqWKp60Z97vB3h
Valid until: 23 May 2022
Download LINK

Please let me know if you have any questions.
For questions of a technical nature, you are free to contact Ian @ support@emenda.com.
The understand support page can be found HERE.

I look forward to working with you and if I don't hear from you, I will assume all is going well with Understand and the product release, but I will contact you in about 3-months to find out if you need anything from Emenda.

Kind regards
Jason

https://support.scitools.com/support/solutions/articles/70000582782-command-line-licensing

und -setlicensecode MDsqWKp60Z97vB3h


https://support.scitools.com/support/solutions/articles/70000582856-api-documentation

Set the license code from command line

und -setlicensecode MDsqWKp60Z97vB3h

https://licensing.scitools.com/download




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
or:    A,B
not:  ~A
wilcards:    * ? ~ (at the end or in middle)

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

------------------------
- Entities & Relations -
------------------------
Entities (Java)

    Java Abstract Class Type Default Member
    Java Abstract Class Type Private Member
    Java Abstract Class Type Protected Member
    Java Abstract Class Type Public Member
    Java Abstract Generic Class Type Default Member
    Java Abstract Generic Class Type Private Member
    Java Abstract Generic Class Type Protected Member
    Java Abstract Generic Class Type Public Member
    Java Abstract Enum Type Default Member
    Java Abstract Enum Type Private Member
    Java Abstract Enum Type Protected Member
    Java Abstract Enum Type Public Member
    Java Abstract Method Default Member
    Java Abstract Method Protected Member
    Java Abstract Method Public Member
    Java Abstract Generic Method Default Member
    Java Abstract Generic Method Protected Member
    Java Abstract Generic Method Public Member
    Java Annotation Interface Type Default
    Java Annotation Interface Type Private
    Java Annotation Interface Type Protected
    Java Annotation Interface Type Public
    Java Catch Parameter
    Java Class Type Anonymous Member
    Java Class Type Default Member
    Java Class Type Private Member
    Java Class Type Protected Member
    Java Class Type Public Member
    Java Generic Class Type Default Member
    Java Generic Class Type Private Member
    Java Generic Class Type Protected Member
    Java Generic Class Type Public Member
    Java Method Constructor Member Default
    Java Method Constructor Member Private
    Java Method Constructor Member Protected
    Java Method Constructor Member Public
    Java Variable EnumConstant Public Member
    Java Enum Class Type Default Member
    Java Enum Class Type Private Member
    Java Enum Class Type Protected Member
    Java Enum Class Type Public Member
    Java File
    Java File Jar
    Java Final Class Type Default Member
    Java Final Class Type Private Member
    Java Final Class Type Protected Member
    Java Final Class Type Public Member
    Java Final Generic Class Type Default Member
    Java Final Generic Class Type Private Member
    Java Final Generic Class Type Protected Member
    Java Final Generic Class Type Public Member
    Java Final Method Default Member
    Java Final Method Private Member
    Java Final Method Protected Member
    Java Final Method Public Member
    Java Generic Final Method Default Member
    Java Generic Final Method Private Member
    Java Final Generic Method Protected Member
    Java Final Generic Method Public Member
    Java Final Variable Default Member
    Java Final Variable Local
    Java Final Variable Private Member
    Java Final Variable Protected Member
    Java Final Variable Public Member
    Java Implicit Final Variable Public Member
    Java Interface Type Default
    Java Interface Type Private
    Java Interface Type Protected
    Java Interface Type Public
    Java Generic Interface Type Default
    Java Generic Interface Type Private
    Java Generic Interface Type Protected
    Java Generic Interface Type Public
    Java Method Default Member
    Java Method Lambda
    Java Method Private Member
    Java Method Protected Member
    Java Method Public Member
    Java Implicit Method Public Member
    Java Generic Method Default Member
    Java Generic Method Private Member
    Java Generic Method Protected Member
    Java Generic Method Public Member
    Java Module
    Java Package
    Java Package Unnamed
    Java Parameter
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
    Java Static Final Method Default Member
    Java Static Final Method Private Member
    Java Static Final Method Protected Member
    Java Static Final Method Public Member
    Java Static Final Generic Method Default Member
    Java Static Final Generic Method Private Member
    Java Static Final Generic Method Protected Member
    Java Static Final Generic Method Public Member
    Java Static Final Variable Default Member
    Java Static Final Variable Private Member
    Java Static Final Variable Protected Member
    Java Static Final Variable Public Member
    Java Static Method Default Member
    Java Static Method Private Member
    Java Static Method Protected Member
    Java Static Method Public Member
    Java Static Generic Method Default Member
    Java Static Generic Method Private Member
    Java Static Generic Method Protected Member
    Java Static Generic Method Public Member
    Java Static Method Public Main Member
    Java Static Variable Default Member
    Java Static Variable Private Member
    Java Static Variable Protected Member
    Java Static Variable Public Member
    Java Class Type TypeVariable
    Java Unknown Class Type Member
    Java Unknown Method Member
    Java Unknown Module
    Java Unknown Package
    Java Unknown Variable Member
    Java Unresolved Method
    Java Unresolved External Final Method Default Member
    Java Unresolved External Final Method Private Member
    Java Unresolved External Final Method Protected Member
    Java Unresolved External Final Method Public Member
    Java Unresolved External Method Default Member
    Java Unresolved External Method Private Member
    Java Unresolved External Method Protected Member
    Java Unresolved External Method Public Member
    Java Unresolved External Static Final Method Default Member
    Java Unresolved External Static Final Method Private Member
    Java Unresolved Extermal Static Final Method Protected Member
    Java Unresolved External Static Final Method Public Member
    Java Unresolved External Static Method Default Member
    Java Unresolved External Static Method Private Member
    Java Unresolved External Static Method Protected Member
    Java Unresolved External Static Method Public Member
    Java Unresolved External Static Method Public Main Member
    Java Unresolved Module
    Java Unresolved Package
    Java Unresolved Type
    Java Unresolved Variable
    Java Variable Default Member
    Java Variable Local
    Java Variable Private Member
    Java Variable Protected Member
    Java Variable Public Member
	
Relations (Java)

    Java Call
    Java Callby
    Java Call Nondynamic
    Java Callby Nondynamic
    Java Use Cast
    Java Useby Castby
    Java Contain
    Java Containin
    Java Couple
    Java Coupleby
    Java Create
    Java Createby
    Java Declare
    Java Declarein
    Java Define
    Java Definein
    Java Define Implicit
    Java Definein Implicit
    Java DotRef
    Java DotRefby
    Java End
    Java Endby
    Java Export
    Java Exportby
    Java Extend Couple
    Java Extendby Coupleby
    Java Extend Couple External
    Java Extendby Coupleby External
    Java Extend Couple Implicit External
    Java Extendby Coupleby Implicit External
    Java Extend Couple Implicit
    Java Extendby Coupleby Implicit
    Java Implement Couple
    Java Implementby Coupleby
    Java Import
    Java Importby
    Java Import Demand
    Java Importby Demand
    Java Modify
    Java Modifyby
    Java Modify Deref Partial
    Java Modifyby Deref Partial
    Java ModuleUse
    Java ModuleUseby
    Java Open
    Java Openby
    Java Overrides
    Java Overriddenby
    Java Provide
    Java Provideby
    Java Require
    Java Requireby
    Java Set
    Java Setby
    Java Set Init
    Java Setby Init
    Java Set Deref Partial
    Java Setby Deref Partial
    Java Throw
    Java Throwby
    Java Typed
    Java Typedby
    Java Use
    Java Useby
    Java Use Deref Partial
    Java Useby Deref Partial
    Java Use Ptr
    Java Useby Ptr
    Java Use Return
    Java Useby Return
	



Entities
    Ada Abstract Function
    Ada Abstract Function Local
    Ada Abstract Function Operator
    Ada Abstract Function Operator Local
    Ada Abstract Procedure
    Ada Abstract Procedure Local
    Ada Abstract Tagged Type Record
    Ada Abstract Tagged Type Record Local
    Ada Abstract Tagged Type Record Private
    Ada Abstract Tagged Type Record Limited Private
    Ada Component
    Ada Component Local
    Ada Variant Component
    Ada Variant Component Local
    Ada Discriminant Component
    Ada Discriminant Component Local
    Ada Constant Object
    Ada Constant Object External
    Ada Constant Object Local
    Ada Constant Object Deferred
    Ada Constant Object Deferred External
    Ada Entry
    Ada Entry Body
    Ada Enumeration Literal
    Ada Exception
    Ada Exception Others
    Ada Exception Local
    Ada Exception Object Local
    Ada File
    Ada Function
    Ada Function External
    Ada Function Local
    Ada Function Operator
    Ada Function Operator Local
    Ada Generic Function
    Ada Generic Function Local
    Ada Generic Package
    Ada Generic Package Local
    Ada Generic Procedure
    Ada Generic Procedure Local
    Ada Gpr Package
    Ada Gpr Project
    Ada Gpr Type
    Ada Gpr Project Unknown
    Ada Gpr Package Unresolved
    Ada Gpr Project Unresolved
    Ada Gpr Unresolved
    Ada Gpr Variable
    Ada LiteralParam Local
    Ada Loop Object Local
    Ada Implicit
    Ada Object
    Ada Object External
    Ada Object Local
    Ada Package
    Ada Package Local
    Ada Parameter
    Ada Procedure
    Ada Procedure External
    Ada Procedure Local
    Ada Protected
    Ada Protected Local
    Ada Protected Object
    Ada Protected Object Local
    Ada Protected Type
    Ada Protected Type Local
    Ada Protected Type Private
    Ada Protected Type Limited Private
    Ada Tagged Type Record
    Ada Tagged Type Record Local
    Ada Tagged Type Record Private
    Ada Tagged Type Record Limited Private
    Ada Task
    Ada Task Local
    Ada Task Object
    Ada Task Object Local
    Ada Task Type
    Ada Task Type Local
    Ada Task Type Private
    Ada Task Type Limited Private
    Ada Type
    Ada Type Incomplete
    Ada Type Local
    Ada Type Private
    Ada Type Limited Private
    Ada Type Access
    Ada Type Access Local
    Ada Type Access Private
    Ada Type Access Limited Private
    Ada Type Access Subprogram
    Ada Type Access Subprogram Local
    Ada Type Access Subprogram Private
    Ada Type Access Subprogram Limited Private
    Ada Type Array
    Ada Type Array Local
    Ada Type Array Private
    Ada Type Array Limited Private
    Ada Type Enumeration
    Ada Type Enumeration Local
    Ada Type Enumeration Private
    Ada Type Enumeration Limited Private
    Ada Type Interface
    Ada Type Record
    Ada Type Record Local
    Ada Type Record Private
    Ada Type Record Limited Private
    Ada Unknown
    Ada Unresolved
    Ada Unresolved External Function
    Ada Unresolved External Object
    Ada Unresolved External Procedure
    Ada Unresolved File
	
    Assembly Class
    Assembly Common
    Assembly Data Constant Global
    Assembly Data Constant Local
    Assembly Data Constant Block Global
    Assembly Data Constant Block Local
    Assembly Data Variable Global
    Assembly Data Variable Local
    Assembly File
    Assembly Label Global
    Assembly Label Local
    Assembly Predefined Symbol
    Assembly Macro Global
    Assembly Macro Local
    Assembly Reserved Segment Global
    Assembly Reserved Segment Local
    Assembly Section
    Assembly Symbol Global
    Assembly Symbol Local
    Assembly Unknown File
    Assembly Unknown Symbol
    Assembly Unresolved Symbol
    Assembly Unresolved File
    Assembly Unresolved Macro
	
    Basic Namespace Alias
    Basic Type Alias
    Basic Const Local
    Basic Constructor Member Method Shared
    Basic Enumerator
    Basic File
    Basic Dll File
    Basic Friend Const Member Field
    Basic Friend Constructor Member Method
    Basic Friend Member Event
    Basic Friend Member Event Shared
    Basic Friend Member Function Method
    Basic Unresolved Friend Member Extern Function Method
    Basic Friend Member Function Method MustOverride
    Basic Friend Member Function Method NotInheritable
    Basic Friend Member Function Method Overridable
    Basic Friend Member Function Method Shared
    Basic Friend Member Sub Method
    Basic Unresolved Friend Member Extern Sub Method
    Basic Friend Member Sub Method MustOverride
    Basic Friend Member Sub Method NotInheritable
    Basic Friend Member Sub Method Overridable
    Basic Friend Member Sub Method Shared
    Basic Friend Module
    Basic Friend Member Property
    Basic Friend Member Property MustOverride
    Basic Friend Member Property NotInheritable
    Basic Friend Member Property Overridable
    Basic Friend Member Property Shared
    Basic Friend Type Class
    Basic Friend Type Class MustInherit
    Basic Friend Type Class NotInheritable
    Basic Friend Type Delegate
    Basic Friend Type Enum
    Basic Friend Type Generic Class
    Basic Friend Type Generic Class MustInherit
    Basic Friend Type Generic Class NotInheritable
    Basic Friend Type Interface
    Basic Friend Type Struct
    Basic Friend Member Variable Field
    Basic Friend Member Variable Field Shared
    Basic Namespace
    Basic Parameter ParamArray
    Basic Parameter Ref
    Basic Parameter Value
    Basic Protected Const Member Field
    Basic Protected Constructor Member Method
    Basic Protected Member Event
    Basic Protected Member Event Shared
    Basic Protected Member Function Method
    Basic Unresolved Protected Member Extern Function Method
    Basic Protected Member Method Function MustOverride
    Basic Protected Member Method Function NotInheritable
    Basic Protected Member Method Function Overridable
    Basic Protected Member Method Function Shared
    Basic Protected Member Sub Method
    Basic Unresolved Protected Member Extern Sub Method
    Basic Protected Member Sub Method MustOverride
    Basic Protected Member Sub Method NotInheritable
    Basic Protected Member Sub Method Overridable
    Basic Protected Member Sub Method Shared
    Basic Protected Module
    Basic Protected Member Property
    Basic Protected Member Property MustOverride
    Basic Protected Member Property NotInheritable
    Basic Protected Member Property Overridable
    Basic Protected Member Property Shared
    Basic Protected Type Class
    Basic Protected Type Class MustInherit
    Basic Protected Type Class NotInheritable
    Basic Protected Type Delegate
    Basic Protected Type Enum
    Basic Protected Type Generic Class
    Basic Protected Type Generic Class MustInherit
    Basic Protected Type Generic Class NotInheritable
    Basic Protected Type Interface
    Basic Protected Type Struct
    Basic Protected Member Variable Field
    Basic Protected Member Variable Field Shared
    Basic Protected Friend Const Member Field
    Basic Protected Friend Constructor Member Method
    Basic Protected Friend Member Event
    Basic Protected Friend Member Event Shared
    Basic Protected Friend Member Function Method
    Basic Unresolved Protected Friend Member Extern Function Method
    Basic Protected Friend Member Function Method MustOverride
    Basic Protected Friend Member Function Method NotInheritable
    Basic Protected Friend Member Function Method Overridable
    Basic Protected Friend Member Function Method Shared
    Basic Protected Friend Member Sub Method
    Basic Unresolved Protected Friend Member Extern Sub Method
    Basic Protected Friend Member Sub Method MustOverride
    Basic Protected Friend Member Sub Method NotInheritable
    Basic Protected Friend Member Sub Method Overridable
    Basic Protected Friend Member Sub Method Shared
    Basic Protected Friend Module
    Basic Protected Friend Member Property
    Basic Protected Friend Member Property MustOverride
    Basic Protected Friend Member Property NotInheritable
    Basic Protected Friend Member Property Overridable
    Basic Protected Friend Member Property Shared
    Basic Protected Friend Type Class
    Basic Protected Friend Type Class MustInherit
    Basic Protected Friend Type Class NotInheritable
    Basic Protected Friend Type Delegate
    Basic Protected Friend Type Enum
    Basic Protected Friend Type Generic Class
    Basic Protected Friend Type Generic Class MustInherit
    Basic Protected Friend Type Generic Class NotInheritable
    Basic Protected Friend Type Interface
    Basic Protected Friend Type Struct
    Basic Protected Friend Member Variable Field
    Basic Protected Friend Member Variable Field Shared
    Basic Private Const Member Field
    Basic Private Constructor Member Method
    Basic Private Member Event
    Basic Private Member Event Shared
    Basic Private Member Function Method
    Basic Unresolved Private Member Extern Function Method
    Basic Private Member Function Method Shared
    Basic Private Member Sub Method
    Basic Unresolved Private Member Extern Sub Method
    Basic Private Member Sub Method Shared
    Basic Private Module
    Basic Private Member Property
    Basic Private Member Property Shared
    Basic Private Type Class
    Basic Private Type Class MustInherit
    Basic Private Type Class NotInheritable
    Basic Private Type Delegate
    Basic Private Type Enum
    Basic Private Type Generic Class
    Basic Private Type Generic Class MustInherit
    Basic Private Type Generic Class NotInheritable
    Basic Private Type Interface
    Basic Private Type Struct
    Basic Private Member Variable Field
    Basic Private Member Variable Field Shared
    Basic Public Const Member Field
    Basic Public Constructor Member Method
    Basic Public Member Event
    Basic Public Member Event Shared
    Basic Public Member Function Method
    Basic Unresolved Public Member Extern Function Method
    Basic Public Member Function Method MustOverride
    Basic Public Member Function Method NotInheritable
    Basic Public Member Function Method Overridable
    Basic Public Member Function Method Shared
    Basic Public Member Sub Method
    Basic Unresolved Public Member Extern Sub Method
    Basic Public Member Sub Method MustOverride
    Basic Public Member Sub Method NotInheritable
    Basic Public Member Sub Method Overridable
    Basic Public Member Sub Method Shared
    Basic Public Module
    Basic Public Member Property
    Basic Public Member Property MustOverride
    Basic Public Member Property NotInheritable
    Basic Public Member Property Overridable
    Basic Public Member Property Shared
    Basic Public Type Class
    Basic Public Type Class MustInherit
    Basic Public Type Class NotInheritable
    Basic Public Type Delegate
    Basic Public Type Enum
    Basic Public Type Generic Class
    Basic Public Type Generic Class MustInherit
    Basic Public Type Generic Class NotInheritable
    Basic Public Type Interface
    Basic Public Type Struct
    Basic Public Member Variable Field
    Basic Public Member Variable Field Shared
    Basic Type Parameter
    Basic Unknown Type Class
    Basic Unknown Type Interface
    Basic Unknown Member Method
    Basic Unknown Namespace
    Basic Unknown Type
    Basic Unknown Variable
    Basic Unresolved Constructor Member Method Shared
    Basic Unresolved Dynamic Member
    Basic Unresolved Enumerator
    Basic Unresolved Finalizer
    Basic Unresolved Friend Constructor Member Method
    Basic Unresolved Friend Member Event
    Basic Unresolved Friend Member Event Shared
    Basic Unresolved Friend Member Method
    Basic Unresolved Friend Extern Member Method
    Basic Unresolved Friend Member Method MustOverride
    Basic Unresolved Friend Member Method NotInheritable
    Basic Unresolved Friend Member Method Overridable
    Basic Unresolved Friend Member Method Shared
    Basic Unresolved Friend Member Property
    Basic Unresolved Friend Member Property MustOverride
    Basic Unresolved Friend Member Property NotInheritable
    Basic Unresolved Friend Member Property Overridable
    Basic Unresolved Friend Member Property Shared
    Basic Unresolved Namespace
    Basic Unresolved Module
    Basic Unresolved Protected Constructor Member Method
    Basic Unresolved Protected Member Event
    Basic Unresolved Protected Member Event Shared
    Basic Unresolved Protected Member Method
    Basic Unresolved Protected Extern Member Method
    Basic Unresolved Protected Member Method MustOverride
    Basic Unresolved Protected Member Method NotInheritable
    Basic Unresolved Protected Member Method Overridable
    Basic Unresolved Protected Member Method Shared
    Basic Unresolved Protected Member Property
    Basic Unresolved Protected Member Property MustOverride
    Basic Unresolved Protected Member Property NotInheritable
    Basic Unresolved Protected Member Property Overridable
    Basic Unresolved Protected Member Property Shared
    Basic Unresolved Protected Friend Constructor Member Method
    Basic Unresolved Protected Friend Member Event
    Basic Unresolved Protected Friend Member Event Shared
    Basic Unresolved Protected Friend Member Method
    Basic Unresolved Protected Friend Extern Member Method
    Basic Unresolved Protected Friend Member Method MustOverride
    Basic Unresolved Protected Friend Member Method NotInheritable
    Basic Unresolved Protected Friend Member Method Overridable
    Basic Unresolved Protected Friend Member Method Shared
    Basic Unresolved Protected Friend Member Property
    Basic Unresolved Protected Friend Member Property MustOverride
    Basic Unresolved Protected Friend Member Property NotInheritable
    Basic Unresolved Protected Friend Member Property Overridable
    Basic Unresolved Protected Friend Member Property Shared
    Basic Unresolved Private Constructor Member Method
    Basic Unresolved Private Member Event
    Basic Unresolved Private Member Event Shared
    Basic Unresolved Private Member Method
    Basic Unresolved Private Extern Member Method
    Basic Unresolved Private Member Method Shared
    Basic Unresolved Private Member Property
    Basic Unresolved Private Member Property Shared
    Basic Unresolved Public Constructor Member Method
    Basic Unresolved Public Member Event
    Basic Unresolved Public Member Event Shared
    Basic Unresolved Public Member Method
    Basic Unresolved Public Extern Member Method
    Basic Unresolved Public Member Method MustOverride
    Basic Unresolved Public Member Method NotInheritable
    Basic Unresolved Public Member Method Overridable
    Basic Unresolved Public Member Method Shared
    Basic Unresolved Public Member Property
    Basic Unresolved Public Member Property MustOverride
    Basic Unresolved Public Member Property NotInheritable
    Basic Unresolved Public Member Property Overridable
    Basic Unresolved Public Member Property Shared
    Basic Unresolved Type
    Basic Unresolved Variable
    Basic Variable Local
	
    C Abstract Class Type
    C Abstract Class Type Template
    C Abstract Struct Type
    C Abstract Struct Type Template
    C Class Type
    C Class Type Template
    C Code File
    C Enum Type
    C Enumerator
    C Function
    C Function Template
    C Function Interrupt
    C Function Interrupt Template
    C Function Interrupt Static
    C Function Interrupt Static Frominclude
    C Function Interrupt Static Template
    C Function Interrupt Static Template Frominclude
    C Function Static
    C Function Static Frominclude
    C Function Static Template
    C Function Static Template Frominclude
    C Header File
    C Inactive Macro
    C Label
    C Macro
    C Macro Functional
    C Namespace
    C Namespace Alias
    C Macro Project
    C Object Global
    C Object Global Static
    C Object Global Static Frominclude
    C Object Local
    C Object Local Static
    C Parameter
    C Private Member Abstract Class Type
    C Private Member Abstract Class Type Template
    C Private Member Abstract Struct Type
    C Private Member Abstract Struct Type Template
    C Private Member Class Type
    C Private Member Class Type Template
    C Private Member Const Function
    C Private Member Const Function Template
    C Private Member Const Function Virtual
    C Private Member Const Function Virtual Pure
    C Private Member Const Volatile Function
    C Private Member Const Volatile Function Template
    C Private Member Const Volatile Function Virtual
    C Private Member Const Volatile Function Virtual Pure
    C Private Member Enum Type
    C Private Member Enumerator
    C Private Member Function
    C Private Member Function Template
    C Private Member Function Explicit
    C Private Member Function Explicit Template
    C Private Member Function Static
    C Private Member Function Static Template
    C Private Member Function Virtual
    C Private Member Function Virtual Pure
    C Private Member Object
    C Private Member Object Static
    C Private Member Struct Type
    C Private Member Struct Type Template
    C Private Member Typedef Type
    C Private Member Union Type
    C Private Member Union Type Template
    C Private Member Volatile Function
    C Private Member Volatile Function Template
    C Private Member Volatile Function Virtual
    C Private Member Volatile Function Virtual Pure
    C Protected Member Abstract Class Type
    C Protected Member Abstract Class Type Template
    C Protected Member Abstract Struct Type
    C Protected Member Abstract Struct Type Template
    C Protected Member Class Type
    C Protected Member Class Type Template
    C Protected Member Const Function
    C Protected Member Const Function Template
    C Protected Member Const Function Virtual
    C Protected Member Const Function Virtual Pure
    C Protected Member Const Volatile Function
    C Protected Member Const Volatile Function Template
    C Protected Member Const Volatile Function Virtual
    C Protected Member Const Volatile Function Virtual Pure
    C Protected Member Enum Type
    C Protected Member Enumerator
    C Protected Member Function
    C Protected Member Function Template
    C Protected Member Function Explicit
    C Protected Member Function Explicit Template
    C Protected Member Function Static
    C Protected Member Function Static Template
    C Protected Member Function Virtual
    C Protected Member Function Virtual Pure
    C Protected Member Object
    C Protected Member Object Static
    C Protected Member Struct Type
    C Protected Member Struct Type Template
    C Protected Member Typedef Type
    C Protected Member Union Type
    C Protected Member Union Type Template
    C Protected Member Volatile Function
    C Protected Member Volatile Function Template
    C Protected Member Volatile Function Virtual
    C Protected Member Volatile Function Virtual Pure
    C Public Member Abstract Class Type
    C Public Member Abstract Class Type Template
    C Public Member Abstract Struct Type
    C Public Member Abstract Struct Type Template
    C Public Member Class Type
    C Public Member Class Type Template
    C Public Member Const Function
    C Public Member Const Function Template
    C Public Member Const Function Virtual
    C Public Member Const Function Virtual Pure
    C Public Member Const Volatile Function
    C Public Member Const Volatile Function Template
    C Public Member Const Volatile Function Virtual
    C Public Member Const Volatile Function Virtual Pure
    C Public Member Enum Type
    C Public Member Enumerator
    C Public Member Function
    C Public Member Function Template
    C Public Member Function Explicit
    C Public Member Function Explicit Template
    C Public Member Function Static
    C Public Member Function Static Template
    C Public Member Function Virtual
    C Public Member Function Virtual Pure
    C Public Member Object
    C Public Member Object Static
    C Public Member Struct Type
    C Public Member Struct Type Template
    C Public Member Typedef Type
    C Public Member Union Type
    C Public Member Union Type Template
    C Public Member Volatile Function
    C Public Member Volatile Function Template
    C Public Member Volatile Function Virtual
    C Public Member Volatile Function Virtual Pure
    C Struct Type
    C Struct Type Template
    C TemplateParameter Object
    C TemplateParameter Template
    C TemplateParameter Type
    C Typedef Type
    C Union Type
    C Union Type Template
    C Unknown Class Type
    C Unknown Class Type Template
    C Unknown Enum Type
    C Unknown Header File
    C Unknown Function
    C Unknown Function Template
    C Unknown Label
    C Unknown Macro
    C Unknown Member Function
    C Unknown Member Function Template
    C Unknown Member Object
    C Unknown Member Type
    C Unknown Name
    C Unknown Object
    C Unknown Struct Type
    C Unknown Struct Type Template
    C Unknown Type
    C Unknown Union Type
    C Unknown Union Type Template
    C Unnamed Class Type
    C Unnamed Enum Type
    C Unnamed Namespace
    C Unnamed Parameter
    C Unnamed Private Member Class Type
    C Unnamed Private Member Enum Type
    C Unnamed Private Member Struct Type
    C Unnamed Private Member Union Type
    C Unnamed Protected Member Class Type
    C Unnamed Protected Member Enum Type
    C Unnamed Protected Member Struct Type
    C Unnamed Protected Member Union Type
    C Unnamed Public Member Class Type
    C Unnamed Public Member Enum Type
    C Unnamed Public Member Struct Type
    C Unnamed Public Member Union Type
    C Unnamed Struct Type
    C Unnamed TemplateParameter Object
    C Unnamed TemplateParameter Template
    C Unnamed TemplateParameter Type
    C Unnamed Union Type
    C Unresolved Class Type
    C Unresolved Class Type Template
    C Unresolved Enum Type
    C Unresolved Enumerator
    C Unresolved Function
    C Unresolved Function Template
    C Unresolved Function Interrupt
    C Unresolved Function Interrupt Template
    C Unresolved Function Interrupt Static
    C Unresolved Function Interrupt Static Frominclude
    C Unresolved Function Interrupt Static Template
    C Unresolved Function Interrupt Static Template Frominclude
    C Unresolved Function Static
    C Unresolved Function Static Frominclude
    C Unresolved Function Static Template
    C Unresolved Function Static Template Frominclude
    C Unresolved Header File
    C Unresolved Macro
    C Unresolved Object Global
    C Unresolved Object Global Static
    C Unresolved Object Global Static Frominclude
    C Unresolved Parameter
    C Unresolved Private Member Class Type
    C Unresolved Private Member Class Type Template
    C Unresolved Private Member Const Function
    C Unresolved Private Member Const Function Template
    C Unresolved Private Member Const Function Virtual
    C Unresolved Private Member Const Function Virtual Pure
    C Unresolved Private Member Const Volatile Function
    C Unresolved Private Member Const Volatile Function Template
    C Unresolved Private Member Const Volatile Function Virtual
    C Unresolved Private Member Const Volatile Function Virtual Pure
    C Unresolved Private Member Enum Type
    C Unresolved Private Member Enumerator
    C Unresolved Private Member Function
    C Unresolved Private Member Function Template
    C Unresolved Private Member Function Explicit
    C Unresolved Private Member Function Explicit Template
    C Unresolved Private Member Function Static
    C Unresolved Private Member Function Static Template
    C Unresolved Private Member Function Virtual
    C Unresolved Private Member Function Virtual Pure
    C Unresolved Private Member Object Static
    C Unresolved Private Member Struct Type
    C Unresolved Private Member Struct Type Template
    C Unresolved Private Member Typedef Type
    C Unresolved Private Member Union Type
    C Unresolved Private Member Union Type Template
    C Unresolved Private Member Volatile Function
    C Unresolved Private Member Volatile Function Template
    C Unresolved Private Member Volatile Function Virtual
    C Unresolved Private Member Volatile Function Virtual Pure
    C Unresolved Protected Member Class Type
    C Unresolved Protected Member Class Type Template
    C Unresolved Protected Member Const Function
    C Unresolved Protected Member Const Function Template
    C Unresolved Protected Member Const Function Virtual
    C Unresolved Protected Member Const Function Virtual Pure
    C Unresolved Protected Member Const Volatile Function
    C Unresolved Protected Member Const Volatile Function Template
    C Unresolved Protected Member Const Volatile Function Virtual
    C Unresolved Protected Member Const Volatile Function Virtual Pure
    C Unresolved Protected Member Enum Type
    C Unresolved Protected Member Enumerator
    C Unresolved Protected Member Function
    C Unresolved Protected Member Function Template
    C Unresolved Protected Member Function Explicit
    C Unresolved Protected Member Function Explicit Template
    C Unresolved Protected Member Function Static
    C Unresolved Protected Member Function Static Template
    C Unresolved Protected Member Function Virtual
    C Unresolved Protected Member Function Virtual Pure
    C Unresolved Protected Member Object Static
    C Unresolved Protected Member Struct Type
    C Unresolved Protected Member Struct Type Template
    C Unresolved Protected Member Typedef Type
    C Unresolved Protected Member Union Type
    C Unresolved Protected Member Union Type Template
    C Unresolved Protected Member Volatile Function
    C Unresolved Protected Member Volatile Function Template
    C Unresolved Protected Member Volatile Function Virtual
    C Unresolved Protected Member Volatile Function Virtual Pure
    C Unresolved Public Member Class Type
    C Unresolved Public Member Class Type Template
    C Unresolved Public Member Const Function
    C Unresolved Public Member Const Function Template
    C Unresolved Public Member Const Function Virtual
    C Unresolved Public Member Const Function Virtual Pure
    C Unresolved Public Member Const Volatile Function
    C Unresolved Public Member Const Volatile Function Template
    C Unresolved Public Member Const Volatile Function Virtual
    C Unresolved Public Member Const Volatile Function Virtual Pure
    C Unresolved Public Member Enum Type
    C Unresolved Public Member Enumerator
    C Unresolved Public Member Function
    C Unresolved Public Member Function Template
    C Unresolved Public Member Function Explicit
    C Unresolved Public Member Function Explicit Template
    C Unresolved Public Member Function Implicit
    C Unresolved Public Member Function Static
    C Unresolved Public Member Function Static Template
    C Unresolved Public Member Function Virtual
    C Unresolved Public Member Function Virtual Pure
    C Unresolved Public Member Object Static
    C Unresolved Public Member Struct Type
    C Unresolved Public Member Struct Type Template
    C Unresolved Public Member Typedef Type
    C Unresolved Public Member Union Type
    C Unresolved Public Member Union Type Template
    C Unresolved Public Member Volatile Function
    C Unresolved Public Member Volatile Function Template
    C Unresolved Public Member Volatile Function Virtual
    C Unresolved Public Member Volatile Function Virtual Pure
    C Unresolved Struct Type
    C Unresolved Struct Type Template
    C Unresolved Typedef Type
    C Unresolved Union Type
    C Unresolved Union Type Template
    C Asm File
    C Asm Function Global
    C Asm Function Local
    C Asm Header File
    C Asm Label Global
    C Asm Label Local
    C Asm Macro
    C Asm Macro Functional
    C Asm Parameter
    C Asm Section
    C Asm Symbol Global
    C Asm Symbol Local
    C Asm Unknown
    C Asm Unresolved Function
    C Asm Unresolved Header File
    C Asm Unresolved Macro
    C ObjC Class Type
    C ObjC Protocol Type
    C ObjC Category Type
    C ObjC Property
    C ObjC Method Member Function
    C ObjC Instance Method Member Function
    C ObjC Optional Method Member Function
    C ObjC Optional Instance Method Member Function
    C ObjC Private Instance Variable Member Object
    C ObjC Protected Instance Variable Member Object
    C ObjC Public Instance Variable Member Object
    C ObjC Package Instance Variable Member Object
    C ObjC Unknown Class Type
    C ObjC Unknown Protocol Type
    C ObjC Unknown Category Type
    C ObjC Unknown Property
    C ObjC Unknown Method Member Function
    C ObjC Unknown Instance Method Member Function
    C ObjC Unknown Instance Variable Member Object
    C ObjC Unresolved Class Type
    C ObjC Unresolved Protocol Type
    C ObjC Unresolved Category Type
    C ObjC Unresolved Property
    C ObjC Unresolved Method Member Function
    C ObjC Unresolved Instance Method Member Function
    C ObjC Unresolved Optional Method Member Function
    C ObjC Unresolved Optional Instance Method Member Function
    C ObjC Unresolved Private Instance Variable Member Object
    C ObjC Unresolved Protected Instance Variable Member Object
    C ObjC Unresolved Public Instance Variable Member Object
    C ObjC Unresolved Package Instance Variable Member Object
    C Lambda Function
    C Private Member Type Alias
    C Private Member Type Alias Template
    C Protected Member Type Alias
    C Protected Member Type Alias Template
    C Public Member Type Alias
    C Public Member Type Alias Template
    C TemplateParameter Object Pack
    C TemplateParameter Template Pack
    C TemplateParameter Type Pack
    C Type Alias
    C Type Alias Template
    C Unnamed TemplateParameter Object Pack
    C Unnamed TemplateParameter Template Pack
    C Unnamed TemplateParameter Type Pack
    C Unresolved Private Member Type Alias
    C Unresolved Private Member Type Alias Template
    C Unresolved Protected Member Type Alias
    C Unresolved Protected Member Type Alias Template
    C Unresolved Public Member Type Alias
    C Unresolved Public Member Type Alias Template
    C Unresolved Type Alias
    C Unresolved Type Alias Template
    C Block Function
	
    Cobol File
    Cobol Copybook File
    Cobol Unknown Copybook File
    Cobol Unresolved Copybook File
    Cobol Sequential DataFile Variable
    Cobol Sequential Indexed DataFile Variable
    Cobol Random Indexed DataFile Variable
    Cobol Dynamic Indexed DataFile Variable
    Cobol Sequential Relative DataFile Variable
    Cobol Random Relative DataFile Variable
    Cobol Dynamic Relative DataFile Variable
    Cobol Unknown DataFile Variable
    Cobol Unresolved DataFile Variable
    Cobol Index
    Cobol Unknown Index
    Cobol Unresolved Index
    Cobol Paragraph
    Cobol Unknown Paragraph
    Cobol Unresolved Paragraph
    Cobol Program
    Cobol Unknown Program
    Cobol Unresolved Program
    Cobol Screen
    Cobol Unknown Screen
    Cobol Unresolved Screen
    Cobol Section
    Cobol Variable
    Cobol Record Variable
    Cobol Unknown Variable
    Cobol Unresolved Variable
	
    c# csharp Namespace Alias
    c# csharp Global Namespace Alias
    c# csharp Type Alias
    c# csharp Global Type Alias
    c# csharp Const Local
    c# csharp Constructor Member Method Static
    c# csharp Enumerator
    c# csharp Extern Alias Namespace
    c# csharp File
    c# csharp Dll File
    c# csharp Finalizer Member Method
    c# csharp Namespace
    c# csharp Parameter In
    c# csharp Parameter Out
    c# csharp Parameter Params
    c# csharp Parameter Ref
    c# csharp Parameter Value
    c# csharp Internal Const Member Field
    c# csharp Internal Constructor Member Method
    c# csharp Internal Member Event
    c# csharp Internal Member Event Static
    c# csharp Internal Member Event Virtual
    c# csharp Internal Member Event Virtual Abstract
    c# csharp Internal Member Event Virtual Sealed
    c# csharp Internal Member Field
    c# csharp Internal Member Field Static
    c# csharp Internal Member Indexer
    c# csharp Internal Member Indexer Virtual
    c# csharp Internal Member Indexer Virtual Abstract
    c# csharp Internal Member Indexer Virtual Sealed
    c# csharp Internal Member Method
    c# csharp Internal Member Method Stub
    c# csharp Internal Member Method Static
    c# csharp Internal Member Method Virtual
    c# csharp Internal Member Method Virtual Abstract
    c# csharp Internal Member Method Virtual Sealed
    c# csharp Internal Member Property
    c# csharp Internal Member Property Extern
    c# csharp Internal Member Property Static
    c# csharp Internal Member Property Static Exter
    c# csharp Internal Member Property Virtual
    c# csharp Internal Member Property Virtual Extern
    c# csharp Internal Member Property Virtual Abstract
    c# csharp Internal Member Property Virtual Sealed
    c# csharp Internal Member Property Virtual Sealed Extern
    c# csharp Internal Type Class
    c# csharp Internal Type Class Abstract
    c# csharp Internal Type Class Static
    c# csharp Internal Type Class Sealed
    c# csharp Internal Type Delegate
    c# csharp Internal Type Enum
    c# csharp Internal Type Interface
    c# csharp Internal Type Record
    c# csharp Internal Type Record Abstract
    c# csharp Internal Type Record Sealed
    c# csharp Internal Type Struct
    c# csharp Lambda Method
    c# csharp Protected Const Member Field
    c# csharp Protected Constructor Member Method
    c# csharp Protected Member Event
    c# csharp Protected Member Event Static
    c# csharp Protected Member Event Virtual
    c# csharp Protected Member Event Virtual Abstract
    c# csharp Protected Member Event Virtual Sealed
    c# csharp Protected Member Field
    c# csharp Protected Member Field Static
    c# csharp Protected Member Indexer
    c# csharp Protected Member Indexer Virtual
    c# csharp Protected Member Indexer Virtual Abstract
    c# csharp Protected Member Indexer Virtual Sealed
    c# csharp Protected Member Method
    c# csharp Protected Member Method Stub
    c# csharp Protected Member Method Static
    c# csharp Protected Member Method Virtual
    c# csharp Protected Member Method Virtual Abstract
    c# csharp Protected Member Method Virtual Sealed
    c# csharp Protected Member Property
    c# csharp Protected Member Property Extern
    c# csharp Protected Member Property Static
    c# csharp Protected Member Property Static Extern
    c# csharp Protected Member Property Virtual
    c# csharp Protected Member Property Virtual Extern
    c# csharp Protected Member Property Virtual Abstract
    c# csharp Protected Member Property Virtual Sealed
    c# csharp Protected Member Property Virtual Sealed Extern
    c# csharp Protected Type Class
    c# csharp Protected Type Class Abstract
    c# csharp Protected Type Class Static
    c# csharp Protected Type Class Sealed
    c# csharp Protected Type Delegate
    c# csharp Protected Type Enum
    c# csharp Protected Type Record
    c# csharp Protected Type Record Abstract
    c# csharp Protected Type Record Sealed
    c# csharp Protected Type Interface
    c# csharp Protected Type Struct
    c# csharp Protected Internal Const Member Field
    c# csharp Protected Internal Constructor Member Method
    c# csharp Protected Internal Member Event
    c# csharp Protected Internal Member Event Static
    c# csharp Protected Internal Member Event Virtual
    c# csharp Protected Internal Member Event Virtual Abstract
    c# csharp Protected Internal Member Event Virtual Sealed
    c# csharp Protected Internal Member Field
    c# csharp Protected Internal Member Field Static
    c# csharp Protected Internal Member Indexer
    c# csharp Protected Internal Member Indexer Virtual
    c# csharp Protected Internal Member Indexer Virtual Abstract
    c# csharp Protected Internal Member Indexer Virtual Sealed
    c# csharp Protected Internal Member Method
    c# csharp Protected Internal Member Method Stub
    c# csharp Protected Internal Member Method Static
    c# csharp Protected Internal Member Method Virtual
    c# csharp Protected Internal Member Method Virtual Abstract
    c# csharp Protected Internal Member Method Virtual Sealed
    c# csharp Protected Internal Member Property
    c# csharp Protected Internal Member Property Extern
    c# csharp Protected Internal Member Property Static
    c# csharp Protected Internal Member Property Static Extern
    c# csharp Protected Internal Member Property Virtual
    c# csharp Protected Internal Member Property Virtual Extern
    c# csharp Protected Internal Member Property Virtual Abstract
    c# csharp Protected Internal Member Property Virtual Sealed
    c# csharp Protected Internal Member Property Virtual Sealed Extern
    c# csharp Protected Internal Type Class
    c# csharp Protected Internal Type Class Abstract
    c# csharp Protected Internal Type Class Static
    c# csharp Protected Internal Type Class Sealed
    c# csharp Protected Internal Type Delegate
    c# csharp Protected Internal Type Enum
    c# csharp Protected Internal Type Interface
    c# csharp Protected Internal Type Record
    c# csharp Protected Internal Type Record Abstract
    c# csharp Protected Internal Type Record Sealed
    c# csharp Protected Internal Type Struct
    c# csharp Private Const Member Field
    c# csharp Private Constructor Member Method
    c# csharp Private Member Event
    c# csharp Private Member Event Static
    c# csharp Private Member Field
    c# csharp Private Member Field Static
    c# csharp Private Member Indexer
    c# csharp Private Member Method
    c# csharp Private Member Method Stub
    c# csharp Private Member Method Static
    c# csharp Private Member Property
    c# csharp Private Member Property Extern
    c# csharp Private Member Property Static
    c# csharp Private Member Property Static Extern
    c# csharp Private Type Class
    c# csharp Private Type Class Abstract
    c# csharp Private Type Class Static
    c# csharp Private Type Class Sealed
    c# csharp Private Type Delegate
    c# csharp Private Type Enum
    c# csharp Private Type Interface
    c# csharp Private Type Record
    c# csharp Private Type Record Abstract
    c# csharp Private Type Record Sealed
    c# csharp Private Type Struct
    c# csharp Private Protected Const Member Field
    c# csharp Private Protected Constructor Member Method
    c# csharp Private Protected Member Event
    c# csharp Private Protected Member Event Static
    c# csharp Private Protected Member Event Virtual
    c# csharp Private Protected Member Event Virtual Abstract
    c# csharp Private Protected Member Event Virtual Sealed
    c# csharp Private Protected Member Field
    c# csharp Private Protected Member Field Static
    c# csharp Private Protected Member Indexer
    c# csharp Private Protected Member Indexer Virtual
    c# csharp Private Protected Member Indexer Virtual Abstract
    c# csharp Private Protected Member Indexer Virtual Sealed
    c# csharp Private Protected Member Method
    c# csharp Private Protected Member Method Stub
    c# csharp Private Protected Member Method Static
    c# csharp Private Protected Member Method Virtual
    c# csharp Private Protected Member Method Virtual Abstract
    c# csharp Private Protected Member Method Virtual Sealed
    c# csharp Private Protected Member Property
    c# csharp Private Protected Member Property Extern
    c# csharp Private Protected Member Property Static
    c# csharp Private Protected Member Property Static Extern
    c# csharp Private Protected Member Property Virtual
    c# csharp Private Protected Member Property Virtual Extern
    c# csharp Private Protected Member Property Virtual Abstract
    c# csharp Private Protected Member Property Virtual Sealed
    c# csharp Private Protected Member Property Virtual Sealed Extern
    c# csharp Private Protected Type Class
    c# csharp Private Protected Type Class Abstract
    c# csharp Private Protected Type Class Static
    c# csharp Private Protected Type Class Sealed
    c# csharp Private Protected Type Delegate
    c# csharp Private Protected Type Enum
    c# csharp Private Protected Type Interface
    c# csharp Private Protected Type Record
    c# csharp Private Protected Type Record Abstract
    c# csharp Private Protected Type Record Sealed
    c# csharp Private Protected Type Struct
    c# csharp Public Const Member Field
    c# csharp Public Constructor Member Method
    c# csharp Public Member Event
    c# csharp Public Member Event Static
    c# csharp Public Member Event Virtual
    c# csharp Public Member Event Virtual Abstract
    c# csharp Public Member Event Virtual Sealed
    c# csharp Public Member Field
    c# csharp Public Member Field Static
    c# csharp Public Member Indexer
    c# csharp Public Member Indexer Virtual
    c# csharp Public Member Indexer Virtual Abstract
    c# csharp Public Member Indexer Virtual Sealed
    c# csharp Public Member Method
    c# csharp Public Member Method Stub
    c# csharp Public Member Method Static
    c# csharp Public Member Method Virtual
    c# csharp Public Member Method Virtual Abstract
    c# csharp Public Member Method Virtual Sealed
    c# csharp Public Member Property
    c# csharp Public Member Property Extern
    c# csharp Public Member Property Static
    c# csharp Public Member Property Static Extern
    c# csharp Public Member Property Virtual
    c# csharp Public Member Property Virtual Extern
    c# csharp Public Member Property Virtual Abstract
    c# csharp Public Member Property Virtual Sealed
    c# csharp Public Member Property Virtual Sealed Extern
    c# csharp Public Type Class
    c# csharp Public Type Class Abstract
    c# csharp Public Type Class Static
    c# csharp Public Type Class Sealed
    c# csharp Public Type Delegate
    c# csharp Public Type Enum
    c# csharp Public Type Interface
    c# csharp Public Type Record
    c# csharp Public Type Record Abstract
    c# csharp Public Type Record Sealed
    c# csharp Public Type Struct
    c# csharp Type Parameter
    c# csharp Type Tuple
    c# csharp Unknown Type Class
    c# csharp Unknown Member Method
    c# csharp Unknown Variable
    c# csharp Unresolved Constructor Member Method Static
    c# csharp Unresolved Dynamic Member
    c# csharp Unresolved Member Field
    c# csharp Unresolved Enumerator
    c# csharp Unresolved Finalizer Member Method
    c# csharp Unresolved Internal Constructor Member Method
    c# csharp Unresolved Internal Member Event
    c# csharp Unresolved Internal Member Event Static
    c# csharp Unresolved Internal Member Event Virtual
    c# csharp Unresolved Internal Member Event Virtual Sealed
    c# csharp Unresolved Internal Member Indexer
    c# csharp Unresolved Internal Member Indexer Virtual
    c# csharp Unresolved Internal Member Indexer Virtual Sealed
    c# csharp Unresolved Internal Member Method
    c# csharp Unresolved Internal Extern Member Method
    c# csharp Unresolved Internal Member Method Static
    c# csharp Unresolved Internal Extern Member Method Static
    c# csharp Unresolved Internal Member Method Virtual
    c# csharp Unresolved Internal Extern Member Method Virtual
    c# csharp Unresolved Internal Member Method Virtual Sealed
    c# csharp Unresolved Internal Extern Member Method Virtual Sealed
    c# csharp Unresolved Internal Member Property
    c# csharp Unresolved Internal Member Property Static
    c# csharp Unresolved Internal Member Property Virtual
    c# csharp Unresolved Internal Member Property Virtual Sealed
    c# csharp Unresolved Namespace
    c# csharp Unresolved Protected Constructor Member Method
    c# csharp Unresolved Protected Member Event
    c# csharp Unresolved Protected Member Event Static
    c# csharp Unresolved Protected Member Event Virtual
    c# csharp Unresolved Protected Member Event Virtual Sealed
    c# csharp Unresolved Protected Member Indexer
    c# csharp Unresolved Protected Member Indexer Virtual
    c# csharp Unresolved Protected Member Indexer Virtual Sealed
    c# csharp Unresolved Protected Member Method
    c# csharp Unresolved Protected Extern Member Method
    c# csharp Unresolved Protected Member Method Static
    c# csharp Unresolved Protected Extern Member Method Static
    c# csharp Unresolved Protected Member Method Virtual
    c# csharp Unresolved Protected Extern Member Method Virtual
    c# csharp Unresolved Protected Member Method Virtual Sealed
    c# csharp Unresolved Protected Extern Member Method Virtual Sealed
    c# csharp Unresolved Protected Member Property
    c# csharp Unresolved Protected Member Property Static
    c# csharp Unresolved Protected Member Property Virtual
    c# csharp Unresolved Protected Member Property Virtual Sealed
    c# csharp Unresolved Protected Internal Constructor Member Method
    c# csharp Unresolved Protected Internal Member Event
    c# csharp Unresolved Protected Internal Member Event Static
    c# csharp Unresolved Protected Internal Member Event Virtual
    c# csharp Unresolved Protected Internal Member Event Virtual Sealed
    c# csharp Unresolved Protected Internal Member Indexer
    c# csharp Unresolved Protected Internal Member Indexer Virtual
    c# csharp Unresolved Protected Internal Member Indexer Virtual Sealed
    c# csharp Unresolved Protected Internal Member Method
    c# csharp Unresolved Protected Internal Extern Member Method
    c# csharp Unresolved Protected Internal Member Method Static
    c# csharp Unresolved Protected Internal Extern Member Method Static
    c# csharp Unresolved Protected Internal Member Method Virtual
    c# csharp Unresolved Protected Internal Extern Member Method Virtual
    c# csharp Unresolved Protected Internal Member Method Virtual Sealed
    c# csharp Unresolved Protected Internal Extern Member Method Virtual Sealed
    c# csharp Unresolved Protected Internal Member Property
    c# csharp Unresolved Protected Internal Member Property Static
    c# csharp Unresolved Protected Internal Member Property Virtual
    c# csharp Unresolved Protected Internal Member Property Virtual Sealed
    c# csharp Unresolved Private Protected Constructor Member Method
    c# csharp Unresolved Private Protected Member Event
    c# csharp Unresolved Private Protected Member Event Static
    c# csharp Unresolved Private Protected Member Event Virtual
    c# csharp Unresolved Private Protected Member Event Virtual Sealed
    c# csharp Unresolved Private Protected Member Indexer
    c# csharp Unresolved Private Protected Member Indexer Virtual
    c# csharp Unresolved Private Protected Member Indexer Virtual Sealed
    c# csharp Unresolved Private Protected Member Method
    c# csharp Unresolved Private Protected Extern Member Method
    c# csharp Unresolved Private Protected Member Method Static
    c# csharp Unresolved Private Protected Extern Member Method Static
    c# csharp Unresolved Private Protected Member Method Virtual
    c# csharp Unresolved Private Protected Extern Member Method Virtual
    c# csharp Unresolved Private Protected Member Method Virtual Sealed
    c# csharp Unresolved Private Protected Extern Member Method Virtual Sealed
    c# csharp Unresolved Private Protected Member Property
    c# csharp Unresolved Private Protected Member Property Static
    c# csharp Unresolved Private Protected Member Property Virtual
    c# csharp Unresolved Private Protected Member Property Virtual Sealed
    c# csharp Unresolved Private Constructor Member Method
    c# csharp Unresolved Private Member Event
    c# csharp Unresolved Private Member Event Static
    c# csharp Unresolved Private Member Indexer
    c# csharp Unresolved Private Member Method
    c# csharp Unresolved Private Extern Member Method
    c# csharp Unresolved Private Member Method Static
    c# csharp Unresolved Private Extern Member Method Static
    c# csharp Unresolved Private Member Property
    c# csharp Unresolved Private Member Property Static
    c# csharp Unresolved Public Constructor Member Method
    c# csharp Unresolved Public Member Event
    c# csharp Unresolved Public Member Event Static
    c# csharp Unresolved Public Member Event Virtual
    c# csharp Unresolved Public Member Event Virtual Sealed
    c# csharp Unresolved Public Member Indexer
    c# csharp Unresolved Public Member Indexer Virtual
    c# csharp Unresolved Public Member Indexer Virtual Sealed
    c# csharp Unresolved Public Member Method
    c# csharp Unresolved Public Extern Member Method
    c# csharp Unresolved Public Member Method Static
    c# csharp Unresolved Public Extern Member Method Static
    c# csharp Unresolved Public Member Method Virtual
    c# csharp Unresolved Public Extern Member Method Virtual
    c# csharp Unresolved Public Member Method Virtual Sealed
    c# csharp Unresolved Public Extern Member Method Virtual Sealed
    c# csharp Unresolved Public Member Property
    c# csharp Unresolved Public Member Property Static
    c# csharp Unresolved Public Member Property Virtual
    c# csharp Unresolved Public Member Property Virtual Sealed
    c# csharp Unresolved Type
    c# csharp Variable Local
	
    Fortran Block Data
    Fortran Block Variable
    Fortran Common
    Fortran Datapool
    Fortran Derived Type
    Fortran Abstract Derived Type
    Fortran Parameter
    Fortran Dummy Argument
    Fortran Coarray Dummy Argument
    Fortran Entry
    Fortran Enumerator
    Fortran File
    Fortran Function
    Fortran Function Virtual
    Fortran Include File
    Fortran Interface
    Fortran Intrinsic Function
    Fortran Intrinsic Module
    Fortran Intrinsic Subroutine
    Fortran Intrinsic Type
    Fortran Intrinsic Variable
    Fortran IoUnit Variable
    Fortran Pointer
    Fortran Procedure
    Fortran Procedure Pointer
    Fortran Procedure Virtual
    Fortran Main Subroutine
    Fortran Module
    Fortran Namelist Variable
    Fortran Submodule
    Fortran Subroutine
    Fortran Subroutine Virtual
    Fortran Unknown Include File
    Fortran Unknown Module
    Fortran Unknown Type
    Fortran Unknown Variable
    Fortran Unresolved
    Fortran Unresolved External Common
    Fortran Unresolved External Function
    Fortran Unresolved External Subroutine
    Fortran Unresolved External Variable
    Fortran Unresolved Function
    Fortran Unresolved Include File
    Fortran Unresolved Subroutine
    Fortran Variable
    Fortran Coarray Variable
    Fortran Variable Component
    Fortran Local Variable
    Fortran Local Coarray Variable
	
    Java Abstract Class Type Default Member
    Java Abstract Class Type Private Member
    Java Abstract Class Type Protected Member
    Java Abstract Class Type Public Member
    Java Abstract Generic Class Type Default Member
    Java Abstract Generic Class Type Private Member
    Java Abstract Generic Class Type Protected Member
    Java Abstract Generic Class Type Public Member
    Java Abstract Enum Type Default Member
    Java Abstract Enum Type Private Member
    Java Abstract Enum Type Protected Member
    Java Abstract Enum Type Public Member
    Java Abstract Method Default Member
    Java Abstract Method Protected Member
    Java Abstract Method Public Member
    Java Abstract Generic Method Default Member
    Java Abstract Generic Method Protected Member
    Java Abstract Generic Method Public Member
    Java Annotation Interface Type Default
    Java Annotation Interface Type Private
    Java Annotation Interface Type Protected
    Java Annotation Interface Type Public
    Java Catch Parameter
    Java Class Type Anonymous Member
    Java Class Type Default Member
    Java Class Type Private Member
    Java Class Type Protected Member
    Java Class Type Public Member
    Java Generic Class Type Default Member
    Java Generic Class Type Private Member
    Java Generic Class Type Protected Member
    Java Generic Class Type Public Member
    Java Method Constructor Member Default
    Java Method Constructor Member Private
    Java Method Constructor Member Protected
    Java Method Constructor Member Public
    Java Variable EnumConstant Public Member
    Java Enum Class Type Default Member
    Java Enum Class Type Private Member
    Java Enum Class Type Protected Member
    Java Enum Class Type Public Member
    Java File
    Java File Jar
    Java Final Class Type Default Member
    Java Final Class Type Private Member
    Java Final Class Type Protected Member
    Java Final Class Type Public Member
    Java Final Generic Class Type Default Member
    Java Final Generic Class Type Private Member
    Java Final Generic Class Type Protected Member
    Java Final Generic Class Type Public Member
    Java Final Method Default Member
    Java Final Method Private Member
    Java Final Method Protected Member
    Java Final Method Public Member
    Java Generic Final Method Default Member
    Java Generic Final Method Private Member
    Java Final Generic Method Protected Member
    Java Final Generic Method Public Member
    Java Final Variable Default Member
    Java Final Variable Local
    Java Final Variable Private Member
    Java Final Variable Protected Member
    Java Final Variable Public Member
    Java Implicit Final Variable Public Member
    Java Interface Type Default
    Java Interface Type Private
    Java Interface Type Protected
    Java Interface Type Public
    Java Generic Interface Type Default
    Java Generic Interface Type Private
    Java Generic Interface Type Protected
    Java Generic Interface Type Public
    Java Method Default Member
    Java Method Lambda
    Java Method Private Member
    Java Method Protected Member
    Java Method Public Member
    Java Implicit Method Public Member
    Java Generic Method Default Member
    Java Generic Method Private Member
    Java Generic Method Protected Member
    Java Generic Method Public Member
    Java Module
    Java Package
    Java Package Unnamed
    Java Parameter
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
    Java Static Final Method Default Member
    Java Static Final Method Private Member
    Java Static Final Method Protected Member
    Java Static Final Method Public Member
    Java Static Final Generic Method Default Member
    Java Static Final Generic Method Private Member
    Java Static Final Generic Method Protected Member
    Java Static Final Generic Method Public Member
    Java Static Final Variable Default Member
    Java Static Final Variable Private Member
    Java Static Final Variable Protected Member
    Java Static Final Variable Public Member
    Java Static Method Default Member
    Java Static Method Private Member
    Java Static Method Protected Member
    Java Static Method Public Member
    Java Static Generic Method Default Member
    Java Static Generic Method Private Member
    Java Static Generic Method Protected Member
    Java Static Generic Method Public Member
    Java Static Method Public Main Member
    Java Static Variable Default Member
    Java Static Variable Private Member
    Java Static Variable Protected Member
    Java Static Variable Public Member
    Java Class Type TypeVariable
    Java Unknown Class Type Member
    Java Unknown Method Member
    Java Unknown Module
    Java Unknown Package
    Java Unknown Variable Member
    Java Unresolved Method
    Java Unresolved External Final Method Default Member
    Java Unresolved External Final Method Private Member
    Java Unresolved External Final Method Protected Member
    Java Unresolved External Final Method Public Member
    Java Unresolved External Method Default Member
    Java Unresolved External Method Private Member
    Java Unresolved External Method Protected Member
    Java Unresolved External Method Public Member
    Java Unresolved External Static Final Method Default Member
    Java Unresolved External Static Final Method Private Member
    Java Unresolved Extermal Static Final Method Protected Member
    Java Unresolved External Static Final Method Public Member
    Java Unresolved External Static Method Default Member
    Java Unresolved External Static Method Private Member
    Java Unresolved External Static Method Protected Member
    Java Unresolved External Static Method Public Member
    Java Unresolved External Static Method Public Main Member
    Java Unresolved Module
    Java Unresolved Package
    Java Unresolved Type
    Java Unresolved Variable
    Java Variable Default Member
    Java Variable Local
    Java Variable Private Member
    Java Variable Protected Member
    Java Variable Public Member
	
    Jovial Close Subroutine
    Jovial Copy File
    Jovial CompoolFile
    Jovial External Constant Variable Item
    Jovial External Constant Variable Table
    Jovial External Constant Component Variable Item
    Jovial External Constant Component Variable Table
    Jovial External Variable Array
    Jovial External Variable Block
    Jovial External Variable FileVar
    Jovial External Variable Item
    Jovial External Variable Table
    Jovial External Component Variable Block
    Jovial External Component Variable Item
    Jovial External Component Variable String
    Jovial External Component Variable Table
    Jovial Local Constant Variable Item
    Jovial Local Constant Variable Table
    Jovial Local Constant Component Variable Item
    Jovial Local Constant Component Variable Table
    Jovial Local Variable Array
    Jovial Local Variable Block
    Jovial Local Variable FileVar
    Jovial Local Variable Item
    Jovial Local Variable Table
    Jovial Local Component Variable Block
    Jovial Local Component Variable Item
    Jovial Local Component Variable String
    Jovial Local Component Variable Table
    Jovial File
    Jovial External Macro
    Jovial Local Macro
    Jovial Compool Module
    Jovial Parameter In
    Jovial Parameter Out
    Jovial Program Procedure Subroutine
    Jovial Status Type
    Jovial Statusname
    Jovial External Function Subroutine
    Jovial External Procedure Subroutine
    Jovial Local Function Subroutine
    Jovial Local Procedure Subroutine
    Jovial Switch
    Jovial External Type Block
    Jovial External Type Item
    Jovial External Type Table
    Jovial External Component Type Block
    Jovial External Component Type Item
    Jovial External Component Type Table
    Jovial Local Type Block
    Jovial Local Type Item
    Jovial Local Type Table
    Jovial Local Component Type Block
    Jovial Local Component Type Item
    Jovial Local Component Type Table
    Jovial Unknown
    Jovial Unknown Copy File
    Jovial Unresolved Compool
    Jovial Unresolved Copy File
    Jovial Unresolved Macro
    Jovial Unresolved Subroutine
    Jovial Unresolved Type
    Jovial Unresolved Variable
	
    Unparsed File
	
    Pascal Method Function Private ClassMethod
    Pascal Method Function Private Virtual ClassMethod
    Pascal Method Function Private Virtual Abstract ClassMethod
    Pascal Method Function Protected ClassMethod
    Pascal Method Function Protected Virtual ClassMethod
    Pascal Method Function Protected Virtual Abstract ClassMethod
    Pascal Method Function Public ClassMethod
    Pascal Method Function Public Virtual ClassMethod
    Pascal Method Function Public Virtual Abstract ClassMethod
    Pascal Method Function Published ClassMethod
    Pascal Method Function Published Virtual ClassMethod
    Pascal Method Function Published Virtual Abstract ClassMethod
    Pascal Method Function Strict Private ClassMethod
    Pascal Method Function Strict Private Virtual ClassMethod
    Pascal Method Function Strict Private Virtual Abstract ClassMethod
    Pascal Method Function Strict Protected ClassMethod
    Pascal Method Function Strict Protected Virtual ClassMethod
    Pascal Method Function Strict Protected Virtual Abstract ClassMethod
    Pascal Method Procedure Private ClassMethod
    Pascal Method Procedure Private Virtual ClassMethod
    Pascal Method Procedure Private Virtual Abstract ClassMethod
    Pascal Method Procedure Protected ClassMethod
    Pascal Method Procedure Protected Virtual ClassMethod
    Pascal Method Procedure Protected Virtual Abstract ClassMethod
    Pascal Method Procedure Public ClassMethod
    Pascal Method Procedure Public Virtual ClassMethod
    Pascal Method Procedure Public Virtual Abstract ClassMethod
    Pascal Method Procedure Published ClassMethod
    Pascal Method Procedure Published Virtual ClassMethod
    Pascal Method Procedure Published Virtual Abstract ClassMethod
    Pascal Method Procedure Strict Private ClassMethod
    Pascal Method Procedure Strict Private Virtual ClassMethod
    Pascal Method Procedure Strict Private Virtual Abstract ClassMethod
    Pascal Method Procedure Strict Protected ClassMethod
    Pascal Method Procedure Strict Protected Virtual ClassMethod
    Pascal Method Procedure Strict Protected Virtual Abstract ClassMethod
    Pascal Const Local
    Pascal Const Resourcestring Global
    Pascal Const Resourcestring Local
    Pascal Const Global
    Pascal CompUnit Module
    Pascal CompUnit Program
    Pascal CompUnit Unit
    Pascal Enumerator Local
    Pascal Enumerator Global
    Pascal Environment
    Pascal Field Private
    Pascal Field Protected
    Pascal Field Public
    Pascal Field Published
    Pascal Field Strict Private
    Pascal Field Strict Protected
    Pascal Field Private Classvar
    Pascal Field Protected Classvar
    Pascal Field Public Classvar
    Pascal Field Published Classvar
    Pascal Field Strict Private Classvar
    Pascal Field Strict Protected Classvar
    Pascal Field Discrim Local
    Pascal Field Discrim Global
    Pascal File
    Pascal File Dfm
    Pascal File Include
    Pascal Routine Function Local
    Pascal Routine Function Local Asynchronous
    Pascal Routine Function Global
    Pascal Routine Function Global Asynchronous
    Pascal Routine Function Global External
    Pascal Routine Function Global Asynchronous External
    Pascal Method Procedure Constructor Private
    Pascal Method Procedure Constructor Private Virtual
    Pascal Method Procedure Constructor Private Virtual Abstract
    Pascal Method Procedure Constructor Protected
    Pascal Method Procedure Constructor Protected Virtual
    Pascal Method Procedure Constructor Protected Virtual Abstract
    Pascal Method Procedure Constructor Public
    Pascal Method Procedure Constructor Public Virtual
    Pascal Method Procedure Constructor Public Virtual Abstract
    Pascal Method Procedure Constructor Published
    Pascal Method Procedure Constructor Published Virtual
    Pascal Method Procedure Constructor Published Virtual Abstract
    Pascal Method Procedure Constructor Strict Private
    Pascal Method Procedure Constructor Strict Private Virtual
    Pascal Method Procedure Constructor Strict Private Virtual Abstract
    Pascal Method Procedure Constructor Strict Protected
    Pascal Method Procedure Constructor Strict Protected Virtual
    Pascal Method Procedure Constructor Strict Protected Virtual Abstract
    Pascal Method Procedure Destructor Private
    Pascal Method Procedure Destructor Private Virtual
    Pascal Method Procedure Destructor Private Virtual Abstract
    Pascal Method Procedure Destructor Protected
    Pascal Method Procedure Destructor Protected Virtual
    Pascal Method Procedure Destructor Protected Virtual Abstract
    Pascal Method Procedure Destructor Public
    Pascal Method Procedure Destructor Public Virtual
    Pascal Method Procedure Destructor Public Virtual Abstract
    Pascal Method Procedure Destructor Published
    Pascal Method Procedure Destructor Published Virtual
    Pascal Method Procedure Destructor Published Virtual Abstract
    Pascal Method Procedure Destructor Strict Private
    Pascal Method Procedure Destructor Strict Private Virtual
    Pascal Method Procedure Destructor Strict Private Virtual Abstract
    Pascal Method Procedure Destructor Strict Protected
    Pascal Method Procedure Destructor Strict Protected Virtual
    Pascal Method Procedure Destructor Strict Protected Virtual Abstract
    Pascal Method Function Private
    Pascal Method Function Private Virtual
    Pascal Method Function Private Virtual Abstract
    Pascal Method Function Protected
    Pascal Method Function Protected Virtual
    Pascal Method Function Protected Virtual Abstract
    Pascal Method Function Public
    Pascal Method Function Public Virtual
    Pascal Method Function Public Virtual Abstract
    Pascal Method Function Published
    Pascal Method Function Published Virtual
    Pascal Method Function Published Virtual Abstract
    Pascal Method Function Strict Private
    Pascal Method Function Strict Private Virtual
    Pascal Method Function Strict Private Virtual Abstract
    Pascal Method Function Strict Protected
    Pascal Method Function Strict Protected Virtual
    Pascal Method Function Strict Protected Virtual Abstract
    Pascal Method Procedure Private
    Pascal Method Procedure Private Virtual
    Pascal Method Procedure Private Virtual Abstract
    Pascal Method Procedure Private Virtual Message
    Pascal Method Procedure Protected
    Pascal Method Procedure Protected Virtual
    Pascal Method Procedure Protected Virtual Abstract
    Pascal Method Procedure Protected Virtual Message
    Pascal Method Procedure Public
    Pascal Method Procedure Public Virtual
    Pascal Method Procedure Public Virtual Abstract
    Pascal Method Procedure Public Virtual Message
    Pascal Method Procedure Published
    Pascal Method Procedure Published Virtual
    Pascal Method Procedure Published Virtual Abstract
    Pascal Method Procedure Published Virtual Message
    Pascal Method Procedure Strict Private
    Pascal Method Procedure Strict Private Virtual
    Pascal Method Procedure Strict Private Virtual Abstract
    Pascal Method Procedure Strict Private Virtual Message
    Pascal Method Procedure Strict Protected
    Pascal Method Procedure Strict Protected Virtual
    Pascal Method Procedure Strict Protected Virtual Abstract
    Pascal Method Procedure Strict Protected Virtual Message
    Pascal Namespace
    Pascal Parameter Const Local
    Pascal Parameter Const Global
    Pascal Parameter Function Local
    Pascal Parameter Function Global
    Pascal Parameter Out Local
    Pascal Parameter Out Global
    Pascal Parameter Procedure Local
    Pascal Parameter Procedure Global
    Pascal Parameter Value Local
    Pascal Parameter Value Global
    Pascal Parameter Var Local
    Pascal Parameter Var Global
    Pascal Predeclared Routine
    Pascal Predeclared Type
    Pascal Predeclared Variable
    Pascal Routine Procedure Local
    Pascal Routine Procedure Local Asynchronous
    Pascal Routine Procedure Local Initializer
    Pascal Routine Procedure Local Finalizer
    Pascal Routine Procedure Global
    Pascal Routine Procedure Global Asynchronous
    Pascal Routine Procedure Global External
    Pascal Routine Procedure Global Asynchronous External
    Pascal Property Private
    Pascal Property Protected
    Pascal Property Public
    Pascal Property Published
    Pascal Property Strict Private
    Pascal Property Strict Protected
    Pascal Type Unnamed Local
    Pascal Type Unnamed Local Enum
    Pascal Type Unnamed Local Record
    Pascal Type Local
    Pascal Type Local Enum
    Pascal Type Local Record
    Pascal Type Global
    Pascal Type Global Enum
    Pascal Type Global Record
    Pascal Type Nested Private
    Pascal Type Nested Private Enum
    Pascal Type Nested Private Record
    Pascal Type Nested Protected
    Pascal Type Nested Protected Enum
    Pascal Type Nested Protected Record
    Pascal Type Nested Public
    Pascal Type Nested Public Enum
    Pascal Type Nested Public Record
    Pascal Type Nested Published
    Pascal Type Nested Published Enum
    Pascal Type Nested Published Record
    Pascal Type Nested Strict Private
    Pascal Type Nested Strict Private Enum
    Pascal Type Nested Strict Private Record
    Pascal Type Nested Strict Protected
    Pascal Type Nested Strict Protected Enum
    Pascal Type Nested Strict Protected Record
    Pascal Type Parameter
    Pascal Type Class Local
    Pascal Type Class Local Abstract
    Pascal Type Class Local Sealed
    Pascal Type Class Global
    Pascal Type Class Global Abstract
    Pascal Type Class Global Sealed
    Pascal Type Class Nested Private
    Pascal Type Class Nested Private Abstract
    Pascal Type Class Nested Private Sealed
    Pascal Type Class Nested Protected
    Pascal Type Class Nested Protected Abstract
    Pascal Type Class Nested Protected Sealed
    Pascal Type Class Nested Public
    Pascal Type Class Nested Public Abstract
    Pascal Type Class Nested Public Sealed
    Pascal Type Class Nested Published
    Pascal Type Class Nested Published Abstract
    Pascal Type Class Nested Published Sealed
    Pascal Type Class Nested Strict Private
    Pascal Type Class Nested Strict Private Abstract
    Pascal Type Class Nested Strict Private Sealed
    Pascal Type Class Nested Strict Protected
    Pascal Type Class Nested Strict Protected Abstract
    Pascal Type Class Nested Strict Protected Sealed
    Pascal Type ClassReference Local
    Pascal Type ClassReference Global
    Pascal Type ClassReference Nested Private
    Pascal Type ClassReference Nested Protected
    Pascal Type ClassReference Nested Public
    Pascal Type ClassReference Nested Published
    Pascal Type ClassReference Nested Strict Private
    Pascal Type ClassReference Nested Strict Protected
    Pascal Type Interface Local
    Pascal Type Interface Global
    Pascal Type Interface Nested Private
    Pascal Type Interface Nested Protected
    Pascal Type Interface Nested Public
    Pascal Type Interface Nested Published
    Pascal Type Interface Nested Strict Private
    Pascal Type Interface Nested Strict Protected
    Pascal Type Object Local
    Pascal Type Object Global
    Pascal Variable Local
    Pascal Variable Global
    Pascal Variable Threadvar Local
    Pascal Variable Threadvar Global
    Pascal Unknown Ambiguous Function
    Pascal Unknown Type Class
    Pascal Unknown Environment
    Pascal Unknown Variable
    Pascal Unknown File
    Pascal Unknown Function
    Pascal Unknown Type Interface
    Pascal Unknown Type
    Pascal Unresolved Environment
    Pascal Unresolved Global Entity
    Pascal Unresolved Global External Function
    Pascal Unresolved Global External Procedure
    Pascal Unresolved Global External Variable
    Pascal Unresolved Global Function
    Pascal Unresolved Include File
    Pascal Unresolved Global Procedure
    Pascal Unresolved Global Type
    Pascal Unresolved Global Variable
    Pascal Sql Alias
    Pascal Sql Column
    Pascal Sql Cursor
    Pascal Sql Dbevent
    Pascal Sql File
    Pascal Sql Group
    Pascal Sql Index
    Pascal Sql IncludeFile
    Pascal Sql Location
    Pascal Sql Parameter
    Pascal Sql Procedure
    Pascal Sql Profile
    Pascal Sql Role
    Pascal Sql Rule
    Pascal Sql Schema
    Pascal Sql SecurityAlarm
    Pascal Sql Statement
    Pascal Sql StatementPrepared
    Pascal Sql Synonym
    Pascal Sql Table
    Pascal Sql Table GlobalTemp
    Pascal Sql User
    Pascal Sql Unresolved
    Pascal Sql Unresolved Table
    Pascal Sql Variable
	
    Plm Based Variable
    Plm File
    Plm Label
    Plm Macro
    Plm Main Module
    Plm Member Variable
    Plm Module
    Plm Parameter
    Plm Predeclared Typed Procedure
    Plm Predeclared Untyped Procedure
    Plm Predeclared Variable
    Plm Public Label
    Plm Public Parameter
    Plm Public Variable
    Plm Typed Procedure
    Plm Typed Procedure Reentrant
    Plm Typed Public Procedure
    Plm Typed Public Procedure Reentrant
    Plm Untyped Interrupt Procedure
    Plm Untyped Interrupt Procedure Reentrant
    Plm Untyped Procedure
    Plm Untyped Procedure Reentrant
    Plm Untyped Public Interrupt Procedure
    Plm Untyped Public Interrupt Procedure Reentrant
    Plm Untyped Public Procedure
    Plm Untyped Public Procedure Reentrant
    Plm Unknown File
    Plm Unknown Label
    Plm Unknown Member Variable
    Plm Unknown Typed Procedure
    Plm Unknown Untyped Procedure
    Plm Unknown Variable
    Plm Unresolved File
    Plm Unresolved Label
    Plm Unresolved Macro
    Plm Unresolved Member Variable
    Plm Unresolved Parameter
    Plm Unresolved Typed Procedure
    Plm Unresolved Typed Procedure Reentrant
    Plm Unresolved Untyped Interrupt Procedure
    Plm Unresolved Untyped Interrupt Procedure Reentrant
    Plm Unresolved Untyped Procedure
    Plm Unresolved Untyped Procedure Reentrant
    Plm Unresolved Variable
    Plm Variable
	
    python Abstract Class
    python Class
    python File
    python Module File
    python Function
    python Function Attribute
    python Function Attribute Static
    python Function Attribute Special
    python Function Attribute Special Static
    python LambdaParameter
    python Package
    python Parameter
    python Unknown Ambiguous Attribute
    python Unknown Class
    python Unknown Module
    python Unknown Package
    python Unknown Variable
    python Unresolved Attribute
    python Unresolved Class
    python Unresolved File
    python Unresolved Function
    python Unresolved Function Attribute
    python Unresolved Function Attribute Special
    python Unresolved Parameter
    python Unresolved Variable
    python Variable Attribute
    python Variable Attribute Instance
    python Variable Attribute Property
    python Variable Global
    python Variable Local
	
    Vhdl Alias
    Vhdl Architecture
    Vhdl Attribute
    Vhdl Buffer Port
    Vhdl Block Label
    Vhdl Component
    Vhdl Configuration
    Vhdl Constant
    Vhdl Entity
    Vhdl Enumeration Type
    Vhdl File
    Vhdl FileObject Object
    Vhdl FileParameter Parameter
    Vhdl FileType Type
    Vhdl Generate Label
    Vhdl Generate Parameter
    Vhdl Generic
    Vhdl Group
    Vhdl Group Template
    Vhdl Impure Function
    Vhdl Inout Parameter
    Vhdl Inout Port
    Vhdl Inout SignalParameter Parameter
    Vhdl In Parameter
    Vhdl In Port
    Vhdl In SignalParameter Parameter
    Vhdl Instantiation Label
    Vhdl Label
    Vhdl Library
    Vhdl Literal
    Vhdl Member
    Vhdl Out Parameter
    Vhdl Out Port
    Vhdl Out SignalParameter Parameter
    Vhdl Package
    Vhdl Postponed Process
    Vhdl Process
    Vhdl Predefined Attribute
    Vhdl Procedure
    Vhdl Pure Function
    Vhdl Record Type
    Vhdl Shared Variable
    Vhdl Signal
    Vhdl Subtype
    Vhdl Type
    Vhdl Unit
    Vhdl Unknown
    Vhdl Unknown Architecture
    Vhdl Unknown Attribute
    Vhdl Unknown Component
    Vhdl Unknown Configuration
    Vhdl Unknown Constant
    Vhdl Unknown Entity
    Vhdl Unknown File
    Vhdl Unknown Function
    Vhdl Unknown Group
    Vhdl Unknown Label
    Vhdl Unknown Literal
    Vhdl Unknown Package
    Vhdl Unknown Procedure
    Vhdl Unknown Signal
    Vhdl Unknown Subtype
    Vhdl Unknown Type
    Vhdl Unknown Unit
    Vhdl Unknown Variable
    Vhdl Unresolved
    Vhdl Unresolved Architecture
    Vhdl Unresolved Attribute
    Vhdl Unresolved Component
    Vhdl Unresolved Configuration
    Vhdl Unresolved Constant
    Vhdl Unresolved Entity
    Vhdl Unresolved File
    Vhdl Unresolved Function
    Vhdl Unresolved Group
    Vhdl Unresolved Label
    Vhdl Unresolved Literal
    Vhdl Unresolved Package
    Vhdl Unresolved Procedure
    Vhdl Unresolved Signal
    Vhdl Unresolved Subtype
    Vhdl Unresolved Type
    Vhdl Unresolved Unit
    Vhdl Unresolved Variable
    Vhdl Variable
    Vhdl Working Library
	
    web Applet
    web Css Class
    web Css FontFamily
    web Css Id
    web Css Keyframe
    web Css Media
    web Css Page
    web Css Property
    web Css Pseudo Class
    web Css TypeSelector
    web Css Unresolved Id
    web Css Unresolved Property
    web File
    web Html Anchor Target
    web Html Frame Name
    web Html Tag Id
    web Html Tag Name
    web Html Tag Value
    web Html Unknown Fragment
    web Html Unresolved
    web Javascript Ambient Module
    web Javascript Class
    web Javascript Enum
    web Javascript Function Class
    web Javascript Function
    web Javascript Method Function Instance
    web Javascript Import Alias
    web Javascript Interface
    web Javascript JQuery Selector
    web Javascript Namespace
    web Javascript Parameter
    web Javascript Predefined Module
    web Javascript Predefined Object
    web Javascript Predefined Type
    web Javascript Private Method Function
    web Javascript Private Method Function Static
    web Javascript Private Property
    web Javascript Private Constant Property
    web Javascript Private Property Static
    web Javascript Property
    web Javascript Property Instance
    web Javascript Protected Property
    web Javascript Protected Constant Property
    web Javascript Protected Property Static
    web Javascript Protected Method Function
    web Javascript Protected Method Function Static
    web Javascript Public Method Function
    web Javascript Public Method Function Static
    web Javascript Public Property
    web Javascript Public Constant Property
    web Javascript Public Property Static
    web Javascript Type Alias
    web Javascript Type Parameter
    web Javascript Variable Global
    web Javascript Variable Local
    web Javascript Unknown Ambiguous Property
    web Javascript Unknown Ambiguous Method
    web Javascript Unnamed Function
    web Javascript Unnamed Global Function
    web Javascript Unresolved Class
    web Javascript Unresolved File
    web Javascript Unresolved Function
    web Javascript Unresolved Function Class
    web Javascript Unresolved Function Method
    web Javascript Unresolved Module
    web Javascript Unresolved Property
    web Javascript Unresolved Variable Global
    web Php Abstract Class
    web Php Final Class
    web Php Class
    web Php Constant
    web Php Function
    web Php Function Anonymous
    web Php Function Reference
    web Php Import Alias
    web Php Interface
    web Php Private Method Final Function
    web Php Private Method Final Function Static
    web Php Private Method Final Reference Function
    web Php Private Method Final Reference Function Static
    web Php Private Method Function
    web Php Private Method Function Static
    web Php Private Method Reference Function
    web Php Private Method Reference Function Static
    web Php Private Property Constant
    web Php Private Property Variable
    web Php Private Static Property Variable
    web Php Protected Method Abstract Function
    web Php Protected Method Abstract Function Static
    web Php Protected Method Abstract Reference Function
    web Php Protected Method Abstract Reference Function Static
    web Php Protected Method Final Function
    web Php Protected Method Final Static Function
    web Php Protected Method Final Reference Function
    web Php Protected Method Final Reference Function Static
    web Php Protected Method Function
    web Php Protected Method Function Static
    web Php Protected Method Reference Function
    web Php Protected Method Reference Function Static
    web Php Protected Property Constant
    web Php Protected Property Variable
    web Php Protected Static Property Variable
    web Php Public Method Abstract Function
    web Php Public Method Abstract Function Static
    web Php Public Method Abstract Reference Function
    web Php Public Method Abstract Reference Function Static
    web Php Public Method Final Function
    web Php Public Method Final Function Static
    web Php Public Method Final Reference Function
    web Php Public Method Final Reference Function Static
    web Php Public Method Function
    web Php Public Method Function Static
    web Php Public Method Reference Function
    web Php Public Method Reference Function Static
    web Php Public Property Constant
    web Php Public Property Variable
    web Php Public Static Property Variable
    web Php Namespace
    web Php Parameter
    web Php Trait
    web Php Unknown Ambiguous Method Function
    web Php Unknown Ambiguous Constant Property
    web Php Unknown Ambiguous Variable Property
    web Php Unresolved Class
    web Php Unresolved Constant
    web Php Unresolved Function
    web Php Unresolved Method Function
    web Php Unresolved Namespace
    web Php Unresolved Property Constant
    web Php Unresolved Property Variable
    web Php Unresolved Trait
    web Php Unresolved Variable
    web Php Variable Global
    web Php Variable Local
    web Unknown File
    web Unresolved File
    web Xml Attribute
    web Xml Attribute Value
    web Xml Data
    web Xml Element
 
References
    Ada Abort Use
    Ada Abortby Useby
    Ada AccessAttrTyped
    Ada AccessAttrTypedby
    Ada Use Alloc
    Ada Useby Alloc
    Ada Association
    Ada Associationby
    Ada Call
    Ada Callby
    Ada Call Indirect
    Ada Callby Indirect
    Ada Call Dispatch
    Ada Callby Dispatch
    Ada Call Dispatch Indirect
    Ada Callby Dispatch Indirect
    Ada Call Implicit
    Ada Callby Implicit
    Ada CallParamFormal
    Ada CallParamFormalfor
    Ada Parent Libunit
    Ada Child Libunit
    Ada Declare
    Ada Declarein
    Ada Declare Body
    Ada Declarein Body
    Ada Declare Body File
    Ada Declarein Body File
    Ada Declare Formal
    Ada Declarein Formal
    Ada Declare Incomplete
    Ada Declarein Incomplete
    Ada Declare Instance
    Ada Declarein Instance
    Ada Declare Instance File
    Ada Declarein Instance File
    Ada Declare Private
    Ada Declarein Private
    Ada Declare Spec
    Ada Declarein Spec
    Ada Declare Spec File
    Ada Declarein Spec File
    Ada Declare Stub
    Ada Declarein Stub
    Ada Dot
    Ada Dotby
    Ada ElaborateBody Implicit
    Ada ElaborateBodyby Implicit
    Ada ElaborateBody Ref
    Ada ElaborateBodyby Refby
    Ada End
    Ada Endby
    Ada End Body
    Ada Endby Body
    Ada End Unnamed
    Ada Endby Unnamed
    Ada End Body Unnamed
    Ada Endby Body Unnamed
    Ada Gpr Extend Use
    Ada Gpr Extendby Useby
    Ada Gpr With
    Ada Gpr Withby
    Ada Handle
    Ada Handleby
    Ada Import Ref
    Ada Importby Refby
    Ada Include
    Ada Includeby
    Ada Instanceof
    Ada Instance
    Ada InstanceActual
    Ada InstanceActualfor
    Ada Instance Copy
    Ada Instanceof Copy
    Ada InstanceParamFormal
    Ada InstanceParamFormalfor
    Ada Operation
    Ada Operationfor
    Ada Operation Classwide
    Ada Operationfor Classwide
    Ada Overrides
    Ada Overriddenby
    Ada Raise
    Ada Raiseby
    Ada Raise Implicit
    Ada Raiseby Implicit
    Ada Ref
    Ada Refby
    Ada Ref DefaultFormal
    Ada Refby DefaultFormal
    Ada Rename
    Ada Renameby
    Ada Renamecall
    Ada Renamecallby
    Ada Representation Ref
    Ada Representation Refby
    Ada Root
    Ada Rootin
    Ada Separatefrom
    Ada Separate
    Ada Set
    Ada Setby
    Ada Set Init
    Ada Setby Init
    Ada Set Partial
    Ada Setby Partial
    Ada Typed
    Ada Typedby
    Ada Typed Implicit
    Ada Typedby Implicit
    Ada Ref Convert
    Ada Refby Convert
    Ada Derivefrom
    Ada Derive
    Ada Subtypefrom
    Ada Subtype
    Ada Use
    Ada Useby
    Ada UsePackage
    Ada UsePackageby
    Ada UsePackage Needed
    Ada UsePackageby Needed
    Ada UsePackageAccess
    Ada UsePackageAccessby
    Ada Use Partial
    Ada Useby Partial
    Ada Use Access
    Ada Useby Access
    Ada Use Ptr
    Ada Useby Ptr
    Ada UseType
    Ada UseTypeby
    Ada UseType Needed
    Ada UseTypeby Needed
    Ada UseTypeAccess
    Ada UseTypeAccessby
    Ada With Body
    Ada Withby Body
    Ada With Spec
    Ada Withby Spec
    Ada With Spec Implicit
    Ada Withby Spec Implicit
    Ada Withaccess
    Ada Withaccessby
    Ada With Needed Body
    Ada Withby Needed Body
    Ada With Needed Spec
    Ada Withby Needed Spec
    Ada With Redundant Body
    Ada Withby Redundant Body
    Ada With Redundant Spec
    Ada Withby Redundant Spec
	
    Assembly Call
    Assembly Callby
    Assembly Declare
    Assembly Declarein
    Assembly Define
    Assembly Definein
    Assembly Goto
    Assembly Gotoby
    Assembly Include
    Assembly Includeby
    Assembly Modify
    Assembly Modifyby
    Assembly Set
    Assembly Setby
    Assembly Use
    Assembly Useby
	
    basic Aliasfor
    basic Alias
    basic Use Alloc
    basic Useby Allocby
    basic Call
    basic Callby
    basic Call Implicit
    basic Callby Implicit
    basic Call Virtual
    basic Callby Virtual
    basic Call Virtual Implicit
    basic Callby Virtual Implicit
    basic Cast Use
    basic Castby Useby
    basic Contain
    basic Containin
    basic Couple
    basic Coupleby
    basic Declare
    basic Declarein
    basic Define
    basic Definein
    basic Base
    basic Derive
    basic Base Implicit
    basic Derive Implicit
    basic DotRef
    basic DotRefby
    basic End
    basic Endby
    basic Catch Exception
    basic Catchby Exception
    basic Throw Exception
    basic Throwby Exception
    basic Import
    basic Importby
    basic Implement
    basic Implementby
    basic Modify
    basic Modifyby
    basic Overrides
    basic Overriddenby
    basic Set
    basic Setby
    basic Set Init
    basic Setby Init
    basic Shadow
    basic Shadowby
    basic Typed
    basic Typedby
    basic Typed Implicit
    basic Typedby Implicit
    basic Use
    basic Useby
    basic Use Ptr
    basic Useby Ptr
	
    C Addr Use
    C Addr Useby
    C Addr Use Return
    C Addr Useby Return
    C Alias
    C Aliasby
    C Asm Call
    C Asm Callby
    C Asm Use
    C Asm Useby
    C Assign Ptr
    C Assignby Ptr
    C Assign Ref
    C Assignby Ref
    C Assign Value
    C Assignby Value
    C Begin
    C Beginby
    C Call
    C Callby
    C Call Virtual
    C Callby Virtual
    C Cast Use
    C Cast Useby
    C Call Implicit
    C Callby Implicit
    C Call Ptr
    C Callby Ptr
    C Declare
    C Declarein
    C Declare Implicit
    C Declarein Implicit
    C Declare Using
    C Declarein Using
    C Define
    C Definein
    C Deref Call
    C Deref Callby
    C Deref Modify
    C Deref Modifyby
    C Deref Set
    C Deref Setby
    C Deref Use
    C Deref Useby
    C Deref Use Return
    C Deref Useby Return
    C End
    C Endby
    C Allow Exception
    C Allowby Exception
    C Catch Exception
    C Catchby Exception
    C Throw Exception
    C Throwby Exception
    C Friend
    C Friendby
    C Inactive Call
    C Inactive Callby
    C Inactive Define
    C Inactive Definein
    C Inactive Include
    C Inactive Includeby
    C Inactive Use
    C Inactive Useby
    C Include
    C Includeby
    C Include Implicit
    C Includeby Implicit
    C Instance
    C Instanceof
    C Modify
    C Modifyby
    C Name
    C Nameby
    C Overrides
    C Overriddenby
    C Private Base
    C Private Derive
    C Protected Base
    C Protected Derive
    C Public Base
    C Public Derive
    C Set
    C Setby
    C Set Init
    C Setby Init
    C Set Init Implicit
    C Setby Init Implicit
    C Specialize
    C Specializeby
    C Typed
    C Typedby
    C Typed Implicit
    C Typedby Implicit
    C Typed TemplateArgument
    C Typedby TemplateArgument
    C Use
    C Useby
    C Use Macrodefine
    C Useby Macrodefine
    C Use Macroexpand
    C Useby Macroexpand
    C Use Ptr
    C Useby Ptr
    C Use Return
    C Useby Return
    C Using
    C Usingby
    C Virtual Private Base
    C Virtual Private Derive
    C Virtual Protected Base
    C Virtual Protected Derive
    C Virtual Public Base
    C Virtual Public Derive
    C ObjC Adopt
    C ObjC Adoptby
    C ObjC Base
    C ObjC Derive
    C ObjC Extend
    C ObjC Extendby
    C ObjC Implement
    C ObjC Implementby
    C ObjC Implement Extend
    C ObjC Implementby Extendby
    C ObjC Message Call
    C ObjC Message Callby
    C Declare Default
    C Declarein Default
    C Declare Delete
    C Declarein Delete
    C Use Capture
    C Useby Capture
    C Use Expand
    C Useby Expand
	
    Cobol Call
    Cobol Callby
    Cobol Cancel
    Cobol Cancelby
    Cobol Close
    Cobol Closeby
    Cobol Copy
    Cobol Copyby
    Cobol Define
    Cobol Definein
    Cobol Delete
    Cobol Deleteby
    Cobol End
    Cobol Endby
    Cobol Goto
    Cobol Gotoby
    Cobol Record Key
    Cobol Record Keyby
    Cobol Alternate Record Key
    Cobol Alternate Record Keyby
    Cobol Relative Key
    Cobol Relative Keyby
    Cobol Modify
    Cobol Modifyby
    Cobol Input Open
    Cobol Input Openby
    Cobol Output Open
    Cobol Output Openby
    Cobol Input Output Open
    Cobol Input Output Openby
    Cobol Extend Open
    Cobol Extend Openby
    Cobol Perform
    Cobol Performby
    Cobol Perform Through
    Cobol Performby Through
    Cobol Read
    Cobol Readby
    Cobol Redefine
    Cobol Redefineby
    Cobol Rename
    Cobol Renameby
    Cobol Rewrite
    Cobol Rewriteby
    Cobol Select
    Cobol Selectby
    Cobol Set
    Cobol Setby
    Cobol Start
    Cobol Startby
    Cobol Status
    Cobol Statusby
    Cobol Use
    Cobol Useby
    Cobol Write
    Cobol Writeby
	
    c# csharp Aliasfor
    c# csharp Alias
    c# csharp Use Alloc
    c# csharp Useby Allocby
    c# csharp Call
    c# csharp Callby
    c# csharp Call Implicit
    c# csharp Callby Implicit
    c# csharp Call Virtual
    c# csharp Callby Virtual
    c# csharp Call Virtual Implicit
    c# csharp Callby Virtual Implicit
    c# csharp Cast Use
    c# csharp Castby Useby
    c# csharp Contain
    c# csharp Containin
    c# csharp Couple
    c# csharp Coupleby
    c# csharp Declare
    c# csharp Declarein
    c# csharp Define
    c# csharp Definein
    c# csharp Base
    c# csharp Derive
    c# csharp DotRef
    c# csharp DotRefby
    c# csharp End
    c# csharp Endby
    c# csharp Catch Exception
    c# csharp Catchby Exception
    c# csharp Throw Exception
    c# csharp Throwby Exception
    c# csharp Implement
    c# csharp Implementby
    c# csharp Modify
    c# csharp Modifyby
    c# csharp Overrides
    c# csharp Overriddenby
    c# csharp Set
    c# csharp Setby
    c# csharp Set Init
    c# csharp Setby Init
    c# csharp Typed
    c# csharp Typedby
    c# csharp Typed Implicit
    c# csharp Typedby Implicit
    c# csharp Use
    c# csharp Useby
    c# csharp Use Attribute
    c# csharp Useby Attribute
    c# csharp Use Ptr
    c# csharp Useby Ptr
    c# csharp Using
    c# csharp Usingby
    c# csharp Global Using
    c# csharp Global Usingby
	
    Fortran Call
    Fortran Callby
    Fortran Deref Call
    Fortran Deref Callby
    Fortran Call Virtual
    Fortran Callby Virtual
    Fortran Contain
    Fortran Containin
    Fortran Declare
    Fortran Declarein
    Fortran Declare Bind
    Fortran Declarein Bind
    Fortran Declare Bind Private
    Fortran Declarein Bind Private
    Fortran Declare Bind Final
    Fortran Declarein Bind Final
    Fortran Define
    Fortran Definein
    Fortran Define Bind
    Fortran Definein Bind
    Fortran Define Bind Private
    Fortran Definein Bind Private
    Fortran Define Inc
    Fortran Definein Inc
    Fortran Define Implicit
    Fortran Definein Implicit
    Fortran Define Private
    Fortran Definein Private
    Fortran Define Private Inc
    Fortran Definein Private Inc
    Fortran End
    Fortran Endby
    Fortran End Unnamed
    Fortran Endby Unnamed
    Fortran Extend
    Fortran Extendby
    Fortran Equivalence
    Fortran Equivalenceby
    Fortran Include
    Fortran Includeby
    Fortran Use IO
    Fortran Useby IO
    Fortran ModuleUse
    Fortran ModuleUseby
    Fortran ModuleUse Only
    Fortran ModuleUseby Only
    Fortran UseModuleEntity
    Fortran UseModuleEntityby
    Fortran UseRenameEntity
    Fortran UseRenameEntityby
    Fortran Overrides
    Fortran Overriddenby
    Fortran Parent
    Fortran Child
    Fortran Rename
    Fortran Renameby
    Fortran Ref
    Fortran Refby
    Fortran Set
    Fortran Setby
    Fortran Set Out Argument
    Fortran Setby Out Argument
    Fortran Coindexed Set
    Fortran Coindexed Setby
    Fortran Coindexed Set Out Argument
    Fortran Coindexed Setby Out Argument
    Fortran Set Init
    Fortran Setby Init
    Fortran Typed
    Fortran Typedby
    Fortran Use
    Fortran Useby
    Fortran Use Argument
    Fortran Useby Argument
    Fortran Use In Argument
    Fortran Useby In Argument
    Fortran Coindexed Use
    Fortran Coindexed Useby
    Fortran Coindexed Use Argument
    Fortran Coindexed Useby Argument
    Fortran Coindexed Use In Argument
    Fortran Coindexed Useby In Argument
    Fortran Addr Use
    Fortran Addr Useby
    Fortran Use Ptr
    Fortran Useby Ptr
	
    Java Call
    Java Callby
    Java Call Nondynamic
    Java Callby Nondynamic
    Java Use Cast
    Java Useby Castby
    Java Contain
    Java Containin
    Java Couple
    Java Coupleby
    Java Create
    Java Createby
    Java Declare
    Java Declarein
    Java Define
    Java Definein
    Java Define Implicit
    Java Definein Implicit
    Java DotRef
    Java DotRefby
    Java End
    Java Endby
    Java Export
    Java Exportby
    Java Extend Couple
    Java Extendby Coupleby
    Java Extend Couple External
    Java Extendby Coupleby External
    Java Extend Couple Implicit External
    Java Extendby Coupleby Implicit External
    Java Extend Couple Implicit
    Java Extendby Coupleby Implicit
    Java Implement Couple
    Java Implementby Coupleby
    Java Import
    Java Importby
    Java Import Demand
    Java Importby Demand
    Java Modify
    Java Modifyby
    Java Modify Deref Partial
    Java Modifyby Deref Partial
    Java ModuleUse
    Java ModuleUseby
    Java Open
    Java Openby
    Java Overrides
    Java Overriddenby
    Java Provide
    Java Provideby
    Java Require
    Java Requireby
    Java Set
    Java Setby
    Java Set Init
    Java Setby Init
    Java Set Deref Partial
    Java Setby Deref Partial
    Java Throw
    Java Throwby
    Java Typed
    Java Typedby
    Java Use
    Java Useby
    Java Use Deref Partial
    Java Useby Deref Partial
    Java Use Ptr
    Java Useby Ptr
    Java Use Return
    Java Useby Return
	
    Jovial Asm Use
    Jovial Asm Useby
    Jovial Call
    Jovial Callby
    Jovial Cast
    Jovial Castby
    Jovial CompoolAccess All
    Jovial CompoolAccessby All
    Jovial CompoolAccess
    Jovial CompoolAccessby
    Jovial CompoolFileAccess
    Jovial CompoolFileAccessby
    Jovial ItemAccess
    Jovial ItemAccessby
    Jovial ItemAccess All
    Jovial ItemAccessby All
    Jovial ItemAccess Implicit
    Jovial ItemAccessby Implicit
    Jovial Copy
    Jovial Copyby
    Jovial Declare
    Jovial Declarein
    Jovial Define
    Jovial Definein
    Jovial End
    Jovial Endby
    Jovial Declare Inline
    Jovial Declarein Inline
    Jovial Like
    Jovial Likeby
    Jovial Overlay
    Jovial Overlayby
    Jovial Overlay Implicit
    Jovial Overlayby Implicit
    Jovial Typed Ptr
    Jovial Typedby Ptr
    Jovial Set
    Jovial Setby
    Jovial Set Init
    Jovial Setby Init
    Jovial Typed
    Jovial Typedby
    Jovial Use
    Jovial Useby
    Jovial Value
    Jovial Valueof
	
    Pascal Call
    Pascal Callby
    Pascal Call Possible
    Pascal Callby Possible
    Pascal Call Ptr
    Pascal Callby Ptr
    Pascal Call Ptr Implicit
    Pascal Callby Ptr Implicit
    Pascal Call Virtual
    Pascal Callby Virtual
    Pascal Cast
    Pascal Castby
    Pascal Classof
    Pascal Classofby
    Pascal Contain
    Pascal Containin
    Pascal Couple
    Pascal Coupleby
    Pascal Declare
    Pascal Declarein
    Pascal Define
    Pascal Definein
    Pascal Deref Call
    Pascal Deref Callby
    Pascal Derivefrom
    Pascal Derive
    Pascal DotRef
    Pascal DotRefby
    Pascal DotRef Context
    Pascal DotRefby Context
    Pascal End
    Pascal Endby
    Pascal Implement
    Pascal Implementby
    Pascal Handle Use
    Pascal Handleby Useby
    Pascal HelperFor
    Pascal HelperForby
    Pascal Inherit
    Pascal Inheritby
    Pascal Include
    Pascal Includeby
    Pascal Inheritenv
    Pascal Inheritenvby
    Pascal Inherit Useunit
    Pascal Inheritby Useunit
    Pascal Inherit Useunit Implicit
    Pascal Inheritby Useunit Implicit
    Pascal Hasambiguous
    Pascal Ambiguousfor
    Pascal Hasenvironment
    Pascal Hasenvironmentby
    Pascal Overrides
    Pascal Overriddenby
    Pascal Raise Use
    Pascal Raiseby Useby
    Pascal Set
    Pascal Setby
    Pascal Set Init
    Pascal Setby Init
    Pascal Typed
    Pascal Typedby
    Pascal Use
    Pascal Useby
    Pascal Use Ptr
    Pascal Useby Ptr
    Pascal Use Read
    Pascal Useby Read
    Pascal Use Write
    Pascal Useby Write
    Pascal Sql Call
    Pascal Sql Callby
    Pascal Sql Call Statement
    Pascal Sql Callby Statement
    Pascal Sql Define
    Pascal Sql Definein
    Pascal Sql Set
    Pascal Sql Setby
    Pascal Sql Typed
    Pascal Sql Typedby
    Pascal Sql Use
    Pascal Sql Useby
	
    Plm Base
    Plm Basefor
    Plm Call
    Plm Callby
    Plm Call Address
    Plm Callby Address
    Plm Declare
    Plm Declarein
    Plm Declare External
    Plm Declarein External
    Plm Declare Formal
    Plm Declarein Formal
    Plm Declare Implicit
    Plm Declarein Implicit
    Plm Declare Label
    Plm Declarein Label
    Plm Declare Public
    Plm Declarein Public
    Plm End
    Plm Endby
    Plm Include
    Plm Includeby
    Plm LocationRef
    Plm LocationRefby
    Plm Overlay
    Plm Overlayby
    Plm Set
    Plm Setby
    Plm Set Init
    Plm Setby Init
    Plm Use
    Plm Useby
	
    Python Alias
    Python Aliasfor
    Python Use Alloc
    Python Useby Alloc
    Python Call
    Python Callby
    Python Call Possible
    Python Callby Possible
    Python Contain
    Python Containin
    Python Couple
    Python Coupleby
    Python Declare
    Python Declarein
    Python Declare Implicit
    Python Declarein Implicit
    Python Define
    Python Definein
    Python Deleter
    Python Deleterfor
    Python DotRef
    Python DotRefby
    Python End
    Python Endby
    Python Getter
    Python Getterfor
    Python Hasambiguous
    Python Ambiguousfor
    Python Import
    Python Importby
    Python Import From
    Python Importby From
    Python Import Implicit
    Python Importby Implicit
    Python Inherit
    Python Inheritby
    Python Modify
    Python Modifyby
    Python Overrides
    Python Overriddenby
    Python Raise
    Python Raiseby
    Python Set
    Python Setby
    Python Set Possible
    Python Setby Possible
    Python Set Init
    Python Setby Init
    Python Setter
    Python Setterfor
    Python Typed
    Python Typedby
    Python Use
    Python Useby
    Python Use Possible
    Python Useby Possible
	
    Vhdl AliasRef
    Vhdl AliasReffor
    Vhdl Body Declare
    Vhdl Body Declarein
    Vhdl Bind
    Vhdl Bindby
    Vhdl Implicit Bind
    Vhdl Implicit Bindby
    Vhdl Call
    Vhdl Callby
    Vhdl Configure
    Vhdl Configureby
    Vhdl Declare
    Vhdl Declarein
    Vhdl Decorate
    Vhdl Decorateby
    Vhdl End
    Vhdl Endby
    Vhdl End Body
    Vhdl Endby Body
    Vhdl Implement
    Vhdl Implementby
    Vhdl Incomplete Declare
    Vhdl Incomplete Declarein
    Vhdl Instantiate
    Vhdl Instantiateby
    Vhdl Map
    Vhdl Mapby
    Vhdl Map Formal
    Vhdl Mapby Formal
    Vhdl Return
    Vhdl Returnby
    Vhdl Set
    Vhdl Setby
    Vhdl Set Init
    Vhdl Setby Init
    Vhdl Typed
    Vhdl Typedby
    Vhdl Use
    Vhdl Useby
    Vhdl Use Name
    Vhdl Useby Name
    Vhdl Wait
    Vhdl Waitby
	
    web Css Define
    web Css Definein
    web Css Import
    web Css Importby
    web Css Set Important
    web Css Setby Important
    web Css Set
    web Css Setby
    web Css Use
    web Css Useby
    web Html Call
    web Html Callby
    web Html Declare
    web Html Declarein
    web Html Define
    web Html Definein
    web Html Link
    web Html Linkby
    web Html Src
    web Html Srcby
    web Html Style Use
    web Html Style Useby
    web Html Use
    web Html Useby
    web Javascript Alias
    web Javascript Aliasfor
    web Javascript Call
    web Javascript Callby
    web Javascript Call Implicit
    web Javascript Callby Implicit
    web Javascript Call New
    web Javascript Callby New
    web Javascript Call New Possible
    web Javascript Callby New Possible
    web Javascript Call Possible
    web Javascript Callby Possible
    web Javascript Declare Export
    web Javascript Declarein Export
    web Javascript Declare Export Default
    web Javascript Declarein Export Default
    web Javascript Define
    web Javascript Definein
    web Javascript Define Export
    web Javascript Definein Export
    web Javascript Define Default Export
    web Javascript Definein Default Export
    web Javascript Define Implicit
    web Javascript Definein Implicit
    web Javascript Prototype Define Implicit
    web Javascript Prototype Definein Implicit
    web Javascript This Define Implicit
    web Javascript This Definein Implicit
    web Javascript Define Implicit Set
    web Javascript Definein Implicit Setby
    web Javascript End
    web Javascript Endby
    web Javascript Extend
    web Javascript Extendby
    web Javascript Getter
    web Javascript Getterfor
    web Javascript Implement
    web Javascript Implementby
    web Javascript Import
    web Javascript Importby
    web Javascript Import From
    web Javascript Importby From
    web Javascript Modify
    web Javascript Modifyby
    web Javascript Overrides
    web Javascript Overriddenby
    web Javascript Hasambiguous
    web Javascript Ambiguousfor
    web Javascript Reexport
    web Javascript Reexportby
    web Javascript Reexport All
    web Javascript Reexportby All
    web Javascript Require
    web Javascript Requireby
    web Javascript Set
    web Javascript Setby
    web Javascript Set Init
    web Javascript Setby Init
    web Javascript Set Possible
    web Javascript Setby Possible
    web Javascript Setter
    web Javascript Setterfor
    web Javascript Typed
    web Javascript Typedby
    web Javascript Use
    web Javascript Useby
    web Javascript Use Ptr
    web Javascript Useby Ptr
    web Javascript Use Ptr Possible
    web Javascript Useby Ptr Possible
    web Javascript Use Possible
    web Javascript Useby Possible
    web Javascript String Use
    web Javascript String Useby
    web Php Alias
    web Php Aliasfor
    web Php Call
    web Php Callby
    web Php Call Possible
    web Php Callby Possible
    web Php Declare
    web Php Declarein
    web Php Define
    web Php Definein
    web Php Define Implicit
    web Php Definein Implicit
    web Php End
    web Php Endby
    web Php Extend
    web Php Extendby
    web Php Hasambiguous
    web Php Ambiguousfor
    web Php Implement
    web Php Implementby
    web Php Include
    web Php Includeby
    web Php Modify
    web Php Modifyby
    web Php Overrides
    web Php Overriddenby
    web Php Require
    web Php Requireby
    web Php Set
    web Php Setby
    web Php Set Possible
    web Php Setby Possible
    web Php Typed
    web Php Typedby
    web Php Typed Implicit
    web Php Typedby Implicit
    web Php Use
    web Php Useby
    web Php Use Possible
    web Php Useby Possible
    web Php Use Implicit
    web Php Useby Implicit
    web Php Import
    web Php Importby
    web Php Use Ptr
    web Php Useby Ptr
    web Php Use Trait
    web Php Useby Trait
    web Xml Contain
    web Xml Containin
    web Xml Define
    web Xml Definein
    web Xml End
    web Xml Endby
    web Xml Set
    web Xml Setby
    web Xml Setto
    web Xml Settoby
 