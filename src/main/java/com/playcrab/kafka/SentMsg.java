package com.playcrab.kafka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * 日志生产者，读取日志灌入到kafka主题中
 * 
 * @author weixingxin@playcrab.com
 * @date 2017-08-02
 * 
 *
 */
public class SentMsg {
	//主题与文件关联关系
	static Map<String, String> topicMap = new HashMap<String, String>();
	//文件存储路径
	static String logPath =null;
	public static void init() {
		topicMap.put("playerLogin", "PlayerLogin_2017-07-16.log.txt");
		topicMap.put("playerLogout", "PlayerLogout_2017-07-16.log.txt");
		logPath= "/Users/playcrab/dev/java_code/ws2/storm-demo/src/main/resources/cjg_log";
	}

	public static void main(String[] args) {
		init();
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(
				props);

		// String[] files = { "PlayerLogin_2017-07-16.log.txt",
		// "PlayerLogout_2017-07-16.log.txt" };

		for (Map.Entry<String, String> entry : topicMap.entrySet()) {
			try {
				readyTxt(logPath, entry.getValue(), producer, entry.getKey(),
						new ProducterBuilder() {
							@Override
							public void callBack(
									Producer<String, String> producer,
									String topic, String vals) {
								String prexStr = "kof|";
								producer.send(new ProducerRecord<String, String>(
										topic,  vals));
							}
						});
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		producer.close();
	}

	public static void readyTxt(String path, String fileName,
			Producer<String, String> producer, String topic,
			ProducterBuilder builder) throws FileNotFoundException {
		Scanner in = new Scanner(new File(path + "/" + fileName));
		while (in.hasNextLine()) {
			String str = in.nextLine();
			builder.callBack(producer, topic, str);
		}
		in.close();
	}

	public interface ProducterBuilder {
		public void callBack(Producer<String, String> producer, String topic,
				String vals);
	}

}
