package org.oursight.study.javaee.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	public static void main(String[] args) {

		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		// ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		// Connection connection = null;
		// Session： 一个发送或接收消息的线程
		// Session session;
		// Destination ：消息的目的地;消息发送给谁.
		// Destination destination;
		// 消费者，消息接收者
		// MessageConsumer consumer;

		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
					"tcp://192.168.10.31:61616");
			// 构造从工厂得到连接对象
			Connection connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// test-queue跟sender的保持一致，一个创建一个来接收
			// 点对点模式获取消息
			String queueName = "JMSDemoQueue";
			Destination destination = session.createQueue(queueName);
			// 广播模式获取消息
			// destination = session.createTopic("test-queue");

			// ====  开始接受消息
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message arg0) {

					// System.out.println("==================");
					try {
						// ((ObjectMessage)arg0).setObject(new User());
						// String info = ((TextMessage) arg0).getText();
						String info = ((TextMessage) arg0).getText();
						System.out.println("consumer1, from queue :" + info);
						// System.out.println("RECEIVE1第一个获得者1:" +info);
						// System.out.println("RECEIVE1第一个获得者1:" +user.getUserName() +
						// ", " + user.getNickName());
					} catch (JMSException e) {
						e.printStackTrace();
					}

				}
			});

			MessageConsumer consumer1 = session.createConsumer(destination);
			consumer1.setMessageListener(new MessageListener() {
				public void onMessage(Message arg0) {
					// System.out.println("+++++++++++++++++++");
					try {
						String info = ((TextMessage) arg0).getText();
						System.out.println("consumer2, from queue :");
						System.out.println(info);
						System.out.println();
						System.out.println();
						// System.out.println("RECEIVE1第二个获得者1:" +info);
						// System.out.println("RECEIVE1第二个获得者2:" +user.getUserName() +
						// ", " + user.getNickName());
					} catch (JMSException e) {
						e.printStackTrace();
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
					"tcp://192.168.10.31:61616");
			// 构造从工厂得到连接对象
			Connection connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// test-queue跟sender的保持一致，一个创建一个来接收
			// 点对点模式获取消息
			Destination destination = session.createTopic("JMSDemoQueue");
			// 广播模式获取消息
			// destination = session.createTopic("test-queue");
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message arg0) {
					// System.out.println("==================");
					try {
						// ((ObjectMessage)arg0).setObject(new User());
						// String info = ((TextMessage) arg0).getText();
						String info = ((TextMessage) arg0).getText();
						System.out.println("consumer1  from topic :" + info);
						System.out.println(info);
						System.out.println();
						System.out.println();
						// System.out.println("RECEIVE1第一个获得者1:" +info);
						// System.out.println("RECEIVE1第一个获得者1:" +user.getUserName() +
						// ", " + user.getNickName());
					} catch (JMSException e) {
						e.printStackTrace();
					}

				}
			});

			MessageConsumer consumer1 = session.createConsumer(destination);
			consumer1.setMessageListener(new MessageListener() {
				public void onMessage(Message arg0) {
					// System.out.println("+++++++++++++++++++");
					try {
						String info = ((TextMessage) arg0).getText();
						System.out.println("consumer2, testtttttt from topic :" + info);
						// System.out.println("RECEIVE1第二个获得者1:" +info);
						// System.out.println("RECEIVE1第二个获得者2:" +user.getUserName() +
						// ", " + user.getNickName());
					} catch (JMSException e) {
						e.printStackTrace();
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 在eclipse里运行的时候，这里不要关闭，这样就可以一直等待服务器发送了，不然就直接结束了。
		// finally {
		// try {
		// if (null != connection)
		// connection.close();
		// } catch (Throwable ignore) {
		// }
		// }

	}
}
