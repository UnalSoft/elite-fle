package com.unalsoft.elitefle.businesslogic.service;

import com.unalsoft.elitefle.dao.DAOFactory;
import com.unalsoft.elitefle.entity.Support;
import com.unalsoft.elitefle.entity.Teacher;
import com.unalsoft.elitefle.vo.SupportVo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Edward
 */
public class SupportService implements IService<SupportVo>{
    
    private static SupportService instance;
    
    private SupportService() {        
    }
    
    public static synchronized SupportService getInstance() {
        if (instance == null) {
            instance = new SupportService();
        }
        return instance;
    }

    @Override
    public void persist(SupportVo vo, EntityManager em) {
        Support entity = new Support();
        entity.setUrlSupport(vo.getUrlSupport());
        entity.setTitle(vo.getTitle());
        entity.setNotion(vo.getNotion());
        entity.setSubNotion(vo.getSubNotion());
        entity.setType(vo.getType());
        
        Teacher author = DAOFactory.getInstance().getTeacherDAO().find(vo.getIdAuthor(), em);
        author.getSupportList().add(entity);
        entity.setAuthor(author);
        
        DAOFactory.getInstance().getSupportDAO().persist(entity, em);
    }

    @Override
    public SupportVo find(Object id, EntityManager em) {
        Support support = DAOFactory.getInstance().getSupportDAO().find(id, em);
        if (support != null) {
            return support.toVo();
        } else {
            return null;
        }
    }

    @Override
    public void update(SupportVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SupportVo> getList(EntityManager em) {
        List<SupportVo> list = new ArrayList<SupportVo>();
        for (Support support : DAOFactory.getInstance().getSupportDAO().getList(em)) {
            list.add(support.toVo());
        }
        return list;
    }
            
}
