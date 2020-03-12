package cn.cwbolg.consumerdemo.controller;

import cn.cwbolg.consumerdemo.manager.ProviderFeignManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ConsumerController {

	@Autowired
	ProviderFeignManager providerFeignManager;

	@GetMapping("/consumer/{message}")
	String echo(@PathVariable(value = "message")String message){
		return providerFeignManager.echo(message);
	}
}
