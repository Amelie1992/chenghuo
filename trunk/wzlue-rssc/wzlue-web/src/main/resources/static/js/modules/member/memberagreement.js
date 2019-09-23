$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'member/agreement/list',
        columns: [
            {checkbox: true},
            {title: '协议内容', field: 'content',formatter: function (value, row, index) {
                    var content = UE.utils.html(value);
                    if (!isBlank(content) && content.length>15) {
                        return content.substring(0,15);
                    }else {
                        return content;
                    }
                }},
            {
                title: '状态',
                field: 'status',
                formatter: function (value, row, index) {
                    return value == 1 ? '<span class="label label-primary">正常</span>' : '<span class="label label-default">禁用</span>';
                }
            },
            {title: '创建时间', field: 'createTime'},
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
        memberAgreement: {},
        ue: UE.getEditor('myEditor', {
            initialFrameHeight: 440,
        }),
        //验证字段
        fields: {
            content: {
                message: '协议内容验证失败',
                validators: {
                    // notEmpty: {
                    //     message: '协议内容不能为空'
                    // },
                    callback: function (value, v) {
                        if (isBlank(vm.ue.getContent())) {
                            return false;
                        } else {
                            return true;
                        }
                    }
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
            vm.memberAgreement = {
                status:1
            };
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
            vm.memberAgreement.content = UE.utils.unhtml(this.ue.getContent());
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

            if (isBlank(vm.memberAgreement.content)) {
                alert("正文不能为空");
                return;
            }else if (ceiShi.length > 1000) {
                alert("正文不能超出1000字符");
                return;
            }

            var url = vm.memberAgreement.id == null ? "member/agreement/save" : "member/agreement/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.memberAgreement),
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
                    url: baseURL + "member/agreement/delete",
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
            $.get(baseURL + "member/agreement/info/" + id, function (r) {
                vm.memberAgreement = r.memberAgreement;
                vm.ue.setContent(UE.utils.html(vm.memberAgreement.content));
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