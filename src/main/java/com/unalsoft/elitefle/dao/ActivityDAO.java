/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.dao;

import com.unalsoft.elitefle.entity.Activity;
import com.unalsoft.elitefle.vo.ActivityVo;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author Jummartinezro
 */
public class ActivityDAO {

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

    public void persist(Activity entity, EntityManager em) {        
        em.persist(entity);
    }
}
