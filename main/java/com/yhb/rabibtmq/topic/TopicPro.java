package com.yhb.rabibtmq.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicPro {
	
	final static String TOPICEXCHANGE_NAME="topic_exchange";
	
	public static void main(String [] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		Connection connection = connectionFactory.newConnection();
		
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(TOPICEXCHANGE_NAME, "topic");
		
		String [] title = {"email","fix","qq"};
		
		String [] msgType = {"info","error","warning"};
		
		String [] server = {"A","B"};
		
		for(int i=0;i<title.length;i++) {
			String msg = "发送消息,主题为：" + title[i%3] + ";类型为：" + msgType[i%3] + "服务器为：" + server[i%3];
			String routeKey = title[i%3] + "." + msgType[i%3] + "." + server[i%3];
			channel.basicPublish(TOPICEXCHANGE_NAME, routeKey, false, null, msg.getBytes("utf-8"));
		}
		
	}
	
	
}
