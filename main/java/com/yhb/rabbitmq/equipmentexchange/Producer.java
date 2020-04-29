package com.yhb.rabbitmq.equipmentexchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者
 * @author Administrator
 *
 */
public class Producer {
	public static final String EXCHANGE_NAME = "equipment_exchange"; 
	public static final String EXCHANGE_EQUIPMENT_NAME = "equipment_exchange"; 
	public static final String []  routeKey = {"equipment_exchange_first","equipment_exchange_second","equipment_exchange_third"};
	public static void  main(String [] args) throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		//创建连接
		Connection connection = connectionFactory.newConnection();
		//创建信道
		Channel channel = connection.createChannel();
		//声明交换器
		Map<String,Object>  argsMap = new HashMap<String,Object>();
		//添加备用交换机到主交换机上
		argsMap.put("alternate-exchange", EXCHANGE_EQUIPMENT_NAME);
		channel.exchangeDeclare(EXCHANGE_NAME, "direct",false,false,argsMap);
		//备用交换机
		channel.exchangeDeclare(EXCHANGE_EQUIPMENT_NAME, "fanout",true,false,null);
		//路由键
		//消息
		for(int i=0;i<routeKey.length;i++) {
			String message = "1234["+routeKey[i%3]+"]";
			System.out.println("发送消息fanout"+message);
			//使用fanout交换器，路由键失去作用
			channel.basicPublish(EXCHANGE_NAME, routeKey[i%3], null, message.getBytes());
		}
		
		//其他没能得到路由的消息进入交换机
		
		channel.close();
		connection.close();
 	}
}
