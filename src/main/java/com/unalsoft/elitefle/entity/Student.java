/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Edward
 */
@Entity
@Table(name = "student")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByIdStudent", query = "SELECT s FROM Student s WHERE s.idStudent = :idStudent")})
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idStudent")
    private Integer idStudent;
    @JoinColumn(name = "idStudent", referencedColumnName = "idUser", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<StudentHasSequence> studentHasSequenceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<TeacherHasStudent> teacherHasStudentList;

    public Student() {
    }

    public Student(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<StudentHasSequence> getStudentHasSequenceList() {
        return studentHasSequenceList;
    }

    public void setStudentHasSequenceList(List<StudentHasSequence> studentHasSequenceList) {
        this.studentHasSequenceList = studentHasSequenceList;
    }

    public List<TeacherHasStudent> getTeacherHasStudentList() {
        return teacherHasStudentList;
    }

    public void setTeacherHasStudentList(List<TeacherHasStudent> teacherHasStudentList) {
        this.teacherHasStudentList = teacherHasStudentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStudent != null ? idStudent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.idStudent == null && other.idStudent != null) || (this.idStudent != null && !this.idStudent.equals(other.idStudent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.Student[ idStudent=" + idStudent + " ]";
    }
    
}
