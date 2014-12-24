package com.wade.decompile;

import java.io.DataInputStream;
import java.io.IOException;

public class ClassFileData {
    private final byte magicNumber[]=new byte[4];
    private final short minorVersion;

    public ClassFileData(DataInputStream in) throws IOException {
        in.read(magicNumber);
        minorVersion = in.readShort();
    }
}
