package com.sample.lambda;

import java.io.File;
import java.io.FileFilter;

public class FileListDemo {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\John_Yang");
        File[] txtFiles = file.listFiles(new FileFilter(){
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".DAT");
            }
        });

        File[] txtFilesInJava8 = file.listFiles( pathname -> pathname.getName().endsWith(".DAT") );

        System.out.println(txtFiles.length);
        System.out.println(txtFilesInJava8.length);
    }
}