package org.hls.check.check_spring_neo4j;

import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.Expression;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.cypherdsl.core.Statement;
import org.neo4j.cypherdsl.core.renderer.Renderer;


public class Main {

    public static void main(String[] args) {
        Node a = Cypher.node("atm").named("a").withProperties("rg", Cypher.parameter("name"));

        // AST
        Statement stmt = Cypher.match(a).returning("a").build();

        System.out.println(Renderer.getDefaultRenderer().render(stmt));

    }
}
