package com.wade.decompile;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Decompiler {
    public Decompiler(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(new File(args[0])));
        ClassParser parser = new ClassParser(args[0]);
        JavaClass javaClass = parser.parse();
        System.out.println(javaClass.toString());
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
