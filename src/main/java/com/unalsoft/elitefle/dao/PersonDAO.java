/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.dao;

import com.unalsoft.elitefle.entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class PersonDAO implements IDAO<Person> {

    private static PersonDAO instance;

    public static synchronized PersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAO();
        }
        return instance;
    }

    @Override
    public void persist(Person entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Person find(Object idA, EntityManager em) {
        Integer id = (Integer) idA;
        Query query = em.createNamedQuery("Person.findByIdPerson")
                .setParameter("idPerson", id);
        return (Person)query.getSingleResult();
    }

    @Override
    public void update(Person entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Person person = find(id,em);
        em.remove(person);
    }

    @Override
    public List<Person> getList(EntityManager em) {
        Query query = em.createNamedQuery("Person.findAll");
        List<Person> lista = query.getResultList();
        return lista;
    }
    
/*
    public Person login(Person entity, EntityManager em) {
        Person person;
        Query q = em.createQuery("SELECT u FROM Person u "
                + "WHERE u.idPerson LIKE :idPerson "
                + "AND u.password LIKE :password")
                .setParameter("idPerson", entity.getIdPerson().toString())
                .setParameter("password", entity.getPassword());
        try {
            person = (Person) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            person = (Person) q.getResultList().get(0);
        } catch (NoResultException e) {
            person = null;
        }
        return person;
    }
 */
    
}
