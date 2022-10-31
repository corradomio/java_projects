package jext.graph.neo4j;

/*
              1                   2
    MATCH  (n {p1:v1, ...}) WHERE n.p1 op v1 AND ... RETURN n.p1
    CREATE (n {p1:v1, ...}) SET n.p1 = v1, ...
 */

import jext.graph.Direction;
import jext.graph.Param;
import jext.logging.Logger;
import jext.util.ArrayUtils;
import jext.util.Parameters;
import jext.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import static jext.graph.neo4j.Neo4JOnlineSession.AND_BLOCK;
import static jext.graph.neo4j.Neo4JOnlineSession.END_BLOCK;
import static jext.graph.neo4j.Neo4JOnlineSession.N;
import static jext.graph.neo4j.Neo4JOnlineSession.NONE;
import static jext.graph.neo4j.Neo4JOnlineSession.WHERE_BLOCK;
import static jext.graph.neo4j.Neo4JOnlineSession.IN_REVISION;


class CypherFormatter {

    private static final Logger logger = Logger.getLogger(Neo4JOnlineSession.class);

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------
    // cypher template:
    //
    //      MATCH (n:label {slot: $param, ...})
    //      WHERE n.slot        OP $param
    //         OR n.slot[index] OP $param
    //         OR sslot(n),     OP $param
    //      SET   n.slot = $param,
    //            n.slot = function(n.slot, index, $param),
    //            n.slot = function(n.slot, $param)
    //
    //  There are some 'special' (s)slots:
    //
    //      n.$label     ==  labels(n)[0]
    //      n.$degree    ==  apoc.node.degree(n)
    //      n.$outdegree ==  apoc.node.degree(n, '>')
    //      n.$indegree  ==  apoc.node.degree(n, '<')
    //
    //      n.$count
    //      e.$count
    //  .

    static String label(String type) {
        if (StringUtils.isEmpty(type))
            return NONE;
        else
            return String.format(":%s", type);
    }

    /**
     * Create the PROPERTIES block:
     *
     *      { param: $[prefix]param, ... }
     *
     * Note: this block has a different meaning if used ina MATCH or CREATE clause:
     *   - in MATCH  clause is a WHERE clause
     *   - in CREATE clause is a SET   clause
     *
     * However, we can limit the 'pblock' only to 'properties with simple values'
     */
    static String pblock(String prefix, Map<String,Object> params) {
        if (noParams(params))
            return NONE;

        StringBuilder sb = new StringBuilder();
        for(String param : params.keySet()) {
            Object value = params.get(param);

            if (isSpecial(param, value))
                continue;

            comma(sb);

            // { ... param: $nparam ...
            sb.append(param).append(":$").append(prefix).append(param);
        }

        return brackets(sb);
    }

    /**
     * Create a WHERE block
     *      WHERE n.param = $param AND ...
     * or
     *      AND  n.param = $param AND ...
     *
     * @param params parameters
     * @param and if to include the WHERE keyword or AND
     * @param pblock if to exclude simple (name, value)
     */
    static String wblock(String alias, Map<String,Object> params, boolean and, boolean pblock) {
        if (noParams(params))
            return NONE;

        StringBuilder sb = new StringBuilder();
        for (String param : new HashSet<>(params.keySet())) {
            String s, pname;
            Object value = params.get(param);

            // check if already used in 'pblock'
            if (pblock && !isSpecial(param, value))
                continue;

            // append "... AND "
            andor(sb, true);

            // strip 'name[...]' -> 'name'
            pname = Param.pnameOf(param);
            params.put(pname, value);

            // [param, null] -> "EXISTS(n.param)"
            if (isExists(value)) {
                s = String.format("EXISTS(%s.%s)", alias, param);
            }

            // ['revision', rev] -> "n.inRevision[rev]"
            else if (isRevision(param)) {
                s = revisionCondition(alias, param, value);
            }

            // ['$degree', deg]
            // ['$degree[edge]', deg]
            else if (isDegree(param)) {
                s = degreeCondition(alias, param, value);
            }

            else if (isLabel(param)) {
                s = String.format("labels(%1$s)[0] = $%1$s%2$s", alias, pname);
            }

            // ['$label', val] -> ...
            // else if (isSpecial(param)) {
            //     s = specialCondition(alias, param, value);
            // }

            // [param, value: a collection] -> "n.param IN $param"
            else if (isCollection(value)) {
                s = String.format("%1$s.%2$s IN $%1$s%3$s", alias, param, pname);
            }

            // [param, value]        -> 'name = $param'
            // [param[index], value] -> 'name[index] = $paramIndex'
            else {
                s = String.format("%1$s.%2$s = $%1$s%3$s", alias, param, pname);
            }

            sb.append(s);
        }

        return where(sb, and);
    }

