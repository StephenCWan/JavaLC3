package com.stephenwan.ljavac3.core;

public class ALUProcessor {

	public ALUProcessor(ALU alu)
	{
		cache = Instruction.values();
		this.alu = alu;
	}
	
	public enum Instruction
	{
		BR, ADD, LD, ST, JSR, AND, LDR, STR, RTI, NOT, LDI, STI, JMP, INVALID, LEA, TRAP
	}
	public static String[] InstructionT = { "BR", "ADD", "LD", "ST", "JSR", "AND", "LDR", "STR", "RTI", "NOT", "LDI", "STI", "JMP", "INVALID", "LEA", "TRAP" };
	
	public ALU alu;
	public Instruction[] cache;
	
	public void execInstruction(String content)
	{
		Instruction opcode = cache[Integer.parseInt(content.substring(0, 4), 2)];
		String sOperands = content.substring(4);
		
		switch(opcode)
		{
			case ADD:
			{
				
				int dr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				int sr = (int)Long.parseLong(sOperands.substring(3, 6), 2);
				int sr1c = (int)Long.parseLong(alu.core.getRegister(sr), 2);
				if (sOperands.charAt(6) == '0')
				{
					int sr2 = (int)Long.parseLong(sOperands.substring(9), 2);	
					int sr2c = (int)Long.parseLong(alu.core.getRegister(sr2), 2);
					String result = Integer.toBinaryString(sr1c + sr2c);
					
					alu.core.writeRegister(dr, result);
				}
				else
				{
					int immediate = (int)Long.parseLong(Tools.sext(sOperands.substring(7)), 2);
					String result = Integer.toBinaryString(sr1c + immediate);
					
					alu.core.writeRegister(dr, result);
				}
				break;
				
			}
			case AND:
			{
				int dr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				int sr = (int)Long.parseLong(sOperands.substring(3, 6), 2);
				int sr1c = (int)Long.parseLong(alu.core.getRegister(sr), 2);
				if (sOperands.charAt(6) == '0')
				{
					int sr2 = (int)Long.parseLong(sOperands.substring(9), 2);	
					int sr2c = (int)Long.parseLong(alu.core.getRegister(sr2), 2);
					String result = Integer.toBinaryString(sr1c & sr2c);
					
					alu.core.writeRegister(dr, result);
				}
				else
				{
					int immediate = (int)Long.parseLong(Tools.sext(sOperands.substring(7)), 2);
					String result = Integer.toBinaryString(sr1c & immediate);
					
					alu.core.writeRegister(dr, result);
				}
				break;
			}
			case BR:
			{
				int offset = (int)Long.parseLong(Tools.sext(sOperands.substring(3)), 2);
				if (sOperands.charAt(0) == '1' && alu.core.n || sOperands.charAt(1) == '1' && alu.core.z || sOperands.charAt(2) == '1' && alu.core.p)
				{
					alu.core.movePCRelative(offset);
				}
				break;
			}
			case JMP:
			{
				// not implemented
				break;
			}
			case JSR:
			{
				// not implemented
				break;
			}
			case LD:
			{
				int dr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				int offset = (int)Long.parseLong(Tools.sext(sOperands.substring(3)), 2);
				
				String result = alu.core.getMemory(offset + alu.core.pc);
				
				alu.core.writeRegister(dr, result);
				break;
			}
			case LDI:
			{
				int dr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				int offset = (int)Long.parseLong(Tools.sext(sOperands.substring(3)), 2);
				String result = alu.core.getMemory((int)Long.parseLong(alu.core.getMemory(offset + alu.core.pc), 2));
				
				alu.core.writeRegister(dr, result);
				break;
			}
			case LDR:
			{
				int dr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				int baser = (int)Long.parseLong(sOperands.substring(3, 6), 2);
				int basercontent = (int)Long.parseLong(alu.core.getRegister(baser));
				int offset = (int)Long.parseLong(Tools.sext(sOperands.substring(6)), 2);
				
				String result = alu.core.getMemory(baser + offset);
			
				alu.core.writeRegister(dr, result);
				break;
			}
			case LEA:
			{
				int dr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				int offset = (int)Long.parseLong(Tools.sext(sOperands).substring(3), 2);
				String result = Integer.toBinaryString(alu.core.pc + offset);
				
				alu.core.writeRegister(dr, result);
				break;
			}
			case NOT:
			{
				int dr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				int sr = (int)Long.parseLong(sOperands.substring(3, 6), 2);
				int data = ~(int)Long.parseLong(alu.core.getRegister(sr));
				
				String result = Integer.toBinaryString(data);
				
				alu.core.writeRegister(dr, result);
				break;
			}
			case RTI:
			{
				// not implemented
				break;
			}
			case ST:
			{
				int sr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				String src = alu.core.getRegister(sr);
				int offset = (int)Long.parseLong(Tools.sext(sOperands.substring(3)), 2);
				
				alu.core.writeMemory(alu.core.pc + offset, src);
				break;
			}
			case STI:
			{
				int sr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				String src = alu.core.getRegister(sr);
				int offset = (int)Long.parseLong(Tools.sext(sOperands.substring(3)), 2);
				
				int location = (int)Long.parseLong(alu.core.getMemory(alu.core.pc + offset), 2);
				
				alu.core.writeMemory(location, src);
				break;
			}
			case STR:
			{
				int sr = (int)Long.parseLong(sOperands.substring(0, 3), 2);
				String src = alu.core.getRegister(sr);
				int baser = (int)Long.parseLong(sOperands.substring(3, 6), 2);
				int basercontent = (int)Long.parseLong(alu.core.getRegister(baser));
				int offset = (int)Long.parseLong(sOperands.substring(6), 2);
				
				alu.core.writeMemory(basercontent + offset, src);
				
				break;
			}
			case TRAP:
			{
				int vector = (int)Long.parseLong(sOperands.substring(4));
				// we should go to the trap vector, but it's not implemented
				// let's just hope it's a halt (x25)... :(
				alu.executing = false;
				break;
			}
			default:
			{
				// uh oh
			}
		}
		
	}
}
