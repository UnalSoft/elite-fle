/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.presentation.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author juanmanuelmartinezromero
 */
@ManagedBean(name = "sequenceBean")
@ViewScoped
public class SequenceBean {

    private String seqName;
    private String seqNotion;
    private List<SelectItem> notions;
    
    
    @PostConstruct
    public void init() {
        //TODO: Change to the correct notions
        notions = new ArrayList<SelectItem>();
        notions.add(new SelectItem("San Francisco"));
        notions.add(new SelectItem("San Francisco1"));
        notions.add(new SelectItem("San Francisco2"));
        notions.add(new SelectItem("San Francisco3"));
        notions.add(new SelectItem("San Francisco4"));
        notions.add(new SelectItem("San Francisco5"));
        notions.add(new SelectItem("San Francisco6"));
    }

    /**
     * Get the value of notions
     *
     * @return the value of notions
     */
    public List getNotions() {
        return notions;
    }

    /**
     * Set the value of notions
     *
     * @param notions new value of notions
     */
    public void setNotions(List<SelectItem> notions) {
        this.notions = notions;
    }

    /**
     * Get the value of seqNotion
     *
     * @return the value of seqNotion
     */
    public String getSeqNotion() {
        return seqNotion;
    }

    /**
     * Set the value of seqNotion
     *
     * @param seqNotion new value of seqNotion
     */
    public void setSeqNotion(String seqNotion) {
        this.seqNotion = seqNotion;
    }

    /**
     * Get the value of seqName
     *
     * @return the value of seqName
     */
    public String getSeqName() {
        return seqName;
    }

    /**
     * Set the value of seqName
     *
     * @param seqName new value of seqName
     */
    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    /**
     * Creates a new instance of SequenceBean
     */
    public SequenceBean() {
    }

}
