//TODO: Implement LinkedBinaryTree

package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.BinaryTree;
import edu.isu.cs.cs3308.structures.Node;

import java.util.LinkedList;
import java.util.List;

public class LinkedBinaryTree<E> extends BinarySearchTree<E> {

    public Node<E> root = null;
    public int size = 0;

    /**
     * Returns the left child of the provided node.
     *
     * @param p The parent node of whom the left child is desired.
     * @return The left child of the provided node, can be null if no such child
     * exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        return node.left;
    }

    /**
     * Returns the right child of the provided node.
     *
     * @param p The parent node of whom the right child is desired.
     * @return The right child of the provided node, can be null if no such
     * child exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        return node.right;
    }

    /**
     * Returns the sibling node of the provided node, if such a sibling exists.
     * That is, if the right node is provided the left node will be returned
     * from the same parent.
     *
     * @param p The node of whom a sibling is requested.
     * @return The sibling of the provided node, or null if no such sibling
     * exists.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public Node<E> sibling(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        BinaryTreeNode<E> parent = (BinaryTreeNode<E>) node.parent;

        if (parent.left == node) {
            return parent.right;
        }
        else return parent.left;
    }

    /**
     * Adds the provided element as a new node to the left side of the provided
     * node.
     *
     * @param p       The node to which the element is to be added as the left child.
     * @param element Element to be added
     * @return The newly created left child of the provided node
     * @throws IllegalArgumentException If the provided node is invalid, if the
     *                                  provided element is null, or if the provided node already has a left
     *                                  child.
     */
    @Override
    public Node<E> addLeft(Node<E> p, E element) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>) validate(p);
        BinaryTreeNode<E> newNode = createNode(element, p, null, null);
        node.left = newNode;

        return newNode;
    }

    /**
     * Adds the provided element as a new node to the right side of the provided
     * node.
     *
     * @param p       The node to which the element is to be added as the right child.
     * @param element Element to be added
     * @return The newly created right child of the provided node
     * @throws IllegalArgumentException If the provided node is invalid, if the
     *                                  provided element is null, or if the provided node already has a right
     *                                  child.
     */
    @Override
    public Node<E> addRight(Node<E> p, E element) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>) validate(p);
        BinaryTreeNode<E> newNode = createNode(element, p, null, null);
        node.right = newNode;

        return newNode;
    }

    /**
     * @return The root node of this tree or null if the Tree is empty.
     */
    @Override
    public Node<E> root() {
        return root;
    }

    /**
     * Sets the tree's root node to the provided item, by creating a new node
     * (unless the given item is the same as the current root's item). Note that
     * this must also reset the size of the tree to the correct value if the
     * current node is replaced.
     *
     * @param item New item for the root node.
     * @return The new root node.
     */
    @Override
    public Node<E> setRoot(E item) {
        if (root.getElement().equals(item)) return root;

        BinaryTreeNode<E> newRoot = createNode(item, null, null, null);
        root = newRoot;

        return newRoot;
    }

    /**
     * Returns the parent node of the node provided, or null if the node is also
     * the root of the tree.
     *
     * @param p Node whose parent is being requested.
     * @return The parent of the provided node, or null if the provided node is
     * the root.
     * @throws IllegalArgumentException If the node is invalid
     */
    @Override
    public Node<E> parent(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        return node.parent;
    }

    /**
     * Returns an iterable collection of the children attached to the provided
     * node.
     *
     * @param p The node whose children are requested.
     * @return An iterable collection of the children attached to the provided
     * node.
     * @throws IllegalArgumentException If the provided node is invalid
     */
    @Override
    public Iterable<Node<E>> children(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        LinkedList<Node<E>> children = new LinkedList<>();

        children.addLast(node.left);
        children.addLast(node.right);

        return children;
    }

    /**
     * Returns the number of children currently attached to the provided node.
     *
     * @param p Node whose number of children is requested.
     * @return The number of children attached to the provided node.
     * @throws IllegalArgumentException If the node is invalid.
     */
    @Override
    public int numChildren(Node<E> p) throws IllegalArgumentException {
        int numChildren = 0;
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);

        if (node.getLeft() != null) numChildren += 1;
        if (node.getRight() != null) numChildren += 1;

        return numChildren;
    }

    /**
     * Tests whether the node is an internal node or not. That is whether the
     * node has children.
     *
     * @param p The node to test.
     * @return True if the node is an internal node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid.
     */
    @Override
    public boolean isInternal(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);

        return numChildren(node) != 0;
    }

    /**
     * Tests whether the node is an external node of the tree. That is whether
     * the node has no children and thus is a leaf of the tree.
     *
     * @param p The node to test.
     * @return True if the node is a leaf node, false otherwise.
     * @throws IllegalArgumentException If the node is invalid
     */
    @Override
    public boolean isExternal(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);

        return numChildren(node) == 0;
    }

    /**
     * Tests whether this node is the root node of the tree. That is that the
     * provided node has children but not parent.
     *
     * @param p Node to test.
     * @return True if the node is the root of the tree.
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public boolean isRoot(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        return node.equals(root);
    }

    /**
     * Inserts the item into the tree under the provided node. If the provided
     * node is null the item becomes the new root of the tree, beware.
     *
     * @param item Item to be inserted into the tree.
     * @param p    The parent node of the tree, if null the item becomes the new
     *             root so be aware.
     * @return True if the item was able to be inserted, false otherwise (for
     * example the item was null)
     * @throws IllegalArgumentException if the provided parent node is invalid,
     *                                  or the provided value is null.
     */
    @Override
    public Node<E> insert(E item, Node<E> p) {
        return null;
    }

    /**
     * Removes the given item from the provided parent node.
     *
     * @param item Item to be removed from the list of children of the provided
     *             node.
     * @param p    Parent node.
     * @return true if the item was removed, false otherwise.
     * @throws IllegalArgumentException If the provided parent node is not valid
     *                                  or the value is null.
     */
    @Override
    public boolean remove(E item, Node<E> p) throws IllegalArgumentException {
        return false;
    }

    /**
     * @return The number of nodes currently in the tree.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if the tree contains no nodes (that is the root = null),
     * false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Updates the value of the node to the provided value. Throws an
     * IllegalArgumentException if the value is null, the node is null, or the
     * node is not in this Tree.
     *
     * @param node    Node whose value is to be updated.
     * @param element New value for the node.
     * @throws IllegalArgumentException If the provided node is invalid, or the
     *                                  element value is null.
     */
    @Override
    public E set(Node<E> node, E element) throws IllegalArgumentException {
        return null;
    }

    /**
     * Validates that the provided node is not null, is of a subtype of Node
     * supported by the implementing tree class, and is currently in this tree.
     * If these conditions are not met then an IllegalArgumentException is
     * thrown.
     *
     * @param p The node to be validated.
     * @return A node of the expected type specific to the implementing tree.
     * @throws IllegalArgumentException Thrown if the provided node is null, not
     *                                  in the current tree, or is not of a type supported by the current tree.
     */
    @Override
    public Node<E> validate(Node<E> p) throws IllegalArgumentException {
        return null;
    }

    /**
     * Calculates the depth of the given node in the current tree.
     *
     * @param node Node whose depth is to be calculated
     * @return Depth of the node in the tree.
     * @throws IllegalArgumentException If the provided node is invalid
     */
    @Override
    public int depth(Node<E> node) throws IllegalArgumentException {
        return 0;
    }

    /**
     * Recursively calculates the size of a subtree rooted at the provided node.
     *
     * @param node Node whose subtree size is to be calculated
     * @return Size of the subtree (excluding the root)
     * @throws IllegalArgumentException If the provided node is invalid.
     */
    @Override
    public int subTreeSize(Node<E> node) throws IllegalArgumentException {
        return 0;
    }

    /**
     * Checks if the provided node is the last child of it's parent node. Note
     * that the root node always returns true.
     *
     * @param node Node to check.
     * @return True if the node is the last child of it's parent node or is the
     * root, false otherwise.
     * @throws IllegalArgumentException If the provided node is not valid.
     */
    @Override
    public boolean isLastChild(Node<E> node) throws IllegalArgumentException {
        return false;
    }

    public BinaryTreeNode<E> createNode(E element, Node<E> parent, Node<E> left, Node<E> right) {
        return null;
    }


    public class BinaryTreeNode<T> implements Node<T> {
        T element = null;
        Node<T> parent = null;
        Node<T> left = null;
        Node<T> right = null;



        /**
         * @return The element contained in this node.
         */
        @Override
        public T getElement() {
            return null;
        }

        /**
         * Sets the new value of this node to the provided one. Thows an
         * IllegalArgumentException if the provided value is null.
         *
         * @param element New value to be contained in this node.
         */
        @Override
        public void setElement(T element) throws IllegalArgumentException {

        }

        /**
         * @return The parent node of this class. Can be null.
         */
        @Override
        public Node<T> getParent() {
            return null;
        }

        public Node<T> getLeft() {
            return null;
        }

        public Node<T> getRight() {
            return null;
        }
    }
}
