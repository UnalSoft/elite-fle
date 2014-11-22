/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.entity.Notion;
import java.util.ArrayList;
import java.util.List;
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

    private boolean seqAreSupports;
    private String seqName;
    //@TODO: Change if necessary for objects
    private String seqSpottingText;
    private String seqSpottingActivity;
    private String seqSystematisationText;
    private String seqSystematisationActivity;
    private String seqKnowledgeApp;
    private Notion seqNotion;
    private Notion.SubNotion seqSubNotion;
    private List<SelectItem> notions;
    private List<SelectItem> subNotions;

    @PostConstruct
    public void init() {
        setSeqNotion(Notion.textualStructuring);
        setNotions(new ArrayList<SelectItem>());

        for (Notion notion : Notion.values()) {
            getNotions().add(new SelectItem(notion, notion.getDescription()));
        }
        setSubNotions();
    }

    /**
     * Change the Sub-Notions list according to the selected Notion
     *
     * @param e
     */
    public void changeSubNotions(ValueChangeEvent e) {
        setSeqNotion((Notion) e.getNewValue());
        setSubNotions();
    }

    /**
     * Refills the Sub-Notions Array List according to the selected Notion
     */
    private void setSubNotions() {
        setSubNotions(new ArrayList<SelectItem>());
        for (Notion.SubNotion subNotion : getSeqNotion().getSubNotions()) {
            getSubNotions().add(new SelectItem(
                    subNotion, subNotion.getDescription()));
        }
    }

    /**
     * @return the seqAreSupports
     */
    public boolean isSeqAreSupports() {
        return seqAreSupports;
    }

    /**
     * @return the seqName
     */
    public String getSeqName() {
        return seqName;
    }

    /**
     * @return the seqSpottingText
     */
    public String getSeqSpottingText() {
        return seqSpottingText;
    }

    /**
     * @return the seqSpottingActivity
     */
    public String getSeqSpottingActivity() {
        return seqSpottingActivity;
    }

    /**
     * @return the seqSystematisationText
     */
    public String getSeqSystematisationText() {
        return seqSystematisationText;
    }

    /**
     * @return the seqSystematisationActivity
     */
    public String getSeqSystematisationActivity() {
        return seqSystematisationActivity;
    }

    /**
     * @return the seqKnowledgeApp
     */
    public String getSeqKnowledgeApp() {
        return seqKnowledgeApp;
    }

    /**
     * @return the seqNotion
     */
    public Notion getSeqNotion() {
        return seqNotion;
    }

    /**
     * @return the seqSubNotion
     */
    public Notion.SubNotion getSeqSubNotion() {
        return seqSubNotion;
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
     * @param seqAreSupports the seqAreSupports to set
     */
    public void setSeqAreSupports(boolean seqAreSupports) {
        this.seqAreSupports = seqAreSupports;
    }

    /**
     * @param seqName the seqName to set
     */
    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    /**
     * @param seqSpottingText the seqSpottingText to set
     */
    public void setSeqSpottingText(String seqSpottingText) {
        this.seqSpottingText = seqSpottingText;
    }

    /**
     * @param seqSpottingActivity the seqSpottingActivity to set
     */
    public void setSeqSpottingActivity(String seqSpottingActivity) {
        this.seqSpottingActivity = seqSpottingActivity;
    }

    /**
     * @param seqSystematisationText the seqSystematisationText to set
     */
    public void setSeqSystematisationText(String seqSystematisationText) {
        this.seqSystematisationText = seqSystematisationText;
    }

    /**
     * @param seqSystematisationActivity the seqSystematisationActivity to set
     */
    public void setSeqSystematisationActivity(String seqSystematisationActivity) {
        this.seqSystematisationActivity = seqSystematisationActivity;
    }

    /**
     * @param seqKnowledgeApp the seqKnowledgeApp to set
     */
    public void setSeqKnowledgeApp(String seqKnowledgeApp) {
        this.seqKnowledgeApp = seqKnowledgeApp;
    }

    /**
     * @param seqNotion the seqNotion to set
     */
    public void setSeqNotion(Notion seqNotion) {
        this.seqNotion = seqNotion;
    }

    /**
     * @param seqSubNotion the seqSubNotion to set
     */
    public void setSeqSubNotion(Notion.SubNotion seqSubNotion) {
        this.seqSubNotion = seqSubNotion;
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
}
