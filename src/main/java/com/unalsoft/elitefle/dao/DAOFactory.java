/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.dao;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class DAOFactory {

    private static DAOFactory instance;

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public TeacherDAO getTeacherDAO() {
        return TeacherDAO.getInstance();
    }

    public UserDAO getUserDAO() {
        return UserDAO.getInstance();
    }
    
    public SupportDAO getSupportDAO() {
        return SupportDAO.getInstance();
    }

    public ActivityDAO getActivityDAO() {
            return ActivityDAO.getInstance();
    }

    public SequenceDAO getSequenceDAO() {
        return SequenceDAO.getInstance();
    }
}
