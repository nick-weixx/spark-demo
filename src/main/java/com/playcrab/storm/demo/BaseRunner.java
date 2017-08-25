package com.playcrab.storm.demo;

import java.util.Properties;

import org.apache.storm.Config;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;

public class BaseRunner implements IRunner {
	protected boolean is_debug = true;
	@Override
	public void run() {

	}

	public SpoutConfig spoutConf(String topic,String zkStr, boolean isdebug) {
		// 配置Zookeeper地址
		BrokerHosts brokerHosts = new ZkHosts(zkStr);
		// 配置Kafka订阅的Topic，以及zookeeper中数据节点目录和名字
		SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, "", "test-consumer-group");
		spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
//		spoutConfig.startOffsetTime = kafka.api.OffsetRequest.EarliestTime();
		 spoutConfig.startOffsetTime = kafka.api.OffsetRequest.LatestTime();
		return spoutConfig;
	}

	/**
	 * 输出到kafka
	 * 
	 * @return
	 */
	public Config blotConf() {
		// 配置KafkaBolt中的kafka.broker.properties
		Config conf = new Config();
		conf.setDebug(true);
		Properties props = new Properties();
		// 配置Kafka broker地址
		props.put("metadata.broker.list", "localhost:9092");
		// serializer.class为消息的序列化类
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		conf.put("kafka.broker.properties", props);
		// 配置KafkaBolt生成的topic
		conf.put("topic", "playerLogin");
		return conf;
	}

}
