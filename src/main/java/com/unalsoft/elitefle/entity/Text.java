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

    text1("Text 1"),
    text2("Text 2"),
    text3("Text 3");

    private final String text;

    /**
     * Text constructor
     *
     * @param text
     */
    private Text(String text) {
        this.text = text;
    }

    /**
     * get the text's name
     *
     * @return
     */
    public String getText() {
        return text;
    }
}
