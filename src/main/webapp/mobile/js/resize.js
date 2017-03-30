var WidthLevel = {'S': 480, 'M': 598, 'P': 890, 'L': 1366, 'B': 1600};
var topiccontent = '.topiccontent';//聊天显示块选择器
var topicbox = '#topicbox';
var chatcontainer;
var scrollconf = {scrollButtons: {enable: true}};
function resize() {
    var windowHeight = $(window).height();
    var windowWidth = $(window).width();
    var mmargin = mMargin();
    var $topic = $('#topic');
    var $main = $('#main');
    $main.height(windowHeight); //设置主体高度
    var topicinputHeight = $('#topicinput').height();  //聊天输入块高度
    var btnHeight = $('.btnBox').height();
    $(".topicMain").height($topic.height());
    $('.topiccontent').height($topic.height()  - topicinputHeight - 2) //聊天显示块高度
}
function mMargin() {
    var windowWidth = $(window).width();
    return windowWidth > WidthLevel.B ? 0 : 0;
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