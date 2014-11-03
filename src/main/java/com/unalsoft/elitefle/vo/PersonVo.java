/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.vo;

import java.util.List;

/**
 *
 * @author germanpinerosm
 */
public class PersonVo implements IValueObject {

    private Integer idPerson;
    private String name;
    private String lastName;
    private String mail;
    private Integer phone;
    private String adress;
    private List<TeacherVo> teacherList;

    public PersonVo() {
    }

    public PersonVo(Integer idPerson, String name, String lastName, String mail, Integer phone, String adress) {
        this.idPerson = idPerson;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.adress = adress;
    }

    /**
     * @return the idPerson
     */
    public Integer getIdPerson() {
        return idPerson;
    }

    /**
     * @param idPerson the idPerson to set
     */
    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the phone
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * @return the teacherList
     */
    public List<TeacherVo> getTeacherList() {
        return teacherList;
    }

    /**
     * @param teacherList the teacherList to set
     */
    public void setTeacherList(List<TeacherVo> teacherList) {
        this.teacherList = teacherList;
    }
}
