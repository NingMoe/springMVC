package com.lph.app.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
/**
 * Spring 是基于Java 的配置API依赖于 Servlet3.0容器。确保你没有使用Servlet声明任何小于3.0
 */
//public class WebInitializer implements WebApplicationInitializer {
//
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//	AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//	ctx.register(Config.class);
//	ctx.setServletContext(servletContext);
//
//	Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//	servlet.addMapping("/");
//	servlet.setLoadOnStartup(1);
//
//    }

//}