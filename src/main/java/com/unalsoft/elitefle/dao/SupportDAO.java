package com.unalsoft.elitefle.dao;

import com.unalsoft.elitefle.entity.Support;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Edward
 */
public class SupportDAO implements IDAO<Support>{
    
    private static SupportDAO instance;

    public static synchronized SupportDAO getInstance() {
        if (instance == null) {
            instance = new SupportDAO();
        }
        return instance;
    }

    @Override
    public void persist(Support entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Support find(Object id, EntityManager em) {
        return (Support) em.find(Support.class, id);
    }

    @Override
    public void update(Support entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Support support = (Support) em.getReference(Support.class, id);
        em.remove(support);
    }

    @Override
    public List<Support> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Support.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
   
}
