package com.unalsoft.elitefle.businesslogic.facade;

import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author Edward
 */
public class PersistException extends DatabaseException {

    PersistException(String message) {
        super(message);
    }
    
}
