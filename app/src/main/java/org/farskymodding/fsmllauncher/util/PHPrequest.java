package org.farskymodding.fsmllauncher.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class PHPrequest {
   public static String getResponse(String url, String content) throws IOException, URISyntaxException {

      URI uri = new URI(url);

      HttpURLConnection connection = (HttpURLConnection)uri.toURL().openConnection();
      connection.setDoOutput(true);
      connection.setDoInput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      connection.setRequestProperty("charset", "utf-8");
      connection.setRequestProperty("Content-Length", "" + Integer.toString(content.getBytes().length));
      connection.setUseCaches(false);

      DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
      outputStream.writeBytes(content);
      outputStream.flush();
      outputStream.close();

      BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
      byte[] buffer = new byte[1024];
      int bytesRead = 0;
      String output = "";

      while ((bytesRead = inputStream.read(buffer)) != -1) {
         output = new String(buffer, 0, bytesRead);
      }

      connection.disconnect();
      return output;
   }
}
