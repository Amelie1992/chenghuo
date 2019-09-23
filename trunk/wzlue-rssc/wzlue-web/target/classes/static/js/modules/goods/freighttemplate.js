$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'order/freighttemplate/list',
        columns: [
            {checkbox: true},
			{ title: '模板名称', field: 'templateName'}			
        ],
		//条件查询
        queryParams:{}
    });
    //表单提交
	$("form").FM({
        fields : vm.fields,
        success : vm.saveOrUpdate

	})

});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		freightTemplate: {
			freightList: [
			   {
				   address: "",
				   price: ""
			   }       
			]
		},
		//验证字段
		fields :{
				templateName : {
						message :'模板名称验证失败',
						validators: {
							notEmpty: {
								message: '模板名称不能为空'
							},
						},
					}									
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		addFreight: function(){
			var freight = {
			   address: "",
			   price: ""
			}
			vm.freightTemplate.freightList.push(freight)
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.freightTemplate = {
					freightList: [
							   {
								   address: "",
								   price: ""
							   }       
							]
			};
		},
		update: function (event) {
           	var id = getSelectedRowId("id");
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		//表单验证
        validate: function () {
            var bl = $('form').VF();//启用验证
            if (!bl) {
                return;
            }
        },
		saveOrUpdate: function (event) {
			var url = vm.freightTemplate.id == null ? "order/freighttemplate/save" : "order/freighttemplate/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.freightTemplate),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRowsId("id");
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "order/freighttemplate/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
                                vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "order/freighttemplate/info/"+id, function(r){
                vm.freightTemplate = r.freightTemplate;
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.title = "";
			//刷新 如需条件查询common.js
            $("#table").BTF5();
		}
	}
});