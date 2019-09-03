/**

 @Name： 用户管理
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
    ,url: 'sysuser/queryAllUser'
    ,cols: [[
      {type: 'checkbox'}
      ,{field: 'id', width: 80, title: 'ID', align:'center'}
      ,{field: 'username', title: '用户名', minWidth: 100, align:'center'}
      ,{field: 'realname', title: '姓名', minWidth: 100, align:'center'}
      ,{field: 'usernumber', title: '用户编号', align:'center'}
      //,{field: 'image', title: '头像', width: 100, templet: '#imgTpl', align:'center'}
      ,{field: 'sex', width: 80, title: '性别', templet: '#sexTpl', align:'center'}
      ,{field: 'phone', title: '手机号', align:'center'}
      //,{field: 'email', title: '邮箱', align:'center'}
      ,{field: 'status', title: '人员状态', sort: true, templet: '#statusTpl', align:'center'}
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
              data:{"pageid":"42"},
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
  table.on('tool(LAY-user-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('您确定真的删除么？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'sysuser/delUser/'+data.id,
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
        ,title: '编辑用户'
        ,content: 'userform'
        ,maxmin: false
        ,area: ['680px', '500px']
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
                  "id":field.uid,
                  "username":field.username,
                  "password":field.password,
                  "realname":field.realname,
                  "usernumber":field.usernumber,
                  "sex":field.sex,
                  "cardid":field.cardid,
                  "phone":field.phone,
                  "email":field.email,
                  "status":field.status,
                  "image":field.image,
                  "adress":field.adress,
                  "descption":field.descption
              };
            $.ajax({
                  url: 'sysuser/upUser',
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
              body.contents().find("#username").val(data.username);
              body.contents().find("#password").val(data.password);
              body.contents().find("#realname").val(data.realname);
              body.contents().find("#usernumber").val(data.usernumber);
              body.contents().find("#sex").val(data.sex);
              body.contents().find("#cardid").val(data.cardid);
              body.contents().find("#phone").val(data.phone);
              body.contents().find("#email").val(data.email);
              body.contents().find("#status").val(data.status);
              body.contents().find("#image").val(data.image);
              body.contents().find("#adress").val(data.adress);
              body.contents().find("#descption").val(data.descption);
          }
      });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 2
            ,title: '查看用户详情'
            ,content: 'userform'
            ,maxmin: false
            ,area: ['680px', '500px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#uid").val(data.id);
                body.contents().find("#username").val(data.username);
                body.contents().find("#password").val(data.password);
                body.contents().find("#realname").val(data.realname);
                body.contents().find("#usernumber").val(data.usernumber);
                body.contents().find("#sex").val(data.sex);
                body.contents().find("#cardid").val(data.cardid);
                body.contents().find("#phone").val(data.phone);
                body.contents().find("#email").val(data.email);
                body.contents().find("#status").val(data.status);
                body.contents().find("#image").val(data.image);
                body.contents().find("#adress").val(data.adress);
                body.contents().find("#descption").val(data.descption);
                body.contents().find("#username").attr("disabled", true);
                body.contents().find("#password").attr("disabled", true);
                body.contents().find("#realname").attr("disabled", true);
                body.contents().find("#usernumber").attr("disabled", true);
                body.contents().find("#sex").attr("disabled", true);
                body.contents().find("#cardid").attr("disabled", true);
                body.contents().find("#phone").attr("disabled", true);
                body.contents().find("#email").attr("disabled", true);
                body.contents().find("#status").attr("disabled", true);
                body.contents().find("#image").attr("disabled", true);
                body.contents().find("#adress").attr("disabled", true);
                body.contents().find("#descption").attr("disabled", true);
            }
        });
    }
  });

  exports('user', {})
});