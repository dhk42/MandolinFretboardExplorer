/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando.scratch;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/**
 * Generate a list of UIManager color keys.
 */
public class ColorKeyPrinter extends JFrame {
    
    JPanel centerPanel;
    
    public ColorKeyPrinter() {
        init();
    }
    
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerPanel = new JPanel();
        BoxLayout box = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(box);
        List<String> colorKeys = new ArrayList<String>();
        Set<Entry<Object, Object>> entries = UIManager.getLookAndFeelDefaults().entrySet();
        for (Entry entry : entries) {
            if (entry.getValue() instanceof Color) {
                JPanel thisPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel keyLabel = new JLabel();
                thisPanel.add(keyLabel);
                keyLabel.setText((String)entry.getKey());
                Color c = (Color) entry.getValue();
                ColorSwatch swatch = new ColorSwatch(60, c, Color.BLACK);
                thisPanel.add(new JLabel(swatch));
                centerPanel.add(thisPanel);
            }
        }
        JScrollPane pane = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(pane);
    }

    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                ColorKeyPrinter p = new ColorKeyPrinter();
                p.pack();
                p.setVisible(true);
            }
        });
    }
}
