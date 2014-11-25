/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.businesslogic.facade;

import com.unalsoft.elitefle.businesslogic.service.ActivityService;
import com.unalsoft.elitefle.businesslogic.service.IService;
import com.unalsoft.elitefle.vo.ActivityVo;

/**
 * 
 * @author Jummartinezro
 */
public class ActivityFacade extends Facade<ActivityVo>{

    public ActivityFacade(String PUName, IService service) {
        super(PUName, service);
    }
    /**
     * Try to find an activity by all his fields, if it doesn't exist, then 
     * creates an activity and return his id
     * @param activityVo
     * @return 
     */
    public Integer findByAll(ActivityVo activityVo){
        try {
            return ((ActivityService)service).findByAll(activityVo, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
}
