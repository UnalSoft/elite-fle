/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.vo;

import java.util.List;

/**
 *
 * @author Jummartinezro
 */
public class SequenceVo implements IValueObject {

    private String nameSequence;
    private String notion;
    private String subNotion;
    private String level;
    private boolean supports;
    private String applicationActivity;
    private String explication;
    private String creationDate;
    private Integer idAuthor;
    private Integer idSpottingActivity;
    private Integer idSystematisationActivity;
    private List<String> supportIdList;
    private int[][] studentHasSequencePKs;

    /**
     * @return the nameSequence
     */
    public String getNameSequence() {
        return nameSequence;
    }

    /**
     * @return the notion
     */
    public String getNotion() {
        return notion;
    }

    /**
     * @return the subNotion
     */
    public String getSubNotion() {
        return subNotion;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @return the supports
     */
    public boolean isSupports() {
        return supports;
    }

    /**
     * @return the applicationActivity
     */
    public String getApplicationActivity() {
        return applicationActivity;
    }

    /**
     * @return the explication
     */
    public String getExplication() {
        return explication;
    }

    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @return the idAuthor
     */
    public Integer getIdAuthor() {
        return idAuthor;
    }

    /**
     * @return the idSpottingActivity
     */
    public Integer getIdSpottingActivity() {
        return idSpottingActivity;
    }

    /**
     * @return the idSystematisationActivity
     */
    public Integer getIdSystematisationActivity() {
        return idSystematisationActivity;
    }

    /**
     * @return the supportIdList
     */
    public List<String> getSupportIdList() {
        return supportIdList;
    }

    /**
     * @return the studentHasSequencePKs
     */
    public int[][] getStudentHasSequencePKs() {
        return studentHasSequencePKs;
    }

    /**
     * @param nameSequence the nameSequence to set
     */
    public void setNameSequence(String nameSequence) {
        this.nameSequence = nameSequence;
    }

    /**
     * @param notion the notion to set
     */
    public void setNotion(String notion) {
        this.notion = notion;
    }

    /**
     * @param subNotion the subNotion to set
     */
    public void setSubNotion(String subNotion) {
        this.subNotion = subNotion;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @param supports the supports to set
     */
    public void setSupports(boolean supports) {
        this.supports = supports;
    }

    /**
     * @param applicationActivity the applicationActivity to set
     */
    public void setApplicationActivity(String applicationActivity) {
        this.applicationActivity = applicationActivity;
    }

    /**
     * @param explication the explication to set
     */
    public void setExplication(String explication) {
        this.explication = explication;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @param idAuthor the idAuthor to set
     */
    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    /**
     * @param idSpottingActivity the idSpottingActivity to set
     */
    public void setIdSpottingActivity(Integer idSpottingActivity) {
        this.idSpottingActivity = idSpottingActivity;
    }

    /**
     * @param idSystematisationActivity the idSystematisationActivity to set
     */
    public void setIdSystematisationActivity(Integer idSystematisationActivity) {
        this.idSystematisationActivity = idSystematisationActivity;
    }

    /**
     * @param supportIdList the supportIdList to set
     */
    public void setSupportIdList(List<String> supportIdList) {
        this.supportIdList = supportIdList;
    }

    /**
     * @param studentHasSequencePKs the studentHasSequencePKs to set
     */
    public void setStudentHasSequencePKs(int[][] studentHasSequencePKs) {
        this.studentHasSequencePKs = studentHasSequencePKs;
    }
}
