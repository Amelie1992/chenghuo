$(function() {
	// 列表
	$("#table").BT({
		url : baseURL + 'wbiao/condition/list',
		columns : [ {
			checkbox : true
		}, {
			title : '成色名称',
			field : 'conditionName'
		}, {
			title : '描述',
			field : 'description'
		}, {
			title : '排序',
			field : 'sort'
		} ],
		// 条件查询
		queryParams : {}
	});
	// 表单提交
	$("form").FM({
		fields : vm.fields,
		success : vm.saveOrUpdate

	})

});

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		condition : {},
		// 验证字段
		fields : {
			conditionName : {
				message : '成色名称验证失败',
				validators : {
					notEmpty : {
						message : '成色名称不能为空'
					},
				},
			},
			description : {
				message : '描述验证失败',
				validators : {
					notEmpty : {
						message : '描述不能为空'
					},
				},
			},
			sort : {
				message : '排序验证失败',
				validators : {
					notEmpty : {
						message : '排序不能为空'
					},
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
			vm.title = "新增";
			vm.condition = {};
		},
		update : function(event) {
			var id = getSelectedRowId("id");
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(id)
		},
		// 表单验证
		validate : function() {
			var bl = $('form').VF();// 启用验证
			if (!bl) {
				return;
			}
		},
		saveOrUpdate : function(event) {
			var url = vm.condition.id == null ? "wbiao/condition/save"
					: "wbiao/condition/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.condition),
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
		del : function(event) {
			var ids = getSelectedRowsId("id");
			if (ids == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "wbiao/condition/delete",
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
		getInfo : function(id) {
			$.get(baseURL + "wbiao/condition/info/" + id, function(r) {
				vm.condition = r.condition;
			});
		},
		reload : function(event) {
			vm.showList = true;
			vm.title = "";
			// 刷新 如需条件查询common.js
			$("#table").BTF5();
		}
	}
});