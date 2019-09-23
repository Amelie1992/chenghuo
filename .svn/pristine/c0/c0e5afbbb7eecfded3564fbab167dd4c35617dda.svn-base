$(function() {
	// 列表
	$("#table").BT({
		url : baseURL + 'goods/goodsproperty/list',
		columns : [ {
			checkbox : true
		}, {
			title : '商品id',
			field : 'goodsId'
		}, {
			title : '属性id',
			field : 'propertyId'
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
		goodsProperty : {},
		// 验证字段
		fields : {
			goodsId : {
				message : '商品id验证失败',
				validators : {
					notEmpty : {
						message : '商品id不能为空'
					},
				},
			},
			propertyId : {
				message : '属性id验证失败',
				validators : {
					notEmpty : {
						message : '属性id不能为空'
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
			vm.goodsProperty = {};
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
			var url = vm.goodsProperty.id == null ? "goods/goodsproperty/save"
					: "goods/goodsproperty/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.goodsProperty),
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
					url : baseURL + "goods/goodsproperty/delete",
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
			$.get(baseURL + "goods/goodsproperty/info/" + id, function(r) {
				vm.goodsProperty = r.goodsProperty;
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