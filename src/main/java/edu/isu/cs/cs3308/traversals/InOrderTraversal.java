//TODO: Implement InOrderTraversal

package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.traversals.commands.TraversalCommand;

import java.util.List;

public class InOrderTraversal<E> extends DepthFirstTraversal<E> {

    /**
     * Method which initiates the traversal of a tree from the root node. This
     * method returns the an iterable container of nodes representing a
     * resulting traveral of the tree.
     *
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    @Override
    public Iterable<Node<E>> traverse() {
        return null;
    }

    /**
     * Method which initiates the traversal of a tree from the root node. This
     * method returns the an iterable container of nodes representing a
     * resulting traveral of the tree.
     *
     * @param node Root of the subtree to start the traversal at.
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {
        return null;
    }

    public void subtree(Node<E> parent, List<Node<E>> snapshot) {

    }

    /**
     * Sets the executable command to the provided value.
     *
     * @param cmd The new executable command
     */
    @Override
    public void setCommand(TraversalCommand cmd) {

    }
}
