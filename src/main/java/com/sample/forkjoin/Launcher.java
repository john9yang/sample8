//package com.sample.forkjoin;
//
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.event.ProgressEvent;
//import com.amazonaws.event.ProgressListener;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//import com.amazonaws.services.s3.transfer.Download;
//import com.amazonaws.services.s3.transfer.Transfer.TransferState;
//import com.amazonaws.services.s3.transfer.TransferManager;
//import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
//import java.io.File;
//import java.util.List;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class Launcher {
//  private static final Logger logger = LogManager.getLogger(Launcher.class);
//
//  public static void main(String[] args) {
//    ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider("tr-fr-preprod");
//    AmazonS3 s3client = AmazonS3ClientBuilder.standard().
//                               withCredentials(credentialsProvider).withRegion(Regions.US_EAST_1).build();
//    String bucketName="pcfp-dnb-output-full";
//    if ( s3client.doesBucketExistV2(bucketName) ){
//      logger.debug("find s3 bucket:{}",bucketName);
//      ObjectListing listing = s3client.listObjects(bucketName,"DnB_Output_Full_3PR");
//      List<S3ObjectSummary> summaries = listing.getObjectSummaries();
//      downloadS3ObjectSerial(summaries,s3client);
//      while( listing.isTruncated() ){
//        summaries.clear();
//        listing = s3client.listNextBatchOfObjects(listing);
//        summaries.addAll(listing.getObjectSummaries());
//        downloadS3ObjectSerial(summaries,s3client);
//      }
//
//    }
//
//  }
//
//  private static void downloadS3ObjectSerial(List<S3ObjectSummary> summaries,AmazonS3 s3client){
//    TransferManager xfer_mgr = TransferManagerBuilder.standard().withS3Client(s3client).build();
//    for (S3ObjectSummary summary : summaries) {
//      logger.debug("downloading object:{}",summary);
//      try {
//        File dest = new File("D:\\DNBData\\"+summary.getKey());
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
//    xfer_mgr.shutdownNow();
//  }
//}
