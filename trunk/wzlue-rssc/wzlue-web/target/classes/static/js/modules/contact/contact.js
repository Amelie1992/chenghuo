$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'contact/wcontact/list',
        columns: [
            {checkbox: true},
			{ title: '联系人', field: 'name'},
			{ title: '联系电话', field: 'tel'},
			{ title: '内容', field: 'remark'},
            { title: '添加时间', field: 'createTime'}
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
		wContact: {},
        q:{
            tel: null
        },
		//验证字段
		fields :{
																							name : {
						message :'验证失败',
						validators: {
							notEmpty: {
								message: '不能为空'
							},
						},
					}, 																tel : {
						message :'验证失败',
						validators: {
							notEmpty: {
								message: '不能为空'
							},
						},
					}, 																remark : {
						message :'验证失败',
						validators: {
							notEmpty: {
								message: '不能为空'
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
			vm.wContact = {};
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
			var url = vm.wContact.id == null ? "contact/wcontact/save" : "contact/wcontact/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.wContact),
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
				    url: baseURL + "contact/wcontact/delete",
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
			$.get(baseURL + "contact/wcontact/info/"+id, function(r){
                vm.wContact = r.wContact;
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.title = "";
			//刷新 如需条件查询common.js
            $("#table").BTF5({
				tel:vm.q.tel
			});
		}
	}
});