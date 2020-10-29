package org.hls.check;

import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.Expression;
import org.neo4j.cypherdsl.core.Functions;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.cypherdsl.core.SortItem;
import org.neo4j.cypherdsl.core.Statement;
import org.neo4j.cypherdsl.core.renderer.Renderer;

public class Main {

    private static Renderer cypherRenderer = Renderer.getDefaultRenderer();

    private static void dump(Statement statement){
        System.out.println(cypherRenderer.render(statement));
    }

    public static void main(String[] args) {
        Node n = Cypher.anyNode().named("n");
        SortItem si;
        ;

        dump(Cypher.match(n).returning(Functions.count(n)).build());
    }
}
