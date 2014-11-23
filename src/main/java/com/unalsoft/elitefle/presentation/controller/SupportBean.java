package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.businesslogic.facade.PersistException;
import com.unalsoft.elitefle.vo.SupportVo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
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
@ManagedBean(name="supportBean")
@SessionScoped
public class SupportBean implements Serializable {
    
    private List <SupportVo> supportList;
    private List <SupportVo> filteredSupports;
    private List<SupportVo> selectedSupports;
    
    private UploadedFile file;
    private String title;
    private String type;
    private String notion;
    private String subNotion;
    @ManagedProperty(value = "#{teacherBean}")
    private TeacherBean user;
    String sRootPath;
    
    @PostConstruct
    public void init() {        
        supportList = FacadeFactory.getInstance().getSupportFacade().getList();
        sRootPath = new File("").getAbsolutePath() + File.separator + "support";
    }
    
    public String addSupport() {
        SupportVo vo = new SupportVo();
        vo.setTitle(getTitle());
        vo.setType(getType());
        vo.setNotion(getNotion());
        vo.setSubNotion(getSubNotion());
        vo.setIdAuthor(getUser().getIdTeacher());
        if (getFile() != null) {
            try {
                String pathFile = uploadFile(getFile().getFileName(), getFile().getInputstream());
                vo.setUrlSupport(pathFile);
                FacadeFactory.getInstance().getSupportFacade().persist(vo);
                init();
            } catch (IOException ex) {
                FacesMessage message = new FacesMessage("Wrong", "Error uploading file");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (PersistException ex) {
                FacesMessage message = new FacesMessage("Wrong", "Database Error: " + ex.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }           
        } else {
            //TODO: Errors?
            FacesMessage message = new FacesMessage("Wrong", "Invalid file");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "failure";
        }
        return "success";
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
            byte[] bytes = new byte[(int)getFile().getSize()];

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
    
}
