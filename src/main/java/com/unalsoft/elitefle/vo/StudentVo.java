package com.unalsoft.elitefle.vo;

/**
 *
 * @author Edward
 */
public class StudentVo extends UserVo implements IValueObject {
    
    private Integer idStudent;

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }        
    
}
