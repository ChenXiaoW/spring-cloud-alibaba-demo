package cn.cwbolg.providerdemo.provider;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

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
		SendResult sendResult = rocketMQTemplate.syncSend("test_topic_1", "这是一条同步消息", 10000);
		System.out.println("[发送结果]>>>"+sendResult);
	}

	/**
	 * 异步消息发送测试
	 */
	@Test
	public void test2() throws InterruptedException {
		//1、topic
		//2、消息体
		//3、回调
		rocketMQTemplate.asyncSend("test_topic_2", "这是一条异步消息", new SendCallback() {
			/**
			 * 成功响应回调
			 * @param sendResult
			 */
			@Override
			public void onSuccess(SendResult sendResult) {
				System.out.println("[成功响应回调]>>>"+sendResult);
			}

			/**
			 * 异常响应回调
			 * @param throwable
			 */
			@Override
			public void onException(Throwable throwable) {
				System.out.println("[异常响应回调]>>>"+throwable);
			}
		});

		Thread.sleep(1000000);
	}

	/**
	 * 单向发送测试
	 */
	@Test
	public void test3(){
		for (int i=0 ; i<15;i++){
			rocketMQTemplate.sendOneWay("myTopic", "这是一条单向消息=="+i);
		}
	}

	@Test
	public void test4(){
		//同步顺序消息
		//第三个参数用于队列选择，唯一
		for (int i = 0; i <5 ; i++) {
			SendResult sendResult = rocketMQTemplate.syncSendOrderly("myTopic", "同步顺序消息", "xxx",10000);
		}
		//异步顺序消息
	/*	rocketMQTemplate.asyncSendOrderly("myTopic", "异步顺序消息", "xxx", new  SendCallback() {
			@Override
			public void onSuccess(SendResult sendResult) {

			}

			@Override
			public void onException(Throwable throwable) {

			}
		});
		//单向顺序消息
		rocketMQTemplate.sendOneWayOrderly("myTopic", "单向顺序消息", "xxx");*/
	}

}
