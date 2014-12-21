/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.dao;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author juanmanuelmartinezromero
 */
public interface IDAO<E> {

    void persist(E entity, EntityManager em);

    E find(Object id, EntityManager em);

    void update(E entity, EntityManager em);

    void delete(Object id, EntityManager em);

    List<E> getList(EntityManager em);
}
