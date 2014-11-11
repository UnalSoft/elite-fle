/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.service;

import com.unalsoft.elitefle.dao.DAOFactory;
import com.unalsoft.elitefle.dao.UserDAO;
import com.unalsoft.elitefle.entity.User;
import com.unalsoft.elitefle.vo.UserVo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class UserService implements IService<UserVo> {

    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void persist(UserVo vo, EntityManager em) {
        User entity = new User();

        entity.setIdUser(vo.getIdUser());
        entity.setName(vo.getName());
        entity.setUsername(vo.getUserName());
        entity.setPassword(vo.getPassword());
        entity.setMail(vo.getMail());

        DAOFactory.getInstance().getUserDAO().persist(entity, em);
    }

    @Override
    public UserVo find(Object id, EntityManager em) {
        UserDAO dao = DAOFactory.getInstance().getUserDAO();
        UserVo personvo = dao.find(id, em).toVo();
        return personvo;
    }

    @Override
    public void update(UserVo vo, EntityManager em) {
        User user = new User();
        UserDAO dao = DAOFactory.getInstance().getUserDAO();

        user.setIdUser(vo.getIdUser());
        user.setName(vo.getName());
        user.setUsername(vo.getUserName());
        user.setPassword(vo.getPassword());
        user.setMail(vo.getMail());

        dao.update(user, em);
    }

    @Override
    public void delete(Object id, EntityManager em) {
        UserDAO dao = DAOFactory.getInstance().getUserDAO();
        dao.delete(id, em);
    }

    @Override
    public List<UserVo> getList(EntityManager em) {
        List<UserVo> list = new ArrayList<UserVo>();
        for (User person : DAOFactory.getInstance().getUserDAO().getList(em)) {
            list.add((person).toVo());
        }
        return list;
    }

    public UserVo login(UserVo userVo, EntityManager em) {
        User entity = new User();
        //TODO: encrypt passwd
        entity.setUsername(userVo.getUserName());
        entity.setPassword(userVo.getPassword());
        
        User administrator = DAOFactory.getInstance().getUserDAO().login(entity, em);
        return administrator != null? administrator.toVo():null;
    }

}
