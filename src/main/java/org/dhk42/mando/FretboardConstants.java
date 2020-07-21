/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dhk42.mando;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author davidklatte
 */
public class FretboardConstants {
    
    public static final List<FretPosition> A_LIST;
    public static final List<FretPosition> As_LIST;
    public static final List<FretPosition> Bb_LIST;
    public static final List<FretPosition> B_LIST;
    public static final List<FretPosition> C_LIST;
    public static final List<FretPosition> Cs_LIST;
    public static final List<FretPosition> D_LIST;
    public static final List<FretPosition> Ds_LIST;
    public static final List<FretPosition> Eb_LIST;
    public static final List<FretPosition> E_LIST;
    public static final List<FretPosition> F_LIST;
    public static final List<FretPosition> Fs_LIST;
    public static final List<FretPosition> G_LIST;
    public static final List<FretPosition> Gs_LIST;
    
    public static final Set<FretPosition> A_SET;
    public static final Set<FretPosition> As_SET;
    public static final Set<FretPosition> Bb_SET;
    public static final Set<FretPosition> B_SET;
    public static final Set<FretPosition> C_SET;
    public static final Set<FretPosition> Cs_SET;
    public static final Set<FretPosition> D_SET;
    public static final Set<FretPosition> Ds_SET;
    public static final Set<FretPosition> Eb_SET;
    public static final Set<FretPosition> E_SET;
    public static final Set<FretPosition> F_SET;
    public static final Set<FretPosition> Fs_SET;
    public static final Set<FretPosition> G_SET;
    public static final Set<FretPosition> Gs_SET;
    
    public static final Map<String, List<FretPosition>> NAME_MAP;
    public static final Map<FretPosition, String> PREFERRED_NAME_MAP;
    
