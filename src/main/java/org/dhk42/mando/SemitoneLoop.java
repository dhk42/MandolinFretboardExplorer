/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author davidklatte
 */
public class SemitoneLoop {

    public static final Map<String, String[]> ROOTS;
    public static final String[] A = {"A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#"};
    public static final String[] Bb = {"Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A"};
    public static final String[] B = {"B", "C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb"};
    public static final String[] C = {"C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B"};
    public static final String[] Cs = {"C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B", "C"};
    public static final String[] D = {"D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B", "C", "C#"};
    public static final String[] Eb = {"Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B", "C", "C#", "D"};
    public static final String[] E = {"E", "F", "F#", "G", "G#", "A", "Bb", "B", "C", "C#", "D", "Eb"};
    public static final String[] F = {"F", "F#", "G", "G#", "A", "Bb", "B", "C", "C#", "D", "Eb", "E"};
    public static final String[] Fs = {"F#", "G", "G#", "A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F"};
    public static final String[] G = {"G", "G#", "A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#"};
    public static final String[] Gs = {"G#", "A", "Bb", "B", "C", "C#", "D", "Eb", "E", "F", "F#", "G"};
    public static final String[][] ROOTS_ARRAY = {A, Bb, B, C, Cs, D, Eb, E, F, Fs, G, Gs};

    static {
        HashMap<String, String[]> roots = new HashMap<>();
        for (int x=0; x<=A.length-1; x++) {
            roots.put(A[x], ROOTS_ARRAY[x]);
        }
        ROOTS = Collections.unmodifiableMap(roots);
    }
    
    public static Set<FretPosition> allFretPositions(String root, Set<Integer> semitones) {
        HashSet<FretPosition> out = new HashSet<>();
        String[] semitoneScale = ROOTS.get(root);
        for (int x : semitones) {
            out.addAll(FretboardConstants.NAME_MAP.get(semitoneScale[x-1]));
        }
        return out;
    }

}
