package demo;

import java.util.Iterator;
import java.util.TreeMap;

public class Example3 {
	public static void main(String[] args) {
        TreeMap<String, String> tm = new TreeMap<String, String>();
        tm.put("1", "Jack");
        tm.put("2", "Rose");
        tm.put("5", "Lucy");
        tm.put("3", "Lucy");
        tm.put("4", "Rose");
        Iterator<String> it = tm.keySet().iterator();
        while (it.hasNext()) {
        	String key = it.next();
        	String value = tm.get(key);
            System.out.println(key + ":" + value);
        }
    }
}
