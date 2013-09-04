package me.jingege.yaserde;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Manage byte buffers
 * User: jingege
 * Datetime: 9/2/13 12:55 PM
 */
public class BufferFactory {

    //non-final, change it at runtime when necessary
    public static int DEFAULT_BUFFER_SIZE = 32;

    public static ByteBuffer newByteBuffer(){
        return newByteBuffer(DEFAULT_BUFFER_SIZE);
    }

    public static ByteBuffer newByteBuffer(int size){
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer;
    }

    public static ByteBuffer resize(ByteBuffer old,int incrementSize){
        int oldCap = old.capacity();
        if(incrementSize > old.remaining()){

            int newSize = Math.max(oldCap << 1, old.limit()+incrementSize);
            ByteBuffer newBuffer = newByteBuffer(newSize);
            old.flip();
            newBuffer.put(old);
            return newBuffer;
        }else{
             return old;
        }
    }
}
