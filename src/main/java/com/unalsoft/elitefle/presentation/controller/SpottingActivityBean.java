package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.entity.xml.*;
import com.unalsoft.elitefle.vo.ActivityVo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edward
 */
@ManagedBean(name = "spottingActivityBean")
@ViewScoped
public class SpottingActivityBean {

    private ActivityVo activity;
    private Integer idActivity;
    private DocumentTexte text;

    public void preRenderView() {
        if (getIdActivity() != null) {
            activity = FacadeFactory.getInstance().getActivityFacade().find(getIdActivity());
            text = Parser.parseXML(activity.getUrl());
        } else {
            //@TODO Error page
        }
    }

    public ActivityVo getActivity() {
        return activity;
    }

    public void setActivity(ActivityVo activity) {
        this.activity = activity;
    }

    public Integer getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

}
