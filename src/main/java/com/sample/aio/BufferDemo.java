package com.sample.aio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferDemo {
    public static void main(String[] args) {
        Buffer buffer = ByteBuffer.allocate(7);
        System.out.println("Capacity:"+buffer.capacity());
        System.out.println("Limit:"+buffer.limit());
        System.out.println("Position:"+buffer.position());
        System.out.println("Remaining:"+buffer.remaining());
        System.out.println("Changing buffer limit to 5");
        buffer.limit(5);
        System.out.println("Capacity:"+buffer.capacity());
        System.out.println("Limit:"+buffer.limit());
        System.out.println("Position:"+buffer.position());
        System.out.println("Remaining:"+buffer.remaining());
        System.out.println("Changing buffer position to 3");
        buffer.position(3);
        System.out.println("Capacity:"+buffer.capacity());
        System.out.println("Limit:"+buffer.limit());
        System.out.println("Position:"+buffer.position());
        System.out.println("Remaining:"+buffer.remaining());
        System.out.println("Reset");
        buffer.mark().position(4).reset();
        System.out.println("Capacity:"+buffer.capacity());
        System.out.println("Limit:"+buffer.limit());
        System.out.println("Position:"+buffer.position());
        System.out.println("Remaining:"+buffer.remaining());

        System.out.println("Create a new buffer");
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byte[] bytes = new byte[200];
        ByteBuffer buffer2 = ByteBuffer.wrap(bytes);
        ByteBuffer buffer3 = ByteBuffer.wrap(bytes,10,50);
        System.out.println("buffer3:"+buffer3);
    }
}
