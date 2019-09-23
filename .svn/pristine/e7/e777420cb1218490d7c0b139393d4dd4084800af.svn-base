$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'order/orderaddress/list',
        columns: [
            {checkbox: true},
			{ title: '订单编号', field: 'orderNumber'}, 			
			{ title: '收货人', field: 'customerName'}, 			
			{ title: '联系电话', field: 'telephone'}, 			
			{ title: '省份', field: 'province'}, 			
			{ title: '市区', field: 'city'}, 			
			{ title: '县镇', field: 'county'}, 			
			{ title: '街道', field: 'street'}, 			
			{ title: '详细地址', field: 'detailInfo'}			
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
		orderAddress: {},
		//验证字段
		fields :{
																							orderNumber : {
						message :'订单编号验证失败',
						validators: {
							notEmpty: {
								message: '订单编号不能为空'
							},
						},
					}, 																customerName : {
						message :'收货人验证失败',
						validators: {
							notEmpty: {
								message: '收货人不能为空'
							},
						},
					}, 																telephone : {
						message :'联系电话验证失败',
						validators: {
							notEmpty: {
								message: '联系电话不能为空'
							},
						},
					}, 																province : {
						message :'省份验证失败',
						validators: {
							notEmpty: {
								message: '省份不能为空'
							},
						},
					}, 																city : {
						message :'市区验证失败',
						validators: {
							notEmpty: {
								message: '市区不能为空'
							},
						},
					}, 																county : {
						message :'县镇验证失败',
						validators: {
							notEmpty: {
								message: '县镇不能为空'
							},
						},
					}, 																street : {
						message :'街道验证失败',
						validators: {
							notEmpty: {
								message: '街道不能为空'
							},
						},
					}, 																detailInfo : {
						message :'详细地址验证失败',
						validators: {
							notEmpty: {
								message: '详细地址不能为空'
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
			vm.orderAddress = {};
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
			var url = vm.orderAddress.id == null ? "order/orderaddress/save" : "order/orderaddress/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderAddress),
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
				    url: baseURL + "order/orderaddress/delete",
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
			$.get(baseURL + "order/orderaddress/info/"+id, function(r){
                vm.orderAddress = r.orderAddress;
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