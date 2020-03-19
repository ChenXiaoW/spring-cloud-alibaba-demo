package cn.cwbolg.apigateway.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author  chenw
 * @date  2020/3/19 11:19
 *
 * 自定义路由
 */
@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenRoutePredicateFactory.Config> {

    public TokenRoutePredicateFactory(){
        super(Config.class);
    }
    /**
     * 断言逻辑
     *
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(TokenRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String token = serverWebExchange.getRequest().getHeaders().getFirst("Token");
                if(!StringUtils.isEmpty(token)){
                    if(token.equals(config.getToken())){
                        return true;
                    }else {
                        return false;
                    }
                }
                return false;
            }
        };
    }

    /**
     * 读取配置文件中的参数，然后赋值到配置类中相应的参数中
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("token");
    }

    //配置类，用于接收配置文件中的参数
    @Data
    @NoArgsConstructor
    public static class Config{

        private String token;

    }
}
