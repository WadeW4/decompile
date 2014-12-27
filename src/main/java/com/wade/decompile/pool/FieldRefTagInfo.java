package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class FieldRefTagInfo extends TagInfo {
    private final short classIndex;
    private final short nameAndTypeIndex;
    private String classInfo;
    private String name;
    private String type;

    public FieldRefTagInfo(DataInputStream in) throws IOException {
	classIndex = in.readShort();
	nameAndTypeIndex = in.readShort();
    }

    @Override
    public String toString() {
	return String.format("FieldRef Tag : class index = %d Name And Type Index = %d (%s %s %s)", classIndex, nameAndTypeIndex, classInfo, name, type);
    }

    public short getClassIndex() {
	return classIndex;
    }

    public short getNameAndTypeIndex() {
	return nameAndTypeIndex;
    }

    @Override
    public void complete(List<ConstantPoolEntry> constantPool) {
	ClassTagInfo classTagInfo = (ClassTagInfo) constantPool.get(classIndex - 1).getInfo();
	classInfo = new String(((UTF8TagInfo) constantPool.get(classTagInfo.getNumber() - 1).getInfo()).getData());

	ConstantPoolEntry temp1 = constantPool.get(nameAndTypeIndex - 1);
	if (temp1 != null && temp1.getInfo() instanceof NameAndTypeTagInfo) {
	    NameAndTypeTagInfo nameAndTypeTagInfo = (NameAndTypeTagInfo) temp1.getInfo();
	    name = new String(((UTF8TagInfo) constantPool.get(nameAndTypeTagInfo.getClassIndex() - 1).getInfo()).getData());
	    type = new String(((UTF8TagInfo) constantPool.get(nameAndTypeTagInfo.getDescriptor() - 1).getInfo()).getData());
	}
    }

    @Override
    public int getSize() {
	return 1;
    }

}
