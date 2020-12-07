var context = '/' + window.location.pathname.split('/')[1];
var layer;
var tableIns;
var newDeviceTableIns;
var form;

$(function() {
    layui.use('form', function(){
        form = layui.form;
        form.on('select(area)', function(data){
            getAreaByParentId(data.value);
        });
    });

    layui.use('layer', function(){
        layer = layui.layer;
    });

    layui.use('table', function(){
        var table = layui.table;
        var devicePrincipal = $("#principal-selector").val();
        var deviceProject = $("#device-project-input").val();
        var area = $("#area-selector2").val();
        if(area === ""){
            area = $("#area-selector").val();
        }
        //第一个实例
        tableIns = table.render({
            elem: '#device-table'
            ,height: 312
            ,id:'device'
            ,even: true //隔行换色
            ,page: true //开启分页
            ,url: context+'/map/queryDevice?devicePrincipal='+devicePrincipal+'&area='+area+'&deviceProject='+deviceProject //数据接口
            ,cols: [[ //表头
                {type: 'checkbox'}
                ,{title: '设备名称', sort: true, templet:function(row){
                        return '<a onclick="openDeviceDetail(\''+row.deviceName+'\',\''+row.deviceId+'\')">' + row.deviceName + '</a>';
                    }}
                ,{field: 'deviceIp', title: '设备IP', width:120}
                ,{field: 'area', title: '所属区域', sort: true}
                ,{field: 'deviceProject', title: '所属项目'}
                ,{field: 'deviceCompany', title: '运维单位'}
                ,{title: '添加时间',  sort: true, width:120 ,templet:function(row){
                        return row.addTime.substr(0,10);
                    }}
                ,{title: '操作',width:150, templet:function (row) {
                        if(row.focus === '1'){
                            var edit = '<a class="layui-btn layui-btn-xs" onclick="editDeviceDetail(\''+row.deviceName+'\',\''+row.deviceId+'\')">&nbsp;&nbsp;编辑&nbsp;&nbsp;</a>';
                            var DELETE = '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="deleteDevice('+row.deviceId+')">&nbsp;&nbsp;删除&nbsp;&nbsp;</a>';
                            return edit + DELETE
                        }else{
                           return '';
                        }
                    }
                }
            ]]
        });

        newDeviceTableIns = table.render({
            elem: '#new-device-table'
            ,height: 200
            ,id:'new-device'
            ,even: true //隔行换色
            ,page: true //开启分页
            ,url: context+'/map/queryNewDevice'
            ,cols: [[ //表头
                {field: 'deviceId', title: '设备ID', width:150}
                ,{field: 'deviceIp', title: '设备IP', width:150}
                ,{title: '添加时间',  sort: true, width:150 ,templet:function(row){
                        return row.addTime.substr(0,10);
                    }}
                ,{title: '操作',width:150, templet:function (row) {
                        var edit = '<a class="layui-btn layui-btn-xs" onclick="editDeviceDetail(\'新增设备\',\''+row.deviceId+'\')">&nbsp;&nbsp;编辑&nbsp;&nbsp;</a>';
                        var DELETE = '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="deleteDevice('+row.deviceId+')">&nbsp;&nbsp;删除&nbsp;&nbsp;</a>';
                        return edit + DELETE;
                        return '';
                    }
                }
            ]]
        });
    });

    $('#editDevice').unbind('click').click(function () {
        var checkStatus = layui.table.checkStatus('device');
        var len = checkStatus.data.length;
        if (len === 1) {
            if(checkStatus.data[0]['focus'] === '0'){
                layer.msg('您不能编辑这条数据！');
            }else{
                editDeviceDetail(checkStatus.data[0]['deviceName'], checkStatus.data[0]['deviceId']);
            }
        } else {
            layer.msg('请选中一条数据进行编辑！');
        }
    });

    $('#deleteDevice').unbind('click').click(function(){
        var checkStatus = layui.table.checkStatus('device');
        var len = checkStatus.data.length;
        layer.confirm('确定删除选中的'+len+'项吗？', {
            btn: ['确定', '取消']
        }, function(index, layero){
            var idArray = [];
            for(var i = 0;i<len;i++){
                if(checkStatus.data[0]['focus'] === '0'){
                    layer.msg('您不能删除这条数据！');
                    return false;
                }else{
                    idArray.push(checkStatus.data[i]['deviceId']);
                }
            }
            deleteDeviceBatch(idArray.join(','));
            tableIns.reload();
        }, function(index){
            layer.close(index);
        });
    });
});

/**
 * 根据父节点id获取地区信息
 * @param parentId
 */
function getAreaByParentId(parentId){
    var selector = document.getElementById("area-selector2");
    selector.innerHTML = "<option value=''>请选择</option>";
    $.ajax({
        url: context + '/map/getAreaByParentId',
        type: 'GET',
        data:{
            parentId: parentId
        },
        success: function(data){
            for(var i = 0; i < data.length; i++){
                var option = document.createElement("option");
                option.value = data[i].code;
                option.innerText = data[i].name;
                selector.appendChild(option);
            }
            form.render("select");
        },
        error:function(data){
            console.log(data);
        }
    });
}

/**
 * 按条件查询设备列表
 */
function queryDevice(){
    var devicePrincipal = $("#principal-selector").val();
    var deviceProject = $("#device-project-input").val();
    var area = $("#area-selector2").val();
    if(area === ""){
        area = $("#area-selector").val();
    }
    tableIns.reload({
        url: context+'/map/queryDevice?devicePrincipal='+devicePrincipal+'&area='+area+'&deviceProject='+deviceProject //数据接口
    });
}

/**
 * 打开设备详细信息页面
 * @param deviceName 设备名称
 * @param deviceId 设备ID
 */
function openDeviceDetail(deviceName, deviceId){
    layer.open({
        title: deviceName,
        area: ['95%', '100%'],
        shadeClose: true,
        type: 2,
        content: context + '/map/detail?deviceId=' + deviceId
    });
}

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
        content: context + '/map/edit?deviceId=' + deviceId,
        cancel: function(index, layero){
            tableIns.reload();
            newDeviceTableIns.reload();
        }
    });
}


function deleteDevice(deviceId){
    layer.confirm('确定要删除编号为[' + deviceId + ']的设备吗？', {
        btn: ['确定', '取消'] //可以无限个按钮
        ,btn2: function(index, layero){

        }
    }, function(index, layero){
        $.ajax({
            type : 'GET',
            url : context + '/map/delete',
            dataType:'json',
            data:{
                deviceId : deviceId
            },
            success:function(data){
                if(data === 1){
                    layer.msg('删除成功！');
                    tableIns.reload();
                    newDeviceTableIns.reload();
                }
            }
        })
    });
}

function deleteDeviceBatch(deviceIds){
    $.ajax({
        type : 'GET',
        url : context + '/map/deleteBatch',
        dataType:'json',
        data:{
            deviceIds : deviceIds
        },
        success:function(data){
            if(data !== 0){
                layer.msg('删除成功！删除了['+ data +']条记录');
                tableIns.reload();
            }
        }
    })
}

function addDevice(){
    layer.open({
        title: '新增设备',
        area: ['95%', '100%'],
        shadeClose: true,
        type: 2,
        content: context + '/map/edit'
    });
}

function synchronizeDevice(){
    $.ajax({
        type : 'GET',
        url : context + '/map/synchronizeDevice',
        success:function(data){
            if(data !== 0){
                layer.msg('同步成功！新增了['+ data +']条记录');
                newDeviceTableIns.reload();
            }
        }
    })
}