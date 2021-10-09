package com.gryffindor.backend.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.lingala.zip4j.ZipFile;

public class FileUtils {
  public static void unzip(String zipPath, String destDir) throws IOException {
    try (ZipFile zipFile = new ZipFile(zipPath)) {
      zipFile.extractAll(destDir);
    }
  }

  public static void copy(InputStream src, File dest) {
    try (OutputStream outstream = new BufferedOutputStream(new FileOutputStream(dest))) {
      byte[] buffer = new byte[1024];

      int lengthRead;
      while ((lengthRead = src.read(buffer)) > 0) {
        outstream.write(buffer, 0, lengthRead);
        outstream.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
