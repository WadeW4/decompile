package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;

public class FloatTagInfo extends TagInfo {
    private final float number;

    public FloatTagInfo(DataInputStream in) throws IOException {
        number = in.readFloat();
    }

	@Override
	public String toString() {
		return String.format("Float Tag : %d", number);
	}

	public float getNumber() {
		return number;
	}
}