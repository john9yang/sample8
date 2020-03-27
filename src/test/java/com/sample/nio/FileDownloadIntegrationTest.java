package com.sample.nio;

import java.io.IOException;
import org.junit.Test;
import sun.net.ftp.FtpProtocolException;

public class FileDownloadIntegrationTest {

  static String FILE_URL =
      "ftp://ECOM\\SGZR-PR-RSAFDN:Fdnrsa!23@stargazer-ftp-hzl.int.thomsonreuters.com/DnBWBInput/25Jan-WB-JAN2020/WB.ACTIVE.JAN20_02_OF_14.gz";
  static String FILE_NAME = "C:\\Users\\John_Yang\\Downloads\\WB.ACTIVE.JAN20_02_OF_14.gz";

  @Test
  public void downloadFileNIO() throws IOException, FtpProtocolException {
    DownloadFileNIO downloadFileNIO = new DownloadFileNIO();
    downloadFileNIO.download(FILE_URL,FILE_NAME);
  }
}
