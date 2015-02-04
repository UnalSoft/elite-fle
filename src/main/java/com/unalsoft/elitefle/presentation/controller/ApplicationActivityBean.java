package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.vo.SequenceVo;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Juan Manuel Martinez Romero
 */
@ManagedBean(name = "applicationActivityBean")
@ViewScoped
public class ApplicationActivityBean implements Serializable {

    private final String SUPPORTED_FORMAT = "^.*(pdf|docx?|odt|pptx?|odp)$";

    private Integer idSequence;
    private SequenceVo sequence;
    private UploadedFile file;
    private String sRootPath;

    public void preRenderView() throws Exception {
        if (getIdSequence() != null) {
            setSequence(FacadeFactory.getInstance().getSequenceFacade().find(getIdSequence()));
            //@TODO Change server location to ../applications/__internal/elite-fle-1.0-SNAPSHOT/
            sRootPath = new File("").getAbsolutePath() + File.separator + "applicationActivities";
        } else {
            throw new Exception("SequenceId is required");
        }
    }

    public String addFile() {
        if (getFile() != null) {
            if (getFile().getFileName().matches(SUPPORTED_FORMAT)) {
                try {
                    String pathFile = uploadFile(getFile().getFileName(), getFile().getInputstream());
                } catch (IOException ex) {
                    //@TODO Correct when folder doesn't exist
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Error uploading file");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    Logger.getLogger(SupportBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Type de fichier invalide");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Fichier invalide");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "failure";
        }
        return "success";
    }

    public Integer getIdSequence() {
        return idSequence;
    }

    public void setIdSequence(Integer idSequence) {
        this.idSequence = idSequence;
    }

    public SequenceVo getSequence() {
        return sequence;
    }

    public void setSequence(SequenceVo sequence) {
        this.sequence = sequence;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * Allows write an inputStream into the file
     *
     * @param fileName
     * @param inputStream
     * @return
     * @throws IOException
     */
    private String uploadFile(String fileName, InputStream inputStream) throws IOException {
        try {
            String path = sRootPath + File.separator + fileName;
            OutputStream out = new FileOutputStream(new File(path));
            int read = 0;
            byte[] bytes = new byte[(int) getFile().getSize()];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            return path;
        } catch (IOException e) {
            throw e;
        }
    }

    public String logOut(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index";
    }
}