/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ercan
 */
/** Hash table implementation using open addressing.
 *  @author Koffman and Wolfgang
 */

public class HashtableOpen < K, V >
    implements HashMap < K, V > {
  // Data Fields
  private Entry < K, V > [] table;
  private static final int START_CAPACITY = 101;
  private double LOAD_THRESHOLD = 0.75;
  private int numKeys;
  private int numDeletes;
  private final Entry < K, V > DELETED = new Entry <  > (null, null);

  // Constructor
  public HashtableOpen() {
   
    table = new Entry[START_CAPACITY];
  }

    @Override
    public V remove(K key) {
         V removed = null;
        int index = find(key);
        if(table[index] == null)
            return null;
        else{
            for(int i = 0; i < table.length; ++i){
                if(i == index){
                    removed = table[index].value;
                    table[index] = DELETED;
                    numDeletes++;
                    numKeys--;
                }
            }
            return removed;
        }
    }

  /** Contains key-value pairs for a hash table. */
   protected static class Entry < K, V > {

    /** The key */
    private K key;

    /** The value */
    private V value;

    /** Creates a new key-value pair.
        @param key The key
        @param value The value
     */
    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    /** Retrieves the key.
        @return The key
     */
    public K getKey() {
      return key;
    }

    /** Retrieves the value.
        @return The value
     */
    public V getValue() {
      return value;
    }

    /** Sets the value.
        @param val The new value
        @return The old value
     */
    public V setValue(V val) {
      V oldVal = value;
      value = val;
      return oldVal;
    }

        @Override
        public String toString() {
            return "\nEntry{" + "\tkey = " + key + ", value = " + value + "\t}";
        }
    
  }

  public int getTableLenght() {
    return table.length;
  }
   

  /** Returns the number of entries in the map */
  @Override
  public int size() {
    return numKeys;
  }

  /** Returns true if empty */
  @Override
  public boolean isEmpty() {
    return numKeys == 0;
  }

    public Entry<K, V>[] getTable() {
        return table;
    }

    @Override
    public String toString() {
        String str= "";
        for(int i = 0; i<START_CAPACITY ; ++i)
            if(table[i] != null)
             str += table[i];
                    
        return str;
    }


   
  /** Finds either the target key or the first empty slot in the
      search chain using linear probing.
      pre: The table is not full.
      @param key The key of the target object
      @return The position of the target or the first empty slot if
              the target is not in the table.
   */
  public int find(Object key) {
    // Calculate the starting index.
    int index = key.hashCode() % table.length;
      
    if (index < 0)
      index += table.length; // Make it positive.
    
      // Increment index until an empty slot is reached
      // or the key is found.
    while ( (table[index] != null)
           && (!key.equals(table[index].key))) {
      index++;
      // Check for wraparound.
      if (index >= table.length)
        index = 0; // Wrap around.
    }
    return index;
  }


  /** Method get for class HashtableOpen.
      @param key The key being sought
      @return the value associated with this key if found;
              otherwise, null
   */
  @Override
  public V get(Object key) {
    // Find the first table element that is empty
    // or the table element that contains the key.
    int index = find(key);
    // If the search is successful, return the value.
    if (table[index] != null)
      return table[index].value;
    return null; // key not found.
  }

  /** Method put for class HashtableOpen.
      post: This key-value pair is inserted in the
            table and numKeys is incremented. If the key is already
            in the table, its value is changed to the argument
            value and numKeys is not changed. If the LOAD_THRESHOLD
            is exceeded, the table is expanded.
      @param key The key of item being inserted
      @param value The value for this key
      @return Old value associated with this key if found;
              otherwise, null
   */
  @Override
  public V put(K key, V value) {
    // Find the first table element that is empty
    // or the table element that contains the key.
    int index = find(key);

    // If an empty element was found, insert new entry.
    if (table[index] == null) {
      table[index] = new Entry <  > (key, value);
      numKeys++;
      // Check whether rehash is needed.
      double loadFactor =
          (double) (numKeys + numDeletes) / table.length;
      if (loadFactor > LOAD_THRESHOLD)
        rehash();
      return null;
    }
    else{
        do{
            index++;
        }while(index < table.length && table[index] != null);

                
        table[index] = new Entry <  > (key, value);
        numKeys++;
         // Check whether rehash is needed.
        double loadFactor =
            (double) (numKeys + numDeletes) / table.length;
        if (loadFactor > LOAD_THRESHOLD)
          rehash();
      
    }

    // assert: table element that contains the key was found.
    // Replace value for this key.
    V oldVal = table[index].value;
    table[index].value = value;
    return oldVal;
  }

  /** Expands table size when loadFactor exceeds LOAD_THRESHOLD
      post: The size of table is doubled and is an odd integer.
            Each nondeleted entry from the original table is
            reinserted into the expanded table.
            The value of numKeys is reset to the number of items
            actually inserted; numDeletes is reset to 0.
   */
  public void rehash() {
    // Save a reference to oldTable.
    Entry < K, V > [] oldTable = table;
    // Double capacity of this table.
    table = new Entry[2 * oldTable.length + 1];

    // Reinsert all items in oldTable into expanded table.
    numKeys = 0;
    numDeletes = 0;
    for (int i = 0; i < oldTable.length; i++) {
      if ( (oldTable[i] != null) && (oldTable[i] != DELETED)) {
        // Insert entry in expanded table
        put(oldTable[i].key, oldTable[i].value);
      }
    }
  }

/**** EXERCISE ****/
}
