package com.wade.decompile;

import java.io.DataInputStream;
import java.io.IOException;

public class ClassFileData {
    private final int magicNumber;
    private final short minorVersion;
    private final short majorVersion;
    private final ConstantPool pool;
    private final short flags;
    private final short thisClass;
    private final short superClass;

    public ClassFileData(DataInputStream in) throws IOException {
	magicNumber = in.readInt();
	minorVersion = in.readShort();
	majorVersion = in.readShort();
	pool = new ConstantPool(in);
	flags = in.readShort();
	thisClass = in.readShort();
	superClass = in.readShort();
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("Magic Number  = 0x" + String.format("%08X", magicNumber) + "\n");
	buffer.append("Version       = " + majorVersion + "." + minorVersion + "\n");
	buffer.append(pool.toString());
	buffer.append(String.format("Flags   : 0x%8s\n", Integer.toHexString(flags).replace(' ', '0')));
	return buffer.toString();
    }

    public void complete() {
	pool.complete();
    }
}
