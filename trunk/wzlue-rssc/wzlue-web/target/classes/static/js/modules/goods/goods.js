$(function() {
	// 列表
	$("#table").BT({
		url : baseURL + 'goods/list',
		columns : [ {
			checkbox : true
		}, {
			title : '商品编号',
			field : 'id'
		}, {
			title : '图片',
			field : 'picUrl',
			formatter : function(value, row, index) {
				return '<img width="100px" height="100px" src=' + value + '>';
			}
		}, {
			title : '商品名称',
			field : 'goodsName'
		}, {
			title : '商品分类',
			field : 'category.categoryName'
		}, {
            title : '价格区间',
            field : 'priceSection'
        }, /*{
			title : '非会员价格',
			field : 'price',
            formatter : function(value, row, index) {
			    if(row.specEntity.length>0 ){
                    let specPrice=row.specEntity[0].specPrice
                    return specPrice;
                }
                if (row.specEntity.length==1){
                    let specPrice=row.specEntity[0].specPrice
                    return specPrice;
                }
               return row.price

            }
		}, {
            title : '会员价格',
            field : 'vipPrice',
            formatter : function(value, row, index) {
                if(row.specEntity.length>0){
                    let specVipPrice=row.specEntity[0].specVipPrice;
                    return specVipPrice;
                }
                if (row.specEntity.length==1){
                    let specVipPrice=row.specEntity[0].specVipPrice;
                    return specVipPrice;
                }
                return row.vipPrice

            }
        }*/
             {
			title : '供货商',
			field : 'supplier'
		}, {
			title : '状态 ',
			field : 'status',
			formatter : function(value, row, index) {
				return value == 1 ? '<span class="label label-primary">上架</span>' : '<span class="label label-default">下架</span>';
			}
		}, {
			title : '创建时间 ',
			field : 'createTime'
		} ],
		// 条件查询
		queryParams : {}

	});
	// 表单提交
	$("form").FM({
		fields : vm.fields,
		success : vm.saveOrUpdate

	});

	$("form").click(function(){
	    if(vm.goods.specList!=null){
            if(vm.goods.specList.length>0){//点击空白处显示隐藏判断
                vm.showOneSpec=false
            }else{
                vm.showOneSpec=true
            }
        }

    })


});

