//package com.sample.forkjoin;
//
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.event.ProgressEvent;
//import com.amazonaws.event.ProgressListener;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//import com.amazonaws.services.s3.transfer.Download;
//import com.amazonaws.services.s3.transfer.Transfer.TransferState;
//import com.amazonaws.services.s3.transfer.TransferManager;
//import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.RecursiveAction;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class ZipDownloadProcessor extends RecursiveAction {
//  private static final Logger logger = LogManager.getLogger(ZipDownloadProcessor.class);
//  private List<S3ObjectSummary> s3ObjectSummaries;
//  private AmazonS3 s3client;
//
//  public ZipDownloadProcessor(List<S3ObjectSummary> s3ObjectSummaries,AmazonS3 s3client) {
//    this.s3ObjectSummaries = s3ObjectSummaries;
//    this.s3client = s3client;
//  }
//
//  @Override
//  protected void compute() {
//      if( s3ObjectSummaries.size() > 10 ){
//        downloadDirectly();
//      }
//      else{
//        logger.debug("Task:Pending tasks:{}",getQueuedTaskCount());
//        invokeAll(createSubtasks());
//      }
//  }
//
//  private List<ZipDownloadProcessor> createSubtasks() {
//    List<ZipDownloadProcessor> subtasks = new ArrayList<>();
//
//    List<S3ObjectSummary> partOne = s3ObjectSummaries.subList(0, s3ObjectSummaries.size() / 2);
//    List<S3ObjectSummary> partTwo = s3ObjectSummaries.subList(s3ObjectSummaries.size() / 2,s3ObjectSummaries.size());
//
//    subtasks.add(new ZipDownloadProcessor(partOne,s3client));
//    subtasks.add(new ZipDownloadProcessor(partTwo,s3client));
//
//    return subtasks;
//  }
//
//  private void downloadDirectly(){
//    TransferManager xfer_mgr = TransferManagerBuilder.standard().withS3Client(s3client).build();
//    for (S3ObjectSummary summary : s3ObjectSummaries) {
//      try {
//        File dest = new File("D:\\DNBData\\"+summary.getKey());
//        if (dest.exists() ){
//          logger.info("data already exists:{}",dest.getAbsolutePath());
//          continue;
//        }
//        logger.debug("downloading object:{}",summary);
//        Download xfer = xfer_mgr.download(summary.getBucketName(), summary.getKey(), dest);
//        xfer.addProgressListener(new ProgressListener() {
//          public void progressChanged(ProgressEvent e) {
//            double pct = e.getBytesTransferred() * 100.0 / e.getBytes();
//            logger.debug("progress:{}",pct);
//          }
//        });
//        xfer.waitForCompletion();
//        TransferState xfer_state = xfer.getState();
//        logger.debug("state:{}",xfer_state);
//      } catch (AmazonServiceException e) {
//        logger.error(e.getErrorMessage());
//        System.exit(1);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }
//    xfer_mgr.shutdownNow(false);
//  }
//
//  @Override
//  public String toString() {
//    return "ZipDownloadProcessor{" +
//        ", s3ObjectSummaries=" + s3ObjectSummaries +
//        ", s3client=" + s3client +
//        '}';
//  }
//}