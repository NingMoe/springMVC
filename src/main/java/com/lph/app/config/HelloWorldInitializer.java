package com.lph.app.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.lph.app.filter.CORSFilter;

/**
 * Spring 是基于Java 的配置API依赖于 Servlet3.0容器。确保你没有使用Servlet声明任何小于3.0
 * 
 * @author Administrator
 *
 */
public class HelloWorldInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class[] { Config.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return null;
    }

    @Override
    protected String[] getServletMappings() {
	return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
	Filter[] singleton = { new CORSFilter() };
	return singleton;
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
	registration.setMultipartConfig(getMultipartConfigElement());
    }

    /**
     * 请注意，我们如何才能注册所需的 MultiPartConfigElement 
     * 到 DispatcherServlet 的重写函数 customizeRegistration。
     * @return
     */
    private MultipartConfigElement getMultipartConfigElement() {
	MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE,
		MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
	return multipartConfigElement;
    }

    private static final String LOCATION = "D:/mytemp/"; // Temporary location
						       // where files will be
						       // stored

    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
						       // Beyond that size
						       // spring will throw
						       // exception.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total
							   // request size
							   // containing Multi
							   // part.

    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after
						      // which files will be
						      // written to disk

}
