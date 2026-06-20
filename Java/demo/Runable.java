package demo;

public class Runable {
	public static void main(String[] args)
	{
		Thread m = new Thread(new mythread1());
		m.start();
		int a;
		for(a=1;a<101;a++)
		{
			System.out.println("main "+a);
		}
	}
}
class mythread1 implements Runnable
{
	public void run()
	{
		int a;
		for(a=1;a<51;a++)
		{
			System.out.println("new "+a);
		}
	}
}
