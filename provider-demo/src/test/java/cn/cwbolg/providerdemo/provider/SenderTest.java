package cn.cwbolg.providerdemo.provider;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderTest {

	@Autowired
	RocketMQTemplate rocketMQTemplate;

	/**
	 * 消息同步发送测试
	 */
	@Test
	public void test1(){
		//参数一 topic 如果需要Tag， 可以使用 topic:tag 的这种写法
		//参数二 消息体
		//参数三 超时时间
		SendResult sendResult = rocketMQTemplate.syncSend("test_topic_1", "这是一条同步消息", 1000);
		System.out.println("[发送结果]>>>"+sendResult);
	}

}
