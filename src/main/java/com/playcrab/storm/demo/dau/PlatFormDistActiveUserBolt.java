package com.playcrab.storm.demo.dau;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.text.ParseException;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.storm.Config;
import org.apache.storm.Constants;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import com.playcrab.storm.demo.model.BasicData;
import com.playcrab.utils.DateUtils;
import com.playcrab.utils.MD5Utils;

/**
 *  在这里可以做入库操作，建议使用redis或者mongodb。
 * 
 * @author playcrab
 *
 */
public class PlatFormDistActiveUserBolt extends BaseRichBolt {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OutputCollector _collector;
	private SqlSessionFactory sqlSessionFactory;
	private Reader reader;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		_collector = collector;
		try {
			reader = Resources.getResourceAsReader("mybitis-map-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(Tuple input) {

		if (input.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)
				&& input.getSourceStreamId().equals(Constants.SYSTEM_TICK_STREAM_ID)) {
			// 在此处做入库操作,最好不实时入库，减轻数据库压力.做限速处理

		} else {
			String dt = input.getStringByField("dt");
			String game = input.getStringByField("game");
			String platform = input.getStringByField("platform");
			String gamesvrid = input.getStringByField("gamesvrid");
			Integer viplevel = input.getIntegerByField("viplevel");
			Long dau = input.getLongByField("dau");

			// String key=mkGroupKey(dt, game, platform, gamesvrid, viplevel);
			SqlSession session = sqlSessionFactory.openSession();
			BasicData basicData = new BasicData();
			basicData.setDau(dau);
			basicData.setViplevel(viplevel);
			basicData.setPlatform(platform);
			basicData.setGamesvrid(gamesvrid);
			basicData.setGame(game);
			try {
				basicData.setDt(new Date(DateUtils.parseDate(dt).getTime()));
				int rs = session.insert("com.playcrab.storm.demo.dao.IBasicData.insert", basicData);
				if (rs > 0) {
					session.commit();
					System.out.println("success...");
				}
				this._collector.ack(input);
			} catch (ParseException e) {
				e.printStackTrace();
				this._collector.fail(input);
			} finally {
				session.close();
			}

		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		Config conf = new Config();
		conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, 10);
		return conf;
	}

	private String mkGroupKey(String dt, String game, String platform, String gamesvrid, Integer viplevel) {
		StringBuilder sb = new StringBuilder();
		sb.append(dt).append(game).append(platform).append(gamesvrid).append(viplevel.intValue());
		return MD5Utils.getMD5Str(sb.toString());
	}

}
