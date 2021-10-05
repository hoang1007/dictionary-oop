package com.gryffindor.backend.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {
  /** Decompresses the given file. */
  public static void unzip(String zipPath, String location) throws IOException {
    File dest = new File(location);

    byte[] buffer = new byte[1024];

    try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
      for (ZipEntry zipEntry = zis.getNextEntry(); 
                        zipEntry != null; zipEntry = zis.getNextEntry()) {
        File newFile = newFile(dest, zipEntry);

        if (zipEntry.isDirectory()) {
          if (!newFile.isDirectory() && !newFile.mkdirs()) {
            throw new IOException("Failed to create directory " + newFile);
          }
        } else {
          File parent = newFile.getParentFile();

          if (!parent.isDirectory() && !parent.mkdirs()) {
            throw new IOException("Failed to create directory " + parent);
          }

          FileOutputStream fos = new FileOutputStream(newFile);
          
          for (int len; (len = zis.read(buffer)) > 0; ) {
            fos.write(buffer, 0, len);
          }

          fos.close();
        }
      }

      zis.closeEntry();
    }
  }

  static File newFile(File destination, ZipEntry entry) throws IOException {
    File destFile = new File(destination, entry.getName());

    String destDirPath = destination.getCanonicalPath();
    String destFilePath = destFile.getCanonicalPath();

    if (!destFilePath.startsWith(destDirPath + File.separator)) {
      throw new IOException("Entry outside of the target dir: " + entry.getName());
    }

    return destFile;
  }
}
