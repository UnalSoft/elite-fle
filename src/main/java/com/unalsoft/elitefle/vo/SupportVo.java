package com.unalsoft.elitefle.vo;

import java.util.List;

/**
 *
 * @author Edward
 */
public class SupportVo implements IValueObject {

    private String urlSupport;
    private String title;
    private String type;
    private String notion;
    private String subNotion;
    private String date;
    private List<Integer> sequenceIdList;
    private Integer idAuthor;
    private String authorName;

    public SupportVo() {
    }
    
    public SupportVo(String urlSupport, String title, String type, String notion, String subNotion) {
        this.urlSupport = urlSupport;
        this.title = title;
        this.type = type;
        this.notion = notion;
        this.subNotion = subNotion;
    }

    public String getUrlSupport() {
        return urlSupport;
    }

    public void setUrlSupport(String urlSupport) {
        this.urlSupport = urlSupport;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotion() {
        return notion;
    }

    public void setNotion(String notion) {
        this.notion = notion;
    }

    public String getSubNotion() {
        return subNotion;
    }

    public void setSubNotion(String subNotion) {
        this.subNotion = subNotion;
    }

    public List<Integer> getSequenceIdList() {
        return sequenceIdList;
    }

    public void setSequenceIdList(List<Integer> sequenceIdList) {
        this.sequenceIdList = sequenceIdList;
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
