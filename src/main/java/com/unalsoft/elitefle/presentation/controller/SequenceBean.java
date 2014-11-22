/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.entity.Level;
import com.unalsoft.elitefle.entity.Notion;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author juanmanuelmartinezromero
 */
@ManagedBean(name = "sequenceBean")
@ViewScoped
public class SequenceBean {

    private boolean supports;
    private String name;
    //@TODO: Change if necessary for objects
    private Level level;
    private String spottingText;
    private String spottingActivity;
    private String systematisationText;
    private String systematisationActivity;
    private String knowledgeApp;
    private Notion notion;
    private Notion.SubNotion subNotion;
    private List<SelectItem> levels;
    private List<SelectItem> notions;
    private List<SelectItem> subNotions;
    //@TODO change for the activities obtained from the db;
    private TreeSet<String> activities;
    private TreeSet<String> texts;

    @PostConstruct
    public void init() {
        setNotion(Notion.textualStructuring);
        setNotions(new ArrayList<SelectItem>());
        setLevels(new ArrayList<SelectItem>());

        for (Notion n : Notion.values()) {
            getNotions().add(new SelectItem(n, n.getDescription()));
        }
        setSubNotions();

        for (Level l : Level.values()) {
            getLevels().add(new SelectItem(l,l.getLevel()));
        }

        //@TODO get a list of activities;
        //@TODO extract the selected activity from the treeset;
    }

    /**
     * Change the Sub-Notions list according to the selected Notion
     *
     * @param e
     */
    public void changeSubNotions(ValueChangeEvent e) {
        setNotion((Notion) e.getNewValue());
        setSubNotions();
    }

    /**
     * Refills the Sub-Notions Array List according to the selected Notion
     */
    private void setSubNotions() {
        setSubNotions(new ArrayList<SelectItem>());
        for (Notion.SubNotion subNotion : getNotion().getSubNotions()) {
            getSubNotions().add(new SelectItem(
                    subNotion, subNotion.getDescription()));
        }
    }

    /**
     * @return the supports
     */
    public boolean isSupports() {
        return supports;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return the spottingText
     */
    public String getSpottingText() {
        return spottingText;
    }

    /**
     * @return the spottingActivity
     */
    public String getSpottingActivity() {
        return spottingActivity;
    }

    /**
     * @return the systematisationText
     */
    public String getSystematisationText() {
        return systematisationText;
    }

    /**
     * @return the systematisationActivity
     */
    public String getSystematisationActivity() {
        return systematisationActivity;
    }

    /**
     * @return the knowledgeApp
     */
    public String getKnowledgeApp() {
        return knowledgeApp;
    }

    /**
     * @return the notion
     */
    public Notion getNotion() {
        return notion;
    }

    /**
     * @return the subNotion
     */
    public Notion.SubNotion getSubNotion() {
        return subNotion;
    }

    /**
     * @return the levels
     */
    public List<SelectItem> getLevels() {
        return levels;
    }

    /**
     * @return the notions
     */
    public List<SelectItem> getNotions() {
        return notions;
    }

    /**
     * @return the subNotions
     */
    public List<SelectItem> getSubNotions() {
        return subNotions;
    }

    /**
     * @return the activities
     */
    public TreeSet<String> getActivities() {
        return activities;
    }

    /**
     * @return the texts
     */
    public TreeSet<String> getTexts() {
        return texts;
    }

    /**
     * @param supports the supports to set
     */
    public void setSupports(boolean supports) {
        this.supports = supports;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @param spottingText the spottingText to set
     */
    public void setSpottingText(String spottingText) {
        this.spottingText = spottingText;
    }

    /**
     * @param spottingActivity the spottingActivity to set
     */
    public void setSpottingActivity(String spottingActivity) {
        this.spottingActivity = spottingActivity;
    }

    /**
     * @param systematisationText the systematisationText to set
     */
    public void setSystematisationText(String systematisationText) {
        this.systematisationText = systematisationText;
    }

    /**
     * @param systematisationActivity the systematisationActivity to set
     */
    public void setSystematisationActivity(String systematisationActivity) {
        this.systematisationActivity = systematisationActivity;
    }

    /**
     * @param knowledgeApp the knowledgeApp to set
     */
    public void setKnowledgeApp(String knowledgeApp) {
        this.knowledgeApp = knowledgeApp;
    }

    /**
     * @param notion the notion to set
     */
    public void setNotion(Notion notion) {
        this.notion = notion;
    }

    /**
     * @param subNotion the subNotion to set
     */
    public void setSubNotion(Notion.SubNotion subNotion) {
        this.subNotion = subNotion;
    }

    /**
     * @param levels the levels to set
     */
    public void setLevels(List<SelectItem> levels) {
        this.levels = levels;
    }

    /**
     * @param notions the notions to set
     */
    public void setNotions(List<SelectItem> notions) {
        this.notions = notions;
    }

    /**
     * @param subNotions the subNotions to set
     */
    public void setSubNotions(List<SelectItem> subNotions) {
        this.subNotions = subNotions;
    }

    /**
     * @param activities the activities to set
     */
    public void setActivities(TreeSet<String> activities) {
        this.activities = activities;
    }

    /**
     * @param texts the texts to set
     */
    public void setTexts(TreeSet<String> texts) {
        this.texts = texts;
    }

}
