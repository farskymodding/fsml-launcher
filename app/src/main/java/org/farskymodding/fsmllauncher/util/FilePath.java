package org.farskymodding.fsmllauncher.util;

import java.io.File;

public class FilePath {
   public static String gameDirectory;
   public static String logDirectory;
   public static OS os;

   public static void init() {
      String osName = System.getProperty("os.name");
      if (osName.matches("(?i).*win.*")) {
         os = OS.WINDOWS;
      } else if (osName.matches("(?i).*mac.*")) {
         os = OS.MAC;
      } else {
         os = OS.LINUX;
      }

      switch (os) {
         case WINDOWS:
            gameDirectory = System.getenv("APPDATA") + System.getProperty("file.separator") + "FarSky" + System.getProperty("file.separator");
            break;
         case MAC:
            gameDirectory = System.getProperty("user.home")
               + System.getProperty("file.separator")
               + "Library"
               + System.getProperty("file.separator")
               + ".FarSky"
               + System.getProperty("file.separator");
            break;
         case LINUX:
         default:
            gameDirectory = System.getProperty("user.home") + System.getProperty("file.separator") + ".FarSky" + System.getProperty("file.separator");
      }

      new File(gameDirectory).mkdirs();
      logDirectory = gameDirectory + "log" + System.getProperty("file.separator");
      new File(logDirectory).mkdirs();
   }
}
