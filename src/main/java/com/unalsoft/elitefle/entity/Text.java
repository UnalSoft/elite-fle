/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.entity;

import java.io.File;

/**
 * @author juanmanuelmartinezromero
 */
public enum Text {

    //@TODO Get text location from DB
    //@TODO Change server location to ../applications/__internal/elite-fle-1.0-SNAPSHOT/
    text1("Aider les handicapés dans le monde du travail", new File("").getAbsolutePath() + File.separator + "texts" + File.separator + "texte_corefB101test.xml"),
    text2("Les Thibault, tome 1, le cahier gris (fragment)", new File("").getAbsolutePath() + File.separator + "texts" + File.separator + "texte_corefB105test.xml"),
    text3("Texte d'essai", "Url/Text/3");

    private final String text;
    private final String url;

    /**
     * Text constructor
     *
     * @param text
     */

    private Text(String text, String url) {
        this.text = text;
        this.url = url;
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
}
