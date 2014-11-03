/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.service;

import com.unalsoft.elitefle.dao.DAOFactory;
import com.unalsoft.elitefle.dao.PersonDAO;
import com.unalsoft.elitefle.entity.Person;
import com.unalsoft.elitefle.vo.PersonVo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class PersonService implements IService<PersonVo> {
    private static  PersonService instance;
    
    public static synchronized PersonService getInstance() {
        if (instance == null) {
            instance = new PersonService();
        }
        return instance;
    }

    @Override
    public void persist(PersonVo vo, EntityManager em) {
        Person entity = new Person();
        entity.setName(vo.getName());
        entity.setLastanames(vo.getLastName());
        entity.setMail(vo.getMail());
        entity.setIdPerson(vo.getIdPerson());

        DAOFactory.getInstance().getPersonDAO().persist(entity, em);
    }

    @Override
    public PersonVo find(Object id, EntityManager em) {
        PersonDAO dao = DAOFactory.getInstance().getPersonDAO();
        PersonVo personvo = dao.find(id, em).toVo();
        return personvo;
    }

    @Override
    public void update(PersonVo vo, EntityManager em) {
        Person person = new Person();
        PersonDAO dao = DAOFactory.getInstance().getPersonDAO();
        person.setName(vo.getName());
        person.setIdPerson(vo.getIdPerson());
        person.setLastanames(vo.getLastName());
        person.setMail(vo.getLastName());
        dao.update(person, em);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        PersonDAO dao =  DAOFactory.getInstance().getPersonDAO();
        dao.delete(id, em);
    }

    @Override
    public List<PersonVo> getList(EntityManager em) {
        List<PersonVo> list = new ArrayList<PersonVo>();
        for (Person person : DAOFactory.getInstance().getPersonDAO().getList(em)) {
            list.add((person).toVo());
        }
        return list;
    }
       
}
