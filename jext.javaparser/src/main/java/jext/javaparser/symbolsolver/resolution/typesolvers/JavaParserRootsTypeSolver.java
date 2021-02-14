package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
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
import java.util.concurrent.ExecutionException;

import static com.github.javaparser.ParseStart.COMPILATION_UNIT;
import static com.github.javaparser.ParserConfiguration.LanguageLevel.BLEEDING_EDGE;
import static com.github.javaparser.Providers.provider;

public class JavaParserRootsTypeSolver implements TypeSolver {

    private String name;
    private Set<File> sourceRoots;
    private JavaParser javaParser;
    private Logger logger;

    private TypeSolver parent;

    private final Cache<Path, Optional<CompilationUnit>> parsedFiles;
    private final Cache<Path, List<CompilationUnit>> parsedDirectories;
    private final Cache<String, SymbolReference<ResolvedReferenceTypeDeclaration>> foundTypes;
    // private static final int CACHE_SIZE_UNSET = -1;

    public JavaParserRootsTypeSolver() {
        this("default", new ParserConfiguration().setLanguageLevel(BLEEDING_EDGE));
    }

    public JavaParserRootsTypeSolver(String name) {
        this(name, new ParserConfiguration().setLanguageLevel(BLEEDING_EDGE));
    }

    public JavaParserRootsTypeSolver(String name, ParserConfiguration parserConfiguration) {
        this.name = name;
        this.parsedFiles = CacheManager.getCache(name +".parsedFiles");
        this.parsedDirectories = CacheManager.getCache(name +".parsedDirectories");
        this.foundTypes = CacheManager.getCache(name +".foundTypes");
        this.sourceRoots = new HashSet<>();
        this.javaParser = new JavaParser(parserConfiguration);
        this.logger = Logger.getLogger(JavaParserRootsTypeSolver.class, name);
    }

    public void add(File sourceRoot) {
        this.sourceRoots.add(sourceRoot);
    }

    public void addAll(Collection<File> sourceRoots) {
        this.sourceRoots.addAll(sourceRoots);
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

    // private <TKey, TValue> Cache<TKey, TValue> BuildCache(long cacheSizeLimit) {
    //     CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().softValues();
    //     if (cacheSizeLimit != CACHE_SIZE_UNSET) {
    //         cacheBuilder.maximumSize(cacheSizeLimit);
    //     }
    //     return cacheBuilder.build();
    // }

    @Override
    public String toString() {
        return String.format("JavaParserRootsTypeSolver{name=%s, parent=%s}", name, parent);
    }

    @Override
    public TypeSolver getParent() {
        return parent;
    }

    @Override
    public void setParent(TypeSolver parent) {
        Objects.requireNonNull(parent);
        if (this.parent != null) {
            throw new IllegalStateException("This TypeSolver already has a parent.");
        }
        if (parent == this) {
            throw new IllegalStateException("The parent of this TypeSolver cannot be itself.");
        }
        this.parent = parent;
    }

    private Optional<CompilationUnit> parse(Path srcFile) {
        try {
            return parsedFiles.getChecked(srcFile.toAbsolutePath(), () -> {
                try {
                    if (!Files.exists(srcFile) || !Files.isRegularFile(srcFile)) {
                        return Optional.empty();
                    }
                    logger.debugf("... parse %s", srcFile.toAbsolutePath());
                    return javaParser.parse(COMPILATION_UNIT, provider(srcFile))
                        .getResult()
                        .map(cu -> cu.setStorage(srcFile));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Issue while parsing while type solving: " + srcFile.toAbsolutePath(), e);
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
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
                                    parse(file).ifPresent(units::add);
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

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        try {
            return foundTypes.getChecked(name, () -> {
                SymbolReference<ResolvedReferenceTypeDeclaration> result = tryToSolveTypeUncached(name);
                if (result.isSolved()) {
                    return SymbolReference.solved(result.getCorrespondingDeclaration());
                }
                return result;
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
                // (teh class can be a inner class)
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
                    Optional<CompilationUnit> compilationUnit = parse(srcFile);
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

        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }

}
