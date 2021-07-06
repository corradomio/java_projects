package org.hls.check;

import jext.logging.Logger;

import java.io.File;

public class DependencyAnalyzer {
    public AnalysisLimits getAnalysisLimits() {
        return new AnalysisLimits();
    }

    public File getSPLDirectory() {
        return new File(".");
    }

    public String getRefId() {
        return "12345";
    }

    public Logger getLogger() {
        return Logger.getLogger(DependencyAnalyzer.class);
    }
}
