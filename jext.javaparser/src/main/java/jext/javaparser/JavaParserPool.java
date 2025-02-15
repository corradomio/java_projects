package jext.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ParserConfiguration.LanguageLevel;
import com.github.javaparser.Problem;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.CommentsCollection;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.java.JavaUtils;
import jext.util.logging.Logger;
import jext.util.FileUtils;
import jext.util.MapUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;

import static com.github.javaparser.ParserConfiguration.LanguageLevel.BLEEDING_EDGE;

/*
    This class is Copy & Paste plus some updates of the class:

        com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver

 */
public class JavaParserPool {

    // ----------------------------------------------------------------------
    // Parser Pool
    // ----------------------------------------------------------------------

    private static JavaParserPool pool = new JavaParserPool();

    public static JavaParserPool getPool() {
        return pool;
    }

    public static JavaParserPool newPool(String name) {
        return new JavaParserPool(name);
    }

    public static void removePools(String poolPrefix) {

    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final String DEFAULT = "default";

    private Logger logger;

    private String name;
    private String cachePrefix;

    private LanguageLevel languageLevel;

    // source directories of the parsed files
    private Set<Path> pathRoots;
    private Set<File> sourceRoots;
    private Set<String> namespaces;

    // caches
    private Cache<Path, ParseResult<CompilationUnit>> parsedFiles;
    private Cache<Path, List<CompilationUnit>> parsedDirectories;
    private Cache<String, Optional<TypeDeclaration<?>>> foundTypes;
    private static final int CACHE_SIZE_UNSET = -1;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private JavaParserPool() {
        this(DEFAULT /*, new ParserConfiguration().setLanguageLevel(BLEEDING_EDGE), CACHE_SIZE_UNSET */);
    }

    private JavaParserPool(String name /*, ParserConfiguration parserConfiguration, long cacheSizeLimit*/) {
        this.name = name;
        this.cachePrefix = name;
        this.pathRoots = new HashSet<>();
        this.sourceRoots = new HashSet<>();
        this.namespaces = new ConcurrentSkipListSet<>();

        // this.parserConfiguration = parserConfiguration;
        // this.parsedFiles = buildCache("parsedFiles", cacheSizeLimit);
        // this.parsedDirectories = buildCache("parsedDirectories", cacheSizeLimit);
        // this.foundTypes = buildCache("foundTypes", cacheSizeLimit);

        this.logger = Logger.getLogger(getClass(), name);
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    private static Map<String, ParserConfiguration.LanguageLevel> LANGUAGE_LEVELS =
        MapUtils.asMap(
            "1.7", LanguageLevel.JAVA_7,
            "1.8", LanguageLevel.JAVA_8,
            "7", LanguageLevel.JAVA_7,
            "8", LanguageLevel.JAVA_8,
            "9", LanguageLevel.JAVA_9,
            "10", LanguageLevel.JAVA_10,
            "11", LanguageLevel.JAVA_11,
            "12", LanguageLevel.JAVA_12,
            "13", LanguageLevel.JAVA_13,
            "14", LanguageLevel.JAVA_14,
            "15", LanguageLevel.JAVA_15,
            "16", LanguageLevel.JAVA_16,
            "17", LanguageLevel.JAVA_17
            //"18", LanguageLevel.JAVA_18,
        );

    public JavaParserPool withLanguageVersion(String languageVersion) {
        // languageVersion can be: 'jdk<Version>' or only '<version>'
        if (StringUtils.isEmpty(languageVersion))
            return this;

        languageVersion = JavaUtils.languageVersionOf(languageVersion);

        if (LANGUAGE_LEVELS.containsKey(languageVersion))
            this.languageLevel = LANGUAGE_LEVELS.get(languageVersion);
        else if ("17".compareTo(languageVersion) < 0)
            this.languageLevel = LanguageLevel.JAVA_17;
        else
            this.languageLevel = LanguageLevel.JAVA_11;

        return this;
    }

    public JavaParserPool withCache() {
        return withCache(this.cachePrefix);
    }

    public JavaParserPool withCache(String cachePrefix) {
        this.cachePrefix = cachePrefix;
        createCaches();
        return this;
    }

    // public JavaParserPool withParserConfiguration(ParserConfiguration parserConfiguration) {
    //     Objects.requireNonNull(parserConfiguration, "parserConfiguration can be not null");
    //     // this.parserConfiguration = parserConfiguration;
    //     return this;
    // }

    // ----------------------------------------------------------------------
    // Source roots
    // ----------------------------------------------------------------------

    public JavaParserPool withSourceRoots(Collection<File> sourceRoots) {
        return addAll(sourceRoots);
    }

    public JavaParserPool add(File sourceRoot) {
        this.sourceRoots.add(sourceRoot);
        this.pathRoots.add(sourceRoot.toPath());
        return this;
    }

    public JavaParserPool addAll(Collection<File> sourceRoots) {
        sourceRoots.forEach(this::add);
        return this;
    }

    public Set<File> getSourceRoots() {
        return sourceRoots;
    }

    public boolean isNamespace(String name) {
        if (namespaces.contains(name))
            return true;
        String relativePath = name.replace(".", "/");
        for(File sourceRoot : sourceRoots)
            if (isDirectory(name, new File(sourceRoot, relativePath))) {
                namespaces.add(name);
                return true;
            }
        return false;
    }

    // Problem: in Windows 'a/b/C' and 'a/b/c' are the SAME path
    // but, in Java, in general, 'C' is a class and 'c' is a namespace
    private boolean isDirectory(String name, File directory) {
        try {
            return directory.isDirectory()
                && directory.getCanonicalFile().getAbsolutePath()
                .replace("\\", "/")
                .endsWith(name.replace('.', '/'));
        } catch (IOException e) { }
        return false;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getName() {
        return this.name;
    }

    // ----------------------------------------------------------------------
    // Cache
    // ----------------------------------------------------------------------

    private void createCaches() {
        if (this.parsedFiles != null) return;
        this.parsedFiles = buildCache("parsedFiles", CACHE_SIZE_UNSET);
        this.parsedDirectories = buildCache("parsedDirectories", CACHE_SIZE_UNSET);
        this.foundTypes = buildCache("foundTypes", CACHE_SIZE_UNSET);
    }

    private <TKey, TValue> Cache<TKey, TValue> buildCache(String name, long cacheSizeLimit) {
        // CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().softValues();
        // if (cacheSizeLimit != CACHE_SIZE_UNSET) {
        //     cacheBuilder.maximumSize(cacheSizeLimit);
        // }
        // return cacheBuilder.build();

        return CacheManager.getCache(String.format("%s.%s", this.cachePrefix, name));
    }

    // ----------------------------------------------------------------------
    // Parsing
    // ----------------------------------------------------------------------

    public ParseResult<CompilationUnit> parse(File srcFile) {
        createCaches();
        return parse(srcFile.toPath());
    }

    // ----------------------------------------------------------------------

    private /*synchronized*/ ParseResult<CompilationUnit> parse(Path srcFile) {
        try {
            // try to use the cache if possible
            return parsedFiles.getChecked(srcFile, () -> parseSource(srcFile));
        }
        catch (ExecutionException e) {
            logger.errorf("Unable to parse %s: %s", srcFile, e);
            return parsedFile(new ParseResult<>(
                null,
                new ArrayList<Problem>() {{
                    add(new Problem("ExecutionException", TokenRange.INVALID, e));
                }},
                new CommentsCollection()),
                srcFile);
        }
    }

    /**
     * Sometime the parsing fails for a ""strange""
     *
     *      """Lexical Error ..."""
     *
     * This seems to be a ""transient error"".
     * To mitigate it, we retry to parse the file some other time
     *
     * 2021/11/07: this "Lexial error" seems to be a more depth problem.
     * Indeed, sometime the compilation DOESN'T fail BUT the AST generated is
     * not valid. We have observed this behaviour in a very simple project
     * ('check_modules') used for testing component analysis. This project is
     * composed by 20 types BUT, the dependency analysis analyzes 20 source files
     * BUT it creates ONLY 17/18 or 19 classes.
     *
     * 2021/11/09: RESOLVED!
     * The problem with "Lexical error" was related to the ParserConfiguration
     * object that contains objects used to PRE/POST-process the current stream.
     * It is necessary to have a DIFFERENT ParserConfiguration for EACH JavaParser
     * object.
     *
     */
    private ParseResult<CompilationUnit> parseSource(Path srcFile) throws IOException {
        logger.debugft("... parse %s", srcFile.toAbsolutePath());
        ParseResult<CompilationUnit> result = null;

        for(int count = 0; count < 3; ++count) {
            Thread.yield();

            String content = FileUtils.toString(srcFile.toFile());
            result = new JavaParser(parserConfiguration()).parse(content);

            result.ifSuccessful(cu -> cu.setStorage(srcFile));
            if (result.isSuccessful())
                break;

            ++count;
        }

        return parsedFile(result, srcFile);
    }

    public ParserConfiguration parserConfiguration() {
        ParserConfiguration configuration = new ParserConfiguration().setLanguageLevel(BLEEDING_EDGE);
        if (this.languageLevel != null)
            configuration.setLanguageLevel(this.languageLevel);
        return configuration;
    }

    /**
     * Note: that this parse only files directly contained in this directory.
     *       It does not traverse recursively all children directory.
     */
    private List<CompilationUnit> parseDirectory(Path srcDirectory, boolean recursively) {
        try {
            return parsedDirectories.getChecked(srcDirectory.toAbsolutePath(), () -> {
                List<CompilationUnit> units = new ArrayList<>();
                if (Files.exists(srcDirectory)) {
                    try (DirectoryStream<Path> srcDirectoryStream = Files.newDirectoryStream(srcDirectory)) {
                        srcDirectoryStream
                            .forEach(file -> {
                                if (file.getFileName().toString().toLowerCase().endsWith(".java")) {
                                    parse(file).getResult().ifPresent(units::add);
                                } else if (recursively && file.toFile().isDirectory()) {
                                    units.addAll(parseDirectory(file, true));
                                }
                            });
                    }
                }
                return units;
            });
        }
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    // ----------------------------------------------------------------------
    // Unparsed files
    // ----------------------------------------------------------------------

    private ParseResult<CompilationUnit> parsedFile(ParseResult<CompilationUnit> result, Path srcFile) {
        return parsedFile(result, srcFile.toFile());
    }

    private ParseResult<CompilationUnit> parsedFile(ParseResult<CompilationUnit> result, File srcFile) {
        // listeners.forEach(l -> l.parsed(result, srcFile));

        // if (!result.isSuccessful())
        //     logger.errorf("Unable to parse for problems %s\n  %s", JPUtils.getProblemMessages(result),
        //         FileUtils.getAbsolutePath(srcFile));
        return result;
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    public Optional<TypeDeclaration<?>> tryToSolveType(String name) {

        if(isNamespace(name))
            return Optional.empty();

        try {
            return foundTypes.getChecked(name, () -> tryToSolveTypeUncached(name));
        }
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<TypeDeclaration<?>> tryToSolveTypeUncached(String name) {
        String[] nameElements = name.split("\\.");

        for(Path srcDir : pathRoots)
            for (int i = nameElements.length; i > 0; i--) {
                StringBuilder filePath = new StringBuilder(srcDir.toAbsolutePath().toString());
                for (int j = 0; j < i; j++) {
                    filePath.append("/")
                        .append(nameElements[j]);
                }

                // check for namespaces
                if (isDirectory(name, new File(filePath.toString())))
                    break;

                // check for file ".java"
                filePath.append(".java");
                if (!new File(filePath.toString()).exists())
                    continue;

                // IT IS a ".java" file!
                // compose the new typename
                StringBuilder typeName = new StringBuilder();
                for (int j = i - 1; j < nameElements.length; j++) {
                    if (j != i - 1) {
                        typeName.append(".");
                    }
                    typeName.append(nameElements[j]);
                }

                // As an optimization we first try to look in the canonical position where we expect to find the file
                Path srcFile = Paths.get(filePath.toString());
                {
                    ParseResult<CompilationUnit> result = parse(srcFile);
                    Optional<CompilationUnit> compilationUnit = result.getResult();
                    if (compilationUnit.isPresent()) {
                        Optional<com.github.javaparser.ast.body.TypeDeclaration<?>> astTypeDeclaration
                            = Navigator.findType(compilationUnit.get(), typeName.toString());
                        if (astTypeDeclaration.isPresent()) {
                            //return SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(astTypeDeclaration.get()));
                            return astTypeDeclaration;
                        }
                    }
                }

                // If this is not possible we parse all files
                // We try just in the same package, for classes defined in a file not named as the class itself
                {
                    List<CompilationUnit> compilationUnits = parseDirectory(srcFile.getParent(), false);
                    for (CompilationUnit compilationUnit : compilationUnits) {
                        Optional<com.github.javaparser.ast.body.TypeDeclaration<?>> astTypeDeclaration
                            = Navigator.findType(compilationUnit, typeName.toString());
                        if (astTypeDeclaration.isPresent()) {
                            //return SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(astTypeDeclaration.get()));
                            return astTypeDeclaration;
                        }
                    }
                }
            }

        return Optional.empty();
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("JavaParserPool[%s]", name);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
