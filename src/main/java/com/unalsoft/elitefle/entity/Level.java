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
public enum Level {

    delfB1("Delf B1"),
    delfB2("Delf B2");

    private final String level;

    /**
     * Level constructor
     *
     * @param level
     */
    private Level(String level) {
        this.level = level;
    }

    /**
     * get the level's name
     *
     * @return
     */
    public String getLevel() {
        return level;
    }
}
