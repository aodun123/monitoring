/**

 @Name： 用户管理
 @Author：liumei

 */

layui.define(['table', 'form'], function(exports){
    var $ = layui.$
        ,table = layui.table
        ,form = layui.form;

    //页面管理
    table.render({
        elem: '#LAY-user-manage'
        //,url: layui.setter.base + 'json/useradmin/webuser.js' //模拟接口
        ,url: 'syspage/selectPage'
        ,cols: [[
            {type: 'checkbox'}
            ,{field: 'id', width: 80, title: 'ID', align:'center'}
            ,{field: 'name', title: '页面名称', align:'center'}
            ,{field: 'descritpion', title: '页面描述',  align:'center'}
            ,{field: 'pageType', title: '页面分类',  align:'center',toolbar: '#table-user-select'}
            ,{title: '操作', width: 220, align:'center', fixed: 'right', toolbar: '#table-useradmin-user'}
        ]]
        ,page: true
        ,limit: 10
        ,height: 'full-220'
        ,text: '对不起，加载出现异常！'
        ,done:function () {
            $.ajax({
                url: 'operatio/queryOpeationByPageId',
                type:"get",
                /* contentType:"application/json",*/
                data:{"pageid":"46"},
                dataType: "json",
                success: function (data) {
                    var opeations = data['data'];
                    console.log(opeations)
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

    //监听工具条
    table.on('tool(LAY-user-manage)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('您确定真的删除么？', function(index){
                obj.del();
                layer.close(index);
                $.ajax({
                    url: 'syspage/delPage/'+data.id,
                    type:"delete",
                    dataType: "json",
                    success: function (data) {
                        var status = data.optStatusCode;
                        if (status == 10400) {
                            layer.msg("删除成功", {icon: 1});
                        } else {
                            layer.msg("删除数据失败");
                        }
                    },
                    error: function () {
                        layer.msg("删除数据失败");
                    }
                });
            });
        } else if(obj.event === 'edit'){
            layer.open({
                type: 2
                ,title: '编辑页面'
                ,content: 'pageform'
                ,maxmin: false
                ,area: ['460px', '290px']
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submitID = 'LAY-user-submit'
                        ,submit = layero.find('iframe').contents().find('#'+ submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        var jsonstr = {
                            "id":field.id,
                            "name":field.name,
                            "descritpion":field.descritpion,
                            "url":field.url,
                            "pid":field.pid,
                            "page_type":field.page_type
                        };
                        $.ajax({
                            url: 'syspage/upPage/',
                            type:"put",
                            contentType:"application/json",//设置请求参数类型为json字符串
                            data:JSON.stringify(jsonstr),//将json对象转换成json字符串发送
                            dataType: "json",
                            success: function (data) {
                                var status = data.optStatusCode;
                                if (status == 10400) {
                                    layer.close(index); //关闭弹层
                                    layer.msg("编辑成功", {icon: 1});
                                    $(".layui-laypage-btn").click();//数据刷新
                                } else {
                                    layer.msg("编辑数据失败");
                                }
                            },
                            error: function () {
                                layer.msg("编辑数据失败");
                            }
                        });
                    });
                    submit.trigger('click');
                }
                ,success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                    body.contents().find("#uid").val(data.id);
                    body.contents().find("#name").val(data.name);
                    body.contents().find("#descritpion").val(data.descritpion);
                    body.contents().find("#url").val(data.url);
                    body.contents().find("#pid").val(data.pid);
                }
            });
        }else if(obj.event === 'detail'){
            console.log(data)
            layer.open({
                type: 2
                ,title: '查看页面详情'
                ,content: 'pageform'
                ,maxmin: false
                ,area: ['480px', '350px']
                ,btn: ['取消']
                ,success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                    body.contents().find("#uid").val(data.id);
                    body.contents().find("#name").val(data.name);
                    body.contents().find("#descritpion").val(data.descritpion);
                    body.contents().find("#pid").val(data.pid);
                    body.contents().find("#url").val(data.url);
                    body.contents().find("#pageType").val(data.pageType);
                    body.contents().find("#pageJb").val(data.pageJb);
                    body.contents().find("#name").attr("disabled", true);
                    body.contents().find("#descritpion").attr("disabled", true);
                    body.contents().find("#dd").attr("style","display:none;");
                    body.contents().find("#url").attr("disabled", true);
                    body.contents().find("#pageType").attr("disabled", true);
                    body.contents().find("#pageJb").attr("disabled", true);
                    layui.use('form', function(){
                        var form = layui.form;
                        form.render('select');
                    });
                }
            });
        }
    });

    exports('page', {})
});