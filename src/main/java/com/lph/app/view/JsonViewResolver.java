package com.lph.app.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 这个视图解析器是使用 Spring MappingJackson2JsonView 为了将 POJO 对象转换成 JSON 视图。
 * @author Administrator
 *
 */
public class JsonViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
	MappingJackson2JsonView view = new MappingJackson2JsonView();
	view.setPrettyPrint(true);
	return view;
    }

}