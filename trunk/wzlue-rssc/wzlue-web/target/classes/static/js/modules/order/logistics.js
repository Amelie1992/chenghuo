$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'order/logistics/list',
        columns: [
            {checkbox: true},
            {title: '公司名称', field: 'companyName'},
            {title: '物流公司编码', field: 'companyCode'},
            {title: '是否常用', field: 'usable',
                formatter: function (value, row, index) {
                    return value == 0 ? '<span class="label label-default">否</span>' : '<span class="label label-primary">是</span>';
                }
            },

            // {title: '网址', field: 'url'},
            // {title: '物流电话', field: 'companyPhone'},
            {title: '状态', field: 'status',
                formatter: function (value, row, index) {
                    return value == 1 ? '<span class="label label-primary">可用</span>' : '<span class="label label-default">禁用</span>';
                }}
            // {title: '创建时间', field: 'createTime'},
            // {title: '更新时间', field: 'updateTime'}
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
        logistics: {},
        queryParams: {},
        //验证字段
        fields: {
            companyName: {
                message: '公司名称验证失败',
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    },
                },
            }, companyCode: {
                message: '物流公司编码验证失败',
                validators: {
                    notEmpty: {
                        message: '物流公司编码不能为空'
                    },
                },
            }, url: {
                message: '网址验证失败',
                validators: {
                    notEmpty: {
                        message: '网址不能为空'
                    },
                },
            }, companyPhone: {
                message: '物流电话验证失败',
                validators: {
                    notEmpty: {
                        message: '物流电话不能为空'
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
            vm.logistics = {
                status:1,
            };
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
            var url = vm.logistics.id == null ? "order/logistics/save" : "order/logistics/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.logistics),
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
                    url: baseURL + "order/logistics/delete",
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
            $.get(baseURL + "order/logistics/info/" + id, function (r) {
                vm.logistics = r.logistics;
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.title = "";
            //刷新 如需条件查询common.js
            $("#table").BTF5(vm.queryParams);
        }
    }
});