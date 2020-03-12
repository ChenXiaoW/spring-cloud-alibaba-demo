package cn.cwbolg.consumerdemo.manager.fallback;

import cn.cwbolg.consumerdemo.manager.ProviderFeignManager;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements ProviderFeignManager {
	@Override
	public String echo(String message) {
		return "服务异常";
	}
}
