package cn.cwbolg.consumerdemo.manager.fallback;

import cn.cwbolg.consumerdemo.manager.ProviderFeignManager;
import cn.cwbolg.consumerdemo.manager.ProviderFeignManager2;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @description: 容错类，实现FallbackFactory接口，指定为哪个类容错
 * @author: ChenWei
 * @create: 2020/3/16 - 21:19
 **/
@Component
public class FeignFallbackFactory implements FallbackFactory<ProviderFeignManager2> {

	@Override
	public ProviderFeignManager2 create(Throwable throwable) {
		return new ProviderFeignManager2() {
			@Override
			public String echo2() {
				return "服务异常，异常原因："+throwable.getMessage();
			}
		};
	}
}
