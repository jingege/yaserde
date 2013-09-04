package me.jingege.yaserde;

/**
 * The type this framework supported
 * User: jingege
 * Datetime: 8/30/13 5:19 PM
 */
public interface Yaserder{

    public static final String CHARSET_DEFAULT = "UTF8";

    public static final byte BYTE_ZERO = (byte)0;
    public static final byte BYTE_FALSE = BYTE_ZERO;
    public static final byte BYTE_TRUE = (byte)1;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    public void inflate(Inflat inflat);

    public void deflate(Deflat deflat);

}
