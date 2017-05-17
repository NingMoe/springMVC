package com.lph.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lph.app.entity.Student;

@Controller("homeController")
/*
 * @Controller表明这个类是一个控制器在处理具有模式映射的@RequestMapping请求。 这里使用 ‘/’,
 * 它被作为默认的控制器。方法newRegistration是相当简单的，注解为@ RequestMethod.
 * GET服务默认是GET请求，使用模型对象，以服务为形式的数据，并呈现包含空白表单的网页。
 */
/**
 * Web应用程序显示使用JSR303来验证Spring表单标签，表单验证，访问静态资源
 * 
 * @author Administrator
 *
 */
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
	Student student = new Student();
	model.addAttribute("student", student);
	return "enroll";
    }

    @RequestMapping(value = "/userManagement/", method = RequestMethod.GET)
    public String userManagement() {
	return "userManagement";
    }

    @RequestMapping(method = RequestMethod.POST)
    /*
     * @Valid要求spring来验证相关的对象(学生)。 BindingResult包含此验证，并可能在此验证过程中发生(产生)任何错误的结果。
     * 请注意，BindingResult一定要在之后立即生效对象，否则spring将无法验证并且将一个异常抛出
     * 注意，在校验失败后，默认/广义错误消息显示在屏幕上这可能不是所期望的。 相反，可以重写此行为提供具体到每个字段中国际化消息。
     * 为了做到这一点，我们需要配置 MessageSource 在应用程序配置类， 并提供包含我们下一步将配置实际的信息属性文件
     */
    public String saveRegistration(@Valid Student student, BindingResult result, ModelMap model) {
	if (result.hasErrors()) {
	    return "enroll";
	}
	model.addAttribute("success", "Dear " + student.getFirstName() + " , your Registration completed successfully");
	return "success";
    }

    @ModelAttribute("sections")
    public List<String> initializeSections() {

	List<String> sections = new ArrayList<String>();
	sections.add("Graduate");
	sections.add("Post Graduate");
	sections.add("Research");
	return sections;
    }

    /*
     * Method used to populate the country list in view. Note that here you can
     * call external systems to provide real data.
     */
    @ModelAttribute("countries")
    public List<String> initializeCountries() {

	List<String> countries = new ArrayList<String>();
	countries.add("USA");
	countries.add("CHINA");
	countries.add("FRANCE");
	countries.add("GERMANY");
	countries.add("ITALY");
	countries.add("OTHER");
	return countries;
    }

    /*
     * Method used to populate the subjects list in view. Note that here you can
     * call external systems to provide real data.
     */
    @ModelAttribute("subjects")
    public List<String> initializeSubjects() {

	List<String> subjects = new ArrayList<String>();
	subjects.add("Physics");
	subjects.add("Chemistry");
	subjects.add("Life Science");
	subjects.add("Political Science");
	subjects.add("Computer Science");
	subjects.add("Mathmatics");
	return subjects;
    }

}
