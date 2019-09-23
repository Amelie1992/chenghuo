$(function() {
	// 列表
	$("#table").BT({
		url : baseURL + 'goods/property/list',
		columns : [ {
			checkbox : true
		}, {
			title : '属性名称',
			field : 'propertyName'
		}, {
			title : '分类',
			field : 'category.categoryName'
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
		property : {
			categoryId: ""
		},
		categoryList:[],
		// 验证字段
		fields : {
			propertyName : {
				message : '属性名称验证失败',
				validators : {
					notEmpty : {
						message : '属性名称不能为空'
					},
				},
			},
			categoryId : {
				message : '分类验证失败',
				validators : {
					notEmpty : {
						message : '分类不能为空'
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
			vm.property = {
                categoryId:""
			};
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
			var url = vm.property.id == null ? "goods/property/save"
					: "goods/property/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.property),
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
					url : baseURL + "goods/property/delete",
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
			$.get(baseURL + "goods/property/info/" + id, function(r) {
				vm.property = r.property;
			});
		},
		reload : function(event) {
			vm.showList = true;
			vm.title = "";
			$("form").RF();
			// 刷新 如需条件查询common.js
			$("#table").BTF5();
		},
		getCategory : function() {
			// 加载菜单树
			$.get(baseURL + "goods/category/all", function(r) {
				vm.categoryList = r.list;
			})
		}
	},
	created : function() {
		this.getCategory();
	}
});