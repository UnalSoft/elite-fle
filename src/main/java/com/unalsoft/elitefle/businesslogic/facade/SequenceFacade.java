/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.businesslogic.facade;

import com.unalsoft.elitefle.businesslogic.service.IService;
import com.unalsoft.elitefle.vo.SequenceVo;

/**
 * 
 * @author Jummartinezro
 */
public class SequenceFacade extends Facade<SequenceVo>{

    public SequenceFacade(String PUName, IService service) {
        super(PUName, service);
    }
}
