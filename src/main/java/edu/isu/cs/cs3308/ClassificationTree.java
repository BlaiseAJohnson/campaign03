//TODO: Implement ClassificationTree

package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree;
import edu.isu.cs.cs3308.structures.impl.LinkedBinaryTree.BinaryTreeNode;
import edu.isu.cs.cs3308.traversals.*;
import edu.isu.cs.cs3308.traversals.commands.EnumeratedLoadCommand;
import edu.isu.cs.cs3308.traversals.commands.EnumeratedSaveCommand;
import edu.isu.cs.cs3308.traversals.commands.EnumerationCommand;

import java.io.*;
import java.util.LinkedList;
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
        BinaryTreeNode<Datum> currentNode = (BinaryTreeNode<Datum>) tree.root;
        LinkedList<String> adjectives = new LinkedList<>();
        String answer;

        if (currentNode.getElement().getPrompt().equals("NONE")) {
            identifyFirstAnimal();
            return;
        }

        while (true) {
            System.out.println("Is this animal " + currentNode.getElement().getPrompt() + "? (y/n) > ");
            Scanner scanner = new Scanner(System.in);
            answer = scanner.next().toLowerCase();

            if (answer.equals("y")) {
                if (currentNode.getLeft() != null) {
                    adjectives.add(currentNode.getElement().getPrompt());
                    currentNode = currentNode.getLeft();
                }
                else break;
            }
            else if (answer.equals("n")) {
                if (currentNode.getRight() != null) {
                    adjectives.add("not " + currentNode.getElement().getPrompt());
                    currentNode = currentNode.getRight();
                }
                else break;
            }
            else {
                System.out.println("I didn't understand your response!");
            }
        }

        if (answer.equals("y")) {
            System.out.println("Good.");
        }
        else if (answer.equals("n")) {
            addNewAnimal(currentNode, adjectives);
        }
        else {
            System.out.println("Please try again.");
        }
    }

    private void identifyFirstAnimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("I don't know any animals!");
        System.out.println("What is the new animal? > ");
        String newAnimal = scanner.next().toLowerCase();

        System.out.println("What characteristic sets it apart?");
        String newCharacteristic = scanner.next().toLowerCase();

        tree.root.setElement(new Datum(newCharacteristic));
        tree.addLeft(tree.root, new Datum("a " + newAnimal));
    }

    private void addNewAnimal(BinaryTreeNode<Datum> node, LinkedList<String> adjectives) {
        Scanner scanner = new Scanner(System.in);
        Datum nodeDatum = node.getElement();
        String nodeAnimal = nodeDatum.getPrompt();

        System.out.print("I don't know any ");
        for (String adjective: adjectives) {
            System.out.print(adjective + " ");
        }
        System.out.print("animals that aren't " + nodeAnimal + "\n");

        System.out.println("What is the new animal? > ");
        String newAnimal = scanner.next().toLowerCase();

        System.out.println("What characteristic sets it apart?");
        String newCharacteristic = scanner.next();

        if (node.getLeft() == null) {
            tree.set(node, new Datum(newCharacteristic));
            tree.addLeft(node, new Datum(newAnimal));
            tree.addRight(node, nodeDatum);
        }
        else {
            tree.addRight(node, new Datum(newCharacteristic));
            tree.addLeft(node.getRight(), new Datum(newAnimal));
        }
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
        File in;
        PrintWriter writer;
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please specify a file to save to: >");
        String file = userIn.next();

        try {
            // Travel through the tree again to save it.
            in = new File(file);
            writer = new PrintWriter(in);
            traversal.setCommand(new EnumeratedSaveCommand(writer));
            traversal.traverse();
            writer.close();
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
        // Prompt for file
        BufferedReader reader;
        Scanner scanner = new Scanner(System.in);
        String line;

        System.out.println("Specify a file to load: >");
        String file = scanner.next();

        try {
            // Extract nodeList from file
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            LinkedList<String> nodeList = new LinkedList<>();

            while (line != null) {
                nodeList.add(line);
                line = reader.readLine();
            }

            // Create tree dynamically while traversing.
            LinkedBinaryTree<Datum> tree = new LinkedBinaryTree<>();
            BreadthFirstTraversal<Datum> traversal = new BreadthFirstTraversal<>(tree);
            EnumeratedLoadCommand command = new EnumeratedLoadCommand(nodeList);
            tree.setRoot(new Datum(""));
            traversal.setCommand(command);
            traversal.traverse();

            this.tree = tree;
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found. An empty tree will be loaded instead.");
            loadEmptyTree();
        } catch (IOException e) {
            System.out.println("File could not be read. An empty tree will be loaded instead.");
            loadEmptyTree();
        }
    }

    private void loadEmptyTree() {
        tree.setRoot(new Datum("NONE"));
    }
}
