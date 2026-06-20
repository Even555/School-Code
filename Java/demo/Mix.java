package demo;

import java.io.*;

public class Mix {
	public static void main(String args[]) throws IOException
	{
		String p1 ="C:\\Users\\78113\\eclipse-workspace\\test\\src\\demo\\hello";
		String p2 ="C:\\Users\\78113\\eclipse-workspace\\test\\src\\demo\\hi";
		String p3 ="C:\\Users\\78113\\eclipse-workspace\\test\\src\\demo\\ok";
		mix(p1,p2,p3);
	}
	public static void mix(String p1,String p2,String p3) throws IOException
	{
		InputStream out1 = new FileInputStream(p1);
		InputStream out2 = new FileInputStream(p2);
		OutputStream in3 = new FileOutputStream(p3);
		int a;
		byte[] b =new byte[1024];
		a=out1.read(b);
		in3.write(b,0,a);
		a=out2.read(b);
		in3.write(b,0,a);
		//System.out.print(b);
		out1.close();
		out2.close();
		in3.close();
	}
}
