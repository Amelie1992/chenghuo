$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'order/ordergoods/list',
        columns: [
            {checkbox: true},
			{ title: '订单id', field: 'orderNumer'}, 			
			{ title: '商品id', field: 'goodsId'}, 			
			{ title: '商品名称', field: 'goodsName'}, 			
			{ title: '商品图片', field: 'picUrl'}, 			
			{ title: '购买数量', field: 'buyNum'}, 			
			{ title: '商品原价', field: 'price'}			
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
		orderGoods: {},
		//验证字段
		fields :{
																							orderNumer : {
						message :'订单id验证失败',
						validators: {
							notEmpty: {
								message: '订单id不能为空'
							},
						},
					}, 																goodsId : {
						message :'商品id验证失败',
						validators: {
							notEmpty: {
								message: '商品id不能为空'
							},
						},
					}, 																goodsName : {
						message :'商品名称验证失败',
						validators: {
							notEmpty: {
								message: '商品名称不能为空'
							},
						},
					}, 																picUrl : {
						message :'商品图片验证失败',
						validators: {
							notEmpty: {
								message: '商品图片不能为空'
							},
						},
					}, 																buyNum : {
						message :'购买数量验证失败',
						validators: {
							notEmpty: {
								message: '购买数量不能为空'
							},
						},
					}, 																price : {
						message :'商品原价验证失败',
						validators: {
							notEmpty: {
								message: '商品原价不能为空'
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
			vm.orderGoods = {};
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
			var url = vm.orderGoods.id == null ? "order/ordergoods/save" : "order/ordergoods/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderGoods),
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
				    url: baseURL + "order/ordergoods/delete",
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
			$.get(baseURL + "order/ordergoods/info/"+id, function(r){
                vm.orderGoods = r.orderGoods;
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