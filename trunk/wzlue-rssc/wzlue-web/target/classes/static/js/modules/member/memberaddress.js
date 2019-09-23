$(function() {
	// 列表
	$("#table").BT({
		url : baseURL + 'member/memberaddress/list',
		columns : [ {
			checkbox : true
		}, {
			title : '姓名',
			field : 'userName'
		}, {
			title : '手机号码',
			field : 'telNumber'
		}, {
			title : '省',
			field : 'province'
		}, {
			title : '市',
			field : 'city'
		}, {
			title : '区',
			field : 'county'
		}, {
			title : '街道',
			field : 'street'
		}, {
			title : '邮编',
			field : 'zipCode'
		}, {
			title : '是否默认',
			field : 'defaultAddress',
			formatter : function(value, row, index) {
				if (value == 1) {
					return "是";
				} else if (value == 0) {
					return "否";
				} else {
					return "-";
				}
			}
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
		memberAddress : {},
		queryParams: {},
		// 验证字段
		fields : {
			memberId : {
				message : '会员id验证失败',
				validators : {
					notEmpty : {
						message : '会员id不能为空'
					},
				},
			},
			userName : {
				message : '姓名验证失败',
				validators : {
					notEmpty : {
						message : '姓名不能为空'
					},
				},
			},
			province : {
				message : '省验证失败',
				validators : {
					notEmpty : {
						message : '省不能为空'
					},
				},
			},
			city : {
				message : '市验证失败',
				validators : {
					notEmpty : {
						message : '市不能为空'
					},
				},
			},
			county : {
				message : '区验证失败',
				validators : {
					notEmpty : {
						message : '区不能为空'
					},
				},
			},
			street : {
				message : '街道验证失败',
				validators : {
					notEmpty : {
						message : '街道不能为空'
					},
				},
			},
			zipCode : {
				message : '邮编验证失败',
				validators : {
					notEmpty : {
						message : '邮编不能为空'
					},
				},
			},
			telNumber : {
				message : '手机号码验证失败',
				validators : {
					notEmpty : {
						message : '手机号码不能为空'
					},
				},
			},
			defaultAddress : {
				message : '默认收货地址：1是，0否验证失败',
				validators : {
					notEmpty : {
						message : '默认收货地址：1是，0否不能为空'
					},
				},
			},
			delFlag : {
				message : '0：未删除 1：已删除验证失败',
				validators : {
					notEmpty : {
						message : '0：未删除 1：已删除不能为空'
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
			vm.memberAddress = {};
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
			var url = vm.memberAddress.id == null ? "member/memberaddress/save"
					: "member/memberaddress/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.memberAddress),
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
					url : baseURL + "member/memberaddress/delete",
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
			$.get(baseURL + "member/memberaddress/info/" + id, function(r) {
				vm.memberAddress = r.memberAddress;
			});
		},
		reload : function(event) {
			vm.showList = true;
			vm.title = "";
			// 刷新 如需条件查询common.js
			$("#table").BTF5(vm.queryParams);
		}
	}
});