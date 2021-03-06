$(function() {
	// 列表
	$("#table").BT({
		url : baseURL + 'member/integralrecord/list',
		columns : [ {
			checkbox : true
		}, {
			title : '会员昵称',
			field : 'memberInfo.nickName'
		}, {
			title : '分类',
			field : 'type',
            formatter : function(value, row, index) {
                if (value == 1) {
                	return "推广";
				}else if (value == 2) {
                    return "充值";
                }else if (value == 3) {
                    return "消费";
                }else if (value==4){
                    return "购买会员"
                }else if (value==5){
                    return "解冻"
                }else if(value==6){
                    return "取消订单"
				}else if (value ==7){
                    return "退款退货"
				}else if (value==8){
					return "退运费返积分"
				}else if (value==9){
					return "扫码返积分"

				}
            }
		}, {
			title : '积分',
			field : 'integral',
			formatter : function(value, row, index) {
				// return value > 0? "+"+value : "-"+value;
                if (row.type == 3 || row.type==4|| row.type==5 || row.type==6 ) {
                    return "-"+value;
                }else if (row.type == 1 || row.type == 2 || row.type==7 || row.type == 8 || row.type ==9) {
                    return "+"+value;
                }
			}
		},{
            title : '踪迹',
            field : 'trace',
            formatter : function(value, row, index) {
                var str = "";
                if (row.type == 1) {
                    str =  "被推广人：";
                }else if (row.type == 2) {
                    str =  "充值码：";
                }else if (row.type == 3) {
                    str =  "订单编号：";
                }
                return str + value;
            }
        },{
			title : '时间',
			field : 'createTime'
		},{
            title : '备注',
            field : 'remarks'
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
				integralRecord : {},
				queryParams:{},
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
					remarks : {
						message : '备注验证失败',
						validators : {
							notEmpty : {
								message : '备注不能为空'
							},
						},
					},
					integral : {
						message : '积分验证失败',
						validators : {
							notEmpty : {
								message : '积分不能为空'
							},
						},
					},
					createTime : {
						message : '时间验证失败',
						validators : {
							notEmpty : {
								message : '时间不能为空'
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
					vm.integralRecord = {};
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
					var url = vm.integralRecord.id == null ? "member/integralrecord/save"
							: "member/integralrecord/update";
					$.ajax({
						type : "POST",
						url : baseURL + url,
						contentType : "application/json",
						data : JSON.stringify(vm.integralRecord),
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
							url : baseURL + "member/integralrecord/delete",
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
					$.get(baseURL + "member/integralrecord/info/" + id,
							function(r) {
								vm.integralRecord = r.integralRecord;
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