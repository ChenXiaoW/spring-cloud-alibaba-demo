package cn.cwbolg.consumerdemo.Service.impl;

import cn.cwbolg.consumerdemo.Service.ConsumerService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	/**
	 * @SentinelResource 指定需要链路的资源，value - 设置资源名
	 */
	@SentinelResource(value = "message")
	@Override
	public String echo(String addr) {
		return "i am from "+addr;
	}
}
