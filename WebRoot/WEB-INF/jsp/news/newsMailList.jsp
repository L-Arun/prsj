<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新闻管理</title>
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
  </head>
  <body>
  		<div>
  			<div class="titlediv">您所在的位置：新闻管理->新闻管理->新闻报送邮箱列表</div>
  			<div class="add_div_margin"><a href="${ctx}/news/newsMail.do?action=input" class="easyui-linkbutton" iconCls="icon-add">添加邮箱</a></div>
  			<div>
  				<div>
  					<div>
  					<table cellpadding="0" cellspacing="0" border="0" class="querytab">
			    		<tr class="font_bold">
			    			<td>序号</td>
			    			<td>编码</td>
			    			<td>邮箱名</td>
			    			<td>邮箱地址</td>
			    			<td>默认发送</td>
			    			<td>备注</td>
			    			<td colspan="2">操作</td>
			    		</tr>
			    		<s:iterator value="newsMails" status="index">
						<tr class="beover">
			    			<td>${index.count}</td>
			    			<td>${newsMailId}</td>
			    			<td><a href="${ctx}/news/newsMail.do?action=view&newsMail.newsMailId=${newsMailId}">${newsMailName}</a></td>
			    			<td>${newsMailAddr}</td>
			    			<td>${isDefaultSend.name}</td>
			    			<td>${memo}</td>
			    			<td><a href="${ctx}/news/newsMail.do?action=input&newsMail.newsMailId=${newsMailId}">修改</a></td>
			    			<td><a href="${ctx}/news/newsMail.do?action=del&newsMail.newsMailId=${newsMailId}" onclick="return confirm('确实要删除吗？');">删除</a></td>
			    		</tr>
			    		</s:iterator>
			    	</table>
			    	</div>
  				</div>
  			</div>
  		</div>
  </body>
</html>
