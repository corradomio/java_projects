package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javassist.ClasspathElements;
import jext.javassist.Classpaths;
import jext.util.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JDKTypeSolver extends BaseTypeSolver {

    private Classpaths classpaths = new Classpaths();
    private File installDir;

    private Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>
        alreadySolved = new HashMap<>();

    public JDKTypeSolver(File installDir) {
        super(FileUtils.getNameWithoutExt(installDir));
        this.installDir = installDir;
    }

    @Override
    public synchronized SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        if (alreadySolved.containsKey(name))
            return alreadySolved.get(name);

        ClasspathElements cpe = classpaths.getClasspathElements(installDir);
        if (cpe.containsKey(name)) {
            SymbolReference<ResolvedReferenceTypeDeclaration> solved =
                SymbolReference.solved(JavassistFactory.toTypeDeclaration(cpe.get(name), getRoot()));
            alreadySolved.put(name, solved);
            return solved;
        }
        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }
}


// public class JDKTypeSolver extends CompositeTypeSolver {
//
//     //private Set<String> unsolvedSymbols = new ConcurrentHashSet<>();
//
//     public JDKTypeSolver(File installDir) {
//         super(installDir);
//
//         load(installDir);
//     }
//
//     public JDKTypeSolver(String jdk, List<File> felements) {
//         super(jdk);
//
//         addAll(felements);
//     }
//
//     @Override
//     public TypeSolver getParent() {
//        return super.getParent();
//     }
//
//     @Override
//     public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
//         // if (unsolvedSymbols.contains(name))
//         //     return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
//
//         // SymbolReference<ResolvedReferenceTypeDeclaration> solved = super.tryToSolveType(name);
//
//         // if (!solved.isSolved())
//         //     unsolvedSymbols.add(name);
//
//         // return solved;
//
//         if (name.equals("java.util.Map"))
//             name = name;
//
//         return super.tryToSolveType(name);
//     }
//
//     // ----------------------------------------------------------------------
//     // Implementation
//     // ----------------------------------------------------------------------
//
//     private void addAll(List<File> felements) {
//         felements.stream()
//             .map(this::newJarTypeSolver)
//             .filter(Objects::nonNull)
//             .forEach(this::add);
//     }
//
//     private TypeSolver newJarTypeSolver(File jarFile) {
//         try {
//             String name = jarFile.getName().toLowerCase();
//             if (name.endsWith(".jar"))
//                 return new JarTypeSolver(jarFile);
//                 // return new com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver(jarFile);
//             else if(name.endsWith(".jmod"))
//                 return new JmodTypeSolver(jarFile);
//             else
//                 throw new IOException("Unsupported " + jarFile.toString());
//
//         }
//         catch (IOException e) {
//             logger.error(e, e);
//             return null;
//         }
//     }
//
//     private void load(File installDir) {
//         List<File> felements = new ArrayList<>();
//         name = installDir.getName();
//
//         try {
//             Files.walkFileTree(installDir.toPath(), new FilteredFileVisitor<Path>() {
//                 @Override
//                 public boolean filterFile(Path file) {
//                     String name = file.toString();
//                     return name.endsWith(".jar") ||  name.endsWith(".jmod");
//                 }
//
//                 @Override
//                 public void onVisitFile(Path file, BasicFileAttributes attrs) {
//                     felements.add(file.toFile());
//                 }
//             });
//         }
//         catch (IOException e) {
//             logger.error(e, e);
//         }
//
//         addAll(felements);
//     }
//
// }
