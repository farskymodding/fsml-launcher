package org.farskymodding.fsmllauncher;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.farskymodding.fsmllauncher.util.FilePath;
import org.farskymodding.fsmllauncher.util.InfoSystem;

public class Main {

    

    public static void main(String[] var0) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable var2) {
            var2.printStackTrace();
        }

        FilePath.init();
        InfoSystem.print();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Launcher launcher = new Launcher();
                    launcher.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
