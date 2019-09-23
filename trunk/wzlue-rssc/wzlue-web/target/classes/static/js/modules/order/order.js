$(function () {
    // 列表
    $("#table").BT({
        url: baseURL + 'order/order/list',
        columns: [{
            checkbox: true
        }, {
            title: '订单编号',
            field: 'orderNumber',
            formatter: function (value, row, index) {
                return '<a onclick="vm.detail(' + row.id + ')">' + value + '</a>';
            }
        }, {
            title: '头像 昵称',
            field: 'memberInfo.avatarUrl',
            formatter: function (value, row, index) {
                return '<div><img src=\"' + value + '\" width="50px" height="50px"/> <label>'+row.memberInfo.nickName+'</label></div>';
            }
        },
        //     {
        //     title: '商品总价格',
        //     field: 'productAmount'
        // },
            {
                title:'商品名称(数量)',
                formatter: function (value, row, index) {
                    for (var i=0;i<row.orderGoodsList.length;i++){
                        var aa
                        if (row.orderGoodsList[i].specName ==null && row.orderGoodsList[i].specNameTwo!=null  || row.orderGoodsList[i].specName =="" && row.orderGoodsList[i].specNameTwo!=""){
                            aa= '<div><label>'+row.orderGoodsList[i].goodsName+'('+row.orderGoodsList[i].specNameTwo+')'+'x'+row.orderGoodsList[i].buyNum+'</label><br/></div>';

                        }else if (row.orderGoodsList[i].specNameTwo==null && row.orderGoodsList[i].specName !=null || row.orderGoodsList[i].specNameTwo=="" && row.orderGoodsList[i].specName !=""){
                            aa= '<div><label>'+row.orderGoodsList[i].goodsName+'('+row.orderGoodsList[i].specName+')'+'x'+row.orderGoodsList[i].buyNum+'</label><br/></div>';
                        }else if (row.orderGoodsList[i].specName ==null && row.orderGoodsList[i].specNameTwo==null || row.orderGoodsList[i].specName =="" && row.orderGoodsList[i].specNameTwo==""){
                            aa= '<div><label>'+row.orderGoodsList[i].goodsName+'x'+row.orderGoodsList[i].buyNum+'</label><br/></div>';
                        }else{
                            aa= '<div><label>'+row.orderGoodsList[i].goodsName+'('+ row.orderGoodsList[i].specName +' '+row.orderGoodsList[i].specNameTwo+')'+'x'+row.orderGoodsList[i].buyNum+'</label><br/></div>';
                        }
                        var b=aa+b;
                        b = b.replace('undefined','')

                    }
                    return b;
                }
            },
            {
                title:'商品单价',
                formatter: function (value, row, index) {
                    for (var i=0;i<row.orderGoodsList.length;i++){
                        var aa= '<div><label>'+row.orderGoodsList[i].price+'</label><br/></div>';
                        var b=aa+b;
                        b = b.replace('undefined','')
                    }
                    return b;
                }
            },
            {
            title: '实付金额',
            field: 'paymentAmount'
        }, {
            title: '所用积分',
            field: 'useIntegral'
        }, {
            title: '微信付款',
            field: 'wechatAmount'
        }, {
            title: '支付方式',
            field: 'payType',
            formatter: function (value, row, index) {
                if (value == "wechat") {
                    return '<span class="label label-default">微信</span>';
                } else if (value == "integral") {
                    return '<span class="label label-primary">积分</span>';
                } else if (value == "mix") {
                    return '<span class="label label-warning">混合</span>';
                }
            }
        }, {
            title: '订单状态 ',
            field: 'status',
            formatter: function (value, row, index) {
                if (value == 0) {
                    return '<span class="label label-default">已取消</span>';
                } else if (value == 1) {
                    return '<span class="label label-primary">待付款</span>';
                } else if (value == 2) {
                    return '<span class="label label-warning">待发货</span>';
                } else if (value == 3) {
                    return '<span class="label label-info">待收货</span>';
                } else if (value == 4) {
                    return '<span class="label label-success">退款中</span>';
                } else if (value == 5) {
                    return '<span class="label label-success">退款成功</span>';
                } else if (value == 6) {
                    return '<span class="label label-success">退款失败</span>';
                } else if (value == 7) {
                    return '<span class="label label-success">已完成</span>';
                }
            }
        }, {
            title: '订单类型',
            field: 'orderType',
            formatter: function (value, row, index) {
                if (value == 2) {
                    return '<span class="label label-default">虚拟</span>';
                } else{
                    return '<span class="label label-primary">普通</span>';
                }
            }
        }, {
            title: '创建时间',
            field: 'createTime'
        }],
        // 条件查询
        queryParams: {}
    });
    // 表单提交
    $("form").FM({
        fields: vm.fields,
        success: vm.saveOrUpdate

    })

});