    static {
        ArrayList<FretPosition> a = new ArrayList<>();
        ArrayList<FretPosition> bb = new ArrayList<>();
        ArrayList<FretPosition> b = new ArrayList<>();
        ArrayList<FretPosition> c = new ArrayList<>();
        ArrayList<FretPosition> cs = new ArrayList<>();
        ArrayList<FretPosition> d = new ArrayList<>();
        ArrayList<FretPosition> eb = new ArrayList<>();
        ArrayList<FretPosition> e = new ArrayList<>();
        ArrayList<FretPosition> f = new ArrayList<>();
        ArrayList<FretPosition> fs = new ArrayList<>();
        ArrayList<FretPosition> g = new ArrayList<>();
        ArrayList<FretPosition> gs = new ArrayList<>();
        
        g.add(new FretPosition(FretPosition.G_STRING, 0));
        gs.add(new FretPosition(FretPosition.G_STRING, 1));
        a.add(new FretPosition(FretPosition.G_STRING, 2));
        bb.add(new FretPosition(FretPosition.G_STRING, 3));
        b.add(new FretPosition(FretPosition.G_STRING, 4));
        c.add(new FretPosition(FretPosition.G_STRING, 5));
        cs.add(new FretPosition(FretPosition.G_STRING, 6));
        d.add(new FretPosition(FretPosition.G_STRING, 7));
        eb.add(new FretPosition(FretPosition.G_STRING, 8));
        e.add(new FretPosition(FretPosition.G_STRING, 9));
        f.add(new FretPosition(FretPosition.G_STRING, 10));
        fs.add(new FretPosition(FretPosition.G_STRING, 11));
        g.add(new FretPosition(FretPosition.G_STRING, 12));
        gs.add(new FretPosition(FretPosition.G_STRING, 13));
        a.add(new FretPosition(FretPosition.G_STRING, 14));
        bb.add(new FretPosition(FretPosition.G_STRING, 15));
        b.add(new FretPosition(FretPosition.G_STRING, 16));
        c.add(new FretPosition(FretPosition.G_STRING, 17));
        cs.add(new FretPosition(FretPosition.G_STRING, 18));
        d.add(new FretPosition(FretPosition.G_STRING, 19));
        eb.add(new FretPosition(FretPosition.G_STRING, 20));
        e.add(new FretPosition(FretPosition.G_STRING, 21));
        f.add(new FretPosition(FretPosition.G_STRING, 22));
        
        d.add(new FretPosition(FretPosition.D_STRING, 0));
        eb.add(new FretPosition(FretPosition.D_STRING, 1));
        e.add(new FretPosition(FretPosition.D_STRING, 2));
        f.add(new FretPosition(FretPosition.D_STRING, 3));
        fs.add(new FretPosition(FretPosition.D_STRING, 4));
        g.add(new FretPosition(FretPosition.D_STRING, 5));
        gs.add(new FretPosition(FretPosition.D_STRING, 6));
        a.add(new FretPosition(FretPosition.D_STRING, 7));
        bb.add(new FretPosition(FretPosition.D_STRING, 8));
        b.add(new FretPosition(FretPosition.D_STRING, 9));
        c.add(new FretPosition(FretPosition.D_STRING, 10));
        cs.add(new FretPosition(FretPosition.D_STRING, 11));
        d.add(new FretPosition(FretPosition.D_STRING, 12));
        eb.add(new FretPosition(FretPosition.D_STRING, 13));
        e.add(new FretPosition(FretPosition.D_STRING, 14));
        f.add(new FretPosition(FretPosition.D_STRING, 15));
        fs.add(new FretPosition(FretPosition.D_STRING, 16));
        g.add(new FretPosition(FretPosition.D_STRING, 17));
        gs.add(new FretPosition(FretPosition.D_STRING, 18));
        a.add(new FretPosition(FretPosition.D_STRING, 19));
        bb.add(new FretPosition(FretPosition.D_STRING, 20));
        b.add(new FretPosition(FretPosition.D_STRING, 21));
        c.add(new FretPosition(FretPosition.D_STRING, 22));
        
        a.add(new FretPosition(FretPosition.A_STRING, 0));
        bb.add(new FretPosition(FretPosition.A_STRING, 1));
        b.add(new FretPosition(FretPosition.A_STRING, 2));
        c.add(new FretPosition(FretPosition.A_STRING, 3));
        cs.add(new FretPosition(FretPosition.A_STRING, 4));
        d.add(new FretPosition(FretPosition.A_STRING, 5));
        eb.add(new FretPosition(FretPosition.A_STRING, 6));
        e.add(new FretPosition(FretPosition.A_STRING, 7));
        f.add(new FretPosition(FretPosition.A_STRING, 8));
        fs.add(new FretPosition(FretPosition.A_STRING, 9));
        g.add(new FretPosition(FretPosition.A_STRING, 10));
        gs.add(new FretPosition(FretPosition.A_STRING, 11));
        a.add(new FretPosition(FretPosition.A_STRING, 12));
        bb.add(new FretPosition(FretPosition.A_STRING, 13));
        b.add(new FretPosition(FretPosition.A_STRING, 14));
        c.add(new FretPosition(FretPosition.A_STRING, 15));
        cs.add(new FretPosition(FretPosition.A_STRING, 16));
        d.add(new FretPosition(FretPosition.A_STRING, 17));
        eb.add(new FretPosition(FretPosition.A_STRING, 18));
        e.add(new FretPosition(FretPosition.A_STRING, 19));
        f.add(new FretPosition(FretPosition.A_STRING, 20));
        fs.add(new FretPosition(FretPosition.A_STRING, 21));
        g.add(new FretPosition(FretPosition.A_STRING, 22));
        
        e.add(new FretPosition(FretPosition.E_STRING, 0));
        f.add(new FretPosition(FretPosition.E_STRING, 1));
        fs.add(new FretPosition(FretPosition.E_STRING, 2));
        g.add(new FretPosition(FretPosition.E_STRING, 3));
        gs.add(new FretPosition(FretPosition.E_STRING, 4));
        a.add(new FretPosition(FretPosition.E_STRING, 5));
        bb.add(new FretPosition(FretPosition.E_STRING, 6));
        b.add(new FretPosition(FretPosition.E_STRING, 7));
        c.add(new FretPosition(FretPosition.E_STRING, 8));
        cs.add(new FretPosition(FretPosition.E_STRING, 9));
        d.add(new FretPosition(FretPosition.E_STRING, 10));
        eb.add(new FretPosition(FretPosition.E_STRING, 11));
        e.add(new FretPosition(FretPosition.E_STRING, 12));
        f.add(new FretPosition(FretPosition.E_STRING, 13));
        fs.add(new FretPosition(FretPosition.E_STRING, 14));
        g.add(new FretPosition(FretPosition.E_STRING, 15));
        gs.add(new FretPosition(FretPosition.E_STRING, 16));
        a.add(new FretPosition(FretPosition.E_STRING, 17));
        bb.add(new FretPosition(FretPosition.E_STRING, 18));
        b.add(new FretPosition(FretPosition.E_STRING, 19));
        c.add(new FretPosition(FretPosition.E_STRING, 20));
        cs.add(new FretPosition(FretPosition.E_STRING, 21));
        d.add(new FretPosition(FretPosition.E_STRING, 22));
        
        A_LIST = Collections.unmodifiableList(a);
        As_LIST = Collections.unmodifiableList(bb);
        Bb_LIST = Collections.unmodifiableList(bb);
        B_LIST = Collections.unmodifiableList(b);
        C_LIST = Collections.unmodifiableList(c);
        Cs_LIST = Collections.unmodifiableList(cs);
        D_LIST = Collections.unmodifiableList(d);
        Ds_LIST = Collections.unmodifiableList(eb);
        Eb_LIST = Collections.unmodifiableList(eb);
        E_LIST = Collections.unmodifiableList(e);
        F_LIST = Collections.unmodifiableList(f);
        Fs_LIST = Collections.unmodifiableList(fs);
        G_LIST = Collections.unmodifiableList(g);
        Gs_LIST = Collections.unmodifiableList(gs);
        
        A_SET = Collections.unmodifiableSet(new HashSet(A_LIST));
        As_SET = Collections.unmodifiableSet(new HashSet(As_LIST));
        Bb_SET = Collections.unmodifiableSet(new HashSet(Bb_LIST));
        B_SET = Collections.unmodifiableSet(new HashSet(B_LIST));
        C_SET = Collections.unmodifiableSet(new HashSet(C_LIST));
        Cs_SET = Collections.unmodifiableSet(new HashSet(Cs_LIST));
        D_SET = Collections.unmodifiableSet(new HashSet(D_LIST));
        Ds_SET = Collections.unmodifiableSet(new HashSet(Ds_LIST));
        Eb_SET = Collections.unmodifiableSet(new HashSet(Eb_LIST));
        E_SET = Collections.unmodifiableSet(new HashSet(E_LIST));
        F_SET = Collections.unmodifiableSet(new HashSet(F_LIST));
        Fs_SET = Collections.unmodifiableSet(new HashSet(Fs_LIST));
        G_SET = Collections.unmodifiableSet(new HashSet(G_LIST));
        Gs_SET = Collections.unmodifiableSet(new HashSet(Gs_LIST));
        
        HashMap<String, List<FretPosition>> map = new HashMap<>();
        map.put("A", A_LIST);
        map.put("B", B_LIST);
        map.put("Bb", Bb_LIST);
        map.put("A#", As_LIST);
        map.put("C", C_LIST);
        map.put("C#", Cs_LIST);
        map.put("D", D_LIST);
        map.put("D#", Ds_LIST);
        map.put("Eb", Eb_LIST);
        map.put("E", E_LIST);
        map.put("F", F_LIST);
        map.put("F#", Fs_LIST);
        map.put("G", G_LIST);
        map.put("G#", Gs_LIST);
        NAME_MAP = Collections.unmodifiableMap(map);
        
        HashMap<FretPosition, String> map2 = new HashMap<>();
        for (FretPosition fp : A_SET) {
            map2.put(fp, "A");
        }
        for (FretPosition fp : As_SET) {
            map2.put(fp, "A#");
        }
        for (FretPosition fp : Bb_SET) {
            map2.put(fp, "Bb/A#");
        }
        for (FretPosition fp : B_SET) {
            map2.put(fp, "B");
        }
        for (FretPosition fp : C_SET) {
            map2.put(fp, "C");
        }
        for (FretPosition fp : Cs_SET) {
            map2.put(fp, "C#");
        }
        for (FretPosition fp : D_SET) {
            map2.put(fp, "D");
        }
        for (FretPosition fp : Ds_SET) {
            map2.put(fp, "Eb/D#");
        }
        for (FretPosition fp : Eb_SET) {
            map2.put(fp, "Eb/D#");
        }
        for (FretPosition fp : E_SET) {
            map2.put(fp, "E");
        }
        for (FretPosition fp : F_SET) {
            map2.put(fp, "F");
        }
        for (FretPosition fp : Fs_SET) {
            map2.put(fp, "F#");
        }
        for (FretPosition fp : G_SET) {
            map2.put(fp, "G");
        }
        for (FretPosition fp : Gs_SET) {
            map2.put(fp, "G#");
        }
        PREFERRED_NAME_MAP = Collections.unmodifiableMap(map2);
    }
    
}
