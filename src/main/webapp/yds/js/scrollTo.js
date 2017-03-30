function ChatContainer(){
    this.maxNum;
    this.container;
    this.scroolwrap;
    this.tabType; //public member private
    this.dynamicscroll;
    this.create = function(scroolwrap,containerid,max){
        this.privateNum = 0;
        this.publicNum = 0;
        this.maxNum = max;
        this.tabType = 'public';
        this.container = $(containerid);
        this.scroolwrap = $(scroolwrap);
        this.dynamicscroll =true;   

        this.scroolwrap.mCustomScrollbar(scrollconf);
    };
    
    this.push = function(msgItem){      
        this.container.append(msgItem);
        this.scrollToLast();
    }
    //滚动到底部
    this.scrollToLast = function(){
        this.scroolwrap.mCustomScrollbar("update");
        if(this.dynamicscroll){
            this.scroolwrap.mCustomScrollbar("scrollTo","bottom");
        }   
    }
}