/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.service;

import com.unalsoft.elitefle.vo.SequenceVo;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SequenceVo find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(SequenceVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SequenceVo> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
