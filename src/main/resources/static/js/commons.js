/**
 * 同步请求，有返回值
 * @param url
 * @param data
 * @returns
 */
function request(url,data){
	var res;
	var type = 'get';
	if(data){
		type='post';
	}else{
		data={};
	}
	$.ajax({
		url: url,
		data: data,
		type: type,
		async: false,
		success: function(v){
			res = v;
		}
	});
	return res;
}

/**
 * 查看页面
 * @param data
 */
function showDetail(data){
	  appendFiled(data);
	  disabledFiled(data);
	  layui.use('treeselect',
     function () {
         treeselect = layui.treeselect;
         treeselect.render();
     });
}


/**
 * 编辑页面
 * @param data
 */
function showEdit(data){
	  appendFiled(data);
	  replaseFiled(data)
	  layui.use('treeselect',
     function () {
         treeselect = layui.treeselect;
         treeselect.render();
     });
}


/**
 * 释放输入框
 * @param data
 */
function replaseFiled(data){
	var sel = $('select');
	var inp = $('input');
	var area = $('textarea');
	if(sel){
		$('select').removeAttr('readonly');
		$('select').removeAttr('disabled');
	}
	if(inp){
		$('input').removeAttr('disabled');
		$('input').removeAttr('readonly');
	}
	if(area){
		$('textarea').removeAttr('disabled');
		$('textarea').removeAttr('readonly');
	}
}


/**
 * 禁用输入框
 * @param data
 */
function disabledFiled(data){
	var sel = $('select');
	var inp = $('input');
	var area = $('textarea');
	if(sel){
		$('select').attr('readonly','readonly');
		$('select').attr('disabled','disabled');
	}
	if(inp){
		$('input').attr('disabled','disabled');
		$('input').attr('readonly','readonly');
	}
	if(area){
		$('textarea').attr('disabled','disabled');
		$('textarea').attr('readonly','readonly');
	}
	 
}
//重新刷新select下拉框
function reloadSelect(id,data,nullnode){
	$('#'+id+" option").remove();
	if(nullnode){
		$('#'+id).append('<option value="'+nullnode.value+'">'+nullnode.name+'</option>');
	}
	for(var i=0;i<data.length;i++){
		$('#'+id).append('<option value="'+data[i].value+'">'+data[i].name+'</option>');
	}
}

function  appendFiled(data,window){
		for(var key in data){
			var item = $('#'+key);
			if(item){
				item.val(data[key])
			}else{
					
			}
			
		}
	}