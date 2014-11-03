/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.entity;

import com.unalsoft.elitefle.vo.PersonVo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByIdPerson", query = "SELECT p FROM Person p WHERE p.idPerson = :idPerson"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByLastanames", query = "SELECT p FROM Person p WHERE p.lastanames = :lastanames"),
    @NamedQuery(name = "Person.findByMail", query = "SELECT p FROM Person p WHERE p.mail = :mail")})
public class Person implements Serializable ,IEntity<PersonVo> {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPerson")
    private Integer idPerson;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "lastanames")
    private String lastanames;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;
    @Size(max = 30)
    @Column(name = "mail")
    private String mail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personidPerson")
    private List<Teacher> teacherList;

    public Person() {
    }

    public Person(Integer idPerson) {
        this.idPerson = idPerson;
        //TODO Change to the correct role
        this.roleEnum=RoleEnum.Teacher;
    }

    public Person(Integer idPerson, String name, String lastanames) {
        this.idPerson = idPerson;
        this.name = name;
        this.lastanames = lastanames;
        //TODO Change to the correct role
        this.roleEnum=RoleEnum.Teacher;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastanames() {
        return lastanames;
    }

    public void setLastanames(String lastanames) {
        this.lastanames = lastanames;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @XmlTransient
    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return !((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson)));
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.Person[ idPerson=" + idPerson + " ]";
    }
    
    @Override
    public PersonVo toVo(){
        PersonVo personVo = new PersonVo();
        personVo.setIdPerson(getIdPerson());
        personVo.setName(getName());
        personVo.setLastName(getLastanames());
        personVo.setMail(getMail());
        personVo.setLastName(getLastanames());
        return personVo;
    }
    
}
