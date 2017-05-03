/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.NavigableMap;

/**
 * Main class
 * @author ercan
 */
public class Main
{
    public static void main(String args[]){
        final Boolean q1 = Q1Test();
        final Boolean q2 = Q2Test();
        if (q1 == q2 == Boolean.TRUE) {
            System.out.println("All test DONE!!! " );
            return;
        }
      
       
    }

    /**
     * Question 1 test
     * @return true if all passed.
     */
    public static Boolean Q1Test(){

        NavigableMap<String,String> turkey = new BinaryNavMap<String,String>();
        turkey.put("uskudar","istanbul");
        turkey.put("kadıkoy","istanbul");
        turkey.put("cekirge","bursa");
        turkey.put("gebze","kocaeli");
        turkey.put("niksar","tokat");
        turkey.put("kecıoren","ankara");
        turkey.put("aksaray","istanbul");
        turkey.put("foca","izmir");
        turkey.put("manavgat","antalya");
        turkey.put("kahta","adıyaman");
        turkey.put("biga","canakkale");

        System.out.println(turkey);
        System.out.println("turkey.lowerEntry(\"niksar\")\t:" + turkey.lowerEntry("niksar"));
        System.out.println("turkey.lowerKey(\"niksar\")\t:" + turkey.lowerKey("niksar"));
        System.out.println("turkey.floorEntry(\"niksar\")\t:" + turkey.floorEntry("niksar"));
        System.out.println("turkey.floorKey(\"niksar\")\t:" + turkey.floorKey("niksar"));
        System.out.println("turkey.ceilingEntry(\"niksar\")\t:" + turkey.ceilingEntry("niksar"));
        System.out.println("turkey.ceilingKey(\"niksar\")\t:" + turkey.ceilingKey("niksar"));
        System.out.println("turkey.higherEntry(\"niksar\")\t:" + turkey.higherEntry("niksar"));
        System.out.println("turkey.higherKey(\"niksar\")\t:" + turkey.higherKey("niksar"));
        System.out.println("turkey.firstEntry()\t\t:" + turkey.firstEntry());
        System.out.println("turkey.firstKey()\t\t:" + turkey.firstKey());
        System.out.println("turkey.lastEntry()\t\t:" + turkey.lastEntry());
        System.out.println("turkey.descendingKeySet()\t:" + turkey.descendingKeySet());
        System.out.println("turkey.descendingMap()\t\t:" + turkey.descendingMap());
        System.out.println("turkey.tailMap(\"niksar\")\t:" + turkey.tailMap("niksar"));
        System.out.println("turkey.headMap(\"niksar\")\t:" + turkey.headMap("niksar"));
        System.out.println("turkey.subMap(\"aksaray\", \"gebze\"):" + turkey.subMap("aksaray", "gebze"));
        System.out.println("turkey.navigableKeySet()\t:" + turkey.navigableKeySet());
        System.out.println("turkey.pollLastEntry()\t\t:" + turkey.pollLastEntry());
        System.out.println("turkey.pollFirstEntry()\t\t:" + turkey.pollFirstEntry());
        
        
        NavigableMap<String,String> m = turkey.subMap("aksaray",true,"gebze",true);
        System.out.println("turkey.subMap(\"uskudar\",true,\"gebze\",false):" + m);
        

        //you should write more test function to show your solution
        //your test must contain all methods to get full points!!!
        //you also may need to owerwrite some methods to provide BST RULES

        /* *some links to help you

           https://docs.oracle.com/javase/8/docs/api/java/util/NavigableMap.html
           https://docs.oracle.com/javase/8/docs/api/java/util/AbstractMap.html

        * */
        return Boolean.TRUE;

    }
    /**
     * Question 2 test
     * @return true if all passed.
     */
    public static Boolean Q2Test(){
        HashMap<String,String> turkey=new HashTableChaining<>();
        turkey.put("edremit","balikesir");
        System.out.println("turkey.put(\"edremit\",\"balikesir\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("edremit","van");
        System.out.println("turkey.put(\"edremit\",\"van\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("kemalpasa","bursa");
        System.out.println("turkey.put(\"kemalpasa\",\"bursa\");");
        System.out.println(turkey.toString()); 
        System.out.println("Size " + turkey.size());
        
        turkey.put("kemalpasa","izmir");
        System.out.println("turkey.put(\"kemalpasa\",\"izmir\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("ortakoy","istanbul");//we assume a district
        System.out.println("turkey.put(\"ortakoy\",\"istanbul\")");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("ortakoy","aksaray");
        System.out.println("turkey.put(\"ortakoy\",\"aksaray\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("ortakoy","corum");
        System.out.println("turkey.put(\"ortakoy\",\"corum\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("kecıoren","ankara");
        System.out.println("turkey.put(\"kecıoren\",\"ankara\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("pinarbasi","kastamonu");
        System.out.println("turkey.put(\"pinarbasi\",\"kastamonu\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("pinarbasi","kayseri");
        System.out.println("turkey.put(\"pinarbasi\",\"kayseri\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("eregli","konya");
        System.out.println("turkey.put(\"eregli\",\"konya\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("eregli","tekirdag");
        System.out.println("turkey.put(\"eregli\",\"tekirdag\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("eregli","zonguldak");
        System.out.println("turkey.put(\"eregli\",\"zonguldak\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("golbasi","adıyaman");
        System.out.println("turkey.put(\"golbasi\",\"adıyaman\");");
        System.out.println(turkey.toString()); 
        System.out.println("Size " + turkey.size());
        
        turkey.put("golbasi","ankara");
        System.out.println("turkey.put(\"golbasi\",\"ankara\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
        turkey.put("biga","canakkale");
        System.out.println("turkey.put(\"biga\",\"canakkale\");");
        System.out.println(turkey.toString());
        System.out.println("Size " + turkey.size());
        
       
        System.out.println("turkey.get(\"edremit\")\t:" +  turkey.get("edremit"));
        System.out.println("turkey.get(\"kemalpasa\")\t:" +  turkey.get("kemalpasa"));
        System.out.println("turkey.get(\"ortakoy\")\t:" +  turkey.get("ortakoy"));
        System.out.println("turkey.get(\"kecıoren\")\t:" +  turkey.get("kecıoren"));
        System.out.println("turkey.get(\"pinarbasi\")\t:" +  turkey.get("pinarbasi"));
        System.out.println("turkey.get(\"golbasi\")\t:" +  turkey.get("golbasi"));
        System.out.println("turkey.get(\"eregli\")\t:" +  turkey.get("eregli"));
        System.out.println("turkey.get(\"biga\")\t:" +  turkey.get("biga"));
        
        System.out.println("\nturkey.get(\"edremit\")\t:" +  turkey.get("edremit"));
        System.out.println("\nturkey.remove(\"edremit\")\t:" +  turkey.remove("edremit"));
        System.out.println("turkey.remove(\"edremit\")\t:" +  turkey.remove("edremit"));
        System.out.println("turkey.remove(\"edremit\")\t:" +  turkey.remove("edremit"));
        System.out.println("Size " + turkey.size());
        System.out.println("ToString " + turkey.toString());

        return Boolean.TRUE;
    }


}

