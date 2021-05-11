Versioning system
-----------------

    A VS supports two concepts:

    1) a 'tagged' snapshot: this is a specific snapshot of the VS content
       that doesn't change in the time

    2) a 'branch': this is a 'separated' evolution of the VS content.
       The content of a 'branch' changes in the time


    minimum list of properties:

    url:    remote url
    branch: (optional) the branch to select
    local:

Git
---


SVN
---
    In SVN a 'branch'  is a specific URL, as in:

        https://svn.apache.org/viewvc/ant/core/

    It can be configures in two way:

        url: http://svn.apache.org/repos/asf/ant/core/branches/ANT_18_BRANCH

    OR:

        root url: http://svn.apache.org/repos/asf/ant/core
        branch: ANT_18_BRANCH

    in this case, it will be created in the following way:

        url: <root url>/branches/<branch>





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
