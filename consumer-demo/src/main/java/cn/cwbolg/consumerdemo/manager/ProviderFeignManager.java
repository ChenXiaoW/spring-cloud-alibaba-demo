package cn.cwbolg.consumerdemo.manager;

import cn.cwbolg.consumerdemo.manager.fallback.FeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "provider-demo",contextId = "provider",fallback = FeignFallback.class)
public interface ProviderFeignManager {

	@GetMapping("/api/echo/{message}")
	String echo(@PathVariable("message") String message);
}
