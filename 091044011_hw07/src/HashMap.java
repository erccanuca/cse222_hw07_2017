/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ercan
 */
public interface HashMap<K,V> {
    
    V get(K key);

    V put(K key, V value);

    V remove(K key);

    int size();

    boolean isEmpty();
    
}
