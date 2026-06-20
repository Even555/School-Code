package chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Sent {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception
	{
		final int port = 5555;
		ServerSocket ss = null;
		ss = new ServerSocket(port);
		System.out.println("准备链接");
		Socket so = null;
		so = ss.accept();
		System.out.println("链接成功");
		new Thread(new Tcps1(so)).run();
		new Thread(new Tcps2(so)).run();
	}
}
class Tcps1 implements Runnable
{
	Socket so = null;
	public Tcps1(Socket so)
	{
		this.so = so;
	}
	public void run()
	{
		OutputStream os = null;
		try {
			os = so.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("请输入对话");
		Scanner sc = new Scanner(System.in);
		try {
			os.write(sc.next().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}
}
class Tcps2 implements Runnable
{
	Socket sk = null;
	public Tcps2(Socket so)
	{
		this.sk = so;
	}
	public void run()
	{
		InputStream is = null;
		try {
			is = sk.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] buf = new byte[1024];
		int js = 0;
		try {
			js = is.read(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("对方："+new String(buf,0,js));
		try {
			sk.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}