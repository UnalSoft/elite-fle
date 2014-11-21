/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.entity;

/**
 * Class allowing to get a specific kind of linguistic notion
 *
 * @author juanmanuelmartinezromero
 */
public enum Notion {

    textualStructuring("Structuration textuelle."),
    textualCohesionAndCoherence("Cohésion et cohérence textuelles."),
    thematicProgression("Progression thématique.");

    private final String description;

    /**
     * Creates a notion
     *
     * @param notion
     */
    Notion(String description) {
        this.description = description;
    }

    /**
     * get the Notion's description
     * @return a String containing the notion's description.
     */
    public String getDescription() {
        return description;
    }

}
