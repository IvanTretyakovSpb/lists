package com.tretyakov.redblacktree;

public interface Tree<T extends Comparable<T>> {

    Tree<T> insert(T data);

    void traverse();

    T getMax();

    T getMin();

    boolean isEmpty();

}
