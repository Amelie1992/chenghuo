$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'order/serviceEvaluate/list',
        columns: [
            {checkbox: true},
            /*{ title: '会员id', field: 'memberId'},
            { title: '退款id', field: 'refundId'},*/
            {title: '退款编号', field: 'refundNumber'},
            {title: '服务评价级别（1至5级）', field: 'evaluateLevel'},
            {
                title: '评价内容', field: 'content', formatter: function (value, row, index) {
                    if (!isBlank(value) && value.length > 15) {
                        return value.substring(0, 15);
                    } else {
                        return value;
                    }
                }
            },
            {title: '创建时间', field: 'createTime'}
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
        serviceEvaluate: {},
        queryParams:{},
        //验证字段
        fields: {
            memberId: {
                message: '会员id验证失败',
                validators: {
                    notEmpty: {
                        message: '会员id不能为空'
                    },
                },
            }, refundId: {
                message: '退款id验证失败',
                validators: {
                    notEmpty: {
                        message: '退款id不能为空'
                    },
                },
            }, refundNumber: {
                message: '退款编号验证失败',
                validators: {
                    notEmpty: {
                        message: '退款编号不能为空'
                    },
                },
            }, evaluateLevel: {
                message: '服务评价级别（1--5）验证失败',
                validators: {
                    notEmpty: {
                        message: '服务评价级别（1--5）不能为空'
                    },
                },
            }, content: {
                message: '评价内容验证失败',
                validators: {
                    notEmpty: {
                        message: '评价内容不能为空'
                    },
                },
            }, createTime: {
                message: '创建时间验证失败',
                validators: {
                    notEmpty: {
                        message: '创建时间不能为空'
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
            vm.serviceEvaluate = {};
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
            var url = vm.serviceEvaluate.id == null ? "order/serviceEvaluate/save" : "order/serviceEvaluate/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.serviceEvaluate),
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
                    url: baseURL + "order/serviceEvaluate/delete",
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
            $.get(baseURL + "order/serviceEvaluate/info/" + id, function (r) {
                vm.serviceEvaluate = r.serviceEvaluate;
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