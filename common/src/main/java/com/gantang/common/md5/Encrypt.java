package com.gantang.common.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	private boolean checkPassword(String str_pwds, String str_passwd) {
		boolean isValid = false;
		try {
			String myinfo = str_pwds + "0";
			MessageDigest alga = MessageDigest.getInstance("MD5");
			alga.update(myinfo.getBytes());
			byte[] digesta = alga.digest();
			String ls_str = byte2hex(digesta);

			isValid = ls_str.equals(str_passwd);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("没有这个加密算法请检查JDK版本，错误描述：" + e.getMessage());
		}

		return isValid;
	}

	private String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else {
				hs = hs + stmp;
			}
			if (n < b.length - 1) {
				//hs = hs;
			}
		}

		return hs.toUpperCase();
	}

	public String addPwd(String str_pwds) {
		try {
			String myinfo = str_pwds + "0";
			MessageDigest alga = MessageDigest.getInstance("MD5");

			alga.update(myinfo.getBytes());
			byte[] digesta = alga.digest();
			String ls_str = byte2hex(digesta);
			return ls_str;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("没有这个加密算法请检查JDK版本，错误描述：" + e.getMessage());
		}

		return null;
	}

	public String passwordEncrypt(String str_pwds) {
		return addPwd(str_pwds);
	}

	public boolean isPass(String str_pwds, String str_passwd) {
		return checkPassword(str_pwds, str_passwd);
	}

	public static String md5(String paramString) {
		String str = null;

		if (paramString == null)
			return "";
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			byte[] arrayOfByte = localMessageDigest.digest(paramString
					.getBytes());
			str = Hex.toString(arrayOfByte);
		} catch (Exception localException) {
			System.out.println("加密错误描述：" + localException.getMessage());
		}
		return str;
	}
	
	public static void main(String[] args) {
		String md5 = md5("888888");
		System.out.println(md5);
	}
}
