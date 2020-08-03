package com.sample.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectServer {
    final static int DEFAULT_PORT = 9999;
    static ByteBuffer bb = ByteBuffer.allocateDirect(8);

    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;

        if ( args.length > 0 ) {
            port = Integer.parseInt(args[0]);
        }

        System.out.println("Server starting ... listening on port "+port);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket ss = ssc.socket();
        ss.bind(new InetSocketAddress(port));

        Selector s = Selector.open();
        ssc.register(s, SelectionKey.OP_ACCEPT);

        while(true){
            int n = s.select();
            if ( n == 0 ){
                continue;
            }

            Iterator it = s.selectedKeys().iterator();
            while( it.hasNext() ){
                SelectionKey key = (SelectionKey) it.next();
                if ( key.isAcceptable() ){
                    SocketChannel sc;
                    sc = ((ServerSocketChannel)key.channel()).accept();
                    if ( sc == null ){
                        continue;
                    }
                    System.out.println("Receiving connection");
                    bb.clear();
                    long now = System.currentTimeMillis();
                    System.out.println("now:"+now);
                    bb.putLong(now);
                    bb.flip();
                    System.out.println("Writting current time");
                    while(bb.hasRemaining()){
                        System.out.println("Writting");
                        sc.write(bb);
                    }
                    sc.close();
                }
                it.remove();
            }
        }
    }
}
