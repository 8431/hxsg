/////////////////////////////////////////////////
// tree排序 
// 1) 按文本字段排序？
// 2) var sorter = new TreeSort(tree);
/////////////////////////////////////////////////

var TreeSort = function (tree) {
    var me = this;
    me.tree = tree;

    tree.on("headercellclick", function (e) {
        
        var column = e.column;

        if (e.column.allowSort) {

            var field = column.field;
            var dir = "asc";


            if (field == me.sortField) {
                if (me.sortOrder == "desc") {
                    me.clearSort();
                    return;
                }
                dir = me.sortOrder == "asc" ? "desc" : "asc";
            }
                        
            me.sort(field, dir);
        }

    });

    tree.on("update", function () {
        me.syncTreeSortIcon();
    });

}

TreeSort.prototype = {

    sortField: undefined,
    sortOrder: undefined,

    clearSort: function () {
        this.sortField = null;
        this.sortOrder = null;

        //this.syncTreeSortIcon();

        this.tree.clearSort();
    },

    sort: function (field, dir) {
        var me = this;

        var column = me.getColumnByField(field);
        if (!column) return;

        this.sortField = field;
        this.sortOrder = dir || "asc";

        //this.syncTreeSortIcon();

        var sortType = me.getSortType(field);
        var typeFn = mini.sortTypes[sortType];
        var reverse = dir == "desc";

        //alert(sortType + ":" + reverse);

        
        this.tree.sort(function (a, b) {
            var a1 = a[field],
                b1 = b[field];

            var nullA = mini.isNull(a1) || a1 === "";
            var nullB = mini.isNull(b1) || b1 === "";
            if (nullA) return 0;
            if (nullB) return 1;

            var v1 = typeFn(a1);
            var v2 = typeFn(b1);
            if (v1 > v2) return 1;      
            else return 0;

        }, null, reverse);

    },

    getColumnByField: function (field) {
        var me = this,
            tree = me.tree,
            columns = tree.getBottomColumns();

        for (var i = 0, l = columns.length; i < l; i++) {
            var col = columns[i];
            if (col.field == field) return col;
        }
    },

    getSortType: function (field) {
        var me = this;
        var column = me.getColumnByField(field);
        var sortType = column.sortType || column.dataType;

        if (!sortType) {
            sortType = "string";
            var data = me.tree.getList();
            for (var i = 0, l = data.length; i < l; i++) {
                var o = data[i];
                var val = o[field];
                if (!mini.isNull(val)) {
                    if (mini.isNumber(val)) {
                        sortType = "int";
                    } else if (mini.isDate(val)) {
                        sortType = "date";
                    }
                    break;
                }
            }
        }
        return sortType;
    },

    syncTreeSortIcon: function () {
        var me = this,
            tree = me.tree,
            columns = tree.getBottomColumns(),
            sortField = me.sortField,
            sortOrder = me.sortOrder;

        function createHeaderCellId(column, index) {
            var id = tree._id + "$headerCell" + index + "$" + column._id;
            return id;
        }

        function getHeaderCellEl(column) {
            var el = document.getElementById(createHeaderCellId(column, 1));
            if (!el) el = document.getElementById(createHeaderCellId(column, 2));
            return el;
        }



        function syncSortIcon() {

            $(tree.el).find(".mini-grid-sortIcon").remove();

            var column = me.getColumnByField(sortField);
            if (!column) return;
            var el = getHeaderCellEl(column);
            if (!el) return;

            if (sortField) {
                var sortCls = sortOrder == "asc" ? "mini-grid-asc" : "mini-grid-desc";
                $(el).removeClass("mini-grid-asc mini-grid-desc").addClass(sortCls);
                $(el).find(".mini-grid-headerCell-inner").append('<span class="mini-grid-sortIcon"></span>');
            }
        }

        syncSortIcon();
    }

};