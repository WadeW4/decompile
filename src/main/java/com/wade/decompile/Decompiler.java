package com.wade.decompile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Decompiler {
    public Decompiler(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(new File(args[0])));
        ClassFileData data = new ClassFileData(in);
        in.close();
    }

    private void disassemble() {
    }

    public void main(String... args){
        try {
            Decompiler dc = new Decompiler(args);
            dc.disassemble();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
