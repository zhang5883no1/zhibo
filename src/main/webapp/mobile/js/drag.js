/* 
 * drag 1.0
 * create by tony@jentian.com
 * date 2015-08-18
 * 拖动滑块
 */
(function($){
        
    $.fn.huakuai = function(options){
        var x,timestampDown,huakuai = this, isMove = false, defaults = {
        };
        var options = $.extend(defaults, options);
        //添加背景，文字，滑块
        var html = '<div class="drag_bg"></div>'+
                    '<div class="drag_text" onselectstart="return false;" unselectable="on">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;拖动滑块，向右边滑动</div>'+
                    '<div class="handler handler_bg"></div>';
        this.append(html);
        
        var handler = huakuai.find('.handler');
        var drag_bg = huakuai.find('.drag_bg');
        var text = huakuai.find('.drag_text');
        var maxWidth = huakuai.width() - handler.width();  //能滑动的最大间距
        
        //鼠标按下时候的x轴的位置
        /**handler.mousedown(function(e){
            isMove = true;
            x = e.pageX - parseInt(handler.css('left'), 10);   //鼠标按下时记录x轴的位置
            timestampDown = new Date().getTime();    //获取当鼠标按下时的时间戳
        });
        
        //鼠标指针在上下文移动时，移动距离大于0小于最大间距，滑块x轴位置等于鼠标移动距离
        $(document).vmousemove(function(e){
            var _x = e.pageX - x;       //鼠标当前的x轴位置
            if(isMove){
                if(_x > 0 && _x <= maxWidth){   //鼠标指针没有移动距离到达最大
                    handler.css({'left': _x});
                    drag_bg.css({'width': _x});
                }else if(_x > maxWidth && _x < (maxWidth+10)){
                     dragOk();
                     yanzheng = "ok";
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
        });*/
        
		$(document).on ( "vmousedown", ".handler", function(e) {
			isMove = true;
            x = e.pageX - parseInt(handler.css('left'), 10);   //鼠标按下时记录x轴的位置
            timestampDown = new Date().getTime();    //获取当鼠标按下时的时间戳
		});

		$(document).on ( "vmousemove", ".handler", function(e) {
			var _x = e.pageX - x;       //鼠标当前的x轴位置
            if(isMove){
                if(_x > 0 && _x <= maxWidth){   //鼠标指针没有移动距离到达最大
                    handler.css({'left': _x});
                    drag_bg.css({'width': _x});
                }else if(_x > maxWidth && _x < (maxWidth+10)){
                     dragOk();
                     yanzheng = "ok";
                }
            }
		});

		$(document).on ( "vmouseup", ".handler", function(e) {
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
            handler.removeClass('handler_bg').addClass('handler_ok_bg');
            text.text('手机验证码发送成功');
            huakuai.css({'color': '#fff'});
            $(document).off ( "vmouseup", ".handler");
            $(document).off ( "vmousedown", ".handler");
            $(document).off ( "vmousemove", ".handler");
        }
    };
})(jQuery);
