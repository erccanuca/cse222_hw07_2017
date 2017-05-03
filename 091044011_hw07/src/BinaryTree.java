/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 * Class for a binary tree that stores type E objects.
 * @author Koffman and Wolfgang
 **/
public class BinaryTree<K,V> implements Serializable {

    /*<listing chapter="6" number="1">*/
    /** Class to encapsulate a tree node. */
    protected static class Node<K,V> implements Serializable {
        // Data Fields
       protected K key;             // sorted by key
       protected V val;           // associated value
       protected Node<K,V> left;
       protected Node<K,V> right;    // left and right subtrees
       protected int size;
        // Constructors
        /**
         * Construct a node with given data and no children.
         * @param key The data key
         * @param val The data to store in this node
         */
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            size = 0;
            left = null;
            right = null;
        }

        // Methods
        /**
         * Returns a string representation of the node.
         * @return A string representation of the data fields
         */
       @Override
        public String toString() {
            return key.toString()+ "->" + val.toString();
        }
    }
    /*</listing>*/
    // Data Field
    /** The root of the binary tree */
    protected Node<K,V> root;

    /** Construct an empty BinaryTree */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a BinaryTree with a specified root.
     * Should only be used by subclasses.
     * @param root The node that is the root of the tree.
     */
    protected BinaryTree(Node<K,V> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root,leftTree
     * as its left subtree and rightTree as its right subtree.
     * @param key root key
     * @param val root value
     * @param leftTree left tree
     * @param rightTree right tree
     */
    public BinaryTree(K key, V val, BinaryTree<K,V> leftTree,
            BinaryTree<K,V> rightTree) {
        root = new Node<>(key,val);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Return the left subtree.
     * @return The left subtree or null if either the root or
     * the left subtree is null
     */
    public BinaryTree<K,V> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right sub-tree
     * @return the right sub-tree or
     *         null if either the root or the
     *         right subtree is null.
     */
    public BinaryTree<K,V> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Return the data field of the root
     * @param key get data of key
     * @return the data field of the root value
     *         or null if the root is null
     */
    public V getData(K key) {
        if (root != null && key.equals(root.key)) {
            return root.val;
        } else {
            return null;
        }
    }

    /**
     * Determine whether this tree is a leaf.
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Perform a preorder traversal.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    private void preOrderTraverse(Node<K,V> node, int depth,
            StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    
    
 
    /*</listing>*/

// Insert solution to programming exercise 1, section 3, chapter 6 here

// Insert solution to programming exercise 2, section 3, chapter 6 here

// Insert solution to programming exercise 3, section 3, chapter 6 here
    

}
/*</listing>*/
