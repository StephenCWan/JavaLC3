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
		processor.execInstruction(core.ir);
		SimulatorMain.dumpState();
		System.out.println();
		cycleCounter++;
	}
	
	public void fetch()
	
	{
		int pc = core.pc;
		String nIR = core.getMemory(pc);
		core.setIR(nIR);
		core.pc++;
		
	}
}
