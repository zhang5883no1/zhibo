package com.xidu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {  
    HttpServletRequest orgRequest = null;  
  
    public XssHttpServletRequestWrapper(HttpServletRequest request) {  
        super(request);  
        orgRequest = request;  
    }  
  
    /** 
    * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/> 
    * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
    */  
    @Override  
    public String getParameter(String name) {  
        String value = super.getParameter(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    }  
    
    @Override
    public String getAttribute(String name){
    	String value = super.getParameter(name);  
        return value;  
    }
    /** 
    * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> 
    * getHeaderNames 也可能需要覆盖 
    */  
    @Override  
    public String getHeader(String name) {  
  
        String value = super.getHeader(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    }  
  
    /** 
    * 将容易引起xss漏洞的半角字符直接替换成全角字符 
    * 
    * @param s 
    * @return 
    */  
    private static String xssEncode(String s) {  
        if (s == null || "".equals(s)) {  
            return s;  
        }  
        StringBuilder sb = new StringBuilder(s.length() + 16);  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            switch (c) {  
            case '>':  
                sb.append('＞');//全角大于号  
                break;  
            case '<':  
                sb.append('＜');//全角小于号  
                break;  
            case '\'':  
                sb.append('‘');//全角单引号  
                break;  
            case '\"':  
                sb.append('“');//全角双引号  
                break;  
            case '&':  
                sb.append('＆');//全角  
                break;  
            case '\\':  
                sb.append('＼');//全角斜线  
                break;  
            case '#':  
                sb.append('＃');//全角井号  
                break;  
            default:  
                sb.append(c);  
                break;  
            }  
        } 
        return cleanSQLInject(cleanXSS(sb.toString()));  
        
    }  
    
    public static String cleanXSS(String src) {  
        String temp =src;  
  
//        System.out.println("xss---temp-->"+src);  
        src = src.replaceAll("<", "<").replaceAll(">", ">");  
       // if (src.indexOf("address")==-1)  
    //  {  
          src = src.replaceAll("\\(", "(").replaceAll("\\)", ")");  
        //}  
       
        src = src.replaceAll("'", "'");  
          
        Pattern pattern=Pattern.compile("(eval\\((.*)\\)|script)",Pattern.CASE_INSENSITIVE);     
        Matcher matcher=pattern.matcher(src);     
        src = matcher.replaceAll("");  
  
        pattern=Pattern.compile("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']",Pattern.CASE_INSENSITIVE);   
        matcher=pattern.matcher(src);  
        src = matcher.replaceAll("\"\"");  
          
        src = src.replaceAll("script", "").replaceAll(";", "")  
            .replaceAll("\"", "")
            .replaceAll("0x0d", "")  
            .replaceAll("0x0a", "").replaceAll(",", "");  
  
        if(!temp.equals(src)){  
//            System.out.println("输入信息存在xss攻击！");  
//            System.out.println("原始输入信息-->"+temp);  
//            System.out.println("处理后信息-->"+src);  
        }  
        return src;  
        
        
    }  
      
    public static String cleanSQLInject(String src) {  
        String temp =src;  
        src = src.replaceAll("(?i)insert", "forbidI")  
            .replaceAll("(?i)select", "forbidS")  
            .replaceAll("(?i)update", "forbidU")  
            .replaceAll("(?i)delete", "forbidD")  
            .replaceAll("(?i)and", "forbidA")  
            .replaceAll("(?i)or", "forbidO");  
          
        if(!temp.equals(src)){  
            System.out.println("输入信息存在SQL攻击！");  
            System.out.println("原始输入信息-->"+temp);  
            System.out.println("处理后信息-->"+src);  
        }  
        return src;  
    }  
    /** 
    * 获取最原始的request 
    * 
    * @return 
    */  
    public HttpServletRequest getOrgRequest() {  
        return orgRequest;  
    }  
  
    /** 
    * 获取最原始的request的静态方法 
    * 
    * @return 
    */  
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {  
        if (req instanceof XssHttpServletRequestWrapper) {  
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();  
        }  
  
        return req;  
    }  
  
}  