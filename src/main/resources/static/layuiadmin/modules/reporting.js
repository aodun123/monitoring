/**

 @Name： 知识库管理
 @Author：wangxiao
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //加载列表
  table.render({
    elem: '#LAY-report-manage'
    ,url: 'report/listReport'
    ,cols: [[
     {field: 'reportTitle', title: '标题说明', align:'center'}
      ,{field: 'mrId', title: '机房', align:'center', templet: '#selIml'}
      ,{field: 'reportPosition', title: '位置说明',  align:'center'}
      // ,{field: 'reportDesc', title: '故障描述',  align:'center'}
      ,{field: 'reportGrade', title: '故障等级',  align:'center', templet: '#r_grade'}
      ,{field: 'handleState', title: '处理状态',  align:'center', templet: '#h_state'}
      ,{field: 'createUserId', title: '创建人', align:'center'}
      ,{field: 'gmtCreate', title: '创建时间',  align:'center', templet: '#timeTpl'}
      ,{title: '操作', width: 220, align:'center', fixed: 'right', toolbar: '#table-report-base'}
    ]]
    ,page: true
    ,limit: 10
    ,height: 'full-220'
    ,text: '对不起，加载出现异常！'
  });

  //监听工具条
  table.on('tool(LAY-report-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('是否确定删除？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'report/delReport?id='+data.id,
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
        ,title: '编辑故障信息'
        ,content: $('#layuiadmin-form-useradmin')
        ,maxmin: false
        ,area: ['500px', '520px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var submit = $('#LAY-report-submit');
          //监听提交
          layui.form.on('submit(LAY-report-submit)', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            var jsonstr = {
                id:field.uid,
                mrId: field.mr_id,
                reportTitle: field.report_title,
                reportPosition: field.report_position,
                reportDesc: field.report_desc,
                reportGrade: field.report_grade,
                handleState: field.handle_state
              };
            $.ajax({
                  url: 'report/updReport',
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
            $("#report_title").val(data.reportTitle);
            $("#report_position").val(data.reportPosition);
            $("#report_desc").val(data.reportDesc);
            $("#report_grade").val(data.reportGrade);
            $("#handle_state").val(data.handleState);
            layui.use('form', function(){
                var form = layui.form;
                form.render();
            });
          }
      });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 1
            ,title: '查看故障详情'
            ,content: $('#layuiadmin-form-useradmin')
            ,maxmin: false
            ,area: ['500px', '520px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                $("#uid").val(data.id);
                $("#mr_id").val(data.mrId);
                $("#report_title").val(data.reportTitle);
                $("#report_position").val(data.reportPosition);
                $("#report_desc").val(data.reportDesc);
                $("#report_grade").val(data.reportGrade);
                $("#handle_state").val(data.handleState);
                setDisabled();
            }
        });
    }
  });
  exports('reporting', {})
});

function setDisabled() {
    $("#mr_id").attr("disabled",true);
    $("#report_title").attr("disabled",true);
    $("#report_position").attr("disabled",true);
    $("#report_desc").attr("disabled",true);
    $("#report_grade").attr("disabled",true);
    $("#handle_state").attr("disabled",true);

    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}

function delDisabled() {
    $("#mr_id").removeAttr("disabled");
    $("#report_title").removeAttr("disabled");
    $("#report_position").removeAttr("disabled");
    $("#report_desc").removeAttr("disabled");
    $("#report_grade").removeAttr("disabled");
    $("#handle_state").removeAttr("disabled");
    $("#uid").val('');
    $("#mr_id").val('');
    $("#report_title").val('');
    $("#report_position").val('');
    $("#report_desc").val('');
    $("#report_grade").val('');
    $("#handle_state").val('');
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}
