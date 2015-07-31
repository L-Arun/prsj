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
	<style type="text/css">
		#main{
			width:80%;
		}
	</style>
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.metadata.js"></script>
	<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
	
  </head>
  <body>
  	
  	<div id="main">
 		<c:choose>
   			<c:when test="${newsMail.newsMailId == null}">
   			<div class="titlediv">您所在的位置：新闻管理->新闻管理->新闻报送邮箱添加</div>
   			</c:when>
   			<c:otherwise>
   			<div class="titlediv">您所在的位置：新闻管理->新闻管理->新闻报送邮箱修改</div>
   			</c:otherwise>
   		</c:choose>  	
    	<div id="content" class="margin_10">
    	<form id="theform" action="${ctx}/news/newsMail.do" method="post">
    		<input type="hidden" name="action" value="manage"/>
     		<input type="hidden" name="newsMail.newsMailId" value="${newsMail.newsMailId}"/>
    		<div id="form">
	    		<table cellpadding="0" cellspacing="0" border="0" style="width:50%" class="querytab">
		    		<c:if test="${newsMail.newsMailId != null}">
		    			<tr id="libtn"><td class="alignright"><span>新闻ID：</span></td><td class="alignleft">${newsMail.newsMailId}</td></tr>
		    		</c:if>
		    		<tr><td class="alignright" width="20%"><span class="red spanmargin">*</span><span>邮箱名：</span></td><td class="alignleft"><input type="text" name="newsMail.newsMailName" value="${newsMail.newsMailName}"  maxlength="255"/></td></tr>
		    		<tr><td class="alignright" width="20%"><span class="red spanmargin">*</span><span>邮箱地址：</span></td><td class="alignleft"><input type="text" name="newsMail.newsMailAddr" value="${newsMail.newsMailAddr}"  maxlength="255"/></td></tr>
		    		<tr>
		    			<td class="alignright">
		    				<span class="red spanmargin">*</span><span>是否默认发送：</span>
		    			</td>
		    			<td class="alignleft"><s:select name="isDefaultSendValue" list="yesNoStatuses" listKey="value" listValue="name"></s:select></td>
		    		</tr>
		    		<tr><td class="alignright"><span>备注：</span></td><td class="alignleft"><textarea name="news.memo" cols="60" rows="5">${news.memo}</textarea></td></tr>
	    		</table>
    		</div>
    		<div id="foot"><center><input id="sub" type="submit" value="提交 "/></center></div>
    		<div class="margin_10"><center><a href="${ctx}/news/newsMail.do" class="easyui-linkbutton" iconCls="icon-reload">返回列表</a></center></div>
 	    </form>
    	</div>
    </div>
  </body>
</html>
