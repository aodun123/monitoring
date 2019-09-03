/**
 @Name：layuiAdmin Echarts集成
 @Author：刘梅
 */

var exportData,dc1,dc2,dc3;

//请求数据
function initData() {
    $.ajaxSettings.async = false;
    $.get("assets/assetsNum",function (data) {
        if(data != null) {
            exportData = data;
            dc1 = data[0];
            dc2 = data[1];
            dc3 = data[2];
        }
    });
    $.ajaxSettings.async = true;
}

$("#addbtn").click(function () {
    var index = layer.confirm('导出Excel文件至本地', {
        btn: ['确认', '取消']
    },function () {
        layer.load(0);
        var downloadURL = "assets/exportExcel";
        var form = $("<form>");
        form.attr("style","display:none");
        form.attr("target","");
        form.attr("method","post");
        form.attr("action",downloadURL);
        var input = $("<input>");
        input.attr("type","hidden");
        input.attr("name","jsonStr");
        input.attr("value",JSON.stringify(exportData));
        $("body").append(form);
        form.append(input);
        form.submit();
        layer.closeAll();
    },function () {
        layer.close(index);
    });
});

layui.define(function(exports){
    initData();
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
                    text: '各机房资源统计图表',
                    subtext: '根据数据计算'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['服务器','交换机','路由器','其它']
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ['中心机房','网络机房','侦察业务机房']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'服务器',
                        type:'bar',
                        data:[dc1.fwq, dc2.fwq, dc3.fwq],
                        markLine : {
                            data : [{type : 'average', name: '平均值'}]
                        }
                    },
                    {
                        name:'交换机',
                        type:'bar',
                        data:[dc1.jhj, dc2.jhj, dc3.jhj],
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
                    },
                    {
                        name:'路由器',
                        type:'bar',
                        data:[dc1.lyq, dc2.lyq, dc3.lyq],
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
                    },
                    {
                        name:'其它',
                        type:'bar',
                        data:[dc1.qt, dc2.qt, dc3.qt],
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
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
            ,url: 'assets/getAssetsData'
            ,cols: [[
                {type: 'checkbox'}
                ,{field: 'dc', title: '机房名称', minWidth: 100, align:'center'/*, templet: '#dcTpl'*/}
                ,{field: 'fwq', title: '服务器', minWidth: 100, align:'center'}
                ,{field: 'jhj', title: '交换机', minWidth: 100, align:'center'}
                ,{field: 'lyq', title: '路由器', align:'center'}
                ,{field: 'qt', title: '其他', align:'center'}
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
                    data:{"pageid":"13"},
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

    exports('resourceStatistics', {})

});