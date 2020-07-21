/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author davidklatte
 */
public class TestFretboardFrame extends JFrame {
    
    private MandolinFretboard c;
    
    public TestFretboardFrame() {
         c = new MandolinFretboard();
        c.fingerDown(new FretPosition(FretPosition.A_STRING, 5));
        getContentPane().add(c);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                TestFretboardFrame m = new TestFretboardFrame();
                m.pack();
                m.setVisible(true);
                System.out.println(m.c.size());
            }
        });
    }
    
}
