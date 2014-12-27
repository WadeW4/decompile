package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class NameAndTypeTagInfo extends TagInfo {
    private final short classIndex;
    private final short descriptor;
    private String classInfo;
    private String methodSignature;

    public NameAndTypeTagInfo(DataInputStream in) throws IOException {
	classIndex = in.readShort();
	descriptor = in.readShort();
    }

    @Override
    public String toString() {
	return String.format("Name and Type Tag : class index = %d descriptor = %d (class=%s signature=%s)", classIndex, descriptor, classInfo, methodSignature);
    }

    public short getClassIndex() {
	return classIndex;
    }

    public short getDescriptor() {
	return descriptor;
    }

    public String getClassInfo() {
	return classInfo;
    }

    public String getMethodSignature() {
	return methodSignature;
    }

    @Override
    public void complete(List<ConstantPoolEntry> constantPool) {
	classInfo = new String(((UTF8TagInfo) constantPool.get(classIndex - 1).getInfo()).getData());
	methodSignature = new String(((UTF8TagInfo) constantPool.get(descriptor - 1).getInfo()).getData());
    }

    @Override
    public int getSize() {
	return 1;
    }
}
