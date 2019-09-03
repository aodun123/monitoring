/**

 @Name： 机构管理
 @Author：liumei
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //用户管理
  table.render({
    elem: '#LAY-user-manage'
    // ,url: '/org/queryOrgAgencyByTreeId'
    ,url: '/org/queryAgencyByTreeId'
    ,cols: [[
      {type: 'checkbox'}
      ,{field: 'id', width: 120, title: 'ID', align:'center'}
      ,{field: 'orgId', title: '所属组织', align:'center', templet: '#orgIdTpl'}
      ,{field: 'parentId', title: '所属机构',  align:'center', templet: '#parentIdTpl'}
      ,{field: 'agencyName', title: '机构名称',  align:'center'}
      //,{field: 'agencyNum', title: '机构数',  align:'center'}
      ,{field: 'description', title: '机构描述',  align:'center'}
      ,{title: '操作', width: 220, align:'center', fixed: 'right', toolbar: '#table-useradmin-user'}
    ]]
    ,page: true
    ,limit: 10
    ,height: 'full-120'
    ,text: '对不起，加载出现异常！'
      ,done:function () {
          $.ajax({
              url: 'operatio/queryOpeationByPageId',
              type:"get",
              /* contentType:"application/json",*/
              data:{"pageid":"45"},
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

            var jsonstr = {
                "id":data.id
            };
            $.ajax({
                url: '/org/delOrgAgency?id='+data.id,
                type:"delete",
                data:JSON.stringify(jsonstr),//将json对象转换成json字符串发送
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
        ,title: '编辑机构'
        ,content: 'organiform'
        ,maxmin: false
        ,area: ['460px', '500px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-user-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);
          iframeWindow.layui.form.on('submit('+submitID+')',function (data) {
              var field = data.field;

          })
          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            var jsonstr = {
                  "id":field.id,
                  "orgId":field.orgId,
                  "parentId":field.parentId,
                  "agencyName":field.agencyName,
                  "agencyNum":field.agencyNum,
                  "description":field.description
              };alert(JSON.stringify(jsonstr));
            $.ajax({
                  url: '/org/updOrgAgency/',
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
              body.contents().find("#orgId").val(data.orgId);
              body.contents().find("#parentId").val(data.parentId);
              body.contents().find("#agencyName").val(data.agencyName);
              body.contents().find("#agencyNum").val(data.agencyNum);
              body.contents().find("#description").val(data.description);
              var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
              //调用弹出界面的方法
              iframeWin.initEditSelect(data.orgId);
              iframeWin.initEditSelect1(data.parentId);
          }
      });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 2
            ,title: '查看机构详情'
            ,content: 'organiform'
            ,maxmin: false
            ,area: ['460px', '500px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#id").val(data.id);
                body.contents().find("#orgId").val(data.orgId);
                body.contents().find("#parentId").val(data.parentId);
                body.contents().find("#agencyName").val(data.agencyName);
                body.contents().find("#agencyNum").val(data.agencyNum);
                body.contents().find("#description").val(data.description);

                body.contents().find("#id").attr("disabled", true);
                body.contents().find("#orgId").attr("disabled", true);
                body.contents().find("#parentId").attr("disabled", true);
                body.contents().find("#agencyName").attr("disabled", true);
                body.contents().find("#agencyNum").attr("disabled", true);
                body.contents().find("#description").attr("disabled", true);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
                //调用弹出界面的方法
                iframeWin.initEditSelect(data.orgId);
                iframeWin.initEditSelect1(data.parentId);
            }
        });
    }
  });

  exports('organi', {})
});