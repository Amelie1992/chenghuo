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
                    codingRule: {}
				},
				// 验证字段
				fields : {
					front : {
						validators : {
							notEmpty : {
								message : '前缀不能为空'
							},regexp : {
								regexp : /^[A-Z]{3,5}$/,
								message : '请输入3至5位的大写英文字母'
							}
						},
					},
					tail : {
						validators : {
							notEmpty : {
								message : '检验位不能为空'
							},regexp : {
                                regexp : /^[1-3]$/,
                                message : '请输入小于4的正整数'
                            }
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
					var params = {key: "CODING_RULE", value: JSON.stringify(vm.config)};
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
					$.get(baseURL + "sys/config/getByKey", {key: "CODING_RULE"},
					function(r) {
						vm.config = JSON.parse(r.value);
						if(vm.config.codingRule.front){
							$("input:checkbox[name='front']").iCheck('check');
						}
						if(vm.config.codingRule.tail){
							$("input:checkbox[name='tail']").iCheck('check');
						}
					});
				}
			},
			created: function(){
				this.getInfo();
			}
		});