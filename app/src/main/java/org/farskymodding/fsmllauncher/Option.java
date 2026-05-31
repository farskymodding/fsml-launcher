package org.farskymodding.fsmllauncher;

import org.farskymodding.fsmllauncher.util.FileManager;
import org.farskymodding.fsmllauncher.util.FilePath;
import org.farskymodding.fsmllauncher.util.ModLoaderManager;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Option extends JDialog {
   private final JPanel contentPanel = new JPanel();

   private JTextField javaPathValue;
   private JCheckBox windowModeCheckBox;
   private JCheckBox forceHeapSizeCheckBox;
   private JCheckBox enableModLoaderCheckBox;

   public static String javaPath = "java";
   public static ArrayList<String> args = new ArrayList<>();
   public static boolean heapSize = false;
   public static boolean enableModLoader = false;

   public static void main(String[] var0) {
      try {
         Option var1 = new Option();
         var1.setDefaultCloseOperation(2);
         var1.setVisible(true);
      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }

   public Option() {
      Properties options = new Properties();
      try (Reader reader = Files.newBufferedReader(Paths.get("launcher.properties"))) {
         options.load(reader);
      } catch (NoSuchFileException nsfe) {
      } catch (IOException e) {
         e.printStackTrace();
      }

      boolean optWindowMode = options.containsKey("WINDOW_MODE") ? options.get("WINDOW_MODE").equals("true") : false;
      boolean optForceHeap = options.containsKey("FORCE_HEAP_SIZE") ? options.get("FORCE_HEAP_SIZE").equals("true") : false;
      String optJavaPath = options.containsKey("JAVA_PATH") ? (String)options.get("JAVA_PATH") : "java";
      boolean optEnableFSML = options.containsKey("FSML_ENABLED") ? options.get("FSML_ENABLED").equals("true") : false;

      this.contentPanel.setBackground(SystemColor.menu);
      this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.contentPanel.setLayout(null);

      this.setBackground(SystemColor.menu);
      this.getContentPane().setBackground(SystemColor.menu);
      this.setUndecorated(true);
      this.setTitle("Options");
      this.setBounds(100, 100, 250, 300);
      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(this.contentPanel, "Center");
      this.setLocationRelativeTo(null);

      JTextPane optionsLabel = new JTextPane();
      optionsLabel.setFont(new Font("Tahoma", 0, 14));
      optionsLabel.setText("General");
      optionsLabel.setEditable(false);
      optionsLabel.setBackground(SystemColor.menu);
      optionsLabel.setBounds(96, 11, 53, 20);
      this.contentPanel.add(optionsLabel);

      this.windowModeCheckBox = new JCheckBox("Play in window mode");
      this.windowModeCheckBox.setBounds(10, 39, 190, 23);
      this.windowModeCheckBox.setSelected(optWindowMode);
      this.contentPanel.add(this.windowModeCheckBox);

      this.forceHeapSizeCheckBox = new JCheckBox("Force heap size");
      this.forceHeapSizeCheckBox.setBounds(10, 65, 190, 23);
      this.forceHeapSizeCheckBox.setSelected(optForceHeap);
      this.contentPanel.add(this.forceHeapSizeCheckBox);

      JTextPane javaPathLabel = new JTextPane();
      javaPathLabel.setBackground(SystemColor.menu);
      javaPathLabel.setText("Change Java Path:");
      javaPathLabel.setEditable(false);
      javaPathLabel.setBounds(10, 95, 190, 20);
      this.contentPanel.add(javaPathLabel);

      this.javaPathValue = new JTextField();
      this.javaPathValue.setBounds(10, 115, 190, 20);
      this.javaPathValue.setColumns(10);
      this.javaPathValue.setText(optJavaPath);
      this.contentPanel.add(this.javaPathValue);

      JButton logFolderButton = new JButton("Open log folder");
      logFolderButton.setBounds(10, 146, 139, 23);
      logFolderButton.addActionListener((action) -> {
         try {
            Desktop.getDesktop().open(new File(FilePath.logDirectory));
         } catch (IOException var3x) {
            var3x.printStackTrace();
         }
      });
      this.contentPanel.add(logFolderButton);

      JTextPane modLabel = new JTextPane();
      modLabel.setFont(new Font("Tahoma", 0, 14));
      modLabel.setText("Mods");
      modLabel.setEditable(false);
      modLabel.setBackground(SystemColor.menu);
      modLabel.setBounds(106, 178, 53, 20);
      this.contentPanel.add(modLabel);
      
      JPanel modloaderPanel = new JPanel();
      modloaderPanel.setLayout(new FlowLayout(2));
      modloaderPanel.setBounds(10, 202, 220, 30);

      boolean verifyModloader = ModLoaderManager.verifyLoaderFiles();

      JButton installLoaderButton = new JButton("Install FSML");
      installLoaderButton.setEnabled(!verifyModloader);
      modloaderPanel.add(installLoaderButton);

      JButton removeLoaderButton = new JButton("Remove FSML");
      removeLoaderButton.setEnabled(verifyModloader);
      modloaderPanel.add(removeLoaderButton);

      this.contentPanel.add(modloaderPanel);

      this.enableModLoaderCheckBox = new JCheckBox("Enable modloader");
      this.enableModLoaderCheckBox.setEnabled(verifyModloader);
      this.enableModLoaderCheckBox.setSelected(verifyModloader && optEnableFSML);
      this.enableModLoaderCheckBox.setBounds(10, 233, 190, 23);
      this.contentPanel.add(this.enableModLoaderCheckBox);

      removeLoaderButton.addActionListener((event) -> {
         ModLoaderManager.removeModLoader();
         boolean modloader = ModLoaderManager.verifyLoaderFiles();
         removeLoaderButton.setEnabled(modloader);
         installLoaderButton.setEnabled(!modloader);
         this.enableModLoaderCheckBox.setEnabled(modloader);
         this.enableModLoaderCheckBox.setSelected(modloader);
      });

      installLoaderButton.addActionListener((event) -> {
         boolean success = ModLoaderManager.installModLoader();
         boolean modloader = ModLoaderManager.verifyLoaderFiles();
         installLoaderButton.setEnabled(!success);
         removeLoaderButton.setEnabled(success);
         this.enableModLoaderCheckBox.setEnabled(success);
         this.enableModLoaderCheckBox.setSelected(modloader);
      });

      JPanel buttonsPanel = new JPanel();
      buttonsPanel.setLayout(new FlowLayout(2));
      this.getContentPane().add(buttonsPanel, "South");

      JButton okButton = new JButton("OK");
      okButton.setActionCommand("OK");
      this.getRootPane().setDefaultButton(okButton);
      okButton.addActionListener((event) -> {
         Option.this.OKpressed();
         Option.this.dispose();
      });
      buttonsPanel.add(okButton);

      JButton cancelButton = new JButton("Cancel");
      cancelButton.setActionCommand("Cancel");
      cancelButton.addActionListener((event) -> { Option.this.dispose(); });
      buttonsPanel.add(cancelButton);
   }

   private void OKpressed() {
      args.clear();
      String newJavaPath = this.javaPathValue.getText();
      if (newJavaPath != null && newJavaPath.length() > 0) {
         javaPath = newJavaPath;
      } else {
         javaPath = "java";
      }

      if (this.windowModeCheckBox.isSelected()) {
         args.add("-windowMode");
      }

      enableModLoader = this.enableModLoaderCheckBox.isSelected();
      heapSize = this.forceHeapSizeCheckBox.isSelected();

      StringBuilder sb = new StringBuilder();
      sb.append("WINDOW_MODE=" + (this.windowModeCheckBox.isSelected() ? "true" : "false")).append("\n");
      sb.append("FORCE_HEAP_SIZE=" + (heapSize ? "true" : "false")).append("\n");
      sb.append("JAVA_PATH=" + javaPath).append("\n");
      sb.append("FSML_ENABLED=" + (enableModLoader ? "true" : "false")).append("\n");

      FileManager.writeToFile(Paths.get("launcher.properties"), sb.toString());
   }
}
