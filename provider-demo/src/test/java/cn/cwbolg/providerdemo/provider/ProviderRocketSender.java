package cn.cwbolg.providerdemo.provider;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 *
 *
 * @description: 发送消息测试
 * @author: ChenWei
 * @create: 2020/3/22 - 22:22
 **/

public class ProviderRocketSender {

	public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		//1、创建消息生产者，指定生产者所属的组名
		DefaultMQProducer defaultMQProducer = new DefaultMQProducer("myproducer-group");

		//2、指定Nameserver地址
		defaultMQProducer.setNamesrvAddr("192.168.37.130:9876");
		//3、启动生产者
		defaultMQProducer.start();
		//4、创建消息对象
		Message message = new Message("myTopic","myTag",("This is message sender test").getBytes());
		//5、发送消息
		SendResult send = defaultMQProducer.send(message,30000);
		System.out.println("[消息发送返回结果]>>>"+send);
		//6、关闭管道
		defaultMQProducer.shutdown();
	}
}
