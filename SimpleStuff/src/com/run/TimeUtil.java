package com.run;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static void main(String args[]) throws ParseException {
		//1490003743
		//1531925660207
		System.out.println(System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(fmt.format(new Date(1490003743*1000L)));
		System.out.println(fmt.format(new Date(1531925660207L)));
		System.out.println(fmt.format(new Date(1504786400*1000L)));
		System.out.println((fmt.parse("2018-01-01 00:00:00")).getTime()/1000L);
	}
}
