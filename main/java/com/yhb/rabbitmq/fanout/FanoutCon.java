package com.yhb.rabbitmq.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yhb.rabbitmq.transaction.TransactionPro;

public class FanoutCon {
	
	public static void main(String [] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		connectionFactory.setHost("localhost");
		
		Connection connection = connectionFactory.newConnection();
		
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(FanoutPro.EXCHANGE_NAME, "fanout");
		
		String queueName = "fanoutConTest";
		
		channel.queueDeclare(queueName, false, false, false, null);
		//��Ȼ����fanout_first·�ɼ������ǽ����յ�ȫ������Ϣ
		channel.queueBind(queueName, FanoutPro.EXCHANGE_NAME,"fanout_first");
		
		Consumer consumer = new DefaultConsumer(channel) {
			 @Override
			public void handleDelivery(String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                //��¼��־���ļ���
                System.out.println( "Received ["+ envelope.getRoutingKey()
                        + "] "+message);
            }
				
		};
		
		channel.basicConsume(queueName, true, consumer);
		
	}

}
