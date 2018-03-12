/*create by code_bunny 20140701
  973295131@qq.com
  https://github.com/OOP-Code-Bunny
*/
(function ($) {
    $.fn.autoComplate = function (opts) {
        this.each(function () {
            init.call(this, opts);
        });
        return this;
    };

    function init(opts) {
        var defaultOption = {
            textInput: $(this),            //文本输入框
            emailBox: $(this).next(),      //用于放置联想邮箱的框
            links:['163.com','qq.com','126.com','gmail.com','yeah.net','hotmail.com','sina.com','sina.cn','sohu.com']  //联想内容,
        };

        var options = $.extend(defaultOption, opts);

        var func = {};

        func.init = function(){
            this.timeOut = null;
            this.cur = null;
            this.emailList = [];
            this.aUl = $('<ul>');
            for(var i=0; i<this.links.length; i++){
                var aLi = $('<li>');
                this.emailList.push(aLi);
                this.aUl.append(aLi);
                aLi.bind('mousedown',func.chooseLi.bind(options));
            }
            this.emailBox.append(this.aUl);
            this.textInput.bind('keydown',func.keyDownFun.bind(this)).bind('keyup',func.keyUpFun.bind(this)).bind('blur',func.textBlur.bind(this));
            return this;
        };

        func.chooseLi = function(e){
            this.textInput.val($(e.target).text());
        };

        func.keyDownFun = function(e){
            func.clearTO.call(this);
            if(e.keyCode==13){
                return func.pressEnter.call(this);
            }
            else if (e.keyCode==40) {
                return func.upAndDown.apply(this,[true]);
            }
            else if(e.keyCode==38){
                return func.upAndDown.apply(this,[false]);
            }
            this.timeOut = setTimeout(func.timeout.bind(this),0)
        };

        func.keyUpFun = function(e){
            func.clearTO.call(this);
            if (func.ifNotText.apply(this, [e.keyCode])) return;
            this.timeOut = setTimeout(func.timeout.bind(this),0)
        };

        func.pressEnter = function(){
            return (this.cur === null) ?  func.noCurPressEnter.call(this) : func.curPressEnter.call(this)
        };

        func.noCurPressEnter = function(){
            return (this.textInput.val().indexOf('@')>0 && this.textInput.val().indexOf('@')<this.textInput.val().length-1) ? this.textInput.blur() : this.textInput.val(this.emailList[0].text()).blur();
        };

        func.curPressEnter = function(){
            return this.textInput.val(this.emailBox.find('li:visible').eq(this.cur).text()).blur();
        };

        //上下键函数,参数表示上下,true为下,false为上
        func.upAndDown = function(direct){
            this.length = this.emailBox.find('li:visible').length;
            var start = direct ? 0 : this.length-1;
            if(this.cur === null) {
                this.cur = start;
                this.emailBox.find('li:visible').eq(this.cur).addClass('cur');
            }
            else {
                func.removeCur.call(this);
                var end = direct ? options.length-1 : 0;
                this.cur = this.cur == end ? start : (direct ? this.cur+1 : this.cur-1);
                this.emailBox.find('li:visible').eq(this.cur).addClass('cur');
            }
        };

        func.timeout = function(){
            return this.textInput.val() == '' ? this.emailBox.hide() : func.match.apply(this,[this.textInput.val()])
        };

        func.ifNotText = function(num){
            return num == 13 || num == 40 || num == 38
        };

        func.match = function(content){
            func.removeCur.call(this).emailBox.show();
            this.cur = null;
            var contentPrv = content.split('@')[0];
            var contentNext = content.split('@')[1] || '';
            for(var i=0; i<this.links.length; i++){
                this.emailList[i] = this.links[i].indexOf(contentNext)!=0 ? this.emailList[i].text(contentPrv+'@'+this.links[i]).hide() : this.emailList[i].text(contentPrv+'@'+this.links[i]).show();
            }
        };

        func.textBlur = function(){
            func.clearTO.call(this);
            this.cur = null;
            func.removeCur.call(this);
            this.emailBox.hide();
            return this
        };

        func.removeCur = function(){
            $.each(this.emailList,function(){
                $(this).removeClass('cur')
            });
            return this
        };

        func.clearTO = function(){
            clearTimeout(this.timeOut);
            this.timeOut = null;
        };

        func.bindFocus = function(){
            this.textInput.bind('keydown',func.keyDownFun.bind(this)).bind('keyup',func.keyUpFun.bind(this));
        };

        func.init.call(options);
    }
})(jQuery);