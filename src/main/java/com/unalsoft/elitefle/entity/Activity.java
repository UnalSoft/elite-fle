/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.entity;

import com.unalsoft.elitefle.vo.ActivityVo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "activity")
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findByIdActivity", query = "SELECT a FROM Activity a WHERE a.idActivity = :idActivity"),
    @NamedQuery(name = "Activity.findByNameText", query = "SELECT a FROM Activity a WHERE a.nameText = :nameText"),
    @NamedQuery(name = "Activity.findByUrlText", query = "SELECT a FROM Activity a WHERE a.urlText = :urlText"),
    @NamedQuery(name = "Activity.findByType", query = "SELECT a FROM Activity a WHERE a.type = :type")})
public class Activity implements Serializable, IEntity<ActivityVo> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActivity")
    private Integer idActivity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nameText")
    private String nameText;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "urlText")
    private String urlText;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "systematizationActivity")
    private List<Sequence> sequenceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spottingActivity")
    private List<Sequence> sequenceList1;

    public Activity() {
    }

    public Activity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public Activity(Integer idActivity, String nameText, String urlText, String type) {
        this.idActivity = idActivity;
        this.nameText = nameText;
        this.urlText = urlText;
        this.type = type;
    }

    public Integer getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public String getUrlText() {
        return urlText;
    }

    public void setUrlText(String urlText) {
        this.urlText = urlText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Sequence> getSequenceList() {
        return sequenceList;
    }

    public void setSequenceList(List<Sequence> sequenceList) {
        this.sequenceList = sequenceList;
    }

    public List<Sequence> getSequenceList1() {
        return sequenceList1;
    }

    public void setSequenceList1(List<Sequence> sequenceList1) {
        this.sequenceList1 = sequenceList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivity != null ? idActivity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.idActivity == null && other.idActivity != null) || (this.idActivity != null && !this.idActivity.equals(other.idActivity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.Activity[ idActivity=" + idActivity + " ]";
    }

    @Override
    public ActivityVo toVo() {
        ActivityVo vo = new ActivityVo();
        vo.setId(getIdActivity());
        vo.setTextName(getNameText());
        vo.setType(getType());
        vo.setUrl(getUrlText());
        return vo;
    }
    
}
