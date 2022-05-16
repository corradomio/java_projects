package jext.graph;

/**
 * Class used to specify the
 *
 *  - input degree
 *  - output degree
 *  - degree (=input degree + output degree)
 *
 *  of a node.
 *  It is possible to specify the minimum and maximum degree
 *
 *  If the degree is specified as a string, the supported sintax is:
 *
 *      deg             exactly this degree
 *      min-max         min <= deg <= max
 *      min-            min <= deg
 *      -max                   deg <= max
 *
 * The flag 'isDegree' is used to specify if it is considered the global
 * node degree or separated input/output degrees
 */
public class NodeDegree {

    public static final long MIN_DEGREE =  0;
    public static final long MAX_DEGREE = -1;

    public static NodeDegree newNodeDegree(String inDegree, String outDegree) {
        long minInDegree  = parseMinDegree(inDegree);
        long maxInDegree  = parseMaxDegree(inDegree);
        long minOutDegree = parseMinDegree(outDegree);
        long maxOutDegree = parseMaxDegree(outDegree);

        return new NodeDegree(minInDegree, maxInDegree, minOutDegree, maxOutDegree);
    }

    private static long parseMinDegree(String degree) {
        int pos = degree.indexOf('-');

        // "" | "-deg"
        if (degree.length() == 0 || pos == 0)
            return MIN_DEGREE;

        // "deg-deg" | "deg-"
        if (pos != -1) degree = degree.substring(0, pos);

        // "deg"
        return Long.parseLong(degree);
    }

    private static long parseMaxDegree(String degree) {
        int pos = degree.indexOf('-');

        // "" | "deg-"
        if (degree.length() == 0 || pos == degree.length()-1)
            return MAX_DEGREE;

        // "deg-deg" | "-deg"
        if (pos != -1) degree = degree.substring(pos+1);

        // "deg"
        return Long.parseLong(degree);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public long minInDegree  = MIN_DEGREE;
    public long maxInDegree  = MAX_DEGREE;
    public long minOutDegree = MIN_DEGREE;
    public long maxOutDegree = MAX_DEGREE;
    public boolean isDegree = true;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NodeDegree() { }

    public NodeDegree(int degree) {
        this(Direction.Any, degree, degree);
    }

    public NodeDegree(int inDegree, int outDegree) {
        this(inDegree, inDegree, outDegree, outDegree);
    }

    public NodeDegree(long minInDegree, long maxInDegree, long minOutDegree, long maxOutDegree) {
        this.minInDegree  = minInDegree;
        this.maxInDegree  = maxInDegree;
        this.minOutDegree = minOutDegree;
        this.maxOutDegree = maxOutDegree;
        this.isDegree = false;
    }

    public NodeDegree(Direction direction, long degree) {
        this(direction, degree, degree);
    }

    public NodeDegree(Direction direction, long minDegree, long maxDegree) {
        switch(direction) {
            case Input:
                this.minInDegree = minDegree;
                this.maxInDegree = maxDegree;
                this.isDegree = false;
                break;
            case Output:
                this.minOutDegree = minDegree;
                this.maxOutDegree = maxDegree;
                this.isDegree = false;
                break;
            case Any:
                minInDegree = minDegree;
                maxInDegree = maxDegree;
                isDegree = true;
                break;
            default:
                minInDegree = minDegree;
                maxInDegree = maxDegree;
                minOutDegree = minDegree;
                maxOutDegree = maxDegree;
                this.isDegree = false;
                break;
        }
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NodeDegree asDegree() {
        this.isDegree = true;
        return this;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean hasInDegree() { return minInDegree  != MIN_DEGREE || maxInDegree  != MAX_DEGREE;  }
    public boolean hasOutDegree(){ return minOutDegree != MIN_DEGREE || maxOutDegree != MAX_DEGREE;  }
    public boolean isEmpty() { return !hasInDegree() && !hasOutDegree(); }

}
