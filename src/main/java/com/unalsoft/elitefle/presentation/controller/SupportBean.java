package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.vo.SupportVo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edward
 */
@ManagedBean(name="supportBean")
@ViewScoped
public class SupportBean implements Serializable {
    
    private List <SupportVo> supportList;
    private List <SupportVo> filteredSupports;    
    
    private String urlSupport;
    private String title;
    private String type;
    private String notion;
    private String subNotion;
    @ManagedProperty(value = "#{teacherBean}")
    private TeacherBean user;
    
    @PostConstruct
    public void init() {        
        supportList = FacadeFactory.getInstance().getSupportFacade().getList();
    }
    
    public void addSupport() {
        SupportVo vo = new SupportVo();
        vo.setTitle(getTitle());
        vo.setType(getType());
        vo.setNotion(getNotion());
        vo.setSubNotion(getSubNotion());
        vo.setIdAuthor(getUser().getIdTeacher());
        FacadeFactory.getInstance().getSupportFacade().persist(vo);
        //TODO: Errors?
    }

    public List<SupportVo> getSupportList() {
        return supportList;
    }

    public List<SupportVo> getFilteredSupports() {
        return filteredSupports;
    }

    public void setFilteredSupports(List<SupportVo> filteredSupports) {
        this.filteredSupports = filteredSupports;
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

    public TeacherBean getUser() {
        return user;
    }

    public void setUser(TeacherBean user) {
        this.user = user;
    }       
    
}
