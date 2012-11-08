package com.stephenwan.ljavac3.sim;

import java.io.DataInputStream;
import java.io.IOException;

public class KeyboardIOProvider implements IOBridgeProvider {

	DataInputStream stream;
	
	public  KeyboardIOProvider()
	{
		stream = new DataInputStream(System.in);
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public char waitForChar() throws IOException {
		// TODO Auto-generated method stub
		return stream.readChar();
	}

	@Override
	public void printLine(Object o) {
		// TODO Auto-generated method stub
		System.out.println(o);
	}

	@Override
	public void print(Object o) {
		// TODO Auto-generated method stub
		System.out.print(o);
	}

}
