
var ColumnsMenu = function (grid) {

    this.grid = grid;
    this.menu = this.createMenu();
    this.currentColumn = null;

    this.menu.on("beforeopen", this.onBeforeOpen, this);
    grid.setHeaderContextMenu(this.menu);

}

ColumnsMenu.prototype = {

    createMenu: function () {
        var grid = this.grid;

        //创建菜单对象
        var menu = mini.create({ type: "menu", hideOnClick: false });

        //创建自定义菜单列
        var items = [
            { text: "配置列", name: "setcolumn" },
            { text: "锁定/解锁列", name: "frozencolumn" },
            '-'
        ];

        //创建隐藏菜单列
        var columns = grid.getBottomColumns();
        var columnMenuItems = { text: "隐藏列", name: "showcolumn" };
        columnMenuItems.children = [];
        for (var i = 0, l = columns.length; i < l; i++) {
            var column = columns[i];
            if (column.hideable) continue;
            var item = {};
            item.checked = column.visible;
            item.checkOnClick = true;
            item.text = column.header;
            if (item.text == "&nbsp;") {
                if (column.type == "indexcolumn") item.text = "序号";
                if (column.type == "checkcolumn") item.text = "选择";
            }
            item.enabled = column.enabled;
            item._column = column;
            columnMenuItems.children.push(item);
        }
        items.push(columnMenuItems);
        menu.setItems(items);

        menu.on("itemclick", this.onMenuItemClick, this);

        return menu;
    },
    onBeforeOpen: function (e) {
        var grid = this.grid;
        var column = grid.getColumnByEvent(e.htmlEvent);
        this.currentColumn = column;
    },
    onMenuItemClick: function (e) {
        var grid = this.grid;
        var menu = e.sender;
        var columns = grid.getBottomColumns();
        var items = menu.getItems();
        var item = e.item;
        var targetColumn = item._column;
        var currentColumn = this.currentColumn;

        //自定义菜单项的事件绑定
        if (item.name == "setcolumn") {

            alert("列：" + currentColumn.field);
            return
        }

        if (item.name == "frozencolumn") {
            var frozenEndColumn = grid.getFrozenEndColumn()
            if (!currentColumn.visible) return
            if (frozenEndColumn != -1) {
                grid.unFrozenColumns();

            } else {
                grid.frozenColumns(0, (currentColumn._index - 1));
            }
            return
        }

        if (item.name == "showcolumn") return;

        //显示/隐藏列
        if (targetColumn) {
            
            //确保起码有一列是显示的
            var checkedCount = 0;
            var columnsItem = mini.getbyName("showcolumn", menu);
            var childMenuItems = columnsItem.menu.items;
            for (var i = 0, l = childMenuItems.length; i < l; i++) {
                var it = childMenuItems[i];
                if (it.getChecked()) checkedCount++;
            }
            if (checkedCount < 1) {
                item.setChecked(true);
            }

            //显示/隐藏列
            if (item.getChecked()) grid.showColumn(targetColumn);
            else grid.hideColumn(targetColumn);
        }

    }

};



