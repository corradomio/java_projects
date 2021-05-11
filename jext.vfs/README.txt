This library is a simplified version of

    Apache Commons VFS

based on the following objects

    VFileSystems:
        factory class used to instantiate a 'virtual filesystem' (VFileSystem)
        which VFileSystem instantiate depende on the 'protocol' used

            local:
            file:
            ftp:

            git:
            git+http:
            git+https:

            svn:
            svn+http:
            svn+https:
            svn+file:
            svn+ssh:

            compress:
            compressed:
            archive:

    VFileSystem:    interface implemented by all 'virtual filesystems'

        VFileSystem
            AbstractFileSystem
                SVNFileSystem
                GitFileSystem
                LocalFileSystem
                FTPFileSystem
                CompressFileSystem

    VFile:              interface implemented by each remote file.
        VFileName:      interface used to represent a file name
        VFileContent:   interface used to represent the file content

    VFileSelector:      interface used to filter the VFile returned by
                        a directory




-----------------------------------------------------------------------------
- GiltLab
-----------------------------------------------------------------------------
GitLab Token: MYsYKXTzsNqz2kA2Z1BU


    https://github.com/gitlab4j/gitlab4j-api

GitHub

    https://github.com/spotify/github-java-client
    https://www.eclipse.org/egit/

    https://github-api.kohsuke.org/
    https://github.com/hub4j/github-api




-----------------------------------------------------------------------------
- Apache Subversion repository
-----------------------------------------------------------------------------

    https://svn.apache.org/

    THIS IS ONLY A WEB INTERFACE TO "http://svn.apache.org/repos/asf"
        https://svn.apache.org/viewvc

Ant:
    https://svn.apache.org/viewvc/ant/core/
        trunk
        tags
        branches

    https://svn.apache.org/viewvc/ant/core/trunk


    svn checkout http://svn.apache.org/repos/asf/ant/core/trunk ant-core


Example (working!)

    svn checkout http://svn.apache.org/repos/asf/spamassassin/trunk spamassassin

-----------------------------------------------------------------------------
- Versioning systems
-----------------------------------------------------------------------------
Almost all versioning systems permit to navigate the remote file system.
Git seems a little more complex.

1) each file has a 'revision number'
2) however the repository can be labelled with a 'tag/label' to permit to
   retrieve an EXACT local copy of the repository status when the repository
   has been 'labelled'

This means that a 'Versioning System' can be considered AS a remote filesystem
WITH an extra property:

1) it is possible to retrieve the list of  'tags/labels'
2) it is possible to 'download' a specific 'tag/label'
3) it is possible to known the 'tag/label' of the 'local copy'
