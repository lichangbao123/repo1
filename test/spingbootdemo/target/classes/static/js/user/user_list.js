layui.use(['table', 'form'], function () {
    var table = layui.table,
        form = layui.form;

    //第一个实例
    table.render({
        elem: '#user'
        , id: 'user'
        // ,height: 312
        , url: 'getUserList' //数据接口
        , page: true //开启分页
        , even: true //隔行换色
        , cols: [[ //表头
            {type: 'checkbox'}
            , {field: 'uid', title: 'ID', width: 80, sort: true}
            , {field: 'name', title: '用户名', sort: true}
            , {field: 'username', title: '登录账号'}
            , {field: 'email', title: '邮箱'}
            , {field: 'createTime', title: '创建时间', sort: true}
            , {
                title: '操作', templet: function (row) {
                    var edit = '<a class="layui-btn layui-btn-xs" onclick="edit(' + row.uid + ')">&nbsp;&nbsp;编辑&nbsp;&nbsp;</a>';
                    var right = '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="roleConfig(' + row.uid + ')">&nbsp;&nbsp;角色配置&nbsp;&nbsp;</a>';
                    return edit + right;

                }
            }

        ]]
    });

    form.on('submit(query)', function (data) {
        table.reload('user', {
            where: data.field //设定异步数据接口的额外参数
        });
        return false;
    });

    form.on('submit(*)', function (data) {
        saveUser(data.field);
    });

    $('#editUser').unbind('click').click(function () {
        var checkStatus = table.checkStatus('user');
        var len = checkStatus.data.length;
        if (len == 1) {
            edit(checkStatus.data[0]['uid']);
        } else {
            layer.msg('请选中一条数据进行编辑！');
        }
    });

    $('#deleteUser').unbind('click').click(function(){
        var checkStatus = table.checkStatus('user');
        var len = checkStatus.data.length;
        layer.confirm('确定删除选中的'+len+'项吗？', {
            btn: ['确定', '取消'] //可以无限个按钮
        }, function(index, layero){
            //按钮【按钮一】的回调
            var idArray = [];
            for(var i = 0;i<len;i++){
                idArray.push(checkStatus.data[i]['uid']);
            }
            deleteUser(idArray.join(','));
            table.reload('user', {
            });
        }, function(index){
            //按钮【按钮二】的回调
            layer.close(index);
        });
    });
});

var openUserWin = function () {
    layer.open({
        type: 1,
        title: '用户信息',
        area: '500px',//只定义宽度，高度自适应
        content: $('#user_info')
    });
    UserFormInit({});
};

var uid = 0;
var saveUser = function (param) {
    if (uid != 0) {
        param.uid = uid;
    }
    $.ajax({
        type: 'post',
        url: 'saveUser',
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
    uid = id;
    openUserWin();
    $.ajax({
        type: 'post',
        url: 'getUserInfo',
        dataType: 'json',
        data: {'id': id},
        success: function (data) {
            UserFormInit(data);
        }
    })

};

var deleteUser = function (ids) {
    $.ajax({
        type: 'post',
        url: 'deleteUser',
        dataType: 'json',
        data: {'ids': ids},
        success: function (data) {
            if(data.msg){
                layer.msg('删除成功');
            } else {
                layer.msg('删除失败');
            }
        }
    })
};

var UserFormInit = function (data) {
    layui.use('form', function () {
        var form = layui.form;
        form.val("userForm", {
            'username': data.username,
            'password': data.password,
            'name': data.name,
            'company': data.company,
            'tel': data.tel,
            'email': data.email
        });
    });
};

//角色配置
var param = {};
var roleConfig = function (id) {
    param.uid = id;
    $.ajax({
        type: 'post',
        url: 'roleConfig',
        dataType: 'json',
        data: param,
        success: function (data) {
            treeInit(data, id);
        }
    })
};

var treeInit = function (data, id) {

    var param = {};
    param.uid = id;

    layui.use('tree', function () {
        var tree = layui.tree;
        tree.render({
            elem: '#tree',
            id: 'tree',
            showCheckbox: true,
            data: data
        });

        $('#save_btn').unbind('click').click(function () {
            var role_ids = [];

            var checkedData = $('.layui-form-checked');
            for (var i = 0; i < checkedData.length; i++) {
                var role_id = checkedData[i].parentNode.parentNode.parentNode.dataset.id;
                role_ids.push(role_id);
            }
            param.roleIds = role_ids.join(',');
            saveRole(param);
        })

    });

    layer.open({
        type: 1,
        title: '角色配置',
        area: ['400px', '400px'],
        content: $('#win_tree')
    });
};

var saveRole = function (param) {
    $.ajax({
        type: 'post',
        url: 'saveUserRole',
        dataType: 'json',
        data: param,
        success: function (data) {
            if (data.msg) {
                layer.msg('保存成功！');
            }
        }
    })
};