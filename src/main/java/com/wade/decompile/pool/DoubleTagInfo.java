package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class DoubleTagInfo extends TagInfo {
    private final double number;
    private int index;

    public DoubleTagInfo(DataInputStream in) throws IOException {
	number = in.readDouble();
    }

    @Override
    public String toString() {
	return String.format("Double Tag : %f", number);
    }

    public double getNumber() {
	return number;
    }

    @Override
    public void complete(List<ConstantPoolEntry> constantPool) {
    }

    @Override
    public int getSize() {
	return 2;
    }
}