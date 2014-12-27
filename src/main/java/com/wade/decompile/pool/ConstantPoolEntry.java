package com.wade.decompile.pool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")
public class ConstantPoolEntry {
    private static final byte CONSTANT_POOL_TAG_UTF8 = 1;
    private static final byte CONSTANT_POOL_TAG_INTEGER = 3;
    private static final byte CONSTANT_POOL_TAG_FLOAT = 4;
    private static final byte CONSTANT_POOL_TAG_LONG = 5;
    private static final byte CONSTANT_POOL_TAG_DOUBLE = 6;
    private static final byte CONSTANT_POOL_TAG_CLASS = 7;
    private static final byte CONSTANT_POOL_TAG_STRING = 8;
    private static final byte CONSTANT_POOL_TAG_FIELDREF = 9;
    private static final byte CONSTANT_POOL_TAG_METHODREF = 10;
    private static final byte CONSTANT_POOL_TAG_INTERFACEMETHODREF = 11;
    private static final byte CONSTANT_POOL_TAG_NAMEANDTYPE = 12;
    private byte tag;
    private TagInfo info;

    public ConstantPoolEntry(DataInputStream in) throws IOException {
	tag = in.readByte();
	switch (tag) {
	    case CONSTANT_POOL_TAG_UTF8:
		info = new UTF8TagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_INTEGER:
		info = new IntegerTagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_FLOAT:
		info = new FloatTagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_LONG:
		info = new LongTagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_DOUBLE:
		info = new DoubleTagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_CLASS:
		info = new ClassTagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_STRING:
		info = new StringTagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_FIELDREF:
		info = new FieldRefTagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_METHODREF:
		info = new MethodRefTagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_INTERFACEMETHODREF:
		info = new InterfaceMethodRefTagInfo(in);
		break;
	    case CONSTANT_POOL_TAG_NAMEANDTYPE:
		info = new NameAndTypeTagInfo(in);
		break;
	}
    }

    @Override
    public String toString() {
	if (info != null) {
	    return info.toString();
	} else {
	    return "empty info  ==> " + tag;
	}
    }

    public void complete(List<ConstantPoolEntry> constantPool) {
	if (info != null) {
	    info.complete(constantPool);
	}
    }

    public TagInfo getInfo() {
	return info;
    }

    public int getSize() {
	if (info == null) {
	    return 1;
	}
	return info.getSize();
    }
}
