//TODO: Implement PostOrderTraversal

package edu.isu.cs.cs3308.traversals;

import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;

import java.util.List;

public class PostOrderTraversal<E> extends DepthFirstTraversal<E> {

    PostOrderTraversal(Tree<E> tree) {
        super(tree);
    }

    public void subtree(Node<E> parent, List<Node<E>> snapshot) {

    }
}
