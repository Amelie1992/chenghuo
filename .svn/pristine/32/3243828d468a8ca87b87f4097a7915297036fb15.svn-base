$(function() {
	// 列表
	$("#table").BT({
		url : baseURL + 'wbiao/recovery/list',
		columns : [ {
			checkbox : true
		}, {
			title : '编号',
			field : 'recoveryNumber'
		}, {
			title : '品牌',
			field : 'brand.brandName'
		}, {
			title : '成色',
			field : 'condition.conditionName'
		}, {
			title : '功能状态',
			field : 'functionStatus'
		}, {
			title : '申请时间',
			field : 'createTime'
		}, {
			title : '状态',
			field : 'status', 
			formatter : function(value, row, index) {
				if (value == 1) {
					return "处理中";
				} else if (value == 2) {
					return "已处理";
				} else {
					return "-";
				}
			}
		}, {
			title : '处理结果',
			field : 'applyResult'
		}, {
			title : '操作',
			field : '', formatter : function(value, row, index) {
				return '<a onclick="vm.detail('+row.id+')">查看</a>'
			}
		}
		],
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
		recovery : {
			condition: {},
			brand: {}
		},
		queryParams: {
			name:''
		},
		// 验证字段
		fields : {
			recoveryNumber : {
				message : '编号验证失败',
				validators : {
					notEmpty : {
						message : '编号不能为空'
					},
				},
			},
			brandId : {
				message : '品牌id验证失败',
				validators : {
					notEmpty : {
						message : '品牌id不能为空'
					},
				},
			},
			condition : {
				message : '成色验证失败',
				validators : {
					notEmpty : {
						message : '成色不能为空'
					},
				},
			},
			status : {
				message : '状态：1处理中，2已处理验证失败',
				validators : {
					notEmpty : {
						message : '状态：1处理中，2已处理不能为空'
					},
				},
			},
			functionStatus : {
				message : '功能状态验证失败',
				validators : {
					notEmpty : {
						message : '功能状态不能为空'
					},
				},
			},
			createTime : {
				message : '申请时间验证失败',
				validators : {
					notEmpty : {
						message : '申请时间不能为空'
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
			vm.recovery = {};
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
		
		detail: function(id){
			vm.showList = false;
			vm.title = "详情";
			vm.getInfo(id)
		},
		
		// 表单验证
		validate : function() {
			var bl = $('form').VF();// 启用验证
			if (!bl) {
				return;
			}
		},
		
		handle: function(){
			var url = "wbiao/recovery/handle";
			var params = {id: vm.recovery.id, applyResult: vm.recovery.applyResult};
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(params),
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
		
		saveOrUpdate : function(event) {
			var url = vm.recovery.id == null ? "wbiao/recovery/save"
					: "wbiao/recovery/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.recovery),
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
					url : baseURL + "wbiao/recovery/delete",
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
			$.get(baseURL + "wbiao/recovery/info/" + id, function(r) {
				vm.recovery = r.recovery;
			});
		},
		reload : function(event) {
			vm.showList = true;
			vm.title = "";
			// 刷新 如需条件查询common.js
			$("#table").BTF5(vm.queryParams);
            $("form").RF();
		}
	}
});