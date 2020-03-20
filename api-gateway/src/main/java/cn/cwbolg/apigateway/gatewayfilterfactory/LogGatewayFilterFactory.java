package cn.cwbolg.apigateway.gatewayfilterfactory;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author  chenw
 * @date  2020/3/19 14:28
 *
 * 自定义局部过滤器
 */
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {


    public LogGatewayFilterFactory(){
        super(LogGatewayFilterFactory.Config.class);
    }

    /**
     * 过滤器逻辑
     *
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(LogGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if(config.isCacheLog()){
                    System.out.println("cacheLog 开启");
                }
                if(config.isConsoleLog()){
                    System.out.println("consoleLog 开启");
                }
                return chain.filter(exchange);
            }
        };
    }

    /**
     * 读取配置文件中的参数，并赋值到配置类上
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }

    /**
     * 配置类
     */
    @Data
    @NoArgsConstructor
    public static class Config{

        private boolean consoleLog;

        private boolean  cacheLog;
    }
}
