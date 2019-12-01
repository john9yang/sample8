//package com.sample.forkjoin;
//
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//import java.util.List;
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.TimeUnit;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class ConcurrentLauncher {
//  private static final Logger logger = LogManager.getLogger(ConcurrentLauncher.class);
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
//      logger.debug("summaries size:{}",summaries.size());
//      startDownloadConcurrent(summaries,s3client);
//      while( listing.isTruncated() ){
//        summaries.clear();
//        try{
//          listing = s3client.listNextBatchOfObjects(listing);
//        }
//        catch (Exception e){
//          logger.error(e.getMessage());
//          for( int i =0;i<5;i++ ){
//            try{
//              listing = s3client.listNextBatchOfObjects(listing);
//              break;
//            }
//            catch (Exception ex){
//              logger.error(ex.getMessage());
//            }
//          }
//        }
//
//        logger.debug("summaries size:{}",listing.getObjectSummaries().size());
//        summaries.addAll(listing.getObjectSummaries());
//        startDownloadConcurrent(summaries,s3client);
//      }
//
//    }
//
//  }
//
//  private static void startDownloadConcurrent(List<S3ObjectSummary> summaries,AmazonS3 s3client){
//
//    try{
//      ZipDownloadProcessor processor = new ZipDownloadProcessor(summaries,s3client);
//      ForkJoinPool pool = new ForkJoinPool();
////      pool.execute(processor);
//
//      pool.invoke(processor);
//
////      do {
////        logger.debug("Main:Thread Count:{},Thread Steal:{},Parallelism:{}",
////            pool.getActiveThreadCount(),pool.getStealCount(),pool.getParallelism());
////        try {
////          TimeUnit.MILLISECONDS.sleep(5);
////        } catch (InterruptedException e) {
////          e.printStackTrace();
////        }
////      } while (!processor.isDone());
////
//      pool.shutdown();
//      if ( processor.isCompletedNormally() ){
//        logger.info("Main: The process {} has completed normally!:-)",processor);
//      }
//    }
//    catch(Exception e){
//      e.printStackTrace();
//    }
//
//  }
//
//}
