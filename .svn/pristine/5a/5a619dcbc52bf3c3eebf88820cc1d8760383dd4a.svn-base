$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'order/orderinvoice/list',
        columns: [
            {checkbox: true},
			{ title: '订单编号', field: 'orderNumber'}, 			
			{ title: '发票类型', field: 'invoiceType'}, 			
			{ title: '发票抬头', field: 'invoiceTitle'}, 			
			{ title: '税号', field: 'taxNumber'}			
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
		orderInvoice: {},
		//验证字段
		fields :{
																							orderNumber : {
						message :'订单编号验证失败',
						validators: {
							notEmpty: {
								message: '订单编号不能为空'
							},
						},
					}, 																invoiceType : {
						message :'发票类型验证失败',
						validators: {
							notEmpty: {
								message: '发票类型不能为空'
							},
						},
					}, 																invoiceTitle : {
						message :'发票抬头验证失败',
						validators: {
							notEmpty: {
								message: '发票抬头不能为空'
							},
						},
					}, 																taxNumber : {
						message :'税号验证失败',
						validators: {
							notEmpty: {
								message: '税号不能为空'
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
			vm.orderInvoice = {};
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
			var url = vm.orderInvoice.id == null ? "order/orderinvoice/save" : "order/orderinvoice/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderInvoice),
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
				    url: baseURL + "order/orderinvoice/delete",
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
			$.get(baseURL + "order/orderinvoice/info/"+id, function(r){
                vm.orderInvoice = r.orderInvoice;
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