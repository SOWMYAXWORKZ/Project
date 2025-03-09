package com.xworkz.medisales.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Slf4j
public class SpringWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        log.info("invoking in the getRootConfigClasses....");
        System.out.println("invoking in the rootConfigraction...");
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        log.info("invoking in the getServletConfigClasses");
        System.out.println("invoking in the servletConfiguration");
        return new Class[]{SpringConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        log.info("invoking in the getServletMappings");
        System.out.println("invoking in the servletMappings");
        return new String[]{"/"};
    }
}
