var boxType = '0101';

layui.use(['table', 'form'], function () {
    var table = layui.table,
        form = layui.form;

    selectInit(form);

    //第一个实例
    table.render({
        elem: '#warning'
        , id: 'warning'
        // ,height: 312
        , url: 'getWarningList' //数据接口
        , page: true //开启分页
        , even: true //隔行换色
        , where: {functionId: '0101'}
        , cols: [[ //表头
            {type: 'checkbox'}
            // , {field: 'funcId', title: '功能码'}
            , {field: 'id', title: 'ID', hide: true}
            , {field: 'ruleName', title: '告警类型',width:220}
            , {field: 'maxValue', title: '最大阀值',width:100}
            , {field: 'minValue', title: '最小阀值',width:100}
            , {field: 'warningLevel', title: '告警级别'}
            , {field: 'valid', title: '是否有效',width:100}
            , {field: 'colName', title: '关联字段',width:150}
            , {
                title: '操作', templet: function (row) {
                    var edit = '<a class="layui-btn layui-btn-xs" onclick="editWarning(\'' + row.id + '\')">&nbsp;&nbsp;编辑&nbsp;&nbsp;</a>';
                    return edit;
                }
            }

        ]]
    });

    form.on('select(boxType)', function (data) {
        boxType = data.value; //得到被选中的值
        table.reload('warning', {
            where: {
                functionId: data.value
            }
        });
    });

    form.on('submit(*)', function (data) {
        saveWarning(data.field);
    });

    $('#warningEdit').unbind('click').click(function () {
        var checkStatus = table.checkStatus('warning');
        var len = checkStatus.data.length;
        if (len == 1) {
            editWarning(checkStatus.data[0]['id']);
        } else {
            layer.msg('请选中一条数据进行编辑！');
        }
    });

    $('#warningDelete').unbind('click').click(function(){
        var checkStatus = table.checkStatus('warning');
        var len = checkStatus.data.length;
        layer.confirm('确定删除选中的'+len+'项吗？', {
            btn: ['确定', '取消'] //可以无限个按钮
        }, function(index, layero){
            //按钮【按钮一】的回调
            var idArray = [];
            for(var i = 0;i<len;i++){
                idArray.push(checkStatus.data[i]['id']);
            }
            deleteWarning(idArray.join(','));
            table.reload('warning', {
                where: {
                    functionId: boxType
                }
            });
        }, function(index){
            //按钮【按钮二】的回调
            layer.close(index);
        });
    });


});

var selectInit = function (form) {

    $.ajax({
        type: 'post',
        url: 'getSelectData',
        dataType: 'json',
        data: {typeCode: 'boxType'},
        success: function (data) {
            var select = $("#boxType");
            var options = '';
            for (var i = 0; i < data.length; i++) {
                var option = '<option value="' + data[i].code + '">'
                    + data[i].name + '</option>';
                options = options + option;
            }
            select.append(options);
            form.render("select");
        }
    });

};

var editWarning = function (id) {
    var param = {'id': id};
    openWarningWin();
    $.ajax({
        type: 'post',
        url: 'getWarningInfo',
        dataType: 'json',
        data: param,
        success: function (data) {
            warningFormInit(data);
        }
    });
};

var addWarning = function () {
    openWarningWin();
    $.ajax({
        type: 'post',
        url: 'getMaxWarning',
        dataType: 'json',
        data: {'funcId': boxType},
        success: function (data) {
            warningFormInit(data);
        }
    });
};

var deleteWarning = function(ids){
    $.ajax({
        type: 'post',
        url: 'deleteWarning',
        dataType: 'json',
        data: {'ids': ids},
        success: function (data) {
            if(data.msg){
                layer.msg('删除成功');
            } else {
                layer.msg('删除失败');
            }
        }
    });
};

var warningFormInit = function (data) {
    layui.use('form', function () {
        var form = layui.form;
        form.val("warningForm", {
            'id': data.id,
            'ruleName': data.ruleName,
            'maxValue': data.maxValue,
            'minValue': data.minValue,
            'warningLevel': data.warningLevel,
            'valid': data.valid,
            'colName': data.colName
        });
    });

};


var openWarningWin = function () {
    layer.open({
        type: 1,
        title: '预警规则信息',
        area: '500px',//只定义宽度，高度自适应
        content: $('#warning_info')
    });
};

var saveWarning = function (param) {

    param.funcId = boxType;
    $.ajax({
        type: 'post',
        url: 'saveWarning',
        dataType: 'json',
        data: param,
        success: function (data) {
            if (data.msg) {
                layer.msg('保存成功！');
            } else {
                layer.msg('保存失败！');
            }
        }
    })
};