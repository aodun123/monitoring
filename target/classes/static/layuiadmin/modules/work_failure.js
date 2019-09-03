/**

 @Name：layuiAdmin 故障报修
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：GPL-2

 */
layui.define(['table', 'form', 'element'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , element = layui.element;

    table.render({
        elem: '#LAY-app-work_failure'
        , url: 'work_failure/pageAll' //模拟接口
        , cols: [[
            {type: 'numbers'}
            , {field: 'device_name', title: '设备名称', sort: true}
            , {field: 'id', title: '主键', sort: false}
            , {
                field: 'failure_time', title: '故障发生时间', templet: function (data) {
                    return data.failure_time ? time2date(data.failure_time) : '';
                }
            }
            , {field: 'reason', minWidth: 100, title: '故障信息'}
            , {field: 'failure_type_name', title: '故障类型'}
            , {field: 'found_user_name', title: '故障发现人'}
            , {field: 'state', title: '状态', templet: '#buttonTpl', align: 'center'}
            , {title: '操作', align: 'center', fixed: 'right', minWidth: 250, toolbar: '#table-work_failure'}
        ]]
        , page: true
        , limit: 10
        , height: 'full-260'
        , limits: [10, 15, 20, 25, 30]
        , text: '对不起，加载出现异常！'
        , done: function (data) {
            $("[data-field='id']").css('display', 'none');
            $("[data-field='failure_time']").css('display', 'none');
            $.ajax({
                url: 'operatio/queryOpeationByPageId',
                type: "get",
                /* contentType:"application/json",*/
                data: {"pageid": "40"},
                dataType: "json",
                success: function (data) {
                    var opeations = data['data'];
                    for (var i = 0; i < opeations.length; i++) {
                        var styleid = opeations[i].btnTitle;
                        $("a[name='" + styleid + "']").show();
                    }
                },
                error: function () {
                    layer.msg("按钮信息加载失败");
                }
            })
        }
    });


    var $ = layui.$, active = {

        reload: function () {
            var device_room_id = $('#device_room_id').val();
            var failure_type_id = $('#failure_type_id').val();
            var state = $('#state').val();

            //执行重载
            table.reload('LAY-app-work_failure', {
                page: {
                    curr: 1
                    //重新从第 1 页开始
                },
                where: {

                    device_room_id: device_room_id
                    , failure_type_id: failure_type_id
                    , state: state

                }
            });
        }
    };

    $('#btn-search').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //监听工具条
    table.on('tool(LAY-app-work_failure)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            var tr = $(obj.tr);
            layer.open({
                type: 2
                , title: '修改故障信息'
                , content: 'work_failure_form'
                , area: ['800px', '400px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-app-work_failure-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        var res = request('work_failure/upd', field);
                        if (res != '200') {
                            alert('发生异常，更改失败');
                        }
                        table.reload('LAY-app-work_failure'); //数据刷新
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {
                    obj.data.failure_time = obj.data.failure_time ? time2date(obj.data.failure_time) : '';
                    var iframeWin = layero.find('iframe')[0];
                    var win = window[iframeWin['name']]; //得到iframe页的窗口对象;
                    win.showEdit(obj.data);
                    win.hideState(obj.data);
                    win.reloadTree();
                }
            });
        } else if (obj.event === 'detail') {
            layer.open({
                type: 2
                , title: '查看报修保障'
                , content: 'work_failure_form'
                , area: ['800px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    layer.close(index); //关闭弹层
                }
                , success: function (layero, index) {
                    obj.data.failure_time = obj.data.failure_time ? time2date(obj.data.failure_time) : '';
                    var iframeWin = layero.find('iframe')[0];
                    var win = window[iframeWin['name']]; //得到iframe页的窗口对象;
                    win.showDetail(obj.data);
                    win.showState(obj.data);
                }
            });

        } else if (obj.event == 'addwork_orders') {

            layer.open({
                type: 2
                , title: '创建工单'
                , content: 'worklistform'
                , area: ['800px', '550px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-app-work_order_create-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        var res = request('/work_orders/submitWorkOrders', field);
                        if (res.optStatusCode == 10400) {
                            layer.close(index); //关闭弹层
                            table.reload('LAY-app-work_failure'); //数据刷新
                        } else {
                            alert('添加失败！');
                        }


                    });

                    submit.trigger('click');

                }
                , success: function (layero, index) {
                    obj.data.failure_time = obj.data.failure_time ? time2date(obj.data.failure_time) : '';
                    var iframeWin = layero.find('iframe')[0];
                    var win = window[iframeWin['name']]; //得到iframe页的窗口对象;
                    win.wzaddorders(obj.data);
                }
            });
        }
    });

    exports('workorder', {})
});

