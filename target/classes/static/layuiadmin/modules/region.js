/**

 @Name： 区域管理
 @Author：renpengfei
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //区域管理
  table.render({
    elem: '#LAY-region-manage'
    //,url: layui.setter.base + 'json/useradmin/webuser.js' //模拟接口
    ,url: 'region/getRegionList'
    ,cols: [[
      {type: 'checkbox'}
      ,{field: 'id', title: 'ID', minWidth: 100, align:'center'}
      ,{field: 'regionName', title: '区域名称', align:'center'}
      ,{field: 'regionNumber', title: '区域编号',  align:'center'}
      ,{field: 'regionSort', title: '区域排序',  align:'center'}
      ,{field: 'regionDesc', title: '区域描述', align:'center'}
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
              data:{"pageid":"19"},
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
  table.on('tool(LAY-region-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('您确定真的删除么？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'region/delRegion?id='+data.id,
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
        ,title: '编辑区域'
        ,content: 'regionform'
        ,maxmin: false
        ,area: ['480px', '500px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-region-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            var jsonstr = {
                    "id":field.id,
                    "regionName":field.regionName,
                    "regionNumber":field.regionNumber,
                    "regionSort":field.regionSort,
                    "regionDesc":field.regionDesc
              };
            $.ajax({
                  url: 'region/updRegion/',
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
            body.contents().find("#regionName").val(data.regionName);
            body.contents().find("#regionNumber").val(data.regionNumber);
            body.contents().find("#regionSort").val(data.regionSort);
            body.contents().find("#regionDesc").val(data.regionDesc);
          }
      });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 2
            ,title: '查看区域详情'
            ,content: 'regionform'
            ,maxmin: false
            ,area: ['480px', '500px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#id").val(data.id);
                body.contents().find("#regionName").val(data.regionName);
                body.contents().find("#regionNumber").val(data.regionNumber);
                body.contents().find("#regionSort").val(data.regionSort);
                body.contents().find("#regionDesc").val(data.regionDesc);
                body.contents().find("#regionName").attr("disabled", true);
                body.contents().find("#regionNumber").attr("disabled", true);
                body.contents().find("#regionSort").attr("disabled", true);
                body.contents().find("#regionDesc").attr("disabled", true);
            }
        });
    }
  });

  exports('region', {})
});