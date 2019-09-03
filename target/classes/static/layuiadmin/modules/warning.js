/**

 @Name：告警信息
 @Author：wangxiao
    
 */
var status = '';
function getReqUrl() {
    var url = 'warn/listWarn';
    var uri = window.location.href;
    var params = uri.split("=");
    if(params[1]) {
        status = params[1];
        url += '?status='+status;
    }
    return  url;
}

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;
  var url = getReqUrl();
  var toolbar = '#table-warn-base';
  if(status != '') {
      toolbar = '#table-warn-base1';
  }
  //加载列表
  table.render({
    elem: '#LAY-warn-manage'
    ,url: url
    ,cols: [[
      {field: 'warnTitle', title: '告警类型', align:'center'}
      ,{field: 'warnDc', title: '告警机房', align:'center'}
      ,{field: 'warnSource', title: '告警来源',  align:'center'}
      /*,{field: 'warnLevel', title: '告警级别',  align:'center', templet: '#w_level'}*/
      ,{field: 'resourceType', title: '资源类型',  align:'center', templet: '#r_type'}
      ,{field: 'warnTime', title: '告警时间',  align:'center', templet: '#timeTpl'}
      ,{field: 'warnStatus', title: '告警状态',  align:'center', templet: '#w_status'}
      ,{field: 'warnConfirmStatus', title: '确认状态',  align:'center', templet: '#c_status'}
      ,{title: '操作', width: 180, align:'center', toolbar: toolbar}
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
              data:{"pageid":"3"},
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
  table.on('tool(LAY-warn-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('是否确定删除？', function(index){
            obj.del();
            layer.close(index);
        });
    } else if(obj.event === 'edit'){
        var index = layer.confirm('开始处理该告警？', {
            btn: ['确认','取消']
        },function () {
            $("#measure").val('');
            switch (status) {
                case '1':
                    processAlert(data.id);
                    break;
                case '2':
                    layer.close(index);
                    layer.open({
                        type: 1,
                        title: "描述处理措施",
                        content: $("#measure_div"),
                        resizeing: false,
                        maxmin: false,
                        area: ['400px', '300px'],
                        btn: ['确定'],
                        yes: function(index, layero){
                            processAlert(data.id);
                            layer.close(index); //如果设定了yes回调，需进行手工关闭
                        }
                    });
                    break;
            }
        },function () {
            layer.close(index);
        });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 1
            ,title: '查看告警信息'
            ,content: $('#layuiadmin-form-useradmin')
            ,maxmin: false
            ,area: ['520px', '520px']
            ,btn: ['确定']
            ,success: function (layero, index) {
                delDisabled();
                $("#uid").val(data.id);
                $("#warn_title").val(data.warnTitle);
                $("#warn_dc").val(data.warnDc);
                $("#warn_source").val(data.warnSource);
                $("#warn_level").val(data.warnLevel);
                $("#resource_type").val(data.resourceType);
                $("#warn_time").val(time2date(data.warnTime));
                $("#warn_content").val(data.warnContent);
                $("#process").val(data.process);
                $("#warn_status").val(data.warnStatus);
                console.log(data.warnStatus);
                $("#warn_confirm_status").val(data.warnConfirmStatus);
                setDisabled();
            }
        });
    }
  });
  exports('warning', {})
});

//处理告警
function processAlert(id) {
   var status1;
   var postData = {};
   postData.id = id;
   if(status == '1') {
       postData.status = 2;
   }
   if(status == '2') {
       postData.status = 3;
       postData.measure = $("#measure").val();
   }
   $.ajax({
       url: "warn/processAlert",
       type: "POST",
       data: postData,
       dataType: "json",
       success: function (ret) {
           if(ret.optStatusCode == 10400) {
               layer.msg("处理成功", {icon: 6});
               $(".layui-laypage-btn").click();//数据刷新
           }else {
               layer.msg("操作失败,错误信息:"+ret.message, {icon: 5, time: 5000});
           }
       },error: function (msg) {
           alert("请求出错");
       }
   });
}

//批量禁用表单项
function setDisabled() {
    $("#warn_title").attr("disabled",true);
    $("#warn_dc").attr('disabled',true);
    $("#warn_source").attr("disabled",true);
    $("#warn_level").attr("disabled",true);
    $("#resource_type").attr("disabled",true);
    $("#warn_time").attr("disabled",true);
    $("#warn_content").attr("disabled",true);
    $("#warn_status").attr("disabled",true);
    $("#warn_confirm_status").attr("disabled",true);
    $("#process").attr("disabled",true);
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}

//批量删除禁用
function delDisabled() {
    $("#warn_title").removeAttr("disabled");
    $("#warn_dc").removeAttr("disabled");
    $("#warn_source").removeAttr("disabled");
    $("#warn_level").removeAttr("disabled");
    $("#resource_type").removeAttr("disabled");
    $("#warn_time").removeAttr("disabled");
    $("#warn_content").removeAttr("disabled");
    $("#warn_status").removeAttr("disabled");
    $("#warn_confirm_status").removeAttr("disabled");
    $("#process").removeAttr("disabled");
    $("#warn_title").val('');
    $("#warn_source").val('');
    $("#warn_level").val('');
    $("#warn_dc").val('');
    $("#resource_type").val('');
    $("#warn_time").val('');
    $("#warn_content").val('');
    $("#warn_status").val('');
    $("#warn_confirm_status").val('');
    $("#process").val('');
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}



