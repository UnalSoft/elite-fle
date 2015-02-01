package com.unalsoft.elitefle.vo;

/**
 * Class that represents the value object for a teacher
 * @author juanmanuelmartinezromero
 */
public class TeacherVo extends UserVo implements IValueObject{
    private Integer idTeacher; 

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
    
}
