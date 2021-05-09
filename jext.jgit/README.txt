This is EXACT clone of the project

    "org.eclipse.jgit"

downloaded from

    https://github.com/eclipse/jgit

The ONLY differences are:

    1) some unused files (in THIS project) removed (but their presence is not a problem)
       The files are removed just to reduce the number of unused files.

    2) CHANGED the class "CloneCommand" in this way:

       method "CloneCommand.isNonEmptyDirectory(File dir)"

       now returns ALWAYS false.


The problem to resolve is this: we MUST download files from Git repository in a NOT NECESSARY EMPTY
directory.