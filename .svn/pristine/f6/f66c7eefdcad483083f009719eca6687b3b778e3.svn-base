//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//请求前缀
var baseURL = "/";

//登录token
var token = localStorage.getItem("token");
if(token == 'null'){
    parent.location.href = baseURL + 'login.html';
}

//jquery全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false,
    headers: {
        "token": token
    },
    xhrFields: {
	    withCredentials: true
    },
    complete: function(xhr) {
        //token过期，则跳转到登录页面
        if(xhr.responseJSON && xhr.responseJSON.code == 401){
            parent.location.href = baseURL + 'login.html';
        }
    }
});

//权限判断
function hasPermission(permission) {
    if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}

//重写alert
window.alert = function(msg, callback){
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//重写confirm式样框
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//选择一条记录
//name 字段名称
function getSelectedRowId(name) {
    var grid = $('#table').bootstrapTable('getSelections');
    console.log(grid);
    if(!grid.length){
    	alert("请选择一条记录");
    	return ;
    }
    
    if(grid.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    
    return grid[0][name.toString()];
}

//选择多条记录
function getSelectedRowsId(name) {
    var grid = $('#table').bootstrapTable('getSelections');
    if(!grid.length){
    	alert("请选择一条记录");
    	return ;
    }
    var ids = [];
    $.each(grid, function(idx, item){
        ids[idx] = item[name.toString()];
    });
    return ids;
}

//选择一条记录
function getSelectedRow() {
  /*  var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
        alert("请选择一条记录");
        return ;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
        alert("只能选择一条记录");
        return ;
    }

    return selectedIDs[0];*/

    var row = $('#table').bootstrapTable('getSelections');
    if (!row.length) {
        alert("请选择一条记录");
        return;
    }
    if (row.length > 1) {
        alert("只能选择一条记录");
        return;
    }
    return row[0];
}

//选择多条记录
function getSelectedRows() {
   /* var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
        alert("请选择一条记录");
        return ;
    }

    return grid.getGridParam("selarrrow");*/

    var row = $('#table').bootstrapTable('getSelections');
    if (!row.length) {
        alert("请选择一条记录");
        return;
    }

    return row;
}

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}

/**
 * 初始化文件上传
 * @param ctrlName 文件上传inputId
 * @param uploadUrl 上传地址
 * @param previewFileIconSettings 文件扩展名
 * @param allowedFileExtensions 上传的文件扩展名
 * 示例：initUpload("excelFile", baseURL + "commodity/commodity/upload", {
        'xlsx': '<i class="fa fa-file-excel-o text-success"></i>',
        'xls': '<i class="fa fa-file-excel-o text-success"></i>',
    }, ["xls", "xlsx"]);
 * @param initialPreview 使用数据键设置初始预览,使用于查看详情回显
 * @param initialPreviewConfig 使用数据键设置初始预览,使用于查看详情回显
 * 文件回显：
 *                 list.forEach(function (item) {
                    files.push({
                        caption: item.picName,
                        width: "120px",
                        url: window.baseURL + 'sys/image/test',
                        key: item.id,
                        extra: {id: item.id}
                    })
                    url.push(item.picUrl)
                })
 $('#file4').fileinput('destroy');
 window.images = files;
 window.initUpload("file4", window.baseURL + "app/file/upload", {
                    'jpg': '<i class="fa fa-file-excel-o text-success"></i>',
                    'gif': '<i class="fa fa-file-excel-o text-success"></i>',
                    'png': '<i class="fa fa-file-excel-o text-success"></i>',
                }, ["jpg", "gif", "png"], url, files);
 */
function initUpload(ctrlName, uploadUrl, previewFileIconSettings, allowedFileExtensions, initialPreview, initialPreviewConfig) {
    var control = $('#' + ctrlName);
    return control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        uploadAsync: true, //默认异步上传
        showCaption: true,//是否显示标题
        textEncoding: 'UTF-8',
        showUpload: true, //是否显示上传按钮
        browseClass: "btn btn-primary", //按钮样式
        allowedFileExtensions: allowedFileExtensions, //接收的文件后缀
        overwriteInitial: false,
        ifshowPreview: true,
        initialPreviewAsData: true,
        initialPreview: initialPreview,
        initialPreviewConfig: initialPreviewConfig,
        maxFileCount: 5,//最大上传文件数限制
        previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',
        showPreview: true, //是否显示预览
        previewFileIconSettings: previewFileIconSettings,
        msgFileNotFound: '文件 "{name}" 未找到!',
        enctype: 'multipart/form-data',
        uploadExtraData: function () {
            var extraValue = "test";
            return {"excelType": extraValue};
        },
    });
}


function initUploadone(ctrlName, uploadUrl, previewFileIconSettings, allowedFileExtensions, initialPreview, initialPreviewConfig) {
    var control = $('#' + ctrlName);
    return control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        uploadAsync: true, //默认异步上传
        showCaption: true,//是否显示标题
        textEncoding: 'UTF-8',
        showUpload: true, //是否显示上传按钮
        browseClass: "btn btn-primary", //按钮样式
        allowedFileExtensions: allowedFileExtensions, //接收的文件后缀
        overwriteInitial: false,
        ifshowPreview: true,
        initialPreviewAsData: true,
        initialPreview: initialPreview,
        initialPreviewConfig: initialPreviewConfig,
        maxFileCount: 1,//最大上传文件数限制
        previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',
        showPreview: true, //是否显示预览
        previewFileIconSettings: previewFileIconSettings,
        msgFileNotFound: '文件 "{name}" 未找到!',
        enctype: 'multipart/form-data',
        uploadExtraData: function () {
            var extraValue = "test";
            return {"excelType": extraValue};
        },
    });
}

