var context = '/' + window.location.pathname.split('/')[1];

$(function(){
    var deviceId = document.getElementById("device-id");
    if(deviceId){
        $.ajax({
            type : 'GET',
            url : context + '/map/warningInfo?deviceId=' + deviceId.value,
            success:function(data){
                for(var i = 0; i < data.length; i++){
                    var tr = document.getElementById(data[i]);
                    tr.style.backgroundColor = "#FF5722";
                    tr.style.color = "#FFFFFF";
                }
            },
            error:function(data){
                console.log(data);
            }
        });
    }
});