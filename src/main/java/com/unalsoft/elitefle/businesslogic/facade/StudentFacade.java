package com.unalsoft.elitefle.businesslogic.facade;

import com.unalsoft.elitefle.businesslogic.service.IService;
import com.unalsoft.elitefle.vo.StudentVo;

/**
 *
 * @author Edward
 */
public class StudentFacade extends Facade<StudentVo> {

    public StudentFacade(String PUName, IService service) {
        super(PUName, service);
    }
    
}
