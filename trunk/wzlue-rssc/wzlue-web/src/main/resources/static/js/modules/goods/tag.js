$(function() {
	// 列表
	$("#table").BT({
		url : baseURL + 'goods/tag/list',
		columns : [ {
			checkbox : true
		}, {
			title : '标签名称',
			field : 'tagName'
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

var vm = new Vue(
		{
			el : '#rrapp',
			data : {
				showList : true,
				title : null,
				tag : {},
				// 验证字段
				fields : {
					tagName : {
						message : '标签名称验证失败',
						validators : {
							notEmpty : {
								message : '标签名称不能为空'
							},
						},
					},
					sort : {
						message : '排序验证失败',
						validators : {
							notEmpty : {
								message : '排序不能为空'
							},regexp:{
                            regexp:/^\d+$/,
                            message:'只能输入数字'
                        }
						},
					},
					createTime : {
						message : '创建时间验证失败',
						validators : {
							notEmpty : {
								message : '创建时间不能为空'
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
					vm.tag = {};
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
					var url = vm.tag.id == null ? "goods/tag/save"
							: "goods/tag/update";
					$.ajax({
						type : "POST",
						url : baseURL + url,
						contentType : "application/json",
						data : JSON.stringify(vm.tag),
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
							url : baseURL + "goods/tag/delete",
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
					$.get(baseURL + "goods/tag/info/" + id, function(r) {
						vm.tag = r.tag;
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