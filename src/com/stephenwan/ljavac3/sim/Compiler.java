package com.stephenwan.ljavac3.sim;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import com.stephenwan.ljavac3.core.ALU;
import com.stephenwan.ljavac3.core.ALUProcessor;
import com.stephenwan.ljavac3.core.ALUProcessor.Instruction;
import com.stephenwan.ljavac3.core.Tools;
import com.stephenwan.ljavac3.sim.CompilerTools.OperandType;

public class Compiler {
	
	public static String[] compileBinary(String file) throws IOException
	{
		ArrayList<String> data = new ArrayList<String>();
		Scanner sc = new Scanner(new File(file));
		
		TreeMap<String, Integer> map = new TreeMap<String , Integer>();
		int counter = 0;
		while (sc.hasNext())
		{
			counter++;
			
			String[] d = sc.nextLine().toUpperCase().split(";")[0].split("[\\s,]+");
			int sindex = 0;
			List instlist = Arrays.asList(ALUProcessor.InstructionT);
			
			if (CompilerTools.findOpcode(d[0]) != ALUProcessor.Instruction.INVALID)
				sindex = 0;
			else if (CompilerTools.findOpcode(d[1]) != ALUProcessor.Instruction.INVALID)
			{
				sindex = 1;
				map.put(d[0], counter);
			}
			else
				throw new CompilerException("Could not parse line " + counter + "");
			
			String opcodestring = d[sindex];
			Instruction iopcode = Instruction.valueOf(d[sindex]);
			String bopcode = Tools.zext(Integer.toBinaryString(Arrays.asList(Instruction.values()).indexOf(iopcode)), 4);
			
			switch (iopcode)
			{
				case ADD:
				{
					String output = bopcode;
					output += Tools.zext(CompilerTools.getRegisterNumber(d[sindex + 1]) + "", 3);
					output += Tools.zext(CompilerTools.getRegisterNumber(d[sindex + 2]) + "", 3);
					
					// register mode
					if (CompilerTools.differentiateType(d[sindex + 3]) == OperandType.Register)
						output += "000" + Tools.zext(CompilerTools.getRegisterNumber(d[sindex + 3]) + "", 3);
					// immediate mode
					else
						output += "1" + d[sindex + 3];
					
					data.add(output);
					break;
					
				}
				case AND:
				{
					String output = bopcode;
					output += Tools.zext(CompilerTools.getRegisterNumber(d[sindex + 1]) + "", 3);
					output += Tools.zext(CompilerTools.getRegisterNumber(d[sindex + 2]) + "", 3);
					
					// register mode
					if (CompilerTools.differentiateType(d[sindex + 3]) == OperandType.Register)
						output += "000" + Tools.zext(CompilerTools.getRegisterNumber(d[sindex + 3]) + "", 3);
					// immediate mode
					else
						output += "1" + d[sindex + 3];
					
					data.add(output);
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
		}
		
		
		String[] output = new String[data.size()];
		for (int i = 0; i < data.size(); i++)
			output[i] = data.get(i);
		return output;
	}
}
