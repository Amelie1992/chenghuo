$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'order/freight/list',
        columns: [
            {checkbox: true},
			{ title: '模板id', field: 'templateId'}, 			
			{ title: '地址', field: 'address'}, 			
			{ title: '价格', field: 'price'}			
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
		freight: {},
		//验证字段
		fields :{
																							templateId : {
						message :'模板id验证失败',
						validators: {
							notEmpty: {
								message: '模板id不能为空'
							},
						},
					}, 																address : {
						message :'地址验证失败',
						validators: {
							notEmpty: {
								message: '地址不能为空'
							},
						},
					}, 																price : {
						message :'价格验证失败',
						validators: {
							notEmpty: {
								message: '价格不能为空'
							},
						},
					}									}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.freight = {};
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
			var url = vm.freight.id == null ? "order/freight/save" : "order/freight/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.freight),
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
				    url: baseURL + "order/freight/delete",
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
			$.get(baseURL + "order/freight/info/"+id, function(r){
                vm.freight = r.freight;
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