layui.use(['table', 'form'], function () {
    var table = layui.table,
        form = layui.form;

    //第一个实例
    table.render({
        elem: '#warning'
        , id: 'warning'
        // ,height: 312
        //,width:700
        , url: 'getLoginList' //数据接口
        , page: true //开启分页
        , even: true //隔行换色
        //, where: {functionId: '0101'}
        , cols: [[ //表头
            //{type: 'checkbox'},
            {type: 'numbers',title:"ID"}
            , {field: 'id',  title: "序号", hide:true, sort:true}
            , {field: 'username', title: '登录账号',sort:true}
            , {field: 'name', 	  title: '用户名',  sort:true}
            , {field: 'company',  title: '所属单位',  sort:true}
            , {field: 'operationType', title: '操作类型',sort:true}
            , {field: 'operationTime', title: '操作日期',sort:true}
//            , {
//                title: '操作', templet: function (row) {
//                    var edit = '<a class="layui-btn layui-btn-xs" onclick="editWarning(\'' + row.id + '\')">&nbsp;&nbsp;编辑&nbsp;&nbsp;</a>';
//                    return edit;
//                }
//            }

        ]]
    });
 
});
