package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;

public class ClassTagInfo extends TagInfo {
    private final short number;

    public ClassTagInfo(DataInputStream in) throws IOException {
        number = in.readShort();
    }

	@Override
	public String toString() {
		return String.format("Class Tag : %d", number);
	}

	public short getNumber() {
		return number;
	}
}
