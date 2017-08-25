package com.playcrab.storm.demo.dau;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.playcrab.utils.MD5Utils;

/**
 * 缓存用户信息，做去重操作
 * 
 * @author playcrab
 *
 */
public class DistinctVopIdBlot extends BaseRichBolt {
	OutputCollector _collector;
	Set<String> cacheUser = null;
	Set<String> dtSet = null;


	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this._collector = collector;
		cacheUser = new HashSet<String>();
		dtSet = new HashSet<String>();
	}

	@Override
	public void execute(Tuple input) {
		String dt = input.getStringByField("dt");
		String game = input.getStringByField("game");
		String platform = input.getStringByField("platform");
		String gamesvrid = input.getStringByField("gamesvrid");
		Integer viplevel = input.getIntegerByField("viplevel");
		String vopenid = input.getStringByField("vopenid");
		String key = mkGroupKey(dt, game, platform, gamesvrid, viplevel, vopenid);

		// 开关，用于清理缓存
		if (!dtSet.contains(dt)) {
			cacheUser.clear();
			dtSet.clear();
			dtSet.add(dt);
		}

		if (!cacheUser.contains(key)) {
			cacheUser.add(key);
			Values vals = new Values();
			vals.add(dt);
			vals.add(game);
			vals.add(platform);
			vals.add(gamesvrid);
			vals.add(viplevel);
			vals.add(vopenid);
			_collector.emit(input, vals);
		}

		this._collector.ack(input);
		// System.err.println("usercachecount: "+cacheUser.size());

	}

	private String mkGroupKey(String dt, String game, String platform, String gamesvrid, Integer viplevel, String vopenid) {
		StringBuilder sb = new StringBuilder();
		sb.append(dt).append(game).append(platform).append(gamesvrid).append(viplevel.intValue()).append(vopenid);
		return MD5Utils.getMD5Str(sb.toString());
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("dt", "game", "platform", "gamesvrid", "viplevel", "vopenid"));
	}

}
