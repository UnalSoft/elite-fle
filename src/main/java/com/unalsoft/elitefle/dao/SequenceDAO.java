/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.dao;

import com.unalsoft.elitefle.entity.Sequence;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Jummartinezro
 */
public class SequenceDAO implements IDAO<Sequence> {

    private static SequenceDAO instance;

    public static synchronized SequenceDAO getInstance() {
        if (instance == null) {
            instance = new SequenceDAO();
        }
        return instance;
    }

    @Override
    public void persist(Sequence entity, EntityManager em) {

//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<Sequence>> constraintViolations = validator.validate(entity);
//        if (constraintViolations.size() > 0) {
//            Iterator<ConstraintViolation<Sequence>> iterator = constraintViolations.iterator();
//            while (iterator.hasNext()) {
//                ConstraintViolation<Sequence> cv = iterator.next();
//                System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
//
//                System.out.println(cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
//            }
//        } else {
            em.persist(entity);
//        }

    }

    @Override
    public Sequence find(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Sequence entity, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sequence> getList(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
