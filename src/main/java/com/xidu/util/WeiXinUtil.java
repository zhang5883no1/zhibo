package com.xidu.util;  

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.xidu.entity.Customer;
  
public class WeiXinUtil {
	protected static String APP_ID="wxe4f3c376dac42a79";
	protected static String SECRET="7e6e052650df666ba5d8a8cdcd413cae";
	
	public static Customer getInfo(String code){
		try {
			String access_tocken_url="https://api.weixin.qq.com/sns/oauth2/access_token?"
					+"appid="+APP_ID
					+"&secret="+SECRET
					+"&code="+code
					+"&grant_type=authorization_code";
			
			String accessResult=httpGet(access_tocken_url);
			System.out.println(accessResult);
			JSONObject json=new JSONObject(accessResult);
			String access_token=json.getString("access_token");
			String refresh_token=json.getString("refresh_token");
			String openid=json.getString("openid");
			String user_info_url="https://api.weixin.qq.com/sns/userinfo"
					+"?access_token="+access_token
					+"&openid="+openid
					+"&lang=zh_CN ";
			String userResult=httpGet(user_info_url);
			JSONObject ujson=new JSONObject(userResult);
			System.out.println(userResult);
			String nickname =StringUtil.replaceEmoji(ujson.getString("nickname")); 
			String headimgurl=ujson.getString("headimgurl");
			String uopenid=ujson.getString("openid");
			Customer cu=new Customer(nickname,headimgurl,uopenid);
//			Customer cu=new Customer(code,"img",code+"1234567");
			return cu;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	public static String httpGet(String url){
		URL u = null;
		HttpURLConnection con = null;
		// 构建请求参数
		System.out.println("send_url:" + url);
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			// // POST 只能为大写，严格限制，post会不识别
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			// 一定要有返回值，否则无法把请求发送给server端。
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(buffer.toString());
		return buffer.toString();
    }
	
	public static void main(String[] args) {
//		http://m.ddv.zhibo.china-shise.com/?code=041kMqxe2ZsBRC0ChKxe2rTHxe2kMqxl&state=STATE
//		http://m.ddv.zhibo.china-shise.com/?code=041IL0I80NeCwK1Q8EI80efIH80IL0IL&state=STATE
//		System.out.println(WeiXinUtil.getInfo("041kMqxe2ZsBRC0ChKxe2rTHxe2kMqxl"));
		System.out.println("♠️黑桃💓小皮蛋".replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", ""));
	}
}
