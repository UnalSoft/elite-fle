package com.unalsoft.elitefle.businesslogic.service;

import com.unalsoft.elitefle.dao.DAOFactory;
import com.unalsoft.elitefle.dao.StudentDAO;
import com.unalsoft.elitefle.entity.Student;
import com.unalsoft.elitefle.vo.StudentVo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Edward
 */
public class StudentService implements IService<StudentVo> {

    private static StudentService instance;

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }
    
    @Override
    public void persist(StudentVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentVo find(Object id, EntityManager em) {
        Student find = DAOFactory.getInstance().getStudentDAO().find(id, em);
        StudentVo vo = null;
        if (find != null) {
            vo = toVo(find);
        }
        return vo;
    }

    @Override
    public void update(StudentVo vo, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object id, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentVo> getList(EntityManager em) {
        StudentDAO dao = DAOFactory.getInstance().getStudentDAO();
        List<Student> studentList = dao.getList(em);
        ArrayList<StudentVo> students = new ArrayList();
        for (Student p : studentList) {
            students.add(toVo(p));
        }
        return students;
    }

    private StudentVo toVo(Student entity) {
        StudentVo vo = new StudentVo();
        vo.setIdStudent(entity.getIdStudent());
        vo.setIdUser(entity.getIdUser());
        vo.setUserName(entity.getUsername());
        vo.setMail(entity.getMail());
        vo.setName(entity.getName());
        //vo.setPassword(entity.getPassword());
        return vo;
    }
    
}
