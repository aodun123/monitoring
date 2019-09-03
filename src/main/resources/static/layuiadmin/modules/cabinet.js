/**

 @Name： 机柜管理
 @Author：wangxiao
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //加载列表
  table.render({
    elem: '#LAY-cabinet-manage'
    ,url: 'cabinet/listCabinet'
    ,cols: [[
      {field: 'mrId', title: '所在机房', align:'center', templet: '#selIml'}
      ,{field: 'mrcName', title: '机柜名称',  align:'center'}
      ,{field: 'mrcNumber', title: '机柜编号',  align:'center'}
      ,{field: 'mrcAdress', title: '机柜位置',  align:'center'}
      ,{field: 'mrcDesc', title: '机柜描述',  align:'center'}
      ,{field: 'gmtCreate', title: '创建时间',  align:'center', templet: '#timeTpl'}
      ,{title: '操作', width: 220, align:'center', fixed: 'right', toolbar: '#table-cabinet-base'}
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
              data:{"pageid":"21"},
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
  table.on('tool(LAY-cabinet-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('是否确定删除？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'cabinet/delCabinet?id='+data.id,
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
        type: 1
        ,title: '编辑机柜信息'
        ,content: $('#layuiadmin-form-useradmin')
        ,maxmin: false
        ,area: ['520px', '520px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var submit = $('#LAY-cabinet-submit');
          //监听提交
          layui.form.on('submit(LAY-cabinet-submit)', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            var jsonstr = {
                id:field.uid,
                mrId: field.mr_id,
                mrcName: field.mrc_name,
                mrcNumber: field.mrc_number,
                mrcAdress: field.mrc_adress,
                mrcDesc: field.mrc_desc
              };
            $.ajax({
                  url: 'cabinet/updCabinet/',
                  type:"post",
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
            delDisabled();
            $("#uid").val(data.id);
            $("#mr_id").val(data.mrId);
            $("#mrc_name").val(data.mrcName);
            $("#mrc_number").val(data.mrcNumber);
            $("#mrc_adress").val(data.mrcAdress);
            $("#mrc_desc").val(data.mrcDesc);
            layui.use('form', function(){
                var form = layui.form;
                form.render();
            });
          }
      });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 1
            ,title: '查看机柜信息'
            ,content: $('#layuiadmin-form-useradmin')
            ,maxmin: false
            ,area: ['520px', '520px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                delDisabled();
                $("#uid").val(data.id);
                $("#mr_id").val(data.mrId);
                $("#mrc_name").val(data.mrcName);
                $("#mrc_number").val(data.mrcNumber);
                $("#mrc_adress").val(data.mrcAdress);
                $("#mrc_desc").val(data.mrcDesc);
                setDisabled();
            }
        });
    }
  });
  exports('cabinet', {})
});


function setDisabled() {
    $("#mr_id").attr("disabled",true);
    $("#mrc_name").attr("disabled",true);
    $("#mrc_number").attr("disabled",true);
    $("#mrc_adress").attr("disabled",true);
    $("#mrc_desc").attr("disabled",true);
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}

function delDisabled() {
    $("#mr_id").removeAttr("disabled");
    $("#mrc_name").removeAttr("disabled");
    $("#mrc_number").removeAttr("disabled");
    $("#mrc_adress").removeAttr("disabled");
    $("#mrc_desc").removeAttr("disabled");
    $("#mr_id").val('');
    $("#mrc_name").val('');
    $("#mrc_number").val('');
    $("#mrc_adress").val('');
    $("#mrc_desc").val('');
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}
