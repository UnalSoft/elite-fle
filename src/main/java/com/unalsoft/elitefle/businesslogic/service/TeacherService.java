/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.service;

import com.unalsoft.elitefle.dao.TeacherDAO;
import com.unalsoft.elitefle.dao.DAOFactory;
import com.unalsoft.elitefle.dao.UserDAO;
import com.unalsoft.elitefle.entity.Teacher;
import com.unalsoft.elitefle.entity.User;
import com.unalsoft.elitefle.vo.TeacherVo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class TeacherService implements IService<TeacherVo> {

    private static TeacherService instance;

    public static synchronized TeacherService getInstance() {
        if (instance == null) {
            instance = new TeacherService();
        }
        return instance;
    }

    @Override
    public void persist(TeacherVo vo, EntityManager em) {
        UserDAO persondao = DAOFactory.getInstance().getUserDAO(); 
        Teacher teacher =  new Teacher();
        teacher.setIdTeacher(vo.getIdTeacher());
        User person = persondao.find(vo.getPersonidPerson(), em);
        teacher.setUser(person);

        DAOFactory.getInstance().getTeacherDAO().persist(teacher, em);
        
    }

    @Override
    public TeacherVo find(Object id, EntityManager em) {
        
        TeacherVo teacherVo = DAOFactory.getInstance().getTeacherDAO().find(id, em).toVo();
        return teacherVo;
    }

    @Override
    public void update(TeacherVo vo, EntityManager em) {
        
        Teacher teacher =  new Teacher();
        teacher.setIdTeacher(vo.getIdTeacher());
        User person = DAOFactory.getInstance().getUserDAO().find(vo.getPersonidPerson(), em);
        teacher.setUser(person);
    
        DAOFactory.getInstance().getTeacherDAO().update(teacher, em);
    }

    @Override
    public void delete(Object id, EntityManager em) {
       DAOFactory.getInstance().getTeacherDAO().delete(id, em);
    }

    @Override
    public List<TeacherVo> getList(EntityManager em) {
        TeacherDAO dao =  DAOFactory.getInstance().getTeacherDAO();
        List<Teacher> teacherList = dao.getList(em);
        ArrayList<TeacherVo> teachers = new ArrayList();
        for(Teacher p : teacherList){
            teachers.add(p.toVo());
        }
        return teachers;
    }   
    
    
    public TeacherVo login(TeacherVo teacherVo, EntityManager em) {
        Teacher entity = new Teacher();
        
        Teacher teacher = DAOFactory.getInstance().getTeacherDAO().login(entity, em);
        return teacher != null? teacher.toVo():null;
    }
}
