package com.playcrab.storm.demo.dau;

import java.util.Date;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.playcrab.storm.demo.model.PlayerLogout;
import com.playcrab.utils.DateUtils;

/**
 * 日志切割方法
 * 
 * @author playcrab
 *
 */
public class PlayerLogoutSplitBlot extends BaseRichBolt {
	// String
	// line="_____meLimitSaleFlow|149|2017-05-13 20:50:08||1|149|0800010000000000000000000000000001650678|0|53|CheckIn|1|0|1|1|79|traditional";
	// kof|traditional|PlayerLogout|149|2017-05-14
	// 00:00:05||1|149|0800010000000000000000000000000001593869|ubBlock_020|1488333901000|0|1434058_149|阿飛正傳|1|17341|1125|1434058|1669
	private static final long serialVersionUID = 1L;
	private String SPLIT_CHAR = "\\|";

	// private String game;
	OutputCollector _collector;

	/**
	 * 后期传入解析日志
	 * 
	 * @param fields
	 */
	public PlayerLogoutSplitBlot() {
	}

	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		 _collector = collector;
	}
	

	@Override
	public void execute(Tuple input) {
		String val = input.getString(0);
		String[] str = val.split(SPLIT_CHAR);
		if (str.length>=45) {
			this.loginFlow(input,str);

		}
	}

	private void loginFlow(Tuple input,String[] str) {
		//TODO 通过配置文件，可以实现自动装箱
		PlayerLogout logout = new PlayerLogout();
		try {
			logout.setGame(str[0]);
			logout.setActionName(str[1]);
			logout.setGameSvrId(str[2]);
			Date detailDate = DateUtils.parseDate(str[3]);
			logout.setDtEventTime(detailDate.getTime());
			logout.setvGameAppid(str[4]);
			logout.setPlatID(Integer.valueOf(str[5]));
			logout.setiZoneAreaID(Integer.valueOf(str[6]));
			logout.setVopenId(str[7]);
			logout.setOnlineTime(Integer.valueOf(str[8]));
			logout.setLevel(Integer.valueOf(str[9]));
			logout.setPlayerFriendsNum(Integer.valueOf(str[10]));
			logout.setClientVersion(str[11]);
			logout.setSystemSoftware(str[12]);
			logout.setSystemHardware(str[13]);
			logout.setTelecomOper(str[14]);
			logout.setNetwork(str[15]);
			logout.setScreenWidth(Integer.valueOf(str[16]));
			logout.setScreenHight(Integer.valueOf(str[17]));
			logout.setDensity(Float.valueOf(str[18]));
			logout.setLoginChannel(Integer.valueOf(str[19]));
			logout.setCpuHardware(str[20]);
			logout.setMemory(Integer.valueOf(str[21]));
			logout.setgLRender(str[22]);
			logout.setgLVersion(str[23]);
			logout.setDeviceId(str[24]);
			logout.setVipLevel(Integer.valueOf(str[25]));
			logout.setCardsNum(Integer.valueOf(str[26]));
			logout.setIsUnion(Integer.valueOf(str[27]));
			logout.setUnionLevel(Integer.valueOf(str[28]));
			logout.setUnionId(str[29]);
			logout.setNormalProgress(str[30]);
			logout.setEliteProgress(str[31]);
			logout.setNightmareProgress(str[32]);
			logout.setUnionInstanceProgress(str[33]);
			logout.setServerOpenTime(str[34]);
			logout.setChristmasInstanceProgress(str[35]);
			logout.setRid(str[36]);
			logout.setRoleName(str[37]);
			logout.setType(str[38]);
			logout.setGold(str[39]);
			logout.setTotalGold(str[40]);
			logout.setUid(str[41]);
			logout.setCssportsGrade(str[42]);
			logout.setCssportsRank(str[43]);
			logout.setPlatform(str[44]);
			logout.setDt(DateUtils.formatDate1(detailDate));
			
			// collector.emit(saleFlow.getValues());
			Values vals = new Values();
//			vals.add(saleFlow);
			vals.add(logout.getDt());
			vals.add(logout.getGame());
			vals.add(logout.getPlatform());
			vals.add(logout.getGameSvrId());
			vals.add(logout.getVipLevel());
			vals.add(logout.getVopenId());
//			System.err.println(new StringBuffer().append("dt: " + logout.getDt())
//					.append(" ,game: " + logout.getGame()).append(" ,platform: " + logout.getPlatform())
//					.append(" ,gamesvrid: " + logout.getGameSvrId())
//					.append(" ,viplevel: " + logout.getVipLevel().intValue()).append(" ,vopenid: " + logout.getVopenId()));
			_collector.emit(input,vals);
			this._collector.ack(input);
		} catch (Exception e) {
			e.printStackTrace();
			this._collector.fail(input);
		}

		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("dt", "game", "platform", "gamesvrid", "viplevel","vopenid"));
	}





	

}