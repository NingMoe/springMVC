package com.lph.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lph.app.view.Pizza;


@Controller
/**
 * 基于注解Web应用程序实例，支持多种输出格式（XML，JSON，XLS，PDF，HTML）使用不同的视图解析器相同的数据。
 * @author Administrator
 *
 */
@RequestMapping("/app")
public class AppController {

    @RequestMapping(value = "/pizzavalley/{pizzaName}", method = RequestMethod.GET)
    public String getPizza(@PathVariable String pizzaName, ModelMap model) {

	Pizza pizza = new Pizza(pizzaName);
	model.addAttribute("pizza", pizza);

	return "pizza";

    }

}
