package cn.cwbolg.consumerdemo.controller;

import cn.cwbolg.consumerdemo.Service.ConsumerService;
import cn.cwbolg.consumerdemo.model.User;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 * @description: 热点规则测试
 * @author: ChenWei
 * @create: 2020/3/14 - 9:35
 **/

@RestController
@RequestMapping("/api4")
public class ConsumerController4 {

	@Autowired
	ConsumerService consumerService;

	@GetMapping("/consumer/message1")
	@SentinelResource("message1") //配置资源名称，否则热点规则不生效
	String echo1(String name,Integer age){
		return "hello name = "+name+" , age = "+age;
	}

	@PostMapping("/consumer/message2")
	@SentinelResource("message2")
	String echo2(@RequestBody User user){
		return "hello name = "+user.getName()+" , age = "+user.getAge();
	}

}
