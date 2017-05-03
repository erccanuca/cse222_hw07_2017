/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.*;


/**
 * Binary navigable class
 * @author ercan
 */
public class BinaryNavMap<K extends Comparable<K>,V> extends AbstractMap<K,V>
            implements NavigableMap<K, V>,Cloneable,Serializable{
    /**
     * BST field
     */
    private BinarySearchTree<K,V> bst;
    /**
     * Set entry field
     */
    private Set<Entry<K,V>> set;
    /**
     * Set key field
     */
    private Set<K> keyset;
    /**
     * For compare method
     */
    private SortedMap<K,V> treemap = new TreeMap<>();
    /**
     * Comparator initialition
     */
    private Comparator<? super K> bnmComparator = treemap.comparator();

    /**
     * Default constroctor
     */
    public BinaryNavMap() {
        this.bst = new BinarySearchTree<>();
        this.set = new HashSet<>();
        this.keyset = new HashSet<>();
    }

    /**
     * Remove method
     * @param key remove by key
     * @return removed entry value
     */
    @Override
    public V remove(Object key) {
        V value = bst.delete((K) key);
        Entry entry = new SimpleEntry<>(key,value);
        boolean removedEntry = set.remove(entry);
        boolean removedKey = keyset.remove((K)key);
        return value;
    }

    /**
     * Add entry
     * @param key key value
     * @param value value
     * @return added entry value
     */
    @Override
    public V put(K key, V value) {
        if(bst.add(key, value)){
            Entry<K, V> entry = new SimpleEntry<>(key,value);
            set.add(entry);
            keyset.add(key);
            return value; //To change body of generated methods, choose Tools | Templates.
        }
        return null;
    }

    /**
     * Get object
     * @param key value
     * @return value depend on key
     */
    @Override
    public V get(Object key) {
        return bst.find((K) key); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Is empty method
     * @return true if empty, else false
     */
    @Override
    public boolean isEmpty() {
        return bst.isEmpty(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Size method
     * @return size
     */
    @Override
    public int size() {
        return bst.size(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * tostring method
     * @return Info about the class
     */
    @Override
    public String toString() {
       String str= "BinaryNavMap:";
       str +="\n*************************************\n";
       str += bst.toString();
       str +="\n*************************************\n";
       return str; 
    }
    
    /**
     * This method return set map
     * @return set map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return set;
    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * strictly less than the given key, or {@code null} if there is
     * no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> lowerEntry(K key) {
        if(!containsKey(key)){
            throw new NullPointerException();
        }
       
        Iterator<K> itr = keyset.iterator();
        Iterator<K> itr2 = keyset.iterator();
        K lowerkey=null,currentKey=null;
        /* if one eleman set */
        if(keyset.size()==1)
            return new SimpleEntry<>(key,get(key));
        if(itr.hasNext())
            lowerkey = itr.next();
        if(itr2.hasNext())
            currentKey = itr2.next();
        
        if(currentKey != null && currentKey.equals(key)){
            return new SimpleEntry<>(currentKey,get(currentKey));
        }
        while(itr2.hasNext() && itr.hasNext()){
           // System.out.println("key : " + lowerkey + "\tvalue : " + get(lowerkey));
            currentKey = itr2.next();
            if(currentKey.equals(key)){
                 break;
            }
            else{
                lowerkey = itr.next();
            }
            
            
           
        }
        V value = get(lowerkey);
        return  new SimpleEntry<>(lowerkey,value);
    }

    /**
     * Returns the greatest key strictly less than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K lowerKey(K key) {
        Entry<K,V> lowerEntry = lowerEntry(key);
        return lowerEntry.getKey();
    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * less than or equal to the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> floorEntry(K key) {
        if(!containsKey(key)){
            throw new NullPointerException();
        }
        Iterator<K> itr = keyset.iterator();
        boolean have = false;
        K currentKey = null;
        while(itr.hasNext()){
            currentKey = itr.next();
            if(currentKey.equals(key)){
               have = true;
            }
        }
        if(have){
            V value = get(key);
            return new SimpleEntry<>(key,value);
        }
        
        return lowerEntry(key);
    }

    /**
     * Returns the greatest key less than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K floorKey(K key) {
        Entry<K,V> floorEntry = floorEntry(key);
        return floorEntry.getKey();
    }

    /**
     * Returns a key-value mapping associated with the least key
     * greater than or equal to the given key, or {@code null} if
     * there is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> ceilingEntry(K key) {
        if(!containsKey(key)){
            throw new NullPointerException();
        }
        Iterator<K> itr = keyset.iterator();
        boolean have = false;
        K currentKey = null;
        while(itr.hasNext()){
            currentKey = itr.next();
            if(currentKey.equals(key)){
               if(itr.hasNext()){
                   currentKey = itr.next();
                   have = true;
                   break;
               }
            }
        }
        if(have){
            V value = get(currentKey);
            return new SimpleEntry<>(currentKey,value);
        }
        V val = get(key);
        return new SimpleEntry<>(key,val);    
    }

    /**
     * Returns the least key greater than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K ceilingKey(K key) {
        Entry<K,V> ceilingEntry = ceilingEntry(key);
        return ceilingEntry.getKey();
    }

    /**
     * Returns a key-value mapping associated with the least key
     * strictly greater than the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> higherEntry(K key) {
        return ceilingEntry(key);
    }

    /**
     * Returns the least key strictly greater than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K higherKey(K key) {
        Entry<K,V> higherEntry = higherEntry(key);
        return higherEntry.getKey();
    }

    /**
     * Returns a key-value mapping associated with the least
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the least key,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> firstEntry() {
        if(keyset.isEmpty()){
            return null;
        }
        Iterator<K> itr = keyset.iterator();
      
        K currentKey = null;
        while(itr.hasNext()){
            currentKey = itr.next();      
        }
        V value = get(currentKey);
        return new SimpleEntry<>(currentKey,value);
    }

    /**
     * Returns a key-value mapping associated with the greatest
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the greatest key,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> lastEntry() {
        if(keyset.isEmpty()){
            return null;
        }
        Iterator<K> itr = keyset.iterator();
        
        K currentKey;
        K nextKey;
        K tempKey = null;
        while(itr.hasNext()){
            
            currentKey = itr.next();
            if(itr.hasNext())
                nextKey = itr.next();
            else
                break;
            
            if(currentKey.compareTo(nextKey)>1)
                tempKey = currentKey;
            else{
                tempKey = nextKey;
            }
        }
        V value = get(tempKey);
        return new SimpleEntry<>(tempKey,value);
       
    }

    /**
     * Removes and returns a key-value mapping associated with
     * the least key in this map, or {@code null} if the map is empty.
     *
     * @return the removed first entry of this map,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> pollFirstEntry() {
        Entry<K,V> pollFirstEntry = firstEntry();
        K key = pollFirstEntry.getKey();
        bst.delete(key);
        keyset.remove(key);
        set.remove(pollFirstEntry);
        return pollFirstEntry;
    }

    /**
     * Removes and returns a key-value mapping associated with
     * the greatest key in this map, or {@code null} if the map is empty.
     *
     * @return the removed last entry of this map,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> pollLastEntry() {
        Entry<K,V> pollLastEntry = lastEntry();
        K key = pollLastEntry.getKey();
        bst.delete(key);
        keyset.remove(key);
        set.remove(pollLastEntry);
        return pollLastEntry;
    }

    /**
     * Returns a reverse order view of the mappings contained in this map.
     * The descending map is backed by this map, so changes to the map are
     * reflected in the descending map, and vice-versa.  If either map is
     * modified while an iteration over a collection view of either map
     * is in progress (except through the iterator's own {@code remove}
     * operation), the results of the iteration are undefined.
     *
     * The returned map has an ordering equivalent to
     * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
     * The expression {@code m.descendingMap().descendingMap()} returns a
     * view of {@code m} essentially equivalent to {@code m}.
     *
     * @return a reverse order view of this map
     */
    @Override
    public NavigableMap<K, V> descendingMap() {
        NavigableMap<K,V> navmap = new TreeMap<>();
        Iterator<K> itr = keyset.iterator();
        V value;
        K key;
        while(itr.hasNext()){
            key = itr.next();
            value = bst.get(key);
            navmap.put(key, value);
        }

        return navmap.descendingMap();
    }

    /**
     * Returns a {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in ascending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a navigable set view of the keys in this map
     */
    @Override
    public NavigableSet<K> navigableKeySet() {
        NavigableSet<K> navigableKeySet = new TreeSet<>();
        Iterator<K> itr = keyset.iterator();
        K key;
        while(itr.hasNext()){
            key = itr.next();          
            navigableKeySet.add(key);
        }

        return navigableKeySet;
    }

    /**
     * Returns a reverse order {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in descending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a reverse order navigable set view of the keys in this map
     */
    @Override
    public NavigableSet<K> descendingKeySet() {
        NavigableSet<K> tempKeys = new TreeSet<>();
        Iterator<K> itr = keyset.iterator();
        
        // copy all keysets in temp
        while(itr.hasNext())
            tempKeys.add(itr.next());
       
        return tempKeys.descendingSet();
    }

    /**
     * Returns a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}.  If {@code fromKey} and
     * {@code toKey} are equal, the returned map is empty unless
     * {@code fromInclusive} and {@code toInclusive} are both true.  The
     * returned map is backed by this map, so changes in the returned map are
     * reflected in this map, and vice-versa.  The returned map supports all
     * optional map operations that this map supports.
     *
     * The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside of its range, or to construct a
     * submap either of whose endpoints lie outside its range.
     *
     * @param fromKey       low endpoint of the keys in the returned map
     * @param fromInclusive {@code true} if the low endpoint
     *                      is to be included in the returned view
     * @param toKey         high endpoint of the keys in the returned map
     * @param toInclusive   {@code true} if the high endpoint
     *                      is to be included in the returned view
     * @return a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}
     * @throws ClassCastException       if {@code fromKey} and {@code toKey}
     *                                  cannot be compared to one another using this map's comparator
     *                                  (or, if the map has no comparator, using natural ordering).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} or {@code toKey}
     *                                  cannot be compared to keys currently in the map.
     * @throws NullPointerException     if {@code fromKey} or {@code toKey}
     *                                  is null and this map does not permit null keys
     * @throws IllegalArgumentException if {@code fromKey} is greater than
     *                                  {@code toKey}; or if this map itself has a restricted
     *                                  range, and {@code fromKey} or {@code toKey} lies
     *                                  outside the bounds of the range
     */
    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        if(fromKey.compareTo(toKey)==0 || (fromInclusive && toInclusive && fromInclusive == false)){
            return null;
        }
        NavigableMap<K,V> NavigableMap = new TreeMap<>();
        V value1 = get(fromKey);
        NavigableMap.put(fromKey, value1);
        
        NavigableMap.putAll(tailMap(fromKey, fromInclusive).headMap(toKey,toInclusive));
        
        V value2 = get(toKey);
        NavigableMap.put(toKey, value2);
        return NavigableMap;
    }

    /**
     * Returns a view of the portion of this map whose keys are less than (or
     * equal to, if {@code inclusive} is true) {@code toKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     *
     * The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param toKey     high endpoint of the keys in the returned map
     * @param inclusive {@code true} if the high endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are less than
     * (or equal to, if {@code inclusive} is true) {@code toKey}
     * @throws ClassCastException       if {@code toKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code toKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code toKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code toKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code toKey} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        NavigableMap<K,V> NavigableMap = new TreeMap<>();
        Iterator<K> itr = keyset.iterator();
        boolean doIt = true;
        K key;
        V value;
        
        while(itr.hasNext()){
            key = itr.next();
            if(key.equals(toKey) && doIt)
            {
               value = get(toKey);
               NavigableMap.put(toKey, value);
               if(inclusive)
                    return NavigableMap;
            }
            else if(doIt){
                value = get(key);
                NavigableMap.put(key, value);
            }
        }
        
        if(inclusive)
            return NavigableMap.headMap(toKey, inclusive);
        else
            return new TreeMap<>();
    }

    /**
     * Returns a view of the portion of this map whose keys are greater than (or
     * equal to, if {@code inclusive} is true) {@code fromKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     *
     * The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param fromKey   low endpoint of the keys in the returned map
     * @param inclusive {@code true} if the low endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are greater than
     * (or equal to, if {@code inclusive} is true) {@code fromKey}
     * @throws ClassCastException       if {@code fromKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code fromKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code fromKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code fromKey} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        NavigableMap<K,V> NavigableMap = new TreeMap<>();
        Iterator<K> itr = keyset.iterator();
        boolean doIt = false;
        K key;
        V value;
        while(itr.hasNext()){
            key = itr.next();
            if(key.equals(fromKey))
            {
               value = get(key);
               NavigableMap.put(key, value);
               doIt =true;
            }
            else if(doIt){
                value = get(key);
                NavigableMap.put(key, value);
            }
        }
        
        if(inclusive)
            return NavigableMap;
        return new TreeMap<>();
    }

    /**
     * Returns the comparator used to order the keys in this map, or
     * {@code null} if this map uses the {@linkplain Comparable
     * natural ordering} of its keys.
     *
     * @return the comparator used to order the keys in this map,
     * or {@code null} if this map uses the natural ordering
     * of its keys
     */
    @Override
    public Comparator<? super K> comparator() {
        return bnmComparator;
    }

    /**
     * {@inheritDoc}
     *
     * Equivalent to {@code subMap(fromKey, true, toKey, false)}.
     *
     * @param fromKey start key
     * @param toKey end key
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
         SortedMap<K,V> sortedmap = new TreeMap<>();
        
        V value1 = get(fromKey);
        sortedmap.put(fromKey, value1);
        
        sortedmap.putAll(tailMap(fromKey).headMap(toKey));
        
        V value2 = get(toKey);
        sortedmap.put(toKey, value2);
        return sortedmap;
        
    }

    /**
     * {@inheritDoc}
     *
     * Equivalent to {@code headMap(toKey, false)}.
     *
     * @param toKey last key
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> headMap(K toKey) {
        SortedMap<K,V> sortedmap = new TreeMap<>();
        Iterator<K> itr = keyset.iterator();
        boolean doIt = true;
        K key;
        V value;
        if(!keyset.contains(toKey))
            throw new NullPointerException();
        while(itr.hasNext()){
            key = itr.next();
            if(key.equals(toKey) && doIt)
            {
               value = get(toKey);
               sortedmap.put(toKey, value);
               return sortedmap;
            }
            else if(doIt){
                value = get(key);
                sortedmap.put(key, value);
            }
        }
        
        return sortedmap;
    }

    /**
     * {@inheritDoc}
     *
     * Equivalent to {@code tailMap(fromKey, true)}.
     *
     * @param fromKey first key
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        SortedMap<K,V> sortedmap = new TreeMap<>();
        Iterator<K> itr = keyset.iterator();
        boolean doIt = false;
        K key;
        V value;
        while(itr.hasNext()){
            key = itr.next();
            if(key.equals(fromKey))
            {
               value = get(key);
               sortedmap.put(key, value);
               doIt =true;
            }
            else if(doIt){
                value = get(key);
                sortedmap.put(key, value);
            }
        }
        if(!doIt)
            throw new NullPointerException();
        
        return sortedmap.tailMap(fromKey);
    }

    /**
     * Returns the first (lowest) key currently in this map.
     *
     * @return the first (lowest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    @Override
    public K firstKey() {
        Entry<K,V> fisEntry = firstEntry();
        return fisEntry.getKey();
    }

    /**
     * Returns the last (highest) key currently in this map.
     *
     * @return the last (highest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    @Override
    public K lastKey() {
        Entry<K,V> lastEntry = lastEntry();
        return lastEntry.getKey();
    }
}
