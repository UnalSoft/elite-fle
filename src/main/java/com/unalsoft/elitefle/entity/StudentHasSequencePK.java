/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unalsoft.elitefle.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Edward
 */
@Embeddable
public class StudentHasSequencePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSequence")
    private int idSequence;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private int idUser;

    public StudentHasSequencePK() {
    }

    public StudentHasSequencePK(int idSequence, int idUser) {
        this.idSequence = idSequence;
        this.idUser = idUser;
    }

    public int getIdSequence() {
        return idSequence;
    }

    public void setIdSequence(int idSequence) {
        this.idSequence = idSequence;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSequence;
        hash += (int) idUser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentHasSequencePK)) {
            return false;
        }
        StudentHasSequencePK other = (StudentHasSequencePK) object;
        if (this.idSequence != other.idSequence) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unalsoft.elitefle.entity.StudentHasSequencePK[ idSequence=" + idSequence + ", idUser=" + idUser + " ]";
    }
    
}
