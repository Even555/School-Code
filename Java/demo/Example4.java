package demo;

import java.util.Iterator;
import java.util.TreeMap;

public class Example4 {
	public static void main(String[] args) {
        TreeMap<String, String> tm = new TreeMap<String,String>();
        tm.put("1", "1");
        tm.put("2", "2");
        tm.put("3", "3");
        tm.put("4", "4");
        tm.put("5", "5");
        tm.put("6", "6");
        tm.put("7", "7");
        /*System.out.println("treeMap:"+treeMap);
        System.out.println("treeMap.get(1):"+treeMap.get("1"));
        System.out.println("treeMap.get(2):"+treeMap.get("2"));
        System.out.println("treeMap.isEmpty():"+treeMap.isEmpty());
        System.out.println("treeMap.containsKey(2):"+treeMap.containsKey("3"));
        System.out.println("treeMap.keySet():"+treeMap.keySet());
        System.out.println("treeMap.entrySet():"+treeMap.entrySet());
        System.out.println("treeMap.size():"+treeMap.size());
        System.out.println("treeMap.firstEntry():"+treeMap.firstEntry());
        System.out.println("treeMap.lastEntry():"+treeMap.lastEntry());
        System.out.println("treeMap.ceilingEntry(3):"+treeMap.ceilingEntry("3"));
        System.out.println("treeMap.floorKey(3):"+treeMap.floorKey("3"));
        System.out.println("treeMap.higherKey(2):"+treeMap.higherKey("2"));
        System.out.println("treeMap.lowerEntry(2):"+treeMap.lowerEntry("2"));
        System.out.println("treeMap.replace(2, 8):"+treeMap.replace("2", "8"));
        System.out.println("treeMap.descendingMap():"+treeMap.descendingMap());
        System.out.println("treeMap.navigableKeySet():"+treeMap.navigableKeySet());
        System.out.println("treeMap.headMap(3):"+treeMap.headMap("3"));
        System.out.println("treeMap.tailMap(3):"+treeMap.tailMap("3"));*/

        System.out.println("µü´úÆ÷±éÀú");
        Iterator<String> it = tm.keySet().iterator();
        while(it.hasNext()){
            System.out.println(tm.get(it.next()));
        }
    }
}
