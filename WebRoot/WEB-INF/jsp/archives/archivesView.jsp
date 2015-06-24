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
  </head>
  <body>	
  	<div id="main">
   		<div class="titlediv">您所在的位置：档案管理->收文管理->文件信息</div> 	
    	<div class="margin_10">
    		<div>
	    		<table cellpadding="0" cellspacing="0" border="0" style="width:50%" class="querytab">
	    			<tr>
		    			<td class="alignright_30">编码：</td><td class="alignleft">${archives.id}</td>
		    		</tr>
	    			<tr>
		    			<td class="alignright_30">文号：</td><td class="alignleft">${archives.number}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">标题：</td><td class="alignleft">${archives.title}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">发文部门：</td><td class="alignleft">${archives.department}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">创建时间：</td><td class="alignleft"><fmt:formatDate value="${archives.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">更新时间：</td><td class="alignleft"><fmt:formatDate value="${archives.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">文件类型：</td><td class="alignleft">${archives.archivesType.name}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">是否有电子版：</td><td class="alignleft">${archives.isDigital.name}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">纸质版保存位置：</td><td class="alignleft">${archives.path}</td>
		    		</tr>
		    		<tr>
		    			<td class="alignright_30">备注：</td><td class="alignleft">${archives.memo}</td>
		    		</tr>
	    		</table>
    		</div>
    		<div class="margin_10"><center><a href="${ctx}/archives/archives.do" class="easyui-linkbutton" iconCls="icon-reload">返回列表</a></center></div>
    	</div>
    </div>
  </body>
</html>
