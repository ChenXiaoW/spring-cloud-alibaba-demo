package cn.cwbolg.consumerdemo.consumer;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 *
 *
 * @description: 接收消息测试
 * @author: ChenWei
 * @create: 2020/3/23 - 18:37
 **/

public class ConsumerReceiveTest {

	public static void main(String[] args) throws MQClientException {
		//1、创建消费者，并且为其指定消费者组名
		DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("myproducer-group");
		//2、为消费者设置NameServer地址
		defaultMQPushConsumer.setNamesrvAddr("192.168.37.130:9876");
		//3、指定消费者订阅的主体和标签
		defaultMQPushConsumer.subscribe("myTopic","*");
		//4、设置一个回调函数，并且在函数中编写接收到消息之后的处理方法
		defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

				System.out.println("[消费消息]>>>"+list);
				//返回消费成功状态
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		//5、启动消费者
		defaultMQPushConsumer.start();
		System.out.println("[消费成功]");
	}
}
