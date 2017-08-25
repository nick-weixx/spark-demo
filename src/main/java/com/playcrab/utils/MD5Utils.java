package com.playcrab.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * MD5加密算法
 * 
 * @author suguoxin
 * @time 2015-04-22
 */
public class MD5Utils {
	private static Logger log = Logger.getLogger(MD5Utils.class);

	/**
	 * MD5加密
	 * 
	 * @param str
	 *            要进行加密的字符串
	 * @return 加密后的字符串
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			// System.out.println("NoSuchAlgorithmException caught!");
			// System.exit(-1);
			log.error("NoSuchAlgorithmException caught! ,ex: " + e);
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	@SuppressWarnings({ "static-access" })
	public static void main(String[] args) {
		System.out.println(new MD5Utils()
				.getMD5Str("playcrab_game_vip1429212345"));
	}
}
