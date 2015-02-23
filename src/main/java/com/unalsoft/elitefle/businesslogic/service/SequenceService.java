/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.service;

import com.unalsoft.elitefle.dao.DAOFactory;
import com.unalsoft.elitefle.entity.Activity;
import com.unalsoft.elitefle.entity.Sequence;
import com.unalsoft.elitefle.entity.Support;
import com.unalsoft.elitefle.entity.Teacher;
import com.unalsoft.elitefle.vo.SequenceVo;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Jummartinezro
 */
public class SequenceService implements IService<SequenceVo> {
    
    private static SequenceService instance;
    
    public static synchronized SequenceService getInstance() {
        if (instance == null) {
            instance = new SequenceService();
        }
        return instance;
    }
    
    @Override
    public void persist(SequenceVo vo, EntityManager em) {
        Sequence sequence = new Sequence();
        sequence.setNameSequence(vo.getNameSequence());
        sequence.setNotion(vo.getNotion());
        sequence.setSubNotion(vo.getSubNotion());
        sequence.setLevel(vo.getLevel());
        sequence.setSupports((short) (vo.isSupports() ? 1 : 0));
        
        Activity spActivity = DAOFactory.getInstance().getActivityDAO()
                .find(vo.getIdSpottingActivity(), em);
        Activity sysActivity = DAOFactory.getInstance().getActivityDAO()
                .find(vo.getIdSystematizationActivity(), em);
        
        sequence.setSpottingActivity(spActivity);
        sequence.setSystematizationActivity(sysActivity);
        sequence.setApplicationActivity(vo.getApplicationActivity());
        
        Teacher author = DAOFactory.getInstance().getTeacherDAO()
                .find(vo.getIdAuthor(), em);
        sequence.setIdAuthor(author);
        
        sequence.setExplication(vo.getExplication());
        sequence.setCreationDate(new GregorianCalendar().getTime());
        
        List<Support> supports = new ArrayList<Support>(vo.getSupportIdList().size());
        
        for (String supportId : vo.getSupportIdList()) {
            Support support = DAOFactory.getInstance().getSupportDAO().find(supportId, em);
            support.getSequenceList().add(sequence);    
            supports.add(support);
        }
        sequence.setSupportList(supports);
        
        DAOFactory.getInstance().getSequenceDAO().persist(sequence,em);
    }
    
    @Override
    public SequenceVo find(Object id, EntityManager em) {
        Sequence sequence = DAOFactory.getInstance().getSequenceDAO().find(id, em);
        if (sequence != null) {
            return sequence.toVo();
        } else {
            return null;
        }
    }
    
    @Override
    public void update(SequenceVo vo, EntityManager em) {
        Sequence sequence = new Sequence();
        sequence.setIdSequence(vo.getIdSequence());
        sequence.setNameSequence(vo.getNameSequence());
        sequence.setNotion(vo.getNotion());
        sequence.setSubNotion(vo.getSubNotion());
        sequence.setLevel(vo.getLevel());
        sequence.setSupports((short) (vo.isSupports() ? 1 : 0));
        
        Activity spActivity = DAOFactory.getInstance().getActivityDAO()
                .find(vo.getIdSpottingActivity(), em);
        Activity sysActivity = DAOFactory.getInstance().getActivityDAO()
                .find(vo.getIdSystematizationActivity(), em);
        
        sequence.setSpottingActivity(spActivity);
        sequence.setSystematizationActivity(sysActivity);
        sequence.setApplicationActivity(vo.getApplicationActivity());
        
        Teacher author = DAOFactory.getInstance().getTeacherDAO()
                .find(vo.getIdAuthor(), em);
        sequence.setIdAuthor(author);
        
        sequence.setExplication(vo.getExplication());
        sequence.setCreationDate(new GregorianCalendar().getTime());
        
        List<Support> supports = new ArrayList<Support>(vo.getSupportIdList().size());
        
        for (String supportId : vo.getSupportIdList()) {
            Support support = DAOFactory.getInstance().getSupportDAO().find(supportId, em);
            support.getSequenceList().add(sequence);    
            supports.add(support);
        }
        sequence.setSupportList(supports);
        
        DAOFactory.getInstance().getSequenceDAO().update(sequence, em);
        
    }
    
    @Override
    public void delete(Object id, EntityManager em) {
        DAOFactory.getInstance().getSequenceDAO().delete(id, em);
    }
    
    @Override
    public List<SequenceVo> getList(EntityManager em) {
        List<SequenceVo> list = new ArrayList<SequenceVo>();
        for (Sequence sequence : DAOFactory.getInstance().getSequenceDAO().getList(em)) {
            list.add(sequence.toVo());
        }
        return list;
    }
    
}
