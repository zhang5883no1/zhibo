package com.xidu.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.json.JSONObject;

public class BaseEntity implements Serializable{

	
	public JSONObject toJson(){
		JSONObject json=new JSONObject();
	    Class c=this.getClass();  
	        //得到对象中的所有方法  
	        Method[] ms=c.getMethods();  
	        //得到对象中所有的属性,虽然在这个里面就能获取所有的字段名，但不建议这么用，破坏类的封装性  
	        Field[]  fs=c.getDeclaredFields();  
	        //得到对象类的名字  
	        String cname=c.getName();  
	        //System.out.println("类名字： "+cname);  
	        //遍历方法  
	        for(Method m:ms){  
	             String methodName=m.getName();//获取每一个方法名  
	             //只得到具有get方法的属性，getClass除外  
	             if(methodName.startsWith("get")&&!methodName.startsWith("getClass")){  
	                 //System.out.println("属性名："+methodName);  
	                 String fieldName = methodName.substring(3, methodName.length());  
	                 //System.out.println("字段名："+fieldName); 
	                 try{  
	                     Object value=m.invoke(this, null); 
	                     //System.out.println("对应值:"+value);
	                     json.accumulate(fieldName.substring(0,1).toLowerCase()+fieldName.substring(1), value);
	                 }catch(Exception e){  
	                     e.printStackTrace();  
	                 }  
	                   
	             }  
	          
	        }  
		return json;
	}
}