var vm = new Vue(
    {
        el: '#rrapp',
        data: {
            showList: true,
            disabledAll: false,
            title: null,
            OrderInvoiceType: "",
            order: {
                memberInfo: {},
                orderAddress: {},
                orderLogistics: {},
                //发票实体类
                orderInvoice: {}
            },
            orderTwo:[],
            logisticsCompanyList: [],//物流公司list
            queryParams: {
                status: '',
            },
            wuLiu: false,//默认物流信息隐藏
            content:{
                data:{
                    time:"",
                    ftime:"",
                    context:"",
                }
            },//物流信息
            //状态
            statusList: [
                {'value': 0, 'label': '已取消'},
                {'value': 1, 'label': '待付款'},
                {'value': 2, 'label': '待发货'},
                {'value': 3, 'label': '待收货'},
                {'value': 4, 'label': '退款中'},
                {'value': 5, 'label': '退款成功'},
                {'value': 6, 'label': '退款失败'},
                {'value': 7, 'label': '已完成'}//(0已取消 1待付款  2 待发货  3待收货  4退款中 5退款成功 6退款失败 7已完成)
            ],
            // 验证字段
            fields: {
                orderNumber: {
                    message: '订单编号验证失败',
                    validators: {
                        notEmpty: {
                            message: '订单编号不能为空'
                        },
                    },
                },
                orderType: {
                    message: '订单类型 1普通订单验证失败',
                    validators: {
                        notEmpty: {
                            message: '订单类型 1普通订单不能为空'
                        },
                    },
                },
                memberId: {
                    message: '客户id验证失败',
                    validators: {
                        notEmpty: {
                            message: '客户id不能为空'
                        },
                    },
                },
                status: {
                    message: '订单的状态 1未付款  2 已付款,待发货  3.已发货 4 确认收货 5待评价  6.已退款 7 取消订单 8 退款成功 9退款失败验证失败',
                    validators: {
                        notEmpty: {
                            message: '订单的状态 1未付款  2 已付款,待发货  3.已发货 4 确认收货 5待评价  6.已退款 7 取消订单 8 退款成功 9退款失败不能为空'
                        },
                    },
                },
                payType: {
                    message: '支付方式：wechat验证失败',
                    validators: {
                        notEmpty: {
                            message: '支付方式：wechat不能为空'
                        },
                    },
                },
                logisticsAmount: {
                    message: '物流价格验证失败',
                    validators: {
                        notEmpty: {
                            message: '物流价格不能为空'
                        },
                    },
                },
                productAmount: {
                    message: '商品总价格验证失败',
                    validators: {
                        notEmpty: {
                            message: '商品总价格不能为空'
                        },
                    },
                },
                paymentAmount: {
                    message: '实付金额验证失败',
                    validators: {
                        notEmpty: {
                            message: '实付金额不能为空'
                        },
                    },
                },
                ramarks: {
                    message: '备注验证失败',
                    validators: {
                        notEmpty: {
                            message: '备注不能为空'
                        },
                    },
                },
                createTime: {
                    message: '创建时间验证失败',
                    validators: {
                        notEmpty: {
                            message: '创建时间不能为空'
                        },
                    },
                },
                delFlag: {
                    message: '订单删除验证失败',
                    validators: {
                        notEmpty: {
                            message: '订单删除不能为空'
                        },
                    },
                }
            }
        },
        methods: {
            query: function () {
                vm.reload();
            },
            queryWuLiu: function () {
                var companyId = vm.order.orderLogistics.companyId;
                var nu = vm.order.orderLogistics.logisticsNumber;
                $.get(baseURL + "order/orderrefund/queryLogistics", {companyId: companyId, nu: nu}, function (r) {
                    vm.content = JSON.parse(r.content);
                    vm.wuLiu = true;
                });
            },
            add: function () {
                vm.showList = false;
                vm.title = "新增";
                vm.order = {
                    memberInfo: {},
                    orderLogistics:{
                        companyId:''
                    }
                };
            },
            update: function (event) {
                var id = getSelectedRowId("id");
                if (id == null) {
                    return;
                }
                vm.showList = false;
                vm.disabledAll = false;
                vm.title = "修改";
                vm.getInfo(id)
            },
            detail: function (id) {
                vm.showList = false;
                vm.title = "详情";
                vm.getInfo(id)
            },
            // 表单验证
            validate: function () {
                var bl = $('form').VF();// 启用验证
                if (!bl) {
                    return;
                }
            },
            saveOrUpdate: function (event) {
                var url = vm.order.id == null ? "order/order/save"
                    : "order/order/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.order),
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
                        url: baseURL + "order/order/delete",
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
            /*导出*/
            outPut:function () {

                window.location.href = '/order/order/outPut?token=' + window.token;

            },
            getInfo: function (id) {
                var _that=this;
                $.get(baseURL + "order/order/info/" + id, function (r) {
                    vm.order = r.order;
                    vm.orderTwo=[];
                    for (var i=0;i<r.order.orderGoodsList.length;i++){
                        if (r.order.orderGoodsList[i].specName==null || r.order.orderGoodsList[i].specName==""){
                            r.order.orderGoodsList[i].specName=""
                        }
                        if (r.order.orderGoodsList[i].specNameTwo==null || r.order.orderGoodsList[i].specNameTwo==""){
                            r.order.orderGoodsList[i].specNameTwo=""
                        }

                        var a ="商品名称:"+r.order.orderGoodsList[i].goodsName+" "+r.order.orderGoodsList[i].specName+r.order.orderGoodsList[i].specNameTwo+",数量:"+r.order.orderGoodsList[i].buyNum+",单价:"+r.order.orderGoodsList[i].price
                        vm.orderTwo.push(a)
                    }
                    var dataId
                    if(r.order.orderLogistics.companyId==null){
                        dataId=""
                    }else{
                        dataId=r.order.orderLogistics.companyId;
                    }
                    _that.getLogisticsCompany(dataId);
                    if (r.order.status == 2) { //发货
                        r.order.orderLogistics = {companyId: ""};
                    }
                    if (r.order.orderInvoice.invoiceType == 0) {
                        vm.OrderInvoiceType = "单位"
                    } else {
                        vm.OrderInvoiceType = "个人"
                    }
                });
            },
            //查询物流公司（status=1）
            getLogisticsCompany: function()
            {
                $.get(baseURL + "order/logistics/list2?status="+1, function(r){
                    vm.logisticsCompanyList = r.logisticsList;
                });
            },
            reload: function (event) {
                vm.showList = true;
                vm.title = "";
                vm.content="";
                // 刷新 如需条件查询common.js
                $("#table").BTF5(vm.queryParams);
                $("form").RF();
            },
            reloadTwo:function (event) {
                vm.showList = true;
                vm.title = "";
                // 刷新 如需条件查询common.js
                $("#table").BTF5();
                $("form").RF();
            },
            sendGoods: function () {
                var companyName = $("#logisticsCompany").find("option:selected").text();
                var companyId = $("#logisticsCompany").find("option:selected").val();
                if(companyId == ""||companyId==null){
                 	alert("请选择物流公司");
                 	return false;
                }
                var logisticsNumber = vm.order.orderLogistics.logisticsNumber;
                if (logisticsNumber == "") {
                    alert("请填写物流单号");
                    return false;
                }
                $.get(baseURL + "order/order/sendGoods", {
                    companyId: companyId,
                    companyName: companyName,
                    logisticsNumber: logisticsNumber,
                    orderNumber: vm.order.orderNumber
                }, function (r) {
                    vm.reload();
                });
            },
            //查询物流公司（status=1）
            getLogisticsCompany: function (dataId) {

                $.ajax({
                    type: 'get',
                    url: baseURL +"order/logistics/list2?status=" + 1,
                    contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
                    dataType:   "json",
                    async : true,
                    success : function(result) {
                        var str = '<option value="" >请选择</option>';
                        for(var i = 0;i<result.logisticsList.length;i++) {
                            str+='<option value="'+result.logisticsList[i].id+'">'+result.logisticsList[i].companyName+'</option>';
                        }

                        $('#logisticsCompany').html(str);
                        if(dataId != '' && dataId != 'undefined') {
                            $('#logisticsCompany').selectpicker('val', dataId);
                        }
                        $('#logisticsCompany').selectpicker('refresh');


                    }
                });


            },
        },
        created: function () {
            //初始化物流公司
            this.getLogisticsCompany();
        }
    });