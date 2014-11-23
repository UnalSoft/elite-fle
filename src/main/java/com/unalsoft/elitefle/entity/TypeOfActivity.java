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
public enum TypeOfActivity {

    activity1("Activité 1"),
    activity2("Activité 2"),
    activity3("Activité 3"),
    activity4("Activité 4");

    private final String activity;

    /**
     * Type of activity constructor
     *
     * @param title
     */
    private TypeOfActivity(String title) {
        this.activity = title;
    }

    /**
     * get the activity's name
     *
     * @return
     */
    public String getActivityName() {
        return activity;
    }
}
