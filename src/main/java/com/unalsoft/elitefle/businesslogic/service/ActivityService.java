/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.service;

import com.unalsoft.elitefle.dao.DAOFactory;
import com.unalsoft.elitefle.entity.Activity;
import com.unalsoft.elitefle.vo.ActivityVo;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Jummartinezro
 */
public class ActivityService implements IService<ActivityVo> {

    private static ActivityService instance;

    public static synchronized ActivityService getInstance() {
        if (instance == null) {
            instance = new ActivityService();
        }
        return instance;
    }

    @Override
    public void persist(ActivityVo vo, EntityManager em) {
        Activity entity = new Activity();
        entity.setNameText(vo.getName());
        //@TODO:SequenceList and SequenceList1 ??
        //      Add when created the sequence ?
        //      activity.setSequenceList(null);
        //      activity.setSequenceList1(null);
        entity.setType(vo.getType());
        entity.setUrlText(vo.getUrl());
        DAOFactory.getInstance().getActivityDAO().persist(entity,em);
    }

    @Override
    public ActivityVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ActivityVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ActivityVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Try to find an activity by all his fields, if it doesn't exist, then
     * creates an activity and return his id
     *
     * @param vo
     * @param em
     * @return
     */
    public Integer findByAll(ActivityVo vo, EntityManager em) {
        return DAOFactory.getInstance().getActivityDAO()
                .findByAll(vo, em);        
    }
}
