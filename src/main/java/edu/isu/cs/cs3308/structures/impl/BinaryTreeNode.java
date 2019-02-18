//TODO:Implement BinaryTreeNode

package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Node;

public class BinaryTreeNode<E> implements Node<E> {
    /**
     * @return The element contained in this node.
     */
    @Override
    public E getElement() {
        return null;
    }

    /**
     * Sets the new value of this node to the provided one. Thows an
     * IllegalArgumentException if the provided value is null.
     *
     * @param element New value to be contained in this node.
     */
    @Override
    public void setElement(E element) throws IllegalArgumentException {

    }

    /**
     * @return The parent node of this class. Can be null.
     */
    @Override
    public Node<E> getParent() {
        return null;
    }
}
