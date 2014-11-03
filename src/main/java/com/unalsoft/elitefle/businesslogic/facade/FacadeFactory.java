/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.facade;

import com.unalsoft.elitefle.businesslogic.service.ServiceFactory;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class FacadeFactory {

    private final String PU = "com.unalsoft.elite-flePU";
    private static FacadeFactory instance;

    public static synchronized FacadeFactory getInstance() {
        if (instance == null) {
            instance = new FacadeFactory();
        }
        return instance;
    }

    public TeacherFacade getTeacherFacade() {
        return new TeacherFacade(PU, ServiceFactory.getInstance().getTeacherService());
    }

    public PersonFacade getPersonFacade() {
        return new PersonFacade(PU, ServiceFactory.getInstance().getPersonService());
    }
}
