package demo;

public class ChuanChuLi 
{
	public static void main(String[] args)
	{
		int js;
		String a = "HelloWorld";
		StringBuffer a1 = new StringBuffer();
		char[] b = a.toCharArray();
		for(js=a.length()-1;js>=0;js--)
		{
			if(b[js]>='A'&&b[js]<='Z')
			{
				a1.append(String.valueOf(b[js]).toLowerCase());
			}
			else if(b[js]>='a'&&b[js]<='z')
			{
				a1.append(String.valueOf(b[js]).toUpperCase());
			}
		}
		System.out.println(a1.toString());
	}
}
