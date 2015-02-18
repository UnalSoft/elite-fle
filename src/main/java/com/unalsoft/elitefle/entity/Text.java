/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author juanmanuelmartinezromero
 */
public enum Text {

    //@TODO Get text location from DB
    //@TODO Change server location to ../applications/__internal/elite-fle-1.0-SNAPSHOT/
    text1("Aider les handicapés dans le monde du travail", new File("").getAbsolutePath() + File.separator + "texts" + File.separator + "texte_corefB101test.xml", Level.delfB1),
    text2("Les Thibault, tome 1, le cahier gris (fragment)", new File("").getAbsolutePath() + File.separator + "texts" + File.separator + "texte_corefB105test.xml", Level.delfB1),
    text3("Texte d'essai", "Url/Text/3", Level.delfB2);

    private final String text;
    private final String url;
    private final Level level;

    /**
     * Text constructor
     *
     * @param text
     */

    private Text(String text, String url, Level level) {
        this.text = text;
        this.url = url;
        this.level = level;
    }

    /**
     * get the text's name
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * get the text's url
     *
     * @return
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * get the text's level
     *
     * @return
     */
    public Level getLevel() {
        return level;
    }
    
    public static List<Text> getByLevel(Level level) {
        List<Text> list = new ArrayList<Text>();
        for (Text t : Text.values()) {
            if (t.getLevel().equals(level)){
                list.add(t);
            }
        }        
        return list;
    }
}
