package com.yhb.rabbitmq.equipmentexchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * ������
 * @author Administrator
 *
 */
public class Producer {
	public static final String EXCHANGE_NAME = "equipment_exchange"; 
	public static final String EXCHANGE_EQUIPMENT_NAME = "equipment_exchange"; 
	public static final String []  routeKey = {"equipment_exchange_first","equipment_exchange_second","equipment_exchange_third"};
	public static void  main(String [] args) throws IOException, TimeoutException {
		//�������ӹ���
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		//��������
		Connection connection = connectionFactory.newConnection();
		//�����ŵ�
		Channel channel = connection.createChannel();
		//����������
		Map<String,Object>  argsMap = new HashMap<String,Object>();
		//��ӱ��ý�����������������
		argsMap.put("alternate-exchange", EXCHANGE_EQUIPMENT_NAME);
		channel.exchangeDeclare(EXCHANGE_NAME, "direct",false,false,argsMap);
		//���ý�����
		channel.exchangeDeclare(EXCHANGE_EQUIPMENT_NAME, "fanout",true,false,null);
		//·�ɼ�
		//��Ϣ
		for(int i=0;i<routeKey.length;i++) {
			String message = "1234["+routeKey[i%3]+"]";
			System.out.println("������Ϣfanout"+message);
			//ʹ��fanout��������·�ɼ�ʧȥ����
			channel.basicPublish(EXCHANGE_NAME, routeKey[i%3], null, message.getBytes());
		}
		
		//����û�ܵõ�·�ɵ���Ϣ���뽻����
		
		channel.close();
		connection.close();
 	}
}
