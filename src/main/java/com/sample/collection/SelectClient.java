package com.sample.collection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class SelectClient {
    final static int DEFAULT_PORT = 9999;
    static ByteBuffer bb = ByteBuffer.allocateDirect(8);

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if ( args.length > 0 ){
            port = Integer.parseInt(args[0]);
        }

        try {
            SocketChannel sc = SocketChannel.open();
            InetSocketAddress addr = new InetSocketAddress("localhost", port);
            sc.connect(addr);

            long time = 0;
            while( sc.read(bb) != -1 ){
                bb.flip();
                while( bb.hasRemaining() ){
                    time <<= 8;
                    byte b = bb.get();
                    time |= b & 255;
                    System.out.println("b:"+b+" time:"+time);
                }
                bb.clear();
            }
            System.out.println("received:"+time);
            System.out.println(new Date(time));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
