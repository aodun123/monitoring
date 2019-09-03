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
                ,arrow: 'none'//always
                ,interval: 8000//othis.data('interval')
                ,autoplay: true//othis.data('autoplay') === true
                ,trigger: (device.ios || device.android) ? 'click' : 'hover'
                ,anim: othis.data('anim')
            });
        });
        element.render('progress');
    });

    //机房资源统计
    layui.use(['carousel', 'echarts'], function(){
        var $ = layui.$
            ,carousel = layui.carousel
            ,echarts = layui.echarts;

        var echartsApp = [], options = [

            //中心机房资源分布
            {
                title : {
                    text: '中心机房资源分布',
                    x: 'center',
                    textStyle: {
                        fontSize: 12
                    }
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:['服务器','交换机','路由器','其它设备']
                },
                series : [{
                    name:'数据统计',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:[
                        {value:dc1.fwq, name:'服务器'},
                        {value:dc1.jhj, name:'交换机'},
                        {value:dc1.lyq, name:'路由器'},
                        {value:dc1.qt, name:'其它设备'}
                    ]
                }]
            },

            //二号机房资源分布
            {
                title : {
                    text: '网络机房资源分布',
                    x: 'center',
                    textStyle: {
                        fontSize: 12
                    }
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:['服务器','交换机','路由器','其它设备']
                },
                series : [{
                    name:'数据统计',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:[
                        {value:dc2.fwq, name:'服务器'},
                        {value:dc2.jhj, name:'交换机'},
                        {value:dc2.lyq, name:'路由器'},
                        {value:dc2.qt, name:'其它设备'}
                    ]
                }]
            },

            //三号机房资源分布
            {
                title : {
                    text: '侦察业务机房资源分布',
                    x: 'center',
                    textStyle: {
                        fontSize: 12
                    }
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:['服务器','交换机','路由器','其它设备']
                },
                series : [{
                    name:'数据统计',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:[
                        {value:dc3.fwq, name:'服务器'},
                        {value:dc3.jhj, name:'交换机'},
                        {value:dc3.lyq, name:'路由器'},
                        {value:dc3.qt, name:'其它设备'}
                    ]
                }]
            }
        ]
            ,elemDataView = $('#LAY-index-dataview').children('div')
            ,renderDataView = function(index){
            echartsApp[index] = echarts.init(elemDataView[index], layui.echartsTheme);
            echartsApp[index].setOption(options[index]);
            window.onresize = echartsApp[index].resize;
        };
        //没找到DOM，终止执行
        if(!elemDataView[0]) return;
        renderDataView(0);

        //监听数据概览轮播
        var carouselIndex = 0;
        carousel.on('change(LAY-index-dataview)', function(obj){
            renderDataView(carouselIndex = obj.index);
        });

        //监听侧边伸缩
        layui.admin.on('side', function(){
            setTimeout(function(){
                renderDataView(carouselIndex);
            }, 300);
        });

        //监听路由
        layui.admin.on('hash(tab)', function(){
            layui.router().path.join('') || renderDataView(carouselIndex);
        });
    });
    exports('welcome', {})
});