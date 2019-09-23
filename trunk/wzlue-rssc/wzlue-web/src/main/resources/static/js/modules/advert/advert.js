$(function () {
    // 列表
    $("#table").BT({
        url: baseURL + 'advert/advert/list',
        columns: [{
            checkbox: true
        }, {
            title: '位置',
            field: 'positionId',
            formatter:function (value, row, index) {
                var str = '';
                switch (value){
                    case 1:
                        str = '首页';
                        break;
                    case 2:
                        str = '分类';
                        break;
                }
                return str;
            }
        }, {
            title: '标题',
            field: 'title'
        }, {
            title: '图片',
            field: 'picUrl',
            formatter:function (value, row, index) {

                return '<img width="200px" src='+value+'>';
            }
        }, {
            title: '连接',
            field: 'link'
        }, {
            title: '排序',
            field: 'sort'
        }, {
            title: '状态',
            field: 'status',
            formatter:function (value, row, index) {

                return value == 1 ? '已启用':'未启用';
            }
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

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        advert: {},
        // 验证字段
        fields: {
            picUrl: {
                message: '图片验证失败',
                validators: {
                    notEmpty: {
                        message: '图片不能为空'
                    },
                },
            },
            // link: {
            //     message: '连接验证失败',
            //     validators: {
            //         notEmpty: {
            //             message: '连接不能为空'
            //         },
            //     },
            // }
        },
        positionList: [{id: 1, name: "首页"}, {id: 2, name: "分类"}]
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            $('#picUrl').fileinput('destroy');
            vm.advert = {
                status:1
            };
            var files = [{
                caption: '图片',
                width: "120px",
                url: window.baseURL + 'advert/advert/info/' + -1,
                key: 1,
                extra: {}
            }]
            vm.upload("picUrl",[],files)

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
        // 表单验证
        validate: function () {
            var bl = $('form').VF();// 启用验证
            if (!bl) {
                return;
            }
        },
        saveOrUpdate: function (event) {
            var url = vm.advert.id == null ? "advert/advert/save"
                : "advert/advert/update";
            if(vm.advert.picUrl == ""||vm.advert.picUrl == null){
                alert("请上传广告图片");
                return;
            }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.advert),
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
            $('#picUrl').fileinput('destroy');
            vm.upload("picUrl",[],"");
        },
        del: function (event) {
            var ids = getSelectedRowsId("id");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "advert/advert/delete",
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
            var that =this;
            $.get(baseURL + "advert/advert/info/" + id, function (r) {
                vm.advert = r.advert;
                var files = [{
                    caption: '图片',
                    width: "120px",
                    url: window.baseURL + 'advert/advert/info/' + id,
                    key: 1,
                    extra: {}
                }]
                $('#picUrl').fileinput('destroy');
                vm.upload("picUrl", [vm.advert.picUrl], files);
            });
        },
        //上传图片
        upload: function (inputName, src, files) {
            window.initUpload(inputName, window.baseURL + "app/file/upload", {
                'jpg': '<i class="fa fa-file-excel-o text-success"></i>',
                'gif': '<i class="fa fa-file-excel-o text-success"></i>',
                'png': '<i class="fa fa-file-excel-o text-success"></i>',
            }, ["jpg", "gif", "png"], src, files)
                .on("fileuploaded", function (event, data, id) {
                        vm.advert.picUrl = data.response.url;
                })
                .on("filesuccessremove", function (event, previewId, data) {
                    // vm.fileList.splice(vm.fileList.indexOf(data))
                        vm.advert.picUrl = "";
                })
                .on('filepredelete', function(event, key, jqXHR, data) {
                        vm.advert.picUrl = "";
                });
        },
        reload: function (event) {
            vm.showList = true;
            vm.title = "";
            // 刷新 如需条件查询common.js
            $("#table").BTF5();
            $('#picUrl').fileinput('destroy');
            vm.upload("picUrl",[],"");
        }
    }
});