package demo;

import java.util.Scanner;

public class Sy1 {
	public static void main(String[] args) 
    {
        // 1、正确登录名和密码
        String okName = "itheima";
        String okPassword = "123456";
        Scanner sc = new Scanner(System.in);
        int count = 3;
        System.out.println("请输入登录名称和登录密码用&连接：");
        while(count!=0)
        {
            String str = sc.next();
            System.out.println(str);
            if(str.equals(okName+"&"+okPassword))
            {
                System.out.println("登录成功！");
                break;
            }
            else
            {
                count--;
                System.out.println("用户名或者密码错误了！");
            }
        }
        sc.close();
    }
}
