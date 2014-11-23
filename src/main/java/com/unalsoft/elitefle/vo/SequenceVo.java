/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.vo;

/**
 *
 * @author Jummartinezro
 */
public class SequenceVo implements IValueObject{
private String name;
private String notion;
private String subNotion;
private String level;
private boolean supports;
private String applicationActivity;
private String urlExplication;
//@TODO link between Sequence and supports
private Integer idAuthor;
private Integer idSpottingActivity;
private Integer idSystematisationActivity;
//@TODO persist spotting and systematization activities
//@TODO persist link between sequence and student when necessary

    /**
     * @return the name
     */
    public String getName() {
        return name;
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
     * @return the urlExplication
     */
    public String getUrlExplication() {
        return urlExplication;
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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @param urlExplication the urlExplication to set
     */
    public void setUrlExplication(String urlExplication) {
        this.urlExplication = urlExplication;
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


}
