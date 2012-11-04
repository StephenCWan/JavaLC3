package com.stephenwan.ljavac3.core;

public class LC3Bridge {

	public LC3Bridge()
	{
		_core = new Core();
	}
	
	private Core _core;
	
	public void writeMemory(int index, int memory)
	{
		_core.writeMemory(index, memory);
	}
	
	public int readMemory(int index)
	{
		return _core.getMemory(index);
	}
	
	public void writeRegister(int index, int content)
	{
		_core.writeRegister(index, content);
	}
	public int readRegister(int index)
	{
		return _core.getRegister(index);
	}
	
	public void executeCycle()
	{
		_core.alu.executeCycle();
	}
	
	public void continueExecution()
	{
		_core.alu.continueExecution();
	}
}
