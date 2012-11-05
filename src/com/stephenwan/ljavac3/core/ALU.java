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

import java.util.Scanner;

import com.stephenwan.ljavac3.sim.SimulatorMain;

public class ALU {
	public ALU(Core core)
	{
		this.core = core;
		this.processor = new ALUProcessor(this);
	}
	
	Core core;
	ALUProcessor processor;
	public int cycleCounter;
	public boolean executing;

	public void continueExecution() throws LC3Exception
	{
		executing = true;
		handleExecution();
	}
	
	public void handleExecution() throws LC3Exception
	{
		while (executing)
			executeCycle();
	}
	
	public void executeCycle()
	{
		fetch();
		System.out.println("Executing instruction: " + core.ir);
		cycleCounter++;
		processor.execInstruction(core.ir);
		SimulatorMain.dumpState();
		System.out.println();
	}
	
	public void fetch()
	
	{
		int pc = core.pc;
		int nIR = core.getMemory(pc);
		core.setIR(nIR);
		core.pc++;
		
	}
}
