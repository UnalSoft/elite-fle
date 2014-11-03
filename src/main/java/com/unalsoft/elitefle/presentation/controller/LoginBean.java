/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.TeacherFacade;
import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.vo.TeacherVo;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juanmanuelmartinezromero
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    @ManagedProperty(value = "#{teacherBean}")
    private TeacherBean user;

    public LoginBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TeacherBean getUser() {
        return user;
    }

    public void setUser(TeacherBean user) {
        this.user = user;
    }

    public String login() {
        TeacherVo teacherVo = new TeacherVo();
        TeacherFacade teacherFacade = FacadeFactory.getInstance().getTeacherFacade();

        teacherVo.setUsername(getUsername());
        teacherVo.setPassword(getPassword());

        TeacherVo login = teacherFacade.login(teacherVo);

        if (login != null) {
            user.setUsername(login.getUsername());
            user.setPersonidPerson(login.getPersonidPerson());
            user.setLoggedIn(true);
            return "success";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "loginForm:idUser", new FacesMessage(
                            "¡Id de usuario o contraseña inválidos!"));
            return "failure";
        }
    }
}
