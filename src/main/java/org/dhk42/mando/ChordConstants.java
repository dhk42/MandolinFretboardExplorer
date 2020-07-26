/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author davidklatte
 */
public class ChordConstants {
    
    public static final SortedSet<String> CHORDS;
    public static final Map<String, List<Integer>> CHORD_TYPES;
    
    static {
        TreeSet<String> chords = new TreeSet<>();
        chords.add("A");
        chords.add("A7");
        chords.add("Am");
        chords.add("Am7");
        chords.add("Bb");
        chords.add("Bb7");
        chords.add("Bbm");
        chords.add("Bbm7");
        chords.add("B");
        chords.add("B7");
        chords.add("Bm");
        chords.add("Bm7");
        chords.add("C");
        chords.add("C7");
        chords.add("Cm");
        chords.add("Cm7");
        chords.add("C#");
        chords.add("C#7");
        chords.add("C#m");
        chords.add("C#m7");
        chords.add("D");
        chords.add("D7");
        chords.add("Dm");
        chords.add("Dm7");
        chords.add("D#");
        chords.add("D#7");
        chords.add("D#m");
        chords.add("D#m7");
        chords.add("Eb");
        chords.add("Eb7");
        chords.add("Ebm");
        chords.add("Ebm7");
        chords.add("E");
        chords.add("E7");
        chords.add("Em");
        chords.add("Em7");
        chords.add("F");
        chords.add("F7");
        chords.add("Fm");
        chords.add("Fm7");
        chords.add("F#");
        chords.add("F#7");
        chords.add("F#m");
        chords.add("F#m7");
        chords.add("G");
        chords.add("G7");
        chords.add("Gm");
        chords.add("Gm7");
        chords.add("G#");
        chords.add("G#m");
        CHORDS = Collections.unmodifiableSortedSet(chords);
        
        HashMap<String, List<Integer>> chordTypes = new HashMap<>();
        chordTypes.put("major", Arrays.asList(new Integer[]{0, 3, 5}));
        chordTypes.put("minor", Arrays.asList(new Integer[]{0, 3, 5}));
        chordTypes.put("major7", Arrays.asList(new Integer[]{}));
        chordTypes.put("minor7", Arrays.asList(new Integer[]{}));
        CHORD_TYPES = Collections.unmodifiableMap(chordTypes);
    }
    
}
