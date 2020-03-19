package cn.cwbolg.apigateway.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author  chenw
 * @date  2020/3/19 10:09
 *
 * 自定义路由断言工厂
 * 1、名字必须是配置+RoutePredicateFactory
 * 2、必须继承AbstractRoutePredicateFactory<配置类>
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    /**
     * 读取配置文件中的参数，然后赋值到配置类中的属性上
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        //这个位置的顺序必须跟配置文件中的值的顺序保持一致
        return Arrays.asList("minAge","maxAge");
    }

    /**
     * 断言逻辑
     *
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //接收前台传入的参数
                String age = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                if(!StringUtils.isEmpty(age)){
                    int i = Integer.parseInt(age);
                    if(i<config.getMaxAge()&&i>config.getMinAge()){
                        return true;
                    }else {
                        return false;
                    }
                }
                return false;
            }
        };
    }

    //配置类，用于接收配置文件中对应的参数
    @Data
    @NoArgsConstructor
    public static class Config {

        private Integer minAge;

        private Integer maxAge;

    }

}