package com.citi.membership.enrollment.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class EnrollmentInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { EnrollmentConfiguration.class };
    }
  
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
}


