package com.tretyakov.redblacktree;

import java.util.*;
import java.util.stream.Collectors;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public Tree<T> insert(T data) {
        Node<T> node = new Node<>(data);
        root = insert(root, node);
        recolorAndRotate(node);
        return this;
    }

    private Node<T> insert(Node<T> node, Node<T> nodeToInsert) {
        if (node == null) {
            return nodeToInsert;
        }
        if (nodeToInsert.getData().compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(node.getLeftChild(), nodeToInsert));
            node.getLeftChild().setParent(node);
        } else if (nodeToInsert.getData().compareTo(node.getData()) > 0) {
            node.setRightChild(insert(node.getRightChild(), nodeToInsert));
            node.getRightChild().setParent(node);
        }
        Collections.sort(new List<T>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<T> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T1> T1[] toArray(T1[] a) {
                return null;
            }

            @Override
            public boolean add(T t) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends T> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends T> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public T get(int index) {
                return null;
            }

            @Override
            public T set(int index, T element) {
                return null;
            }

            @Override
            public void add(int index, T element) {

            }

            @Override
            public T remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<T> listIterator() {
                return null;
            }

            @Override
            public ListIterator<T> listIterator(int index) {
                return null;
            }

            @Override
            public List<T> subList(int fromIndex, int toIndex) {
                return List.of();
            }
        });
        return node;
    }



    private void recolorAndRotate(Node<T> node) {
        Node<T> parent = node.getParent();
        if (node != root && parent.getColor() == RED) {
            Node<T> grandParent = node.getParent().getParent();
            Node<T> uncle = parent.isLeftChild() ?
                    grandParent.getRightChild() : grandParent.getLeftChild();
            if (uncle != null && uncle.getColor() == RED) { // Recoloring
                handleRecoloring(parent, uncle, grandParent);
            } else if (parent.isLeftChild()) { // Left-Left or Left-Right situation
                handleLeftSituations(node, parent, grandParent);
            } else if (!parent.isLeftChild()) { // Right-Right or Right-Left situation
                handleRightSituations(node, parent, grandParent);
            }
        }
        root.setColor(BLACK); // Color the root node black
    }

    private void handleRightSituations(Node<T> node, Node<T> parent, Node<T> grandParent) {
        if (node.isLeftChild()) {
            rotateRight(parent);
        }
        parent.flipColor();
        grandParent.flipColor();
        rotateLeft(grandParent);
        recolorAndRotate(node.isLeftChild() ? grandParent : parent);
    }

    private void handleLeftSituations(Node<T> node, Node<T> parent, Node<T> grandParent) {
        if (!node.isLeftChild()) {
            rotateLeft(parent);
        }
        parent.flipColor();
        grandParent.flipColor();
        rotateRight(grandParent);
        recolorAndRotate(node.isLeftChild() ? parent : grandParent);
    }

    private void handleRecoloring(Node<T> parent, Node<T> uncle, Node<T> grandParent) {
        uncle.flipColor();
        parent.flipColor();
        grandParent.flipColor();
        recolorAndRotate(grandParent);
    }

    private void rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild();
        node.setLeftChild(leftNode.getRightChild());
        if (node.getLeftChild() != null) {
            node.getLeftChild().setParent(node);
        }
        leftNode.setRightChild(node);
        leftNode.setParent(node.getParent());
        updateChildrenOfParentNode(node, leftNode);
        node.setParent(leftNode);
    }

    private void rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRightChild();
        node.setRightChild(rightNode.getLeftChild());
        if (node.getRightChild() != null) {
            node.getRightChild().setParent(node);
        }
        rightNode.setLeftChild(node);
        rightNode.setParent(node.getParent());
        updateChildrenOfParentNode(node, rightNode);
        node.setParent(rightNode);
    }

    private void updateChildrenOfParentNode(Node<T> node, Node<T> tempNode) {
        if (node.getParent() == null) {
            root = tempNode;
        } else if (node.isLeftChild()) {
            node.getParent().setLeftChild(tempNode);
        } else {
            node.getParent().setRightChild(tempNode);
        }
    }

    @Override
    public void traverse() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeftChild());
            System.out.println(node);
            traverseInOrder(node.getRightChild());
        }
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node.getData();
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

}
