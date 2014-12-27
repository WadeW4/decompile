package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class UTF8TagInfo extends TagInfo {
    private final short length;
    private final byte[] data;

    public UTF8TagInfo(DataInputStream in) throws IOException {
	length = in.readShort();
	data = new byte[length];
	in.read(data);
    }

    @Override
    public String toString() {
	return String.format("UTF8 Tag data=%s", new String(data));
    }

    public short getLength() {
	return length;
    }

    public byte[] getData() {
	return data;
    }

    @Override
    public void complete(List<ConstantPoolEntry> constantPool) {
    }

    @Override
    public int getSize() {
	return 1;
    }
}
