package com.wade.decompile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Decompiler {
    private ClassFileData data;

    public Decompiler(String[] args) throws IOException {
	DataInputStream in = new DataInputStream(new FileInputStream(new File(args[0])));
	data = new ClassFileData(in);
	data.complete();
	in.close();
	System.out.println(data.toString());
    }

    private void disassemble() {
    }

    public static void main(String... args) {
	try {
	    Decompiler dc = new Decompiler(args);
	    dc.disassemble();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