    private static String revisionCondition(String alias, String param, Object value) {
        /*
            [revision, 0 | [0,1,...]] ->
                n.inRevision[0]
                   (n.inRevision[0] OR n.inRevision[1] ... )
         */
            // revs == null -> true
        if (value == null) {
            logger.errorf("Revision condition with null value");
            return "true";
        }

        // rev:integer|long -> inRevision[rev]
        if (value instanceof Number) {
            int rev = ((Number) value).intValue();
            return String.format("%s.%s[%d]", alias, IN_REVISION, rev);
        }

        // convert collection in int[]
        int[] revs ;
        if (value instanceof Collection)
            revs = ArrayUtils.asIntArray(value);
        else
            revs = (int[]) value;
        // end

        // revs == []
        if (revs.length == 0) {
            logger.errorf("Revision condition with empty array []");
            return "true";
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< revs.length; ++i) {
            andor(sb, false);
            sb.append(String.format("%s.%s[%d]", alias, IN_REVISION, revs[0]));
        }

        return parens(sb);
    }

    private static String degreeCondition(String alias, String param, Object value) {
        String s = "true";
        String edge = Param.keyOf(param);
        String pname = Param.pnameOf(param);

        // ["$degree", d] -> apoc.node.degree(n) = $_degree
        // ["$degree[e]", d]
        if (param.startsWith("$degree") && edge.isEmpty()) {
            s = String.format("apoc.node.degree(%1$s) = $%1$s%2$s", alias, pname);
        }

        else if (param.startsWith("$degree[")) {
            s = String.format("apoc.node.degree(%1$s,%3$s) = $%1$s%2$s", alias, pname, edge);
        }

        // ["$outdegree", d] -> apoc.node.degree(n,'>') = $_outdegree
        else if (param.startsWith("$outdegree")) {
            s = String.format("apoc.node.degree(%1$s, '%3$s>') = $%1$s%2$s", alias, pname, edge);
        }

        // ["$indegree", d] -> apoc.node.degree(n,'<') = $_indegree
        else if (param.startsWith("$indegree")) {
            s = String.format("apoc.node.degree(%1$s, '%3$s<') = $%1$s%2$s", alias, pname, edge);
        }

        return s;
    }

    /**
     * Create a SET block
     *
     *      SET n.param = $nparam, ...
     *
     * @param pblock specify if some parameters are already used in 'pblock'
     */
    static String sblock(String alias, Map<String,Object> params, boolean pblock) {
        if (noParams(params))
            return NONE;

        StringBuilder sb = new StringBuilder();
        for(String param : new HashSet<>(params.keySet())) {
            Object value = params.get(param);

            // check if already used in 'pblock'
            if (pblock && !isSpecial(param, value))
                continue;

            comma(sb);

            // [revision, rev] -> inRevision[rev] = true -> inRevision = [...true]
            if (isRevision(param)) {
                String index = params.get(param).toString();
                sb.append(String.format("%1$s.%3$s = apocx.coll.arraySet(%1$s.%3$s, $%1$s%2$s, true)",
                    alias, param, IN_REVISION));
            }

            // [$count, 1] -> n.count = arrayIncr(n.count, index)
            else if (isCount(param)) {
                sb.append(String.format("%1$s.count = apocx.coll.arrayIncr(%1$s.count, $%1$srevision)",
                    alias));
            }

            // name[!] -> appendDistinct(n.name, $pname)
            else if (isAppendDistinct(param)) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);

                sb.append(String.format("%1$s.%2$s = apocx.coll.appendDistinct(%1$s.%2$s, $%1$s%3$s)",
                    alias, name, pname));

                params.put(pname, params.get(param));
                params.remove(param);
            }

