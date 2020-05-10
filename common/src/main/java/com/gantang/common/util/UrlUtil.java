package com.gantang.common.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Description Url
 * @author Lingqinyu
 * @date 2018��11��21������5:40:27
 */
public class UrlUtil {
	
	
	/**
	 *
	 * @Description ����URL�ַ��������ӿ�
	 * @param urlString
	 * @return 
	 * @return String
	 * @author Lingqinyu
	 * @date 2018��11��21������5:40:45
	 */
	public static String loadUrl(String urlString) {
		StringBuilder json = new StringBuilder();
		try {
			URL url = new URL(urlString);
			URLConnection urlCnn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCnn.getInputStream(), "utf-8"));// ��ֹ����
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
	/**
	 *
	 * @Description 获取access_token
	 * @return void
	 * @author Lingqinyu
	 * @throws Exception 
	 * @date 2018��11��21������7:00:37
	 */
	public static void getAccessToten() throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx3f1ad5fce7e196df&secret=8d2b53f12b02f7fd2d608563e43c1f44";
		JSONObject json = JSONObject.parseObject(loadUrl(url));
		String accessToken = json.getString("access_token");
		String expiresIn = json.getString("expires_in"); // 过期时间
		if(accessToken == null){
			String errcode = json.getString("errcode");
			String errmsg = json.getString("errmsg");
			String errorMes = "获取access_token：错误码errcode:"+errcode+" 错误信息errMsg:"+errmsg;
			throw new Exception(errorMes);
		}
	}
}
