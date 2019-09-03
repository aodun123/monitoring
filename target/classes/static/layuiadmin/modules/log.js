/**

 @Name： 日志管理
 @Author：liumei
    
 */
function getReqUrl() {
    var uri = window.location.href;
    var params = uri.split('?')[1];
    var type = params.split("=")[1];
    return  'log/listLog?log_type='+type;
}

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;
  var url = getReqUrl();
  //日志管理
  table.render({
    elem: '#LAY-user-manage'
    ,url:url
    ,cols: [[
        {field: 'id', title: 'ID',  align:'center'}
     // {field: 'logType',title: '日志类型', align:'center'}
     // {field: 'logLevel', title: '日志等级',  align:'center'}
      ,{field: 'ipAddress', title: '客户端ip',  align:'center'}
      ,{field: 'operationUserId', title: '操作人',  align:'center'}
      ,{field: 'logContent',width: 300, title: '日志内容',  align:'center'}
      ,{field: 'gmtCreate', title: '创建时间',  align:'center', templet: '#timeTpl'}
      ,{title: '操作', width: 100, align:'center', toolbar: '#table-useradmin-user'}
    ]]
    ,page: true
    ,limit: 10
    ,height: 'full-220'
    ,text: '对不起，加载出现异常！'
  });

  //监听工具条
  table.on('tool(LAY-user-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
        layer.open({
            type: 2
            ,title: '查看日志'
            ,content: 'logform'
            ,maxmin: false
            ,area: ['1000px', '356px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#id").val(data.id);
                switch (data.logType) {
                    case '2':
                        body.contents().find("#logType").val('系统日志');
                        break;
                    default:
                        body.contents().find("#logType").val('业务日志');
                        break;
                }
                body.contents().find("#logLevel").val(data.logLevel);
                body.contents().find("#ipAddress").val(data.ipAddress);
                body.contents().find("#operationUserId").val(data.operationUserId);
                body.contents().find("#logContent").val(data.logContent);
                body.contents().find("#gmtCreate").val(time2date(data.gmtCreate));

                body.contents().find("#id").attr("disabled", true);
                body.contents().find("#logType").attr("disabled", true);
                body.contents().find("#logLevel").attr("disabled", true);
                body.contents().find("#ipAddress").attr("disabled", true);
                body.contents().find("#operationUserId").attr("disabled", true);
                body.contents().find("#logContent").attr("disabled", true);
                body.contents().find("#gmtCreate").attr("disabled", true);
            }
        });
    }
  });
  exports('log', {})
});