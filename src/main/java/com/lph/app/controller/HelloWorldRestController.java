package com.lph.app.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lph.app.common.Message;

@RestController
@RequestMapping("/rest/")

/*
 * @RestController 注解，这标志着这个类作为控制器，每一个方法返回域对象/pojo代替一个视图。
 * 这意味着我们不再使用视图解析器，我们不再直接发送响应的HTML，我们只发送的域对象转换成格式。
 */
/**
 * json+xml服务 视图解析部分不需要
 * @author Administrator
 *
 */
public class HelloWorldRestController {

    @RequestMapping("/hello/{player}")
    //@PathVariable表示参数将被绑定到变量 URI 模板。
    public Message message(@PathVariable String player) {

	Message msg = new Message(player, "Hello " + player);
	return msg;
    }

}