package org.farskymodding.fsmllauncher.util;

import org.farskymodding.fsmllauncher.Info;

public class InfoSystem {
   public static void print() {
      System.out.println("******** HARDWARE INFO ************");
      System.out.println("OS name: " + System.getProperty("os.name") + " version " + System.getProperty("os.version"));
      System.out.println("Java version: " + System.getProperty("java.version"));
      System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors() + " cores");
      System.out.println("OS Architecture: " + System.getProperty("os.arch"));
      System.out.println("Launcher Version: " + Info.VERSION);
      System.out.println("***********************************");
   }
}
