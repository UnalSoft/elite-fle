package com.unalsoft.elitefle.presentation.filter;

import com.unalsoft.elitefle.presentation.controller.TeacherBean;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter checks if LoginBean has loginIn property set to true. If it is not set
 * then request is being redirected to the index.xhml page.
 *
 * @author Edward
 *
 */
public class LoginFilter implements Filter {

    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletrequest = (HttpServletRequest) request;
        // Get the loginBean from session attribute
        TeacherBean userBean = (TeacherBean) httpServletrequest.getSession().getAttribute("teacherBean");

        String contextPath = httpServletrequest.getContextPath();
        String targetPage = httpServletrequest.getRequestURI();
        String[] split = targetPage.split(".xhtml");
        String[] split2 = split[0].split("/elite-fle");
        targetPage = split2[1];

        if (userBean == null || !userBean.isLoggedIn()) {            

            String redirect = contextPath + "/index.xhtml";

            redirect = redirect.concat("?target=" + targetPage + "?faces-redirect=true");

            Enumeration<String> parameterNames = httpServletrequest.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String parameter = parameterNames.nextElement();
                redirect = redirect.concat("_" + parameter + "=" + httpServletrequest.getParameter(parameter));
            }

            ((HttpServletResponse) response).sendRedirect(redirect);
        } else if (userBean.isStudent() && targetPage.startsWith("/teacher")) {
            ((HttpServletResponse) response).sendRedirect(contextPath + "/index.xhtml");
        }

        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }

    @Override
    public void destroy() {
        // Nothing to do here!
    }

}
