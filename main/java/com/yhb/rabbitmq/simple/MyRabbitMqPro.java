package com.yhb.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * ������
 * @author Administrator
 *
 */
public class MyRabbitMqPro {
	
	public static final String EXCHANGE_NAME = "direct_exchange"; 
	public static final String routeKey = "first";
	public static void  main(String [] args) throws IOException, TimeoutException {
		//�������ӹ���
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		//��������
		Connection connection = connectionFactory.newConnection();
		//�����ŵ�
		Channel channel = connection.createChannel();
		//����������
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		//·�ɼ�
		//��Ϣ
		String message = "1234";
		System.out.println("������Ϣ"+message);
		channel.basicPublish(EXCHANGE_NAME, routeKey, null, message.getBytes());
		channel.close();
		connection.close();
 	}
}
