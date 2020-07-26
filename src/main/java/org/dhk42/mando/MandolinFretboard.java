/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.SystemFlavorMap;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import org.jfree.graphics2d.svg.SVGGraphics2D;

/**
 *
 * @author davidklatte
 */
public class MandolinFretboard extends JComponent implements MouseListener {

    int numberOfFrets = 12;
    int fretWidth = 35;
    int borderSize = 20;
    int fretboardLength = fretWidth * numberOfFrets;
    int fretboardWidth = 60;//42;
    Color fretboardForeground = Color.GRAY.brighter(); //UIManager.getLookAndFeelDefaults().getColor("TextPane.foreground");  //Color.BLACK;
    Color fretboardBackground = UIManager.getLookAndFeelDefaults().getColor("TextPane.background");  //Color.WHITE;
    Color inlay = UIManager.getLookAndFeelDefaults().getColor("TextField.shadow");  //Color.LIGHT_GRAY;
    Color finger = Color.RED;//UIManager.getLookAndFeelDefaults().getColor("TitledBorder.titleColor");  //Color.RED.darker();
    Color root = UIManager.getLookAndFeelDefaults().getColor("TextPane.caretForeground");  //Color.ORANGE;
    Color textColor = Color.BLACK;
    JPopupMenu menu;
    private Set<FretPosition> fingersDown = new HashSet<>();
    private Set<FretPosition> rootsDown = new HashSet<>();
    
    int yOfG, yOfD, yOfA, yOfE;
    ArrayList<Integer> fretXs = new ArrayList<>();
    ArrayList<FretLocation> fretLocations = new ArrayList<>();
    
    private static boolean printed = false;
    private boolean printNames = false;

    public MandolinFretboard() {
        init();
    }

    public MandolinFretboard(int numberOfFrets, int fretWidth, int borderSize, int fretboardWidth) {
        this(numberOfFrets, fretWidth, borderSize, fretboardWidth, null, null, null, null, null, null);
    }

    public MandolinFretboard(int numberOfFrets, int fretWidth, int borderSize,
            int fretboardWidth, Color foreground, Color background, Color inlay,
            Color finger, Color root, Color textColor) {
        this.numberOfFrets = numberOfFrets;
        this.fretWidth = fretWidth;
        this.borderSize = borderSize;
        this.fretboardWidth = fretboardWidth;
        if (foreground != null) {
            this.fretboardForeground = foreground;
        }
        if (background != null) {
            this.fretboardBackground = background;
        }
        if (inlay != null) {
            this.inlay = inlay;
        }
        if (finger != null) {
            this.finger = finger;
        }
        if (root != null) {
            this.root = root;
        }
        if (textColor != null) {
            this.textColor = textColor;
        }
        init();
    }

