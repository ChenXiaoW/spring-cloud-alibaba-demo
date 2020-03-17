package cn.cwbolg.consumerdemo.manager;

import cn.cwbolg.consumerdemo.manager.fallback.FeignFallback;
import cn.cwbolg.consumerdemo.manager.fallback.FeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "provider-demo",contextId = "provider2",fallbackFactory = FeignFallbackFactory.class)
public interface ProviderFeignManager2 {

	@GetMapping("/api/echo2")
	String echo2();
}
