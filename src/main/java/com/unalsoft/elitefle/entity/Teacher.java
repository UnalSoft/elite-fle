package com.unalsoft.elitefle.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Edward
 */
@Entity
@Table(name = "teacher")
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t"),
    @NamedQuery(name = "Teacher.findByIdTeacher", query = "SELECT t FROM Teacher t WHERE t.idTeacher = :idTeacher")})
public class Teacher extends User implements Serializable/*, IEntity<TeacherVo>*/ {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTeacher")
    private Integer idTeacher;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Support> supportList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAuthor")
    private List<Sequence> sequenceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<TeacherHasStudent> teacherHasStudentList;

    public Teacher() {
    }

    public Teacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public List<Support> getSupportList() {
        return supportList;
    }

    public void setSupportList(List<Support> supportList) {
        this.supportList = supportList;
    }

    public List<Sequence> getSequenceList() {
        return sequenceList;
    }

    public void setSequenceList(List<Sequence> sequenceList) {
        this.sequenceList = sequenceList;
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
        hash += (idTeacher != null ? idTeacher.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        return !((this.idTeacher == null && other.idTeacher != null) || (this.idTeacher != null && !this.idTeacher.equals(other.idTeacher)));
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.Teacher[ idTeacher=" + idTeacher + " ]";
    }

//    @Override
//    public TeacherVo toVo() {
//TODO Uncomment when heritage in Vos
//        TeacherVo vo = new TeacherVo();
//        vo.setIdTeacher(getIdTeacher());
//        vo.setPersonidPerson(getUser().getIdUser());
//        return vo;
//    }
    
}
