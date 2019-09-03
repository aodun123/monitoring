/**
 @Name：layuiAdmin Echarts集成
 @Author：刘梅
 */

function room_names_fun(device_rooms){
	var room_names = [];
	for(var i=0;i<device_rooms.length;i++){
		room_names.push(device_rooms[i].name);
	}
	room_names.push('合计');
	return room_names;
}

function toNameFromState(state){
	if(state && state>=1){
		switch(state){
		case 1:
			return '维修中';
		case 2:
			return '已维修';
		case 3:
			return '已审核';
		}
	}else {
		return '未派单';
	}
}

var table_data =[];
//进行数据统计
function statistics(data,rooms){
	var series = [];
	var series_map={};
	var room_index={};
	var table_map={};
	var table_all={
			room_name:"合计"
		   ,wpd:0
		   ,wxz:0
		   ,ywx:0
		   ,ysh:0
	};
	
	for(var i=0;i<rooms.length;i++){
		room_index[rooms[i].value]=i;
		table_item = {};
		table_item.wpd=0;
		table_item.wxz=0;
		table_item.room_name=rooms[i].name;
		table_item.ywx=0;
		table_item.ysh=0;
		table_map[rooms[i].value]=table_item;
		table_data.push(table_item);
	}
	for(var i=0;i<4;i++){
		var series_item={};
		series_item.name=toNameFromState(i); 
		series_item.type='bar';
		series_item.data=[];
		for(var j=0;j<=rooms.length;j++){
			series_item.data[j]=0;
		}
		series_map[series_item.name]=series_item;
	}
	for(var i=0;i<data.length;i++){
		var state_name = toNameFromState(data[i].state);
		var series_item = series_map[state_name];
		series_item.data[room_index[data[i].mr_id]]++;
		series_item.data[rooms.length]++;
		
		var table_item = table_map[data[i].mr_id];
	
		
		if(data[i].state && data[i].state>=1){
			switch(data[i].state){
			case 1:
				table_item.wxz++;
				table_all.wxz++;
				break;
			case 2:
				table_item.ywx++;
				table_all.ywx++;
				break;
			case 3:
				table_item.ysh++;
				table_all.ysh++;
				break;
			}
		}else{
			table_item.wpd++;
			table_all.wpd++;
		}
		
	}
	table_data.push(table_all);
	for(var key in series_map){
		series.push(series_map[key]);
		
	}
	return series;
}

layui.define(function(erts){
	var device_rooms = request('bm/device_rooms');
	var room_names = room_names_fun(device_rooms);
	var statistics_data= request('work_orders/statistics');
	var statistics_result = statistics(statistics_data,device_rooms);
	  $("#addbtn").click(function(){
	    	var downloadURL = "work_orders/exportExcel";
	        var form = $("<form>");
	        form.attr("style","display:none");
	        form.attr("target","");
	        form.attr("method","post");
	        form.attr("action",downloadURL);
	        var input = $("<input>");
	        input.attr("type","hidden");
	        input.attr("name","jsonStr");
	        input.attr("value",JSON.stringify(table_data));
	        $("body").append(form);
	        form.append(input);
	        form.submit();
	    });
	
	
    //区块轮播切换
    layui.use(['admin', 'carousel'], function(){
        var $ = layui.$
            ,admin = layui.admin
            ,carousel = layui.carousel
            ,element = layui.element
            ,device = layui.device();

        //轮播切换
        $('.layadmin-carousel').each(function(){
            var othis = $(this);
            carousel.render({
                elem: this
                ,width: '100%'
                ,arrow: 'none'
                ,interval: othis.data('interval')
                ,autoplay: othis.data('autoplay') === true
                ,trigger: (device.ios || device.android) ? 'click' : 'hover'
                ,anim: othis.data('anim')
            });
        });
        element.render('progress');
    });

    //柱状图
    layui.use(['carousel', 'echarts'], function(){
        var $ = layui.$
            ,carousel = layui.carousel
            ,echarts = layui.echarts;

        //标准柱状图
        var echnormcol = [], normcol = [
            {
                title : {
                    text: '各机房工单统计图表',
                    subtext: '根据数据计算'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['未派单','维修中','已维修','已审核']
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : room_names
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : statistics_result
            }
        ]
            ,elemNormcol = $('#LAY-index-normcol').children('div')
            ,renderNormcol = function(index){
            echnormcol[index] = echarts.init(elemNormcol[index], layui.echartsTheme);
            echnormcol[index].setOption(normcol[index]);
            window.onresize = echnormcol[index].resize;
        };
        if(!elemNormcol[0]) return;
        renderNormcol(0);

    });

    //表格
    layui.use(['table', 'form'], function(){
        var $ = layui.$
            ,table = layui.table
            ,form = layui.form;
        table.render({
            elem: '#LAY-user-manage'
            ,data: table_data
            ,cols: [[
                {type: 'checkbox'}
                ,{field: 'room_name', width: 80, title: '机房', align:'center'}
                ,{field: 'wpd', title: '未派单', minWidth: 100, align:'center'}
                ,{field: 'wxz', title: '维修中', minWidth: 100, align:'center'}
                ,{field: 'ywx', title: '已维修', align:'center'}
                ,{field: 'ysh', title: '已审核', align:'center'}
            ]]
            ,page: false
            ,limit: 10
            // ,min_height: '193'
            ,text: '对不起，加载出现异常！'
			,done:function () {
                $.ajax({
                    url: 'operatio/queryOpeationByPageId',
                    type:"get",
                    /* contentType:"application/json",*/
                    data:{"pageid":"11"},
                    dataType: "json",
                    success: function (data) {
                        var opeations = data['data'];
                        for(var i=0;i<opeations.length;i++){
                            var styleid = opeations[i].btnTitle;
                            $("a[name='"+styleid+"']").show();
                        }
                    },
                    error: function () {
                        layer.msg("按钮信息加载失败");
                    }
                })
            }
        });
    });

    exports('workorderStatistics', {})

    
  
});