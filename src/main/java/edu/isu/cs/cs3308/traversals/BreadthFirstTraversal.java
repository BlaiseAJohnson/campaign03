package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree.BinaryTreeNode;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;
import edu.isu.cs.cs3308.traversals.commands.TraversalCommand;

import java.util.LinkedList;

public class BreadthFirstTraversal<E> extends AbstractTraversal<E> {

    protected Tree<E> tree;
    protected TraversalCommand command = null;

    public BreadthFirstTraversal(Tree<E> tree) throws IllegalArgumentException {
        if (tree == null) throw new IllegalArgumentException();
        this.tree = tree;
    }

    /**
     * Method which initiates the traversal of a tree from the root node. This
     * method returns the an iterable container of nodes representing a
     * resulting traveral of the tree.
     *
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    @Override
    public Iterable<Node<E>> traverse() throws IllegalArgumentException {
        return traverseFrom(tree.root());
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
    public Iterable<Node<E>> traverseFrom(Node<E> node) throws IllegalArgumentException {
        if (node == null) throw new IllegalArgumentException();

        BinaryTreeNode<E> fromNode = (BinaryTreeNode<E>) node;
        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        LinkedList<Node<E>> list = new LinkedList<>();

        queue.offer(fromNode);

        while (!queue.isEmpty()) {
            fromNode = (BinaryTreeNode<E>) queue.poll();
            if (command != null) command.execute(tree, fromNode);
            list.add(fromNode);
            if (fromNode.getLeft() != null) queue.offer(fromNode.getLeft());
            if (fromNode.getRight() != null) queue.offer(fromNode.getRight());
        }

        return list;
    }

    /**
     * Sets the executable command to the provided value.
     *
     * @param cmd The new executable command
     */
    @Override
    public void setCommand(TraversalCommand cmd) {
        command = cmd;
    }
}
