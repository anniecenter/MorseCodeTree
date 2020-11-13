import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable {

    /** Inner Node Class */
    protected static class Node<E> implements Serializable {
        /** The information stored in the Node */
        protected E data;
        /** Reference to the left child */
        protected Node<E> left;
        /** Reference to the right child */
        protected Node<E> right;

        // Constructors

        /**
         * Constructs a new node with no children using the given data.
         * @param data The data to be stored in this Node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        // Methods

        /**
         * Returns a string representation of this Node.
         * @return A string representation of the data in this node
         */
        public String toString() {
            return data.toString();
        }
    }

    // Data field
    /** The root of the binary tree */
    protected Node<E> root;

    // Constructors
    /** Default Constructor */
    public BinaryTree() {
        this.root = null;
    }

    /** Root Only Constructor */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /** Left-Right Constructor */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        this.root = new Node<E>(data);
        if(leftTree != null) {
            this.root.left = leftTree.root;
        }
        else {
            this.root.left = null;
        }
        if(rightTree != null) {
            this.root.right = rightTree.root;
        }
        else {
            this.root.right = null;
        }
    }

    // Methods
    /**
     * Returns the left subtree.
     * @return The left subtree or null
     */
    public BinaryTree<E> getLeftSubtree() {
        if(this.root != null && this.root.left != null) {
            return new BinaryTree<E>(this.root.left);
        }
        else {
            return new BinaryTree<E>(null);
        }
    }

    /**
     * Returns the right subtree.
     * @return The right subtree or null
     */
    public BinaryTree<E> getRightSubtree() {
        if(this.root != null && this.root.right != null) {
            return new BinaryTree<E>(this.root.right);
        }
        else {
            return new BinaryTree<E>(null);
        }
    }

    /**
     * Returns true if the current Node is a leaf.
     *
     * @return True if left and right are both null, false otherwise
     */
    public Boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * Returns a string representation of a BinaryTree.
     *
     * @return A string representation of a BinaryTee
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Performs a preorder traversal.
     *
     * @param node The local root
     * @param depth The level we are at on the tree
     * @param sb The string buffer to save the output
     */
    public void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for(int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if(node == null) {
            sb.append("null\n");
        }
        else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Constructs a binary tree by reading its data using a scanner.
     * @param scan A scanner used to read the binary tree
     * @return The binary tree from a file
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.next();
        if(data.equals("null")) {
            return null;
        }
        else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }

}
