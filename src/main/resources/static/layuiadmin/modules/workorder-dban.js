/**

 @Name：layuiAdmin 待办工单
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
    elem: '#LAY-app-system-order-db'
    ,url: 'work_orders/page/db' 
    ,cols: [[
      {type: 'numbers'}
      ,{field: 'title', width: 100, title: '标题', sort: true}
      ,{field: 'deal_user_name', width: 100, title: '受理人员'}
      ,{field: 'deal_user_tel', width: 200, title: '受理人员联系方式'}
      ,{field: 'project_name', width: 100, title: '项目名称', width: 100}
      ,{field: 'project_addr', width: 100, title: '地点'}
      ,{field: 'create_user_name', width: 100, title: '工单提交人员'}
      ,{field: 'info', width: 100, title: '备注信息'}
      ,{field: 'level_name', width: 100, title: '工单等级'}
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
      ,{title: '操作', width: 200, align: 'center', fixed: 'right', toolbar: '#table-system-order'}
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
              data:{"pageid":"32"},
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
				table.reload('LAY-app-system-order-db', {
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
  table.on('tool(LAY-app-system-order-db)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
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
        
    }else if(obj.event === 'edit'){
    	var data = obj.data;
    	if(data.work_flow_type=='接单'){
    		jd(data,table);
    	}else if(data.work_flow_type=='维修完毕'){
    		done(data,table);
    	}else if(data.work_flow_type=='审核'){
    		sp(data,table);
    	}else if(data.work_flow_type=='签到'){
    		qd(data,table);
    	}
    }
  }); 

  exports('workorder', {})
});


/**
 * 接单
 * @param data
 * @param table
 */
function jd(data,table){
	var ret = request('work_orders/jd',{id:data.id});
	if(ret.optStatusCode==10400){
		table.reload('LAY-app-system-order-db')
	}else{
		alert("接单失败");
	}
}

/**
 * 签到
 * @param data
 * @param table
 */
function qd(data,table){
	var ret = request('work_orders/qd',{id:data.id});
	if(ret.optStatusCode==10400){
		table.reload('LAY-app-system-order-db')
	}else{
		alert("签到失败");
	}
}

/**
 * 维修完毕
 */
function done(data,table){
	layer.open({
        type: 2
        ,title: '备注'
        ,content: '/work_orders_mess'
        ,area: ['450px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
	       	 var iframeWindow = window['layui-layer-iframe'+ index]
	         ,submitID = 'LAY-app-work_orders_mess-submit'
	         ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
	         //监听提交
	         iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	           var field = data.field; //获取提交的字段
	           
	          	 var res = request('/work_orders/done',field);
	          	 if(res.optStatusCode==10400){
	          		table.reload('LAY-app-system-order-db'); //数据刷新
	                layer.close(index); //关闭弹层
	          	 }else{
	          		alert('对不起，发生异常！');
	          	 }
	           
	           
	         });  
	         
	         submit.trigger('click');
	    	 
	     }
        ,success: function(layero, index){
      	  var iframeWin = layero.find('iframe')[0];
      	  var win = window[iframeWin['name']]; //得到iframe页的窗口对象;
           win.hideSp(data.id);
        }
      });
}

/**
 * 
 * @param data
 * @param table
 * @param layero
 */
function sp(data,table){
	layer.open({
        type: 2
        ,title: '备注'
        ,content: '/work_orders_mess'
        ,area: ['450px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
	       	 var iframeWindow = window['layui-layer-iframe'+ index]
	         ,submitID = 'LAY-app-work_orders_mess-submit'
	         ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
	         //监听提交
	         iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	           var field = data.field; //获取提交的字段
	           
	          	 var res = request('/work_orders/sp',field);
	          	 if(res.optStatusCode==10400){
	          		table.reload('LAY-app-system-order-db'); //数据刷新
	                layer.close(index); //关闭弹层
	          	 }else{
	          		alert('对不起，发生异常！');
	          	 }
	           
	           
	         });  
	         
	         submit.trigger('click');
	    	 
	     }
        ,success: function(layero, index){
      	  var iframeWin = layero.find('iframe')[0];
      	  var win = window[iframeWin['name']]; //得到iframe页的窗口对象;
           win.showSp(data.id);
        }
      });
}

function tampletTime(time){
	return time?time2date(time):'';
}
