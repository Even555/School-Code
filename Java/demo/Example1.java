package demo;

import java.util.ArrayList;
import java.util.Iterator;

public class Example1 
{
	 public static void main(String[] args)
	 {
	        ArrayList<String> list = new ArrayList<String>();
	        list.add("stu1");
	        list.add("stu2");
	        list.add("stu3");
	        list.add("stu4");
	        list.add("stu5");
	        list.add("stu6");
	        list.add("stu7");
	        list.add("stu8");
	        list.add("stu9");
	        list.add("stu0");
	        Iterator<String> it = list.iterator();
	        while(it.hasNext()) {
	            System.out.print(it.next()+" ");
	        }
	 }
}
