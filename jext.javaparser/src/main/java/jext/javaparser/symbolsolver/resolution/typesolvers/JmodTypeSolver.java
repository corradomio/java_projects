package jext.javaparser.symbolsolver.resolution.typesolvers;
/*
 * Copyright (C) 2015-2016 Federico Tomassetti
 * Copyright (C) 2017-2020 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistClassDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import javassist.CtClass;
import jext.javassist.ClasspathElements;
import jext.javassist.JVMByteCodeAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class JmodTypeSolver extends BaseTypeSolver {

    private ClasspathElements classpathElements;

    public JmodTypeSolver(File pathToJar) throws IOException {
        super(pathToJar);

        addPathToJar(pathToJar);
    }

    private void addPathToJar(File pathToJar) throws IOException {
        classpathElements = JVMByteCodeAnalyzer.getInstance().getClasspathElements(pathToJar);
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        // try {
            if (classpathElements.containsKey(name)) {
                ResolvedReferenceTypeDeclaration solved;
                if ("java.lang.Object".equals(name)) {
                    CtClass ctClazz = classpathElements.get(name);
                    solved = new JavassistClassDeclaration(ctClazz, getRoot()) {
                        @Override
                        public List<ResolvedReferenceType> getAncestors(boolean acceptIncompleteList) {
                            return Collections.emptyList();
                        }
                    };
                }
                else {
                    solved =
                        JavassistFactory.toTypeDeclaration(classpathElements.get(name), getRoot());
                }

                return SymbolReference.solved(solved);
            } else {
                return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
            }
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }
    }

    @Override
    public ResolvedReferenceTypeDeclaration solveType(String name) throws UnsolvedSymbolException {
        SymbolReference<ResolvedReferenceTypeDeclaration> ref = tryToSolveType(name);
        if (ref.isSolved()) {
            return ref.getCorrespondingDeclaration();
        } else {
            throw new UnsolvedSymbolException(name);
        }
    }

}
