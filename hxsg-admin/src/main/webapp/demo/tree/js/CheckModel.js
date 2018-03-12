
var CheckModel = function (tree) {

    this.tree = tree;

    this.init();

    this.syncAllNodes();
}

CheckModel.prototype = {

    init: function () {
        var me = this,
            tree = me.tree;

        tree.setCheckRecursive(false);
        tree.setAutoCheckParent(false);
        tree.on("beforenodecheck", this.onBeforeNodeCheck, me);
        tree.on("loaddata", this.onDataLoad, me);

    },

    onDataLoad: function (e) {
        this.syncAllNodes();
    },

    onBeforeNodeCheck: function (e) {
        e.cancel = true;

        this.setNodeChecked(e.node, !e.checked);

    },

    setNodeChecked: function (node, checked) {
        var tree = this.tree;

        node.indeterminate = false;
        if (checked) {
            tree.checkNode(node, false);
        } else {
            tree.uncheckNode(node, false);
        }

        this.syncNode(node);
    },

    syncNode: function (node, syncChildren, syncParent) {
        var me = this,
            tree = me.tree,
            checked = node.checked;

        //1) 同步子节点
        if (syncChildren !== false) {
            tree.cascadeChild(node, function (child) {
                child.indeterminate = false;
                if (checked) {
                    tree.checkNode(child, false);
                } else {
                    tree.uncheckNode(child, false);
                }
            });
        }

        //2) 同步父节点
        if (syncParent !== false) {
            var parentNode = tree.getParentNode(node);
            while (parentNode) {

                var allChecked = true,
                indeterminate = false;
                var children = parentNode.children;
                for (var i = 0, l = children.length; i < l; i++) {
                    var child = children[i];

                    if (child.checked || child.indeterminate) {
                        indeterminate = true;
                    }

                    if (child.checked) {
                        if (allChecked == false) {
                            break;
                        }
                    } else {
                        allChecked = false;
                        if (indeterminate) {
                            break;
                        }
                    }
                }

                if (allChecked) {
                    parentNode.indeterminate = false;
                    tree.checkNode(parentNode, false);
                } else {
                    parentNode.indeterminate = indeterminate;
                    //if(parentNode.text == 'Base') debugger
                    tree.uncheckNode(parentNode, false);
                }

                parentNode = tree.getParentNode(parentNode);
            }
        }
    },

    syncAllNodes: function () {
        var me = this,
            tree = me.tree,
            root = tree.getRootNode();

        //0) 记录所有checked的节点（初始态）
        var nodes = [];
        tree.cascadeChild(root, function (node) {
            if (node.checked) {
                nodes.push(node);
            }
        });

        //1) 处理父节点
        for (var i = 0, l = nodes.length; i < l; i++) {
            var node = nodes[i];
            var children = node.children;
            if (node.checked && children && children.length) {  //同步子节点
                me.syncNode(node, true, false);
            }
        }

        //2) 处理子节点
        for (var i = 0, l = nodes.length; i < l; i++) {
            var node = nodes[i];
            var children = node.children;
            if (node.checked && (!children || children.length == 0)) {  //同步父节点
                me.syncNode(node, false, true);
            }
        }

    }

}

