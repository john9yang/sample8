package com.sample.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import sun.net.ftp.FtpProtocolException;

public class DownloadFileNIO {

  public void download(String fileURL,String localFileName) throws IOException, FtpProtocolException {
    URL url = new URL(fileURL);
    try( ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
         FileOutputStream fileOutputStream = new FileOutputStream(localFileName);
         FileChannel fileChannel = fileOutputStream.getChannel()){
      fileChannel.transferFrom(readableByteChannel,0,Long.MAX_VALUE);
    }
  }

}