var _czc = _czc || [];
$(function(){ 
	_czc.push(["_setAccount", "1259465169"]);
	$(".qqbts").find("a").on('click',function(){
		_czc.push(﻿["_trackEvent","聊天下方营销qq","click","qq"+$(this).index()]);
	});
	
	$(".alertqq").find("a").on('click',function(){
		_czc.push(﻿["_trackEvent","页面加载广告营销qq","click","qq"+$(this).index()]);
	});
	
	$("#is_look p").find("a").on('click',function(){
		_czc.push(﻿["_trackEvent","5分钟广告营销qq","click","qq"+$(this).index()]);
	});
	
	$("#sub_info_btn").on('click',function(){
		_czc.push(﻿["_trackEvent","发送聊天","click",""]);
	});
	
	_czc.push(["_setCustomVar","等级",customer.level,2]);
 });

