package com.unalsoft.elitefle.vo;

/**
 * Class that represents the value object for a teacher
 * @author juanmanuelmartinezromero
 */
public class TeacherVo implements IValueObject{
    private Integer idTeacher;
    private String username;
    private String password;
    private Integer personidPerson;

    /**
     * @return the idTeacher
     */
    public Integer getIdTeacher() {
        return idTeacher;
    }

    /**
     * @param idTeacher the idTeacher to set
     */
    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the personidPerson
     */
    public Integer getPersonidPerson() {
        return personidPerson;
    }

    /**
     * @param personidPerson the personidPerson to set
     */
    public void setPersonidPerson(Integer personidPerson) {
        this.personidPerson = personidPerson;
    }
   
    
}
