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
		//�������ӹ���
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		//��������
		Connection connection = connectionFactory.newConnection();
		//�����ŵ�
		Channel channel = connection.createChannel();
		//����������
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		//·�ɼ�
		//��Ϣ
		for(int i=0;i<routeKey.length;i++) {
			String message = "1234["+routeKey[i%3]+"]";
			System.out.println("������Ϣfanout"+message);
			//ʹ��fanout��������·�ɼ�ʧȥ����
			channel.basicPublish(EXCHANGE_NAME, routeKey[i%3], null, message.getBytes());
		}
		
		channel.close();
		connection.close();
 	}
}
