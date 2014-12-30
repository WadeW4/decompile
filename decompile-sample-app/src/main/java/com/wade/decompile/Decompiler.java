package com.wade.decompile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

public class Decompiler {
    public Decompiler(String[] args) throws IOException {
	DataInputStream in = new DataInputStream(new FileInputStream(new File(args[0])));
	ClassParser parser = new ClassParser(args[0]);
	JavaClass javaClass = parser.parse();

	System.out.println("*******Constant Pool*********");
	System.out.println(javaClass.getConstantPool());

	System.out.println("*******Fields*********");
	System.out.println(Arrays.toString(javaClass.getFields()));
	System.out.println();

	System.out.println("*******Methods*********");
	System.out.println(Arrays.toString(javaClass.getMethods()));

	for (Method method : javaClass.getMethods()) {
	    System.out.println(method);
	    System.out.println(method.getCode());
	}
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
