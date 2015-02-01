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

    public UserFacade getUserFacade() {
        return new UserFacade(PU, ServiceFactory.getInstance().getUserService());
    }

    public SupportFacade getSupportFacade() {
        return new SupportFacade(PU, ServiceFactory.getInstance().getSupportService());
    }

    public SequenceFacade getSequenceFacade() {
        return new SequenceFacade(PU, ServiceFactory.getInstance().getSequenceService());
    }

    public ActivityFacade getActivityFacade() {
        return new ActivityFacade(PU,ServiceFactory.getInstance().getActivityService());
    }
    
    public StudentFacade getStudentFacade() {
        return new StudentFacade(PU, ServiceFactory.getInstance().getStudentService());
    }
}
