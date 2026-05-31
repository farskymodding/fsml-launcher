package org.farskymodding.fsmllauncher;

import java.awt.Color;
//import java.awt.Dimension;
//import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextPane;
import javax.swing.Timer;

public class Launcher {
   public JFrame frame;
   public static JButton playButton;
   private static JButton quitButton;
   //private static JTextPane webNews;
   private static Thread updaterThread;
   private static JButton optionButton;
   private static Option optionsDialog;

   public Launcher() {
      launchUpdater();
      this.initialize();
   }

   public static void launchUpdater() {
      if (updaterThread != null) {
         try {
            updaterThread.join();
         } catch (InterruptedException var1) {
            var1.printStackTrace();
         }
      }
   }

   private void initialize() {
      this.frame = new JFrame();
      this.frame.getContentPane().setBackground(Color.BLACK);
      this.frame.setTitle("FarSky Launcher");
      ImageIcon icon = new ImageIcon(this.getClass().getResource("/res/icon.png"));
      this.frame.setIconImage(icon.getImage());
      this.frame.setResizable(false);
      this.frame.setBounds(100, 100, 700, 500);
      this.frame.setDefaultCloseOperation(3);
      this.frame.getContentPane().setLayout(null);
      this.frame.setLocationRelativeTo(null);
      this.frame.setUndecorated(true);

      // TODO: Recreate updates channel
      /* 
      webNews = new JTextPane();
      webNews.setForeground(Color.BLACK);
      webNews.setEditable(false);
      webNews.setContentType("text/html");
      webNews.setText("<span style='color:white;'>Loading news...</span>\r\n");
      webNews.setBackground(Color.BLACK);

      try {
         webNews.setPage("http://www.farskygame.com/release/lastNews.php");
      } catch (IOException var5) {
         webNews.setText(
            "<html><span style='color:yellow;'>Connection problem.<br/>Please make sure you are connected to the Internet.<br/>If you are using a proxy server, configure it within the Options menu or directly in the Java settings.</span></html>"
         );
      }

      JScrollPane webNewsPane = new JScrollPane(webNews);
      webNewsPane.setHorizontalScrollBarPolicy(31);
      webNewsPane.setViewportBorder(null);
      webNewsPane.setBounds(113, 464, 393, 23);
      webNewsPane.setToolTipText("");
      webNewsPane.setBorder(null);
      webNewsPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
      this.frame.getContentPane().add(webNewsPane);
      */

      playButton = new JButton(new ImageIcon(this.getClass().getResource("/res/playButton.png")));
      playButton.setRolloverIcon(new ImageIcon(this.getClass().getResource("/res/playButtonHover.png")));
      playButton.setPressedIcon(new ImageIcon(this.getClass().getResource("/res/playButtonClick.png")));
      playButton.setBackground(Color.DARK_GRAY);
      playButton.setBounds(281, 292, 135, 39);
      playButton.addActionListener((event) -> { Play.launchGame(); });
      this.frame.getContentPane().add(playButton);

      optionButton = new JButton(new ImageIcon(this.getClass().getResource("/res/optionsButton.png")));
      optionButton.setRolloverIcon(new ImageIcon(this.getClass().getResource("/res/optionsButtonHover.png")));
      optionButton.setPressedIcon(new ImageIcon(this.getClass().getResource("/res/optionsButtonClick.png")));
      optionButton.setForeground(Color.BLACK);
      optionButton.setEnabled(true);
      optionButton.setBounds(281, 235, 135, 39);
      optionButton.setBorder(BorderFactory.createEmptyBorder());
      optionButton.setContentAreaFilled(false);
      this.frame.getContentPane().add(optionButton);
      
      quitButton = new JButton(new ImageIcon(this.getClass().getResource("/res/quit.png")));
      quitButton.setBounds(669, 15, 16, 16);
      quitButton.addActionListener((event) -> { System.exit(0); });
      this.frame.getContentPane().add(quitButton);

      JLabel launcherBackground = new JLabel("New label");
      launcherBackground.setBounds(0, 0, 700, 500);
      launcherBackground.setIcon(new ImageIcon(Launcher.class.getResource("/res/launcher.png")));
      launcherBackground.setBackground(Color.YELLOW);
      this.frame.getContentPane().add(launcherBackground);

      optionsDialog = new Option();
      optionsDialog.setDefaultCloseOperation(2);
      optionsDialog.setUndecorated(true);
      optionButton.addActionListener((event) -> { Launcher.optionsDialog.setVisible(true); });

      Timer repaintWindowTimer = new Timer(100, (event) -> { Launcher.this.frame.repaint(); });
      repaintWindowTimer.start();
   }
}
