package cn.cwbolg.apigateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;
/**
 *
 *
 * @description: 基于route维度的限流
 * @author: ChenWei
 * @create: 2020/3/21 - 9:54
 **/

//@Configuration
public class RouteGatewayConfiguration {

	private final List<ViewResolver> viewResolverList;

	private final ServerCodecConfigurer serverCodecConfigurer;

	public RouteGatewayConfiguration(ObjectProvider<List<ViewResolver>> viewsolverProvider, ServerCodecConfigurer serverCodecConfigurer) {
		this.viewResolverList = viewsolverProvider.getIfAvailable(Collections::emptyList);
		this.serverCodecConfigurer = serverCodecConfigurer;
	}

	/**
	 * 初始化一个限流过滤器
	 *
	 * @return
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public GlobalFilter sentinelGatewayFilter() {
		return new SentinelGatewayFilter();
	}

	/**
	 * 配置初始化的限流参数
	 */
	@PostConstruct
	public void initGatewayRules(){
		Set<GatewayFlowRule> ruleSet = new HashSet<>();
		ruleSet.add(new GatewayFlowRule("consumer-demo")   //资源名称，对应路由ID
		        .setCount(2) //限流阈值
				.setIntervalSec(1) //统计时间窗口，单位默认是秒，默认是1秒
		);
		GatewayRuleManager.loadRules(ruleSet);
	}

	/**
	 * 配置限流的异常处理器
	 *
	 * @return
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler(){
		return new SentinelGatewayBlockExceptionHandler(viewResolverList,serverCodecConfigurer);
	}

	/**
	 * 自定义限流异常界面
	 */
	public void initBlockHandlers(){
		BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
			@Override
			public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
				Map map = new HashMap<>();
				map.put("code",0);
				map.put("message","接口限流");
				return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(map));
			}
		};
		GatewayCallbackManager.setBlockHandler(blockRequestHandler);
	}
}
