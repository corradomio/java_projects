package jext.io.file;

/*
        <directory name="..."  includes="pat1,pat2 pat3" excludes="pat1 pat2 pat3">
             <include name="pattern"/>
             <exclude name="pattern"/>
        </directory>

        <filename name="pattern"/>
 */

public class FileSet extends ItemSet {

    protected FileSet() {
        super();
        this.selectDirs = false;
    }

}
