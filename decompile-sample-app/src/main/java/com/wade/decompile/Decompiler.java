package com.wade.decompile;

import java.io.IOException;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Utility;

public class Decompiler {
    private JavaClass javaClass;

    public Decompiler(String[] args) throws IOException {
	ClassParser parser = new ClassParser(args[0]);
	javaClass = parser.parse();
	System.out.println(javaClass.toString());
    }

    private void disassemble() throws ClassNotFoundException {
	StringBuffer buf = new StringBuffer();

	String access = Utility.accessToString(javaClass.getAccessFlags(), true);
	access = access.equals("") ? "" : (access + " ");

	String classOrInterface = Utility.classOrInterface(javaClass.getAccessFlags());
	String superClass = Utility.compactClassName(javaClass.getSuperclassName(), false);
	String className = javaClass.getClassName();
	String interfaceClasses = Utility.getListOfInterfaces(javaClass.getAllInterfaces());
	String fields = Utility.getFields("\t", javaClass.getFields());
	String methods = Utility.getMethods("\t", javaClass.getMethods(), className);

	buf.append(String.format("%s%s %s extends %s implements %s {\n", access, classOrInterface, className, superClass, interfaceClasses));
	buf.append(fields);
	buf.append("\n");
	buf.append(methods);
	buf.append("}\n");

	System.out.println(buf.toString());
    }

    public static void main(String... args) {
	try {
	    Decompiler dc = new Decompiler(args);
	    dc.disassemble();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
