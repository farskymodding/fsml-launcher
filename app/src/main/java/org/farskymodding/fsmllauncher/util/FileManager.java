package org.farskymodding.fsmllauncher.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
   public void copy(String sourceFile, String targetFile) {
      InputStream inputStream = null;
      FileOutputStream outputStream = null;
      byte[] buffer = new byte[4096];

      try {
         URL sourceUri = this.getClass().getResource(sourceFile);
         inputStream = sourceUri.openStream();
         outputStream = new FileOutputStream(new File(targetFile));

         int bytesRead;
         while ((bytesRead = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, bytesRead);
         }
      } catch (IOException var9) {
         var9.printStackTrace();
      }

      try {
         inputStream.close();
         outputStream.close();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }
   }

   public static boolean writeToFile(Path file, String content) {
      try {
         Files.writeString(file, content);
         return true;
      } catch (IOException e) {
         e.printStackTrace();
         return false;
      }
   }
}