var vm = new Vue({
	el : '#rrapp',
	data : {
		step: 1,
        showProList: true,//选择规格窗
		proName:"",//分类名
        proNameTwo:"",//分类名2
        showProListTwo:true,//选择规格窗2
		selectOne:true,//选择弹出框1的确定
        selectTwo:true,//选择弹出框2的确定
        proTable :true,//规格信息表
        showAddressList: true,//选择商品发货地址窗
        AddressTable :true,//规格信息表
		showList : true,
		title : null,
		picShow :true,//图片的显示
        specPicIndex:"",//图片下标
        showOneSpec:true,//零散的规格
		goods : {
			brandId: '',
			categoryId: '',
			//规格集合
            specList:[],
            specEntityList:[],
			//发货地址
			addressId:'',
            address:[],
		},
		specListOne:[],//选择规格1
		specListTwo:[],//选择规格2
        addressList:[],
        queryParams:{
		},
        deptParams: {
        },
		addressParams:{
		},
		shopList : [],
		categoryList : [],
		addEditor : {},// 编辑器对象
		brandList : [],// 品牌集合
		freightTemplateList: [],
        productParams:'',

		tagList: [],
		tags: [],
		propertyList: [],
        ue: UE.getEditor('myEditor', {
            initialFrameHeight: 440,
        }),
		// 验证字段
		fields : {
			goodsName : {
				message : '商品名称验证失败',
				validators : {
					notEmpty : {
						message : '商品名称不能为空'
					},
				},
			},
			categoryId : {
				message : '分类验证失败',
				validators : {
					notEmpty : {
						message : '分类不能为空'
					},
				},
			},
            priceSection : {
                message : '价格区间验证失败',
                validators : {
                    notEmpty : {
                        message : '价格区间不能为空'
                    },
                },
            },
			brandId : {
				message : '品牌验证失败',
				validators : {
					notEmpty : {
						message : '品牌不能为空'
					},
				},
			},
			price : {
				message : '价格验证失败',
				validators : {
					notEmpty : {
						message : '价格不能为空'
					},regexp:{
                        regexp:/(^[1-9](\d{1,7})?(\.\d{1,2})?$)|(^\d\.\d{1,2}$)/,
                        message:'只能输入正整数或小数'
                    }
				},
			},
			/*payPrice : {
				message : '活动价格验证失败',
				validators : {
					notEmpty : {
						message : '活动价格不能为空'
					},regexp:{
                        regexp:/(^[1-9](\d{1,7})?(\.\d{1,2})?$)|(^\d\.\d{1,2}$)/,
                        message:'只能输入正整数或小数'
                    }
				},
			},*/
            vipPrice : {
                message : '会员价格验证失败',
                validators : {
                    notEmpty : {
                        message : '会员价格不能为空'
                    },regexp:{
                        regexp:/(^[1-9](\d{1,7})?(\.\d{1,2})?$)|(^\d\.\d{1,2}$)/,
                        message:'只能输入正整数或小数'
                    }
                },
            },
			stock : {
				message : '库存验证失败',
				validators : {
					notEmpty : {
						message : '库存不能为空'
					}
				},
			}, description: {
                message: '正文验证失败',
                validators: {
                    /*notEmpty: {
                        message: '正文不能为空'
                    },*/
                    callback: function (value, v) {
                        if (isBlank(vm.ue.getContent())) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                },
            }
		}
	},
	methods : {
		/*规格窗*/
        chooseProduct: function () {
        	vm.selectTwo=false;
        	vm.selectOne=true;
            vm.initChooseTable();
            vm.queryProduct();
            $("#chooseProductModal").modal('show');

        },
		/*规格窗2*/
        chooseProductTwo: function () {
            vm.selectOne=false;
            vm.selectTwo=true;
            vm.initChooseTable();
            vm.queryProduct();
            $("#chooseProductModal").modal('show');

        },

        chooseAddress: function () {
            vm.initChooseTable2();
            vm.queryAddress();
            $("#chooseAddressModal").modal('show');

        },

        //规格弹出
        initChooseTable: function () {
            //清空历史
            vm.deptParams.specName=''
            //列表
            $("#table1").BT({
                url: baseURL + 'goods/spec/getPid',
                columns: [
                    {checkbox: true,},
                    {title: '规格总类',field:'specName', align: 'center'},
                    {title: '序号',field:'sort', align: 'center'}
                ],
                //条件查询
                queryParams: vm.deptParams
            });
        },
		//规格的确定
        okSpec:function(){
            obj = document.getElementsByName("specName");//规格1
            objTwo=document.getElementsByName("specNameTwo");//规格2
            check_val = [];//规格1的获取选中
            check_val2=[];//规格2的获取选中
            var ids=[];
            var idsTwo=[];
            var data = [];//总集合
            for(k in obj){
                if(obj[k].checked)
                    ids.push(obj[k].value);
            }
            $.ajax({
                type : "POST",
                url : baseURL + "goods/goodsSpec/getEntity",
                contentType : "application/json",
                async:false,
                data : JSON.stringify(ids),
                success : function(r) {
                    for (var i= 0;i<r.specList.length;i++) {
                        check_val.push({"specId":ids[i],"specName":r.specList[i].specName});
					}



                }
            });

            for(k in objTwo){
                if(objTwo[k].checked)
                    idsTwo.push(objTwo[k].value);
            }
            $.ajax({
                type : "POST",
                url : baseURL + "goods/goodsSpec/getEntity",
                contentType : "application/json",
                async:false,
                data : JSON.stringify(idsTwo),
                success : function(r) {
                    for (var i= 0;i<r.specList.length;i++) {
                        check_val2.push({"specIdTwo":idsTwo[i],"specNameTwo":r.specList[i].specName});
                    }

                    if (check_val.length >0){
                        check_val.forEach(function(item){
                            if (check_val2.length >0){
                                check_val2.forEach(function(item2){
                                    data.push({"specId":item.specId,"specIdTwo":item2.specIdTwo,"specName":item.specName,"specNameTwo":item2.specNameTwo,"specPic":null})
                                })
                            }else{
                                data.push({"specId":item.specId,"specIdTwo":null,"specName":item.specName,"specNameTwo":null,"specPic":null})
                            }
                        })

                    }else{
                        check_val2.forEach(function(item2){
                            data.push({"specId":null,"specIdTwo":item2.specIdTwo,"specName":null,"specNameTwo":item2.specNameTwo,"specPic":null})
                        })

                    }
                    console.log(data)
                    vm.goods.specList.forEach(function(item,index) {
                        data.forEach(function(item2,index2){//现塞的
                          if (item2.specId==item.specId && item2.specIdTwo==item.specIdTwo){
                              data.splice(index2,1);
                          }
                        })
                        data.unshift(item);//数据库的全塞入


                    })

					vm.goods.specList=data;
                    vm.goods.specList.push();
                    if(vm.goods.specList.length>0){
                        vm.showOneSpec=false
                    }
                }
            });


		},
        //选择规格
        selectProduct: function () {
            var grid = $('#table1').bootstrapTable('getSelections');
            if (!grid.length || grid.length>1) {
                alert("请选择一条记录");
                return;
            }
            vm.proName=grid[0].specName;
            if (vm.proName==vm.proNameTwo){
            	alert("此规格分类已存在")
                return
			}
            var productName = "";
            grid.forEach(function (item) {
                //判断有没有重复添加规格
                var flag = vm.goods.specList.some(function (items, index, array) {
                    return item.id == items.id;
                })
                if (!flag) {
                    var one={};
                    //获取父级子类
                    $.get(baseURL + "goods/spec/getSon/"+item.id, function(r) {
                        vm.specListOne= r.specList;
                    })
                    vm.specListOne.push();
                }
            });
            $("#chooseProductModal").modal('hide');
        },
		//规格2的确定
        selectProductTwo:function(){
            var grid = $('#table1').bootstrapTable('getSelections');
            if (!grid.length || grid.length>1) {
                alert("请选择一条记录");
                return;
            }
            vm.proNameTwo=grid[0].specName;
            if (vm.proName==vm.proNameTwo){
                alert("此规格分类已存在")
				return
            }
            var productName = "";
            grid.forEach(function (item) {
                //判断有没有重复添加规格
                var flag = vm.goods.specList.some(function (items, index, array) {
                    return item.id == items.id;
                })
                if (!flag) {
                    var one={};
                    //获取父级子类
                    $.get(baseURL + "goods/spec/getSon/"+item.id, function(r) {
                        vm.specListTwo= r.specList;
                    })
                    vm.specListTwo.push();
                }
            });
            $("#chooseProductModal").modal('hide');
		},
        queryProduct: function () {
            $("#table1").BTF5(vm.deptParams);
            console.log(vm.deptParams)
        },
        removeSpu: function (index) {
            vm.goods.specList.splice(index, 1);
            if (vm.goods.specList.length<1){
              vm.specListOne=[];
              vm.specListTwo=[];
              vm.proName="";
              vm.proNameTwo="";
            }

        },

        //地址弹出
        initChooseTable2: function () {
            //清空历史
            vm.addressParams.name=''
            //列表
            $("#table2").BT({
                url: baseURL + 'goods/merchantAddress/list?status='+1,
                columns: [
                    {checkbox: true,},
                    {title: '发货人姓名', field: 'name'},
                    {title: '发货人手机号', field: 'phone'},
                    {title: '省', field: 'provinceName'},
                    {title: '市', field: 'cityName'},
                    {title: '区', field: 'countyName'},
                    {title: '街道(详细地址)', field: 'street'},
                    {title: '邮编', field: 'zipCode'},
                    {title: '状态', field: 'status',
                        formatter: function (value, row, index) {
                            return value == 1 ? '<span class="label label-primary">可用</span>' : '<span class="label label-default">禁用</span>';
                        }}
                ],
                //条件查询
                queryParams: vm.addressParams
            });
        },
        //选择地址
        selectAddress: function () {
            var grid = $('#table2').bootstrapTable('getSelections');
            if (!grid.length || grid.length>1) {
                alert("请选择一条记录");
                return;
            }
            grid.forEach(function (item) {
                //判断有没有重复添加规格
                var flag = vm.goods.address.some(function (items, index, array) {
                    return item.addressId == items.id;
                })
                if (!flag) {
                	vm.goods.addressId = item.id;
                	vm.addressList.push(item);
                    //vm.goods.address.splice(0, 1,item);
                }
            });
            $("#chooseAddressModal").modal('hide');
        },
        queryAddress: function () {
            $("#table2").BTF5(vm.addressParams);
            console.log(vm.addressParams)
        },
        removeAddress: function (index) {
            vm.addressList.splice(index, 1);
        },

        goodsOn: function(){
			var ids = getSelectedRowsId("id");
			if (ids == null) {
				return;
			}

			confirm('确定要上架选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "goods/goodsOn",
					contentType : "application/json",
					data : JSON.stringify(ids),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		goodsOff: function(){
			var ids = getSelectedRowsId("id");
			if (ids == null) {
				return;
			}

			confirm('确定要下架选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "goods/goodsOff",
					contentType : "application/json",
					data : JSON.stringify(ids),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		// 品牌集合
		getBrand : function() {
			$.get(baseURL + "goods/brand/all", function(r) {
				vm.brandList = r.list;
			})
		},
		getCategory : function() {
			// 加载菜单树
			$.get(baseURL + "goods/category/all", function(r) {
				vm.categoryList = r.list;
			})
		},
		getFreightTemplate : function() {
			// 加载菜单树
			$.get(baseURL + "order/freighttemplate/all", function(r) {
				vm.freightTemplateList = r.list;
			})
		},
		next: function(){
			var step = vm.step;
			vm.step = step + 1;
			if(step == 3){
			}
		},
		prev: function(){
			var step = vm.step;
			vm.step = step - 1;
		},
		query : function() {
			vm.reload();
		},
		add : function() {
		    vm.specListOne=[];
		    vm.specListTwo=[];
		    vm.proName="";
		    vm.proNameTwo="";
			vm.picShow=false;
			vm.showList = false;
            vm.showProList = true;
            showProListTwo:true,
            vm.showAddressList = true;
            vm.proTable = true;
            vm.AddressTable = true;
			vm.title = "新增";
			vm.goods = {
				picUrls : [],
				picUrl : '',
				status : 1,
                sign:1,
				brandId: "",
				categoryId: "",
				freightTemplateId: "",
                description:"",
                specList:[],
				address:[],
                isCard:0
			};
			vm.addressList  = [];
            vm.ue.setContent('');
		},
		update : function(event) {
            vm.specListOne=[];
            vm.specListTwo=[];
            vm.proName="";
            vm.proNameTwo="";
			var id = getSelectedRowId("id");
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.showProList = true;
            showProListTwo:true;
            vm.showAddressList = true;
            vm.proTable = true;
            vm.AddressTable = true;
			vm.title = "修改";
			vm.getInfo(id);
		},
		//所有表格图片上穿
		fileUpload:function(event){
            var index=event.path[2].rowIndex;//获取此事件的下标
            var formData = new FormData();
            formData.append("file",event.currentTarget.files[0]); // 固定格式获取图片信息
            $.ajax({
                url:"/app/file/upload",
                type:"POST",
                cache:false,
                data:formData,
                processData:false,
                contentType:false,
                success:function(data){
                   vm.goods.specList[index-1].specPic=data.url;
                }
            });

		},
		// 表单验证
		validate : function() {
			var bl = $('form').VF();// 启用验证
			if (!bl) {
				return;
			}
		},
		saveOrUpdate : function(event) {
            vm.goods.description = UE.utils.unhtml(this.ue.getContent());
            let ceiShi=this.ue.getContentTxt();//获取纯文本内容
            let photos=UE.utils.unhtml(this.ue.getContent());
            var imgReg = /img.*?(?:>|\/)/gi;
            var arr = photos.match(imgReg);//筛选出图片个数
            // if(arr!=null){
            //     if (arr.length>5){
            //         alert("正文的图片数量请少于6张");
            //         return
            //     }
            // }
            ceiShi=ceiShi.replace(/(^\s+)|(\s+$)/g,"").replace(/(\n)/g, "").replace(/(\t)/g, "").replace(/(\r)/g, "").replace(/<\/?[^>]*>/g, "").replace(/\s*/g, "");//去除空格等筛选

            if (ceiShi.length > 1000) {
                alert("描述不能超出1000字符");
                return;
            }
			var url = vm.goods.id == null ? "goods/save" : "goods/update";

            if (vm.addressList.length == 0) {
                alert("收货地址不能为空");
                return;
            }else if (vm.addressList.length >1) {
                alert("收货地址最多一个");
                return;
			}
			if(vm.goods.picUrls.length == 0){
				alert("请选择商品图片");
				return;
			}
			var price = parseInt(vm.goods.price);//原价
			/*var payPrice = parseInt(vm.goods.payPrice);//活动价
			if (price<payPrice) {
                alert("商品活动价格不得高于原价");
                return;
			}*/
			/*//判断图片格式
            // 后缀获取
            var suffix = '';
            // 获取类型结果
            var result = '';
            try {
                var flieArr = vm.goods.picUrl.split('.');
                suffix = flieArr[flieArr.length - 1];
            } catch (err) {
                suffix = '';
            }
            // fileName无后缀返回 false
            if (!suffix) {
                result = false;
                return result;
            }
            // 图片格式
            var imglist = ['png', 'jpg', 'jpeg', 'gif'];
            // 进行图片匹配
            result = imglist.some(function (item) {
                return item == suffix;
            });
            if (result) {
                result = 'image';
                alert("请上传格式为gif、jpeg、jpg、png的图片");
                return result;
            };*/

            var categoryName = $("#categoryId").find("option:selected").text();
			var brandName = $("#brandId").find("option:selected").text();
			
			vm.goods.keywords = vm.goods.goodsName + categoryName + brandName;
			vm.goods.picUrl = vm.goods.picUrls[0];
			var propertyList = vm.propertyList;
			var goodsPropertyList = [];
			for (var i = 0; i < propertyList.length; i++) {
				console.log(propertyList);
				var id = propertyList[i].id;
				if(!id){
					id = propertyList[i].propertyId;
				}
				goodsPropertyList.push({propertyId: id, goodsId: propertyList[i].goodsId, propertyValue: propertyList[i].propertyValue})
			}
			vm.goods.goodsPropertyList = goodsPropertyList;
			var tagList = [];
			for(var i = 0; i < vm.tags.length; i++){
				var tag = {id: vm.tags[i]};
				tagList.push(tag);
			}
			vm.goods.tagList = tagList;
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.goods),
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功', function(index) {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		del : function(event) {
			var ids = getSelectedRowsId("id");
			if (ids == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "goods/delete",
					contentType : "application/json",
					data : JSON.stringify(ids),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo : function(id) {
            vm.picShow=true;
            vm.ue.setContent('');
			var that = this;
			$.get(baseURL + "goods/info/" + id, function(r) {
				vm.goods = r.goods;
				//规格1
                vm.specListOne=r.specEntityListOne;
                //规格2
                vm.specListTwo=r.specEntityListTwo
                if(r.goodsSpecEntityList.length>0){
                    if (r.goodsSpecEntityList[0].specId==null && r.goodsSpecEntityList[0].specIdTwo==null){
                        vm.goods.specList=[];
                        vm.showOneSpec=true;
                    }else{
                        vm.goods.specList=r.goodsSpecEntityList;
                        vm.showOneSpec=false;
                    }

                }else{
                    vm.goods.specList=[];
                    vm.showOneSpec=true;
                }
                if (r.goods.merchantAddress!=null){
                    vm.goods.address = r.goods.merchantAddress;//发货地址
                }
                vm.addressList = r.goods.merchantAddress;
				vm.propertyList = r.goods.goodsPropertyList;
				vm.tags = [];
				var tagList = r.goods.tagList;
				for(var i = 0; i < tagList.length; i++){
					vm.tags.push(tagList[i].id);
				}
				// 初始化富文本
                vm.ue.setContent(UE.utils.html(vm.goods.description));
			});
		},

		reload : function(event) {
			vm.showList = true;
			vm.title = "";
            vm.ue.setContent('');
			vm.goods = {};
			$("form").RF();
			// 刷新 如需条件查询common.js
			$("#table").BTF5(vm.queryParams);
		},
		chooseCategory: function(){
			var categoryId = vm.goods.categoryId;
			console.log(categoryId);
			
			$.get(baseURL + "goods/property/getByCategoryId?categoryId="+categoryId, function(r) {
				vm.propertyList = r.propertyList;
			})
		},
		getTag: function(){
			$.get(baseURL + "goods/tag/getAll", function(r) {
				vm.tagList = r.tagList;
			})
		}
	},
	created : function() {
		this.getCategory();
		this.getBrand();
		this.getTag();
		this.getFreightTemplate();
	}
});