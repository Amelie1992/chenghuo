// var setting = {
//     data: {
//         simpleData: {
//             enable: true,
//             idKey: "id",
//             pIdKey: "parentId",
//             rootPId: -1
//         },
//         key: {
//             url: "nourl"
//         }
//     }
// };
var ztree;
$(function () {
    // 列表
    $("#table").BT({
        url: baseURL + 'goods/category/list',
        columns: [{
            checkbox: true
        }, {
            title: '名称',
            field: 'categoryName'
        }, {
            title: '图标',
            field: 'picUrl',
            formatter : function(value, row, index) {
                return '<img src=\"' + value + '\" width="50px" height="50px"/>';
            }
        },{
            title: '排序',
            field: 'sort'
        }, {
            title: '备注',
            field: 'remarks'
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
        category: {
            parentId: -1
        },
        categoryList: [],
        // 验证字段
        fields: {
            categoryName: {
                message: '名称验证失败',
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    },
                },
            },
            sort: {
                message: '排序验证失败',
                validators: {
                    notEmpty: {
                        message: '排序不能为空'
                    },regexp:{
                    regexp:/^\d+$/,
                    message:'只能输入数字'
                }
                },
            }
        }
    },
    methods: {
        // getCategory: function () {
        //     //加载菜单树
        //     $.get(baseURL + "goods/category/tree", function (r) {
        //         ztree = $.fn.zTree.init($("#categoryTree"), setting, r.list);
        //         var node = ztree.getNodeByParam("id", vm.category.parentId);
        //         ztree.selectNode(node);
        //
        //         vm.category.parentId = node.id;
        //         vm.category.parentName = node.name;
        //     })
        // },
        // categoryTree: function () {
        //     layer.open({
        //         type: 1,
        //         offset: '50px',
        //         skin: 'layui-layer-molv',
        //         title: "选择菜单",
        //         area: ['300px', '450px'],
        //         shade: 0,
        //         shadeClose: false,
        //         content: jQuery("#categoryLayer"),
        //         btn: ['确定', '取消'],
        //         btn1: function (index) {
        //             var node = ztree.getSelectedNodes();
        //             //选择上级菜单
        //             vm.category.parentId = node[0].id;
        //             vm.category.parentName = node[0].name;
        //
        //             layer.close(index);
        //         }
        //     });
        // },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.category = {};
            // vm.getCategory();
            //
            $('#picUrl').fileinput('destroy');
            var files = [{
                caption: '图片',
                width: "120px",
                url: window.baseURL + 'goods/category/info/' + -1,
                key: 1,
                extra: {}
            }]
            vm.upload("picUrl",[],files);

        },

        upload:function(inputName, src,files){
            //上传图片
            window.initUploadone(inputName, window.baseURL + "app/file/upload", {
                'jpg': '<i class="fa fa-file-excel-o text-success"></i>',
                'gif': '<i class="fa fa-file-excel-o text-success"></i>',
                'png': '<i class="fa fa-file-excel-o text-success"></i>',
            }, ["jpg", "gif", "png"], src, files)
                .on("fileuploaded", function (event, data, id) {
                    vm.category.picUrl = data.response.url;
                })
                .on("filesuccessremove", function (event, previewId, data) {
                    // vm.fileList.splice(vm.fileList.indexOf(data))
                    vm.category.picUrl = "";
                })
                .on('filepredelete', function(event, key, jqXHR, data) {
                    vm.category.picUrl = "";
                });

        },

        update: function (event) {
            var id = getSelectedRowId("id");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
            // vm.getCategory();
        },
        // 表单验证
        validate: function () {
            var bl = $('form').VF();// 启用验证
            if (!bl) {
                return;
            }
        },
        saveOrUpdate: function (event) {

            var url = vm.category.id == null ? "goods/category/save" : "goods/category/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.category),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else if (r.code ==1 ){
                        alert("分类名重复了")
                        return
                    }else {
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
                    url: baseURL + "goods/category/delete",
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
            $.get(baseURL + "goods/category/info/" + id, function (r) {
                vm.category = r.category;
                $('#picUrl').fileinput('destroy');
                var files = [{
                    caption: '图片',
                    width: "120px",
                    url: window.baseURL + 'goods/category/info/' + id,
                    key: 1,
                    extra: {}
                }]
                console.log("修改",vm.category);
                vm.upload("picUrl",[ vm.category.picUrl],files);

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