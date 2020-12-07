
//页面初始化
layui.use(['table', 'form','tree','layer'], function () {
    var table = layui.table,
        form = layui.form,
        tree = layui.tree,
    	layer = layui.layer;

    table.render({
        elem: '#menu'
        , id: 'menu'
        , url: 'getMenuList' //数据接口
        , page: true //开启分页
        , even: true //隔行换色
        , where: {'id':0}
        , cols: [[ //表头
            {type: 'checkbox'}
            , {field: 'id', title: 'ID', width: 80, sort: true}
            , {field: 'name', title: '菜单', sort: true}
            , {field: 'url', title: 'URL'}
            , {field: 'parentId', title: 'parentId',hide:true}
            , {field: 'icon', title: '图标'}
            , {
                title: '操作', templet: function (row) {
                    var edit = '<a class="layui-btn layui-btn-xs" onclick="edit(' + row.id + ')">&nbsp;&nbsp;编辑&nbsp;&nbsp;</a>';
                    return edit ;

                }
            }

        ]]
    });

    treeInit(tree,table);
    
    form.on('submit(query)', function (data) {
        table.reload('menu', {
            where: data.field //设定异步数据接口的额外参数
        });
        return false;
    });
    
    form.on('submit(add)', function (data) {
    	var param={};
    	param.parentId=$("#addselect option:selected").val();
    	param.name=$("#addname").val();
    	param.url=$("#addurl").val();
    	param.icon=$("#addicon").val();
    	$.ajax({
    		type:'post',
    		url:'addMenu',
    		dataType:'json',
    		data:param,
    		success:function(data){
    			if(data.msg=="true"){
    				 layer.close(layer.index);
    				 layer.msg('新增成功！');
    				 table.reload('menu', {
				            where: {"id":param.parentId} //设定异步数据接口的额外参数
			         });
    				 treeInit(tree,table);
    			}else{
    				 layer.msg('新增失败！数量已达上限');
    			}
    		}
    	})
    });
    form.on('submit(edit)', function (data) {
    	var param={};
    	param.id=$("#editid").val();
    	param.name=$("#editname").val();
    	param.url=$("#editurl").val();
    	param.icon=$("#editicon").val();
    	 $.ajax({
    		 url:'save',
    		 type:'post',
    		 dataType:'json',
    		 data:param,
    		 success:function(data){
    			 if (data.msg) {
    				 layer.close(layer.index);
					 layer.msg('保存成功！');
					 //console.log(pid); 
					 table.reload('menu', {
				            where: {"id":pid} //设定异步数据接口的额外参数
				        });
				} else {
					 layer.msg('保存失败！');
				}
    		 }
    	 })
    });

    $('#editMenu').unbind('click').click(function () {
        var checkStatus = table.checkStatus('menu');
        var len = checkStatus.data.length;
        if (len === 1) {
            edit(checkStatus.data[0]['id']);
        } else {
            layer.msg('请选中一条数据进行编辑！');
        }
    });

    $('#deleteMenu').unbind('click').click(function(){
        var checkStatus = table.checkStatus('menu');
        var len = checkStatus.data.length;
        layer.confirm('确定删除选中的'+len+'项吗？', {
            btn: ['确定', '取消'] //可以无限个按钮
        }, function(index, layero){
            //按钮【按钮一】的回调
            var idArray = [];
            for(var i = 0;i<len;i++){
                idArray.push(checkStatus.data[i]['id']);
            }
            deleteMenu(idArray.join(','),pid);
            table.reload('menu', {
            	where: {"id":"0"}
            });
            treeInit(tree,table);
        }, function(index){
            //按钮【按钮二】的回调
            layer.close(index);
        });
    });
    
});
var pid = 0;
var treeInit = function(tree,table){
    $.ajax({
        type: 'post',
        url: 'getMenuTree',
        dataType: 'json',
        data: {},
        success: function (data) {
            tree.render({
                elem: '#tree',
                id: 'tree',
                data: data,
                click: function(obj){
                    var children = obj.data.children;
                    var id = obj.data.id;
                    pid = id;
                    if(children != null && children .length>0){
                        table.reload('menu',{
                            where:{
                                'id': id
                            }
                        });
                    }
                }
            });
        }
    })
};

function openRoleWin(){
	layer.open({
        type: 1,
        title: '菜单信息',
        area: '500px',//只定义宽度，高度自适应
        content: $('#add_info')
    });
	$.ajax({
		type:'post',
		url:'getMenu',
		dataType:'json',
		success:function(data){
			$("#addselect").empty();
			$("#addname").val('');
	    	$("#addurl").val('');
	    	$("#addicon").val('');
			var content="";
			for (var i = 0; i < data.length; i++) {
				content+="<option value="+data[i].id+">"+data[i].name+"</option>"
			}
			$("#addselect").append(content);
		}
	})
}
var deleteMenu = function (ids,pid) {
    $.ajax({
        type: 'post',
        url: 'deleteMenu',
        dataType: 'json',
        data: {'ids': ids,"pid":pid},
        success: function (data) {
            if(data.msg){
                layer.msg('删除成功');
            } else {
                layer.msg('删除失败');
            }
        }
    })
};
 
var edit = function (id) {
    layer.open({
        type: 1,
        title: '菜单信息',
        area: '500px',//只定义宽度，高度自适应
        content: $('#edit_info')
    });
	     
    $.ajax({
        type: 'post',
        url: 'getMenuInfo',
        dataType: 'json',
        data: {'id': id},
        success: function (data) {
            $("#editid").val(data.id);
            $("#editname").val(data.name);
            $("#editurl").val(data.url);
            $("#editicon").val(data.icon);
        }
    })

};
function reset(){
    $("#editname").val('');
    $("#editurl").val('');
    $("#editicon").val('');
}
function addreset(){
	$("#addname").val('');
	$("#addurl").val('');
	$("#addicon").val('');
}