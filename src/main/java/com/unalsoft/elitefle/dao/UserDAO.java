/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.dao;

import com.unalsoft.elitefle.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class UserDAO implements IDAO<User> {

    private static UserDAO instance;

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public void persist(User entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public User find(Object idA, EntityManager em) {
        Integer id = (Integer) idA;
        Query query = em.createNamedQuery("User.findByIdUser")
                .setParameter("idUser", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void update(User entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        User person = find(id, em);
        em.remove(person);
    }

    @Override
    public List<User> getList(EntityManager em) {
        Query query = em.createNamedQuery("User.findAll");
        List<User> listUsers = query.getResultList();
        return listUsers;
    }

    public User login(User entity, EntityManager em) {
        User administrator;
        Query q = em.createQuery("SELECT u FROM User u where u.idUser=0");
        //+ "AND u.password LIKE :password")
//                .setParameter("username", entity.getUsername())
//                .setParameter("password", entity.getPassword());
        try {
//            System.out.println("q.getSingleResult().toStrin÷g() = " + q.getSingleResult().toString());
            administrator = (User) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            administrator = (User) q.getResultList().get(0);
        } catch (NoResultException e) {
            administrator = null;
        }
        return administrator;
    }

}
