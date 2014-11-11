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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Edward
 */
@Entity
@Table(name = "sequence")
@NamedQueries({
    @NamedQuery(name = "Sequence.findAll", query = "SELECT s FROM Sequence s"),
    @NamedQuery(name = "Sequence.findByIdSequence", query = "SELECT s FROM Sequence s WHERE s.idSequence = :idSequence"),
    @NamedQuery(name = "Sequence.findByNameSequence", query = "SELECT s FROM Sequence s WHERE s.nameSequence = :nameSequence"),
    @NamedQuery(name = "Sequence.findByNotion", query = "SELECT s FROM Sequence s WHERE s.notion = :notion"),
    @NamedQuery(name = "Sequence.findBySubNotion", query = "SELECT s FROM Sequence s WHERE s.subNotion = :subNotion"),
    @NamedQuery(name = "Sequence.findByLevel", query = "SELECT s FROM Sequence s WHERE s.level = :level"),
    @NamedQuery(name = "Sequence.findBySupports", query = "SELECT s FROM Sequence s WHERE s.supports = :supports"),
    @NamedQuery(name = "Sequence.findByApplicationActivity", query = "SELECT s FROM Sequence s WHERE s.applicationActivity = :applicationActivity"),
    @NamedQuery(name = "Sequence.findByUrlExplication", query = "SELECT s FROM Sequence s WHERE s.urlExplication = :urlExplication")})
public class Sequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSequence")
    private Integer idSequence;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nameSequence")
    private String nameSequence;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "notion")
    private String notion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "subNotion")
    private String subNotion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "level")
    private String level;
    @Basic(optional = false)
    @NotNull
    @Column(name = "supports")
    private short supports;
    @Size(max = 500)
    @Column(name = "applicationActivity")
    private String applicationActivity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "urlExplication")
    private String urlExplication;
    @JoinTable(name = "sequence_has_support", joinColumns = {
        @JoinColumn(name = "Sequence_idSequence", referencedColumnName = "idSequence")}, inverseJoinColumns = {
        @JoinColumn(name = "Support_urlSupport", referencedColumnName = "urlSupport")})
    @ManyToMany
    private List<Support> supportList;
    @JoinColumn(name = "idAuthor", referencedColumnName = "idTeacher")
    @ManyToOne(optional = false)
    private Teacher idAuthor;
    @JoinColumn(name = "systematizationActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity systematizationActivity;
    @JoinColumn(name = "spottingActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity spottingActivity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sequence")
    private List<StudentHasSequence> studentHasSequenceList;

    public Sequence() {
    }

    public Sequence(Integer idSequence) {
        this.idSequence = idSequence;
    }

    public Sequence(Integer idSequence, String nameSequence, String notion, String subNotion, String level, short supports, String urlExplication) {
        this.idSequence = idSequence;
        this.nameSequence = nameSequence;
        this.notion = notion;
        this.subNotion = subNotion;
        this.level = level;
        this.supports = supports;
        this.urlExplication = urlExplication;
    }

    public Integer getIdSequence() {
        return idSequence;
    }

    public void setIdSequence(Integer idSequence) {
        this.idSequence = idSequence;
    }

    public String getNameSequence() {
        return nameSequence;
    }

    public void setNameSequence(String nameSequence) {
        this.nameSequence = nameSequence;
    }

    public String getNotion() {
        return notion;
    }

    public void setNotion(String notion) {
        this.notion = notion;
    }

    public String getSubNotion() {
        return subNotion;
    }

    public void setSubNotion(String subNotion) {
        this.subNotion = subNotion;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public short getSupports() {
        return supports;
    }

    public void setSupports(short supports) {
        this.supports = supports;
    }

    public String getApplicationActivity() {
        return applicationActivity;
    }

    public void setApplicationActivity(String applicationActivity) {
        this.applicationActivity = applicationActivity;
    }

    public String getUrlExplication() {
        return urlExplication;
    }

    public void setUrlExplication(String urlExplication) {
        this.urlExplication = urlExplication;
    }

    public List<Support> getSupportList() {
        return supportList;
    }

    public void setSupportList(List<Support> supportList) {
        this.supportList = supportList;
    }

    public Teacher getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Teacher idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Activity getSystematizationActivity() {
        return systematizationActivity;
    }

    public void setSystematizationActivity(Activity systematizationActivity) {
        this.systematizationActivity = systematizationActivity;
    }

    public Activity getSpottingActivity() {
        return spottingActivity;
    }

    public void setSpottingActivity(Activity spottingActivity) {
        this.spottingActivity = spottingActivity;
    }

    public List<StudentHasSequence> getStudentHasSequenceList() {
        return studentHasSequenceList;
    }

    public void setStudentHasSequenceList(List<StudentHasSequence> studentHasSequenceList) {
        this.studentHasSequenceList = studentHasSequenceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSequence != null ? idSequence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sequence)) {
            return false;
        }
        Sequence other = (Sequence) object;
        if ((this.idSequence == null && other.idSequence != null) || (this.idSequence != null && !this.idSequence.equals(other.idSequence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.Sequence[ idSequence=" + idSequence + " ]";
    }
    
}
