package com.wade.decompile;

import java.io.DataInputStream;
import java.io.IOException;

public class ClassFileData {
    private final int magicNumber;
    private final short minorVersion;
    private final short majorVersion;
    private final ConstantPool pool;

    public ClassFileData(DataInputStream in) throws IOException {
	magicNumber = in.readInt();
	minorVersion = in.readShort();
	majorVersion = in.readShort();
	pool = new ConstantPool(in);
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("Magic Number  = 0x" + String.format("%08X", magicNumber) + "\n");
	buffer.append("Version       = " + majorVersion + "." + minorVersion + "\n");
	return buffer.toString();
    }

}
