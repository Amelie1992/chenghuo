$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'poster/extensionPoster/list',
        columns: [
            {checkbox: true},
            {title: '推广海报', field: 'posterUrl',formatter:function (value, row, index) {
                    return '<img width="200px" src='+value+'>';
                }},
            {title: '状态', field: 'status',formatter:function (value, row, index) {
                    return value == 1 ? '已启用':'未启用';
                }},
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
        extensionPoster: {},
        //验证字段
        fields: {
            posterUrl: {
                message: '推广海报验证失败',
                validators: {
                    notEmpty: {
                        message: '推广海报不能为空'
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
            vm.extensionPoster = {
                status:1
            };
            $('#posterUrl').fileinput('destroy');
            var files = [{
                caption: '图片',
                width: "120px",
                url: window.baseURL + 'poster/extensionPoster/info/' + -1,
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
        //表单验证
        validate: function () {
            var bl = $('form').VF();//启用验证
            if (!bl) {
                return;
            }
        },
        saveOrUpdate: function (event) {
            var url = vm.extensionPoster.id == null ? "poster/extensionPoster/save" : "poster/extensionPoster/update";
            if(vm.extensionPoster.posterUrl == ""||vm.extensionPoster.posterUrl == null){
                alert("请上传海报图片");
                return;
            }
            var url = vm.extensionPoster.id == null ? "poster/extensionPoster/save" : "poster/extensionPoster/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.extensionPoster),
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
                    url: baseURL + "poster/extensionPoster/delete",
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
            $.get(baseURL + "poster/extensionPoster/info/" + id, function (r) {
                vm.extensionPoster = r.extensionPoster;
                var files = [{
                    caption: '图片',
                    width: "120px",
                    url: window.baseURL + 'poster/extensionPoster/info/' + id,
                    key: 1,
                    extra: {}
                }]
                $('#picUrl').fileinput('destroy');
                vm.upload("picUrl", [vm.extensionPoster.posterUrl], files);
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
                    vm.extensionPoster.posterUrl = data.response.url;
                })
                .on("filesuccessremove", function (event, previewId, data) {
                    // vm.fileList.splice(vm.fileList.indexOf(data))
                    vm.extensionPoster.posterUrl = "";
                })
                .on('filepredelete', function(event, key, jqXHR, data) {
                    vm.extensionPoster.posterUrl = "";
                });
        },

        reload: function (event) {
            vm.showList = true;
            vm.title = "";
            //刷新 如需条件查询common.js
            $("#table").BTF5();
            $('#picUrl').fileinput('destroy');
            vm.upload("picUrl",[],"");
        }
    }
});