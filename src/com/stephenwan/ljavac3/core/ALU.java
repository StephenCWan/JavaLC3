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
	public int processed;
	public boolean executing;

	public void continueExecution()
	{
		executing = true;
		handleExecution();
	}
	
	public void handleExecution()
	{
		while (executing)
		{
			fetch();
			System.out.println("Executing instruction: " + core.ir);
			processor.execInstruction(core.ir);
			SimulatorMain.dumpState();
			System.out.println();
			processed++;
		}
	}
	
	public void fetch()
	
	{
		int pc = core.pc;
		String nIR = core.getMemory(pc);
		core.setIR(nIR);
		core.pc++;
		
	}
}
