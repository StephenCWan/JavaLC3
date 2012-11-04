package com.stephenwan.ljavac3.sim;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import com.stephenwan.ljavac3.core.ALU;
import com.stephenwan.ljavac3.core.Core;
import com.stephenwan.ljavac3.core.LC3Exception;
import com.stephenwan.ljavac3.core.Tools;
public class SimulatorMain {
	public static void pl(Object o) { System.out.println(o); }
 	
	static Core core;
	
	public static void main(String[] args) throws LC3Exception
	{
		System.out.print("Machine Initializing...");
		core = new Core();
		System.out.println(" [OK]\n");
		core.writeMemory((int)Long.parseLong("30FF", 16), ("1111000011110000"));
		core.writeMemory((int)Long.parseLong("3100", 16), ("0000000000001111"));
		System.out.println("Setting memory...");
		System.out.println("x30FF = " + core.getMemory((int)Long.parseLong("30FF", 16)));
		System.out.println("x3100 = " + core.getMemory((int)Long.parseLong("3100", 16)));

		System.out.print("\nLoading binary program...");
		binLoader("/Users/stephen/Desktop/leftrotate.bin");
		//binLoader("C:/Users/Stephen/Desktop/Git/JavaLC3/bin/com/stephenwan/ljavac3/sim/leftrotate.bin");
		System.out.println(" [OK]");

		System.out.print("Executing program... ");
		core.alu.continueExecution();
		System.out.println(" [OK]");

		System.out.println("Executed " + core.alu.cycleCounter + " instruction(s)\n");
		System.out.println("Memory Dump");
		System.out.println("x3101 = " + core.getMemory((int)Long.parseLong("3101", 16)));
	}
	
	public static void binLoader(String file)
	{
		try
		{
			Scanner sc = new Scanner(new File(file));
			String sexted = Tools.sext(sc.nextLine().trim());
			int line = (int)Long.parseLong(sexted, 2);
			core.pc = line;
			while (sc.hasNextLine())
			{
				String content = sc.nextLine();
				core.writeMemory(line, content);
				line++;
			}
		}
		catch (Exception e)
		{
			throw new LC3Exception("Failed to load binary file '" + file + "'");
		}
	}
	public static void dumpState()
	{
		pl("\t" + Arrays.toString(core.registers) + " : " + core.alu.cycleCounter);
	}
}
