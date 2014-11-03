/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.entity;

import com.unalsoft.elitefle.vo.TeacherVo;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "teacher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT a FROM Teacher a"),
    @NamedQuery(name = "Teacher.findByIdTeacher", query = "SELECT a FROM Teacher a WHERE a.idTeacher = :idTeacher"),
    @NamedQuery(name = "Teacher.findByUsername", query = "SELECT a FROM Teacher a WHERE a.username = :username"),
    @NamedQuery(name = "Teacher.findByPassword", query = "SELECT a FROM Teacher a WHERE a.password = :password")})
public class Teacher implements Serializable, IEntity<TeacherVo> {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTeacher")
    private Integer idTeacher;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "Person_idPerson", referencedColumnName = "idPerson")
    @ManyToOne(optional = false)
    private Person personidPerson;

    public Teacher() {
    }

    public Teacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public Teacher(Integer idTeacher, String username, String password) {
        this.idTeacher = idTeacher;
        this.username = username;
        this.password = password;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPersonidPerson() {
        return personidPerson;
    }

    public void setPersonidPerson(Person personidPerson) {
        this.personidPerson = personidPerson;
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
    
    @Override
    public TeacherVo toVo(){
        TeacherVo teacherVo = new TeacherVo();
        teacherVo.setIdTeacher(getIdTeacher());
        teacherVo.setPassword(getPassword());
        teacherVo.setUsername(getUsername());
        teacherVo.setPersonidPerson(getPersonidPerson().getIdPerson());
        return teacherVo;
    }
       
}
