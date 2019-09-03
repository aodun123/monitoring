/**
 @Name：layuiAdmin Echarts集成
 @Author：刘梅
 */
var table_data;
var image_data=[];
function room_names_fun(device_rooms){
	var room_names = [];
	for(var i=0;i<device_rooms.length;i++){
		room_names.push(device_rooms[i].name);
	}
	return room_names;
}

function sum(){
	var s = 0;
	for(var i=0;i<table_data.length;i++){
		if(table_data[i].mr_id && table_data[i].mr_id!=''){
			s += parseInt(table_data[i].value);
		}else{
			table_data[i].value=0;
		}
		image_data.push(table_data[i]);
	}
	return s;
}

layui.define(function(exports){
	var device_rooms = request('bm/device_rooms');
	var room_names = room_names_fun(device_rooms);
	
	table_data=request('work_failure/statistics');

	var allcount = sum();
	for(var i=0;i<table_data.length;i++){
		table_data[i].pa=(table_data[i].value*100/allcount).toFixed(2)+"%"
	}
	table_data.push({name:'合计',value: allcount,pa:'100%'});
	$("#addbtn").click(function(){
    	var downloadURL = "work_failure/exportExcel";
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

    //饼图
    layui.use(['carousel', 'echarts'], function(){
        var $ = layui.$
            ,carousel = layui.carousel
            ,echarts = layui.echarts;

        //标准饼图
        var echnormcol = [], normcol = [
            {
                title : {
                    text: '各机房报障统计',
                    subtext: '根据真实数据计算',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:room_names
                },
                calculable : true,
                series : [
                    {
                        name:'各机房报障统计',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:image_data
                    }
                ]
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
            ,data:table_data
            ,cols: [[
                {type: 'checkbox'}
                ,{field: 'name', minWidth: 80, title: '机房', align:'center'}
                ,{field: 'value', title: '报障数量', minWidth: 100, align:'center'}
                ,{field: 'pa', title: '占比', minWidth: 100, align:'center'}
                
            ]]
            ,page: false
            ,limit: 10
            // ,height: 'full-220'
            ,text: '对不起，加载出现异常！'
            ,done:function () {
                $.ajax({
                    url: 'operatio/queryOpeationByPageId',
                    type:"get",
                    /* contentType:"application/json",*/
                    data:{"pageid":"16"},
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

    exports('securityStatistics', {})

});