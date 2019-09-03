/**
 @Name：layuiAdmin Echarts集成
 @Author：刘梅
 */

layui.define(function(exports){

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
                    data:['服务器','交换机','路由器','存储设备','网络设备','其它']
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ['一号机房','二号机房','三号机房']
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
                        data:[192, 169, 180],
                        markLine : {
                            data : [{type : 'average', name: '平均值'}]
                        }
                    },
                    {
                        name:'交换机',
                        type:'bar',
                        data:[26, 59, 90],
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
                    },
                    {
                        name:'路由器',
                        type:'bar',
                        data:[16, 29, 6],
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
                    },
                    {
                        name:'存储设备',
                        type:'bar',
                        data:[16, 19, 16],
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
                    },
                    {
                        name:'网络设备',
                        type:'bar',
                        data:[6, 9, 12],
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
                    },
                    {
                        name:'其它',
                        type:'bar',
                        data:[26, 29, 20],
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
            ,url: 'sysuser/queryAllUser'
            ,cols: [[
                {type: 'checkbox'}
                ,{field: 'id', width: 80, title: 'ID', align:'center'}
                ,{field: 'username', title: '用户名', minWidth: 100, align:'center'}
                ,{field: 'realname', title: '姓名', minWidth: 100, align:'center'}
                ,{field: 'phone', title: '手机号', align:'center'}
                ,{field: 'email', title: '邮箱', align:'center'}
            ]]
            ,page: false
            ,limit: 10
            ,height: 'full-220'
            ,text: '对不起，加载出现异常！'
        });
    });

    exports('InspectionReport', {})

});