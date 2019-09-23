$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'shop/sysshop/list',
        columns: [
            {checkbox: true},
			{ title: '用户id', field: 'userId'}, 			
			{ title: '店铺名称', field: 'shopName'}, 			
			{ title: '电话', field: 'tel'}, 			
			{ title: '店铺logo', field: 'logo'}, 			
			{ title: '省', field: 'province'}, 			
			{ title: '城市', field: 'city'}, 			
			{ title: '区', field: 'county'}, 			
			{ title: '详细地址', field: 'street'}, 			
			{ title: '店铺描述', field: 'description'}, 			
			{ title: '', field: 'createTime'}, 			
			{ title: '0：未删除 1：已删除', field: 'delFlag'}			
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
		showList: false,
		title: null,
		sysShop: {
            street:""
		},
		//验证字段
		fields :{
																							userId : {
						message :'用户id验证失败',
						validators: {
							notEmpty: {
								message: '用户id不能为空'
							},
						},
					}, 																shopName : {
						message :'企业名称验证失败',
						validators: {
							notEmpty: {
								message: '企业名称不能为空'
							},
						},
					}, 																tel : {
						message :'电话验证失败',
						validators: {
							notEmpty: {
								message: '电话不能为空'
							},
						},
					}, 																logo : {
						message :'企业logo验证失败',
						validators: {
							notEmpty: {
								message: '企业logo不能为空'
							},
						},
					}, 																province : {
						message :'省验证失败',
						validators: {
							notEmpty: {
								message: '省不能为空'
							},
						},
					}, 																city : {
						message :'城市验证失败',
						validators: {
							notEmpty: {
								message: '城市不能为空'
							},
						},
					}, 																county : {
						message :'区验证失败',
						validators: {
							notEmpty: {
								message: '区不能为空'
							},
						},
					}, 																street : {
						message :'详细地址验证失败',
						validators: {
							notEmpty: {
								message: '详细地址不能为空'
							},
						},
					}, description : {
						message :'企业描述验证失败',
						validators: {
							notEmpty: {
								message: '企业描述不能为空'
							},
						},
					},cooperation : {
                       message :'合作伙伴验证失败',
                       validators: {
                       notEmpty: {
                        message: '合作伙伴不能为空'
                    },
                },
            }, 	createTime : {
						message :'验证失败',
						validators: {
							notEmpty: {
								message: '不能为空'
							},
						},
					}, 																delFlag : {
						message :'0：未删除 1：已删除验证失败',
						validators: {
							notEmpty: {
								message: '0：未删除 1：已删除不能为空'
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
			vm.sysShop = {};
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
                vm.saveOrUpdate();
                return;
            }
        },

		saveOrUpdate: function (event) {
			// var url = vm.sysShop.id == null ? "shop/sysshop/save" : "shop/sysshop/update";
            var url =  "shop/sysshop/update";
            vm.sysShop.id=1;

            vm.sysShop.description=ue.getContent();
            //合作伙伴的信息
            vm.sysShop.cooperation=ueTwo.getContent();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.sysShop),
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
				    url: baseURL + "shop/sysshop/delete",
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
			$.get(baseURL + "shop/sysshop/info/"+id, function(r){
                vm.sysShop = r.sysShop;
                console.log("查詢頂頂頂頂頂",vm.sysShop);
                $('#logo').fileinput('destroy');
                vm.upload("logo",[vm.sysShop.logo]);

                console.log("======================");
                ue.addListener("ready", function () {
                    ue.setContent(vm.sysShop.description); // 确保UE加载完成后，放入内容。
                });
                ueTwo.addListener("ready", function () {
                    ueTwo.setContent(vm.sysShop.cooperation); // 确保UE加载完成后，放入内容。
                });

            });
		},
        upload:function(inputName, src){
            var _this=this;
            var files = [{
                caption: '图片',
                width: "120px",
                url: window.baseURL + 'shop/sysshop/info/' + 1,
                key: 1,
                extra: {}
            }]
            //上传图片
                window.initUploadone(inputName, window.baseURL + "app/file/upload", {
                    'jpg': '<i class="fa fa-file-excel-o text-success"></i>',
                    'gif': '<i class="fa fa-file-excel-o text-success"></i>',
                    'png': '<i class="fa fa-file-excel-o text-success"></i>',
                }, ["jpg", "gif", "png"], src, files)
                    .on("fileuploaded", function (event, data, id) {
                        vm.sysShop.logo = data.response.url;
                    })
                    .on("filesuccessremove", function (event, previewId, data) {
                        // vm.fileList.splice(vm.fileList.indexOf(data))
                        vm.sysShop.logo = "";
                    })
                    .on('filepredelete', function(event, key, jqXHR, data) {
                        vm.sysShop.logo = "";
                    });

        },
		reload: function (event) {
			vm.showList = false;
			vm.title = "";
			//刷新 如需条件查询common.js
            // $("#table").BTF5();
		}
	},
	mounted: function(){

        this.getInfo(1);
		this.upload("logo", [this.sysShop.logo]);
        // ue.setContext(this.sysShop.description,true);
		// ue.setContext(vm.sysShop.city)


     },

});


