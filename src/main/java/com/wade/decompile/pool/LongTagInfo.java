package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;

public class LongTagInfo extends TagInfo {
    private final long number;

    public LongTagInfo(DataInputStream in) throws IOException {
        number = in.readLong();
    }

	@Override
	public String toString() {
		return null;
	}

	public long getNumber() {
		return number;
	}

}
