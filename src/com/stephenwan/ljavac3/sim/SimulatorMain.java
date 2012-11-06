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
package com.stephenwan.ljavac3.sim;

import java.io.IOException;
import com.stephenwan.ljavac3.core.LC3Bridge;
import com.stephenwan.ljavac3.core.LC3Exception;
import com.stephenwan.ljavac3.core.Tools;
public class SimulatorMain {
	public static void pl(Object o) { System.out.println(o); }
	public static void pt(Object o) { System.out.print(o); }
	
	public static void main(String[] args) throws LC3Exception, IOException
	{
		pt("Machine Initializing...");
		LC3Bridge bridge = new LC3Bridge(new KeyboardIOProvider());
		pl(" [OK]\n");
		bridge.writeMemory(Tools.hex2int("30FF"), Tools.bin2int("1111000011110000"));
		bridge.writeMemory(Tools.hex2int("3100"), Tools.bin2int("0000000000000100"));
		pl("Setting memory...");
		pl("x30FF = " + Tools.int2bin(bridge.readMemory(Tools.hex2int("30FF"))));
		pl("x3100 = " + Tools.int2bin(bridge.readMemory(Tools.hex2int("3100"))));

		pt("\nLoading binary program...");
		Loader.loadBinary(bridge, "/Users/stephen/Desktop/leftrotate.obj");
		//Loader.loadBinary(bridge, "C:/Users/Stephen/Desktop/leftrotate.obj");
		pl(" [OK]");

		pt("Executing program... ");
		bridge.continueExecution();
		pl(" [OK]");

		pl("Executed " + bridge.getProcessedCycles() + " instruction(s)\n");
		pl("Memory Dump");
		pl("x3101 = " + Tools.int2bin(bridge.readMemory(Tools.hex2int("3101"))));
	}
	
}
