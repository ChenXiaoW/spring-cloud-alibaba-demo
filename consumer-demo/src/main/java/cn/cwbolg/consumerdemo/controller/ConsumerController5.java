package cn.cwbolg.consumerdemo.controller;

import cn.cwbolg.consumerdemo.Service.ConsumerService;
import cn.cwbolg.consumerdemo.model.User;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 * @description: 授权规则测试
 * @author: ChenWei
 * @create: 2020/3/14 - 9:35
 **/

@RestController
@RequestMapping("/api5")
public class ConsumerController5 {

	@Autowired
	ConsumerService consumerService;

	@GetMapping("/consumer/message1")
	String echo1(String serviceName){
		return serviceName;
	}


}
