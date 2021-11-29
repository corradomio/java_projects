package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.Problem;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.CommentsCollection;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.logging.Logger;

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
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static com.github.javaparser.ParseStart.COMPILATION_UNIT;
import static com.github.javaparser.ParserConfiguration.LanguageLevel.BLEEDING_EDGE;
import static jext.javaparser.Providers.provider;

public class JavaParserRootsTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final String name;
    private String cachePrefix;

    // maximum cache size
    // private long cacheSizeLimit = -1;
    private ParserConfiguration parserConfiguration;

    private final Set<File> sourceRoots;
    private Set<String> namespaces;
    private final Logger logger;

    private Cache<Path, ParseResult<CompilationUnit>> parsedFiles;
    private Cache<Path, List<CompilationUnit>> parsedDirectories;
    private Cache<String, SymbolReference<ResolvedReferenceTypeDeclaration>> foundTypes;
    private static final int CACHE_SIZE_UNSET = -1;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public JavaParserRootsTypeSolver() {
        this(DEFAULT, new ParserConfiguration().setLanguageLevel(BLEEDING_EDGE));
    }

    public JavaParserRootsTypeSolver(String name) {
        this(name, new ParserConfiguration().setLanguageLevel(BLEEDING_EDGE));
    }

    public JavaParserRootsTypeSolver(String name, ParserConfiguration parserConfiguration) {
        super(name);

        this.name = name;
        this.cachePrefix = name;
        this.sourceRoots = new HashSet<>();
        this.namespaces = new HashSet<>();
        this.parserConfiguration = parserConfiguration;
        this.logger = Logger.getLogger(JavaParserRootsTypeSolver.class, name);

        // this.parsedFiles = CacheManager.getCache(name +".parsedFiles");
        // this.parsedDirectories = CacheManager.getCache(name +".parsedDirectories");
        // this.foundTypes = CacheManager.getCache(name +".foundTypes");
    }

    // public JavaParserRootsTypeSolver(File srcDir) {
    //     this(srcDir.toPath());
    // }

    // public JavaParserRootsTypeSolver(String srcDir) {
    //     this(new File(srcDir));
    // }

    // public JavaParserRootsTypeSolver(Path srcDir) {
    //     this(srcDir, new ParserConfiguration().setLanguageLevel(BLEEDING_EDGE));
    // }

    // public JavaParserRootsTypeSolver(File srcDir, ParserConfiguration parserConfiguration) {
    //     this(srcDir.toPath(), parserConfiguration);
    // }

    // public JavaParserRootsTypeSolver(String srcDir, ParserConfiguration parserConfiguration) {
    //     this(new File(srcDir), parserConfiguration);
    // }

    // public JavaParserRootsTypeSolver(Path srcDir, ParserConfiguration parserConfiguration) {
    //     if (!Files.exists(srcDir) || !Files.isDirectory(srcDir)) {
    //         throw new IllegalStateException("SrcDir does not exist or is not a directory: " + srcDir);
    //     }
    //     this.srcDir = srcDir;
    //     this.javaParser = new JavaParser(parserConfiguration);
    //     parsedFiles = BuildCache(cacheSizeLimit);
    //     parsedDirectories = BuildCache(cacheSizeLimit);
    //     foundTypes = BuildCache(cacheSizeLimit);
    // }

    // ----------------------------------------------------------------------
    // Source roots
    // ----------------------------------------------------------------------

    public JavaParserRootsTypeSolver withCache() {
        return withCache(this.name);
    }

    public JavaParserRootsTypeSolver withCache(String cachePrefix) {
        this.cachePrefix = cachePrefix;
        createCaches();
        return this;
    }

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

        // return (Cache<TKey, TValue>) ((ManagedCache)cache).getInnerCache();
    }

    // private <TKey, TValue> Cache<TKey, TValue> BuildCache(long cacheSizeLimit) {
    //     CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().softValues();
    //     if (cacheSizeLimit != CACHE_SIZE_UNSET) {
    //         cacheBuilder.maximumSize(cacheSizeLimit);
    //     }
    //     return cacheBuilder.build();
    // }

    // ----------------------------------------------------------------------
    // Source roots
    // ----------------------------------------------------------------------

    public JavaParserRootsTypeSolver add(File sourceRoot) {
        this.sourceRoots.add(sourceRoot);
        return this;
    }

    public JavaParserRootsTypeSolver addAll(Collection<File> sourceRoots) {
        this.sourceRoots.addAll(sourceRoots);
        return this;
    }

    public ParseResult<CompilationUnit> parse(File srcFile) {
        return parse(srcFile.toPath());
    }

    // ----------------------------------------------------------------------
    // TypeSolver parent
    // ----------------------------------------------------------------------

    // @Override
    // public TypeSolver getParent() {
    //     return parent;
    // }
    //
    // @Override
    // public void setParent(TypeSolver parent) {
    //     Objects.requireNonNull(parent);
    //     if (this.parent != null) {
    //         throw new IllegalStateException("This TypeSolver already has a parent.");
    //     }
    //     if (parent == this) {
    //         throw new IllegalStateException("The parent of this TypeSolver cannot be itself.");
    //     }
    //     this.parent = parent;
    //
    //     createCaches();
    // }

    // ----------------------------------------------------------------------
    // TypeSolver parent
    // ----------------------------------------------------------------------

    private ParseResult<CompilationUnit> parse(Path srcFile) {
        try {
            return parsedFiles.getChecked(srcFile.toAbsolutePath(), () -> {
                // logger.debugf("... parse %s", srcFile.toAbsolutePath());
                // ParseResult<CompilationUnit> result =
                //     new JavaParser(parserConfiguration)
                //         .parse(COMPILATION_UNIT, provider(srcFile))
                //     ;
                // result.ifSuccessful(cu -> {
                //     cu.setStorage(srcFile);
                // });
                // return result;
                return parseSource(srcFile);
            });
        } catch (ExecutionException e) {
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
     * This seems to a ""transient error"".
     * To mitigate it, we retry to parse the file some other time
     */
    private ParseResult<CompilationUnit> parseSource(Path srcFile) throws IOException {
        logger.debugft("... parse %s", srcFile.toAbsolutePath());
        ParseResult<CompilationUnit> result = null;

        for(int count = 0; count < 3; ++count) {
            Thread.yield();

            result =
                new JavaParser(parserConfiguration)
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
     * Note that this parse only files directly contained in this directory.
     * It does not traverse recursively all children directory.
     */
    private List<CompilationUnit> parseDirectory(Path srcDirectory) {
        return parseDirectory(srcDirectory, false);
    }

    private List<CompilationUnit> parseDirectoryRecursively(Path srcDirectory) {
        return parseDirectory(srcDirectory, true);
    }

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
                                    units.addAll(parseDirectoryRecursively(file));
                                }
                            });
                    }
                }
                return units;
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    // ----------------------------------------------------------------------
    // TypeSolver
    // ----------------------------------------------------------------------

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
    // TypeSolver
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        try {
            return foundTypes.getChecked(name, () -> {
                SymbolReference<ResolvedReferenceTypeDeclaration>
                    solved = tryToSolveTypeUncached(name);
                if (solved.isSolved()) {
                    return SymbolReference.solved(solved.getCorrespondingDeclaration());
                }
                return solved;
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveTypeUncached(String name) {

        String[] nameElements = name.split("\\.");

        for (File sourceRoot : sourceRoots) {

            Path srcDir = sourceRoot.toPath();

            for (int i = nameElements.length; i > 0; i--) {
                StringBuilder filePath = new StringBuilder(srcDir.toAbsolutePath().toString());
                for (int j = 0; j < i; j++) {
                    filePath.append("/")
                        .append(nameElements[j]);
                }

                // if it is a directory, it is not necessary to continue
                if (new File(filePath.toString()).isDirectory())
                    break;

                filePath.append(".java");

                // if the file doesn't exist, try with less elements
                // (the class can be a inner class)
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
                        Optional<com.github.javaparser.ast.body.TypeDeclaration<?>> astTypeDeclaration = Navigator.findType(compilationUnit.get(), typeName.toString());
                        if (astTypeDeclaration.isPresent()) {
                            return SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(astTypeDeclaration.get()));
                        }
                    }
                }

                // If this is not possible we parse all files
                // We try just in the same package, for classes defined in a file not named as the class itself
                {
                    List<CompilationUnit> compilationUnits = parseDirectory(srcFile.getParent());
                    for (CompilationUnit compilationUnit : compilationUnits) {
                        Optional<com.github.javaparser.ast.body.TypeDeclaration<?>> astTypeDeclaration = Navigator.findType(compilationUnit, typeName.toString());
                        if (astTypeDeclaration.isPresent()) {
                            return SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(astTypeDeclaration.get()));
                        }
                    }
                }
            }
        }

        return UNSOLVED; //SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("JavaParserRootsTypeSolver{name=%s, parent=%s}", name, parent);
    }

}
