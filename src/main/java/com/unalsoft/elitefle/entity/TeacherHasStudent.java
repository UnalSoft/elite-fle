/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Edward
 */
@Entity
@Table(name = "teacher_has_student")
@NamedQueries({
    @NamedQuery(name = "TeacherHasStudent.findAll", query = "SELECT t FROM TeacherHasStudent t"),
    @NamedQuery(name = "TeacherHasStudent.findByIdTeacher", query = "SELECT t FROM TeacherHasStudent t WHERE t.teacherHasStudentPK.idTeacher = :idTeacher"),
    @NamedQuery(name = "TeacherHasStudent.findByIdStudent", query = "SELECT t FROM TeacherHasStudent t WHERE t.teacherHasStudentPK.idStudent = :idStudent"),
    @NamedQuery(name = "TeacherHasStudent.findByGroup", query = "SELECT t FROM TeacherHasStudent t WHERE t.group = :group")})
public class TeacherHasStudent implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TeacherHasStudentPK teacherHasStudentPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Group")
    private String group;
    @JoinColumn(name = "idStudent", referencedColumnName = "idStudent", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "idTeacher", referencedColumnName = "idTeacher", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Teacher teacher;

    public TeacherHasStudent() {
    }

    public TeacherHasStudent(TeacherHasStudentPK teacherHasStudentPK) {
        this.teacherHasStudentPK = teacherHasStudentPK;
    }

    public TeacherHasStudent(TeacherHasStudentPK teacherHasStudentPK, String group) {
        this.teacherHasStudentPK = teacherHasStudentPK;
        this.group = group;
    }

    public TeacherHasStudent(int idTeacher, int idStudent) {
        this.teacherHasStudentPK = new TeacherHasStudentPK(idTeacher, idStudent);
    }

    public TeacherHasStudentPK getTeacherHasStudentPK() {
        return teacherHasStudentPK;
    }

    public void setTeacherHasStudentPK(TeacherHasStudentPK teacherHasStudentPK) {
        this.teacherHasStudentPK = teacherHasStudentPK;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (teacherHasStudentPK != null ? teacherHasStudentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeacherHasStudent)) {
            return false;
        }
        TeacherHasStudent other = (TeacherHasStudent) object;
        if ((this.teacherHasStudentPK == null && other.teacherHasStudentPK != null) || (this.teacherHasStudentPK != null && !this.teacherHasStudentPK.equals(other.teacherHasStudentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.TeacherHasStudent[ teacherHasStudentPK=" + teacherHasStudentPK + " ]";
    }
    
}
