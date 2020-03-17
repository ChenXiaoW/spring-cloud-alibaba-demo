package cn.cwbolg.consumerdemo.config;


import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SentinelPushConfig implements InitFunc {

	@Value("${spring.cloud.nacos.discovery.server-addr}")
	String serverAddr;

	@Value("${spring.cloud.sentinel.datasource.flow.nacos.groupId}")
	String groupId;

	@Value("${spring.cloud.sentinel.datasource.flow.nacos.dataId}")
	String dataId;

	@Override
	public void init() throws Exception {
		serverAddr = serverAddr;
		groupId = groupId;
		dataId = dataId;
		Converter<String, List<FlowRule>> parser = source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
		});
		ReadableDataSource<String, List<FlowRule>> nacosDataSource = new NacosDataSource<>(serverAddr, groupId, dataId, parser);
		FlowRuleManager.register2Property(nacosDataSource.getProperty());
	}
}
