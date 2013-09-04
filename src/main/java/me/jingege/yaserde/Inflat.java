package me.jingege.yaserde;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Inflat data to a byte buffer
 * User: jingege
 * Datetime: 8/31/13 12:13 AM
 */
public class Inflat {

    private ByteBuffer buffer;

    public Inflat(){
        buffer = BufferFactory.newByteBuffer();
    }

    public Inflat(byte[] bytes){
        this();
        buffer = BufferFactory.resize(buffer,bytes.length);
        buffer.put(bytes);
    }

    public Inflat pushByte(byte b){
        buffer.put(b);
        return this;
    }

    public Inflat pushBytes(byte[] bytes){
        if(bytes == null){
            buffer.put(Yaserder.BYTE_ZERO);
            bytes = Yaserder.EMPTY_BYTE_ARRAY;
        }else{
            buffer.putShort((short)bytes.length);
        }
        buffer.put(bytes);
        return this;
    }

    public Inflat pushString(String str){
        return pushString(str,Yaserder.CHARSET_DEFAULT);
    }

    public Inflat pushString(String str,String charset){
        try {
            byte[] bytes;
            if(str == null){
                bytes = Yaserder.EMPTY_BYTE_ARRAY;
            }else{
                bytes = str.getBytes(charset);
                if(bytes.length > Short.MAX_VALUE){
                   throw new IllegalArgumentException("String is too long,use pushLongString instead");
                }
            }
            buffer = BufferFactory.resize(buffer,2 + bytes.length);
            buffer.putShort((short)bytes.length);
            buffer.put(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Inflat pushLongString(String str,String charset){
        try {
            byte[] bytes;
            if(str == null){
                bytes = Yaserder.EMPTY_BYTE_ARRAY;
            }else{
                bytes = str.getBytes(charset);
                if(bytes.length > Integer.MAX_VALUE){
                    throw new IllegalArgumentException("Too long string,not supported");
                }
            }
            buffer = BufferFactory.resize(buffer,4 + bytes.length);
            buffer.putInt(bytes.length);
            buffer.put(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Inflat pushBoolean(boolean bool){
        buffer = BufferFactory.resize(buffer,1);
        buffer.put(bool?Yaserder.BYTE_TRUE:Yaserder.BYTE_FALSE);
        return this;
    }

    public Inflat pushInt(int aint){
        buffer = BufferFactory.resize(buffer,4);
        buffer.putInt(aint);
        return this;
    }

    public Inflat pushShort(short ashort){
        buffer = BufferFactory.resize(buffer,2);
        buffer.putShort(ashort);
        return this;
    }

    public Inflat pushChar(char achar){
        buffer = BufferFactory.resize(buffer,1);
        buffer.putChar(achar);
        return this;
    }

    public Inflat pushLong(long along){
        buffer = BufferFactory.resize(buffer,8);
        buffer.putLong(along);
        return this;
    }

    public Inflat pushFloat(float afloat){
        buffer = BufferFactory.resize(buffer,4);
        buffer.putFloat(afloat);
        return this;
    }

    public Inflat pushDouble(double adouble){
        buffer = BufferFactory.resize(buffer,8);
        buffer.putDouble(adouble);
        return this;
    }

    public Inflat pushInflat(Inflat inflat){
        byte[] bytes = inflat.getBytes();
        buffer = BufferFactory.resize(buffer,bytes.length);
        buffer.put(bytes);
        return this;
    }

    public Inflat pushYaserder(Yaserder yaserder){
        yaserder.inflate(this);
        return this;
    }

    public byte[] getBytes(){
        buffer.flip();
        return buffer.array();
    }
}
