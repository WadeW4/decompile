package org.apache.bcel.classfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Constants;

/**
 * This class represents a chunk of Java byte code contained in a method. It is
 * instantiated by the <em>Attribute.readAttribute()</em> method. A
 * <em>Code</em> attribute contains informations about operand stack, local
 * variables, byte code and the exceptions handled within this method.
 * <p/>
 * This attribute has attributes itself, namely <em>LineNumberTable</em> which
 * is used for debugging purposes and <em>LocalVariableTable</em> which contains
 * information about the local variables.
 *
 * @author <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 * @version $Id: Code.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @see Attribute
 * @see CodeException
 * @see LineNumberTable
 * @see LocalVariableTable
 */
public final class Code extends Attribute {
    private static final long serialVersionUID = 2479862991086148192L;
    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private byte[] code;
    private int exceptionTableLength;
    private CodeException[] exceptionTable;
    private int attributesCount;
    private Attribute[] attributes;

    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use copy() for a physical copy.
     */
    public Code(Code c) {
	this(c.getNameIndex(), c.getLength(), c.getMaxStack(), c.getMaxLocals(), c.getCode(), c.getExceptionTable(), c.getAttributes(), c.getConstantPool());
    }

    /**
     * @param name_index
     *            Index pointing to the name <em>Code</em>
     * @param length
     *            Content length in bytes
     * @param file
     *            Input stream
     * @param constant_pool
     *            Array of constants
     */
    Code(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
	this(name_index, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[]) null, (CodeException[]) null, (Attribute[]) null, constant_pool);
	codeLength = file.readInt();
	code = new byte[codeLength];
	file.readFully(code);

	exceptionTableLength = file.readUnsignedShort();
	exceptionTable = new CodeException[exceptionTableLength];
	for (int i = 0; i < exceptionTableLength; i++) {
	    exceptionTable[i] = new CodeException(file);
	}

	attributesCount = file.readUnsignedShort();
	attributes = new Attribute[attributesCount];
	for (int i = 0; i < attributesCount; i++) {
	    attributes[i] = Attribute.readAttribute(file, constant_pool);
	}

