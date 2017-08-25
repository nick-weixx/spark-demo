package com.playcrab.storm.demo.dau;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

import com.playcrab.storm.demo.BaseRunner;

public class DauRunner extends BaseRunner {
	String zkStr = "localhost:2181/kafka";

	public DauRunner(boolean is_debug, String zkStr) {
		this.is_debug = is_debug;
		if (!is_debug) {
			this.zkStr = zkStr;
		}
	}

	// kafka topic name
	final String TOPIC_LOGIN = "playerLogin";
	final String TOPIC_LOGOUT = "playerLogout";
	// spout name
	final String SPOUT_LOGIN = "spout_login";
	final String SPOUT_LOGOUT = "spout_logout";
	// blot split name
	final String BLOT_SPLIT_LOGIN = "blot_split_login";
	final String BLOT_SPLIT_LOGOUT = "blot_split_logout";
	// topology name
	final String TOPOLOGY_NAME = "dau_topic_top";
	// distinct openid blot
	final String BLOT_UNION = "blot_union_distinct";

	final String BLOT_DAU = "blot_dau";
	final String BLOT_LOADDATA = "blot_load";

	@Override
	public void run() {
		TopologyBuilder builder = new TopologyBuilder();
		// 设置登录信息spout
		builder.setSpout(SPOUT_LOGIN, new KafkaSpout(spoutConf(TOPIC_LOGIN, zkStr, is_debug)));
		builder.setBolt(BLOT_SPLIT_LOGIN, new PlayerLoginSplitBlot(), 2).setNumTasks(4).shuffleGrouping(SPOUT_LOGIN);
		// 设置登出spout
		builder.setSpout(SPOUT_LOGOUT, new KafkaSpout(spoutConf(TOPIC_LOGOUT, zkStr, is_debug)));
		builder.setBolt(BLOT_SPLIT_LOGOUT, new PlayerLogoutSplitBlot(), 2).setNumTasks(4).shuffleGrouping(SPOUT_LOGOUT);
		// union login 和logout信息,并去重用户。保证相同的用户发到同一个blot
		builder.setBolt(BLOT_UNION, new DistinctVopIdBlot(), 2).setNumTasks(4)
				.fieldsGrouping(BLOT_SPLIT_LOGIN, new Fields("dt", "game", "platform", "gamesvrid", "viplevel", "vopenid"))
				.fieldsGrouping(BLOT_SPLIT_LOGOUT, new Fields("dt", "game", "platform", "gamesvrid", "viplevel", "vopenid"));
		// dau 聚合blot，缓存每个区服下的dua总数
		builder.setBolt(BLOT_DAU, new PlatformDistVopidCountBlot(), 2).setNumTasks(4)
				.fieldsGrouping(BLOT_UNION, new Fields("dt", "game", "platform", "gamesvrid", "viplevel"));
		// // 入库blot，将数据更新至数据库
		builder.setBolt(BLOT_LOADDATA, new PlatFormDistActiveUserBolt(), 1).setNumTasks(1).globalGrouping(BLOT_DAU);

		if (is_debug) {
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology(TOPOLOGY_NAME, blotConf(), builder.createTopology());
			Utils.sleep(30 * 60 * 1000);
			cluster.killTopology(TOPOLOGY_NAME);
			cluster.shutdown();
		} else {
			Config conf = blotConf();
			conf.setDebug(true);
			conf.setNumWorkers(2);
			try {
				StormSubmitter.submitTopology(TOPOLOGY_NAME, conf, builder.createTopology());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
