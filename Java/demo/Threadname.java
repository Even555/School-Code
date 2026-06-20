package demo;

public class Threadname {
	public static void main(String[] args)
	{
		mythread m1 = new mythread("Ò»");
		mythread m2 = new mythread("¶þ");
		m1.start();
		m2.start();
	}
	
}
class mythread extends Thread
{
	String name;
	public void run()
	{
		System.out.println(name);
	}
	public mythread(String a)
	{
		name = a;
	}
}