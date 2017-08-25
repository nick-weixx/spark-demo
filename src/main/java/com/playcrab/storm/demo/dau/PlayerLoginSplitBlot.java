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

import com.playcrab.storm.demo.model.PlayerLogin;
import com.playcrab.utils.DateUtils;

/**
 * 日志切割方法
 * 
 * @author playcrab
 *
 */
public class PlayerLoginSplitBlot extends BaseRichBolt {
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
	public PlayerLoginSplitBlot() {
	}

	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		 _collector = collector;
	}
	

	@Override
	public void execute(Tuple input) {
		String val = input.getString(0);
		String[] str = val.split(SPLIT_CHAR);
		if (str.length>=36) {
			this.loginFlow(input,str);
		}
	}

	private void loginFlow(Tuple input,String[] str) {
		//TODO 通过配置文件，可以实现自动装箱
		PlayerLogin saleFlow = new PlayerLogin();
		try {
			saleFlow.setGame(str[0]);
			saleFlow.setActionName(str[1]);
			saleFlow.setGameSvrId(str[2]);
			Date detailDate = DateUtils.parseDate(str[3]);
			saleFlow.setDtEventTime(detailDate.getTime());
			saleFlow.setvGameAppid(str[4]);
			saleFlow.setPlatID(Integer.valueOf(str[5]));
			saleFlow.setiZoneAreaID(Integer.valueOf(str[6]));
			saleFlow.setVopenId(str[7]);
			saleFlow.setLevel(Integer.valueOf(str[8]));
			saleFlow.setPlayerFriendsNum(Integer.valueOf(str[9]));
			saleFlow.setClientVersion(str[10]);
			saleFlow.setSystemSoftware(str[11]);
			saleFlow.setSystemHardware(str[12]);
			saleFlow.setTelecomOper(str[13]);
			saleFlow.setNetwork(str[14]);
			saleFlow.setScreenWidth(Integer.valueOf(str[15]));
			saleFlow.setScreenHight(Integer.valueOf(str[16]));
			saleFlow.setDensity(Float.valueOf(str[17]));
			saleFlow.setLoginChannel(Integer.valueOf(str[18]));
			saleFlow.setCpuHardware(str[19]);
			saleFlow.setMemory(Integer.valueOf(str[20]));
			saleFlow.setgLRender(str[21]);
			saleFlow.setgLVersion(str[22]);
			saleFlow.setDeviceId(str[23]);
			saleFlow.setVipLevel(Integer.valueOf(str[24]));
			saleFlow.setCardsNum(Integer.valueOf(str[25]));
			saleFlow.setIsUnion(Integer.valueOf(str[26]));
			saleFlow.setUnionLevel(Integer.valueOf(str[27]));
			saleFlow.setUnionId(str[28]);
			saleFlow.setServerOpenTime(str[29]);
			saleFlow.setTowerMoney(Integer.valueOf(str[30]));
			saleFlow.setIp(str[31]);
			saleFlow.setUid(Integer.valueOf(str[32]));
			saleFlow.setRid(str[33]);
			saleFlow.setRoleName(str[34]);
			saleFlow.setPlatform(str[35]);
			saleFlow.setDt(DateUtils.formatDate1(detailDate));
			
			// collector.emit(saleFlow.getValues());
			Values vals = new Values();
//			vals.add(saleFlow);
			vals.add(saleFlow.getDt());
			vals.add(saleFlow.getGame());
			vals.add(saleFlow.getPlatform());
			vals.add(saleFlow.getGameSvrId());
			vals.add(saleFlow.getVipLevel());
			vals.add(saleFlow.getVopenId());
//			vals.add(saleFlow.getDtEventTime());
//			System.err.println(new StringBuffer().append("dt: " + saleFlow.getDt())
//					.append(" ,game: " + saleFlow.getGame()).append(" ,platform: " + saleFlow.getPlatform())
//					.append(" ,gamesvrid: " + saleFlow.getGameSvrId())
//					.append(" ,viplevel: " + saleFlow.getVipLevel().intValue()).append(" ,vopenid: " + saleFlow.getVopenId()));
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