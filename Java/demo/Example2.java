package demo;

import java.util.HashSet;

class Person {
    private String age;
    private String name;

    public Person(String age, String name) {
        this.age = age;
        this.name = name;
    }

    // 重写toString()方法
    public String toString() 
    {
    	 return name+","+age;
    }

    // 重写hashCode方法
    public int hashCode() {
        return name.hashCode(); // 返回id属性的哈希值
    }

    // 重写equals方法
	 public boolean equals(Object person1) 
	 {
		 Person person = (Person)person1;
		 return (name==person.name);
	 }
}
public class Example2 {
	public static void main(String[] args) 
	{
        HashSet<Person> hs = new HashSet<Person>(); // 创建HashSet对象
        Person p1 = new Person("18", "Jack"); // 创建Student对象
        Person p2 = new Person("19", "Rose");
        Person p3 = new Person("20", "Rose");
        hs.add(p1); // 向集合存入对象
        hs.add(p2);
        hs.add(p3);
        System.out.println(hs); // 打印集合中的元素
    }

}


