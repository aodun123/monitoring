/**

 @Name：告警参数设置
 @Author：wangxiao
    
 */
function getReqUrl() {
    var url = 'warn/listWarn';
    var uri = window.location.href;
    var status = uri.split("=");
    if(status[1]) {
        url += '?status='+status[1];
    }
    return  url;
}

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;
  var url = getReqUrl();
  //加载列表
  table.render({
    elem: '#LAY-warn-manage'
    ,url: 'task/selectTask'
    ,cols: [[
      {field: 'id', title: '任务', align:'center', templet: '#id'}
      ,{field: 'messagrtype', title: '类型',  align:'center', templet: '#messagrType'}
      ,{field: 'dbrps', title: 'dbrps',  align:'center', templet: '#dbrps'}
      ,{field: 'status', title: '状态',  align:'center', templet: '#status'}
      ,{field: 'modified', title: '最后修改时间',  align:'center', templet: '#last_Modified'}
      ,{title: '操作', align:'center', fixed: 'right', toolbar: '#table-warn-base'}
    ]]
    ,page: true
    ,limit: 10
    ,height: 'full-220'
    ,text: '对不起，加载出现异常！'
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

    }else if(obj.event === 'detail'){
        layer.open({
            type: 1
            ,title: '查看告警参数设置'
            ,content: $('#layuiadmin-form-useradmin')
            ,maxmin: false
            ,area: ['480px', '420px']
            ,btn: ['确定']
            ,success: function (layero, index) {
                delDisabled();
                $("#id").val(data.id);
                $("#messagrType").val(data.messagrtype);
                $("#dbrps").val(data.dbrps);
                $("#table-warn-status").val(data.status);
                $("#last_Modified").val(data.modified);
                setDisabled();
            }
        });
    }
  });
  exports('warnset', {})
});


function setDisabled() {
    $("#id").attr("disabled",true);
    $("#messagrType").attr('disabled',true);
    $("#dbrps").attr("disabled",true);
    $("#table-warn-status").attr("disabled",true);
    $("#last_Modified").attr("disabled",true);
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}

function delDisabled() {
    $("#id").removeAttr("disabled");
    $("#messagrType").removeAttr("disabled");
    $("#dbrps").removeAttr("disabled");
    $("#table-warn-status").removeAttr("disabled");
    $("#last_Modified").removeAttr("disabled");
    $("#id").val('');
    $("#messagrType").val('');
    $("#dbrps").val('');
    $("#table-warn-status").val('');
    $("#last_Modified").val('');

    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}
