var context = '/' + window.location.pathname.split('/')[1];

layui.use(['table', 'form'], function () {
    var table = layui.table,
        form = layui.form;

    //第一个实例
    table.render({
        elem: '#warning'
        , id: 'warning'
        // ,height: 312
    	//,width:1100
        , url: 'getWarningHistoryinfoList' //数据接口
        , page: true //开启分页
        , even: true //隔行换色
        //, where: {functionId: '0101'}
        , cols: [[ //表头
            //{type: 'checkbox'},
            {type: 'numbers',title:"序号"}
            ,{title: '设备名称 ',sort:true
                ,templet:function(row){
                    return '<a onclick="editDeviceDetail(\''+row.device_name+'\',\''+row.deviceId+'\')">' + row.device_name + '</a>';
                }
            }
            , {field: 'deviceIp', title: '设备ip',sort:true}
            , {field: 'ruleName', title: '告警类型',sort:true}
            , {field: 'warningLevel', title: '告警级别',sort:true}
            , {field: 'warningDate', title: '告警日期',sort:true}
            , {field: 'deviceId', title: '设备id',hide:true,sort:true}
            , {field: 'lnt', title: '经度',hide:true,sort:true}
            , {field: 'lat', title: '纬度',hide:true,sort:true}
            , {field: 'funcId', title: '功能码',hide:true,sort:true}
            , {field: 'ruleId', title: 'ruleId',hide:true,sort:true}
            , {field: 'repairDate', title: '修复日期',sort:true}
//            , {
//                title: '操作', templet: function (row) {
//                    var edit = '<a class="layui-btn layui-btn-xs" onclick="editWarning(\'' + row.id + '\')">&nbsp;&nbsp;编辑&nbsp;&nbsp;</a>';
//                    return edit;
//                }
//            }

        ]]
    });
 
});

/**
 * 编辑设备详细信息页面
 * @param deviceName 设备名称
 * @param deviceId 设备ID
 */
function editDeviceDetail(deviceName, deviceId){
    layer.open({
        title: deviceName,
        area: ['95%', '100%'],
        shadeClose: true,
        type: 2,
        content: context + '/map/edit?deviceId=' + deviceId + '&isEdit=0'
    });
}


