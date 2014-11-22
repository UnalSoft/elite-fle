/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class allowing to get a specific kind of linguistic notion
 *
 * @author juanmanuelmartinezromero
 */
public enum Notion {

    textualStructuring("Structuration textuelle"),
    textualCohesionAndCoherence("Cohésion et cohérence textuelles"),
    thematicProgression("Progression thématique");

    private final String description;
    private final List<SubNotion> subNotions;
    private static final int MAX_SUBNOTIONS = 3;

    Notion(String description) {
        this.description = description;
        this.subNotions = new ArrayList<SubNotion>(MAX_SUBNOTIONS);

        if (description.equals("Structuration textuelle")) {
            subNotions.add(SubNotion.keyPhrases);
            subNotions.add(SubNotion.logicalTextStructure);
            subNotions.add(SubNotion.textualTypeOrSequence);
        } else if (description.equals("Cohésion et cohérence textuelles")) {
            subNotions.add(SubNotion.textualCoreference);
            subNotions.add(
                    SubNotion.connectorsAndLogical_TimeAndDiscourseMarkers);
        } else if (description.equals("Progression thématique")) {
            subNotions.add(SubNotion.themeAndRheme);
        }
    }

    /**
     * get the Notion's description
     *
     * @return a String containing the notion's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * get the sub-Notions related to the created Notion
     *
     * @return a list with the related sub-Notions
     */
    public List<SubNotion> getSubNotions() {
        return subNotions;
    }

    public enum SubNotion {

        keyPhrases("Phrases principales"),
        logicalTextStructure("Structure logique du texte"),
        textualTypeOrSequence("Types ou séquences textuelles"),
        textualCoreference("Coréférence textuelle"),
        connectorsAndLogical_TimeAndDiscourseMarkers(
                "Connecteurs et marqueurs logico-temporels et discursifs"),
        themeAndRheme("Thème et rhème");

        private final String description;

        /**
         * Creates a subnotion
         *
         * @param description
         */
        SubNotion(String description) {
            this.description = description;
        }

        /**
         * Get the sub-notion's description
         *
         * @return
         */
        public String getDescription() {
            return description;
        }
    }
}
