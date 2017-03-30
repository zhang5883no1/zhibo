package com.xidu.util;

import java.security.MessageDigest;

public class MD5Util {

	/**
	 * 密码加密
	 * @param inStr
	 * @return
	 * @throws Exception
	 */
	public static String MD5Encrypt(String inStr) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5"); // 可以选中其他的算法如SHA
		byte[] digest = md.digest(inStr.getBytes()); // 返回的是byet[]，要转化为String存储比较方便
		String outStr = byteToString(digest);
		return outStr;
	}

	/**
	 * byteToString
	 * @param digest
	 * @return
	 * @throws Exception
	 */
	private static String byteToString(byte[] digest) throws Exception {
		String str = "";
		String tempStr = "";
		StringBuffer sb = new StringBuffer("");
		for (int i = 1; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				sb.append("0");
				sb.append(tempStr);
			} else {
				sb.append(tempStr);
			}
		}
		str = sb.toString().toUpperCase();
		return str;
	}
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return validZero(new String(str));
//            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	 private static String validZero(String s){
	    	String r="";
	    	for(int i=0;i<s.length();i+=2){
	    		String a=s.substring(i,i+2);
	    		if(a.substring(0,1).equals("0")){
	    			r+=a.substring(1,2);
	    		}else{
	    			r+=a;
	    		}
	    	}
	    	return r;
	    }
	public static void main(String[] args) throws Exception {
		System.out.println(MD5Util.MD5Encrypt("123456"));
		System.out.println(MD5Util.MD5("123456"));
	}
}
