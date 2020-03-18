package cn.cwbolg.consumerdemo.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 * @description: 自定义来源访问控制
 * @author: ChenWei
 * @create: 2020/3/15 - 21:48
 **/
@Component
public class SentinelOriginParserDefinition implements RequestOriginParser {
	/**
	 * 定义区分来源，通过请求域来获取来源标识，然后交给Sentinel流控应用  进行位置匹配
	 *
	 * @param httpServletRequest
	 * @return
	 */
	@Override
	public String parseOrigin(HttpServletRequest httpServletRequest) {

		String serviceName = httpServletRequest.getParameter("serviceName");
		if(StringUtils.isEmpty(serviceName)){
			throw new RuntimeException(" serviceName is not empty");
		}

		return serviceName;
	}
}
