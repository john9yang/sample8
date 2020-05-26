package com.sample.nio;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;

/**
 * @author John_Yang
 */
public class WorkingWithNIOPath {

  public static void main(String[] args) {
    defineAbsolutePath();
    defineRelativePathToRoot();
    definePathFromURI();
  }

  //Starts with file store root or drive
  private static void defineAbsolutePath(){
    Path absolutePath1 = Paths.get("C:/Users/John_Yang/Downloads","log1.txt");
    System.out.println(absolutePath1.toString());
    Path absolutePath2 = Paths.get("C:/Users/John_yang/","Downloads","log1.txt");
    System.out.println(absolutePath2.toString());
  }

  //Starts with a "/"
  private static void defineRelativePathToRoot(){
    Path relativePath1 = FileSystems.getDefault().getPath("/Users/John_yang","Downloads","log1.txt");
    System.out.println(relativePath1.toAbsolutePath().toString());
  }

  private static void definePathFromURI(){
    URI uri = URI.create("file://Users/John_yang/Downloads/log1.txt");
    String scheme = uri.getScheme();
    if ( scheme == null ){
      throw new IllegalArgumentException("Missing scheme");
    }
    if (scheme.equalsIgnoreCase("file")) {
      System.out.println(FileSystems.getDefault().provider().getPath(uri).toAbsolutePath().toString());
    }
    // try to find provider
    for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
      if (provider.getScheme().equalsIgnoreCase(scheme)) {
        System.out.println(provider.getPath(uri).toAbsolutePath().toString());
        break;
      }
    }
  }
}
