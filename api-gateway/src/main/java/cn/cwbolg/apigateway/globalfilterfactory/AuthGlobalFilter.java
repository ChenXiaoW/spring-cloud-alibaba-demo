package cn.cwbolg.apigateway.globalfilterfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
/**
 *
 *
 * @description: 自定义全局过滤器 （统一鉴权）
 * @author: ChenWei
 * @create: 2020/3/19 - 18:55
 **/

//@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
	/**
	 * 过滤器逻辑
	 * @param exchange
	 * @param chain
	 * @return
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		//统一鉴权逻辑
		String token = exchange.getRequest().getHeaders().getFirst("token");
		if(!StringUtils.pathEquals("admin",token)){
			//认证失败
			log.info("认证失败...");
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		//放行
		return chain.filter(exchange);
	}

	/**
	 * 标识当前过滤器的优先级，返回值越小，优先级越高
	 * @return
	 */
	@Override
	public int getOrder() {
		return 0;
	}
}
