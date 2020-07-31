https://ant.apache.org/manual/conceptstypeslist.html

widcards:
    *       any seq ofchars
    ?       any char
    **      any seq of directories/files



dirset:     group of directories

    <dirset dir="${build.dir}">
      <include name="apps/**/classes"/>
      <exclude name="apps/**/*Test*"/>
    </dirset>

fileset:    group of files

    <fileset dir="${server.src}" casesensitive="yes">
      <include name="**/*.java"/>
      <exclude name="**/*Test*"/>
    </fileset>


multirootfileset:   group of files & directories

    <multirootfileset basedirs="${build.dir},${other.project.dir}">
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
