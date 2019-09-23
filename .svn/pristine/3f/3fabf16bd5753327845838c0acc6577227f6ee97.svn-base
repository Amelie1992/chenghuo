$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'order/orderlogistics/list',
        columns: [
            {checkbox: true},
			{ title: '订单编号', field: 'orderNumber'}, 			
			{ title: '物流公司id', field: 'companyId'}, 			
			{ title: '物流公司', field: 'companyName'}, 			
			{ title: '物流运单号', field: 'logisticsNumber'}			
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
		orderLogistics: {},
		//验证字段
		fields :{
																							orderNumber : {
						message :'订单编号验证失败',
						validators: {
							notEmpty: {
								message: '订单编号不能为空'
							},
						},
					}, 																companyId : {
						message :'物流公司id验证失败',
						validators: {
							notEmpty: {
								message: '物流公司id不能为空'
							},
						},
					}, 																companyName : {
						message :'物流公司验证失败',
						validators: {
							notEmpty: {
								message: '物流公司不能为空'
							},
						},
					}, 																logisticsNumber : {
						message :'物流运单号验证失败',
						validators: {
							notEmpty: {
								message: '物流运单号不能为空'
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
			vm.orderLogistics = {};
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
			var url = vm.orderLogistics.id == null ? "order/orderlogistics/save" : "order/orderlogistics/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderLogistics),
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
				    url: baseURL + "order/orderlogistics/delete",
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
			$.get(baseURL + "order/orderlogistics/info/"+id, function(r){
                vm.orderLogistics = r.orderLogistics;
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