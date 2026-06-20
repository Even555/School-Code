package demo;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Converter {
	public static void main(String[] args)
	{
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 100);
        Date date = calendar.getTime();
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
        String string = format.format(date);
        System.out.println(string);
	}
}
