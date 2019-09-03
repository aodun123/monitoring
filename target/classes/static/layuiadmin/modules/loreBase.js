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
    elem: '#LAY-lore-manage'
    ,url: 'loreBase/listLoreBase'
    ,cols: [[
     {field: 'kbTitle', title: '知识标题', align:'center'}
      ,{field: 'kbtNumber', title: '知识类别',  align:'center', templet: '#selIml'}
      ,{field: 'kbScore', title: '综合评分',  align:'center'}
      ,{field: 'lookCount', title: '查看次数',  align:'center'}
      // ,{field: 'kbContent', title: '内容描述',  align:'center'}
      ,{field: 'createUserId', title: '创建人', align:'center'}
      //,{field: 'modifiedUserId', title: '最后修改人', align:'center'}
      // ,{field: 'gmtCreate', title: '创建时间',  align:'center'}
      //,{field: 'gmtModified', title: '最后修改时间',  align:'center', templet: '#timeTpl'}
      ,{title: '操作', width: 220, align:'center',fixed:'right', toolbar: '#table-lore-base'}
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
              data:{"pageid":"38"},
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
  table.on('tool(LAY-lore-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('是否确定删除？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'loreBase/delLoreBase?id='+data.id,
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
        createCkeditor("ckeditor");
        layer.open({
        type: 1
        ,title: '编辑知识信息'
        ,content: $('#layuiadmin-form-useradmin')
        ,maxmin: false
        ,area: ['800px', '500px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var submit = $('#LAY-lore-submit');
          //监听提交
          layui.form.on('submit(LAY-lore-submit)', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            var jsonstr = {
                id:field.uid,
                kbTitle: field.lore_title,
                kbtNumber: field.lore_type,
                kbScore: field.lore_score,
                kbContent: CKEDITOR.instances.ckeditor.getData()
              };
            $.ajax({
                  url: 'loreBase/updLoreBase/',
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
            $("#lore_title").val(data.kbTitle);
            $("#lore_type").val(data.kbtNumber);
            CKEDITOR.instances.ckeditor.setData(data.kbContent);
            $("#lore_score").val(data.kbScore);
            //评分组件
            layui.use('rate', function(){
                var rate = layui.rate;
                //渲染
                rate.render({
                    elem: $("#score"),
                    value: Number(data.kbScore),
                    choose: function(value){
                        $("#lore_score").val(value);
                    }
                });
            });
            layui.use('form', function(){
                var form = layui.form;
                form.render();
            });
          }
      });
    }else if(obj.event === 'detail'){
        createCkeditor("ckeditor");
        jQuery.get("loreBase/addSeeCount?id="+data.id,function (msg) {
        });
        $(".layui-laypage-btn").click();//数据刷新
        layer.open({
            type: 1
            ,title: '查看知识详情'
            ,content: $('#layuiadmin-form-useradmin')
            ,maxmin: false
            ,area: ['800px', '600px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                $("#uid").val(data.id);
                $("#lore_title").val(data.kbTitle);
                $("#lore_type").val(data.kbtNumber);
                CKEDITOR.instances.ckeditor.setData(data.kbContent);
                // $("#lore_title").attr("disabled",true);
                // $("#lore_type").attr("disabled",true);
                // CKEDITOR.on('instanceReady', function (ev) {
                //     var editor = ev.editor;
                //     editor.setReadOnly(true);
                // });
                setDisabled();
                //评分组件
                layui.use('rate', function(){
                    var rate = layui.rate;
                    //渲染
                    rate.render({
                        elem: $("#score"),
                        value: Number(data.kbScore),
                        readonly: true
                    });
                });
            }
        });
    }
  });
  exports('loreBase', {})
});

function setDisabled() {
    $("#lore_title").attr("disabled",true);
    $("#lore_type").attr("disabled",true);
    CKEDITOR.on('instanceReady', function (ev) {
        var editor = ev.editor;
        editor.setReadOnly(true);
    });
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}

function delDisabled() {
    $("#lore_title").removeAttr("disabled");
    $("#lore_type").removeAttr("disabled");
    $("#lore_title").val('');
    $("#lore_type").val('');
    CKEDITOR.on('instanceReady', function (ev) {
        var editor = ev.editor;
        editor.setReadOnly(false);
    });
    layui.use('rate', function(){
        var rate = layui.rate;
        //渲染
        rate.render({
            elem: $("#score")
        });
    });
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}
