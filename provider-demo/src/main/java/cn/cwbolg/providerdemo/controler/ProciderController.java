package cn.cwbolg.providerdemo.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 * @description: 服务提供者
 * @author: ChenWei
 * @create: 2020/3/11 - 20:08
 **/
@RestController
@RequestMapping("/api")
public class ProciderController {

	@Value("${server.port}")
	String port;

	@GetMapping("/echo/{message}")
	String echo(@PathVariable("message") String message){
		return "Hello Nacos " + message+", i am  "+port;
	}
}
