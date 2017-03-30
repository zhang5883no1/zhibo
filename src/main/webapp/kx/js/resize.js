var WidthLevel = {'S': 480, 'M': 598, 'P': 880, 'L': 1366, 'B': 1600};
var topiccontent = '.topiccontent';//聊天显示块选择器
var topicbox = '#topicbox';
var chatcontainer;
var scrollconf = {scrollButtons: {enable: true}};
function resize() {
    var windowHeight = $(window).height();
    var windowWidth = $(window).width();
    var headerHeight = $('header').height();
    var footerHeight = $('footer').height();
    var mmargin = mMargin();
    var $topic = $('#topic');
    var $main = $('#main');
    $main.height(windowHeight - headerHeight - footerHeight - mmargin * 2); //设置主体高度
    var noticeHeight = $('.notice').height();//房间通知跑马灯高度
    var topicinputHeight = $('#topicinput').height();  //聊天输入块高度
    var msgHeight = $('.msg').height();
    var qqbtsHeight = $('.qqbts').height();
    if (windowWidth > WidthLevel.P) {
        $topic.height($main.height()); //聊天模块高度
        $('.topiccontent').height($topic.height() - topicinputHeight - noticeHeight - mmargin * 2 - msgHeight - qqbtsHeight - 20); //聊天显示块高度
    } else if (windowWidth <= WidthLevel.M) {
        if (isPhone) {
            $main.height(windowHeight);
            $topic.css('margin-top', '30px');
        } else {
            $main.height(windowHeight - headerHeight); //设置主体高度
        }

        $topic.height($main.height() - $('.video').height()); //聊天模块高度 
        $('.topiccontent').height($topic.height() - topicinputHeight - mmargin * 2); //聊天显示块高度
    } else {
        $topic.height($main.height() - $('#sidebar').height()); //聊天模块高度
        $('.topiccontent').height($topic.height() - topicinputHeight - noticeHeight - mmargin * 2 - 20); //聊天显示块高度
    }
    var liveHeight = $('.emb').height() + $('.title').height() ; //视频高度

    $('.tab').height($topic.height() - liveHeight - mmargin * 2-3);//

    $('.box').height($('.tab').height() - 38);
}

function mMargin() {//大屏间距10  小屏间距5
    var windowWidth = $(window).width();
    return windowWidth > WidthLevel.B ? 5 : 5;
}
$(function(){
    $(window).resize(function() {
        resize();
    });
    resize();
    //实例化聊天窗
    chatcontainer = new ChatContainer();
    chatcontainer.create(topiccontent, topicbox, 50);
})