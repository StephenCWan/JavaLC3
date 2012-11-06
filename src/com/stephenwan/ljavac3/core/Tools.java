/*******************************************************************************
 * Copyright (c) 2012 Stephen Wan.
 * All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.stephenwan.ljavac3.core;

public class Tools {

	public static String sext(String input)
	{
		return sext(input, 32);
	}
	
	public static String sext(String input, int length)
	{
		// sign extension adds multiple of the sign bit to the beginning to pad the length
		while (input.length() < length)
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
		while (input.length() < length)
			input = ("0") + input;
		return input;
	}
	
	public static int convertNumber(String input)
	{
		// if the number of hex...
		if (input.toLowerCase().startsWith("x"))
			return hex2int(input);
		
		// all other cases... (including decimals prefixed with #)
		else
			return (int)Long.parseLong(input.replaceAll("[#]", ""));
	}
	
	public static int bin2int(String binary)
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
	public static int hex2int(String binary)
	{
		// see note on bin2int
		return (int)Long.parseLong(binary, 16);
	}
	public static String int2bin(int content)
	{
		return zext(Integer.toBinaryString(content), 16);
	}
	public static String byte2bin(byte content)
	{
		return zext(Integer.toBinaryString(content & 0xFF), 8);
	}
}
