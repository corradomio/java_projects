package jext.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.Problem;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.CommentsCollection;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import jext.cache.CacheManager;
import jext.cache.Cache;
import jext.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;

import static com.github.javaparser.ParseStart.COMPILATION_UNIT;
import static com.github.javaparser.ParserConfiguration.LanguageLevel.BLEEDING_EDGE;
import static jext.javaparser.Providers.provider;

/*
    This class is Copy & Paste plus some updates of the class:

        com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver

 */
public class JavaParserPool {

    // ----------------------------------------------------------------------
    // Pool
    // ----------------------------------------------------------------------

    private static JavaParserPool pool = new JavaParserPool();

    public static JavaParserPool getPool() {
        return pool;
    }

    public static JavaParserPool newPool(String name) {
        return new JavaParserPool(name);
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final String DEFAULT = "default";

    private Logger logger;

    private String name;
    private String cachePrefix;

    // source directories of the parsed files
    private Set<Path> pathRoots;
    private Set<File> sourceRoots;
    private Set<String> namespaces;

    // parser configuration used for the parser
    private ParserConfiguration parserConfiguration;

    // maximum cache size
    // private long cacheSizeLimit = -1;

    // caches
    private Cache<Path, ParseResult<CompilationUnit>> parsedFiles;
    private Cache<Path, List<CompilationUnit>> parsedDirectories;
    private Cache<String, Optional<TypeDeclaration<?>>> foundTypes;
    private static final int CACHE_SIZE_UNSET = -1;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JavaParserPool() {
        this(DEFAULT, new ParserConfiguration().setLanguageLevel(BLEEDING_EDGE), CACHE_SIZE_UNSET);
    }

    public JavaParserPool(String name) {
        this(name, new ParserConfiguration().setLanguageLevel(BLEEDING_EDGE), CACHE_SIZE_UNSET);
    }

    /**
     * @param parserConfiguration is the configuration the solver should use when inspecting source code files.
     * @param cacheSizeLimit is an optional size limit to the internal caches used by this solver.
     *        Be advised that setting the size too low might lead to noticeable performance degradation.
     *        However, using a size limit is advised when solving symbols in large code sources. In such cases, internal
     *        caches might consume large amounts of heap space.
     */
    public JavaParserPool(String name, ParserConfiguration parserConfiguration, long cacheSizeLimit) {
        this.name = name;
        this.cachePrefix = name;
        this.pathRoots = new HashSet<>();
        this.sourceRoots = new HashSet<>();
        this.parserConfiguration = parserConfiguration;
        this.namespaces = new ConcurrentSkipListSet<>();
        // this.cacheSizeLimit = cacheSizeLimit;
        this.logger = Logger.getLogger(getClass(), name);

        // this.parsedFiles = buildCache("parsedFiles", cacheSizeLimit);
        // this.parsedDirectories = buildCache("parsedDirectories", cacheSizeLimit);
        // this.foundTypes = buildCache("foundTypes", cacheSizeLimit);
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public JavaParserPool withCache() {
        return withCache(this.name);
    }

    public JavaParserPool withCache(String cachePrefix) {
        this.cachePrefix = cachePrefix;
        createCaches();
        return this;
    }

    public JavaParserPool withParserConfiguration(ParserConfiguration parserConfiguration) {
        Objects.requireNonNull(parserConfiguration, "parserConfiguration can be not null");
        this.parserConfiguration = parserConfiguration;
        return this;
    }

    // ----------------------------------------------------------------------
    // Source roots
    // ----------------------------------------------------------------------

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

        String cacheName = String.format("%s.%s", this.cachePrefix, name);
        return CacheManager.getCache(cacheName);

        // return (Cache<TKey, TValue>) ((ManagedCache)cache).getInnerCache();
    }

    // public JavaParserPool resetCache() {
    //     parsedFiles = BuildCache(cacheSizeLimit);
    //     parsedDirectories = BuildCache(cacheSizeLimit);
    //     foundTypes = BuildCache(cacheSizeLimit);
    //     return this;
    // }

    // ----------------------------------------------------------------------
    // Parsing
    // ----------------------------------------------------------------------

    public ParseResult<CompilationUnit> parse(File srcFile) {
        createCaches();
        return parse(srcFile.toPath());
    }

    // ----------------------------------------------------------------------

    // WARNING:
    //  IT IS NOT POSSIBLE to compile source files in parallel ALSO creating
    //  different instances of "JavaParser", one for each source file.
    //
    private /*synchronized*/ ParseResult<CompilationUnit> parse(Path srcFile) {
        if (!Files.exists(srcFile) || !Files.isRegularFile(srcFile)) {
            return new ParseResult<>(
                null,
                new ArrayList<Problem>() {{
                    add(new Problem("FileNotFoundException", TokenRange.INVALID,
                        new FileNotFoundException(srcFile.toString())));
                }},
                new CommentsCollection());
        }

        try {
            return parsedFiles.getChecked(srcFile, () -> {
                // logger.debugft("... parse %s", srcFile);
                // ParseResult<CompilationUnit> result =
                //     new JavaParser(parserConfiguration)
                //         .parse(COMPILATION_UNIT, provider(srcFile));
                // result.ifSuccessful(cu -> {
                //     cu.setStorage(srcFile);
                //     cu.getPackageDeclaration().ifPresent(pdecl -> {
                //         addSourceRoot(srcFile, pdecl.getNameAsString());
                //     });
                // });
                // return result;
                return parseSource(srcFile);
            });
        }
        catch (ExecutionException e) {
            logger.errorf("Unable to parse %s: %s", srcFile, e);
            return new ParseResult<>(
                null,
                new ArrayList<Problem>() {{
                    add(new Problem("ExecutionException", TokenRange.INVALID, e));
                }},
                new CommentsCollection());
        }
    }

    /**
     * Sometime the parsing fails for a ""strange""
     *
     *      """Lexical Error ..."""
     *
     * This seems to be a ""transient error"".
     * To mitigate it, we retry to parse the file some other time
     */
    private ParseResult<CompilationUnit> parseSource(Path srcFile) throws IOException {
        logger.debugft("... parse %s", srcFile.toAbsolutePath());
        ParseResult<CompilationUnit> result = null;

        for(int count = 0; count < 3; ++count) {
            Thread.yield();

            result = new JavaParser(parserConfiguration)
                    .parse(COMPILATION_UNIT, provider(srcFile));
            if (result.isSuccessful()) {
                result.ifSuccessful(cu -> {
                    cu.setStorage(srcFile);
                });
                break;
            }
            ++count;
        }
        return result;
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
    // Resolve
    // ----------------------------------------------------------------------

    // @Override
    // public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
    //     try {
    //         return foundTypes.get(name, () -> {
    //             SymbolReference<ResolvedReferenceTypeDeclaration> result = tryToSolveTypeUncached(name);
    //             if (result.isSolved()) {
    //                 return SymbolReference.solved(result.getCorrespondingDeclaration());
    //             }
    //             return result;
    //         });
    //     }
    //     catch (ExecutionException e) {
    //         throw new RuntimeException(e);
    //     }
    // }
    //
    // private SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveTypeUncached(String name) {
    //     String[] nameElements = name.split("\\.");
    //
    //     for(Path srcDir : sourceRoots)
    //     for (int i = nameElements.length; i > 0; i--) {
    //         StringBuilder filePath = new StringBuilder(srcDir.toAbsolutePath().toString());
    //         for (int j = 0; j < i; j++) {
    //             filePath.append("/")
    //                 .append(nameElements[j]);
    //         }
    //         filePath.append(".java");
    //
    //         StringBuilder typeName = new StringBuilder();
    //         for (int j = i - 1; j < nameElements.length; j++) {
    //             if (j != i - 1) {
    //                 typeName.append(".");
    //             }
    //             typeName.append(nameElements[j]);
    //         }
    //
    //         // As an optimization we first try to look in the canonical position where we expect to find the file
    //         Path srcFile = Paths.get(filePath.toString());
    //         {
    //             Optional<CompilationUnit> compilationUnit = parse(srcFile);
    //             if (compilationUnit.isPresent()) {
    //                 Optional<com.github.javaparser.ast.body.TypeDeclaration<?>> astTypeDeclaration
    //                     = Navigator.findType(compilationUnit.get(), typeName.toString());
    //                 if (astTypeDeclaration.isPresent()) {
    //                     return SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(astTypeDeclaration.get()));
    //                 }
    //             }
    //         }
    //
    //         // If this is not possible we parse all files
    //         // We try just in the same package, for classes defined in a file not named as the class itself
    //         {
    //             List<CompilationUnit> compilationUnits = parseDirectory(srcFile.getParent());
    //             for (CompilationUnit compilationUnit : compilationUnits) {
    //                 Optional<com.github.javaparser.ast.body.TypeDeclaration<?>> astTypeDeclaration
    //                     = Navigator.findType(compilationUnit, typeName.toString());
    //                 if (astTypeDeclaration.isPresent()) {
    //                     return SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(astTypeDeclaration.get()));
    //                 }
    //             }
    //         }
    //     }
    //
    //     return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    // }

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
                if (isDirectory(name, new File(filePath.toString())))
                    break;

                filePath.append(".java");
                if (!new File(filePath.toString()).exists())
                    continue;

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
                    Optional<CompilationUnit> compilationUnit = parse(srcFile).getResult();
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
