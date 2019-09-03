/**

 @Name：layuiAdmin 工单系统
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：GPL-2
    
 */
layui.define(['table', 'form', 'element'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form
  ,element = layui.element;

  table.render({
    elem: '#LAY-app-system-order-jb'
    ,url: 'work_orders/page/jb' //模拟接口
    ,cols: [[
      {type: 'numbers'}
      ,{field: 'title', width: 100, title: '标题', sort: true}
      ,{field: 'level_id', width: 100, title: '工单等级',templet:function(data){
    	  return findName(level,data.level_id);
      }}
      ,{field: 'deal_user_id', width: 100, title: '受理人员',templet:function(data){
    	  return findName(users,data.deal_user_id);
      }}
      ,{field: 'deal_user_tel', minWidth: 200, title: '受理人员联系方式'}
      ,{field: 'project_name', width: 100, title: '项目名称', width: 100}
      ,{field: 'project_addr', width: 100, title: '地点'}
      ,{field: 'create_user_name', width: 100, title: '工单提交人员'}
      ,{field: 'info', width: 100, title: '备注信息'}
      ,{field: 'create_time', width: 100, title: '创建时间',templet:function(data){
    	  return tampletTime(data.create_time);
      }}
      ,{field: 'due_time', width: 100, title: '要求完成时间',templet:function(data){
    	  return tampletTime(data.due_time);
      }}
      ,{field: 'end_time', width: 100, title: '工单结束时间',templet:function(data){
    	  return tampletTime(data.end_time);
      }}
      ,{field: 'state', title: '工单状态', templet: '#buttonTpl', minWidth: 80, align: 'center'}
      ,{field: 'progress', title: '进度', width: 200, align: 'center', templet: '#progressTpl'}
      ,{title: '操作', align: 'center',width: 200, fixed: 'right', toolbar: '#table-system-order'}
    ]]
    ,page: true
    ,limit: 10
    ,height: 'full-220'
    ,limits: [10, 15, 20, 25, 30]
    ,text: '对不起，加载出现异常！'
    ,done: function(){
    	element.render('progress');
    	$("[data-field='create_user_name']").css('display','none');
    	$("[data-field='create_time']").css('display','none');
    	$("[data-field='create_time']").css('display','none');
    	$("[data-field='due_time']").css('display','none');
    	$("[data-field='end_time']").css('display','none');
          $.ajax({
              url: 'operatio/queryOpeationByPageId',
              type:"get",
              /* contentType:"application/json",*/
              data:{"pageid":"33"},
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
  
  
  //模糊搜索
  var $ = layui.$, active = {

			reload : function() {
				var project_name = $('#project_name').val();
				var title = $('#title').val();
				var state = $('#state').val();
				var project_addr = $('#project_addr').val();
				var deal_user_id = $('#deal_user_id').val();
				var level_id = $('#level_id').val();

				//执行重载
				table.reload('LAY-app-system-order-jb', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					where : {
						title:title
						,project_name: project_name
						,state:state
						,project_addr:project_addr
						,deal_user_id:deal_user_id
						,level_id:level_id
						
					}
				});
			}
		};

$('#btn-search').on('click', function() {
	var type = $(this).data('type');
	active[type] ? active[type].call(this) : '';
});
  

  //监听工具条
  table.on('tool(LAY-app-system-order-jb)', function(obj){
    var data = obj.data;
    if(obj.event === 'edit'){
      var tr = $(obj.tr);
      layer.open({
        type: 2
        ,title: '编辑工单'
        ,content: 'worklistform'
        ,area: ['450px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-app-workorder-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            table.reload('LAY-user-front-submit'); //数据刷新
            layer.close(index); //关闭弹层
          });  
          
          submit.trigger('click');
        }
        ,success: function(layero, index){
        	
        }
      });
    }else if(obj.event === 'detail'){
        layer.open({
          type: 2
          ,title: '查看'
          ,content: 'worklistform'
          ,area: ['800px', '600px']
          ,btn: ['确定', '取消']
          ,yes: function(index, layero){
        	  layer.close(index); //关闭弹层
          }
          ,success: function(layero, index){
        	  obj.data.create_time=tampletTime(obj.data.create_time);
        	  obj.data.due_time=tampletTime(obj.data.due_time);
        	  obj.data.end_time=tampletTime(obj.data.end_time);
        	  var iframeWin = layero.find('iframe')[0];
        	  var win = window[iframeWin['name']]; //得到iframe页的窗口对象;
              win.showDetailData(obj.data);
          }
        });
        
    }
  });

  exports('workorder', {})
});

function tampletTime(time){
	return time?time2date(time):'';
}
