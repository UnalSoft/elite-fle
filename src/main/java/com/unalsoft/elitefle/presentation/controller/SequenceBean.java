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

    private String seqName;
    private Notion seqNotion;
    private Notion.SubNotion seqSubNotion;
    private List<SelectItem> notions;
    private List<SelectItem> subNotions;

    @PostConstruct
    public void init() {
        seqNotion = Notion.textualStructuring;
        notions = new ArrayList<SelectItem>();
        
        for (Notion notion : Notion.values()) {
            notions.add(new SelectItem(notion, notion.getDescription()));
        }
        setSubNotions();
    }

    /**
     * @return the seqName
     */
    public String getSeqName() {
        return seqName;
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
     * @param seqName the seqName to set
     */
    public void setSeqName(String seqName) {
        this.seqName = seqName;
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

    public void changeSubNotions(ValueChangeEvent e) {
        seqNotion=(Notion)e.getNewValue();
        System.out.println("The selected Notion is "+seqNotion);
        setSubNotions();
    }
    
    private void setSubNotions() {
        subNotions = new ArrayList<SelectItem>();
        for (Notion.SubNotion subNotion : seqNotion.getSubNotions()) {
            subNotions.add(new SelectItem(
                    subNotion, subNotion.getDescription()));
        }
    }
}
