/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unalsoft.elitefle.entity;

import com.unalsoft.elitefle.vo.SequenceVo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Sequence.findByExplication", query = "SELECT s FROM Sequence s WHERE s.explication = :explication")})
public class Sequence implements Serializable, IEntity<SequenceVo> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
    @Size(max = 2500)
    @Column(name = "applicationActivity")
    private String applicationActivity;
    @Basic(optional = false)
    @NotNull
    @Size(max = 2500)
    @Column(name = "explication")
    private String explication;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date creationDate;
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

    public Sequence(Integer idSequence, String nameSequence, String notion, String subNotion, String level, short supports, String explication) {
        this.idSequence = idSequence;
        this.nameSequence = nameSequence;
        this.notion = notion;
        this.subNotion = subNotion;
        this.level = level;
        this.supports = supports;
        this.explication = explication;
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

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    @Override
    public SequenceVo toVo() {
        SequenceVo vo = new SequenceVo();
        vo.setIdSequence(idSequence);
        vo.setNameSequence(nameSequence);
        vo.setNotion(notion);
        vo.setSubNotion(subNotion);
        vo.setLevel(level);
        vo.setSupports(supports == 1);
        vo.setApplicationActivity(applicationActivity);
        vo.setExplication(explication);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        vo.setCreationDate(format.format(getCreationDate()));
        //SupportList,
        List<String> supportIdList = new ArrayList<String>();
        for (Support support : getSupportList()) {
            supportIdList.add(support.getUrlSupport());
        }
        vo.setSupportIdList(supportIdList);
        //Author
        if (getIdAuthor() != null) {
            vo.setIdAuthor(idAuthor.getIdTeacher());
            vo.setAuthor(getIdAuthor().getName());
        }
        //Spotting Activity
        //Systematisation Activity
        vo.setIdSpottingActivity(spottingActivity.getIdActivity());
        vo.setIdSystematizationActivity(systematizationActivity.getIdActivity());

        //StudentHasSequenceList
        int[][] studentHasSequencePKs = new int[studentHasSequenceList.size()][2];
        for (int i = 0; i < studentHasSequencePKs.length; i++) {
            studentHasSequencePKs[i][0] = studentHasSequenceList.get(i).getSequence().getIdSequence();
            studentHasSequencePKs[i][1] = studentHasSequenceList.get(i).getStudent().getIdStudent();
        }
        vo.setStudentHasSequencePKs(studentHasSequencePKs);

        return vo;
    }
}