/**
 * 初始化table插件,
 *      函数仅仅支持简单的列表 如果是需要添加父级列表请在函数中自行添加
 * @param obj
 * {
         url：请求后台的URL * 必填
         *      method：请求方式 默认get
         *      sortOrder：排序方式 默认 desc
         *      pageSize：每页的记录行数 默认 10
         *      queryParams : 分页参数
         *      pageList：可供选择的每页的行数 默认 [10, 25, 50, 100]
         *      height：行高 默认 600
         *      columns：列表数据 如果html已经设置 可不写
         *      列表数据 格式如下：
         *      [
                {
                    title : '序号',   //列名
                    field: 'userNumber',    //entity字段值
                    formatter:function (value, row, index) {

                        return index + 1;
                    }, //由于formatter属于回调函数 只需要定义无需执行
                    //定义事件
                    events : {
                        'click .edit' : function(e, value, row, index) {
                            //自动弹出弹出层
                            layer_show('标题', 路径, 宽度, 长度)
                        }
                    }
                },...
            ]*
      * }
 * 参考文档：https://www.cnblogs.com/landeanfen/p/4976838.html
 */
$.fn.BT = function(obj){
   return $(this).bootstrapTable({
        url: obj.url,                           //请求后台的URL（*）
        method: obj.method ? obj.method : 'get',//请求方式（*）
        // toolbar: '#toolbar',                    //工具按钮用哪个容器
        striped: true,                          //是否显示行间隔色
        cache: obj.cache ? obj.cache : true,    //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                       //是否显示分页（*）
        sortable: false,                         //是否启用排序
        sortOrder: obj.sortOrder ? obj.sortOrder : "desc",//排序方式
        sidePagination: 'server',               //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                          //初始化加载第一页，默认第一页
        pageSize: obj.pageSize ? pageSize : 10, //每页的记录行数（*）
        queryParams : function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                page: Math.ceil((params.offset + 1)/params.limit),  //页码
            };
            console.log(obj.queryParams);
        //对象合并
        temp = Object.assign(temp, (obj.queryParams || {}));
            console.log(temp);
        return temp;
    },
        pageList: obj.pageList ? obj.pageList : [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                      //是否显示表格搜索，此搜索是客户端搜索
        showColumns: false,                      //是否显示所有的列
        showRefresh: false,                      //是否显示刷新按钮
        clickToSelect: true,                    //是否启用点击选中行
        height: obj.height ? obj.height : 650,  //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                         //每一行的唯一标识，一般为主键列
        showToggle: false,                       //是否显示详细视图和列表视图的切换按钮
        cardView: false,                        //是否显示详细视图
        showExport: false,                       //是否显示导出
        columns: function () {                  //列表数据
        //获取列名
        var array = obj.columns;
        var columns = [];
        //循环所有列名
        /*$.each(array,function(index,object){
            var _array = object;
            //判断复选框
            if (!_array.checkbox){
                _array.align ? _array.align : _array.align = 'center';
                _array.halign ? _array.halign : _array.halign = 'center';
                _array.sortable ? _array.sortable : _array.sortable = true;
            }
            columns.push(_array);
        });*/
        return array;
    }(),
   });
}

/**
 * 刷新列表
 *      若单纯刷新 params 可不填
 * @param params 条件查询参数
 * @constructor
 */
$.fn.BTF5 = function (params) {
    params = params || {};
    //初始化页数
    params.page = 1;
    //刷新事件
    $(this).bootstrapTable("refreshOptions",{
        queryParams:function(param){
            var query = $.extend( true, param, params );
            return query;
        },
        pageNumber:1
    });
};


/**
 * 初始化 bootstrapValidator 插件
 * @param obj
 * {
         *      fields : 字段验证
         *      success : 点击提交 回调函数
         * }
 * 参考文档：https://www.cnblogs.com/landeanfen/p/5035608.html
 */
$.fn.FM = function (obj) {
    $(this).bootstrapValidator({
        container: 'tooltip',
        message: '字段不能为空',
        /*input状态样式图片*/
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: obj.fields,
    }).on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);
        //回调函数
        obj.success($form);
    })
};

/**
 * bootstrapValidator 验证
 * @param obj
 * @returns {*|jQuery}
 * @constructor
 */
$.fn.VF = function (obj) {
    if (obj != null && obj != '') {
        $(this).data("bootstrapValidator").validate(obj);
    } else {
        $(this).data("bootstrapValidator").validate();
    }
    return $(this).data("bootstrapValidator").isValid();
};

/**
 * bootstrapValidator 重置form表单
 * @constructor
 */
$.fn.RF = function () {
    $(this).data("bootstrapValidator").resetForm();
};


/**
 * 时间插件
 *  <link rel="stylesheet" href="../../plugins/datetimepicker/css/bootstrap-datetimepicker.css">
 *  <script src="../../plugins/datetimepicker/js/bootstrap-datetimepicker.js"></script>
 *  <script src="../../plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
 * @constructor
 */
$.fn.DATE = function(format){
    $(this).datetimepicker({
        format: format || 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',           //语言
        autoclose: true,
        minView: 0,
        minuteStep:1
    });
}



