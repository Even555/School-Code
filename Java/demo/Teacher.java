package demo;

public class Teacher {
	public static void main(String[] args)
	{
		tothread t = new tothread();
		    new Thread(t,"黄老师").start();
		    new Thread(t,"周老师").start();
		    new Thread(t,"杨老师").start();
	}
}
class tothread implements Runnable
{
	int a=80;
	Object lock = new Object();
	public void run()
	{
		while(true)
		{
			synchronized(lock)
			{
				if(a>0)
				{
					System.out.println(Thread.currentThread().getName()+"发出第"+(81-a--)+"份");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
					break;
			}
		}
	}
}