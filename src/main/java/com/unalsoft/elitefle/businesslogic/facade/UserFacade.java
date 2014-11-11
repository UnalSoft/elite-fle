/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.facade;

import com.unalsoft.elitefle.businesslogic.service.UserService;
import com.unalsoft.elitefle.vo.UserVo;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class UserFacade extends Facade<UserVo> {

    public UserFacade(String PUName, UserService service) {
        super(PUName, service);
    }

    public UserVo login(UserVo userVo) {

        try {
            em = emf.createEntityManager();
            return ((UserService) service).login(userVo, em);
        } finally {
            if (em != null) { 
                em.clear();
                em.close();
            }
        }
    }
}
