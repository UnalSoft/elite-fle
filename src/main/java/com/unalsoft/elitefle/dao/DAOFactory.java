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

    public PersonDAO getPersonDAO() {
        return PersonDAO.getInstance();
    }
}
