//页面初始化
layui.use(['table', 'form'], function () {
    var table = layui.table,
        form = layui.form;
    //第一个实例
    table.render({
        elem: '#role'
        , id: 'role'
        , url: 'getRoleList' //数据接口
        , page: true //开启分页
        , even: true //隔行换色
        , cols: [[ //表头
            {type: 'checkbox'}
            , {field: 'id', title: 'ID', width: 80, sort: true}
            , {field: 'role', title: '角色', sort: true}
            , {field: 'description', title: '描述'}
            , {
                title: '操作', templet: function (row) {
                    var edit = '<a class="layui-btn layui-btn-xs" onclick="edit(' + row.id + ')">&nbsp;&nbsp;编辑&nbsp;&nbsp;</a>';
                    var right = '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="rightConfig(' + row.id + ')">&nbsp;&nbsp;权限配置&nbsp;&nbsp;</a>';
                    return edit + right;

                }
            }

        ]]
    });

    form.on('submit(*)', function (data) {
        saveRole(data.field);
    });

    $('#editRole').unbind('click').click(function () {
        var checkStatus = table.checkStatus('role');
        var len = checkStatus.data.length;
        if (len === 1) {
            edit(checkStatus.data[0]['id']);
        } else {
            layer.msg('请选中一条数据进行编辑！');
        }
    });

    $('#deleteRole').unbind('click').click(function(){
        var checkStatus = table.checkStatus('role');
        var len = checkStatus.data.length;
        layer.confirm('确定删除选中的'+len+'项吗？', {
            btn: ['确定', '取消'] //可以无限个按钮
        }, function(index, layero){
            //按钮【按钮一】的回调
            var idArray = [];
            for(var i = 0;i<len;i++){
                idArray.push(checkStatus.data[i]['id']);
            }
            deleteRole(idArray.join(','));
            table.reload('role', {
            });
        }, function(index){
            //按钮【按钮二】的回调
            layer.close(index);
        });
    });

});

//权限配置
var param = {};
var rightConfig = function (id) {
    param.id = id;
    $.ajax({
        type: 'post',
        url: 'rightConfig',
        dataType: 'json',
        data: param,
        success: function (data) {
            treeInit(data, id);
        }
    })
};

var treeInit = function (data, id) {

    var param = {};
    param.role_id = id;

    layui.use('tree', function () {
        var tree = layui.tree;
        tree.render({
            elem: '#tree',
            id: 'tree',
            showCheckbox: true,
            data: data
        });

        $('#save_btn').unbind('click').click(function () {
            var permission_ids = [];

            var checkedData = $('.layui-form-checked');
            for (var i = 0; i < checkedData.length; i++) {
                var permission_id = checkedData[i].parentNode.parentNode.parentNode.dataset.id;
                permission_ids.push(permission_id);
            }
            param.permission_ids = permission_ids.join(',');
            saveRight(param);
        })

    });

    layer.open({
        type: 1,
        title: '权限配置',
        area: ['600px', '500px'],
        content: $('#win_tree')
    });
};


var saveRight = function (param) {

    $.ajax({
        type: 'post',
        url: 'saveRight',
        dataType: 'json',
        data: param,
        success: function (data) {
            if (data.msg) {
                layer.msg('保存成功！');
            }
        }
    })
};

var openRoleWin = function () {
    layer.open({
        type: 1,
        title: '角色信息',
        area: '500px',//只定义宽度，高度自适应
        content: $('#role_info')
    });
    roleFormInit({});
};

var saveRole = function (param) {
    $.ajax({
        type: 'post',
        url: 'saveRole',
        dataType: 'json',
        data: param,
        success: function (data) {
            if (data.msg) {
                layer.msg('保存成功！');
            }
        }
    })
};

var edit = function (id) {
    openRoleWin();
    param.id = id;

    $.ajax({
        type: 'post',
        url: 'getRoleInfo',
        dataType: 'json',
        data: param,
        success: function (data) {
            roleFormInit(data);
        }
    })
};

var roleFormInit = function (data) {
    layui.use('form', function () {
        var form = layui.form;
        form.val("roleForm", {
            'role': data.role,
            'description': data.description
        });
    });
};

var deleteRole = function(ids){
    param.ids = ids;

    $.ajax({
        type: 'post',
        url: 'deleteRole',
        dataType: 'json',
        data: param,
        success: function (data) {
            if(data.msg){
                layer.msg('删除成功');
            } else {
                layer.msg('删除失败');
            }
        }
    })
};