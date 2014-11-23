/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.entity;

/**
 *
 * @author juanmanuelmartinezromero
 */
public enum Text {

    text1("Texte 1", "Url/Text/1"),
    text2("Texte 2", "Url/Text/2"),
    text3("Texte 3", "Url/Text/3");

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
