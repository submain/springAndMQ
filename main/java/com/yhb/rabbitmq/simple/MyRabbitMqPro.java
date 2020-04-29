package com.yhb.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者
 * @author Administrator
 *
 */
public class MyRabbitMqPro {
	
	public static final String EXCHANGE_NAME = "direct_exchange"; 
	public static final String routeKey = "first";
	public static void  main(String [] args) throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		//创建连接
		Connection connection = connectionFactory.newConnection();
		//创建信道
		Channel channel = connection.createChannel();
		//声明交换器
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		//路由键
		//消息
		String message = "1234";
		System.out.println("发送消息"+message);
		channel.basicPublish(EXCHANGE_NAME, routeKey, null, message.getBytes());
		channel.close();
		connection.close();
 	}
}
