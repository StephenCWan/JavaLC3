package com.stephenwan.ljavac3.core;

import java.util.TreeMap;

public class Core {
	
	public Core()
	{
		this.initialize();
	}
	
	public void initialize()
	{
		registers = new String[8];
		memory = new TreeMap<Integer, String>();
		alu = new ALU(this);
	}
	
	public ALU alu;
	public String[] registers;
	public int pc;
	public String ir;
	public TreeMap<Integer, String> memory;
	public byte nzpflags;
	
	
	public void movePC(int position)
	{
		this.pc = position;
	}
	
	public void movePCRelative(int position)
	{
		this.pc += position;
	}
	
	public void setIR(String instr)
	{
		this.ir = instr;
	}
	
	public void writeRegister(int index, String content) throws LC3Exception
	{
		
		String result = Tools.zext(content).substring(16);
		
		if (result.startsWith("0")) { nzpflags = 1; }
		if (result.startsWith("1")) { nzpflags = 4; }
		if (((int)Long.parseLong(result)) == 0) { nzpflags = 2; }
		
		this.registers[index] = result;
	}
	public String getRegister(int index)
	{
		return this.registers[index];
	}
	public void writeMemory(int index, String content)
	{
		// can only write user memory
		if (index >= (int)Long.parseLong("FE00", 16) || index <= (int)Long.parseLong("01FF", 16))
		{
			throw new LC3Exception("The address x" + Integer.toHexString(index) + " cannot be written to");
		}
		
		this.memory.put(index, Tools.zext(content).substring(16));
	}
	public String getMemory(int index)
	{
		if (index >= (int)Long.parseLong("FE00", 16))
		{
			// get from device registers
			return Tools.sext("0");
		}
		else if (index <= (int)Long.parseLong("01FF", 16))
		{
			// these memory locations are from the OS stack
			return Tools.sext("0");
		}
		else
		{
			// these locations are in the user program memory area
			if (memory.containsKey(index))
				return memory.get(index);
			return Tools.zext("0", 16);
		}
	}
	
}
