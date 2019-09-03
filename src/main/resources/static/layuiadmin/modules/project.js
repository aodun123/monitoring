/**

 @Name： 项目管理
 @Author：renpengfei
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //项目管理
  table.render({
    elem: '#LAY-project-manage'
    //,url: layui.setter.base + 'json/useradmin/webuser.js' //模拟接口
    ,url: 'project/getProjectList'
    ,cols: [[
      {type: 'checkbox'}
      ,{field: 'id', title: 'ID', minWidth: 100, align:'center'}
      ,{field: 'projectName', title: '项目名称', align:'center'}
      //,{field: 'projectType', title: '项目类型',  align:'center'}
      ,{field: 'projectHeader', title: '项目负责人', align:'center'}
      ,{field: 'projectContacts', title: '项目联系人',  align:'center'}
      ,{field: 'telphoneNumber', title: '联系电话', align:'center'}
      ,{title: '操作', width: 220, align:'center',fixed:'right',  toolbar: '#table-useradmin-user'}
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
              data:{"pageid":"24"},
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
  table.on('tool(LAY-project-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('您确定真的删除么？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'project/delProject?id='+data.id,
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
        ,title: '编辑项目'
        ,content: 'projectform'
        ,maxmin: false
        ,area: ['480px', '500px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-project-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            var jsonstr = {
                    "id":field.id,
                    "projectName":field.projectName,
                    "projectType":field.projectType,
                    "projectHeader":field.projectHeader,
                    "projectContacts":field.projectContacts,
                    "telphoneNumber":field.telphoneNumber
              };
            $.ajax({
                  url: 'project/updProject/',
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
            body.contents().find("#projectName").val(data.projectName);
            body.contents().find("#projectType").val(data.projectType);
            body.contents().find("#projectHeader").val(data.projectHeader);
            body.contents().find("#projectContacts").val(data.projectContacts);
            body.contents().find("#telphoneNumber").val(data.telphoneNumber);
          }
      });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 2
            ,title: '查看项目详情'
            ,content: 'projectform'
            ,maxmin: false
            ,area: ['480px', '500px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#id").val(data.id);
                body.contents().find("#projectName").val(data.projectName);
                body.contents().find("#projectType").val(data.projectType);
                body.contents().find("#projectHeader").val(data.projectHeader);
                body.contents().find("#projectContacts").val(data.projectContacts);
                body.contents().find("#telphoneNumber").val(data.telphoneNumber);
                body.contents().find("#projectName").attr("disabled", true);
                body.contents().find("#projectType").attr("disabled", true);
                body.contents().find("#projectHeader").attr("disabled", true);
                body.contents().find("#projectContacts").attr("disabled", true);
                body.contents().find("#telphoneNumber").attr("disabled", true);
            }
        });
    }
  });

  exports('project', {})
});