package me.jingege.yaserde;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Byte deflater
 * <p>
 *      A byte container in general.We pop data from a ByteBuffer.
 * </p>
 * User: jingege
 * Datetime: 8/31/13 12:13 AM
 */
public class Deflat {

    ByteBuffer buffer;

    public Deflat(){
        ;//call deflat() before pop anything
    }

    public Deflat(byte[] bytes){
        buffer = BufferFactory.newByteBuffer();
        buffer = BufferFactory.resize(buffer,bytes.length);
        buffer.put(bytes);
        buffer.flip();
    }

    public void deflat(byte[] bytes){
        if(buffer != null){
            throw new IllegalStateException("This deflat is already binded");
        }else{
            buffer = BufferFactory.newByteBuffer(bytes.length);
            buffer.put(bytes);
            buffer.flip();
        }

    }

    private byte[] pop(int length){
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return bytes;
    }

    public byte popByte(){
        return buffer.get();
    }

    public byte[] popBytes(){
        short length = buffer.getShort();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return bytes;
    }
    public String popString(String charset){
        byte[] bytes = pop(buffer.getShort());
        if(bytes.length == 0){
            return null;
        }
        try {
            return new String(bytes,charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String popString(){
        return popString(Yaserder.CHARSET_DEFAULT);
    }

    public boolean popBoolean(){
        return buffer.get() == 0X1;
    }

    public char popChar(){
        return buffer.getChar();
    }

    public int popInt(){
        return buffer.getInt();
    }

    public short popShort(){
        return buffer.getShort();
    }

    public double popDouble(){
        return buffer.getDouble();
    }

    public float popFloat(){
        return buffer.getFloat();
    }

    public long popLong(){
        return buffer.getLong();
    }

    public <T extends Yaserder> T popYaserder(T t){
        t.deflate(this);
        return t;
    }

}
