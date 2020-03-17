package cn.cwbolg.consumerdemo.controller;

import cn.cwbolg.consumerdemo.Service.ConsumerService;
import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @description: @SentinelResource 测试
 * @author: ChenWei
 * @create: 2020/3/14 - 9:35
 **/

@RestController
@RequestMapping("/api6")
public class ConsumerController6 {

	@Autowired
	ConsumerService consumerService;

    int i =0;


	/**
	 *
	 *
	 * @param serviceName
	 * @return
	 */
	@GetMapping("/consumer/message1")
	@SentinelResource(value = "message1",blockHandler = "blockHandler",fallback = "fallback")
	String echo1(String serviceName){
	    i++;
	    if(i%2 == 0){
	        throw new RuntimeException("服务异常,进入fallback");
        }
		return serviceName;
	}

	/**
	 * blockHandler 要求当前方法的返回值和参数要跟原方法一致，但是允许在参数列表最后加入BlockException参数，用来接收原方法中发生的Sentinel异常
	 *
	 * @param serviceName
	 * @param e
	 * @return
	 */
	public String blockHandler(String serviceName, BlockException e){

		return "BlockException";
	}

    /**
     * fallback 要求当前方法的返回值和参数要跟原方法一致，但是允许在参数列表最后加入Throwable参数，用来接收原方法中发生的异常
     *
     * @param serviceName
     * @param e
     * @return
     */
	public String fallback(String serviceName,Throwable e){
	    return "fallback";
    }
}
