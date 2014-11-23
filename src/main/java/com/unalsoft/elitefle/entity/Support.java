package com.unalsoft.elitefle.entity;

import com.unalsoft.elitefle.vo.SupportVo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "support")
@NamedQueries({
    @NamedQuery(name = "Support.findAll", query = "SELECT s FROM Support s"),
    @NamedQuery(name = "Support.findByUrlSupport", query = "SELECT s FROM Support s WHERE s.urlSupport = :urlSupport"),
    @NamedQuery(name = "Support.findByTitle", query = "SELECT s FROM Support s WHERE s.title = :title"),
    @NamedQuery(name = "Support.findByType", query = "SELECT s FROM Support s WHERE s.type = :type"),
    @NamedQuery(name = "Support.findByNotion", query = "SELECT s FROM Support s WHERE s.notion = :notion"),
    @NamedQuery(name = "Support.findBySubNotion", query = "SELECT s FROM Support s WHERE s.subNotion = :subNotion")})
public class Support implements Serializable, IEntity<SupportVo> {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "urlSupport")
    private String urlSupport;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
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
    @ManyToMany(mappedBy = "supportList")
    private List<Sequence> sequenceList;
    @JoinColumn(name = "idAuthor", referencedColumnName = "idTeacher")
    @ManyToOne(optional = false)
    private Teacher author;

    public Support() {
    }

    public Support(String urlSupport) {
        this.urlSupport = urlSupport;
    }

    public Support(String urlSupport, String title, String type, String notion, String subNotion) {
        this.urlSupport = urlSupport;
        this.title = title;
        this.type = type;
        this.notion = notion;
        this.subNotion = subNotion;
    }

    public String getUrlSupport() {
        return urlSupport;
    }

    public void setUrlSupport(String urlSupport) {
        this.urlSupport = urlSupport;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Sequence> getSequenceList() {
        return sequenceList;
    }

    public void setSequenceList(List<Sequence> sequenceList) {
        this.sequenceList = sequenceList;
    }

    public Teacher getAuthor() {
        return author;
    }

    public void setAuthor(Teacher author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (urlSupport != null ? urlSupport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Support)) {
            return false;
        }
        Support other = (Support) object;
        if ((this.urlSupport == null && other.urlSupport != null) || (this.urlSupport != null && !this.urlSupport.equals(other.urlSupport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.Support[ urlSupport=" + urlSupport + " ]";
    }

    @Override
    public SupportVo toVo() {
        SupportVo vo = new SupportVo();
        
        vo.setUrlSupport(getUrlSupport());
        vo.setTitle(getTitle());
        vo.setType(getType());
        vo.setNotion(getNotion());
        vo.setSubNotion(getSubNotion());
        if (getAuthor() != null) {
            vo.setIdAuthor(getAuthor().getIdTeacher());
            vo.setAuthorName(getAuthor().getName());
        }
        List<Integer> sequenceIdList = new ArrayList<Integer>();
        for (Sequence seq : getSequenceList()) {
            sequenceIdList.add(seq.getIdSequence());
        }
        vo.setSequenceIdList(sequenceIdList);
                
        return vo;
    }
    
}
