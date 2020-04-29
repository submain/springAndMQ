package com.yhb.rabbitmq.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutPro {
	public static final String EXCHANGE_NAME = "fanout_exchange"; 
	public static final String []  routeKey = {"fanout_first","fanout_second","fanout_third"};
	public static void  main(String [] args) throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		//创建连接
		Connection connection = connectionFactory.newConnection();
		//创建信道
		Channel channel = connection.createChannel();
		//声明交换器
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		//路由键
		//消息
		for(int i=0;i<routeKey.length;i++) {
			String message = "1234["+routeKey[i%3]+"]";
			System.out.println("发送消息fanout"+message);
			//使用fanout交换器，路由键失去作用
			channel.basicPublish(EXCHANGE_NAME, routeKey[i%3], null, message.getBytes());
		}
		
		channel.close();
		connection.close();
 	}
}
