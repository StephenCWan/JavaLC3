package com.stephenwan.ljavac3.sim;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;

import com.stephenwan.ljavac3.core.LC3Bridge;
import com.stephenwan.ljavac3.core.LC3Exception;
import com.stephenwan.ljavac3.core.Tools;

public class Loader {
	public static void loadBinary(LC3Bridge bridge, String filename) throws IOException
	{
		FileInputStream stream = null;
		byte[] buffer = null;
		try
		{
			File handle = new File(filename);
			stream = new FileInputStream(handle);
			buffer = new byte[(int)handle.length()];
			
			stream.read(buffer);
			
		}
		catch (IOException e)
		{
			throw new LC3Exception("An error occurred while opening object file '" + filename + "'");
		}
		catch (Exception e)
		{
			throw new LC3Exception("Failed to parse object file '" + filename + "'");
		}
		finally
		{
			if (stream != null)
				stream.close();
		}
		if (buffer.length % 2 == 1)
			throw new LC3Exception("Length of the object file '" + filename + "'parsed is wrong");
		if (buffer.length < 2)
			throw new LC3Exception("The object file '" + filename + "' is empty or corrupted");
		
		// get start location
		int counter = bytes2int(buffer[0], buffer[1]);
		bridge.setPC(counter);
		
		for (int i = 2; i < buffer.length; i+= 2)
		{
			int bytes = bytes2int(buffer[i], buffer[i + 1]);
			//System.out.println("writing " + Tools.int2bin(bytes) + " to x" + Integer.toHexString(counter));
			bridge.writeMemory(counter, bytes);
			counter++;
		}
		
	}
	public static int bytes2int(byte byte1, byte byte2)
	{
		int res = Tools.bin2int(Tools.byte2bin(byte1) + "" + Tools.byte2bin(byte2));
		return res;
	}
}
