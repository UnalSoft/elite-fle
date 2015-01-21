package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.businesslogic.facade.PersistException;
import com.unalsoft.elitefle.entity.Notion;
import com.unalsoft.elitefle.entity.Notion.SubNotion;
import com.unalsoft.elitefle.vo.SupportVo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Edward
 */
@ManagedBean(name = "supportBean")
@SessionScoped
public class SupportBean implements Serializable {

    private final String SUPORTED_FORMAT = "^.*(pdf|docx?|odt|pptx?|odp)$";

    private List<SupportVo> supportList;
    private List<SupportVo> filteredSupports;
    private List<SupportVo> selectedSupports;

    private List<Notion> notions;
    private List<SubNotion> subNotions;

    private UploadedFile file;
    private String title;
    private String type;
    private Notion notion;
    private SubNotion subNotion;
    @ManagedProperty(value = "#{teacherBean}")
    private TeacherBean user;
    String sRootPath;

    @PostConstruct
    public void init() {
        supportList = FacadeFactory.getInstance().getSupportFacade().getList();
        selectedSupports=new ArrayList<SupportVo>();
        sRootPath = new File("").getAbsolutePath() + File.separator + "support";
        notions = Arrays.asList(Notion.values());
        subNotions = getNotions().get(0).getSubNotions();
    }

    public String addSupport() {
        SupportVo vo = new SupportVo();
        vo.setTitle(getTitle());
        vo.setType(getType());
        vo.setNotion(getNotion().getDescription());
        vo.setSubNotion(getSubNotion().getDescription());
        vo.setIdAuthor(getUser().getIdTeacher());
        if (getFile() != null) {
            if (getFile().getFileName().matches(SUPORTED_FORMAT)) {
                try {
                    String pathFile = uploadFile(getFile().getFileName(), getFile().getInputstream());
                    vo.setUrlSupport(pathFile);
                    FacadeFactory.getInstance().getSupportFacade().persist(vo);
                    init();
                } catch (IOException ex) {
                    //@TODO Correct when folder doesn't exist
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Error uploading file");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    Logger.getLogger(SupportBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PersistException ex) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Database Erreur: " + ex.getMessage());
                    FacesContext.getCurrentInstance().addMessage(null, message);
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

    public void onNotionChange() {
        setSubNotions(getNotion().getSubNotions());
    }

    public String selectSupports() {
        return "success";
    }

    public List<SupportVo> getSupportList() {
        return supportList;
    }

    public List<SupportVo> getFilteredSupports() {
        return filteredSupports;
    }

    public void setFilteredSupports(List<SupportVo> filteredSupports) {
        this.filteredSupports = filteredSupports;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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

    public TeacherBean getUser() {
        return user;
    }

    public void setUser(TeacherBean user) {
        this.user = user;
    }

    public List<SupportVo> getSelectedSupports() {
        return selectedSupports;
    }

    public void setSelectedSupports(List<SupportVo> selectedSupports) {
        this.selectedSupports = selectedSupports;
    }

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

    public List<Notion> getNotions() {
        return notions;
    }

    public void setNotions(List<Notion> notions) {
        this.notions = notions;
    }

    public List<SubNotion> getSubNotions() {
        return subNotions;
    }

    public void setSubNotions(List<SubNotion> subNotions) {
        this.subNotions = subNotions;
    }

    public Notion getNotion() {
        return notion;
    }

    public void setNotion(Notion notion) {
        this.notion = notion;
    }

    public SubNotion getSubNotion() {
        return subNotion;
    }

    public void setSubNotion(SubNotion subNotion) {
        this.subNotion = subNotion;
    }

}
