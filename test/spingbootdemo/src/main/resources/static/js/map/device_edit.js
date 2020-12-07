var map;
var context = '/' + window.location.pathname.split('/')[1];
var form;
var layer;
var isEdit;

$(function() {
    isEdit = document.getElementById("isEdit").value;
    layui.use('form', function(){
        form = layui.form;
        form.on('select(area)', function(data){
            getAreaByParentId(data.value);
        });

        initArea();

        form.on('submit(*)', function(data){
            if(isEdit === '0'){
                return false;
            }
            // console.log(data.elem); //被执行事件的元素DOM对象，一般为button对象
            // console.log(data.form); //被执行提交的form对象，一般在存在form标签时才会返回
            //console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}
            if(data.field.area === "" || data.field.area0 === ""){
                layer.msg("请选择设备所在地区。");
                return false;
            }
            if(data.field.lat === "0.0" || data.field.lng === "0.0"){
                layer.msg("请在地图上选择设备位置。");
                return false;
            }
            return true;
        });

    });
    layui.use('layer', function(){
        layer = layui.layer;
        var message = document.getElementById("message").value;
        if(message !== ""){
            layer.msg(message);
        }
    });

    if(isEdit === '0'){
        var deviceForm = document.getElementById("device-form");
        for(var i = 0, l = deviceForm.elements.length; i <l; i++) {
            deviceForm.elements[i].disabled = 'disabled';
        }
    }
    initMap();
});

function initArea(){
    var areaCode = document.getElementById("area-code").value;
    var selector = document.getElementById("area0-selector");
    selector.innerHTML = "<option value=''>请选择</option>";
    $.ajax({
        url: context+'/map/getAreaByParentId',
        type: 'GET',
        data:{
            parentId : "37000000"
        },
        success: function(data){
            for (var i = 0; i < data.length; i++) {
                var option = document.createElement("option");
                option.value = data[i].code;
                option.innerText = data[i].name;
                if(areaCode !== "" && areaCode.substr(0, 4) === data[i].code.substr(0, 4)){
                    option.selected = true;
                    getAreaByParentId(data[i].code);
                }
                selector.appendChild(option);
            }
            form.render('select');
        },
        error:function(data){
            console.log(data);
        }
    });
}

function initMap() {
    map = new BMap.Map("map-div",{enableMapClick:false});
    map.enableScrollWheelZoom();//应用鼠标滚轮缩放
    map.enableContinuousZoom();//应用连续缩放
    var lng = $("#lng").val();
    var lat = $("#lat").val();
    if(lng === "0.0" || lat === "0.0"){
        map.centerAndZoom(new BMap.Point(116.771946, 36.682786), 10);
    }else{
        var point = new BMap.Point(lng, lat);
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
        map.centerAndZoom(point, 16);
    }
    if(isEdit === '1'){
        map.addEventListener("click", updateCoordinate);
    }
}

function updateCoordinate(e){
    var point = e.point;
    point.lng = point.lng.toFixed(6);
    point.lat = point.lat.toFixed(6);
    map.clearOverlays();
    $("#lng").val(point.lng);
    $("#lat").val(point.lat);
    var marker = new BMap.Marker(point);
    map.addOverlay(marker);
}

/**
 * 根据父节点id获取地区信息
 * @param parentId
 */
function getAreaByParentId(parentId) {
    var areaCode = document.getElementById("area-code").value;
    var selector = document.getElementById("area-selector");
    selector.innerHTML = "<option value=''>请选择</option>";
    $.ajax({
        url: context + '/map/getAreaByParentId',
        type: 'GET',
        data: {
            parentId: parentId
        },
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var option = document.createElement("option");
                option.value = data[i].code;
                option.innerText = data[i].name;
                if(areaCode !== null && areaCode === data[i].code){
                    option.selected = true;
                }
                selector.appendChild(option);
            }
            form.render('select');
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function saveDevice(){
    var form = $("#device-form");
    form.attr('action', context + "/map/save");
    form.submit();
}

function getDeviceInfo(input){
    //var deviceId = input.value;
    var form = $("#device-form");
    form.attr('action', context + "/map/edit");
    form.submit();
}