// $(function(){
//     // 图片上传
//     var url = "/app/file/upload";
//     $('#fileupload').fileupload({
//         url: url,
//         dataType: 'json',
//         acceptFileTypes:  /(\.|\/)(gif|jpe?g|png)$/i,
//         maxFileSize: 153600,
//         done: function (e, data) {
//             if(data.result.code == 0){
//                 vm.goods.picUrls.push(data.result.url);
//             }
//         }
//     }).prop('disabled', !$.support.fileInput)
//         .parent().addClass($.support.fileInput ? undefined : 'disabled');
// })
// var ue = UE.getEditor('container',{
//     toolbars: [
//         ['fullscreen', 'source', 'undo', 'redo', 'bold', 'italic', 'underline', 'fontborder', 'simpleupload',
//             'insertvideo', 'justifyleft', 'justifyright', 'justifycenter', 'fontfamily', 'fontsize']
//     ],
//     autoHeightEnabled: true,
//     autoFloatEnabled: true,
//     initialFrameHeight: 400
// });

// 百度地图API功能
var map = new BMap.Map("allmap", {
    enableMapClick : false
}); // 创建地图实例
var point = new BMap.Point(116.331398,39.897445); // 创建点坐标
map.centerAndZoom(point,11); // 初始化地图，设置中心点坐标和地图级别
map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

map.addControl(new BMap.NavigationControl());
map.addControl(new BMap.NavigationControl());
map.addControl(new BMap.ScaleControl());
map.addControl(new BMap.OverviewMapControl());
map.addControl(new BMap.MapTypeControl());
map.setCurrentCity("北京"); // 仅当设置城市信息时，MapTypeControl的切换功能才能可用

var add="";    //地址
var lng="";    //经度
var lat="";    //维度
var marker = new BMap.Marker(point);        // 创建标注
marker.enableDragging();
var geoc = new BMap.Geocoder();
marker.addEventListener("dragend", function(e){//您可以监听标注的dragend事件来捕获拖拽后标注的最新位置
    var pt = e.point;
    geoc.getLocation(pt, function(rs){
        var addComp = rs.addressComponents;
        alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
        add=addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
        vm.sysShop.province=addComp.province;
        vm.sysShop.city=addComp.city;
        vm.sysShop.county=addComp.district;
        vm.sysShop.street=addComp.province+addComp.city+addComp.district+addComp.street+ addComp.streetNumber;
        vm.sysShop.lng=e.point.lng;
        vm.sysShop.lat=e.point.lat;
        console.log("地图获取经纬度", vm.sysShop);
    });
    // alert("当前位置：" + e.point.lng + ", " + e.point.lat);


})
map.addOverlay(marker);                     // 将标注添加到地图中

map.addEventListener("click", function(e){
    marker.remove();
    point=new BMap.Point(e.point.lng,e.point.lat); // 创建点坐标
    marker=new BMap.Marker(point);
    marker.enableDragging();
    marker.addEventListener("dragend", function(e){
        var pt = e.point;
        geoc.getLocation(pt, function(rs){
            var addComp = rs.addressComponents;
            alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
            add=addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
            vm.sysShop.province=addComp.province;
            vm.sysShop.city=addComp.city;
            vm.sysShop.county=addComp.district;
            vm.sysShop.street=addComp.province+addComp.city+addComp.district+addComp.street+ addComp.streetNumber;
            vm.sysShop.lng=e.point.lng;
            vm.sysShop.lat=e.point.lat;
            console.log("地图获取经纬度", vm.sysShop);
        });
    })
    map.addOverlay(marker);
});