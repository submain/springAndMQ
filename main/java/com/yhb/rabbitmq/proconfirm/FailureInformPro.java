package com.yhb.rabbitmq.proconfirm;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * ʧ��֪ͨ
 * @author Administrator
 *
 */
public class FailureInformPro {
	public static final String EXCHANGE_NAME = "direct_exchange"; 
	public static final String routeKey [] = {"failTest_01","failTest_02","failTest_03"};
	public static void  main(String [] args) throws IOException, TimeoutException, InterruptedException {
		//�������ӹ���
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		//��������
		Connection connection = connectionFactory.newConnection();
		//�����ŵ�
		Channel channel = connection.createChannel();
		//����������
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		
		//�������Ӻ��ŵ��ر�ʱ��Ҳӵ�м���    ���Խ��йر����ǵ�һЩ��Դ����
		channel.addShutdownListener(new ShutdownListener() {
			
			@Override
			public void shutdownCompleted(ShutdownSignalException cause) {
				// TODO Auto-generated method stub
				
			}
		});
		
		connection.addShutdownListener(new ShutdownListener() {
			
			@Override
			public void shutdownCompleted(ShutdownSignalException cause) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//��Ϣʧ��֪ͨ����
		channel.addReturnListener(new ReturnListener() {
			
			@Override
			public void handleReturn(int arg0, String arg1, String arg2, String arg3, BasicProperties arg4, byte[] arg5)
					throws IOException {
				// TODO Auto-generated method stub
				String message = new String(arg5,"utf-8");
				System.out.println("replatText:"+arg1);
				System.out.println("exchange:"+arg2);
				System.out.println("routingKey:"+arg3);
				System.out.println("properties:"+arg4);
				System.out.println("body:"+message);
				
			}
		});
		
		for(int i=0;i<routeKey.length;i++) {
			//·�ɼ�
			//��Ϣ
			String message = "ʧ��֪ͨ��Ϣ:"+routeKey[i%3];
			System.out.println("������Ϣ"+message);
			channel.basicPublish(EXCHANGE_NAME, routeKey[i%3], true,null, message.getBytes());
			
		}
		
		channel.close();
		connection.close();
 	}
}
