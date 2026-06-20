package chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Recive {
	public static void main(String[] args) throws Exception
	{
		//Scanner sc = new Scanner(System.in);
		int a,b;
		//System.out.println("请输入发送端端口号");
		a=500;
		//a = sc.nextInt();
		//System.out.println("请输入接收端端口号");
		b=600;
		//b = sc.nextInt();
		new Thread(new Tcpr1(a)).start();
		new Thread(new Tcpr2(b)).start();
		//sc.close();
	}
}
class Tcpr1 implements Runnable
{
	int port;
	public Tcpr1(int port)
	{
		this.port = port;
	}
	@SuppressWarnings("resource")
	public void run()
	{
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("准备链接");
		Socket so = null;
		try {
			so = ss.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("链接成功");
		OutputStream os = null;
		try {
			os = so.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("请输入对话");
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			try {
				os.write(sc.next().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Tcpr2 implements Runnable
{
	int port;
	public Tcpr2(int port)
	{
		this.port = port;
	}
	public void run()
	{
		Socket sk = null;
		try {
			sk = new Socket(InetAddress.getLocalHost(),port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("准备链接");
		InputStream is = null;
		try {
			is = sk.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("链接成功,等待来信");
		byte[] buf = new byte[1024];
		while(true)
		{
			int js = 0;
				try {
					js = is.read(buf);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("对方："+new String(buf,0,js));
		}
	}
}