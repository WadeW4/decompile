package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;

public class StringTagInfo extends TagInfo {
    private final short number;

    public StringTagInfo(DataInputStream in) throws IOException {
        number = in.readShort();
    }

	@Override
	public String toString() {
		return String.format("String Tag : number = %d", number);
	}

	public short getNumber() {
		return number;
	}

}
