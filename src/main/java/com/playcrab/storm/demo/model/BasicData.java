package com.playcrab.storm.demo.model;

import java.sql.Date;

public class BasicData {
	private Integer id;
	private Date dt;
	private String game;
	private String platform;
	private String gamesvrid;
	private Integer viplevel;
	private Long dau;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
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

	public Long getDau() {
		return dau;
	}

	public void setDau(Long dau) {
		this.dau = dau;
	}

}