    private void init() {
        this.addMouseListener(this);
        menu = new JPopupMenu();
        JMenuItem copy = new JMenuItem("Copy Image");
        menu.add(copy);
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyToClipboard();
            }
        });
        JMenuItem svg = new JMenuItem("Copy SVG");
        menu.add(svg);
        svg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SVGGraphics2D sg = new SVGGraphics2D(getPreferredSize().width, getPreferredSize().height);
                paintComponent(sg);
                String svgString = sg.getSVGDocument();
                System.out.println("SVG is\n" + svgString);
                svgToClipboard(svgString);
            }
        });
        JMenuItem svgtxt = new JMenuItem("Copy SVG as text");
        menu.add(svgtxt);
        svgtxt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 SVGGraphics2D sg = new SVGGraphics2D(getPreferredSize().width, getPreferredSize().height);
                paintComponent(sg);
                String svgString = sg.getSVGDocument();
                svgTextToClipboard(svgString);
            }
        });
        JMenuItem clear = new JMenuItem("Clear Frets");
        menu.add(clear);
        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MandolinFretboard.this.clearFrets();
            }
        });
        if (fretboardBackground == null || fretboardBackground.toString().startsWith("Derived")) {
            fretboardBackground = Color.WHITE;
        }
        if (inlay == null || inlay.toString().startsWith("Derived")) {
            inlay = Color.LIGHT_GRAY;
        }
        initFretLocations();
    }
    
    private void initFretLocations() {
        yOfE = borderSize;
        yOfG = yOfE + fretboardWidth;
        yOfA = borderSize + fretboardWidth / 3;
        yOfD = borderSize + 2*fretboardWidth / 3;
        for (int i=0; i<=numberOfFrets; i++) {
            int fretX = borderSize + i * fretWidth;
            fretXs.add(fretX);
            FretLocation el = new FretLocation(fretX, yOfE, new FretPosition(FretPosition.E_STRING, i));
            FretLocation al = new FretLocation(fretX, yOfA, new FretPosition(FretPosition.A_STRING, i));
            FretLocation dl = new FretLocation(fretX, yOfD, new FretPosition(FretPosition.D_STRING, i));
            FretLocation gl = new FretLocation(fretX, yOfG, new FretPosition(FretPosition.G_STRING, i));
            fretLocations.add(el);
            fretLocations.add(al);
            fretLocations.add(dl);
            fretLocations.add(gl);
        }
    }
    
    private FretPosition getClosestFret(int x, int y) {
        double min = Double.MAX_VALUE;
        ArrayList<Double> distances = new ArrayList<>();
        for (int i=0; i<=fretLocations.size()-1; i++) {
            FretLocation f = fretLocations.get(i);
            double distance = distance(x, y, f.x, f.y);
            if (distance < min) min = distance;
            distances.add(distance);
        }
        System.out.println(distances);
        for (int i=0; i<=distances.size()-1; i++) {
            double d = distances.get(i);
            if (d == min) {
                return fretLocations.get(i).fret;
            }
        }
        return null;
    }
    
    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
    }

    public void setNoteNames(boolean value) {
        this.printNames = value;
        repaint();
    }

    public void copyToClipboard() {
        BufferedImage image = new BufferedImage(getPreferredSize().width, getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
        paintComponent(image.getGraphics());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        ImageTransferable selection = new ImageTransferable(image);
        clipboard.setContents(selection, null);
    }

    public void fingerDown(FretPosition fp) {
        fingersDown.add(fp);
        repaint();
    }
    
    public void toggleFret(FretPosition fp) {
        if (!fingersDown.contains(fp)) fingerDown(fp);
        else {
            fingersDown.remove(fp);
            repaint();
        }
    }

    public void rootDown(FretPosition fp) {
        rootsDown.add(fp);
        repaint();
    }

    public void clearFrets() {
        rootsDown.clear();
        fingersDown.clear();
        repaint();
    }

    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        if (!printed) {
            printed = true;
            System.out.println("Background " + fretboardBackground);
            System.out.println("Foreground " + fretboardForeground);
        }
        g.setColor(fretboardBackground);
        g.fillRect(0, 0, fretboardLength + borderSize * 2, fretboardWidth + borderSize * 2);
        g.setColor(fretboardForeground);
        g.drawRect(borderSize, borderSize, fretboardLength, fretboardWidth);
        paintStrings(g);
        paintFrets(g);
        paintInlays(g);
        paintFingersDown(g);
        paintRootsDown(g);
    }

    private void paintFrets(Graphics2D g) {
        g.setColor(fretboardForeground);
        for (int x = 1; x <= numberOfFrets; x++) {
            g.drawLine(borderSize + x * fretWidth, borderSize, borderSize + x * fretWidth, borderSize + fretboardWidth);
            
        }
    }

    private void paintStrings(Graphics2D g) {
        g.setColor(fretboardForeground);
        g.drawLine(borderSize, borderSize + fretboardWidth / 3, borderSize + fretboardLength, borderSize + fretboardWidth / 3);
        g.drawLine(borderSize, borderSize + 2 * fretboardWidth / 3, borderSize + fretboardLength, borderSize + 2 * fretboardWidth / 3);
    }

    private void paintInlays(Graphics2D g) {
        g.setColor(inlay);
        int diameter = (int) (fretWidth * 0.8);
        if (fretboardWidth / 3 < fretWidth) {
            diameter = (int) (fretboardWidth / 3 * 0.8);
        }
        int centerY = fretboardWidth / 2 + borderSize;
        int centerXatZero = borderSize;
        if (numberOfFrets >= 5) {
            int centerX5Marker = centerXatZero + (int) (fretWidth * 4.5);
            int x = centerX5Marker - diameter / 2;
            int y = centerY - diameter / 2;
            g.fillOval(x, y, diameter, diameter);
        }
        if (numberOfFrets >= 7) {
            int centerX7Marker = centerXatZero + (int) (fretWidth * 6.5);
            int x = centerX7Marker - diameter / 2;
            int y = centerY - diameter / 2;
            g.fillOval(x, y, diameter, diameter);
        }
        if (numberOfFrets >= 10) {
            int centerX10Marker = centerXatZero + (int) (fretWidth * 9.5);
            int x = centerX10Marker - diameter / 2;
            int y = centerY - diameter / 2;
            g.fillOval(x, y, diameter, diameter);
        }
        if (numberOfFrets >= 12) {
            int centerX12Marker = centerXatZero + (int) (fretWidth * 11.5);
            int x = centerX12Marker - diameter / 2;
            int y1 = borderSize + fretboardWidth / 6 - diameter / 2;
            int y2 = borderSize + fretboardWidth - fretboardWidth / 6 - diameter / 2;
            g.fillOval(x, y1, diameter, diameter);
            g.fillOval(x, y2, diameter, diameter);
        }
    }

    private void paintFingersDown(Graphics2D g) {
        g.setColor(finger);
        int diameter = (int) (fretWidth);// * 0.8);
        if (fretboardWidth / 3 < fretWidth) {
            diameter = (int) (fretboardWidth / 3);//3 * 0.8);
        }
        for (FretPosition fp : fingersDown) {
            int fret = fp.getFret();
            if (fret <= numberOfFrets) {
                int string = fp.getString();
                int centerX = borderSize + (int) (((float) fretWidth) * (((float) fret) - 0.5));
                if (fret == 0) {
                    centerX = borderSize;
                }
                int centerY = borderSize + (string - 1) * fretboardWidth / 3;
                g.fillOval(centerX - diameter / 2, centerY - diameter / 2, diameter, diameter);
                if (printNames) {
                    String name = FretboardConstants.PREFERRED_NAME_MAP.get(fp);
                    g.setColor(textColor);
                    drawCenteredString(g, name, centerX, centerY, null);/// 2, null);
//                    g.drawString(name, centerX - diameter / 2, centerY - diameter / 2);
                    g.setColor(finger);
                }
            }
        }
    }

    public void drawCenteredString(Graphics g, String text, int xToCenterOn, int yToCenterOn, Font font) {
        if (font == null) {
            font = g.getFont();
        }
        FontMetrics metrics = g.getFontMetrics(font);
        int x = xToCenterOn - metrics.stringWidth(text) / 2;
        
        //XXX TODO Why is this 3. Can we calculate it better?
        int y = yToCenterOn + (int)(metrics.getHeight()/3);
        
        if (font != null) {
            g.setFont(font);
        }
        g.drawString(text, x, y);
    }

    private void paintRootsDown(Graphics2D g) {
        g.setColor(root);
    }

    private void svgToClipboard(String svgString) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        SvgClip strSVG = new SvgClip(svgString);
        clip.setContents(strSVG, null);
    }

    private void svgTextToClipboard(String svgString) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        TextTransferable strSVG = new TextTransferable(svgString);
        clip.setContents(strSVG, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(fretboardLength + borderSize * 2, fretboardWidth + borderSize * 2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            menu.show(this, e.getX(), e.getY());
        } else if (e.getClickCount() == 2) {
            int x= e.getX();
            int y = e.getY();
            System.out.println("Double-click at " + x + ", " + y);
            FretPosition f = this.getClosestFret(x, y);
            this.toggleFret(f);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    class ImageTransferable implements Transferable {

        /**
         * Constructs the selection.
         *
         * @param image an image
         */
        public ImageTransferable(Image image) {
            theImage = image;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.imageFlavor};
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(DataFlavor.imageFlavor);
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (flavor.equals(DataFlavor.imageFlavor)) {
                return theImage;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }

        private Image theImage;

    }
    class TextTransferable implements Transferable {

        public TextTransferable(String text) {
            theText = text;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.stringFlavor};
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(DataFlavor.stringFlavor);
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (flavor.equals(DataFlavor.stringFlavor)) {
                return theText;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }

        private String theText;

    }

// blatant copy/adapation of https://stackoverflow.com/questions/33726321/how-to-transfer-svg-image-to-other-programs-with-dragndrop-in-java
    class SvgClip implements Transferable {

        String svgString;

        DataFlavor svgFlavor = new DataFlavor("image/svg+xml; class=java.io.InputStream", "Scalable Vector Graphic");

        DataFlavor[] supportedFlavors = {svgFlavor};

        SvgClip(String svgString) {
            this.svgString = svgString;

            SystemFlavorMap systemFlavorMap = (SystemFlavorMap) SystemFlavorMap.getDefaultFlavorMap();
            systemFlavorMap.addUnencodedNativeForFlavor(svgFlavor, "image/svg+xml");
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return this.supportedFlavors;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return true;
        }

        @Override
        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException, IOException {
            return new ByteArrayInputStream(svgString.getBytes(StandardCharsets.UTF_8));
        }

    }
    
    private class FretLocation {
        int x,y;
        FretPosition fret;
        public FretLocation(int x, int y, FretPosition fret) {
            this.x = x;
            this.y = y;
            this.fret = fret;
        }
    }
}
