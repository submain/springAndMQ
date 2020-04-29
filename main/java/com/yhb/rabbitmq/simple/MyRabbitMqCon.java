package com.yhb.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.*;

/**
 * 消费者
 * @author Administrator
 *
 */
public class MyRabbitMqCon {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		//声明交换器
		channel.exchangeDeclare(MyRabbitMqPro.EXCHANGE_NAME, "direct");
		//创建队列
		String queueName = "firstQueue";
		channel.queueDeclareNoWait(queueName, false, false, false, null);
		//将队列通过路由键绑定交换机
		channel.queueBind(queueName, MyRabbitMqPro.EXCHANGE_NAME, MyRabbitMqPro.routeKey);
		//创建消费者
		  final Consumer consumer = new DefaultConsumer(channel){
	            @Override
	            public void handleDelivery(String consumerTag,
	                                       Envelope envelope,
	                                       AMQP.BasicProperties properties,
	                                       byte[] body) throws IOException {
	                String message = new String(body, "UTF-8");
	                System.out.println("Received["+envelope.getRoutingKey()
	                        +"]"+message);
	            }
	        };
	        channel.basicConsume(queueName, true,consumer);
	}
}
