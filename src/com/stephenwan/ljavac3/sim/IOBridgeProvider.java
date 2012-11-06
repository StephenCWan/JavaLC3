package com.stephenwan.ljavac3.sim;

import java.io.IOException;

public interface IOBridgeProvider {
	void reset();
	char waitForChar() throws IOException;
}
