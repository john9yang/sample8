package com.sample.aio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class FileChannelMapDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\john_yang\\Study\\Code\\sample8\\resources\\poem.txt", "rw");
        FileChannel fc = raf.getChannel();
        long size = fc.size();
        System.out.println("size:"+size);
        FileChannel.MapMode mode;
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, size);
        while(mbb.hasRemaining()){
            System.out.print((char)mbb.get());
        }
        System.out.println();
        System.out.println("mbb:"+mbb);
        System.out.println();

        for (int i = 0; i < mbb.limit()/2; i++) {
            byte b1 = mbb.get(i);
            byte b2 = mbb.get(mbb.limit()-i-1);
            mbb.put(i,b2);
            mbb.put(mbb.limit()-i-1,b1);
        }
        mbb.flip();
        while(mbb.hasRemaining()){
            System.out.print((char)mbb.get());
        }
        fc.close();
    }
}
