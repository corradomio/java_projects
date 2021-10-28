package org.hls.check;

public class Node {

    Node prev, next;

    Node() {
        next = this;
        prev = this;
    }
}
