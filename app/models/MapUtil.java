package models;

import java.util.*;

public class MapUtil {

    public static Set sortByValue(Map mapToSortByValue){

        Set<Map.Entry<String,Integer>> set = new TreeSet<Map.Entry<String,Integer>>(
                new Comparator<Map.Entry<String,Integer>>(){
                    @Override
                    public int compare(Map.Entry<String,Integer> obj1, Map.Entry<String,Integer> obj2) {
                        Integer val1 = obj1.getValue();
                        Integer val2 = obj2.getValue();
                        // DUPLICATE VALUE CASE
                        // If the values are equals, we can't return 0 because the 2 entries would be considered
                        // as equals and one of them would be deleted (because we use a set, no duplicate, remember!)
                        int compareValues = val1.compareTo(val2);
                        if ( compareValues == 0 ) {
                            String key1 = obj1.getKey();
                            String key2 = obj2.getKey();
                            int compareKeys = key1.compareTo(key2);
                            if ( compareKeys == 0 ) {
                                // what you return here will tell us if you keep REAL KEY-VALUE duplicates in your set
                                // if you want to, do whatever you want but do not return 0 (but don't break the comparator contract!)
                                return 0;
                            }
                            return compareKeys;
                        }
                        return compareValues;
                    }
                }
        );
        set.addAll(mapToSortByValue.entrySet());


        // OK NOW OUR SET IS SORTED COOL!!!!

        // And there's nothing more to do: the entries are sorted by value!
        for ( Map.Entry<String,Integer> entry : set ) {
//            System.out.println("Set entries: " + entry.getKey() + " -> " + entry.getValue());
        }


        // But if you add them to an hashmap
        Map<String,Integer> myMap = new HashMap<String,Integer>();
        // When iterating over the set the order is still good in the println...
        for ( Map.Entry<String,Integer> entry : set ) {
//            System.out.println("Added to result map entries: " + entry.getKey() + " " + entry.getValue());
            myMap.put(entry.getKey(), entry.getValue());
        }

        // But once they are in the hashmap, the order is not kept!
        for ( Integer value : myMap.values() ) {
//            System.out.println("Result map values: " + value);
        }
        // Also this way doesn't work:
        // Logic because the entryset is a hashset for hashmaps and not a treeset
        // (and even if it was a treeset, it would be on the keys only)
        for ( Map.Entry<String,Integer> entry : myMap.entrySet() ) {
//            System.out.println("Result map entries: " + entry.getKey() + " -> " + entry.getValue());
        }

        
        // CONCLUSION:
        // If you want to iterate on a map ordered by value, you need to remember:
        // 1) Maps are only sorted by keys, so you can't sort them directly by value
        // 2) So you simply CAN'T return a map to a sortMapByValue function
        // 3) You can't reverse the keys and the values because you have duplicate values
        //    This also means you can't neither use Guava/Commons bidirectionnal treemaps or stuff like that

        // SOLUTIONS
        // So you can:
        // 1) only sort the values which is easy, but you loose the key/value link (since you have duplicate values)
        // 2) sort the map entries, but don't forget to handle the duplicate value case (like i did)
        return set;
    }

    public static List<Company> toList(Set<Map.Entry> set){
        List<Company> list = new ArrayList<Company>();

        for ( Map.Entry<String,Integer> entry : set ) {
//            System.out.println("Set entries: " + entry.getKey() + " -> " + entry.getValue());
            Company company = Company.find("orgName", entry.getKey()).first();
            list.add(company);
        }
        
        return list;
    }
}
