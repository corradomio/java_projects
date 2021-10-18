package jext.jgrapht.alg.closure;

import jext.jgrapht.util.Utils;

import java.util.Set;

public class Closure<V> {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final int inDegree;
    private final int outDegree;
    private V vertex;
    private final Set<V> members;
    private boolean singletons;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    Closure(Set<V> members) {
        this.inDegree = 0;
        this.outDegree = 0;
        this.vertex = null;
        this.members = members;
        this.singletons = true;

        if (!members.isEmpty())
            this.vertex = members.iterator().next();
    }

    public Closure(V vertex, Set<V> members, int inDegree, int outDegree) {
        this.inDegree = inDegree;
        this.outDegree = outDegree;
        this.vertex = vertex;
        this.members = members;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public V vertex() {
        return vertex;
    }

    public int inDegree() {
        return inDegree;
    }

    public int outDegree() {
        return outDegree;
    }

    public Set<V> members() {
        return members;
    }

    public Set<V> difference(Closure<V> that) {
        return Utils.difference(members, that.members);
    }

    public int size() {
        return members.size();
    }

    /**
     * A singleton is a vertex with 0 degree
     */
    public boolean isSingletons() {
        return singletons;
    }

    /**
     * Check if the closure is empty.
     * Used ONLY when there are NO singletons available
     */
    public boolean isEmpty() {
        return members.isEmpty();
    }

    /**
     * Check if two closures are the same
     *
     * @param that other closure
     * @return if they are the same closure
     */
    public boolean isSameSet(Closure<V> that) {
        return Utils.isSameSet(this.members, that.members);
    }

    /**
     * Check if this closure is a superset than the other one
     *
     * @param that other closure
     * @return if this closure is superset that the other
     */
    public boolean isSuperset(Closure<V> that) {
        return Utils.isSuperset(this.members, that.members);
    }

    /**
     * Check if this closure can be removed.
     * Used when two closures are the same and it is necessary to decide
     * which closure to remove.
     * <p>
     * The rules used are:
     * <p>
     * 1) the reference vertex has a smaller input degree
     * 2) the reference vertex has a smaller output degree
     * 3) lexicographically (based on 'vertex.toString()')
     *
     * @param that other closure
     * @return if this closure can be removed
     */
    public boolean isRemovable(Closure<V> that) {
        int thisDegree, thatDegree;

        thisDegree = this.inDegree;
        thatDegree = that.inDegree;
        if (thisDegree < thatDegree)
            return true;
        if (thisDegree > thatDegree)
            return false;

        thisDegree = this.outDegree;
        thatDegree = that.outDegree;
        if (thisDegree < thatDegree)
            return true;
        if (thisDegree > thatDegree)
            return false;

        if (this.vertex.toString().compareTo(that.vertex.toString()) < 0)
            return true;
        else
            return false;
    }

}
