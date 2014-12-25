package com.wade.decompile;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompile.pool.ConstantPoolEntry;

public class ConstantPool {
    private final short constantPoolSize;
    private List<ConstantPoolEntry> constantPool;

    public ConstantPool(DataInputStream in) throws IOException {
	constantPoolSize = in.readShort();
	constantPool = new ArrayList<ConstantPoolEntry>();
	for (int i = 1; i <= constantPoolSize; i++) {
	    constantPool.add(new ConstantPoolEntry(in));
	}
    }
}
