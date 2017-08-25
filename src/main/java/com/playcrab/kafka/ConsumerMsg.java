package com.playcrab.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * 消费者队列,查看日志信息
 * @author playcrab
 *
 */
public class ConsumerMsg {
	//playerLogin,playerLogout
	static String  topic="playerLogin";
    private final static int SLEEP = 1000 * 3;

	public static void main(String[] args) {
		ConsumerConnector consumer=Consumer.createJavaConsumerConnector(consumerConfig());
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            System.out.println("Receive->[" + new String(it.next().message()) + "]");
            try {
                Thread.sleep(SLEEP);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
	}
	
	 private static ConsumerConfig consumerConfig() {
	        Properties props = new Properties();
	        props.put("zookeeper.connect", "127.0.0.1:2181/kafka");
	        props.put("group.id", "test-consumer-group");
	        props.put("zookeeper.session.timeout.ms", "40000");
	        props.put("zookeeper.sync.time.ms", "200");
	        props.put("auto.commit.interval.ms", "1000");
	        return new ConsumerConfig(props);
	    }
}
