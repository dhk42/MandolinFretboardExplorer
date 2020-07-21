/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

import java.awt.Color;

/**
 *
 * @author davidklatte
 */
public class ColorUtil {

    public static double luminance(Color color) {
        double a = color.getRed() / 255 <= 0.03928 ? color.getRed() / 12.92 : Math.pow((color.getRed() + 0.055) / 1.055, 2.4);
        double b = color.getGreen() / 255 <= 0.03928 ? color.getGreen() / 12.92 : Math.pow((color.getGreen() + 0.055) / 1.055, 2.4);
        double c = color.getBlue() / 255 <= 0.03928 ? color.getBlue() / 12.92 : Math.pow((color.getBlue() + 0.055) / 1.055, 2.4);
        return a * 0.2126 + b * 0.7152 + c * 0.0722;
    }
    
    public static double contrast(Color color1, Color color2) {
        double lum1 = luminance(color1);
        double lum2 = luminance(color2);
        double brightest = Math.max(lum1, lum2);
        double darkest = Math.min(lum1, lum2);
        return (brightest + 0.05)/(darkest + 0.05);
    }

}