            // name[+] -> append(n.name, $pname)
            else if (isAppend(param)) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);

                sb.append(String.format("%1$s.%2$s = apocx.coll.append(%1$s.%2$s, $%1$s%3$s)",
                    alias, name, pname));

                params.put(pname, params.get(param));
                params.remove(param);
            }

            // name[index,!] -> appendDistinct2(n.name, index, $pname)
            else if (isAppendDistinct2(param)) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);
                int index = Param.indexOf(param, 1);

                sb.append(String.format("%1$s.%2$s = apocx.coll.appendDistinct2(%1$s.%2$s, %4$d, $%1$s%3$s)",
                    alias, name, pname, index));

                params.put(pname, params.get(param));
                params.remove(param);
            }

            // name[index,+] -> append2(n.name, index, $pname)
            else if (isAppend2(param)) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);
                int index = Param.indexOf(param, 1);

                sb.append(String.format("%1$s.%2$s = apocx.coll.append2(%1$s.%2$s, %4$d, $%1$s%3$s)",
                    alias, name, pname, index));

                params.put(pname, params.get(param));
                params.remove(param);
            }

            // name[idx1,idx2] -> array2Set(n.name, idx1, idx2, $pname)
            else if (param.contains("[") && param.contains(",")) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);
                int index1 = Param.indexOf(param, 1);
                int index2 = Param.indexOf(param, 2);

                sb.append(String.format("%1$s.%2$s = apocx.coll.array2Set(%1$s.%2$s, %4$d, %5$d, $%1$s%3$s)",
                    alias, name, pname, index1, index2));

                params.put(pname, params.get(param));
                params.remove(param);
            }

            // name[index] -> arraySet(n.name, index, $pname)
            else if (param.contains("[")) {
                String name  = Param.nameOf(param);
                String pname = Param.pnameOf(param);
                int index = Param.indexOf(param, 0);

                sb.append(String.format("%1$s.%2$s = apocx.coll.arraySet(%1$s.%2$s, %4$d, $%1$s%3$s)",
                    alias, name, pname, index));

                params.put(pname, params.get(param));
                params.remove(param);
            }
            // name -> name = $pname
            else {
                sb.append(String.format("%1$s.%2$s = $%1$s%2$s", alias, param));
            }
        }

        return update(sb);
    }

    /**
     * Create the EDGE block
     *
     *       -[e:etype {...}]-      Any
     *       -[e:etype {...}]->     Output
     *      <-[e:etype {...}]-      Input
     *       -[e:type *]->          recursive
     */
    static String eblock(String alias,
                         String edgeType,
                         Direction direction, boolean recursive,
                         Map<String,Object> edgeProps) {
        String eblock;

        switch (direction) {
            case Input:
                eblock = "<-[%s%s%s %s]-";
                break;
            case Output:
                eblock = "-[%s%s%s %s]->";
                break;
            default:
                eblock = "-[%s%s%s %s]-";
                break;
        }

        String pblock = pblock(alias, edgeProps);
        String etype = edgeType != null ? ":" + edgeType : NONE;

        return String.format(eblock,
            alias,
            etype,
            recursive ? "*" : NONE,
            pblock);
    }

    /**
     * Create an ATTRIBUTES block
     *
     *      n.att1, n.att2, ...
     *
     */
    static String ablock(String alias, Collection<String> attributes) {
        if (attributes.isEmpty())
            return NONE;

        StringBuilder sb = new StringBuilder();
        for(String attr : attributes) {
            comma(sb);
            sb.append(alias).append(".").append(attr);
        }
        return sb.toString();
    }

    // ----------------------------------------------------------------------
    // ${and:  [alias]:[name]}
    // ${where:[alias]:[name]}

    /**
     * Create a USER WHERE block
     */
    static String ublock(String stmt, Parameters params, boolean and) {
        if (noParams(params))
            return NONE;

        // split ${where:[alias]:[name]} in the parts
        String prefix = and ? AND_BLOCK : WHERE_BLOCK;
        int bgn = stmt.indexOf(prefix) ;
        int end = stmt.indexOf(END_BLOCK, bgn);
        String name = stmt.substring(bgn+prefix.length(), end);
        String alias;

        // name | alias:name
        int sep = name.indexOf(':');
        if (sep == -1)
            alias = N;
        else {
            alias = name.substring(0, sep);
            name  = name.substring(sep+1);
        }

        //
        // params['name'] is ANOTHER map
        //
        Map<String,Object> aparams = (Map<String,Object>) params.getOrDefault(name, Collections.emptyMap());
        params.remove(name);

        String wblock = wblock(alias, aparams, and, false);

        // replace '${and...}' or '${where...}' with 'wblock'
        stmt = stmt.substring(0, bgn) + wblock + stmt.substring(end+END_BLOCK.length());
        params.add(alias, aparams);

        return stmt;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static boolean noParams(Map<String,Object> params) {
        return params == null || params.isEmpty();
    }

    private static void comma(StringBuilder sb) {
        if (sb.length() > 0)
            sb.append(",");
    }

    private static String brackets(StringBuilder sb) {
        if (sb.length() > 0)
            return sb.insert(0, "{").append("}").toString();
        else
            return NONE;
    }

    private static String parens(StringBuilder sb) {
        if (sb.length() > 0)
            return sb.insert(0, "(").append(")").toString();
        else
            return NONE;
    }

    private static void andor(StringBuilder sb, boolean and) {
        if (sb.length() > 0)
            sb.append(and ? " AND " : " OR ");
    }

    private static String where(StringBuilder sb, boolean and) {
        if (sb.length() > 0)
            return sb.insert(0, and ? " AND " : " WHERE ").toString();
        else
            return NONE;
    }

    private static String update(StringBuilder sb) {
        if (sb.length() > 0)
            return sb.insert(0, " SET ").toString();
        else
            return NONE;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static boolean isSpecial(String name, Object value) {
        if (name.startsWith("$") || name.contains("[") || name.equals("revision"))
            return true;
        if (value == null || value instanceof Collection || value.getClass().isArray())
            return true;
        if (name.contains("<") || name.contains(">") || name.contains("With") || name.contains("append"))
            return true;
        else
            return false;
    }

    private static boolean isExists(Object value) {
        return value == null;
    }

    private static boolean isRevision(String name) {
        return "revision".equals(name);
    }

    private static boolean isCount(String name) {
        return "$count".equals(name);
    }

    private static boolean isDegree(String name) {
        return name.startsWith("$degree") || name.startsWith("$indegree") || name.startsWith("$outdegree");
    }

    private static boolean isLabel(String name) {
        return name.equals("$label");
    }

    private static boolean isCollection(Object value) {
        return value instanceof Collection || value.getClass().isArray();
    }

    private static boolean isAppendDistinct(String name) {
        return name.endsWith("[!]");
    }

    private static boolean isAppend(String name) {
        return name.endsWith("[+]");
    }

    private static boolean isAppendDistinct2(String name) {
        return name.endsWith(",!]");
    }

    private static boolean isAppend2(String name) {
        return name.endsWith(",+]");
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
