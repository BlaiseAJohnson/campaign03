//TODO: Implement PreOrderTraversal

package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree.BinaryTreeNode;

import java.util.List;

public class PreOrderTraversal<E> extends DepthFirstTraversal<E> {

    PreOrderTraversal(Tree<E> tree) throws IllegalArgumentException {
        super(tree);
    }

    public void subtree(Node<E> parent, List<Node<E>> snapshot) throws IllegalArgumentException {
        if (parent == null || snapshot == null) throw new IllegalArgumentException();
        BinaryTreeNode<E> node = (BinaryTreeNode<E>) parent;

        snapshot.add(node);
        if (command != null) command.execute(tree, node);

        if (node.getLeft() != null) {
            subtree(node.getLeft(), snapshot);
        }

        if (node.getRight() != null) {
            subtree(node.getRight(), snapshot);
        }
    }
}