	this.length = length;
    }

    /**
     * @param name_index
     *            Index pointing to the name <em>Code</em>
     * @param length
     *            Content length in bytes
     * @param max_stack
     *            Maximum size of stack
     * @param max_locals
     *            Number of local variables
     * @param code
     *            Actual byte code
     * @param exception_table
     *            Table of handled exceptions
     * @param attributes
     *            Attributes of code: LineNumber or LocalVariable
     * @param constant_pool
     *            Array of constants
     */
    public Code(int name_index, int length, int max_stack, int max_locals, byte[] code, CodeException[] exception_table, Attribute[] attributes, ConstantPool constant_pool) {
	super(Constants.ATTR_CODE, name_index, length, constant_pool);
	this.maxStack = max_stack;
	this.maxLocals = max_locals;
	setCode(code);
	setExceptionTable(exception_table);
	setAttributes(attributes); // Overwrites length!
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v
     *            Visitor object
     */
    @Override
    public void accept(Visitor v) {
	v.visitCode(this);
    }

    /**
     * Dump code attribute to file stream in binary format.
     *
     * @param file
     *            Output file stream
     * @throws IOException
     */
    @Override
    public final void dump(DataOutputStream file) throws IOException {
	super.dump(file);
	file.writeShort(maxStack);
	file.writeShort(maxLocals);
	file.writeInt(codeLength);
	file.write(code, 0, codeLength);
	file.writeShort(exceptionTableLength);
	for (int i = 0; i < exceptionTableLength; i++) {
	    exceptionTable[i].dump(file);
	}
	file.writeShort(attributesCount);
	for (int i = 0; i < attributesCount; i++) {
	    attributes[i].dump(file);
	}
    }

    /**
     * @return Collection of code attributes.
     * @see Attribute
     */
    public final Attribute[] getAttributes() {
	return attributes;
    }

    /**
     * @return LineNumberTable of Code, if it has one
     */
    public LineNumberTable getLineNumberTable() {
	for (int i = 0; i < attributesCount; i++) {
	    if (attributes[i] instanceof LineNumberTable) {
		return (LineNumberTable) attributes[i];
	    }
	}
	return null;
    }

    /**
     * @return LocalVariableTable of Code, if it has one
     */
    public LocalVariableTable getLocalVariableTable() {
	for (int i = 0; i < attributesCount; i++) {
	    if (attributes[i] instanceof LocalVariableTable) {
		return (LocalVariableTable) attributes[i];
	    }
	}
	return null;
    }

    /**
     * @return Actual byte code of the method.
     */
    public final byte[] getCode() {
	return code;
    }

    /**
     * @return Table of handled exceptions.
     * @see CodeException
     */
    public final CodeException[] getExceptionTable() {
	return exceptionTable;
    }

    /**
     * @return Number of local variables.
     */
    public final int getMaxLocals() {
	return maxLocals;
    }

    /**
     * @return Maximum size of stack used by this method.
     */
    public final int getMaxStack() {
	return maxStack;
    }

    /**
     * @return the internal length of this code attribute (minus the first 6
     *         bytes) and excluding all its attributes
     */
    private final int getInternalLength() {
	return 2 /* max_stack */+ 2 /* max_locals */+ 4 /* code length */
		+ codeLength /* byte-code */
		+ 2 /* exception-table length */
		+ 8 * exceptionTableLength /* exception table */
		+ 2 /* attributes count */;
    }

    /**
     * @return the full size of this code attribute, minus its first 6 bytes,
     *         including the size of all its contained attributes
     */
    private final int calculateLength() {
	int len = 0;
	for (int i = 0; i < attributesCount; i++) {
	    len += attributes[i].length + 6 /* attribute header size */;
	}
	return len + getInternalLength();
    }

    /**
     * @param attributes
     *            the attributes to set for this Code
     */
    public final void setAttributes(Attribute[] attributes) {
	this.attributes = attributes;
	attributesCount = (attributes == null) ? 0 : attributes.length;
	length = calculateLength(); // Adjust length
    }

    /**
     * @param code
     *            byte code
     */
    public final void setCode(byte[] code) {
	this.code = code;
	codeLength = (code == null) ? 0 : code.length;
    }

    /**
     * @param exception_table
     *            exception table
     */
    public final void setExceptionTable(CodeException[] exception_table) {
	this.exceptionTable = exception_table;
	exceptionTableLength = (exception_table == null) ? 0 : exception_table.length;
    }

    /**
     * @param max_locals
     *            maximum number of local variables
     */
    public final void setMaxLocals(int max_locals) {
	this.maxLocals = max_locals;
    }

    /**
     * @param max_stack
     *            maximum stack size
     */
    public final void setMaxStack(int max_stack) {
	this.maxStack = max_stack;
    }

    /**
     * @return String representation of code chunk.
     */
    public final String toString(boolean verbose, String indent) {
	StringBuffer buf;
	buf = new StringBuffer(100);
	buf.append(String.format("Code(max_stack = %s, max_locals = %s, code_length = %s)\n%s", maxStack, maxLocals, codeLength, Utility.codeToString(code, constant_pool, 0, -1, verbose, indent)));
	if (exceptionTableLength > 0) {
	    buf.append("\n" + indent + "Exception handler(s) = \n").append("From\tTo\tHandler\tType\n");
	    for (int i = 0; i < exceptionTableLength; i++) {
		buf.append(exceptionTable[i].toString(constant_pool, verbose)).append("\n");
	    }
	}
	if (attributesCount > 0) {
	    buf.append("\n" + indent + "Attribute(s) = \n");
	    for (int i = 0; i < attributesCount; i++) {
		buf.append(attributes[i].toString(indent)).append("\n");
	    }
	}
	return buf.toString();
    }

    /**
     * @return String representation of code chunk.
     */
    @Override
    public final String toString(String indent) {
	return toString(true, indent);
    }

    /**
     * @return String representation of code chunk.
     */
    @Override
    public final String toString() {
	return toString(true, "");
    }

    /**
     * @param _constant_pool
     *            the constant pool to duplicate
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy(ConstantPool _constant_pool) {
	Code c = (Code) clone();
	if (code != null) {
	    c.code = new byte[code.length];
	    System.arraycopy(code, 0, c.code, 0, code.length);
	}
	c.constant_pool = _constant_pool;
	c.exceptionTable = new CodeException[exceptionTableLength];
	for (int i = 0; i < exceptionTableLength; i++) {
	    c.exceptionTable[i] = exceptionTable[i].copy();
	}
	c.attributes = new Attribute[attributesCount];
	for (int i = 0; i < attributesCount; i++) {
	    c.attributes[i] = attributes[i].copy(_constant_pool);
	}
	return c;
    }
}
