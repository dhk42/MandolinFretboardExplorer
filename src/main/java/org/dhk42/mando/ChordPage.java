/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jfree.graphics2d.svg.SVGGraphics2D;

/**
 *
 * @author davidklatte
 */
public class ChordPage extends JPanel {

    private MandolinFretboard singleTester = new MandolinFretboard();

    public static final int DEFAULT_MAX_ROWS = 10;
    int maxRows = DEFAULT_MAX_ROWS;
    private int rows = 0;
    private List<JPanel> panels = new ArrayList<>();
    private String title = null;

    public ChordPage() {
        init();
    }
    
    public ChordPage(String title) {
        this.title = title;
        init();
    }

    private void init() {
        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(box);
        setBackground(Color.WHITE);
        if (title != null) {
            JLabel label = new JLabel(title);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension single = singleTester.getPreferredSize();
        int maxWidth = 0;
        for (JPanel p : panels) {
            int width = p.getPreferredSize().width;
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return new Dimension(maxWidth, single.height * panels.size());
    }

    public void setMaxRows(int value) {
        maxRows = value;
    }

    public void addFretboardRow(Collection<FretPosition> fingering, String text) throws PageFullException {
        if (rows >= maxRows) {
            throw new PageFullException("Page is full. Max rows is " + maxRows);
        }
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        MandolinFretboard fretboard = new MandolinFretboard();
        for (FretPosition f : fingering) {
            fretboard.fingerDown(f);
        }
        fretboard.setNoteNames(true);
        JLabel label = new JLabel(text);
        label.setBackground(Color.WHITE);
        panel.add(label);
        panel.add(fretboard);
        this.add(panel);
        this.panels.add(panel);
    }

    public String renderAsSVG() {
        SVGGraphics2D sg = new SVGGraphics2D(getPreferredSize().width, getPreferredSize().height);
//        this.paintAll(sg);
        this.paintChildren(sg);
        String svgString = sg.getSVGDocument();
        return svgString;
    }

    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ChordPage chordPage = new ChordPage();
                frame.getContentPane().add(chordPage);
                HashSet<FretPosition> set = new HashSet<>();
                try {
                    set.add(new FretPosition(FretPosition.G_STRING, 5));
                    set.add(new FretPosition(FretPosition.D_STRING, 3));
                    set.add(new FretPosition(FretPosition.A_STRING, 6));
                    chordPage.addFretboardRow(set, "G6");
                    set.clear();
                    set.add(new FretPosition(FretPosition.G_STRING, 2));
                    set.add(new FretPosition(FretPosition.D_STRING, 1));
                    set.add(new FretPosition(FretPosition.A_STRING, 3));
                    chordPage.addFretboardRow(set, "Cm6");
                } catch (PageFullException ex) {
                    ex.printStackTrace();
                    System.exit(-1);
                }
                frame.pack();
                frame.setVisible(true);
                System.out.println(chordPage.renderAsSVG());
            }
        });
    }
}
