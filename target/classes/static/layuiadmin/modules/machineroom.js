/**

 @Name： 设备管理
 @Author：liumei
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //设备管理
  table.render({
    elem: '#LAY-machineroom-manage'
    ,url: 'machineroom/getMachineRoomList'
    ,cols: [[
      {type: 'checkbox'}
      ,{field: 'id', title: 'ID', align:'center'}
      ,{field: 'mrName', title: '机房名称', align:'center'}
      ,{field: 'regionId', title: '所在区域',  align:'center', templet: '#regionIdTpl'}
      ,{field: 'mrNumber', title: '机房编号', align:'center'}
      // ,{field: 'mrImgurl', title: '机房图地址', align:'center'}
      ,{field: 'mrAddress', title: '机房地址',  align:'center'}
      ,{field: 'mrSort', title: '机房排序', align:'center'}
      ,{field: 'mrDesc', title: '机房描述', align:'center'}
      ,{title: '操作', width: 220, align:'center', fixed: 'right', toolbar: '#table-machineroom-base'}
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
              data:{"pageid":"20"},
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
  table.on('tool(LAY-machineroom-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('您确定真的删除么？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'machineroom/delMachineRoom?id='+data.id,
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
            ,title: '编辑机房'
            ,content: 'machineRoomform'
            ,maxmin: false
            ,area: ['480px', '500px']
            ,btn: ['确定', '取消']
            ,yes: function(index, layero){
              var iframeWindow = window['layui-layer-iframe'+ index]
              ,submitID = 'LAY-machineroom-submit'
              ,submit = layero.find('iframe').contents().find('#'+ submitID);

              //监听提交
              iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                //提交 Ajax 成功后，静态更新表格中的数据
                var jsonstr = {
                        "id":field.id,
                        "mrName":field.mrName,
                        "regionId":field.regionId,
                        "mrNumber":field.mrNumber,
                        "mrImgurl":field.mrImgurl,
                        "mrAddress":field.mrAddress,
                        "mrSort":field.mrSort,
                        "mrDesc":field.mrDesc
                  };
                $.ajax({
                  url: 'machineroom/updMachineRoom/',
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
                body.contents().find("#mrName").val(data.mrName);
                //body.contents().find("#regionId").val(data.regionId);
                body.contents().find("#mrNumber").val(data.mrNumber);
                body.contents().find("#mrImgurl").val(data.mrImgurl);
                body.contents().find("#mrAddress").val(data.mrAddress);
                body.contents().find("#mrSort").val(data.mrSort);
                body.contents().find("#mrDesc").val(data.mrDesc);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
                //调用弹出界面的方法
                iframeWin.initEditSelect(data.regionId);
              }
        });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 2
            ,title: '查看机房详情'
            ,content: 'machineRoomform'
            ,maxmin: false
            ,area: ['480px', '500px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#id").val(data.id);
                body.contents().find("#mrName").val(data.mrName);
                //body.contents().find("#regionId").val(data.regionId);
                body.contents().find("#mrNumber").val(data.mrNumber);
                body.contents().find("#mrImgurl").val(data.mrImgurl);
                body.contents().find("#mrAddress").val(data.mrAddress);
                body.contents().find("#mrSort").val(data.mrSort);
                body.contents().find("#mrDesc").val(data.mrDesc);
                body.contents().find("#mrName").attr("disabled", true);
                body.contents().find("#regionId").attr("disabled", true);
                body.contents().find("#mrNumber").attr("disabled", true);
                body.contents().find("#mrImgurl").attr("disabled", true);
                body.contents().find("#mrAddress").attr("disabled", true);
                body.contents().find("#mrSort").attr("disabled", true);
                body.contents().find("#mrDesc").attr("disabled", true);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
                //调用弹出界面的方法
                iframeWin.initEditSelect(data.regionId);
            }
        });
    }
  });

  exports('machineroom', {})
});