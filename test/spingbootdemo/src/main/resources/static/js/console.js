$(function() {
	init();
})

var init=function(){
	$.ajax({
		url:'getDeviceCount',
		type:'post',
		dataType:'json',
		success:function(data){
			$("#dataCount").html(data.data.dataCount);
			$("#warningCount").html(data.data.warningCount);
			$("#offlineCount").html(data.data.offlineCount);
			$("#normalCount").html(data.data.normalCount);
			
			//echarts 饼图和折线图
			 application(data.data);
			 application2(data.data);
		}
	})
}

function application(data) {
	var dom = document.getElementById("main");
	var myChart = echarts.init(dom);
	
	var count=[];
	count.push(data.normalCount);
	count.push(data.warningCount);
	count.push(data.offlineCount);
	var legendData = ['设备正常','设备告警','设备离线'];
    var seriesData = [];
    for (var i = 0; i < legendData.length; i++) {
        seriesData.push({
            name: legendData[i],
            value:  count[i]
        });
    }
	option = {
		    title : {
		        text: '设备占比率',
		        subtext: '',
		        x:'center',
		        left:'0'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        type: 'scroll',
		       // orient: 'vertical',
		        right: 20,
		        top: 0,
		        bottom: 20,
		        data: legendData,
		    },
		    series : [
		        {
		            name: '种类',
		            type: 'pie',
		            radius : '55%',
		            center: ['40%', '50%'],
		            data: seriesData,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ],
		    color:['rgb(36,186,99)','rgb(255,169,73)','rgb(239,73,69)']
		};
	myChart.setOption(option);
}
function application2(data) {
	var dom2 = document.getElementById("main2");
	var myChart2 = echarts.init(dom2);
	 
	var xdata=[];
	var seriesData1=[];
	var seriesData2=[];
	var seriesData3=[];
	for (var i = 0; i < data.datalist.length; i++) {
		xdata.push(data.datalist[i].date);
		seriesData1.push( data.datalist[i].cnt );
	}
	for (var i = 0; i < data.offlineList.length; i++) {
		seriesData3.push(data.offlineList[i].cnt);
	}
	for (var i = 0; i < data.warningList.length; i++) {
		seriesData2.push(data.warningList[i].cnt);
	}
	option = {
		    title: {
		        text: '最近7天设备状态走势图',
		        left:'0' 
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		    	right:'20',
		        data:['设备正常','设备告警','设备离线']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        top:'10%',
		        containLabel: true
		    },
//		    toolbox: {
//		        feature: {
//		            saveAsImage: {}
//		        }
//		    },
		    xAxis: {
		        type: 'category',
		        axisLabel:{rotate:'60'},
		        boundaryGap: false,
		        data: xdata
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series:[{
	            name:'设备正常',
	            type:'line',
	            data:seriesData1
	        },{
	            name:'设备告警',
	            type:'line',
	            data:seriesData2
	        },{
	            name:'设备离线',
	            type:'line',
	            data:seriesData3
	        }],
		    color:['rgb(36,186,99)','rgb(255,169,73)','rgb(239,73,69)']
		};
		myChart2.setOption(option);
}