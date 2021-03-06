package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.vo.SequenceVo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author juanmanuelmartinezromero
 */
@ManagedBean(name = "allSequencesBean")
@ViewScoped
public class AllSequencesBean implements Serializable {

    private SequenceVo selectedSequence;
    private List<SequenceVo> sequences;
    
    /**
     * Creates a new instance of allSequencesBean
     */
    public AllSequencesBean() {
    }

    @PostConstruct
    public void init() {
        setSequences(FacadeFactory.getInstance().getSequenceFacade().getList());
    }
    
    public void deleteSequence(SequenceVo sequence) {
        FacadeFactory.getInstance().getSequenceFacade().delete(sequence.getIdSequence());
    }

    /**
     * @return the selectedSequence
     */
    public SequenceVo getSelectedSequence() {
        return selectedSequence;
    }

    /**
     * @return the sequences
     */
    public List<SequenceVo> getSequences() {
        return sequences;
    }

    /**
     * @param selectedSequence the selectedSequence to set
     */
    public void setSelectedSequence(SequenceVo selectedSequence) {
        this.selectedSequence = selectedSequence;
    }

    /**
     * @param sequences the sequences to set
     */
    public void setSequences(List<SequenceVo> sequences) {
        this.sequences = sequences;
    }
}
