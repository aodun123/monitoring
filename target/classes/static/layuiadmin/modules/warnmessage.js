/**

 @Name： 报警参数设置
 @Author：liumei

 */

layui.define(['table', 'form'], function(exports){
    var $ = layui.$
        ,table = layui.table
        ,form = layui.form;

    //用户管理
    table.render({
        elem: '#LAY-user-manage'
        //,url: layui.setter.base + 'json/useradmin/webuser.js' //模拟接口
        ,url: 'task/selectTask'
        ,cols: [[
             {field: 'id', width: 120, title: '任务标识', align:'center'}
            ,{field: 'messagrtype',width: 120, title: '类型', align:'center'}
            ,{field: 'dbrps',width: 200, title: 'DBRP',  align:'center'}
            ,{field: 'status', width: 120,title: '任务状态',  align:'center',toolbar:'#table-warn-status'}
            ,{field: 'modified', width: 180,title: '修改时间',  align:'center'}
            ,{title: '操作', width: 235, align:'center',toolbar: '#table-useradmin-user'}
        ]]
        ,page: true
        ,limit: 10
        ,height: 'full-100'
        ,text: '对不起，加载出现异常！'
        ,done:function () {
            $.ajax({
                url: 'operatio/queryOpeationByPageId',
                type:"get",
                /* contentType:"application/json",*/
                data:{"pageid":"6"},
                dataType: "json",
                success: function (data) {
                    var opeations = data['data'];
                    console.log(opeations);
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
        if(obj.event === 'upeab'){
            var jsonstr = {
                "id":data.id,
                "status":data.status
            };
            $.ajax({
                url: 'task/upStatus',
                type:"patch",
                contentType:"application/json",//设置请求参数类型为json字符串
                data:JSON.stringify(jsonstr),//将json对象转换成json字符串发送
                dataType: "json",
                success: function (data) {
                    var status = data.optStatusCode;
                    if (status == 10400) {
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
        }else if(obj.event === 'updis'){
            var jsonstr = {
                "id":data.id,
                "status":data.status
            };
            $.ajax({
                url: 'task/upStatus',
                type:"patch",
                contentType:"application/json",//设置请求参数类型为json字符串
                data:JSON.stringify(jsonstr),//将json对象转换成json字符串发送
                dataType: "json",
                success: function (data) {
                    var status = data.optStatusCode;
                    if (status == 10400) {
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
        }
        if(obj.event === 'del') {
            layer.confirm('您确定真的删除么？', function (index) {
                obj.del();
                layer.close(index);
                $.ajax({
                    url: 'task/delAlertTask?Task_Id=' + data.id,
                    type: "delete",
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
        }else if(obj.event === 'detail'){
            layer.open({
                type: 2
                ,title: '查看任务详情'
                ,content: 'selectform'
                ,maxmin: false
                ,area: ['480px', '520px']
                ,btn: ['取消']
                ,success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                    body.contents().find("#taskid").val(data.id);
                    body.contents().find("#dbrps").val(data.dbrps);
                    body.contents().find("#script").val(data.script);
                    body.contents().find("#vars").val(data.vars);
                    body.contents().find("#type").val(data.messagrtype);
                    body.contents().find("#status").val(data.status);
                    body.contents().find("#taskid").attr("disabled", true);
                    body.contents().find("#dbrps").attr("disabled", true);
                    body.contents().find("#script").attr("disabled", true);
                    body.contents().find("#vars").attr("disabled", true);
                    body.contents().find("#type").attr("disabled", true);
                    body.contents().find("#status").attr("disabled", true);
                }
            });
        }
    });

    exports('warnmessage', {})
});