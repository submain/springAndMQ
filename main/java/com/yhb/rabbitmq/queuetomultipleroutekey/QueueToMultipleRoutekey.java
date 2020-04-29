package com.yhb.rabbitmq.queuetomultipleroutekey;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yhb.rabbitmq.fanout.FanoutPro;
import com.yhb.rabbitmq.transaction.TransactionPro;

public class QueueToMultipleRoutekey {
	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory connectionFactory = new ConnectionFactory();

		connectionFactory.setHost("localhost");

		Connection connection = connectionFactory.newConnection();

		Channel channel = connection.createChannel();
		//ʹ��fanout�Ľ�������������
		channel.exchangeDeclare(FanoutPro.EXCHANGE_NAME, "fanout");

		String queueName = "queueToMultipleRoutekeyTest";

		channel.queueDeclare(queueName, false, false, false, null);
		// һ�����а󶨶��·�ɼ�
		for(int i=0;i<FanoutPro.routeKey.length;i++) {
			channel.queueBind(queueName, FanoutPro.EXCHANGE_NAME, FanoutPro.routeKey[i%3]);
		}
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				// ��¼��־���ļ���
				System.out.println("Received [" + envelope.getRoutingKey() + "] " + message);
			}

		};

		channel.basicConsume(queueName, true, consumer);
	}
}
