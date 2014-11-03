/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.facade;

import com.unalsoft.elitefle.businesslogic.service.TeacherService;
import com.unalsoft.elitefle.vo.TeacherVo;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class TeacherFacade extends Facade<TeacherVo> {

    public TeacherFacade(String PUName, TeacherService service) {
        super(PUName, service);
    }
    
    public TeacherVo login(TeacherVo teacherVo) {
        try {
            em = emf.createEntityManager();
            return ((TeacherService)service).login(teacherVo, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
