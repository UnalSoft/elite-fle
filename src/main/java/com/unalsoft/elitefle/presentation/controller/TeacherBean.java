/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package com.unalsoft.elitefle.presentation.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
*
* @author FAMILIA
*/

@ManagedBean
@SessionScoped
public class TeacherBean implements Serializable {
    private Integer idTeacher;
    private String username;
    //private String password;
    private String type;
    private Integer personidPerson;
    private boolean loggedIn;
    
    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    /*public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPersonidPerson() {
        return personidPerson;
    }

    /**
* @param personidPerson the personidPerson to set
*/
    public void setPersonidPerson(Integer personidPerson) {
        this.personidPerson = personidPerson;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public String logOut() {
        idTeacher = null;
        type = null;
        username = null;
        personidPerson = null;
        loggedIn = false;
        return "logout";
    }
}