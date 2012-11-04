package com.stephenwan.ljavac3.sim;

import java.util.List;

import com.stephenwan.ljavac3.core.ALUProcessor;
import com.stephenwan.ljavac3.core.Tools;

public class CompilerTools {

	public static int getRegisterNumber(String reg)
	{
		reg = reg.toUpperCase();
		if (reg.length() == 2 && reg.startsWith("R") && "0123456789".contains(reg.substring(1)))
			return Integer.parseInt(reg.substring(1));
		else
			throw new CompilerException("Unknown symbol '" + reg + "' encountered when expecting register name");
	}
	
	public static int getNumber(String input)
	{
		try
		{
			return Tools.convertNumber(input);
		}
		catch (Exception e)
		{
			throw new CompilerException("Unable to parse number '" + input +  "'");
		}
	}
	
	public enum OperandType
	{
		Register,
		Number
	}
	
	public static OperandType differentiateType(String data)
	{
		if (data.startsWith("R"))
			return OperandType.Register;
		else
			return OperandType.Number;
	}
	
	public static ALUProcessor.Instruction findOpcode(String opcode)
	{
		ALUProcessor.Instruction[] cache = ALUProcessor.Instruction.values();
		for (int i = 0; i < cache.length; i++)
		{
			String matcher = ALUProcessor.InstructionMatchers[i];
			if (opcode.matches(matcher))
				return cache[i];
		}
		return ALUProcessor.Instruction.INVALID;
	}
}
