package com.sample.forkjoin;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CountFileDemo {

	public static void main(String[] args) throws InterruptedException,ExecutionException{
		ForkJoinPool forJoinPool = new ForkJoinPool();
		Integer count = forJoinPool.invoke(new CountingTask(Paths.get("D://StudyDoc")));
		System.out.println("D盘总文件数："+count);
		System.out.println("Thread Main End!");
		
	}

}

class CountingTask extends RecursiveTask<Integer>{
	private static final long serialVersionUID = 5083961640462355611L;
	private Path dir;
	
	public CountingTask(Path dir) {
		this.dir = dir;
	}

	@Override
	protected Integer compute() {
		int count = 0;
		List<CountingTask> subTasks = new ArrayList<CountingTask>();
		
		try {
			DirectoryStream<Path> ds = Files.newDirectoryStream(dir);
			for( Path subPath :ds ) {
				if ( Files.isDirectory(subPath, LinkOption.NOFOLLOW_LINKS)) {
					//对每个子目录都新建一个子任务
					subTasks.add(new CountingTask(subPath));
				}
				else
				{
					count++;
				}
			}
			
			if( !subTasks.isEmpty()) {
				//在当前的ForkJoinPool上调度所有的子任务
				for( CountingTask subTask : invokeAll(subTasks)) {
					count += subTask.get();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
		return count;
	}
}