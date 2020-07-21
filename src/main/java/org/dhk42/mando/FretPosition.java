/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

/**
 *
 * @author davidklatte
 */
public class FretPosition {

    /**
     * @return the string
     */
    public int getString() {
        return string;
    }

    /**
     * @param string the string to set
     */
    public void setString(int string) {
        this.string = string;
    }

    /**
     * @return the fret
     */
    public int getFret() {
        return fret;
    }

    /**
     * @param fret the fret to set
     */
    public void setFret(int fret) {
        this.fret = fret;
    }
    
    public static final int E_STRING = 1;
    public static final int A_STRING = 2;
    public static final int D_STRING = 3;
    public static final int G_STRING = 4;
    
    private int[] hashcodeMultipliers = {-1, 7, 23, 41, 79};
    private String[] stringNames = {"NoSuchString", "E", "A", "D", "G"};
    
    private int string;
    private int fret;
    
    public FretPosition(int string, int fret) {
        if (string < E_STRING || string > G_STRING) throw new IllegalArgumentException("The string value is invalid");
        if (fret < 0 || fret > 22) throw new IllegalArgumentException("The fret value is invalid - " + fret);
        this.string = string;
        this.fret = fret;
    }
    
    @Override
    public int hashCode() {
        return string*hashcodeMultipliers[string] + fret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FretPosition other = (FretPosition) obj;
        if (this.string != other.string) {
            return false;
        }
        if (this.fret != other.fret) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "{" + stringNames[string] + ", " + fret + "}";
    }
}
