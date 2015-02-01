/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.service;

import com.unalsoft.elitefle.dao.TeacherDAO;
import com.unalsoft.elitefle.dao.DAOFactory;
import com.unalsoft.elitefle.entity.Teacher;
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
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(vo.getIdTeacher());

        DAOFactory.getInstance().getTeacherDAO().persist(teacher, em);

    }

    @Override
    public TeacherVo find(Object id, EntityManager em) {
        //TODO Change if necessary
        Teacher find = DAOFactory.getInstance().getTeacherDAO().find(id, em);
        TeacherVo teacherVo = null;
        if (find != null) {
            teacherVo = toVo(find);
        }
        return teacherVo;
    }

    @Override
    public void update(TeacherVo vo, EntityManager em) {

        Teacher teacher = new Teacher();
        teacher.setIdTeacher(vo.getIdTeacher());

        DAOFactory.getInstance().getTeacherDAO().update(teacher, em);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        DAOFactory.getInstance().getTeacherDAO().delete(id, em);
    }

    @Override
    public List<TeacherVo> getList(EntityManager em) {
        TeacherDAO dao = DAOFactory.getInstance().getTeacherDAO();
        List<Teacher> teacherList = dao.getList(em);
        ArrayList<TeacherVo> teachers = new ArrayList();
        for (Teacher p : teacherList) {
            teachers.add(toVo(p));
        }
        return teachers;
    }

    public TeacherVo login(TeacherVo teacherVo, EntityManager em) {
        Teacher entity = new Teacher();

        Teacher teacher = DAOFactory.getInstance().getTeacherDAO().login(entity, em);
        return teacher != null? toVo(teacher) : null;
    }
    
    public TeacherVo toVo(Teacher entity) {
        TeacherVo vo = new TeacherVo();
        vo.setIdUser(entity.getIdUser());
        vo.setIdTeacher(entity.getIdTeacher());
        vo.setUserName(entity.getUsername());
        vo.setMail(entity.getMail());
        vo.setName(entity.getName());
        //vo.setPassword(entity.getPassword());
        return vo;
    }
}
