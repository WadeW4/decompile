package com.wade.decompile.pool;

import java.util.List;

public abstract class TagInfo {
    @Override
    public abstract String toString();

    public abstract void complete(List<ConstantPoolEntry> constantPool);

    public abstract int getSize();
}
