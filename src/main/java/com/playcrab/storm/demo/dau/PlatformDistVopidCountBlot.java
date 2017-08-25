package com.playcrab.storm.demo.dau;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.storm.Config;
import org.apache.storm.Constants;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class PlatformDistVopidCountBlot extends BaseRichBolt {
	OutputCollector _collector;
	Map<DomenValue, Long> cacheUserCount = null;
	Set<String> dtSet = null;
	int dau_secs = 20;

	public PlatformDistVopidCountBlot() {
	}

	public PlatformDistVopidCountBlot(int secs) {
		dau_secs = secs;
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		_collector = collector;
		cacheUserCount = new HashMap<DomenValue, Long>();
		dtSet = new HashSet<String>();
	}

	@Override
	public void execute(Tuple input) {

		if (input.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)
				&& input.getSourceStreamId().equals(Constants.SYSTEM_TICK_STREAM_ID)) {
			// System.out.println("################################TickTest bolt: "
			// + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new
			// Date()));
			for (Map.Entry<DomenValue, Long> entey : cacheUserCount.entrySet()) {
				DomenValue dv = entey.getKey();
				Long count = entey.getValue();
				Values vals = new Values();
				vals.add(dv.getDt());
				vals.add(dv.getGame());
				vals.add(dv.getPlatform());
				vals.add(dv.getGamesvrid());
				vals.add(dv.getViplevel());
				vals.add(count);
				_collector.emit(input, vals);
			}
		} else {
			String dt = input.getStringByField("dt");
			String game = input.getStringByField("game");
			String platform = input.getStringByField("platform");
			String gamesvrid = input.getStringByField("gamesvrid");
			Integer viplevel = input.getIntegerByField("viplevel");
			// String vopenid = input.getStringByField("vopenid");
			DomenValue domenValue = new DomenValue();
			domenValue.setDt(dt);
			domenValue.setGame(game);
			domenValue.setPlatform(platform);
			domenValue.setGamesvrid(gamesvrid);
			domenValue.setViplevel(viplevel);
			// 开关程序，如果数据更新到第二天，将前一天的缓存数据清理掉
			if (!dtSet.contains(dt)) {
				cacheUserCount.clear();
				dtSet.clear();
				dtSet.add(dt);
			}
			// String key = mkGroupKey(dt, game, platform, gamesvrid, viplevel);
			if (cacheUserCount.containsKey(domenValue)) {
				Long count = cacheUserCount.get(domenValue);
				cacheUserCount.put(domenValue, count + 1L);
			} else {
				cacheUserCount.put(domenValue, 1L);
			}
		}
		// 锚定成功
		this._collector.ack(input);
	}

	// private String mkGroupKey(String dt, String game, String platform,
	// String gamesvrid, String viplevel) {
	// StringBuilder sb = new StringBuilder();
	// sb.append(dt).append(game).append(platform).append(gamesvrid)
	// .append(viplevel);
	// return MD5Utils.getMD5Str(sb.toString());
	// }

	@Override
	public Map<String, Object> getComponentConfiguration() {
		Config conf = new Config();
		conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, dau_secs);
		return conf;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("dt", "game", "platform", "gamesvrid", "viplevel", "dau"));
	}

	class DomenValue {
		String dt;
		String game;
		String platform;
		String gamesvrid;
		Integer viplevel;

		public String getDt() {
			return dt;
		}

		public void setDt(String dt) {
			this.dt = dt;
		}

		public String getGame() {
			return game;
		}

		public void setGame(String game) {
			this.game = game;
		}

		public String getPlatform() {
			return platform;
		}

		public void setPlatform(String platform) {
			this.platform = platform;
		}

		public String getGamesvrid() {
			return gamesvrid;
		}

		public void setGamesvrid(String gamesvrid) {
			this.gamesvrid = gamesvrid;
		}

		public Integer getViplevel() {
			return viplevel;
		}

		public void setViplevel(Integer viplevel) {
			this.viplevel = viplevel;
		}

		@Override
		public int hashCode() {
			return dt.hashCode() + game.hashCode() + platform.hashCode() + gamesvrid.hashCode() + viplevel.intValue() * 31;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof DomenValue)) {
				return false;
			}
			DomenValue dv = (DomenValue) obj;
			return this.game.equals(dv.getGame()) && this.dt.equals(dv.getDt()) && this.platform.equals(dv.getPlatform())
					&& this.gamesvrid.equals(dv.getGamesvrid()) && this.viplevel.intValue() == dv.getViplevel().intValue();
		}

	}

}
