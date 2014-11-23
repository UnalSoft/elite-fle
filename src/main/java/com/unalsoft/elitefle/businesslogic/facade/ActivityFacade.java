/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.businesslogic.facade;

import com.unalsoft.elitefle.businesslogic.service.IService;
import com.unalsoft.elitefle.vo.ActivityVo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 
 * @author Jummartinezro
 */
public class ActivityFacade extends Facade<ActivityVo>{

    public ActivityFacade(String PUName, IService service) {
        super(PUName, service);
    }
    public Integer findOrCreate(ActivityVo activityVo){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
