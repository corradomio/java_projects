package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;
import java.util.Objects;

public abstract class BaseTypeSolver implements TypeSolver {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected Logger logger;
    protected TypeSolver parent;
    protected String name;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    // private BaseTypeSolver() {
    //     this.logger = Logger.getLogger(getClass(), name);
    // }

    protected BaseTypeSolver(String name) {
        this.name = name;
        this.logger = Logger.getLogger(getClass(), name);
    }

    // protected BaseTypeSolver(File file) {
    //     String name = FileUtils.getNameWithoutExt(file);
    //     this.name = name;
    //     this.logger = Logger.getLogger(getClass(), name);
    // }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public String getName() {
        return name;
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

}
