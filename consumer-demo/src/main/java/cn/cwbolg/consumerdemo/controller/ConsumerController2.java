package cn.cwbolg.consumerdemo.controller;

import cn.cwbolg.consumerdemo.manager.ProviderFeignManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 *
 * @description: 测试关联模式
 * @author: ChenWei
 * @create: 2020/3/14 - 9:36
 **/

@RestController
@RequestMapping("/api2")
public class ConsumerController2 {

	@GetMapping("/consumer/message1")
	String echo(){
		return "success - 1";
	}

	@GetMapping("/consumer/message2")
	String echo2(){
		return "success - 2";
	}

	@GetMapping("/consumer/message3")
	String echo3(){
		return "success - 3";
	}

	@GetMapping("/consumer/message4")
	String echo4(){
		return "success - 4";
	}
}
