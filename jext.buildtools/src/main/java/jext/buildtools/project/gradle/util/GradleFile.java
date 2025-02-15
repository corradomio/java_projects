package jext.buildtools.project.gradle.util;

import jext.util.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GradleFile {

    private Logger logger = Logger.getLogger(getClass());

    protected File gradleFile;
    protected List<String> content;

    protected GradleFile(File gradleFile) {
        this.gradleFile = gradleFile;
        loadContent();
    }

    private void loadContent() {
        // read the content 'line by line'
        content = new ArrayList<>();

        boolean continuation = false;
        if (gradleFile.exists())
        try (BufferedReader r = new BufferedReader(new FileReader(gradleFile))) {
            for(String line = r.readLine(); line !=null; line = r.readLine()) {
                line = line.trim();

                // skip empty lines and line comments
                if (line.length() == 0 || line.startsWith("//"))
                    continue;

                    // add to the previous line if the previous line ended with ","
                else if (continuation) {
                    int last = content.size() - 1;
                    content.set(last, content.get(last) + line);
                    continuation = line.endsWith(",");
                }
                // 'multi-line' line
                else if (line.endsWith(",")) {
                    continuation = true;
                    content.add(line);
                }
                // single line
                else {
                    content.add(line);
                }
            }
        }
        catch (Exception e) {
            logger.error(e, e);
        }
    }

}
