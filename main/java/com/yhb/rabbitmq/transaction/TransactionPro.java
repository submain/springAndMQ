package com.yhb.rabbitmq.transaction;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * ���������Ĵ���
 * @author Administrator
 *
 */
public class TransactionPro {
	
	public final static String EXCHANGE_NAME = "transaction_exchange";
	public static void main(String [] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME,"direct");
		String [] routeKeys = {"first","second","third"};
		channel.txSelect();
		try {
			for(int i=0;i<routeKeys.length;i++) {
				String message = "·��Ϊ"+routeKeys[i%3]+"����Ϣ";
				//ʹ��utf-8���б���
				channel.basicPublish(EXCHANGE_NAME, routeKeys[i%3], null,message.getBytes("UTF-8"));	
				System.out.println("----------------------------------");
	            System.out.println(" Sent Message: [" + routeKeys[i%3] +"]:'"
	                    + message);
			}
			channel.txCommit();
		}
		catch(Exception e){
			channel.txRollback();
		}
		finally {
			System.out.println("�ö��������....");
		}
		
		channel.close();
		connection.close();
		
		
	}
}
