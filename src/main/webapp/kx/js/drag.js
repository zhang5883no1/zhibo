/* 
 * drag 1.0
 * create by tony@jentian.com
 * date 2015-08-18
 * 拖动滑块
 */
 var validF = false;
var scrolf = true;
(function($){
	
    $.fn.huakuai = function(options){
        var x,timestampDown,huakuai = this, isMove = false, defaults = {
        };
        var options = $.extend(defaults, options);
        //添加背景，文字，滑块
        var html = '<div class="drag_bg"></div>'+
                    '<div class="drag_text" onselectstart="return false;" unselectable="on">拖动滑块 , 向右边滑动</div>'+
                    '<div class="handler handler_bg"></div>';
        this.append(html);
        
        var handler = huakuai.find('.handler');
        var drag_bg = huakuai.find('.drag_bg');
        var text = huakuai.find('.drag_text');
        var maxWidth = huakuai.width() - handler.width();  //能滑动的最大间距
        
        //鼠标按下时候的x轴的位置
        handler.mousedown(function(e){
            isMove = true;
            x = e.pageX - parseInt(handler.css('left'), 10);   //鼠标按下时记录x轴的位置
			timestampDown = new Date().getTime();    //获取当鼠标按下时的时间戳
        });
        
        //鼠标指针在上下文移动时，移动距离大于0小于最大间距，滑块x轴位置等于鼠标移动距离
        $(document).mousemove(function(e){
            var _x = e.pageX - x;       //鼠标当前的x轴位置        
            if(isMove&&scrolf){
                if(_x > 0 && _x <= maxWidth){   //鼠标指针没有移动距离到达最大
                    handler.css({'left': _x});
                    drag_bg.css({'width': _x});
                }else if(_x > maxWidth && _x < (maxWidth+10)){
                	dragOk();
                }
            }
        }).mouseup(function(e){
            isMove = false;
            var _x = e.pageX - x;
			var timestampUp = new Date().getTime();   //获取当鼠标松开时的时间戳
            if(_x < maxWidth || (timestampUp - timestampDown) < 600 || _x > (maxWidth+10)){ //鼠标松开时，如果没有达到最大距离位置并且时间小于600毫秒，滑块就返回初始位置
                handler.css({'left': 0});
                drag_bg.css({'width': 0});
            }
        });
        
        //清空事件
        function dragOk(){
        	scrolf=false;
        	var mobile = $("#mobile").val();
        	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
        	if(mobile==""||mobile=="12345678900"){
        		text.text("请重新输入手机号码");
        		validSendMsg();
    		}else if(reg.test(mobile)){
    				$.ajax({
        				url:"../controller/codeService;jsessionid="+sessionId,
        				type:"post",
        				dataType:"text",
        				contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
        				data:{"mobile":mobile,"roomNo":roomNo},      //encodeURI(xxx) 编码
        				success:function(res){ //1:验证码已发送  2:短信发送失败  3:手机号码已注册过  4:短信一天发送次数超过5次  5:短信获取间隔小于5分钟  6:手机号码格式不正确
        					if(res == "1"){
        						text.text("验证码已发送");
        						yanzheng = "ok";
        						validF = true;
        					}else if(res == "2"){
        						text.text("短信发送失败 ");
        					}else if(res == "3"){
        						text.text("手机号码已注册过");
        					}else if(res == "4"){
        						text.text("短信一天发送次数超过5次");
        					}else if(res == "5"){
        						text.text("短信获取间隔小于5分钟");
        					}else if(res == "6"){
        						text.text("手机号码格式不正确");
        					}
        					validSendMsg();
        				}
                 });
    		}else{
    			text.text("请重新输入手机号码");
    			validSendMsg();
    		}
        }
        
        
        function validSendMsg(){
        	scrolf = true;
        	if(validF){
        		handler.css({'left': 256});
        		drag_bg.css({'width': 256});
        		handler.removeClass('handler_bg').addClass('handler_ok_bg');
        		huakuai.css({'color': '#fff'});
        		handler.unbind('mousedown');
                $(document).unbind('mousemove');
                $(document).unbind('mouseup');
        		return;
        	}
        	handler.css({'left': 0});
        	drag_bg.css({'width': 0});
        }
    };
})(jQuery);

