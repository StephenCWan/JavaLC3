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
	public boolean n;
	public boolean z;
	public boolean p;
	
	
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
	
	public void writeRegister(int index, String content)
	{
		String result = Tools.zext(content).substring(16);
		
		if (result.startsWith("0")) { alu.core.n = false; alu.core.z = false; alu.core.p = true; }
		if (result.startsWith("1")) { alu.core.n = true; alu.core.z = false; alu.core.p = false; }
		if (((int)Long.parseLong(result)) == 0) { alu.core.n = false; alu.core.z = true; alu.core.p = false; }
		
		this.registers[index] = result;
	}
	public String getRegister(int index)
	{
		return this.registers[index];
	}
	public void writeMemory(int index, String content)
	{
		this.memory.put(index, Tools.zext(content).substring(16));
	}
	public String getMemory(int index)
	{
		if (memory.containsKey(index))
			return memory.get(index);
		return Tools.zext("0", 16);
	}
	
}
