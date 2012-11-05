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

public class LC3Bridge {

	public int getProcessedCycles()
	{
		return _core.alu.cycleCounter;
	}
	
	public LC3Bridge()
	{
		_core = new Core();
	}
	
	private Core _core;
	
	public void setPC(int location)
	{
		_core.pc = location;
	}
	
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

	public void dumpState()
	{
		String l = "[";
		for (int i = 0; i < _core.registers.length; i++)
			l += Tools.int2bin(_core.registers[i]) + (i + 1 == _core.registers.length ? "" : ", ");
		l += "]";
		System.out.println("\t" + l + " : " + _core.alu.cycleCounter);
	}
}
