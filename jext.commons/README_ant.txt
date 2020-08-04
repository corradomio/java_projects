https://ant.apache.org/manual/conceptstypeslist.html

widcards:
    *       any seq of chars
    ?       any char
    **      any seq of directories/files

dirset:     group of directories

    <dirset dir="${dir}">
        <include name="apps/**/classes"/>
        <exclude name="apps/**/*Test*"/>
    </dirset>

fileset:    group of files

    <fileset dir="${dir}">
        <include name="**/*.java"/>
        <exclude name="**/*Test*"/>
    </fileset>

multirootfileset:   group of files & directories

    <multirootfileset basedirs="${dir1},${dir2}">
        <basedir file="${dir1}"/>
        <basedir file="${dir2}"/>
        <include name="apps/**/classes"/>
        <exclude name="apps/**/*Test*"/>
    </multirootfileset>


--------------------------------------------------------------

Wildcard
    FilePattern : Wildcard + recursiveFlag
        FilePatterns : List[FilePattern]
            FileSet : List[FilePatters] + relativeDir
                FileSets : List[FileSet] + configuration
            PatternSet : FilePatterns + configuration
