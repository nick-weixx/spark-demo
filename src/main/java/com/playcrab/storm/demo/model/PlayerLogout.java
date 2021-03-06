package com.playcrab.storm.demo.model;

import java.util.Date;

import org.apache.storm.tuple.Values;
import java.io.Serializable;

public class PlayerLogout  implements Serializable{
	// 游戏名称 TODO
	private String game;
	// 日志类型 TODO
	private String actionName;
	// 服务器编号
	private String gameSvrId;
	// 游戏事件时间
	private Long dtEventTime;
	// 游戏APPID
	private String vGameAppid;
	// ios 0/android 1
	private Integer platID;
	// 分区标识
	private Integer iZoneAreaID;
	// 用户OPENID
	private String vopenId;
	// 用户在线时长s
	private Integer onlineTime;
	// 用户等级
	private Integer level;
	private Integer playerFriendsNum;
	// 客户端版本
	private String clientVersion;
	// 移动终端操作系统版本
	private String systemSoftware;
	// 移动终端机型
	private String systemHardware;
	// 运营商
	private String telecomOper;
	// 客户端网络类型
	private String network;
	// 显示屏宽度
	private Integer screenWidth;
	// 显示屏高度
	private Integer screenHight;
	// 像素密度
	private Float density;
	// 登录渠道
	private Integer loginChannel;
	// cpu类型|频率|核数
	private String cpuHardware;
	// 内存信息单位M
	private Integer memory;
	// pengl render信息
	private String gLRender;
	// opengl版本信息
	private String gLVersion;
	// 设备ID
	private String deviceId;
	// vip等级
	private Integer vipLevel;

	// 玩家持有卡牌数量
	private Integer cardsNum;
	// 是否有社团 1有社团 0 没有社团
	private Integer isUnion;
	// 社团等级
	private Integer unionLevel;
	// 社团ID
	private String unionId;
	// 普通副本进度
	private String normalProgress;
	// 精英副本进度
	private String eliteProgress;
	// 噩梦副本进度
	private String nightmareProgress;
	// 社团副本进度
	private String unionInstanceProgress;
	// 服务器开启时间
	private String serverOpenTime;
	// 圣诞副本进度
	private String christmasInstanceProgress;
	// 玩家的rid
	private String rid;
	// 玩家的昵称
	private String roleName;
	// 玩家账号类型
	private String type;
	// 玩家的钻石数量
	private String gold;
	// 玩家的总共获取钻石数量
	private String totalGold;
	// 玩家的UID
	private String uid;
	// 跨服竞技场阶位
	private String cssportsGrade;
	// 跨服竞技场排名
	private String cssportsRank;
	// 平台
	private String platform;
	private String dt;

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getGameSvrId() {
		return gameSvrId;
	}

	public void setGameSvrId(String gameSvrId) {
		this.gameSvrId = gameSvrId;
	}


	public Long getDtEventTime() {
		return dtEventTime;
	}

	public void setDtEventTime(Long dtEventTime) {
		this.dtEventTime = dtEventTime;
	}

	public String getvGameAppid() {
		return vGameAppid;
	}

	public void setvGameAppid(String vGameAppid) {
		this.vGameAppid = vGameAppid;
	}

	public Integer getPlatID() {
		return platID;
	}

	public void setPlatID(Integer platID) {
		this.platID = platID;
	}

	public Integer getiZoneAreaID() {
		return iZoneAreaID;
	}

	public void setiZoneAreaID(Integer iZoneAreaID) {
		this.iZoneAreaID = iZoneAreaID;
	}

	public String getVopenId() {
		return vopenId;
	}

	public void setVopenId(String vopenId) {
		this.vopenId = vopenId;
	}

	public Integer getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Integer onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPlayerFriendsNum() {
		return playerFriendsNum;
	}

