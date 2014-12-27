package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class FloatTagInfo extends TagInfo {
    private final float number;

    public FloatTagInfo(DataInputStream in) throws IOException {
	number = in.readFloat();
    }

    @Override
    public String toString() {
	return String.format("Float Tag : %f", number);
    }

    public float getNumber() {
	return number;
    }

    @Override
    public void complete(List<ConstantPoolEntry> constantPool) {
    }

    @Override
    public int getSize() {
	return 1;
    }
}