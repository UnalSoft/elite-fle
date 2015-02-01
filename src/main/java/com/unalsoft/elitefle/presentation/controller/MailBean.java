package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.businesslogic.service.ServiceFactory;
import com.unalsoft.elitefle.vo.StudentVo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Edward
 */
@ManagedBean(name = "mailBean")
@ViewScoped
public class MailBean {

    private String idSequence;
    private String sequenceName;

    private DualListModel<StudentVo> students;
    private Map<Integer, StudentVo> studentsMap;

//    private String subject;
//    private String message;
    public MailBean() {
    }

    public void preRenderView() throws Exception {
        if (getIdSequence() != null && getSequenceName() != null) {
            List<StudentVo> sourcestudents = FacadeFactory.getInstance().getStudentFacade().getList();
            List<StudentVo> selectedStudents = new ArrayList<StudentVo>();
            students = new DualListModel<StudentVo>(sourcestudents, selectedStudents);
            studentsMap = new HashMap<Integer, StudentVo>();
            for (StudentVo studentVo : sourcestudents) {
                studentsMap.put(studentVo.getIdStudent(), studentVo);
            }
        } else {
            throw new Exception("SequenceId and SequenceName are required");
        }
    }

    public String sendMail() throws Exception {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();        
        String fullURL = request.getRequestURL().toString();
        String[] split = fullURL.split("/teacher/sendSequence.xhtml");
        String url = split[0] + "/sequence/activity/spottingActivity.xhtml?idSequence=" + idSequence;
        HashMap<String, String> recipients = new HashMap<String, String>();
        for (StudentVo student : students.getTarget()) {
            recipients.put(student.getMail(), student.getName());
        }
        ServiceFactory.getInstance().getMailService().sendEmail(recipients, sequenceName, url);
        return "/teacher/teacherPage?faces-redirect=true";
    }

    public String getIdSequence() {
        return idSequence;
    }

    public void setIdSequence(String idSequence) {
        this.idSequence = idSequence;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public DualListModel<StudentVo> getStudents() {
        return students;
    }

    public void setStudents(DualListModel<StudentVo> students) {
        this.students = students;
    }  

    public Map<Integer, StudentVo> getStudentsMap() {
        return studentsMap;
    }

    public void setStudentsMap(Map<Integer, StudentVo> studentsMap) {
        this.studentsMap = studentsMap;
    }

}
