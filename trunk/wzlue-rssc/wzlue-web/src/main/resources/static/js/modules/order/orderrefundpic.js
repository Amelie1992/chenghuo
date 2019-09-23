$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'order/orderrefundpic/list',
        columns: [
            {checkbox: true},
			{ title: '退款编号', field: 'refundNumber'}, 			
			{ title: '图片', field: 'picUrl'}			
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
		orderRefundPic: {},
		//验证字段
		fields :{
																							refundNumber : {
						message :'退款编号验证失败',
						validators: {
							notEmpty: {
								message: '退款编号不能为空'
							},
						},
					}, 																picUrl : {
						message :'图片验证失败',
						validators: {
							notEmpty: {
								message: '图片不能为空'
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
			vm.orderRefundPic = {};
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
			var url = vm.orderRefundPic.id == null ? "order/orderrefundpic/save" : "order/orderrefundpic/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderRefundPic),
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
				    url: baseURL + "order/orderrefundpic/delete",
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
			$.get(baseURL + "order/orderrefundpic/info/"+id, function(r){
                vm.orderRefundPic = r.orderRefundPic;
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