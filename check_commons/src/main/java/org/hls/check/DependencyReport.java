package org.hls.check;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jext.logging.Logger;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Module;
import jext.util.FileUtils;
import jext.util.JSONUtils;
import jext.util.MapUtils;
import jext.util.MemoryUtils;
import jext.util.Parameters;
import jext.util.TimeUtils;
import jext.util.tuples.Tuple3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DependencyReport {

    private static class SourceKey implements Comparable<SourceKey> {
        public String module;
        public String source;

        SourceKey(Source source) {
            this.module = source.getModule().getName().getFullName();
            this.source = source.getName().getFullName();
        }

        @Override
        public int hashCode() {
            return Objects.hash(module, source);
        }

        @Override
        public boolean equals(Object obj) {
            SourceKey that = (SourceKey) obj;
            return this.module.equals(that.module) && this.source.equals(that.source);
        }

        @Override
        public int compareTo(SourceKey that) {
            int cmp = module.compareTo(that.module);
            if (cmp == 0)
                cmp = source.compareTo(that.source);
            return cmp;
        }

        @Override
        public String toString() {
            //return String.format("{module: %s, source: %s", module, source);
            return String.format("%s::%s", module, source);
        }
    }

    private static class MemoryStatistics {

        private transient long timestamp;

        private float sum1;     // sum(xi)
        private float sum2;     // sum(xi^2)
        private long n;

        private float min = Float.MAX_VALUE;
        private float max = 0;

        MemoryStatistics() {
            timestamp = System.currentTimeMillis()-1010;
        }

        synchronized void allocatedMemory() {
            long now = System.currentTimeMillis();
            if (now - timestamp < 1000)
                return;

            float allocatedMemory = MemoryUtils.allocatedMemory();
            if (allocatedMemory < min) min = allocatedMemory;
            if (allocatedMemory > max) max = allocatedMemory;

            n += 1;
            sum1 += allocatedMemory;
            sum2 += allocatedMemory*allocatedMemory;
            timestamp = now;
        }

        public float mean() {
            return sum1/n;
        }
        public float delta() { return max-min; }

        public String getMax() {
            return MemoryUtils.format(max);
        }

        public String getMin() {
            return MemoryUtils.format(min);
        }

        public String getMean() {
            float mean = sum1/n;
            return MemoryUtils.format(mean);
        }

        public String getDelta() {
            return MemoryUtils.format(max-min);
        }

        public String getStandardDeviation() {
            float mean = sum1/n;
            float variance = (sum2 - 2*mean*sum1 + n*(mean*mean))/n;
            float sdev = (float)Math.sqrt(variance);
            return MemoryUtils.format(sdev);
        }
    }

    private static class AnalysisTime {

        private static long MAX_TIME = 5*60000;

        private final long[] maxTimes;
        private final long[] counts;
        private final List<Tuple3<String, String, String>> longestProcessedFiles;

        private long start;
        private long done;

        private long totalTime;
        private long filesCount;
        private long linesCount;

        // ------------------------------------------------------------------

        AnalysisTime() {
            this(MAX_TIME);
        }

        AnalysisTime(long timeLimit) {
            longestProcessedFiles = new ArrayList<>();

            if (timeLimit <= MAX_TIME) {
                maxTimes = new long[]{
                    10000,
                    30000,
                    60000,
                    MAX_TIME,
                    2*MAX_TIME
                    //Long.MAX_VALUE
                };
            }
            else {
                maxTimes = new long[]{
                    10000,
                    30000,
                    60000,
                    MAX_TIME,
                    timeLimit,
                    2*timeLimit
                    //Long.MAX_VALUE
                };
            }

            counts = new long[maxTimes.length];
        }

        // ------------------------------------------------------------------

        void start(long timestamp) {
            start = timestamp;
        }

        void done(long timestamp) {
            done = timestamp;
        }

        String start() {
            return TimeUtils.format(new Date(start));
        }

        String done() {
            return TimeUtils.format(new Date(done));
        }


        synchronized void add(Source source, String step, long delta) {
            File file = source.getFile();
            totalTime += delta;
            filesCount += 1;
            linesCount += source.getSourceInfo().codeLines;

            int at = at(delta);
            counts[at] += 1;

            if (delta >= MAX_TIME) {
                longestProcessedFiles.add(Tuple3.of(step, FileUtils.getAbsolutePath(file), TimeUtils.format(delta)));
            }
        }

        private int at(long delta) {
            for (int i=0; i<maxTimes.length; ++i)
                if (delta < maxTimes[i])
                    return i;
            return maxTimes.length-1;
        }

        // ------------------------------------------------------------------
        // Report
        // ------------------------------------------------------------------

        public String getAnalysisTime() {
            return TimeUtils.format(done - start);
        }

        public String getSequentialTime() {
            return TimeUtils.format(totalTime);
        }

        public String getSpeedup() {
            float analysisTime = done-start;
            return String.format("%.2f x", totalTime/analysisTime);
        }

        public String getMeanFileAnalysisTime() {
            // if (filesCount > 0)
            //     return TimeUtils.toIntervalMillis(totalTime/filesCount);
            // else
            //     return TimeUtils.toIntervalMillis(totalTime);
            if (linesCount > 0)
                return String.format("%.03f ms", ((float)totalTime)/((float)filesCount));
            else
                return String.format("%.03f ms", ((float)totalTime));
        }

        public String getMeanLineAnalysisTime() {
            if (linesCount > 0)
                return String.format("%.03f ms", ((float)totalTime)/((float)linesCount));
            else
                return String.format("%.03f ms", ((float)totalTime));
        }

        public List<Tuple3<String, String, String>> getLongestAnalyzedFiles() {
            return longestProcessedFiles;
        }

        public Map<String, Long> getAnalysisTimeCounts() {
            Map<String, Long> intervalCounts = new LinkedHashMap<>();

            for(int i=0; i<maxTimes.length; ++i) {
                String maxTime = TimeUtils.format(maxTimes[i], null);
                intervalCounts.put(maxTime, counts[i]);
            }
            return intervalCounts;
        }
    }

    // step -> exception -> sourceKey -> message
    private static class AnalysisAborts extends TreeMap<String, Map<String, Map<SourceKey, String>>> {

        AnalysisAborts() { }

        synchronized void add(String step, Source source, Throwable reason) {
            if (!super.containsKey(step))
                super.put(step, new TreeMap<>());
            String exceptionName = reason.getClass().getSimpleName();
            Map<String, Map<SourceKey, String>> exceptionsMap = super.get(step);
            if (!exceptionsMap.containsKey(exceptionName))
                exceptionsMap.put(exceptionName, new TreeMap<>());
            Map<SourceKey, String> abortedFiles = exceptionsMap.get(exceptionName);
            SourceKey key = new SourceKey(source);
            abortedFiles.put(key, reason.getMessage());
        }

    }

    // step -> symbol -> list[source]
    private static class UnsolvedSymbols extends TreeMap<String, Map<String, Set<String>>> {

        UnsolvedSymbols() { }

        synchronized boolean add(String step, Source source, String symbol) {
            boolean added = false;
            if (!super.containsKey(step))
                super.put(step, new TreeMap<>());

            Map<String, Set<String>> unsolved = super.get(step);
            if (!unsolved.containsKey(symbol)) {
                unsolved.put(symbol, new TreeSet<>());
                added = true;
            }
            unsolved.get(symbol).add(new SourceKey(source).toString());
            return added;
        }
    }

    // step -> sourceKey -> set[symbol]
    private static class DuplicatedMethods extends TreeMap<String, Map<SourceKey, Set<String>>> {

        DuplicatedMethods() { }

        synchronized boolean add(String step, Source source, String symbol) {
            if (!super.containsKey(step))
                super.put(step, new TreeMap<>());
            Map<SourceKey, Set<String>> sourcesProblems = super.get(step);
            SourceKey key = new SourceKey(source);
            if (!sourcesProblems.containsKey(key))
                sourcesProblems.put(key, new TreeSet<>());
            Set<String> sourceProblems = sourcesProblems.get(key);
            return sourceProblems.add(symbol);
        }
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private static final String VERSION = "1.1";

    private Name projectName;
    private File projectHome;
    private String projectType;

    private ObjectStatus status;

    private Map<String, Object> projectCounts;
    private Parameters parameters = new Parameters();
    private MemoryStatistics memoryStatistics = new MemoryStatistics();
    private AnalysisTime analysisTime;
    private AnalysisAborts analysisAborts = new AnalysisAborts();
    private UnsolvedSymbols unsolvedSymbols = new UnsolvedSymbols();
    private DuplicatedMethods duplicatedMethods = new DuplicatedMethods();
    private Set<String> missingLibraries = new TreeSet<>();
    private Map<String, String> unparsedFiles = new TreeMap<>();

    // processed times:
    // 1)  1, 5, 10, >10 minutes

    @JsonIgnore
    private DependencyAnalyzer da;

    @JsonIgnore
    private AnalysisLimits limits;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    DependencyReport(DependencyAnalyzer da) {
        this.da = da;
        this.limits = da.getAnalysisLimits();
        this.analysisTime = new AnalysisTime(this.limits.fileTime);
    }

    // ----------------------------------------------------------------------
    // Initialization
    // ----------------------------------------------------------------------

    @JsonIgnore
    public File getReportFile() {
        File splDirectory = da.getSPLDirectory();
        String refId = da.getRefId();
        String reportName = String.format("%s-dependency-analysis-report.json", refId);
        File reportFile = new File(splDirectory, reportName);
        return reportFile;
    }

    // ----------------------------------------------------------------------
    // Initialization
    // ----------------------------------------------------------------------

    public void setParameters(Parameters params) {
        this.parameters.add(params);
    }

    public void setProjectInfo(Project project) {
        this.projectName = project.getName();
        this.projectHome = project.getProjectHome();
        this.projectType = project.getProjectType();
        // this.info.put("name", project.getName().getFullName());
        // this.info.put("home", FileUtils.getAbsolutePath(project.getProjectHome()));
        // this.info.put("type", project.getProjectType());
    }

    public void setResourceCounts(Collection<Source> sources, Collection<Library> libraries, Collection<Library> rtlibraries, Collection<Module> modules) {
        this.projectCounts = MapUtils.asMap(
            "sources", sources.size(),
            "libraries", libraries.size(),
            "runtimeLibraries", rtlibraries.size(),
            "modules", modules.size()
        );
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    void start(long timestamp) {
        analysisTime.start(timestamp);
    }

    void done(ObjectStatus status) {
        this.status = status;
        analysisTime.done(status.getTimestamp());
        // save();
    }

    // ----------------------------------------------------------------------
    // Compose report
    // ----------------------------------------------------------------------

    void allocatedMemory() {
        memoryStatistics.allocatedMemory();
    }

    void analysisTime(String step, Source source, long delta) {
        analysisTime.add(source, step, delta);
    }

    void analysisAborted(String step, Source source, Throwable t) {
        analysisAborts.add(step, source, t);
    }

    boolean unsolvedSymbol(String step, Source source, String symbol) {
        return unsolvedSymbols.add(step, source, symbol);
    }

    boolean duplicatedMethod(String step, Source source, String method) {
        return duplicatedMethods.add(step, source, method);
    }

    void invalidLibrary(String libraryName) {
        missingLibraries.add(libraryName);
    }

    boolean unparsedFile(String sourcePath, String message) {
        if (unparsedFiles.containsKey(sourcePath))
            return false;
        unparsedFiles.put(sourcePath, message);
        return true;
    }

    // ----------------------------------------------------------------------
    // Report
    // ----------------------------------------------------------------------

    public String getVersion() {
        return VERSION;
    }

    public Map<String, Object> getProject() {
        if (projectName == null)
            return Collections.emptyMap();

        return MapUtils.asMap(
            "fullname", projectName.getFullName(),
            "name", projectName.getName(),
            "repository", projectName.getParentName(),
            "projectHome", FileUtils.getAbsolutePath(projectHome),
            "projectType", projectType,
            "counts", projectCounts
        );
    }

    public Map<String, Object> getAnalysisConfiguration() {
        // wrapped to add an order to the keys
        Map<String, Object> configuration = new TreeMap<>(parameters);
        configuration.remove("name");
        configuration.remove("project");
        configuration.remove("repository");
        return configuration;
    }

    public Map<String, Object> getAnalysis() {
        if (this.status == null)
            return Collections.emptyMap();

        return MapUtils.asMap(
            "start", analysisTime.start(),
            "done", analysisTime.done(),
            "status", this.status.getStatus(),
            "reason", this.status.getReason()
        );
    }

    public AnalysisAborts getAnalysisAborts() {
        return analysisAborts;
    }

    public Map<String, Object> getAnalysisStatistics() {
        //return processTime;
        return MapUtils.asMap(
            "analysisTime", analysisTime.getAnalysisTime(),
            "sequentialTime", analysisTime.getSequentialTime(),
            "speedup", analysisTime.getSpeedup(),
            "processedFiles", analysisTime.filesCount,
            "processedLines", analysisTime.linesCount,
            "meanFileAnalysisTime", analysisTime.getMeanFileAnalysisTime(),
            "meanLineAnalysisTime", analysisTime.getMeanLineAnalysisTime(),
            "analysisTimeCounts", analysisTime.getAnalysisTimeCounts(),
            "longestAnalyzedFiles", analysisTime.getLongestAnalyzedFiles(),

            "minMemoryAllocated", memoryStatistics.getMin(),
            "maxMemoryAllocated", memoryStatistics.getMax(),
            "meanMemoryAllocated", memoryStatistics.getMean(),
            "sdevMemoryAllocated", memoryStatistics.getStandardDeviation(),
            "memoryAllocated", memoryStatistics.getDelta(),
            "meanMemoryForLine", MemoryUtils.format(memoryStatistics.delta()/analysisTime.linesCount)
        );
    }

    public Map<String, Object> getAnalysisErrors() {
        return MapUtils.asMap(
            "unsolvedSymbols", unsolvedSymbols,
            "duplicatedMethods", duplicatedMethods,
            "missingLibraries", missingLibraries,
            "unparsedFiles", unparsedFiles
        );
    }

    // ----------------------------------------------------------------------
    // Save report
    // ----------------------------------------------------------------------

    public void save(int keepReports) {
        bakupReports(keepReports);
        save();
    }

    private void bakupReports(int keepReports) {
        File reportFile = getReportFile();
        try {
            int lastReport = keepReports - 1;

            File lastReportFile = FileUtils.addExtension(reportFile, lastReport);
            FileUtils.deleteAll(lastReportFile);

            for (int i=lastReport-1; i>=0; --i) {
                File prevReportFile = FileUtils.addExtension(reportFile, i);
                File nextReportFile = FileUtils.addExtension(reportFile, i+1);
                if (prevReportFile.exists())
                    prevReportFile.renameTo(nextReportFile);
            }
        }
        catch (Throwable t) { }
    }

    private void save() {
        File reportFile = getReportFile();
        Logger logger = da.getLogger();
        try {
            JSONUtils.save(reportFile, this);
        } catch (IOException e) {
            logger.error(e, e);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
