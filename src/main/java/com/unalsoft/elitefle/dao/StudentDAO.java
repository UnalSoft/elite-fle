package com.unalsoft.elitefle.dao;

import com.unalsoft.elitefle.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Edward
 */
public class StudentDAO implements IDAO<Student> {
    
    private static StudentDAO instance;

    public static synchronized StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    @Override
    public void persist(Student entity, EntityManager em) {
        em.persist(entity);
    }

    @Override
    public Student find(Object id, EntityManager em) {
        return (Student) em.find(Student.class, id);
    }

    @Override
    public void update(Student entity, EntityManager em) {
        em.merge(entity);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        Student find = find(id, em);
        em.remove(find);
    }

    @Override
    public List<Student> getList(EntityManager em) {
        Query query = em.createNamedQuery("Student.findAll");
        List<Student> list = query.getResultList();
        return list;
    }
    
}
