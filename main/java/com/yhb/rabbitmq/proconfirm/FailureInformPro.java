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
 * 失败通知
 * @author Administrator
 *
 */
public class FailureInformPro {
	public static final String EXCHANGE_NAME = "direct_exchange"; 
	public static final String routeKey [] = {"failTest_01","failTest_02","failTest_03"};
	public static void  main(String [] args) throws IOException, TimeoutException, InterruptedException {
		//创建连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		//创建连接
		Connection connection = connectionFactory.newConnection();
		//创建信道
		Channel channel = connection.createChannel();
		//声明交换器
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		
		//另，在连接和信道关闭时，也拥有监听    可以进行关闭我们的一些资源处理
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
		
		
		//消息失败通知监听
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
			//路由键
			//消息
			String message = "失败通知消息:"+routeKey[i%3];
			System.out.println("发送消息"+message);
			channel.basicPublish(EXCHANGE_NAME, routeKey[i%3], true,null, message.getBytes());
			
		}
		
		channel.close();
		connection.close();
 	}
}
