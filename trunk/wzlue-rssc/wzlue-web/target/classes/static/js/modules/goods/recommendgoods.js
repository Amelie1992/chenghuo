$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'goods/recommendGoods/list',
        columns: [
            {checkbox: true},
            {title: '模块ID', field: 'moduleId'},
            {title: '商品ID', field: 'goodsId'},
            {title: '排序', field: 'sort'},
            {title: '添加时间', field: 'addTime'},
            {title: '更新时间', field: 'updateTime'}
        ],
        //条件查询
        queryParams: {}
    });
    //表单提交
    $("form").FM({
        fields: vm.fields,
        success: vm.saveOrUpdate

    })

});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        recommendGoods: {},
        //验证字段
        fields: {
            moduleId: {
                message: '模块ID验证失败',
                validators: {
                    notEmpty: {
                        message: '模块ID不能为空'
                    },
                },
            }, goodsId: {
                message: '商品ID验证失败',
                validators: {
                    notEmpty: {
                        message: '商品ID不能为空'
                    },
                },
            }, sort: {
                message: '排序验证失败',
                validators: {
                    notEmpty: {
                        message: '排序不能为空'
                    },
                },
            }, addTime: {
                message: '添加时间验证失败',
                validators: {
                    notEmpty: {
                        message: '添加时间不能为空'
                    },
                },
            }, updateTime: {
                message: '更新时间验证失败',
                validators: {
                    notEmpty: {
                        message: '更新时间不能为空'
                    },
                },
            }
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.recommendGoods = {};
        },
        update: function (event) {
            var id = getSelectedRowId("id");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        //表单验证
        validate: function () {
            var bl = $('form').VF();//启用验证
            if (!bl) {
                return;
            }
        },
        saveOrUpdate: function (event) {
            var url = vm.recommendGoods.id == null ? "goods/recommendGoods/save" : "goods/recommendGoods/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.recommendGoods),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRowsId("id");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "goods/recommendGoods/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "goods/recommendGoods/info/" + id, function (r) {
                vm.recommendGoods = r.recommendGoods;
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.title = "";
            //刷新 如需条件查询common.js
            $("#table").BTF5();
        }
    }
});