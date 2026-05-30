package org.farskymodding.fsmllauncher;

import org.farskymodding.fsmllauncher.util.FilePath;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
   private JCheckBox chckbxForceHeapSize;

   public static String javaPath = "java";
   public static ArrayList<String> args = new ArrayList<>();
   public static boolean heapSize = false;

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
      this.setBackground(SystemColor.menu);
      this.getContentPane().setBackground(SystemColor.menu);
      this.setUndecorated(true);
      this.setTitle("Options");
      this.setBounds(100, 100, 250, 250);
      this.getContentPane().setLayout(new BorderLayout());
      this.contentPanel.setBackground(SystemColor.menu);
      this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.getContentPane().add(this.contentPanel, "Center");
      this.contentPanel.setLayout(null);
      this.setLocationRelativeTo(null);
      this.windowModeCheckBox = new JCheckBox("Play in window mode");
      this.windowModeCheckBox.setBounds(10, 59, 190, 23);
      this.contentPanel.add(this.windowModeCheckBox);

      JTextPane javaPathLabel = new JTextPane();
      javaPathLabel.setBackground(SystemColor.menu);
      javaPathLabel.setText("Change Java Path:");
      javaPathLabel.setEditable(false);
      javaPathLabel.setBounds(10, 115, 190, 20);
      this.contentPanel.add(javaPathLabel);

      this.javaPathValue = new JTextField();
      this.javaPathValue.setBounds(10, 135, 190, 20);
      this.contentPanel.add(this.javaPathValue);
      this.javaPathValue.setColumns(10);

      JTextPane optionsLabel = new JTextPane();
      optionsLabel.setFont(new Font("Tahoma", 0, 14));
      optionsLabel.setText("Options");
      optionsLabel.setEditable(false);
      optionsLabel.setBackground(SystemColor.menu);
      optionsLabel.setBounds(96, 11, 53, 20);
      this.contentPanel.add(optionsLabel);

      JButton logFolderButton = new JButton("Open log folder");
      logFolderButton.setBounds(10, 166, 139, 23);
      logFolderButton.addActionListener((action) -> {
         try {
            Desktop.getDesktop().open(new File(FilePath.logDirectory));
         } catch (IOException var3x) {
            var3x.printStackTrace();
         }
      });
      this.contentPanel.add(logFolderButton);

      this.chckbxForceHeapSize = new JCheckBox("Force heap size");
      this.chckbxForceHeapSize.setBounds(10, 85, 190, 23);
      this.contentPanel.add(this.chckbxForceHeapSize);



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

      heapSize = this.chckbxForceHeapSize.isSelected();
   }
}
