/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.NoSuchElementException;

/*<listing chapter="6" section="4">*/

/**
 * A class to represent a binary search tree.
 * @author Koffman and Wolfgang
 */
public class BinarySearchTree<K extends Comparable<K>, V>
         extends BinaryTree<K,V>{
    
    private int size = 0;
    // Data Fields
    /** Return value from the public add method. */
    protected boolean addReturn;
    /** Return value from the public delete method. */
    protected K deleteKey;
    protected V deleteReturn;
    
    

    // Methods
    /*<listing chapter="6" number="3">*/
    /**
     * Starter method find.
     * pre The target object must implement
     *      the Comparable interface.
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null
     */

    public V find(K target) {
        return find(root, target);
    }

    /**
     * Recursive find method.
     * @param localRoot The local subtree�s root
     * @param target The object being sought
     * @return The object, if found, otherwise null
     */
    private V find(Node<K,V> localRoot, K target) {
        if (localRoot == null) {
            return null;
        }

        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.key);
        if (compResult == 0) {
            return localRoot.val;
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }
    /*</listing>*/

    /*<listing chapter="6" number="4">*/
    /**
     * Starter method add.
     * pre The object to insert must implement the
     *      Comparable interface.
     * @param key The object being inserted key
     * @param item The object being inserted value
     * @return true if the object is inserted, false
     *         if the object already exists in the tree
     */

    public boolean add(K key,V item) {
        root = add(root, key, item);
       
        return addReturn;
    }

    /**
     * Recursive add method.
     * post The data field addReturn is set true if the item is added to
     *       the tree, false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root that now contains the
     *         inserted item
     */
    private Node<K,V> add(Node<K,V> localRoot, K key, V item) {
        if (localRoot == null) {
            // item is not in the tree � insert it.
            size++;
            addReturn = true;
            return new Node<>(key,item);
        } else if (key.compareTo(localRoot.key) == 0) {
            // item is equal to localRoot.data
            localRoot.val = item;
            //size++;
            addReturn = true;
            return localRoot;
        } else if (key.compareTo(localRoot.key) < 0) {
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left,key, item);
            return localRoot;
        } else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right,key, item);
            return localRoot;
        }
    }

    /**
     * Size method
     * @return return size
     */
    public int size(){
        return size(root);
    }
    private int size(Node<K,V> root){
        if(root == null)
            return 0;
        else if(root.left == null && root.right != null)
            return 1+size(root.right);
        else if(root.right == null && root.left != null)
            return 1+size(root.left);
        return 1+ size(root.left) + size(root.right);
    }
    
    /**
     * Returns true if this symbol table is empty.
     * @return true if this symbol table is empty,false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    /*</listing>*/

    /*<listing chapter="6" number="5">*/
    /**
     * Starter method delete.
     * post The object is not in the tree.
     * @param target The object to be deleted
     * @return The object deleted from the tree
     *         or null if the object was not in the tree
     * throws ClassCastException if target does not implement
     *         Comparable
     */
    public V delete(K target) {
        if(target == null)
            throw new IllegalArgumentException("called delete() with a null key");
        root = delete(root,target);
        return deleteReturn;
    }
   

    /**
     * Recursive delete method.
     * post The item is not in the tree;
     *       deleteReturn is equal to the deleted item
     *       as it was stored in the tree or null
     *       if the item was not found.
     * @param localRoot The root of the current subtree
     * @param item The item to be deleted
     * @return The modified local root that does not contain
     *         the item
     */
    private Node<K,V> delete(Node<K,V> localRoot, K key) {
        if (localRoot == null) 
            return null;

        int compare = key.compareTo(localRoot.key);
        if (compare < 0) 
            localRoot.left = delete(localRoot.left,key);
        else if (compare > 0)
            localRoot.right = delete(localRoot.right,key);
        else { 
            if(localRoot.right == null)
                return localRoot.left;
            if(localRoot.left == null)
                return localRoot.right;
            Node<K,V> temp = localRoot;
            localRoot = min(temp.right);
            localRoot.right = deleteMin(temp.right);
            localRoot.left = temp.left;
            
        }
        localRoot.size = size(localRoot.left) + size(localRoot.right) + 1;
        return localRoot;
    }
     /**
     * Removes the smallest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) 
            throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node<K,V> deleteMin(Node<K,V> min) {
        if (min.left == null) 
            return min.right;
        min.left = deleteMin(min.left);
        min.size = size(min.left) + size(min.right) + 1;
        return min;
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) 
            throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        
    }

    private Node<K,V> deleteMax(Node<K,V> max) {
        if (max.right == null) 
            return max.left;
        max.right = deleteMax(max.right);
        max.size = size(max.left) + size(max.right) + 1;
        return max;
    }
    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public K min() {
        if (isEmpty()) 
            throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    } 

    private Node<K,V> min(Node<K,V> min) { 
        if (min.left == null) 
            return min; 
        else                
            return min(min.left); 
    } 

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public K max() {
        if (isEmpty()) 
            throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    } 

    private Node<K,V> max(Node<K,V> max) {
        if (max.right == null)
            return max; 
        else                 
            return max(max.right); 
    } 
    /*</listing>*/

// Insert solution to programming exercise 1, section 4, chapter 6 here

    /*<listing chapter="6" number="6">*/
    /**
     * Find the node that is the
     * inorder predecessor and replace it
     * with its left child (if any).
     * post The inorder predecessor is removed from the tree.
     * @param parent The parent of possible inorder
     *        predecessor (ip)
     * @return The data in the ip
     */
    private K findLargestChild(Node<K,V> parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            V returnValue = parent.right.val;
            parent.right = parent.right.left;
            return parent.right.key;
        } else {
            return findLargestChild(parent.right);
        }
    }
    /***************************************************************************
    *  Search BST for given key
    ***************************************************************************/
    V get(K key) {
        Node<K,V> x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }
    /*</listing>*/
    

// Insert solution to programming exercise 2, section 4, chapter 6 here

// Insert solution to programming exercise 3, section 4, chapter 6 here
  /*  
    public static void main(String[] args) {
        
        BinarySearchTree<Integer> Btree = new BinarySearchTree<>();
        Btree.add(2);
        Btree.add(1);
        Btree.add(12);
        Btree.add(20);
        Btree.add(11);
        Btree.add(0);
        
        BinarySearchTree<Integer> Btree2 = new BinarySearchTree<Integer>();
        Btree2.add(2);
        Btree2.add(1);
        Btree2.add(12);
        Btree2.add(20);
        Btree2.add(11);
        Btree2.add(0);
        
        //System.out.println(Btree.find(21));
        System.out.println(Btree.toString());
        Btree.add(15);
        System.out.println(Btree.toString());
        Btree.delete(11);
        System.out.println(Btree.toString());
        
    }
    public  boolean isSameTree(BinaryTree<E> b1,BinaryTree<E> b2){
         
        if(b1==null && b2 == null){
            return true;
        }
        else if(b1.getData().equals(b2.getData())){
            return isSameTree(b1.getLeftSubtree(), b2.getLeftSubtree()) &&
                   isSameTree(b1.getRightSubtree(), b2.getRightSubtree());
         }   
         return false;
        
    }*/

    @Override
    public String toString() {
        return super.toString();
    }
}

/*</listing>*/
