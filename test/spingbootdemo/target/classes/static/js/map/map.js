var map;
var form;
var layer;
var context = '/' + window.location.pathname.split('/')[1];

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
});

/**
 * 初始化地图
 */
function initMap(){
    map = new BMap.Map("map-div",{enableMapClick:false});
    map.enableScrollWheelZoom();//应用鼠标滚轮缩放
    map.enableContinuousZoom();//应用连续缩放
    var bottom_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL});
    map.addControl(bottom_right_navigation);//平移缩放控件
    getBoundary("山东省");
    setTimeout(function (){
        queryDevice();
    },500);
}

/**
 * 定位行政区划
 * @param name 行政区划名称
 */
function getBoundary(name){
    var bdary = new BMap.Boundary();
    bdary.get(name, function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        if (count === 0) {
            getBoundary('山东省');
            return ;
        }
        var pointArray = [];
        for (var i = 0; i < count; i++) {
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 3, strokeColor: "#3280fc",fillColor:"#ebf2f9",fillOpacity:0.4}); //建立多边形覆盖物
            map.addOverlay(ply);  //添加覆盖物
            pointArray = pointArray.concat(ply.getPath());
        }
        map.setViewport(pointArray);    //调整视野
    });
}

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
        }
    })
}

/**
 * ajax 按查询条件获取设备信息
 */
function queryDevice(){
    var devicePrincipal = $("#principal-selector").val();
    var deviceStatus = $("#status-selector").val();
    var area = $("#area-selector2").val();
    if(area === ""){
        area = $("#area-selector").val();
    }

    $.ajax({
        type : 'GET',
        url : context + '/map/queryDevice',
        dataType:'json',
        data:{
            devicePrincipal : devicePrincipal,
            deviceStatus : deviceStatus,
            area : area,
            page : 0,
            limit: -1
        },
        success:function(data){
            markPoint(data.data);
        },
        error:function(data){
            console.log(data);
        }
    });
}

/**
 * 在地图上按经纬度标记多个地点，并标明相关信息
 * @param data
 */
function markPoint(data){
    map.clearOverlays();
    var zoomArr = [];
    var deviceListUl = document.getElementById("device-list-ul");
    var onlineIcon = new BMap.Icon(context+"/static/images/map/online.png", new BMap.Size(32, 32));
    var warningIcon = new BMap.Icon(context+"/static/images/map/warning.png", new BMap.Size(32, 32));
    var offlineIcon = new BMap.Icon(context+"/static/images/map/offline.png", new BMap.Size(32, 32));
    var onlineNum = 0;
    var warningNum = 0;
    var offlineNum = 0;
    deviceListUl.innerHTML = "";
    for(var i = 0; i < data.length; i++){
        (function(i){
            var point = new BMap.Point(data[i].lng, data[i].lat);
            var marker;
            var device_status_class = "";
            if(data[i].deviceStatus === "正常在线"){
                marker = new BMap.Marker(point, {icon:onlineIcon});
                device_status_class = "device-online";
                onlineNum++;
            }else if(data[i].deviceStatus === "告警"){
                marker = new BMap.Marker(point, {icon:warningIcon});
                device_status_class = "device-warning";
                warningNum++;
            }else if(data[i].deviceStatus === "离线"){
                marker = new BMap.Marker(point, {icon:offlineIcon});
                device_status_class = "device-offline";
                offlineNum++;
            }else{
                marker = new BMap.Marker(point);  // 创建标注
            }

            zoomArr.push(point);
            var content = "设备名称: " + data[i].deviceName +
                "</br> 状态:   " + data[i].deviceStatus +
                "</br> 负责人: " + data[i].devicePrincipal +
                "</br> 电话:   " + data[i].principalPhone +
                "</br> 区域:   " + data[i].area +
                "</br> 设备IP: " + data[i].deviceIp;
            map.addOverlay(marker);               // 将标注添加到地图中
            addEventHandler(content, marker, point, data[i]);

            var li = document.createElement("li");
            li.className = "device-list-li";
            var a = document.createElement("a");
            a.innerHTML = data[i].deviceName + "&nbsp;<span class='device-status "+device_status_class+"'>("+data[i].deviceStatus+")</span>";
            a.title = "责任人：" + data[i].devicePrincipal;
            a.onclick = function () {
                openInfo(content, point);
            };
            li.appendChild(a);
            deviceListUl.appendChild(li);
        })(i);

    }
    document.getElementById("online-number").innerText = onlineNum;
    document.getElementById("warning-number").innerText = warningNum;
    document.getElementById("offline-number").innerText = offlineNum;
    map.setViewport(zoomArr);
    map.zoomOut();
}

/**
 * 给地图上的标记点增加点击事件
 * @param content
 * @param marker
 * @param point
 */
function addEventHandler(content, marker, point, device){
    marker.addEventListener("mouseover",function(){
        openInfo(content, point)}
    );
    marker.addEventListener("click", function(){
        openDeviceDetail(device);
    });
}

/**
 * 点击地图上标记点或左侧设备列表，展示设备信息
 * @param content
 * @param point
 */
function openInfo(content, point){
    var infoWindow = new BMap.InfoWindow(content, {});  // 创建信息窗口对象
    map.openInfoWindow(infoWindow,point); //开启信息窗口
}

/**
 * 打开设备详细信息页面
 * @param device
 */
function openDeviceDetail(device){
    layer.open({
        title: device.deviceName,
        area: ['95%', '100%'],
        shadeClose: true,
        type: 2,
        content: context + '/map/detail?deviceId=' + device.deviceId
    });
}

/**
 * 控制设备列表显示或隐藏
 */
function changeDeviceList(){
    var deviceListUl = document.getElementById("device-list-ul");
    var deviceListBtn = document.getElementById("device-list-btn");
    var deviceListIcon = document.getElementById("device-list-icon");
    if(deviceListUl.style.display === "none"){
        deviceListUl.style.display = "";
        deviceListIcon.className = "layui-icon layui-icon-up";
        deviceListBtn.title = "隐藏设备列表";
    }else{
        deviceListUl.style.display = "none";
        deviceListIcon.className = "layui-icon layui-icon-down";
        deviceListBtn.title = "显示设备列表";
    }
}



