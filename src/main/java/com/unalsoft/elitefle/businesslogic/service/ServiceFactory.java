/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.service;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class ServiceFactory {

    private static ServiceFactory instance;

    public static synchronized ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public TeacherService getTeacherService() {
        return TeacherService.getInstance();
    }

    public UserService getUserService() {
        return UserService.getInstance();
    }
    
    public SupportService getSupportService() {
        return SupportService.getInstance();
    }
}
