/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Edward
 */
@Entity
@Table(name = "student_has_sequence")
@NamedQueries({
    @NamedQuery(name = "StudentHasSequence.findAll", query = "SELECT s FROM StudentHasSequence s"),
    @NamedQuery(name = "StudentHasSequence.findByIdSequence", query = "SELECT s FROM StudentHasSequence s WHERE s.studentHasSequencePK.idSequence = :idSequence"),
    @NamedQuery(name = "StudentHasSequence.findByIdUser", query = "SELECT s FROM StudentHasSequence s WHERE s.studentHasSequencePK.idUser = :idUser"),
    @NamedQuery(name = "StudentHasSequence.findByUrlApplicationActivity", query = "SELECT s FROM StudentHasSequence s WHERE s.urlApplicationActivity = :urlApplicationActivity")})
public class StudentHasSequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentHasSequencePK studentHasSequencePK;
    @Size(max = 45)
    @Column(name = "urlApplicationActivity")
    private String urlApplicationActivity;
    @JoinColumn(name = "idUser", referencedColumnName = "idStudent", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "idSequence", referencedColumnName = "idSequence", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sequence sequence;

    public StudentHasSequence() {
    }

    public StudentHasSequence(StudentHasSequencePK studentHasSequencePK) {
        this.studentHasSequencePK = studentHasSequencePK;
    }

    public StudentHasSequence(int idSequence, int idUser) {
        this.studentHasSequencePK = new StudentHasSequencePK(idSequence, idUser);
    }

    public StudentHasSequencePK getStudentHasSequencePK() {
        return studentHasSequencePK;
    }

    public void setStudentHasSequencePK(StudentHasSequencePK studentHasSequencePK) {
        this.studentHasSequencePK = studentHasSequencePK;
    }

    public String getUrlApplicationActivity() {
        return urlApplicationActivity;
    }

    public void setUrlApplicationActivity(String urlApplicationActivity) {
        this.urlApplicationActivity = urlApplicationActivity;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentHasSequencePK != null ? studentHasSequencePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentHasSequence)) {
            return false;
        }
        StudentHasSequence other = (StudentHasSequence) object;
        if ((this.studentHasSequencePK == null && other.studentHasSequencePK != null) || (this.studentHasSequencePK != null && !this.studentHasSequencePK.equals(other.studentHasSequencePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.StudentHasSequence[ studentHasSequencePK=" + studentHasSequencePK + " ]";
    }
    
}
