package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree.BinaryTreeNode;

import java.util.List;

public class InOrderTraversal<E> extends DepthFirstTraversal<E> {


    InOrderTraversal(Tree<E> tree) throws IllegalArgumentException {
        super(tree);
    }

    public void subtree(Node<E> parent, List<Node<E>> snapshot) {
        if (parent == null || snapshot == null) throw new IllegalArgumentException();
        BinaryTreeNode<E> node = (BinaryTreeNode<E>) parent;

        if (node.getLeft() != null) subtree(node.getLeft(), snapshot);

        snapshot.add(node);
        if (command != null) command.execute(tree, node);

        if (node.getRight() != null) subtree(node.getRight(), snapshot);
    }
}
