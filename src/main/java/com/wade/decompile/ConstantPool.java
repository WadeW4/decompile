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
	constantPool = new ArrayList<ConstantPoolEntry>(constantPoolSize - 1);
	for (int i = 1; i <= constantPoolSize - 1; i++) {
	    ConstantPoolEntry element = new ConstantPoolEntry(in);
	    constantPool.add(element);
	    for (int j = 1; j <= element.getSize() - 1; j++) {
		constantPool.add(null);
	    }
	}
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append(String.format("Pool Entries  = %d\n", constantPoolSize - 1));
	for (int i = 0; i < constantPoolSize - 1;) {
	    ConstantPoolEntry entry = constantPool.get(i);
	    if (entry != null) {
		buffer.append(String.format("%03d %s\n", i + 1, entry.toString()));
	    }
	    i += entry.getSize();
	}

	return buffer.toString();
    }

    public void complete() {
	for (ConstantPoolEntry entry : constantPool) {
	    if (entry != null) {
		entry.complete(constantPool);
	    }
	}
    }
}
