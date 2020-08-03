package com.sample.aio;

import java.nio.ByteBuffer;
import java.util.Date;

public class ByteBufferDemo {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocateDirect(8);
        bb.clear();
        System.out.println(System.currentTimeMillis());
        bb.putLong(System.currentTimeMillis());
        bb.flip();
        long time = 0;
        bb.clear();
        time = bb.getLong();
        System.out.println(time);
        System.out.println(new Date(time));
    }
}
