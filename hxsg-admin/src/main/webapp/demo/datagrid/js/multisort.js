/////////////////////////////////////////////////
// 多级排序 
// 1) grid:     allowSortColumn="false"
// 2) var sorter = new MultiSort(grid);
/////////////////////////////////////////////////

var MultiSort = function (grid) {
    var me = this;
    me.grid = grid;
    me.sortFields = [];

    grid.on("headercellclick", function (e) {
        //alert(e.column.field + ":" + e.column.allowSort);

        var column = e.column,
                    field = column.field;

        if (column.allowSort) {

            var o = me.getSort(field);
            if (!o) {
                o = { field: field, dir: "asc" };
            } else {
                if (o.dir == "asc") o.dir = "desc";
                else {
                    me.removeSort(o);
                    return;
                }
            }
            me.addSort(o);

        }
    });

    grid.on("update", function () {
        me.syncGridSortIcon();
    });

    grid.on("load", function () {
        me.syncGridSortIcon();
    });
}

MultiSort.prototype = {

    sortFieldsParam: "sortFields",

    sort: function (fields) {
        this.sortFields = fields;

        var o = {};
        o[this.sortFieldsParam] = mini.encode(fields);

        this.grid.load(o);

        this.syncGridSortIcon();
    },

    addSort: function (field, dir) {

        var me = this,
                    fields = me.sortFields;

        if (typeof field == "object") {
            dir = field.dir;
            field = field.field;
        }

        var o = me.getSort(field);
        if (o) {
            o.dir = dir;
        } else {
            o = { field: field, dir: dir };
        }

        fields.remove(o);
        //fields.insert(0, o);
        fields.push(o);

        me.sort(fields);

    },

    removeSort: function (field) {
        var o = this.getSort(field);
        if (o) {
            this.sortFields.remove(o);
            this.sort(this.sortFields);
        }
    },

    getSort: function (field) {
        if (typeof field == "object") return field;
        for (var i = 0, l = this.sortFields.length; i < l; i++) {
            var o = this.sortFields[i];
            if (o.field == field) return o;
        }
    },

    clearSort: function () {
        this.sort([]);
    },

    syncGridSortIcon: function () {
        var me = this,
                    grid = me.grid,
                    sortFields = me.sortFields,
                    columns = grid.getBottomColumns();

        function createHeaderCellId(column, index) {
            var id = grid._id + "$headerCell" + index + "$" + column._id;
            return id;
        }

        function getHeaderCellEl(column) {
            var el = document.getElementById(createHeaderCellId(column, 1));
            if (!el) el = document.getElementById(createHeaderCellId(column, 2));
            return el;
        }

        function getColumnByField(field) {
            for (var i = 0, l = columns.length; i < l; i++) {
                var col = columns[i];
                if (col.field == field) return col;
            }
        }

        function syncSortIcon() {
            me.syncSortIconTimer = null;
            for (var i = 0, l = sortFields.length; i < l; i++) {
                var o = sortFields[i];
                var column = getColumnByField(o.field);
                if (!column) continue;
                var el = getHeaderCellEl(column);
                if (!el) continue;

                var sortCls = o.dir == "asc" ? "mini-grid-asc" : "mini-grid-desc";
                $(el).removeClass("mini-grid-asc mini-grid-desc").addClass(sortCls);

                $(el).find(".mini-grid-sortIcon").remove();

                $(el).find(".mini-grid-headerCell-inner").append('<span class="mini-grid-sortIcon"></span>');
            }
        }

        //                if (me.syncSortIconTimer) {
        //                    clearTimeout(me.syncSortIconTimer);
        //                    me.syncSortIconTimer = null;
        //                }
        //                me.syncSortIconTimer = setTimeout(syncSortIcon, 100);

        syncSortIcon();
    }

};