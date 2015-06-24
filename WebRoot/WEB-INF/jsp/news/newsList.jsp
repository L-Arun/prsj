<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户管理</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="keywords" content="" />
	<meta http-equiv="description" content="" />
	<link href="${ctx}/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/js/jscalendar/skins/aqua/theme.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/easyui/themes/icon.css"/>
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.metadata.js"></script>
	<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jscalendar/calendar.js"></script>
	<script type="text/javascript" src="${ctx}/js/jscalendar/lang/cn_utf8.js"></script>
	<script type="text/javascript" src="${ctx}/js/jscalendar/calendar-setup.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#sub').removeAttr('disabled');
			Calendar.setup(
				    {
				      inputField  : "beginCreateTime",    // ID of the input field
				      ifFormat    : "%Y-%m-%d",   // the date format
				      button      : "beginCreateTimeTrigger"      // ID of the button
				    }
				  );
			Calendar.setup(
				    {
				      inputField  : "endCreateTime",    // ID of the input field
				      ifFormat    : "%Y-%m-%d",   // the date format
				      button      : "endCreateTimeTrigger"      // ID of the button
				    }
				  );
			  
			$("tr.beover").mouseover(function() {
				$(this).addClass("over");
			}).mouseout(function() {
				$(this).removeClass("over");
			});
			
			$.validator.setDefaults({
				submitHandler: function(form) {
					$('#sub').attr('disabled','disabled');
					form.submit();
				},
				meta: "validate",
				ignoreTitle: true//解决与google Toolbar的冲突
			});
			$("#theform").validate({
				rules:{
					"number":{
							maxlength:64,
							minlength:1,
							rangelength:[1,64]
						},
					"department":{
							maxlength:64,
							minlength:1,
							rangelength:[1,64]
						}
				},
				success: function(label) {
					label.addClass("valid").html("<img src='${ctx}/images/ok.gif' border='0'/>")
				}
			});
		});
	</script>
  </head>
  <body>
  		<div>
  			<div class="titlediv">您所在的位置：新闻管理->新闻管理->新闻列表</div>
  			<div class="add_div_margin"><a href="${ctx}/news/news.do?action=input" class="easyui-linkbutton" iconCls="icon-add">添加新闻</a></div>
  			<div>
  				<div>
  					<div class="pagediv">条件查询</div>
  					<div>
	  					<form id="theform" action="${ctx}/archives/archives.do" method="post">
	  					<input type="hidden" value="query" name="action"/>
				    	<table cellpadding="0" cellspacing="0" border="0" style="width:80%" class="querytab">
				    		<tr>
				    			<td>文号:<input type="text" name="number" maxlength="24" value="${number}" /></td>
				    			<td>标题:<input type="text" name="title" maxlength="24" value="${title}" /></td>
				    			<td>发文部门:<input type="text" name="department" maxlength="24" value="${department}" /></td>
				    		</tr>
				    		<tr>
				    			<td>文件类型:
				    				<s:select id="archivesTypeValue" name="archivesTypeValue" list="archivesTypesQuery" listKey="value" listValue="name"></s:select>
				    			</td>
				    			<td colspan="2">收文时间:从<input type="text" id="beginCreateTime" name="beginCreateTime" size="10" value="<fmt:formatDate value='${beginCreateTime}' pattern='yyyy-MM-dd'/>"/><input type="button" id="beginCreateTimeTrigger" value="选择"/>
				    			至<input type="text" id="endCreateTime" name="endCreateTime" size="10" value="<fmt:formatDate value='${endCreateTime}' pattern='yyyy-MM-dd'/>"/><input type="button" id="endCreateTimeTrigger" value="选择"/></td>
				    		</tr>
				    		<tr>
				    			<td colspan="3"><input id="sub" type="submit" value="查询"/></td>
				    		</tr>
				    	</table>
				    	</form>
			    	</div>
  				</div>
  				<div>
  					<div class="pagediv">结果列表</div>
  					<div>
  					<table cellpadding="0" cellspacing="0" border="0" class="querytab">
			    		<tr class="font_bold">
			    			<td>序号</td>
			    			<td>编码</td>
			    			<td>标题</td>
			    			<td>类别</td>
			    			<td>创建时间</td>
			    			<td>创建人</td>
			    			<td>最后修改时间</td>
			    			<td>修改人</td>
			    			<td>状态</td>
			    			<td>操作</td>
			    		</tr>
			    		<s:iterator value="newses" status="index">
						<tr class="beover">
			    			<td>${index.count}</td>
			    			<td>${id}</td>
			    			<td><a href="${ctx}/news/news.do?action=view&news.id=${id}">${title}</a></td>
			    			<td></td>
			    			<td><s:date name="createTime" format="yyyy-MM-dd" /></td>
			    			<td>${username}</td>
			    			<td><s:date name="updateTime" format="yyyy-MM-dd" /></td>
			    			<td>${updateUsername}</td>
			    			<td>${isApply.name}</td>
			    			<td><a href="${ctx}/news/news.do?action=input&news.id=${id}">修改</a></td>
			    		</tr>
			    		</s:iterator>
			    	</table>
			    	</div>
			    	<div class="pagediv"><center>${pageString}</center></div>
  				</div>
  			</div>
  		</div>
  </body>
</html>
