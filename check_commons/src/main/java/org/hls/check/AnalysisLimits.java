package org.hls.check;

import jext.util.TimeUtils;

public class AnalysisLimits {
    // maximum time, in milliseconds, to analyze a file
    public long fileTime;
    // maximum time, in milliseconds, to analyze a method
    public long methodTime;
    // maximum size, in number of lines, for the analysis
    public long fileSize = TimeUtils.parse("100000m");
}
