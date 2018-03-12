if (!window.UserControl) window.UserControl = {};

// 实现下拉弹出层的工具栏

UserControl.ToolBarOverflow = function () {

    UserControl.ToolBarOverflow.superclass.constructor.apply(this, arguments);

    this.initComponents();
    this.bindEvents();
}

mini.extend(UserControl.ToolBarOverflow, mini.Container, {

    uiCls: 'uc-toolbaroverflow',

    _clearBorder: false,

    popupVisible: false,

    _create: function () {
        this.el = document.createElement("div");
        this.el.className = "uc-toolbar mini-toolbar";
        this.el.innerHTML = '<div class="uc-toolbar-inner"><div class="uc-toolbar-content"></div></div><div class="uc-toolbar-arrow "></div>';
        this._innerEl = this.el.firstChild;
        this._contentEl = this._innerEl.firstChild;

        this._arrowEl = this.el.childNodes[1];
        this._popupEl = mini.append(document.body, '<div class="uc-toolbar-popup mini-toolbar"></div>');
    },

    initComponents: function () {

    },

    bindEvents: function () {
        var me = this;

        jQuery(me._arrowEl).bind("click", function () {

            if (me.popupVisible) {
                me.hidePopup();
            } else {
                me.showPopup();
            }
        });

        jQuery(document).bind("mouseup", function (e) {

            if (me.popupVisible) {
                if (!mini.isAncestor(me._popupEl, e.target) && e.target != me._arrowEl) {
                    me.hidePopup();
                }
            }
        });

    },

    doLayout: function () {
        if (!this.canLayout()) return;

        //document.title = "layout:" + new Date().getTime();

        var me = this,
            innerEl = me._innerEl,
            contentEl = me._contentEl,
            popupEl = me._popupEl,
            innerBox = mini.getBox(innerEl),
            popupBox = mini.getBox(popupEl),
            contentNodes = mini.getChildNodes(contentEl, true),
            popupNodes = mini.getChildNodes(popupEl, true);

        var toPopup = false;

        //1) 超过InnerBox.right则加入到popupEl中
        var lastBox = null;
        for (var i = contentNodes.length - 1; i >= 0; i--) {
            var node = contentNodes[i];

            if (node.nodeType != 1) {
                if (toPopup) mini.prepend(popupEl, node);

            } else {
                var nodeBox = mini.getBox(node);

                if (!lastBox) lastBox = nodeBox;

                if (nodeBox.right <= innerBox.right) break;

                mini.prepend(popupEl, node);
                toPopup = true;
            }



        }
        if (!lastBox) lastBox = { right: innerBox.left };

        //2) toPopup为false时，将部分popupNodes中节点加回contentEl
        var addNodes = [];
        if (toPopup == false) {

            var preTextNode = null;
            for (var i = 0, l = popupNodes.length; i < l; i++) {
                var node = popupNodes[i];
                if (node.nodeType != 1) {
                    preTextNode = node;
                } else {
                    var nodeBox = mini.getBox(node);
                    nodeBox.right = nodeBox.right - popupBox.left + lastBox.right;
                    if (nodeBox.right > innerBox.right) break;

                    if (preTextNode) addNodes.push(preTextNode);
                    preTextNode = null;

                    addNodes.push(node);
                }
            }

            for (var i = 0, l = addNodes.length; i < l; i++) {
                var node = addNodes[i];
                contentEl.appendChild(node);
            }
        }

        var childNodes = mini.getChildNodes(popupEl);
        if (childNodes.length > 0) {
            this._arrowEl.style.display = "block";
            //if (this.popupVisible) this.syncPopup();
        } else {
            this._arrowEl.style.display = "none";
            //this.hidePopup();
        }
        this.hidePopup();
    },

    syncPopup: function () {
        var box = mini.getBox(this.el);

        //        this._popupEl.style.top = "0px";
        //        this._popupEl.style.left = "0px";

        //        alert(this._popupEl.offsetWidth);
        //        alert(this._popupEl.offsetWidth);

        var pbox = mini.getBox(this._popupEl);
        var x = box.right - pbox.width,
            y = box.bottom;

        if (x < 0) x = 0;

        mini.setXY(this._popupEl, x, y);
    },

    showPopup: function () {

        this.popupVisible = true;

        this.syncPopup();

    },

    hidePopup: function () {
        this._popupEl.style.left = "-10000px";
        this._popupEl.style.top = "-10000px";
        this.popupVisible = false;
    },

    getAttrs: function (el) {
        var attrs = UserControl.ToolBarOverflow.superclass.getAttrs.call(this, el);

        var cs = mini.getChildNodes(el, true);

        attrs.controls = cs;

        return attrs;
    }

});

mini.regClass(UserControl.ToolBarOverflow, "toolbaroverflow");