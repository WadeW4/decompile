package org.apache.bcel.classfile;

/**
 * Thrown when the BCEL attempts to read a class file and determines that the
 * file is malformed or otherwise cannot be interpreted as a class file.
 *
 * @author <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 * @version $Id: ClassFormatException.java 386056 2006-03-15 11:31:56Z tcurdt $
 */
public class ClassFormatException extends RuntimeException {
    private static final long serialVersionUID = -2999337718592214688L;

    public ClassFormatException() {
	super();
    }

    public ClassFormatException(String s) {
	super(s);
    }
}
