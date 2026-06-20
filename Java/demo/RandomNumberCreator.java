package demo;

import java.util.Random;

public class RandomNumberCreator 
{
	public static void main(String[] args)
	{
		Random random = new Random();
		int a;
		for(a=0;a<5;a++)
		{
			System.out.println(random.nextInt(20,50));
		}
	}
}
