package com.unalsoft.elitefle.businesslogic.facade;

import com.unalsoft.elitefle.businesslogic.service.IService;
import com.unalsoft.elitefle.vo.SupportVo;

/**
 *
 * @author Edward
 */
public class SupportFacade extends Facade<SupportVo>{

    public SupportFacade(String PUName, IService service) {
        super(PUName, service);
    }
    
    
    
}
