/**

 @Name：layuiAdmin 用户管理 管理员管理 角色管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */
/*
 * 人员管理页
 */
layui.define(['table', 'form'], function(exports) {
    var $ = layui.$,
        table = layui.table,
        form = layui.form;
    //用户管理表格(实际数据接口：'sysuser/queryAllUser')
    table.render({
        elem: '#LAY-contracttype-manage'
        ,url:'contract/typelist'
        ,cols: [[
            {type: 'checkbox'}
            ,{field: 'id', width: 80, title: 'ID', align:'center'}
            ,{field: 'contracttype_name', title: '合同类别名称', align:'center'}
            ,{field: 'state', title: '是否可用', templet: '#stateTpl', align:'center'}
            ,{title: '操作', width: 220, align:'center', fixed: 'right', toolbar: '#table-useradmin-user'}
        ]]
        ,page: true
        ,limit: 10
        ,height: 'full-280'
        ,text: '对不起，加载出现异常！'
        ,done:function () {
            $.ajax({
                url: 'operatio/queryOpeationByPageId',
                type:"get",
                data:{"pageid":"50"},
                dataType: "json",
                success: function (data) {
                    var opeations = data['data'];
                    //表头按钮
                    var buttonString='';
                    //表格排中的按钮
                    var tableButtonString='';
                    var controlType='';
                    for(var i=0;i<opeations.length;i++){
                        //根据按钮名称进行事件绑定判断
                        if(opeations[i].btnName=='编辑'){
                            tableButtonString+='<a class="tableButton1" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
                        }else if(opeations[i].btnName=='删除'){
                            tableButtonString+='<a class="tableButton1" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>';
                        }else if(opeations[i].btnName=='新增类别'){
                            controlType='add';
                            buttonString+='<button buttonId="'+opeations[i].btnId+'" class="layui-btn layui-btn-sm layuiadmin-btn-useradmin" data-type="'+controlType+'">'+opeations[i].btnName+'</button>';
                        }else if(opeations[i].btnName=='批量删除'){
                            controlType='batchdel';
                            buttonString+='<button buttonId="'+opeations[i].btnId+'" class="layui-btn layui-btn-sm layuiadmin-btn-useradmin" data-type="'+controlType+'">'+opeations[i].btnName+'</button>';
                        }else if(opeations[i].btnName=='重置密码'){
                            controlType='resetPassword';
                            buttonString+='<button buttonId="'+opeations[i].btnId+'" class="layui-btn layui-btn-sm layuiadmin-btn-useradmin" data-type="'+controlType+'">'+opeations[i].btnName+'</button>';
                        }else if(opeations[i].btnName=='设置角色'){
                            controlType='setRole';
                            buttonString+='<button buttonId="'+opeations[i].btnId+'" class="layui-btn layui-btn-sm layuiadmin-btn-useradmin" data-type="'+controlType+'">'+opeations[i].btnName+'</button>';
                        }
                    }
                    $('.conButtonWraper').append(tableButtonString);
                    $('.buttonsWraper').append(buttonString);
                },
                error: function () {
                    layer.msg("按钮信息加载失败");
                }
            })

        }

    });
    //用户管理监听工具条
    table.on('tool(LAY-contracttype-manage)', function(obj) {
        console.log(obj);
        var data = obj.data;
        if(obj.event === 'del') {
            layer.confirm('您确定真的删除么？', function(index) {
                layer.close(index);
                $.ajax({
                    url: 'sysuser/delUser/' + data.id,
                    type: "delete",
                    dataType: "json",
                    success: function(data) {
                        var status = data.optStatusCode;
                        if(status == 10400) {
                            //执行删除操作
                            obj.del();
                            layer.msg("删除成功", {
                                icon: 1
                            });
                        } else {
                            layer.msg("删除数据失败");
                        }
                    },
                    error: function() {
                        layer.msg("删除数据失败");
                    }
                });
            });
        } else if(obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '编辑用户',
                content: 'userform',
                maxmin: false,
                area:  ['800px', '530px'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index],
                        submitID = 'LAY-user-submit',
                        submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function(data) {
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        var jsonstr = {
                            "id": field.uid,
                            "username": field.username,
                            "password": field.password,
                            "realname": field.realname,
                            "usernumber": field.usernumber,
                            "sex": field.sex,
                            "cardid": field.cardid,
                            "phone": field.phone,
                            "email": field.email,
                            "status": field.status,
                            "image": field.image,
                            "adress": field.adress,
                            "descption": field.descption
                        };
                        $.ajax({
                            url: 'sysuser/upUser',
                            type: "put",
                            contentType: "application/json", //设置请求参数类型为json字符串
                            data: JSON.stringify(jsonstr), //将json对象转换成json字符串发送
                            dataType: "json",
                            success: function(data) {
                                var status = data.optStatusCode;
                                if(status == 10400) {
                                    layer.close(index); //关闭弹层
                                    layer.msg("编辑成功", {
                                        icon: 1
                                    });
                                    $(".layui-laypage-btn").click(); //数据刷新
                                } else {
                                    layer.msg("编辑数据失败");
                                }
                            },
                            error: function() {
                                layer.msg("编辑数据失败");
                            }
                        });
                    });
                    submit.trigger('click');
                },
                success: function(layero, index) {
                    //为输入框赋值
                    var body = layer.getChildFrame('body', index);
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
        } else if(obj.event === 'detail') {
            layer.open({
                type: 2,
                title: '用户详情',
                content: 'user_view',
                maxmin: false,
                area:['800px', '530px'],
                btn: ['关闭'],
                success: function(layero, index) {
                    //为查看页赋值
                    var sexArry=['女','男'];
                    var statusArry=['注销','正常'];
                    data.sex=sexArry[data.sex];
                    data.status=statusArry[data.status];
                    var body = layer.getChildFrame('body', index);
                    body.contents().find("#uid").text(data.id);
                    body.contents().find("#username").text(data.username);
                    body.contents().find("#password").text(data.password);
                    body.contents().find("#realname").text(data.realname);
                    body.contents().find("#usernumber").text(data.usernumber);
                    body.contents().find("#sex").text(data.sex);
                    body.contents().find("#cardid").text(data.cardid);
                    body.contents().find("#phone").text(data.phone);
                    body.contents().find("#email").text(data.email);
                    body.contents().find("#status").text(data.status);
                    body.contents().find("#image").text(data.image);
                    body.contents().find("#adress").text(data.adress);
                    body.contents().find("#descption").text(data.descption);
                }
            });
        }
    });
    /**
     * 信息列表页
     */
    table.render({
        elem: '#LAY-message-list',
        url: layui.setter.base + 'json/useradmin/webuser.js' //数据接口
        ,
        cols: [
            [{
                type: 'checkbox',
                fixed: 'left'
            }, {
                field: 'id',
                width: 460,
                title: '标题',
                sort: true
            }, {
                field: 'username',
                title: '接收时间',
                width: 250
            }
                //渲染模板类型
                , {
                field: 'avatar',
                title: '消息类型',
                width: 250,
                templet: '#imgTpl'
            }, {
                field: 'phone',
                title: '消息状态',
                width: 250,
            }
                //操作工具类型
                , {
                title: '操作',
                width: 350,
                align: 'center',
                fixed: 'right',
                toolbar: '#table-message-list'
            }
            ]
        ],
        page: true,
        limit: 10,
        height: 'full-30',
        text: '对不起，加载出现异常！'
    });

    //信息监听，监听操作按钮
    table.on('tool(LAY-message-list)', function(obj) {
        //table绑定的数据对象
        var data = obj.data;
        if(obj.event === 'del') {
            layer.prompt({
                formType: 1,
                title: '敏感操作，请验证口令'
            }, function(value, index) {
                layer.close(index);
                //确认框
                layer.confirm('真的删除行么', function(index) {

                    obj.del();
                    layer.close(index);
                });
            });
        } else if(obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2,
                title: '编辑用户',
                content: '../../../views/user/user/contractadd.html',
                maxmin: true,
                area: ['500px', '450px'],
                btn: ['确定', '取消'],
                yes: function(index, layero) {
                    //提交编辑内容
                    //			$.ajax({
                    //				type: "",
                    //	            url: "",
                    //	            data: {},
                    //	            dataType: "json",
                    //	            success: function(data){}
                    //			});
                    //在回调中关闭弹窗,传入的index是必须的参数，其余数据无关是layer的本身机制，打印出来其是递增的一个数
                    //回调函数从监听的table函数的obj中获取
                    layer.close(index);
                },
                //layer成功弹出时调用的钩子函数
                success: function(layero, index) {
                    //第一个参数是一个类文档对象，第二个参数是自增数列
                }
            });
        }
    });

    exports('contracttype', {})
});