	public void setPlayerFriendsNum(Integer playerFriendsNum) {
		this.playerFriendsNum = playerFriendsNum;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getSystemSoftware() {
		return systemSoftware;
	}

	public void setSystemSoftware(String systemSoftware) {
		this.systemSoftware = systemSoftware;
	}

	public String getSystemHardware() {
		return systemHardware;
	}

	public void setSystemHardware(String systemHardware) {
		this.systemHardware = systemHardware;
	}

	public String getTelecomOper() {
		return telecomOper;
	}

	public void setTelecomOper(String telecomOper) {
		this.telecomOper = telecomOper;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public Integer getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(Integer screenWidth) {
		this.screenWidth = screenWidth;
	}

	public Integer getScreenHight() {
		return screenHight;
	}

	public void setScreenHight(Integer screenHight) {
		this.screenHight = screenHight;
	}

	public Float getDensity() {
		return density;
	}

	public void setDensity(Float density) {
		this.density = density;
	}

	public Integer getLoginChannel() {
		return loginChannel;
	}

	public void setLoginChannel(Integer loginChannel) {
		this.loginChannel = loginChannel;
	}

	public String getCpuHardware() {
		return cpuHardware;
	}

	public void setCpuHardware(String cpuHardware) {
		this.cpuHardware = cpuHardware;
	}

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public String getgLRender() {
		return gLRender;
	}

	public void setgLRender(String gLRender) {
		this.gLRender = gLRender;
	}

	public String getgLVersion() {
		return gLVersion;
	}

	public void setgLVersion(String gLVersion) {
		this.gLVersion = gLVersion;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}

	public Integer getCardsNum() {
		return cardsNum;
	}

	public void setCardsNum(Integer cardsNum) {
		this.cardsNum = cardsNum;
	}

	public Integer getIsUnion() {
		return isUnion;
	}

	public void setIsUnion(Integer isUnion) {
		this.isUnion = isUnion;
	}

	public Integer getUnionLevel() {
		return unionLevel;
	}

	public void setUnionLevel(Integer unionLevel) {
		this.unionLevel = unionLevel;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getNormalProgress() {
		return normalProgress;
	}

	public void setNormalProgress(String normalProgress) {
		this.normalProgress = normalProgress;
	}

	public String getEliteProgress() {
		return eliteProgress;
	}

	public void setEliteProgress(String eliteProgress) {
		this.eliteProgress = eliteProgress;
	}

	public String getNightmareProgress() {
		return nightmareProgress;
	}

	public void setNightmareProgress(String nightmareProgress) {
		this.nightmareProgress = nightmareProgress;
	}

	public String getUnionInstanceProgress() {
		return unionInstanceProgress;
	}

	public void setUnionInstanceProgress(String unionInstanceProgress) {
		this.unionInstanceProgress = unionInstanceProgress;
	}

	public String getServerOpenTime() {
		return serverOpenTime;
	}

	public void setServerOpenTime(String serverOpenTime) {
		this.serverOpenTime = serverOpenTime;
	}

	public String getChristmasInstanceProgress() {
		return christmasInstanceProgress;
	}

	public void setChristmasInstanceProgress(String christmasInstanceProgress) {
		this.christmasInstanceProgress = christmasInstanceProgress;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	public String getTotalGold() {
		return totalGold;
	}

	public void setTotalGold(String totalGold) {
		this.totalGold = totalGold;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCssportsGrade() {
		return cssportsGrade;
	}

	public void setCssportsGrade(String cssportsGrade) {
		this.cssportsGrade = cssportsGrade;
	}

	public String getCssportsRank() {
		return cssportsRank;
	}

	public void setCssportsRank(String cssportsRank) {
		this.cssportsRank = cssportsRank;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	@Override
	public String toString() {
		return "PlayerLogout [game=" + game + ", actionName=" + actionName + ", gameSvrId=" + gameSvrId + ", dtEventTime=" + dtEventTime
				+ ", vGameAppid=" + vGameAppid + ", platID=" + platID + ", iZoneAreaID=" + iZoneAreaID + ", vopenId=" + vopenId
				+ ", onlineTime=" + onlineTime + ", level=" + level + ", playerFriendsNum=" + playerFriendsNum + ", clientVersion="
				+ clientVersion + ", systemSoftware=" + systemSoftware + ", systemHardware=" + systemHardware + ", telecomOper="
				+ telecomOper + ", network=" + network + ", screenWidth=" + screenWidth + ", screenHight=" + screenHight + ", density="
				+ density + ", loginChannel=" + loginChannel + ", cpuHardware=" + cpuHardware + ", memory=" + memory + ", gLRender="
				+ gLRender + ", gLVersion=" + gLVersion + ", deviceId=" + deviceId + ", vipLevel=" + vipLevel + ", cardsNum=" + cardsNum
				+ ", isUnion=" + isUnion + ", unionLevel=" + unionLevel + ", unionId=" + unionId + ", normalProgress=" + normalProgress
				+ ", eliteProgress=" + eliteProgress + ", nightmareProgress=" + nightmareProgress + ", unionInstanceProgress="
				+ unionInstanceProgress + ", serverOpenTime=" + serverOpenTime + ", christmasInstanceProgress=" + christmasInstanceProgress
				+ ", rid=" + rid + ", roleName=" + roleName + ", type=" + type + ", gold=" + gold + ", totalGold=" + totalGold + ", uid="
				+ uid + ", cssportsGrade=" + cssportsGrade + ", cssportsRank=" + cssportsRank + ", platform=" + platform + ", dt=" + dt
				+ "]";
	}

	

}
