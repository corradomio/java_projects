package org.hls.check;

import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Node {
    private String name;
    private List<Node> children = new ArrayList<Node>();

    public Node(String name) {
        this.name = name;
    }

    public Node add(Node child) {
        children.add(child);
        return this;
    }

    public void dump() {
        dump(0);
    }

    private void dump(int depth) {
        for(int d=0;d<depth;++d)
            System.out.print("  ");
        System.out.println(name);
        for (Node child : children)
            child.dump(depth+1);
    }
}

public class Main {

    public static void main(String[] args) {
        // Initialize a storage manager ("the database") with purely defaults.
        final EmbeddedStorageManager storageManager = EmbeddedStorage.start();

        Node root = new Node("root");
        storageManager.setRoot(root);
        storageManager.storeRoot();

        Node dbroot = (Node) storageManager.root();
        dbroot.dump();

        root.add(new Node("child1").add(new Node("child11"))).add(new Node("child2"));

        storageManager.store(root);
        dbroot = (Node) storageManager.root();
        dbroot.dump();

        storageManager.shutdown();
    }
}
