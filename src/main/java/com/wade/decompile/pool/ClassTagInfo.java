package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class ClassTagInfo extends TagInfo {
    private final short number;
    private String classInfo;

    public ClassTagInfo(DataInputStream in) throws IOException {
	number = in.readShort();
    }

    @Override
    public String toString() {
	return String.format("Class Tag : %d (%s)", number, classInfo);
    }

    public short getNumber() {
	return number;
    }

    public String getClassInfo() {
	return classInfo;
    }

    public void setClassInfo(String classInfo) {
	this.classInfo = classInfo;
    }

    @Override
    public void complete(List<ConstantPoolEntry> constantPool) {
	ConstantPoolEntry constantPoolEntry = constantPool.get(number - 1);
	UTF8TagInfo info = (UTF8TagInfo) constantPoolEntry.getInfo();
	if (info != null) {
	    classInfo = new String(info.getData());
	}
    }

    @Override
    public int getSize() {
	return 1;
    }
}
