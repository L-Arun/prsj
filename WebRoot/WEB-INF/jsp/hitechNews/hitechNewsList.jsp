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
  			<div class="titlediv">您所在的位置：高创园新闻管理->高创园新闻管理->高创园新闻列表</div>
  			<div class="add_div_margin"><a href="${ctx}/hitechNews/hitechNews.do?action=input" class="easyui-linkbutton" iconCls="icon-add">添加新闻</a></div>
  			<div>
  				<div>
  					<div class="pagediv">条件查询</div>
  					<div>
	  					<form id="theform" action="${ctx}/hitechNews/hitechNews.do" method="post">
	  					<input type="hidden" value="query" name="action"/>
				    	<table cellpadding="0" cellspacing="0" border="0" style="width:80%" class="querytab">
				    		<tr>
				    			<td>ID:<input type="text" name="newsId" maxlength="24" value="${newsId}" /></td>
				    			<td>标题:<input type="text" name="title" maxlength="24" value="${title}" /></td>
				    			<td>作者:<input type="text" name="username" maxlength="24" value="${username}" /></td>
				    		</tr>
				    		<tr>
				    			<td>新闻类型:
				    				<s:select id="hitechNewsTypeValue" name="hitechNewsTypeValue" list="hitechNewsTypesQuery" listKey="value" listValue="name"></s:select>
				    			</td>
				    			<td>审核状态:
				    				<s:select id="isApplyValue" name="isApplyValue" list="yesNoStatusQuery" listKey="value" listValue="name"></s:select>
				    			</td>
				    			<td>图片新闻:
				    				<s:select id="isImageNewsValue" name="isImageNewsValue" list="yesNoStatusQuery" listKey="value" listValue="name"></s:select>
				    			</td>
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
			    			<td>新闻ID</td>
			    			<td>标题</td>
			    			<td>类别</td>
			    			<td>创建时间</td>
			    			<td>创建人</td>
			    			<td>最后修改时间</td>
			    			<td>修改人</td>
			    			<td>图片新闻</td>
			    			<td>审核状态</td>
			    			<td>操作</td>
			    		</tr>
			    		<s:iterator value="hitechNewses" status="index">
						<tr class="beover">
			    			<td>${index.count}</td>
			    			<td>${newsId}</td>
			    			<td><a href="${ctx}/hitechNews/hitechNews.do?action=view&hitechNews.newsId=${newsId}">${title}</a></td>
			    			<td>${hitechNewsType.name}</td>
			    			<td><s:date name="createTime" format="yyyy-MM-dd" /></td>
			    			<td>${username}</td>
			    			<td><s:date name="updateTime" format="yyyy-MM-dd" /></td>
			    			<td>${updateUsername}</td>
			    			<td>${isImageNews.name}</td>
			    			<td>${isApply.name}</td>
			    			<td><a href="${ctx}/hitechNews/hitechNews.do?action=input&hitechNews.newsId=${newsId}">修改</a></td>
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
