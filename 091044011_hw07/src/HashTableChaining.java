/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ercan
 */
public class HashTableChaining<K, V> implements HashMap<K, V>{
    
    //private BinarySearchTree<K,V> bst = new BinarySearchTree<>();
    
    private HashtableOpen<K,V> [] table;
   
    /** The number of keys */
    private int numKeys;
    private int numDeletes;
    /** The capacity */
    private static final int CAPACITY = 203;

    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;
    private final HashtableOpen <K,V> DELETED = null;
    private final HashtableOpen.Entry < K, V > DELETEDENTRY = new HashtableOpen.Entry <  > (null, null);

    /**
     * Default constructor
     */
    public HashTableChaining() {
        this.table = new HashtableOpen[CAPACITY];

    }

    /**
     *  Get method
     * @param key object key
     * @return return key of value
     */
    @Override
    public V get(Object key) {
        
       int index = find(key);     
       int index2 = table[index].find(key);
       if(table[index].getTable()[index2] != null)
           return table[index].get(key);
       
        return null;
    }

    /**
     * Put entry
     * @param key key value
     * @param value value
     * @return return putted value
     */
    @Override
    public V put(K key, V value) {
    
    // Find the first table element that is empty
    // or the table element that contains the key.
    int index = find(key);

    // If an empty element was found, insert new entry.
    if (table[index] == null) {
      table[index] = new  HashtableOpen<>();
      numKeys++;
      // Check whether rehash is needed.
      double loadFactor =
      (double) (numKeys + numDeletes) / table.length;
      if (loadFactor > LOAD_THRESHOLD)
        rehash();
      }
        // assert: table element that contains the key was found.
        // Replace value for this key.
        V oldVal = table[index].put(key, value);
         
        int index2 = table[index].find(key);
        
        //System.out.println("index " +index +" index2 " +index2);
        //System.out.println("new key " +key +" Old key " + table[index].getTable()[index2].getKey());
        if(!key.equals(table[index].getTable()[index2].getKey())){
            //table[index].put(key, value);
            index2++;
            
            oldVal = table[index].put(key, value);
            
        }   
        return oldVal;
    }
    /**
     * Find object by key
     * @param key key value
     * @return return index of entry location
     */
    public int find(Object key){
        // Calculate the starting index.
        int index = key.hashCode() % table.length;
        if (index < 0)
          index += table.length; // Make it positive.
        
        // Increment index until an empty slot is reached
        // or the key is found.
        while ( (table[index] != null)
               && (!key.equals(key))) {
          index++;
          // Check for wraparound.
          if (index >= table.length)
            index = 0; // Wrap around.
        }
        return index;
    }

    /**
     * Size method
     * @return size
     */
    @Override
    public int size() {
        return numKeys;
    }

    /**
     * isEmpty method
     * @return return true if empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    /**
     * Remove method
     * @param key key value to remove
     * @return return removed value
     */
    @Override
    public V remove(K key) {
       int index = find(key);
       V oldVal = null;
       if(table[index] == null)
        return null;
       else{
           int index2 = table[index].find(key);
           if(table[index].getTable()[index2] != null){
               while(!table[index].getTable()[index2].getKey().equals(key)){
                   index2++;
                }
               oldVal = table[index].getTable()[index2].getValue();
               table[index].getTable()[index2] = DELETEDENTRY;
               numDeletes++;
               index2++;
               if(table[index].getTable()[index2] == null){
                   table[index] = DELETED;
                   numKeys--;
               }
           }
       }
       return oldVal;
    }

    /**
     * To string method
     * @return return info about this class
     */
    @Override
    public String toString() {
       String str= "Table Chaning:";
       str +="\n*************************************";
        for(int i = 0; i<CAPACITY ; ++i)
            if(table[i] != null)
             str += table[i]; 
        str +="\n*************************************";
        return str;
    }


     /** Expands table size when loadFactor exceeds LOAD_THRESHOLD
      post: The size of table is doubled and is an odd integer.
            Each nondeleted entry from the original table is
            reinserted into the expanded table.
            The value of numKeys is reset to the number of items
            actually inserted; numDeletes is reset to 0.
   */
    private void rehash() {
      // Save a reference to oldTable.
      HashtableOpen [] oldTable = table;
      // Double capacity of this table.
      table = new HashtableOpen[2 * oldTable.length + 1];

      // Reinsert all items in oldTable into expanded table.
      numKeys = 0;
      numDeletes = 0;
        for (HashtableOpen oldTable1 : oldTable) {
            if ((oldTable1 != null) && (oldTable1 != DELETED)) {
                // Insert entry in expanded table
                oldTable1.rehash();
            }
        }

    }
  
}
