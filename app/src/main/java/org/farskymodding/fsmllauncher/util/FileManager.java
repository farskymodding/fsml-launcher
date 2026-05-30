package org.farskymodding.fsmllauncher.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileManager {
   public void copy(String sourceFile, String targetFile) {
      InputStream inputStream = null;
      FileOutputStream outputStream = null;
      byte[] var6 = new byte[4096];

      try {
         URL var7 = this.getClass().getResource(sourceFile);
         inputStream = var7.openStream();
         outputStream = new FileOutputStream(new File(targetFile));

         int var5;
         while ((var5 = inputStream.read(var6)) > 0) {
            outputStream.write(var6, 0, var5);
         }
      } catch (IOException var9) {
         var9.printStackTrace();
      }

      try {
         inputStream.close();
         outputStream.close();
      } catch (IOException var8) {
         var8.printStackTrace();
      }
   }
}
