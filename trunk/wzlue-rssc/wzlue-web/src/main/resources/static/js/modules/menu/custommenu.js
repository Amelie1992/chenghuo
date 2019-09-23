$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'menu/custommenu/list',
        columns: [
            {checkbox: true},
            {title: '分类', field: 'classification', formatter: function (value, row, index) {
                    if (value == 1) {
                        return "会员协议"
                    }else if (value == 2) {
                        return "积分规则"
                    }else if (value == 3) {
                        return "积分兑换规则"
                    }else if (value == 4) {
                        return "联系客服"
                    }else if (value == 5) {
                        return "会员权益"
                    }else if (value == 20) {
                        return "自定义菜单"
                    }
                }
            },
            {title: '菜单', field: 'head'},
            {title: '标题', field: 'title', formatter: function (value, row, index) {
                    if (!isBlank(value) && value.length>15) {
                        return value.substring(0,15);
                    }else {
                        return value;
                    }
                }
            },
            {title: '正文', field: 'body', formatter: function (value, row, index) {
                    var body = UE.utils.html(value);
                    if (!isBlank(body) && body.length>15) {
                        return body.substring(0,15);
                    }else {
                        return body;
                    }
                }
            },
            {title: '状态', field: 'state', formatter: function (value, row, index) {
                    if (value == 1) {
                        return "显示"
                    }else if (value == 2) {
                        return "隐藏"
                    }
                }
            }
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
        customMenu: {},
        ue: UE.getEditor('myEditor', {
            initialFrameHeight: 440,
        }),
        //验证字段
        fields: {
            head: {
                message: '菜单验证失败',
                validators: {
                    notEmpty: {
                        message: '菜单不能为空'
                    },stringLength: {
                        max: 15,
                        message: '菜单长度在15个字符以内'
                    }
                },
            }, title: {
                message: '标题验证失败',
                validators: {
                    notEmpty: {
                        message: '标题不能为空'
                    },stringLength: {
                        max: 30,
                        message: '标题长度在30个字符以内'
                    }
                },
            }, body: {
                message: '正文验证失败',
                validators: {
                    /*notEmpty: {
                        message: '正文不能为空'
                    },*/
                    callback: function (value, v) {
                        if (isBlank(vm.ue.getContent())) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                },
            }, state: {
                message: '1上架；2下架验证失败',
                validators: {
                    notEmpty: {
                        message: '1上架；2下架不能为空'
                    },
                },
            }
        }
    },
    methods: {
        //显示
        show: function () {
            var id = getSelectedRowId("id");
            if (id == null) {
                return;
            }
            $.ajax({
                type: "POST",
                url: baseURL + "menu/custommenu/show/" + id,
                // contentType: "application/json",
                // data: JSON.stringify(id),
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
        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.customMenu = {};
            vm.ue.setContent('');
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
            vm.customMenu.body = UE.utils.unhtml(this.ue.getContent());
            let ceiShi=this.ue.getContentTxt();//获取纯文本内容
            let photos=UE.utils.unhtml(this.ue.getContent());
            var imgReg = /img.*?(?:>|\/)/gi;
            var arr = photos.match(imgReg);//筛选出图片个数
            if(arr!=null){
                if (arr.length>5){
                    alert("正文的图片数量请少于6张");
                    return
                }
            }
            ceiShi=ceiShi.replace(/(^\s+)|(\s+$)/g,"").replace(/(\n)/g, "").replace(/(\t)/g, "").replace(/(\r)/g, "").replace(/<\/?[^>]*>/g, "").replace(/\s*/g, "");//去除空格等筛选

            if (isBlank(vm.customMenu.body)) {
                alert("正文不能为空");
                return;
            }else if (ceiShi.length > 1000) {
                alert("正文不能超出1000字符");
                return;
            }
            var url = vm.customMenu.id == null ? "menu/custommenu/save" : "menu/custommenu/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.customMenu),
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
                    url: baseURL + "menu/custommenu/delete",
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
            vm.ue.setContent('');
            $.get(baseURL + "menu/custommenu/info/" + id, function (r) {
                vm.customMenu = r.customMenu;
                vm.ue.setContent(UE.utils.html(vm.customMenu.body));
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.title = "";
            vm.ue.setContent('');
            //刷新 如需条件查询common.js
            $("#table").BTF5();
        }
    }
});