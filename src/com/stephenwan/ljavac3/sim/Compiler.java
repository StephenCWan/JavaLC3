package com.stephenwan.ljavac3.sim;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

import com.stephenwan.ljavac3.core.ALU;
import com.stephenwan.ljavac3.core.ALUProcessor;
import com.stephenwan.ljavac3.core.ALUProcessor.Instruction;
import com.stephenwan.ljavac3.core.Tools;

public class Compiler {
	
	public static String[] compileBinary(String file) throws IOException
	{
		ArrayList<String> data = new ArrayList<String>();
		Scanner sc = new Scanner(new File(file));
		
		TreeMap<String, Integer> map = new TreeMap<String , Integer>();
		int counter = 0;
		while (sc.hasNext())
		{
			String[] d = sc.nextLine().split(";")[0].split(" +");
			int sindex = 0;
			if (Arrays.asList(ALUProcessor.InstructionT).contains(d[1]))
			{
				// label is present
				map.put(d[0], counter);
				sindex = 1;
			}
			Instruction iopcode = Instruction.valueOf(d[sindex]);
			String bipcode = Tools.zext(Integer.toBinaryString(Arrays.asList(Instruction.values()).indexOf(iopcode)), 4);
			
			switch (iopcode)
			{
				case ADD:
				{
					break;
					
				}
				case AND:
				{
					
					break;
				}
				case BR:
				{
					
					break;
				}
				case JMP:
				{
					// not implemented
					break;
				}
				case JSR:
				{
					// not implemented
					break;
				}
				case LD:
				{
					
					break;
				}
				case LDI:
				{
					
					break;
				}
				case LDR:
				{
					
					break;
				}
				case LEA:
				{
					
					break;
				}
				case NOT:
				{
					
					break;
				}
				case RTI:
				{
					// not implemented
					break;
				}
				case ST:
				{
					
					break;
				}
				case STI:
				{
					
					break;
				}
				case STR:
				{
					
					break;
				}
				case TRAP:
				{
					
					break;
				}
				default:
				{
					// uh oh
				}
			}
			
			counter++;
		}
		
		
		String[] output = new String[data.size()];
		for (int i = 0; i < data.size(); i++)
			output[i] = data.get(i);
		return output;
	}
}
