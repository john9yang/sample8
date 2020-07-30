package com.sample.aio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\john_yang\\Study\\Code\\sample8\\src\\main\\resources\\temp.dat", "rw");
        FileChannel fc = raf.getChannel();
        long pos;
        System.out.println("Position = "+(pos = fc.position()));
        System.out.println("Size:"+fc.size());

        String msg = "This is a test message.";
        ByteBuffer buffer = ByteBuffer.allocate(msg.length()*2);
        buffer.asCharBuffer().put(msg);
        fc.write(buffer);
        fc.force(true);
        System.out.println("position:"+fc.position());
        System.out.println("size:"+fc.size());
        buffer.clear();

        fc.position(pos);
        fc.read(buffer);
        buffer.flip();
        while(buffer.hasRemaining()){
            System.out.println(buffer.getChar());
        }
    }
}
