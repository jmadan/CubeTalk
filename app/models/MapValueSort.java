package models;

import java.util.*;

public class MapValueSort {

    /** inner class to do soring of the map **/
    public static class ValueComparer implements Comparator {
        private Map _data = null;
        public ValueComparer (Map data){
            super();
            _data = data;
        }

        public int compare(Object o1, Object o2) {
//            String e1 = (String) _data.get(o1);
//            String e2 = (String) _data.get(o2);
//            return e1.compareTo(e2);

            Integer i1 = (Integer) _data.get(o1);
            Integer i2 = (Integer) _data.get(o2);
            return i1.compareTo(i2);
        }
    }

//    public static void main(String[] args){
//
//        Map unsortedData = new HashMap();
//        unsortedData.put("2", "DEF");
//        unsortedData.put("1", "ABC");
//        unsortedData.put("4", "ZXY");
//        unsortedData.put("3", "BCD");
//
//        SortedMap sortedData = new TreeMap(new models.MapValueSort.ValueComparer(unsortedData));
//
//        printMap(unsortedData);
//
//        sortedData.putAll(unsortedData);
//        System.out.println();
//        printMap(sortedData);
//    }

    public static void printMap(Map data) {
        for (Iterator iter = data.keySet().iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            System.out.println("Value/key - printMap:"+data.get(key)+"/"+key);
        }
    }
    
    public static List<Company> toList(Map data){
        List<Company> list = new ArrayList<Company>();
        for (Iterator iter = data.keySet().iterator(); iter.hasNext();) {
            String key = (String)iter.next();
            Company company = Company.find("orgName", key).first();
            list.add(company);
            System.out.println("Value/key - toList:"+data.get(key)+"/"+key);
        }

        return list;
    }

}