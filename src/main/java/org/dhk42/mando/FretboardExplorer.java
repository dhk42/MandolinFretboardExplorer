/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author davidklatte
 */
public class FretboardExplorer extends javax.swing.JFrame {

    public static final String EXAMPLE_CHORDS = "#Chords for Receita de Samba\n"
            + "chord|C|G5|D2|A3\n"
            + "chord|Bbdim|G3|D2|A4\n"
            + "chord|G7|G4|D3|A5\n"
            + "chord|F7|G2|D1|A3\n"
            + "chord|A7|G2|D2|A4|E3\n"
            + "chord|D7|G5|D4|A5\n"
            + "chord|G|G0|D0|A2|E3\n"
            + "chord|G6|G5|D3|A6\n"
            + "chord|Cm6|G2|D1|A3\n"
            + "chord|D7#5|G5|D4|A5|E6\n"
            + "chord|E7|G1|D0|A2\n"
            + "chord|F#7|G2|D1|A3\n"
            + "chord|Am|G2|D2|A3\n"
            + "chord|B7|G4|D4|A6|E5\n"
            + "chord|Em|G4|D2|A2";

    MandolinFretboard fretboard = new MandolinFretboard();

    /**
     * Creates new form FretboardExplorer
     */
    public FretboardExplorer() {
        initComponents();
        componentPanel.setLayout(new BorderLayout());
        componentPanel.add(fretboard, BorderLayout.CENTER);
        fretboard.setNoteNames(namesCheckBox.isSelected());
    }

    public String[] chunk(String input, int maxLinesPerChunk) throws IOException {
        ArrayList<String> chunks = new ArrayList<>();
        int count = 0;
        StringReader sr = new StringReader(input);
        BufferedReader br = new BufferedReader(sr);
        String line = "";
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
            if (!line.startsWith("#")) {
                if (++count >= maxLinesPerChunk) {
                    chunks.add(sb.toString());
                    sb = new StringBuilder();
                    count = 0;
                }
            }
        }
        if (count != 0) {
            chunks.add(sb.toString());
        }
        String[] out = chunks.toArray(new String[chunks.size()]);
        return out;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        componentPanel = new javax.swing.JPanel();
        noteCombo = new javax.swing.JComboBox<>();
        clearButton = new javax.swing.JButton();
        namesCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        chordTextPane = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        chordPageButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout componentPanelLayout = new javax.swing.GroupLayout(componentPanel);
        componentPanel.setLayout(componentPanelLayout);
        componentPanelLayout.setHorizontalGroup(
            componentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        componentPanelLayout.setVerticalGroup(
            componentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );

        noteCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add Notes", "A", "Bb/A#", "B", "C", "C#", "D", "D#/Eb", "E", "F", "F#", "G", "G#" }));
        noteCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noteComboActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        namesCheckBox.setSelected(true);
        namesCheckBox.setText("Add Note Names");
        namesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namesCheckBoxActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(chordTextPane);

        jLabel1.setText("Paste Chord Data Here:");

        chordPageButton.setText("Generate Chord Diagrams");
        chordPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chordPageButtonActionPerformed(evt);
            }
        });

        jButton1.setText("(Example)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(componentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(chordPageButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(noteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(namesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(clearButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(componentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton)
                    .addComponent(namesCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chordPageButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        fretboard.clearFrets();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void noteComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noteComboActionPerformed
        String s = noteCombo.getSelectedItem().toString();
        if (!s.startsWith("Add")) {
            if (s.contains("/")) {
                s = s.substring(0, s.indexOf("/"));
            }
            System.out.println("Adding positions for " + s);
            List<FretPosition> list = FretboardConstants.NAME_MAP.get(s);
            for (FretPosition fp : list) {
                fretboard.fingerDown(fp);
            }
        }
    }//GEN-LAST:event_noteComboActionPerformed

    private void namesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namesCheckBoxActionPerformed
        fretboard.setNoteNames(namesCheckBox.isSelected());
    }//GEN-LAST:event_namesCheckBoxActionPerformed

    private void chordPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chordPageButtonActionPerformed
        try {
            String chordString = this.chordTextPane.getText();
            String title = null;
            if (chordString.startsWith("#")) {
                title = chordString.substring(1, chordString.indexOf("\n")).trim();
            }
            String[] chordChunks = chunk(chordString, 10);
            for (String chordChunk : chordChunks) {
                ChordPageFrame cpf = new ChordPageFrame(title);
                try {
                    cpf.SetChords(chordChunk);
                    cpf.pack();
                    cpf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    cpf.setLocationRelativeTo(this);
                    cpf.setVisible(true);
                } catch (PageFullException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_chordPageButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        chordTextPane.setText(EXAMPLE_CHORDS);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FretboardExplorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FretboardExplorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FretboardExplorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FretboardExplorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FretboardExplorer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chordPageButton;
    private javax.swing.JTextPane chordTextPane;
    private javax.swing.JButton clearButton;
    private javax.swing.JPanel componentPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox namesCheckBox;
    private javax.swing.JComboBox<String> noteCombo;
    // End of variables declaration//GEN-END:variables
}