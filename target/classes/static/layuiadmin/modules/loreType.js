/**

 @Name： 知识类别管理
 @Author：wangxiao
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //加载列表
  table.render({
    elem: '#LAY-loreType-manage'
    ,url: 'loreType/listLoreType'
    ,cols: [[
     {field: 'kbtName', title: '类别名称', align:'center'}
      ,{field: 'kbtNumber', title: '类别编号',  align:'center'}
      ,{field: 'gmtModified', title: '最后修改时间', align:'center', templet: '#timeTpl'}
      ,{title: '操作', width: 220, align:'center', toolbar: '#table-loreType-base'}
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
              data:{"pageid":"39"},
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
  table.on('tool(LAY-loreType-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('是否确定删除？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'loreType/delLoreType?id='+data.id,
                type:"get",
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
        ,title: '编辑知类别'
        ,content: 'loreTypeform'
        ,maxmin: false
        ,area: ['460px', '290px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-loreType-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            var jsonObj = {
                "id":field.uid,
                "kbtName": field.type_name,
                "kbtNumber": field.type_num
              };
            $.ajax({
                  url: 'loreType/updLoreType/',
                  type:"post",
                  data: jsonObj,
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
              body.contents().find("#type_name").val(data.kbtName);
              body.contents().find("#type_num").val(data.kbtNumber);
          }
      });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 2
            ,title: '查看知识类别'
            ,content: 'loreTypeform'
            ,maxmin: false
            ,area: ['460px', '290px']
            ,btn: ['取消']
            ,success: function (layero, index) {

                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#uid").val(data.id);
                body.contents().find("#type_name").val(data.kbtName);
                body.contents().find("#type_num").val(data.kbtNumber);
                body.contents().find("#type_name").attr("disabled",true);
                body.contents().find("#type_num").attr("disabled",true);
            }
        });
    }
  });

  exports('loreType', {})
});