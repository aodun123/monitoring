/**

 @Name： 设备管理
 @Author：liumei
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //设备管理
  table.render({
    elem: '#LAY-device-manage'
    ,url: 'device/getAllDevice'
    ,cols: [[
      {type: 'checkbox'}
      ,{field: 'id', title: 'ID', width: 100, align:'center'}
      ,{field: 'mrId', title: '所在机房', align:'center', templet: '#mrIdTpl'}
      //,{field: 'mrcId', title: '所在机柜',  align:'center', templet: '#mrcIdTpl'}
      ,{field: 'deviceName', title: '设备名称', align:'center'}
      // ,{field: 'deviceNumber', title: '设备编号',  align:'center'}
       ,{field: 'deviceIp', title: '设备IP', align:'center'}
      // ,{field: 'deviceIp2', title: '设备管理口IP',  align:'center'}
      ,{field: 'deviceType', title: '设备类型', templet: '#deviceTypeTpl', align:'center'}
       ,{field: 'deviceSystemType', title: '操作系统',  templet: '#deviceSystemTypeTpl',  align:'center'}
      // ,{field: 'deviceUsername', title: '设备登录用户名', align:'center'}
      // ,{field: 'devicePassword', title: '设备登录密码',  align:'center'}
      // ,{field: 'devicePort', title: '设备使用端口', align:'center'}
      ,{field: 'deviceStatus', title: '设备状态', templet: '#deviceStatusTpl',  align:'center'}
      // ,{field: 'deviceOnlineStatus', title: '设备在线状态', templet: '#deviceOnlineStatusTpl', align:'center'}
      // ,{field: 'deviceWarnStatus', title: '设备报警状态', templet: '#deviceWarnStatusTpl',  align:'center'}
      //,{field: 'deviceHead', title: '设备负责人',  align:'center'}
      //,{field: 'deviceHeadPhone', title: '设备负责人联系方式',  align:'center'}
      // ,{field: 'macAddress', title: '设备MAC地址',  align:'center'}
      ,{title: '操作', width: 420, align:'center', toolbar: '#table-useradmin-user'}
     ]]
    ,page: true
    ,limit: 10
    ,height: 'full-300'
    ,text: '对不起，加载出现异常！'
      ,done:function () {
          $.ajax({
              url: 'operatio/queryOpeationByPageId',
              type:"get",
              /* contentType:"application/json",*/
              data:{"pageid":"22"},
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
  table.on('tool(LAY-device-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('您确定真的删除么？', function(index){
            obj.del();
            layer.close(index);
            $.ajax({
                url: 'device/delDevice?id='+data.id,
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
        ,title: '编辑设备'
        ,content: 'deviceform'
        ,maxmin: false
        ,area: ['1000px', '500px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-device-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            var jsonstr = {
                    "id":field.id,
                    "mrId":field.mrId,
                    "mrcId":field.mrcId,
                    "deviceName":field.deviceName,
                    "deviceNumber":field.deviceNumber,
                    "deviceIp":field.deviceIp,
                    "deviceIp2":field.deviceIp2,
                    "deviceType":field.deviceType,
                    "deviceSystemType":field.deviceSystemType,
                    "deviceBrand":field.deviceBrand,
                    "deviceModel":field.deviceModel,
                    "deviceUsername":field.deviceUsername,
                    "devicePassword":field.devicePassword,
                    "devicePort":field.devicePort,
                    "deviceUse":field.deviceUse,
                    "deviceBuyDate":field.deviceBuyDate,
                    "deviceDesc":field.deviceDesc,
                    "deviceStatus":field.deviceStatus,
                    "deviceOnlineStatus":field.deviceOnlineStatus,
                    "deviceWarnStatus":field.deviceWarnStatus,
                    "deviceHead":field.deviceHead,
                    "deviceHeadPhone":field.deviceHeadPhone,
                    "macAddress":field.macAddress,
                    "orgAgencyId": field.orgAgencyId
              };
            $.ajax({
                  url: 'device/updDevice/',
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
            // body.contents().find("#mrId").val(data.mrId);
            // body.contents().find("#mrcId").val(data.mrcId);
            body.contents().find("#deviceName").val(data.deviceName);
            body.contents().find("#deviceNumber").val(data.deviceNumber);
            body.contents().find("#deviceIp").val(data.deviceIp);
            body.contents().find("#deviceIp2").val(data.deviceIp2);
            body.contents().find("#deviceType").val(data.deviceType);
            body.contents().find("#deviceSystemType").val(data.deviceSystemType);
            body.contents().find("#deviceBrand").val(data.deviceBrand);
            body.contents().find("#deviceModel").val(data.deviceModel);
            body.contents().find("#deviceUsername").val(data.deviceUsername);
            body.contents().find("#devicePassword").val(data.devicePassword);
            body.contents().find("#devicePort").val(data.devicePort);
            body.contents().find("#deviceUse").val(data.deviceUse);
            body.contents().find("#deviceBuyDate").val(data.deviceBuyDate);
            body.contents().find("#deviceDesc").val(data.deviceDesc);
            body.contents().find("#deviceStatus").val(data.deviceStatus);
            body.contents().find("#deviceOnlineStatus").val(data.deviceOnlineStatus);
            body.contents().find("#deviceWarnStatus").val(data.deviceWarnStatus);
            body.contents().find("#deviceHead").val(data.deviceHead);
            body.contents().find("#deviceHeadPhone").val(data.deviceHeadPhone);
            body.contents().find("#macAddress").val(data.macAddress);
            //body.contents().find("#orgAgencyId").val(data.orgAgencyId);
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
            //调用弹出界面的方法
            iframeWin.initEditSelect1(data.mrId);
            iframeWin.initEditSelect2(data.mrcId);
            iframeWin.initEditSelect3(data.orgAgencyId);
          }
      });
    } else if(obj.event === 'addassets'){
        layer.open({
            type: 2
            ,title: '新增资源'
            ,content: 'deviceassetsform'
            ,maxmin: false
            ,area: ['500px', '400px']
            ,btn: ['确定', '取消']
            ,yes: function(index, layero){
                var iframeWindow = window['layui-layer-iframe'+ index]
                    ,submitID = 'LAY-assets-submit'
                    ,submit = layero.find('iframe').contents().find('#'+ submitID);

                //监听提交
                iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                    var field = data.field; //获取提交的字段
                    //提交 Ajax 成功后，静态更新表格中的数据
                    var jsonstr = {
                        "deviceId":field.deviceId,
                        "deviceName":field.deviceName,
                        "assetsStatus":field.assetsStatus,
                        "deviceTime":field.deviceTime
                    };
                    $.ajax({
                        url: 'assets/addAssets/',
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
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#id").val(data.id);
                // body.contents().find("#deviceId").val(data.deviceId);
                body.contents().find("#deviceName").val(data.deviceName);
                body.contents().find("#assetsStatus").val(data.assetsStatus);
                body.contents().find("#deviceTime").val(data.deviceTime);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
                //调用弹出界面的方法
                iframeWin.initEditSelect(data.deviceId);
            }
        });
    }else if(obj.event === 'assetsdetail'){
        layer.open({
            type: 2
            ,title: '查看设备资源详情'
            ,content: 'assetstimes?deviceId='+data.id
            ,maxmin: false
            ,area: ['600px', '500px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#id").val(data.id);
            }
        });
    }else if(obj.event === 'lookDetail'){
        layer.open({
            type: 2
            ,title: '查看设备实时信息'
            ,content: 'realDeviceData?deviceType='+data.deviceType+"&deviceSystemType="+data.deviceSystemType+"&deviceIp="+data.deviceIp+"&mrId="+data.mrId
            ,maxmin: false
            ,area: ['95%', '95%']
            ,success: function (layero, index) {
                //var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                //body.contents().find("#id").val(data.id);
            }
        });
    }else if(obj.event === 'detail'){
        layer.open({
            type: 2
            ,title: '查看设备详情'
            ,content: 'deviceform'
            ,maxmin: false
            ,area: ['1000px', '500px']
            ,btn: ['取消']
            ,success: function (layero, index) {
                var body = layer.getChildFrame('body', index);  //巧妙的地方在这里哦
                body.contents().find("#id").val(data.id);
                // body.contents().find("#mrId").val(data.mrId);
                // body.contents().find("#mrcId").val(data.mrcId);
                body.contents().find("#deviceName").val(data.deviceName);
                body.contents().find("#deviceNumber").val(data.deviceNumber);
                body.contents().find("#deviceIp").val(data.deviceIp);
                body.contents().find("#deviceIp2").val(data.deviceIp2);
                body.contents().find("#deviceType").val(data.deviceType);
                body.contents().find("#deviceSystemType").val(data.deviceSystemType);
                body.contents().find("#deviceBrand").val(data.deviceBrand);
                body.contents().find("#deviceModel").val(data.deviceModel);
                body.contents().find("#deviceUsername").val(data.deviceUsername);
                body.contents().find("#devicePassword").val(data.devicePassword);
                body.contents().find("#devicePort").val(data.devicePort);
                body.contents().find("#deviceUse").val(data.deviceUse);
                body.contents().find("#deviceBuyDate").val(data.deviceBuyDate);
                body.contents().find("#deviceDesc").val(data.deviceDesc);
                body.contents().find("#deviceStatus").val(data.deviceStatus);
                body.contents().find("#deviceOnlineStatus").val(data.deviceOnlineStatus);
                body.contents().find("#deviceWarnStatus").val(data.deviceWarnStatus);
                body.contents().find("#deviceHead").val(data.deviceHead);
                body.contents().find("#deviceHeadPhone").val(data.deviceHeadPhone);
                body.contents().find("#macAddress").val(data.macAddress);
                //body.contents().find("#orgAgencyId").val(data.orgAgencyId);
                body.contents().find("#mrId").attr("disabled", true);
                body.contents().find("#mrcId").attr("disabled", true);
                body.contents().find("#deviceName").attr("disabled", true);
                body.contents().find("#deviceNumber").attr("disabled", true);
                body.contents().find("#deviceIp").attr("disabled", true);
                body.contents().find("#deviceIp2").attr("disabled", true);
                body.contents().find("#deviceType").attr("disabled", true);
                body.contents().find("#deviceSystemType").attr("disabled", true);
                body.contents().find("#deviceBrand").attr("disabled", true);
                body.contents().find("#deviceModel").attr("disabled", true);
                body.contents().find("#deviceUsername").attr("disabled", true);
                body.contents().find("#devicePassword").attr("disabled", true);
                body.contents().find("#devicePort").attr("disabled", true);
                body.contents().find("#deviceUse").attr("disabled", true);
                body.contents().find("#deviceBuyDate").attr("disabled", true);
                body.contents().find("#deviceDesc").attr("disabled", true);
                body.contents().find("#deviceOnlineStatus").attr("disabled", true);
                body.contents().find("#deviceWarnStatus").attr("disabled", true);
                body.contents().find("#deviceHead").attr("disabled", true);
                body.contents().find("#deviceHeadPhone").attr("disabled", true);
                body.contents().find("#macAddress").attr("disabled", true);
                body.contents().find("#orgAgencyId").attr("disabled", true);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象;
                //调用弹出界面的方法
                iframeWin.initEditSelect1(data.mrId);
                iframeWin.initEditSelect2(data.mrcId);
                iframeWin.initEditSelect3(data.orgAgencyId);
                //调整查看时的input内容
                var inputDom=body.contents().find("input");
                for(var i=0;i<inputDom.length;i++){
                    var InputItem=inputDom[i];
                    var valueInner=InputItem.value;
                    console.log(valueInner);
                    if(!valueInner){
                        InputItem.value='未填加';
                    }
                }
            }
        });
    }
  });
    function statusTransfor(status){
        if(status==="0"){
            return "<span style=\"color: #007DDB;\">购买</span>";
        }else if(status==="1"){
            return "入库";
        }else if(status==="2"){
            return "领用";
        }else if(status==="3"){
            return "转移调拨";
        }else if(status==="4"){
            return "维修";
        } else {
            return "报废";
        }
    }
    function time2date(time){
        var date = new Date(time);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    }
  exports('device', {})
});