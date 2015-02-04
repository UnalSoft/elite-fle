package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.vo.SequenceVo;
import com.unalsoft.elitefle.vo.SupportVo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Edward
 */
@ManagedBean(name = "sequenceSupportsBean")
@SessionScoped
public class SequenceSupportsBean implements Serializable {

    private Integer idSequence;
    private List<SupportVo> supports;
    private String activeSupport;
    private boolean linguistic;
    private boolean didactic;

    public SequenceSupportsBean() {
    }

    public void preRenderView() {
        SequenceVo sequence = FacadeFactory.getInstance().getSequenceFacade().find(getIdSequence());
        List<String> supportIdList = sequence.getSupportIdList();
        supports = new ArrayList<SupportVo>();
        linguistic = false;
        didactic = false;
        for (String supportId : supportIdList) {
            SupportVo support = FacadeFactory.getInstance().getSupportFacade().find(supportId);
            supports.add(support);
            if (support.getType().equals("Linguistique")) {
                linguistic = true;
            } else if (support.getType().equals("Didactique")) {
                didactic = true;
            }
        }
        if (activeSupport == null) {

            String urlSupport = supports.get(0).getUrlSupport();
//        urlSupport = urlSupport.replaceAll("\\\\", "/");
            //String[] contextUrl = urlSupport.split(new File("").getAbsolutePath());
            activeSupport = urlSupport;
        }
    }

    public String changeDoc(String urlSupport) {
        activeSupport = urlSupport;
        return "/sequence/activity/supports?idSequence=" + idSequence + "&faces-redirect=false";
    }

    public StreamedContent getStream() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            File file = new File(getActiveSupport());
            // So, browser is requesting the media. Return a real StreamedContent with the media bytes.
            FileInputStream fis = new FileInputStream(file);
            byte[] bFile = new byte[(int) file.length()];
            fis.read(bFile);
            fis.close();
            return new DefaultStreamedContent(new ByteArrayInputStream(bFile), "application/pdf");
        }
    }

    public String next() {
        activeSupport = null;
        return "systematizationActivity?idSequence=" + idSequence + "&faces-redirect=true";
    }

    public Integer getIdSequence() {
        return idSequence;
    }

    public void setIdSequence(Integer idSequence) {
        this.idSequence = idSequence;
    }

    public List<SupportVo> getSupports() {
        return supports;
    }

    public void setSupports(List<SupportVo> supports) {
        this.supports = supports;
    }

    public String getActiveSupport() {
        return activeSupport;
    }

    public void setActiveSupport(String activeSupport) {
        this.activeSupport = activeSupport;
    }

    public boolean isLinguistic() {
        return linguistic;
    }

    public void setLinguistic(boolean linguistic) {
        this.linguistic = linguistic;
    }

    public boolean isDidactic() {
        return didactic;
    }

    public void setDidactic(boolean didactic) {
        this.didactic = didactic;
    }

}
