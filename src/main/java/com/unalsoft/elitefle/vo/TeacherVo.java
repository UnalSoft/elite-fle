package com.unalsoft.elitefle.vo;

/**
 * Class that represents the value object for a teacher
 * @author juanmanuelmartinezromero
 */
public class TeacherVo implements IValueObject{
    private Integer idTeacher; 
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
