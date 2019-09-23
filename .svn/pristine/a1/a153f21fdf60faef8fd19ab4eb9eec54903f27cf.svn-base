$(function() {
	// 列表
	$("#table").BT({
		url : baseURL + 'goods/spec/list',
		columns : [ {
			checkbox : true
		}, {
			title : '规格名称',
			field : 'specName',
            align: 'center',
		}, {
			title : '操作',
			align: 'center',
			events: operateEvent,
			formatter: function (value, row, index) {
                return '<a id="btn_data">属性管理</a>'
			}
		}
		],
		// 条件查询
		queryParams : {}
	});

	// 表单提交
	$("#form").FM({
		fields : vm.fields,
		success : vm.saveOrUpdate
	})
    //二级表单提交
    $("#formOne").FM({
        fields: {},
        success: vm.saveOrUpdateData
    })

    $("#table2").BT({
        url: baseURL + 'goods/spec/attributeList',
        columns: [
            {checkbox: true},
            {
                title: '规格名称',
                field: 'specName',
                align: 'center'
            },
            {
                title: '规格排序',
                field: 'sort',
                align: 'center'
            },
            {
                title: '操作', width: '10%', align: 'center', formatter: function (value, row, index) {
                return '<a id="btn_dataUpdate">修改</a>';
            }, events: {
                //修改
                'click #btn_dataUpdate': function (e, value, row, index) {
                    vm.showList2 = false;
                    vm.showList = false;
                    vm.edit = false;
                    vm.edit2 = true;
                    vm.title = "属性修改";
                    $.get(baseURL + "goods/spec/info/" + row.id, function (r) {
                        vm.specs = r.spec;
                    })
                },
            }
            }
        ]
    });

});

function getSelectedRowIdData(dataId) {
    var grid = $('#table2').bootstrapTable('getSelections');
    if (!grid.length) {
        alert("请选择一条记录");
        return;
    }
    var ids = [];
    $.each(grid, function (idx, item) {
        ids[idx] = item[dataId.toString()];
    });
    return ids;
}

//管理属性二级方法
window.operateEvent = {
    //属性的点击事件
    'click #btn_data': function (e, value, row, index) {
        vm.title = '属性管理';
        vm.showList = false;
        vm.edit = false;
        //二级目录列表
        vm.showList2 = true;
        //二级编辑页
        vm.edit2 = false;
        vm.queryParams2.parentId = row.id;
        vm.reloadData();
    },
};

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,//一级列表
        showList2 : false,//二级列表
        edit2 : false,//二级编辑页面
		edit:false,//一级编辑页
		title : null,
		spec : {},
        specs : {},
        queryParams:{},
        queryParams2:{
		    parentId: ""
        },
		// 验证字段
		fields : {
			specName : {
				message : '规格名称验证失败',
				validators : {
					notEmpty : {
						message : '规格名称不能为空'
					},
                    stringLength: {
                        min: 1, max: 50, message: '最大长度50'
                    }
				},
			},sort : {
                message : '规格排序验证失败',
                validators : {
                    notEmpty : {
                        message : '规格排序不能为空'
                    },regexp:{
                        regexp:/^[+]{0,1}(\d+)$/,
                        message:'请输入整数'
                    },stringLength: {
                        min: 1, max: 20, message: '最大长度20'
                    }
                },
            }
		}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
            vm.showList2 = false;
            vm.edit = true;
			vm.edit2 = false;
			vm.title = "新增";
			vm.spec = {};
		},
        adds : function() {
            vm.showList = false;
            vm.showList2 = false;
            vm.edit = false;
            vm.edit2 = true;
            vm.title = "属性新增";
            vm.specs = {};
        },
		update : function(event) {
			var id = getSelectedRowId("id");
			if (id == null) {
				return;
			}
            vm.edit = true;
			vm.showList = false;
            vm.showList2 = false;
            vm.edit2 = false;
			vm.title = "修改";
			vm.getInfo(id)
		},
        updates : function(event) {
            var id = getSelectedRowId("id");
            if (id == null) {
                return;
            }
            vm.edit2 = true;
            vm.showList = false;
            vm.showList2 = false;
            vm.edit = false;
            vm.title = "属性修改";
            vm.getInfos(id)
        },
		// 表单验证
		validate : function() {
			var bl = $('#form').VF();// 启用验证
			if (!bl) {
				return;
			}
		},

        //二级确认提交
        formOneValidate : function () {
            var bl = $('#formOne').VF();
            if (!bl) {
                return;
            }
        },

		saveOrUpdate : function(event) {
            var url = vm.spec.id == null ? "goods/spec/save" : "goods/spec/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.spec),
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功', function(index) {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},

		//二级表单新增修改
        saveOrUpdateData : function(event) {
            var regu =/^[+]{0,1}(\d+)$/;
            var re = new RegExp(regu);

		    if (vm.specs.specName==null||vm.specs.specName=='') {
		        alert("规格属性名称不能为空！")
		        return;
            } else {
		        if (vm.specs.specName.length>50) {
		            alert("规格属性名称不得超过50！")
                    return;
                }
            }
            if (vm.specs.sort==="") {
                alert("规格属性排序不能为空！")
                return;
            } else {
                if (!re.test(vm.specs.sort)) {
                    alert("规格属性排序为正整数！")
                    return;
                }
                if (vm.specs.sort.length>20) {
                    alert("规格属性排序不得超过20！")
                    return;
                }
            }
            var url = vm.specs.id == null ? "goods/spec/attributeSave?parentId="+vm.queryParams2.parentId : "goods/spec/attributeUpdate";
            $.ajax({
                type : "POST",
                url : baseURL + url,
                contentType : "application/json",
                data : JSON.stringify(vm.specs),
                success : function(r) {
                    if (r.code === 0) {
                        alert('操作成功', function(index) {
                            vm.reloadData();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },

		del : function(event) {
			var ids = getSelectedRowsId("id");
			if (ids == null) {
				return;
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "goods/spec/delete",
					contentType : "application/json",
					data : JSON.stringify(ids),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},

		//二级删除
        delData : function() {
            var ids = getSelectedRowIdData("id");
            if (ids == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : baseURL + "goods/spec/delete",
                    contentType : "application/json",
                    data : JSON.stringify(ids),
                    success : function(r) {
                        if (r.code == 0) {
                            alert('操作成功', function(index) {
                                vm.reloadData();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },

		getInfo : function(id) {
			$.get(baseURL + "goods/spec/info/" + id, function(r) {
				vm.spec = r.spec;
			});
		},

		//二级详情
        getInfos : function(id) {
            $.get(baseURL + "goods/spec/info/" + id, function(r) {
                vm.specs = r.spec;
            });
        },

        //二级  属性的返回
        dataBack: function () {
            vm.reload();
            vm.title = "属性管理";
            vm.showList = false;
            vm.showList2 = true;
            vm.edit = false;
            vm.edit2 = false;
        },

        //一级列表的刷新
        reload : function(event) {
            vm.showList = true;
            vm.showList2 = false;
            vm.edit = false;
            vm.edit2 = false;
            vm.title = "规格管理";
            // 刷新 如需条件查询common.js
            $("#table").BTF5(vm.queryParams);
            $("form").RF();
        },

        //二级列表的刷新
        reloadData: function (event) {
            vm.title = "属性管理"
            vm.showList2 = true;
            vm.showList = false;
            vm.edit = false;
            vm.edit2 = false;
            $("#table2").BTF5(vm.queryParams2);
            $("form").RF();
        },

        //页面强刷
        reloadTwo: function () {
            vm.reload();
        }
	}
});