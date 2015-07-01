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
	<link rel="stylesheet" type="text/css" href="${ctx}/js/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/easyui/themes/icon.css"/>
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function apply(newsId) {
			$.post( "${ctx}/news/news.do",
				{
					"action": "apply",
					"news.newsId" : newsId,
				},
				function(object){
					if (object.code == 0) {
						alert("成功");
						$("#isApply").html("是");
					} else {
						alert(msg);
					}
				}, "json");
		}
	</script>
  </head>
  <body>	
  	<div id="main">
   		<div class="titlediv">您所在的位置：新闻管理->新闻管理->新闻预览</div> 	
    	<div class="margin_10">
    		<div>
	    		<table cellpadding="0" cellspacing="0" border="0" style="width:50%" class="querytab">
	    			<tr>
		    			<td class="alignright_30">编码：</td><td class="alignleft">${news.newsId}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">标题：</td><td class="alignleft">${news.title}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">作者：</td><td class="alignleft">${news.username}(<fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>)</td>
		    		</tr>
	    			<tr>
		    			<td class="alignright_30">最后修改：</td><td class="alignleft">${news.updateUsername}(<fmt:formatDate value="${news.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>)</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">分类：</td><td class="alignleft">${news.newsType.name}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">审核状态：</td><td class="alignleft" id="isApply">${news.isApply.name}
		    			<c:if test="${news.isApply.value == 0}">
		    				<input type="button" value="通过审核" onclick="apply(${news.newsId})"/>
		    			</c:if>
		    			
		    			</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">内容预览：</td><td class="alignleft">${news.content}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">备注：</td><td class="alignleft">${news.memo}</td>
		    		</tr>
	    		</table>
    		</div>
    		<div class="margin_10"><center><a href="${ctx}/news/news.do" class="easyui-linkbutton" iconCls="icon-reload">返回列表</a></center></div>
    	</div>
    </div>
  </body>
</html>
