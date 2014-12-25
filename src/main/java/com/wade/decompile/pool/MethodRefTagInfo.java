package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodRefTagInfo extends TagInfo {
    private final short classIndex;
    private final short nameAndTypeIndex;

    public MethodRefTagInfo(DataInputStream in) throws IOException {
        classIndex = in.readShort();
        nameAndTypeIndex = in.readShort();
    }
    
	@Override
	public String toString() {
		return String.format("MethodRef Tag : class index = %d Name And Type Index = %d", classIndex,nameAndTypeIndex);
	}
	
	public short getClassIndex() {
		return classIndex;
	}

	public short getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}

}
