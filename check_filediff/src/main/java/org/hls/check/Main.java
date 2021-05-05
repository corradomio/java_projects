package org.hls.check;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String ORIGINAL = "D:\\SPLGroup\\SPLDevelopment3.0\\splserver2.8.3\\splserver\\spl-analysis\\spl-analysis-algos\\src\\main\\java\\ae\\ebtic\\spl\\analysis\\runtime\\RuntimeGraph.java";
        String RIVISED = "D:\\SPLGroup\\SPLDevelopment3.0\\splserver3.0\\splserver\\spl-analysis\\spl-analysis-algos\\src\\main\\java\\ae\\ebtic\\spl\\analysis\\runtime\\RuntimeGraph.java";

        //build simple lists of the lines of the two testfiles
        List<String> original = Files.readAllLines(new File(ORIGINAL).toPath());
        List<String> revised  = Files.readAllLines(new File(RIVISED).toPath());

        //compute the patch: this is the diffutils part
        Patch<String> patch = DiffUtils.diff(original, revised);

        //simple output the computed patch to console
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            System.out.println(delta);
        }
    }
}
