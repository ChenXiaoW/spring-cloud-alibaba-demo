package cn.cwbolg.consumerdemo.controller;

import cn.cwbolg.consumerdemo.Service.ConsumerService;
import cn.cwbolg.consumerdemo.manager.ProviderFeignManager;
import cn.cwbolg.consumerdemo.manager.ProviderFeignManager2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @description: feign的使用
 * @author: ChenWei
 * @create: 2020/3/14 - 9:35
 **/

@RestController
@RequestMapping("/api6")
public class ConsumerController6 {

	@Autowired
	ProviderFeignManager providerFeignManager;

	@Autowired
	ProviderFeignManager2 providerFeignManager2;

	@GetMapping("/consumer/message1")
	String echo1(String serviceName){
		return providerFeignManager.echo("调用feign");
	}

	@GetMapping("/consumer/message2")
	String echo2(){
		return providerFeignManager2.echo2();
	}
}
