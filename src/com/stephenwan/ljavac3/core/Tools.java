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
		// sign extension adds multiple of the sign bit to the beginning to pad the length
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
		// zero extension adds 0's to the beginning to pad the length
		while (input.length() != length)
			input = ("0") + input;
		return input;
	}
	
	public static int convertNumber(String input)
	{
		// if the number of hex...
		if (input.toLowerCase().startsWith("x"))
			return hex2Int(input);
		
		// all other cases... (including decimals prefixed with #)
		else
			return (int)Long.parseLong(input.replaceAll("[#]", ""));
	}
	
	public static int bin2Int(String binary)
	{
		// The binary string is parsed as a long due to limitations
		// of the Java Integer class; Integer.parseInt() will not take a 32
		// bit unsigned number; therefore we must use the Long class to
		// convert the binary number, then use that binary data as a string
		//
		// The core should eventually be re-written to use int's instead of 
		// strings to store memory data content
		//
		return (int)Long.parseLong(binary, 2);
	}
	public static int hex2Int(String binary)
	{
		// see note on bin2Int
		return (int)Long.parseLong(binary, 16);
	}
}
