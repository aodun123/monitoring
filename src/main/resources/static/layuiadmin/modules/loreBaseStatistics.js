/**
 @Name：layuiAdmin Echarts集成
 @Author：刘梅
 */
var exportData,sbl,xtl,aql,ggzs,qt;

function initData() {
    $.ajaxSettings.async = false;
    $.get("loreBase/statisticsNum",function (data) {
        exportData = data;
        sbl = data[0];
        xtl = data[1];
        aql = data[2];
        ggzs = data[3];
        qt = data[4];
    });
    $.ajaxSettings.async = true;
}

$("#addbtn").click(function () {
    var index = layer.confirm('导出Excel文件至本地', {
        btn: ['确认', '取消']
    },function () {
        layer.load(0);
        var downloadURL = "loreBase/exportExcel";
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

    //饼图
    layui.use(['carousel', 'echarts'], function(){
        var $ = layui.$
            ,carousel = layui.carousel
            ,echarts = layui.echarts;

        //标准饼图
        var echnormcol = [], normcol = [
            {
                title : {
                    text: '知识库数量统计图表',
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
                    data:['设备类','系统类','安全类','公共知识','其它']
                },
                calculable : true,
                series : [
                    {
                        name:'知识库数量统计',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:sbl.num, name:'设备类'},
                            {value:xtl.num, name:'系统类'},
                            {value:aql.num, name:'安全类'},
                            {value:ggzs.num, name:'公共知识'},
                            {value:qt.num, name:'其它'}
                        ]
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
            ,url: 'loreBase/statisticsList'
            ,cols: [[
                {field: 'type', title: '知识类别', align:'center'}
                ,{field: 'num', title: '数量', align:'center'}
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
                    data:{"pageid":"14"},
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

    exports('loreBaseStatistics', {})

});