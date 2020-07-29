package com.sample.aio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

public class ChannelDemo {
    public static void main(String[] args) throws IOException {
        ScatteringByteChannel src;
        FileInputStream fis = new FileInputStream("C:\\Users\\john_yang\\Study\\Code\\sample8\\src\\main\\resources\\x.dat");
        src = fis.getChannel();
        ByteBuffer buffer1 = ByteBuffer.allocate(5);
        ByteBuffer buffer2 = ByteBuffer.allocate(3);
        ByteBuffer[] buffers = {buffer1,buffer2};
        src.read(buffers);

        buffer1.flip();
        while (buffer1.hasRemaining()){
            System.out.println(buffer1.get());
        }

        buffer2.flip();
        while(buffer2.hasRemaining()){
            System.out.println(buffer2.get());
        }

        buffer1.rewind();
        buffer2.rewind();

        GatheringByteChannel dest;
        FileOutputStream fos = new FileOutputStream("C:\\Users\\john_yang\\Study\\Code\\sample8\\src\\main\\resources\\y.dat");
        dest = (GatheringByteChannel) Channels.newChannel(fos);
        buffers[0]=buffer2;
        buffers[1]=buffer1;
        dest.write(buffers);
    }
}
