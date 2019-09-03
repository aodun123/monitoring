/**

 @Name： 设备管理
 @Author：renpengfei
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //设备管理
  table.render({
    elem: '#LAY-assets-manage'
    ,url: 'assets/getAllAssets'
    ,cols: [[
      {type: 'checkbox'}
      // ,{field: 'id', title: 'ID', minWidth: 100, align:'center'}
      ,{field: 'deviceId', title: '设备名称', align:'center', templet: '#deviceIdTpl'}
      // ,{field: 'deviceName', title: '设备名称',  align:'center', hidden:'true'}
      ,{field: 'assetsStatus', title: '设备状态', align:'center', templet: '#assetsStatusTpl'}
      ,{field: 'deviceType', title: '设备类型', align:'center', templet: '#deviceTypeTpl'}
      ,{field: 'deviceIp', title: '设备IP', align:'center'}
      //,{field: 'deviceHead', title: '设备负责人', align:'center'}
      ,{field: 'deviceTime', title: '设备时间', align:'center', templet: '#deviceTimeTpl'}
      ,{title: '操作', width: 220, align:'center', fixed: 'right', toolbar: '#table-useradmin-user'}
    ]]
    ,page: true
    ,limit: 10
    ,height: 'full-260'
    ,text: '对不起，加载出现异常！'
     ,done:function () {
          $.ajax({
              url: 'operatio/queryOpeationByPageId',
              type:"get",
              /* contentType:"application/json",*/
              data:{"pageid":"35"},
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

  //监听工具条
  table.on('tool(LAY-assets-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('您确定真的删除么？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'assets/delAssets?id='+data.id,
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
        ,title: '编辑资源'
        ,content: 'assetsform?deviceId='+data.deviceId
        ,maxmin: false
        ,area: ['500px', '400px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-assets-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            var jsonstr = {
                    "id":field.id,
                    "deviceId":field.deviceId,
                    "deviceName":field.deviceName,
                    "assetsStatus":field.assetsStatus,
                    "deviceTime":field.deviceTime
              };
            $.ajax({
                  url: 'assets/updAssets',
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
            body.contents().find("#id").val(data.id);
            // body.contents().find("#deviceId").val(data.deviceId);
            body.contents().find("#deviceName").val(data.deviceName);
            body.contents().find("#assetsStatus").val(data.assetsStatus);
            body.contents().find("#deviceTime").val(data.deviceTime);
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
            //调用弹出界面的方法
            iframeWin.initEditSelect(data.deviceId);
          }
      });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 2
            ,title: '查看设备详情'
            ,content: 'assetsform'
            ,maxmin: false
            ,area: ['500px', '400px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#id").val(data.id);
                // body.contents().find("#deviceId").val(data.deviceId);
                body.contents().find("#deviceName").val(data.deviceName);
                body.contents().find("#assetsStatus").val(data.assetsStatus);
                body.contents().find("#deviceTime").val(data.deviceTime);
                body.contents().find("#deviceId").attr("disabled", true);
                body.contents().find("#deviceName").attr("disabled", true);
                body.contents().find("#assetsStatus").attr("disabled", true);
                body.contents().find("#deviceTime").attr("disabled", true);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
                //调用弹出界面的方法
                iframeWin.initEditSelect(data.deviceId);
            }
        });
    }
  });

  exports('assets', {})
});