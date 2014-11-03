/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.businesslogic.facade;

import com.unalsoft.elitefle.businesslogic.service.PersonService;
import com.unalsoft.elitefle.vo.PersonVo;

/**
 *
 * @author juanmanuelmartinezromero
 */
public class PersonFacade extends Facade<PersonVo> {
    
    public PersonFacade(String PUName, PersonService service){
        super(PUName, service);
    }
    
/*
    public PersonVo login(PersonVo personVo) {
        try {
            em = emf.createEntityManager();
            return ((PersonService)service).login(personVo, em);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
    }
    * */
    
}
