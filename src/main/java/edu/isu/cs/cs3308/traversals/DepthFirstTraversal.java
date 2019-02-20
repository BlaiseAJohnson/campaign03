//TODO:Implement DepthFirstTraversal

package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.traversals.commands.TraversalCommand;

import java.util.LinkedList;
import java.util.List;

public abstract class DepthFirstTraversal<E> extends AbstractTraversal<E> {

    private Tree<E> tree;
    /**
     * Method which initiates the traversal of a tree from its root node. This
     * Method returns the an iterable container of nodes representing a
     * resulting traversal of the tree.
     *
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    @Override
    public Iterable<Node<E>> traverse() {
        return traverseFrom(tree.root());
    }

    /**
     * Method which initiates the traversal of a tree from the specified node. This
     * Method returns an iterable container of nodes representing the
     * resulting traversal of the tree.
     *
     * @param node Root of the subtree at which to start the traversal.
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {
        return subTreeTraverse(node);
    }

    /**
     * Method with traverses the subtree starting at the specified node.
     * Method returns an iterable container of nodes representing the
     * resulting traversal of the tree.
     *
     * @param node Root of the subtree at which to start the traversal.
     * @return An iterable container of nodes representing the traversal
     * of the tree.
     */
    public Iterable<Node<E>> subTreeTraverse(Node<E> node) {
        LinkedList<Node<E>> nodeList = new LinkedList<>();

        if (!tree.isEmpty()) {
            subtree(node, nodeList);
        }

        return nodeList;
    }


    public abstract void subtree(Node<E> parent, List<Node<E>> snapshot);

    /**
     * Sets the executable command to the provided value.
     *
     * @param cmd The new executable command
     */
    @Override
    public abstract void setCommand(TraversalCommand cmd);
}
