package com.yhb.rabibtmq.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class TopicCon {
	public static void main(String [] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		Connection connection = connectionFactory.newConnection();
		
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(TopicPro.TOPICEXCHANGE_NAME, "topic");
		//声明随机队列
		String ququeName = channel.queueDeclare().getQueue();
		
		channel.queueDeclare(ququeName, false, false, false, null);
		
		channel.queueBind(ququeName, TopicPro.TOPICEXCHANGE_NAME, "*.error.*");
		
		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// TODO Auto-generated method stub
				String message = new String(body,"utf-8");
				System.out.println("接受到消息："+message);
			}
			
		};
	}
	
	
}
