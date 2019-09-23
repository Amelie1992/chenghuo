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
                    member: {}
				},
				// 验证字段
                fields : {
                    lose : {
                        validators : {
                            notEmpty : {
                                message : '不能为空'
                            },regexp : {
                                regexp : /^[1-9]\d{0,8}$/,
                                message : '请输入小于10位的正整数'
                            }
                        }
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
					var params = {key: "MEMBERSHIP_SETTING", value: JSON.stringify(vm.config)};
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
					$.get(baseURL + "sys/config/getByKey", {key: "MEMBERSHIP_SETTING"},
					function(r) {
						vm.config = JSON.parse(r.value);
						if(vm.config.member.state){
							$("input:checkbox[name='memberState']").iCheck('check');
						}
					});
				}
			},
			created: function(){
				this.getInfo();
			}
		});