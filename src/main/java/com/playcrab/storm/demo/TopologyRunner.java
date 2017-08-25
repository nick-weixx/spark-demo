package com.playcrab.storm.demo;

import com.playcrab.storm.demo.dau.DauRunner;

public class TopologyRunner {
	public static void main(String[] args) {
		if (args.length == 2 && ("true".equals(args[0]) || "false".equals(args[0]))) {
			DauRunner dau = new DauRunner(Boolean.valueOf(args[0]), args[1]);
			dau.run();
		} else {
			System.out.println("参数错误...");
		}

	}
}
