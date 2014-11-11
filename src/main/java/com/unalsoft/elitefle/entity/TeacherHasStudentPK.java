/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Edward
 */
@Embeddable
public class TeacherHasStudentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTeacher")
    private int idTeacher;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idStudent")
    private int idStudent;

    public TeacherHasStudentPK() {
    }

    public TeacherHasStudentPK(int idTeacher, int idStudent) {
        this.idTeacher = idTeacher;
        this.idStudent = idStudent;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTeacher;
        hash += (int) idStudent;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeacherHasStudentPK)) {
            return false;
        }
        TeacherHasStudentPK other = (TeacherHasStudentPK) object;
        if (this.idTeacher != other.idTeacher) {
            return false;
        }
        if (this.idStudent != other.idStudent) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.TeacherHasStudentPK[ idTeacher=" + idTeacher + ", idStudent=" + idStudent + " ]";
    }
    
}
