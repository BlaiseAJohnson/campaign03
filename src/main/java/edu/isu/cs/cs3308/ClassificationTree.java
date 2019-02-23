//TODO: Implement ClassificationTree

package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.BinaryTree;
import edu.isu.cs.cs3308.structures.Node;
import edu.isu.cs.cs3308.structures.impl.BinarySearchTree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree.BinaryTreeNode;
import edu.isu.cs.cs3308.traversals.*;
import edu.isu.cs.cs3308.traversals.commands.EnumeratedSaveCommand;
import edu.isu.cs.cs3308.traversals.commands.EnumerationCommand;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * A very simple classification tree example using a BinaryTree and console
 * input.
 *
 * @author Isaac Griffith
 */
public class ClassificationTree {

    private LinkedBinaryTree<Datum> tree;

    /**
     * Constructs a new Animal tree class which manages an underlying animal
     * tree
     */
    public ClassificationTree() {
        tree = new LinkedBinaryTree<>();
        load();
    }

    /**
     * Main method which controls the identification and tree management loop.
     */
    public void identify() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Saves a tree to a file.
     */
    public void save() {
        // Run through the tree to properly number the nodes.
        BreadthFirstTraversal<Datum> traversal = new BreadthFirstTraversal<>(tree);
        traversal.setCommand(new EnumerationCommand());
        traversal.traverse();

        // Open a file stream.
        FileOutputStream in;
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please specify a file to save to: >");
        String file = userIn.next();

        try {
            // Travel through the tree again to save it.
            in = new FileOutputStream(file);
            traversal.setCommand(new EnumeratedSaveCommand(new PrintWriter(in)));
            traversal.traverse();
        } catch (IllegalArgumentException e) {
            System.out.println("Something went wrong! The tree could not be saved.");
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found. The tree could not be saved.");
        }
    }

    /**
     * Loads a tree from the given file, if an exception occurs during file
     * operations, a hardcoded basic tree will be loaded instead.
     */
    public void load() {
        BufferedReader reader = null;
        Scanner scanner = new Scanner(System.in);
        String line;

        System.out.println("Specify a file to load: >");
        String file = scanner.next();

        try {
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            LinkedList<String> nodeList = new LinkedList<>();
            BinarySearchTree<Datum> tree = new LinkedBinaryTree<>();

            while (line != null) {
                nodeList.add(line);
                line = reader.readLine();
            }

            loadTree(tree, nodeList);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found. An empty tree will be loaded instead.");
            loadDefaultTree();
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File could not be read. An empty tree will be loaded instead.");
            loadDefaultTree();
            e.printStackTrace();
        }
    }

    private void loadDefaultTree() {
        tree.setRoot(null);
    }

    private void loadTree(BinarySearchTree<Datum> tree, List<String> nodeList) {
        for (String node: nodeList) {
            String[] nodeCode = node.split(":");


        }
    }
}
