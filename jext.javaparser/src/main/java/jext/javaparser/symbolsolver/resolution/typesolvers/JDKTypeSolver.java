package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.nio.file.FilteredFileVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class JDKTypeSolver extends CompositeTypeSolver {

    public JDKTypeSolver(File installDir) {
        super(installDir);

        load(installDir);
    }

    public JDKTypeSolver(String jdk, List<File> felements) {
        super(jdk);
        addAll(felements);
    }

    @Override   // DEBUG
    public TypeSolver getParent() {
       return super.getParent();
    }

    @Override   // DEBUG
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        return super.tryToSolveType(name);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void addAll(List<File> felements) {
        felements.stream()
            .map(this::newJarTypeSolver)
            .filter(Objects::nonNull)
            .forEach(this::add);
    }

    private TypeSolver newJarTypeSolver(File jarFile) {
        try {
            String name = jarFile.getName().toLowerCase();
            if (name.endsWith(".jar"))
                return new JarTypeSolver(jarFile);
                // return new com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver(jarFile);
            else if(name.endsWith(".jmod"))
                return new JmodTypeSolver(jarFile);
            else
                throw new IOException("Unsupported " + jarFile.toString());

        } catch (IOException e) {
            logger.error(e, e);
            return null;
        }
    }

    private void load(File installDir) {
        List<File> felements = new ArrayList<>();
        name = installDir.getName();

        try {
            Files.walkFileTree(installDir.toPath(), new FilteredFileVisitor<Path>() {
                @Override
                public boolean filterFile(Path file) {
                    String name = file.toString();
                    return name.endsWith(".jar") ||  name.endsWith(".jmod");
                }

                @Override
                public void onVisitFile(Path file, BasicFileAttributes attrs) {
                    felements.add(file.toFile());
                }
            });
        } catch (IOException e) {
            logger.error(e, e);
        }

        addAll(felements);
    }

}
