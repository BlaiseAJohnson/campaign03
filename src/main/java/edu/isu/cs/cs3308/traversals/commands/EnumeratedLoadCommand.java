package edu.isu.cs.cs3308.traversals.commands;

import edu.isu.cs.cs3308.Datum;
import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.Tree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;

import java.util.LinkedList;
import java.util.List;

public class EnumeratedLoadCommand extends TraversalCommand<Datum> {

    private List<String[]> nodeList;

    public EnumeratedLoadCommand(List<String> nodeList) {

        List<String[]> nodeArrayList = new LinkedList<>();
        for (String node: nodeList) {
            nodeArrayList.add(node.split(":"));
        }
        this.nodeList = nodeArrayList;
    }

    @Override
    public void execute(Tree<Datum> tree, Node<Datum> node) {
        // Set up root if needed.
        if (tree.isRoot(node)) {
            for (String[] searchNode: nodeList) {
                int parent = Integer.parseInt(searchNode[0]);
                int selfNum = Integer.parseInt(searchNode[1]);
                String prompt = searchNode[3];

                if (parent == -1) {
                    node.setElement(new Datum(prompt, selfNum));
                    nodeList.remove(searchNode);
                }
            }
        }

        int nodeNumber = node.getElement().getNumber();

        // Add children of current node from list
        for (String[] searchNode: nodeList) {
            int parent = Integer.parseInt(searchNode[0]);
            int selfNum = Integer.parseInt(searchNode[1]);
            String position = searchNode[2];
            String prompt = searchNode[3];

            if (parent == nodeNumber) {
                if (position.equals("l")) {
                    ((LinkedBinaryTree<Datum>)tree).addLeft(node, new Datum(prompt, selfNum));
                    nodeList.remove(searchNode);
                }
                if (position.equals("r")) {
                    ((LinkedBinaryTree<Datum>)tree).addRight(node, new Datum(prompt, selfNum));
                    nodeList.remove(searchNode);
                }
            }
        }
    }
}
