package org.farskymodding.fsmllauncher.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
   public static void process(String zipFilePath, String outputDir) {
      byte[] buffer = new byte[1024];

      try {
         File var3 = new File(outputDir);
         if (!var3.exists()) {
            var3.mkdir();
         }

         ZipInputStream inputStream = new ZipInputStream(new FileInputStream(zipFilePath));

         for (ZipEntry zipEntry = inputStream.getNextEntry(); zipEntry != null; zipEntry = inputStream.getNextEntry()) {
            String entryName = zipEntry.getName();
            File outputFile = new File(outputDir + File.separator + entryName);
            if (zipEntry.isDirectory()) {
               new File(outputFile.getParent()).mkdirs();
            } else {
               new File(outputFile.getParent()).mkdirs();
               FileOutputStream outputStream = new FileOutputStream(outputFile);

               int bytesRead;
               while ((bytesRead = inputStream.read(buffer)) > 0) {
                  outputStream.write(buffer, 0, bytesRead);
               }

               outputStream.close();
            }
         }

         inputStream.closeEntry();
         inputStream.close();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }
   }
}
