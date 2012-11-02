package com.stephenwan.ljavac3.core;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Tools {

	public static String sext(String input)
	{
		return sext(input, 32);
	}
	
	public static String sext(String input, int length)
	{
		while (input.length() != length)
			input = ("" + input.charAt(0)) + input;
		return input;
	}
	
	public static String zext(String input)
	{
		return zext(input, 32);
	}
	
	public static String zext(String input, int length)
	{
		while (input.length() != length)
			input = ("0") + input;
		return input;
	}
	
	public static int convertNumber(String input)
	{
		if (input.toLowerCase().startsWith("x"))
			return (int)Long.parseLong(input, 16);
		else
			return (int)Long.parseLong(input.replaceAll("[#]", ""));
	}
}
