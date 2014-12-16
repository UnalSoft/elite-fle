/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.dao;

import com.unalsoft.elitefle.entity.Activity;
import com.unalsoft.elitefle.vo.ActivityVo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Jummartinezro
 */
public class ActivityDAO implements IDAO<Activity>{

    private static ActivityDAO instance;

    public static synchronized ActivityDAO getInstance() {
        if (instance == null) {
            instance = new ActivityDAO();
        }
        return instance;
    }

    /**
     * Try to find an activity by all his fields
     * @param vo
     * @param em
     * @return
     */
    public Integer findByAll(ActivityVo vo, EntityManager em) {
        Integer id;
        Query q = em.createQuery("SELECT a.idActivity FROM Activity a "
                + "WHERE a.nameText LIKE :nameText "
                + "AND a.type LIKE :typeActivity AND a.urlText LIKE :url")
                .setParameter("nameText", vo.getName())
                .setParameter("typeActivity", vo.getType())
                .setParameter("url", vo.getUrl());
        try {
            id = (Integer) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            id = (Integer) q.getResultList().get(0);
        } catch (NoResultException e) {
            id = null;
        }
        return id;
    }
    
    @Override
    public void persist(Activity entity, EntityManager em) {        
        em.persist(entity);
    }

    @Override
    public Activity find(Object id, EntityManager em) {
        return (Activity) em.find(Activity.class, id);
    }

    @Override
    public void update(Activity entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Activity support = (Activity) em.getReference(Activity.class, id);
        em.remove(support);
    }

    @Override
    public List<Activity> getList(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Activity.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
}
