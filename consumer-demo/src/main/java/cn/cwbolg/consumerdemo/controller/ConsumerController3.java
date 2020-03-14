package cn.cwbolg.consumerdemo.controller;

import cn.cwbolg.consumerdemo.Service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 *
 * @description: 测试链路流控模式
 * @author: ChenWei
 * @create: 2020/3/14 - 9:35
 **/

@RestController
@RequestMapping("/api3")
public class ConsumerController3 {

	@Autowired
	ConsumerService consumerService;

	@GetMapping("/consumer/message1")
	String echo1(){
		return consumerService.echo("/consumer/message1");
	}

	@GetMapping("/consumer/message2")
	String echo2(){
		return consumerService.echo("/consumer/message2");
	}

}
