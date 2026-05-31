package org.farskymodding.fsmllauncher;

import org.farskymodding.fsmllauncher.util.FilePath;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Play {
   public static void launchGame() {
      ArrayList<String> gameArgs = new ArrayList<>();
      String fileSeperator = System.getProperty("file.separator");

      try {
         switch (FilePath.os) {
            case LINUX:
               gameArgs.add(Option.javaPath);
               if (Option.heapSize) {
                  gameArgs.add("-Xms1300m");
                  gameArgs.add("-Xmx1300m");
               }

               gameArgs.add("-Djava.library.path=." + fileSeperator + "native" + fileSeperator + "linux" + fileSeperator);
               gameArgs.add("-Dsun.java2d.d3d=false");
               gameArgs.add("-cp");
               gameArgs.add("." + fileSeperator + "farsky.jar");
               gameArgs.add("-classpath");
               gameArgs.add("." + fileSeperator + "lib" + fileSeperator + "*:." + fileSeperator + "farsky.jar");
               break;
            case MAC:
               String launcherJar = new File(System.getProperty("java.class.path")).getAbsolutePath();
               String launcherJarDir = launcherJar.substring(0, launcherJar.lastIndexOf(File.separator));
               String gameRootDir = launcherJarDir + fileSeperator + ".." + fileSeperator + ".." + fileSeperator + "..";
               gameArgs.add(Option.javaPath);
               if (Option.heapSize) {
                  gameArgs.add("-Xms1300m");
                  gameArgs.add("-Xmx1300m");
               }

               gameArgs.add("-Djava.library.path=" + gameRootDir + fileSeperator + "native" + fileSeperator + "macosx" + fileSeperator);
               gameArgs.add("-Dsun.java2d.d3d=false");
               gameArgs.add("-cp");
               gameArgs.add("" + gameRootDir + fileSeperator + "farsky.jar");
               gameArgs.add("-classpath");
               gameArgs.add("" + gameRootDir + fileSeperator + "lib" + fileSeperator + "*:" + gameRootDir + fileSeperator + "farsky.jar");
               break;
            case WINDOWS:
               gameArgs.add(Option.javaPath);
               if (Option.heapSize) {
                  gameArgs.add("-Xms1300m");
                  gameArgs.add("-Xmx1300m");
               }

               gameArgs.add("-Djava.library.path=." + fileSeperator + "native" + fileSeperator + "windows" + fileSeperator);
               gameArgs.add("-Dsun.java2d.d3d=false");
               gameArgs.add("-cp");
               gameArgs.add("." + fileSeperator + "farsky.jar");
               gameArgs.add("-classpath");
               gameArgs.add("." + fileSeperator + "lib" + fileSeperator + "*;." + fileSeperator + "farsky.jar");
         }

         if (Option.enableModLoader) {
            gameArgs.add("org.farskymodding.fsml.Loader");
         } else {
            gameArgs.add("game.Main");
         }

         gameArgs.add("-param");
         gameArgs.addAll(Option.args);
         gameArgs.add("-path:" + FilePath.gameDirectory);
         gameArgs.add("-logPath:" + FilePath.logDirectory);

         System.out.println("*************** PLAY COMMAND ****************");

         for (int i = 0; i < gameArgs.size(); i++) {
            System.out.println(gameArgs.get(i));
         }

         System.out.println("*********************************************");
         Runtime.getRuntime().exec(arrayListToArray(gameArgs));
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

      System.exit(0);
   }

   private static String[] arrayListToArray(ArrayList<String> arrayList) {
      String[] array = new String[arrayList.size()];

      for (int i = 0; i < arrayList.size(); i++) {
         array[i] = arrayList.get(i);
      }

      return array;
   }
}
