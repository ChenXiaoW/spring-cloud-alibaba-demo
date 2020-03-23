package cn.cwbolg.apigateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
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
 * @description: 基于api分组维度的限流
 * @author: ChenWei
 * @create: 2020/3/21 - 9:54
 **/

@Configuration
public class ApiGatewayConfiguration {

	private final List<ViewResolver> viewResolverList;

	private final ServerCodecConfigurer serverCodecConfigurer;
	//分组1
	private final String cousumerApi1 = "cousumer-api-1";
	//分组2
	private final String cousumerApi2 = "cousumer-api-2";

	public ApiGatewayConfiguration(ObjectProvider<List<ViewResolver>> viewsolverProvider, ServerCodecConfigurer serverCodecConfigurer) {
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
		Set<GatewayFlowRule> rules = new HashSet<>(2);
		rules.add(new GatewayFlowRule(cousumerApi1).setCount(2).setIntervalSec(1));
		rules.add(new GatewayFlowRule(cousumerApi2).setCount(3).setIntervalSec(1));
		GatewayRuleManager.loadRules(rules);
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

	/**
	 * 自定义API分组
	 */
	@PostConstruct
	public void initCustomizedApis(){
		Set<ApiDefinition> definitions = new HashSet<>();
		ApiDefinition api1= new ApiDefinition(cousumerApi1)
				.setPredicateItems(new HashSet<ApiPredicateItem>(){{
					//以/consumerdemo/api/开头的请求
					add(new ApiPathPredicateItem().setPattern("/consumerdemo/api/**").setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
				}});
		ApiDefinition api2= new ApiDefinition(cousumerApi2)
				.setPredicateItems(new HashSet<ApiPredicateItem>(){{
					add(new ApiPathPredicateItem().setPattern("/consumerdemo/api2/**").setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
				}});
		definitions.add(api1);
		definitions.add(api2);
		GatewayApiDefinitionManager.loadApiDefinitions(definitions);

	}
}
