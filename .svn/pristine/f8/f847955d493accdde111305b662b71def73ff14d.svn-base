$(function() {
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
				showList : false,
				title : null,
				config: {
					consume: {},
					deduction: {},
					signIn: {},
                    popularization: {},
                    sweepCode:{}
				},
				// 验证字段
				fields : {
					ruleType : {
						message : '规则类型验证失败',
						validators : {
							notEmpty : {
								message : '规则类型不能为空'
							},
						},
					},
					lose : {
						message : '失去验证失败',
						validators : {
							notEmpty : {
								message : '不能为空'
							},regexp : {
								regexp : /^[1-9]\d{0,8}$/,
								message : '请输入小于10位的正整数'
							}
						},
					},
					get : {
						message : '获得验证失败',
						validators : {
							notEmpty : {
								message : '不能为空'
							},regexp : {
                                regexp : /^[1-9]\d{0,8}$/,
                                message : '请输入小于10位的正整数'
                            }
						},
					},
                    get2 : {
                        message : '获得验证失败',
                        validators : {
                            notEmpty : {
                                message : '不能为空'
                            },regexp : {
                                regexp : /^[1-9]\d{0,8}$/,
                                message : '请输入小于10位的正整数'
                            }
                        },
                    },
                    get3: {
                        message : '获得验证失败',
                        validators : {
                            notEmpty : {
                                message : '不能为空'
                            },regexp : {
                                regexp : /^[1-9]\d{0,8}$/,
                                message : '请输入小于10位的正整数'
                            }
                        },
                    },
					upperLimit : {
						message : '上限验证失败',
						validators : {
							notEmpty : {
								message : '上限不能为空'
							},
						},
					},
					updateTime : {
						message : '更新时间验证失败',
						validators : {
							notEmpty : {
								message : '更新时间不能为空'
							},
						},
					}
				}
			},
			methods : {
				update : function(event) {
					var id = getSelectedRowId("id");
					if (id == null) {
						return;
					}
					vm.showList = false;
					vm.title = "修改";
				},
				// 表单验证
				validate : function() {
					var bl = $('form').VF();// 启用验证
					if (!bl) {
						return;
					}
				},
				saveOrUpdate : function(event) {
					var url = "sys/config/updateByKey";
					var params = {key: "INTEGRAL_SETTING", value: JSON.stringify(vm.config)};
					$.ajax({
						type : "POST",
						url : baseURL + url,
						contentType : "application/json",
						data : JSON.stringify(params),
						success : function(r) {
							if (r.code === 0) {
								alert('操作成功', function(index) {
									
								});
							} else {
								alert(r.msg);
							}
						}
					});
				},
				getInfo : function() {
					$.get(baseURL + "sys/config/getByKey", {key: "INTEGRAL_SETTING"},
					function(r) {
						vm.config = JSON.parse(r.value);
						if(vm.config.consume.state){
							$("input:checkbox[name='consumeState']").iCheck('check');
						}
						if(vm.config.deduction.state){
							$("input:checkbox[name='deductionState']").iCheck('check');
						}
						if(vm.config.signIn.state){
							$("input:checkbox[name='signInState']").iCheck('check');
						}
                        if(vm.config.popularization.state){
                            $("input:checkbox[name='popularizationState']").iCheck('check');
                        }

					});
				}
			},
			created: function(){
				this.getInfo();
			}
		});