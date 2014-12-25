package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;

public class DoubleTagInfo extends TagInfo {
    private final double number;

    public DoubleTagInfo(DataInputStream in) throws IOException {
        number = in.readDouble();
    }

	@Override
	public String toString() {
		return null;
	}

	public double getNumber() {
		return number;
	}
}