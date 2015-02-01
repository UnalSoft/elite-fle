package com.unalsoft.elitefle.presentation.converter;

import com.unalsoft.elitefle.presentation.controller.MailBean;
import com.unalsoft.elitefle.vo.StudentVo;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Edward
 */
@FacesConverter("studentConverter")
    public class StudentConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent uic, String value) {
            if (value != null && value.trim().length() > 0) {
                try {     
                    MailBean mailBean = context.getApplication().evaluateExpressionGet(context, "#{mailBean}", MailBean.class);
                    StudentVo vo = mailBean.getStudentsMap().get(Integer.parseInt(value));
                    return vo;
                } catch (NumberFormatException e) {
                    throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
                }
            } else {
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext fc, UIComponent uic, Object object) {
            if (object != null && object instanceof StudentVo) {
                return String.valueOf(((StudentVo) object).getIdStudent());
            } else {
                return null;
            }
        }
    }
