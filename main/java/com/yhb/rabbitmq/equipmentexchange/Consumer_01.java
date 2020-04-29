package com.yhb.rabbitmq.equipmentexchange;

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

/**
 * 备用交换机消费者
 * @author Administrator
 *
 */
public class Consumer_01 {
	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory connectionFactory = new ConnectionFactory();

		connectionFactory.setHost("localhost");

		Connection connection = connectionFactory.newConnection();

		Channel channel = connection.createChannel();

		channel.exchangeDeclare(Producer.EXCHANGE_EQUIPMENT_NAME, "fanout");

		String queueName = "equipmentTest";

		channel.queueDeclare(queueName, false, false, false, null);
		// 绑定备用交换机     使用#接受所有的消息
		channel.queueBind(queueName, Producer.EXCHANGE_EQUIPMENT_NAME, "#");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				// 记录日志到文件：
				System.out.println("Received [" + envelope.getRoutingKey() + "] " + message);
			}

		};

		channel.basicConsume(queueName, true, consumer);
	}
}
