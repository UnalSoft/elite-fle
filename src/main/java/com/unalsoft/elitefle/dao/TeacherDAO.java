/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.dao;

import com.unalsoft.elitefle.entity.Teacher;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class TeacherDAO implements IDAO<Teacher> {

    private static TeacherDAO instance;

    public static synchronized TeacherDAO getInstance() {
        if (instance == null) {
            instance = new TeacherDAO();
        }
        return instance;
    }

    @Override
    public void persist(Teacher entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Teacher find(Object id, EntityManager em) {
        return (Teacher) em.find(Teacher.class, id);
    }

    @Override
    public void update(Teacher entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Teacher admin = find(id, em);
        em.remove(admin);
    }

    @Override
    public List<Teacher> getList(EntityManager em) {
        Query query = em.createNamedQuery("Teacher.findAll");
        List<Teacher> lista = query.getResultList();
        return lista;
    }

    public Teacher login(Teacher entity, EntityManager em) {
        Teacher teacher;
        //TODO Fix to User
        //TODO Delete when login complete
//        Query q = em.createQuery("SELECT u FROM Teacher u "
//                + "WHERE u.username LIKE :username "
//                + "AND u.password LIKE :password")
//                
//                .setParameter("username", entity.getUsername())
//                .setParameter("password", entity.getPassword());
//        try {
//            teacher = (Teacher) q.getSingleResult();
//        } catch (NonUniqueResultException e) {
//            teacher = (Teacher) q.getResultList().get(0);
//        } catch (NoResultException e) {
//            teacher = null;
//        }
//        return teacher;
        return null;
    }
}
