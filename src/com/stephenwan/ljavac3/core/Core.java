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

import java.util.TreeMap;

import com.stephenwan.ljavac3.sim.IOBridgeProvider;

class Core {
	
	//
	// In this implementation we must use integers rather than shorts to carry our data. This is because
	// java shorts are only signed, and cannot be used without severe casting in code that requires operations
	// on the data
	//
	
	public Core()
	{
		this.initialize();
	}
	
	public void initialize()
	{
		registers = new int[8];
		memory = new TreeMap<Integer, Integer>();
		alu = new ALU(this);
	}
	
	public IOBridgeProvider bridge;
	public ALU alu;
	public int[] registers;
	public int pc;
	public String ir;
	public TreeMap<Integer, Integer> memory;
	public byte nzpFlags;
	public boolean supervisorMode;
	public byte priorityLevel;
	
	
	public void movePC(int position)
	{
		// pc = program counter
		this.pc = position;
	}
	
	public void movePCRelative(int position)
	{
		// pc = program counter
		this.pc += position;
	}
	
	public void setIR(int instr)
	{
		// IR = instruction register
		this.ir = Tools.int2bin(instr);
	}
	
	public void writeRegister(int index, String content)
	{
		String result = Tools.zext(content).substring(16); // make sure register is correct length
		writeRegister(index, Tools.bin2int(result));
	}
	
	public void writeRegister(int index, int content) throws LC3Exception
	{
		
		// set nzp (cc) flags
		short contents = (short)content;
		if (contents > 0) { nzpFlags = 1; } // 001
		if (contents < 0) { nzpFlags = 4; } // 100
		if (contents == 0) { nzpFlags = 2; } // 010
		
		this.registers[index] = content;
	}
	
	public int getRegister(int index)
	{
		return this.registers[index];
	}
	
	public void writeMemory(int index, String content)
	{
		writeMemory(index, Tools.bin2int(Tools.zext(content).substring(16)));
	}
	
	public void writeMemory(int index, int content)
	{
		// can only write user memory
		// this is a simplification of what the LC3 actually does
		if (index >= (int)Long.parseLong("FE00", 16) || index <= (int)Long.parseLong("01FF", 16))
		{
			throw new LC3Exception("The address x" + Integer.toHexString(index) + " cannot be written to");
		}
		
		this.memory.put(index, content);
	}
	
	public int getMemory(int index)
	{
		// this will eventually need to be replaced by the memory protection table
		// and checks against the supervisor mode
		// the psr needs to be implementd still
		if (index >= (int)Long.parseLong("FE00", 16))
		{
			// get from device registers
			return 0;
		}
		else if (index <= (int)Long.parseLong("01FF", 16))
		{
			// these memory locations are from the OS stack
			return 0;
		}
		else
		{
			// these locations are in the user program memory area
			if (memory.containsKey(index))
				return memory.get(index);
			return 0;
		}
	}
	
}
