/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author davidklatte
 */
public class ChordPageFrame extends javax.swing.JFrame {

    private ChordPage page;
    private boolean exitOnClose = true;
    private String title = null;

    /**
     * Creates new form ChordPageFrame
     */
    public ChordPageFrame(String title) {
        if (title != null) {
            setTitle(title);
        }
        this.title = title;
        initComponents();
        initFurther();
    }

    private void initFurther() {
        if (title == null) {
            page = new ChordPage();
        } else {
            page = new ChordPage(title);
        }
        scrollPane.setViewportView(page);
    }

    public void SetChords(String chords) throws PageFullException {
        String[] lines = chords.trim().split("\\\n");
        page.setMaxRows(lines.length);
        for (String s : lines) {
            s = s.trim();
            if (s.startsWith("chord")) {
                ArrayList<FretPosition> frets = new ArrayList<>();
                String[] elements = s.split("\\|");
                String label = elements[1];
                for (int x = 2; x <= elements.length - 1; x++) {
                    int str = -1;
                    String string = elements[x].substring(0, 1);
                    if (string.equals("G")) {
                        str = FretPosition.G_STRING;
                    }
                    if (string.equals("D")) {
                        str = FretPosition.D_STRING;
                    }
                    if (string.equals("A")) {
                        str = FretPosition.A_STRING;
                    }
                    if (string.equals("E")) {
                        str = FretPosition.E_STRING;
                    }
                    int fret = Integer.parseInt(elements[x].substring(1).trim());
                    FretPosition fp = new FretPosition(str, fret);
                    System.out.println("Adding chord finger for " + label + ", " + fp.toString());
                    frets.add(fp);
                }
                page.addFretboardRow(frets, label);
            }

        }
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        if (operation == JFrame.EXIT_ON_CLOSE) {
            this.exitOnClose = true;
        }
        if (operation == JFrame.DISPOSE_ON_CLOSE) {
            this.exitOnClose = false;
        }
        super.setDefaultCloseOperation(operation);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openItem = new javax.swing.JMenuItem();
        svgSaveItem = new javax.swing.JMenuItem();
        exitItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        svgCopyItem = new javax.swing.JMenuItem();
        svtTextCopy = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        browserItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setText("File");

        openItem.setText("Open");
        openItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openItemActionPerformed(evt);
            }
        });
        fileMenu.add(openItem);

        svgSaveItem.setText("Save as SVG");
        svgSaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                svgSaveItemActionPerformed(evt);
            }
        });
        fileMenu.add(svgSaveItem);

        exitItem.setText("Quit");
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitItem);

        jMenuBar1.add(fileMenu);

        jMenu2.setText("Edit");

        svgCopyItem.setText("Copy As SVG");
        jMenu2.add(svgCopyItem);

        svtTextCopy.setText("Copy SVG As Text");
        jMenu2.add(svtTextCopy);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("View");

        browserItem.setText("Open Image In Application");
        browserItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browserItemActionPerformed(evt);
            }
        });
        jMenu1.add(browserItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void svgSaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_svgSaveItemActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.showSaveDialog(this);
        File f = jfc.getSelectedFile();
        System.out.println("Saving to file " + f.getAbsolutePath());
        String svg = page.renderAsSVG();
        try {
            try (FileWriter fw = new FileWriter(f)) {
                fw.write(svg);
                fw.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_svgSaveItemActionPerformed

    private void openItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openItemActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(this);
        File f = jfc.getSelectedFile();
        try {
            try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                SetChords(sb.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (PageFullException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_openItemActionPerformed

    private void browserItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browserItemActionPerformed
        try {
            File f = File.createTempFile("MandolinChords", "svg");
            System.out.println("Saving to file " + f.getAbsolutePath());
            String svg = page.renderAsSVG();
            try (FileWriter fw = new FileWriter(f)) {
                fw.write(svg);
                fw.flush();
            }
            java.awt.Desktop.getDesktop().browse(f.toURI());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_browserItemActionPerformed

    private void exitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitItemActionPerformed
        this.setVisible(false);
        if (this.exitOnClose)
            System.exit(0);
        else
            this.dispose();
    }//GEN-LAST:event_exitItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChordPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChordPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChordPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChordPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChordPageFrame(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem browserItem;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem openItem;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JMenuItem svgCopyItem;
    private javax.swing.JMenuItem svgSaveItem;
    private javax.swing.JMenuItem svtTextCopy;
    // End of variables declaration//GEN-END:variables
}
