///////////////////////////////////////////////////////////////////////////////
//扩展：自定义高度
//////////////////////////////////////////////////////////////////////////////
var fixheight_prototype = {
    doLayout: function () {
        if (!this.canLayout()) return;

        if (this._noLayout && this._doInputLayout) {
            this._doInputLayout(false);
        }

        if (this._doLabelLayout) {
            this._labelLayout();
        }

        if (this._heightChanged) {
            this._heightChanged = false;

            var h = mini.getHeight(this.el);
            mini.setHeight(this._borderEl, h);
            h -= 2;
            if (h < 0) h = 0;
            this._textEl.style.height = h + "px";
        }

    },
    setHeight: function (value) {
        if (parseInt(value) == value) value += "px";
        this.height = value;

        if (value != "21px") {
            this._textEl.style["line-height"] = (parseInt(value) - 2) + "px";
            this.el.style.height = value;
            this._heightChanged = true;
            mini.addClass(this.el, "mini-buttonedit-height");
            this.doLayout();
        }
    }
};


$.extend(mini.TextBox.prototype, fixheight_prototype);
$.extend(mini.Password.prototype, fixheight_prototype);
$.extend(mini.ButtonEdit.prototype, fixheight_prototype);
$.extend(mini.PopupEdit.prototype, fixheight_prototype);
$.extend(mini.ComboBox.prototype, fixheight_prototype);
$.extend(mini.TreeSelect.prototype, fixheight_prototype);
$.extend(mini.DatePicker.prototype, fixheight_prototype);
$.extend(mini.FileUpload.prototype, fixheight_prototype);
$.extend(mini.HtmlFile.prototype, fixheight_prototype);
$.extend(mini.Lookup.prototype, fixheight_prototype);
$.extend(mini.Spinner.prototype, fixheight_prototype);
$.extend(mini.TimeSpinner.prototype, fixheight_prototype);
$.extend(mini.AutoComplete.prototype, fixheight_prototype);