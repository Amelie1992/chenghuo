$(function () {
    //列表
    $("#table").BT({
        url: baseURL + 'goods/merchantAddress/list',
        columns: [
            {checkbox: true},
            {title: '发货人姓名', field: 'name'},
            {title: '发货人手机号', field: 'phone'},
            {title: '省', field: 'provinceName'},
            {title: '市', field: 'cityName'},
            {title: '区', field: 'countyName'},
            {title: '街道(详细地址)', field: 'street'},
            {title: '邮编', field: 'zipCode'},
            {
                title: '状态', field: 'status',
                formatter: function (value, row, index) {
                    return value == 1 ? '<span class="label label-primary">可用</span>' : '<span class="label label-default">禁用</span>';
                }
            }
        ],
        //条件查询
        queryParams: {}
    });
    //表单提交
    $("form").FM({
        fields: vm.fields,
        success: vm.saveOrUpdate

    })

});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        disabledAll: false,
        title: null,
        merchantAddress: {},
        queryParams: {},
        provinceList: [],
        cityList: [],
        areasList: [],
        //验证字段
        fields: {
            name: {
                message: '发货人姓名验证失败',
                validators: {
                    notEmpty: {
                        message: '发货人姓名不能为空'
                    },
                },
            }, phone: {
                message: '发货人手机号验证失败',
                validators: {
                    notEmpty: {
                        message: '发货人手机号不能为空'
                    }, regexp: {
                        regexp: /^1\d{10}$/,
                        message: '手机号格式错误'
                    }
                },
            },
            province: {
                message: '省验证失败',
                validators: {
                    notEmpty: {
                        message: '省不能为空'
                    },
                },
            }, city: {
                message: '市验证失败',
                validators: {
                    notEmpty: {
                        message: '市不能为空'
                    },
                },
            }, county: {
                message: '区验证失败',
                validators: {
                    notEmpty: {
                        message: '区不能为空'
                    },
                },
            },
            street: {
                message: '街道(详细地址)验证失败',
                validators: {
                    notEmpty: {
                        message: '街道(详细地址)不能为空'
                    },
                },
            }, zipCode: {
                message: '邮编验证失败',
                validators: {
                    notEmpty: {
                        message: '邮编不能为空'
                    },
                },
            }
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.merchantAddress = {
                status: 1,
                province: "",
                city: "",
                county: ""
            };
            vm.cityList = [],
                vm.areasList = []
        },
        update: function (event) {
            var id = getSelectedRowId("id");
            if (id == null) {
                return;
            }
            vm.disabledAll = false;
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
            var objS = document.getElementById("city");
            var grade = objS.options[objS.selectedIndex].value;
            vm.merchantAddress.city = grade;

            var objSTwo = document.getElementById("county");
            var county = objSTwo.options[objSTwo.selectedIndex].value;
            vm.merchantAddress.county = county;
            var url = vm.merchantAddress.id == null ? "goods/merchantAddress/save" : "goods/merchantAddress/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.merchantAddress),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRowsId("id");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "goods/merchantAddress/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "goods/merchantAddress/info/" + id, function (r) {
                vm.merchantAddress = r.merchantAddress;
            });
        },

        //查询省
        getProvince: function () {
            $.get(baseURL + "sys/provinces/city/-1", function (r) {
                vm.provinceList = r.sysCityInfoEntityList;
            });
        },
        //修改市
        updateCity: function (cityCode) {
            //如果不为空进行监听
            if (!isBlank(cityCode)) {
                $.get(baseURL + "sys/provinces/city/" + cityCode, function (r) {
                    vm.cityList = r.sysCityInfoEntityList;
                });
            }

        },
        //修改区
        updateAreas: function (cityCode) {
            if (!isBlank(cityCode)) {
                $.get(baseURL + "sys/provinces/city/" + cityCode, function (r) {
                    vm.areasList = r.sysCityInfoEntityList;
                });
            }
        },
        cityCountyClear: function () {
            vm.merchantAddress.city = ""
            vm.merchantAddress.county = ""
        },
        //改变获取区
        gradeChange: function () {
            var objS = document.getElementById("city");
            var grade = objS.options[objS.selectedIndex].value;
            vm.merchantAddress.city = grade;
            if (grade != "" && grade != undefined) {
                $.ajax({
                    type: "GET",
                    url: "/sys/provinces/city/" + grade,
                    dataType: "json",
                    success: function (data) {
                        vm.areasList = data.sysCityInfoEntityList;

                    },

                })
            }
            $("#city").val(vm.merchantAddress.city);
            vm.merchantAddress.county = ""

        },


        reload: function (event) {
            vm.showList = true;
            vm.title = "";
            $("form").RF();
            //刷新 如需条件查询common.js
            $("#table").BTF5(vm.queryParams);
        },

    },

    created: function () {
        //初始化省
        this.getProvince();

    },
    updated: function () {

        $("#city").val(vm.merchantAddress.city);
        $("#county").val(vm.merchantAddress.county);
    },
    watch: {
        'merchantAddress.province': {
            handler: function (newName, oldName) {
                this.updateCity(newName);
                if (vm.merchantAddress.province == "") {
                    vm.cityList = [];
                    vm.areasList = [];
                }
                vm.areasList = [];

            },

        },
        'merchantAddress.city': {
            handler: function (newName, oldName) {
                console.log(newName)
                if (vm.merchantAddress.city == "") {
                    vm.areasList = []
                }
                this.updateAreas(newName);
            },

        }

    }
});