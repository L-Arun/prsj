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
	<script type="text/javascript">
		var checkUrl = "${ctx}/user/user.do?action=check";
		$(function() {
			$('#sub').removeAttr('disabled');
			$("#chgpwd").toggle(
			  function () {
				    $(this).val("取消更改");
				    $("#lip").show();
				    $("#licp").show();
				    $("#password").removeClass("ignore");
				    $("#conPassword").removeClass("ignore");
				    $("#pwdflag").val("1");
				  },
			  function () {
			        $(this).val("更改密码");
			        $("#lip").hide();
				    $("#licp").hide();
				    $("#password").addClass("ignore");
				    $("#conPassword").addClass("ignore");
				    $("#pwdflag").val("0");
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
				},
				messages:{
				},
				success: function(label) {
					label.addClass("valid").html("<img src='${ctx}/images/ok.gif' border='0'/>")
				},
				ignore: ".ignore"
			});
		});	
	</script>
  </head>
  <body>
  	<div id="main">
 		<c:choose>
   			<c:when test="${archives.id == null}">
   			<div class="titlediv">您所在的位置：档案管理->收文管理->文件添加</div>
   			</c:when>
   			<c:otherwise>
   			<div class="titlediv">您所在的位置：档案管理->收文管理->文件修改</div>
   			</c:otherwise>
   		</c:choose>  	
    	<div id="content" class="margin_10">
    	<form id="theform" action="${ctx}/archives/archives.do" method="post">
    		<input type="hidden" name="action" value="manage"/>
    		<input type="hidden" name="archives.id" value="${archives.id}"/>
    		<div id="form">
	    		<table cellpadding="0" cellspacing="0" border="0" style="width:50%" class="querytab">
	    			<c:choose>
		    			<c:when test="${archives.id == null}">
		    			</c:when>
		    			<c:otherwise>
		    			<tr id="libtn"><td class="alignright"><span>编码：</span></td><td class="alignleft">${archives.id}</td></tr>
		    			</c:otherwise>
		    		</c:choose>	
		    		<tr><td class="alignright"><span class="red spanmargin">*</span><span>文号：</span></td><td class="alignleft"><input type="text" name="archives.number" value="${archives.number}"  maxlength="30"/></td></tr>
		    		<tr><td class="alignright"><span class="red spanmargin">*</span><span>标题：</span></td><td class="alignleft"><input type="text" name="archives.title" value="${archives.title}"  maxlength="255"/></td></tr>
		    		<tr><td class="alignright"><span class="red spanmargin">*</span><span>发文部门：</span></td><td class="alignleft"><input type="text" name="archives.department" value="${archives.department}"  maxlength="60"/></td></tr>
		    		<c:choose>
		    			<c:when test="${archives.id == null}">
		    			</c:when>
		    			<c:otherwise>
		    			<tr id="libtn"><td class="alignright"><span>收文时间：</span></td><td class="alignleft">${archives.createTime}</td></tr>
		    			</c:otherwise>
		    		</c:choose>
		    		<tr>
		    			<td class="alignright">
		    				<span class="red spanmargin">*</span><span>文件类型：</span>
		    			</td>
		    			<td class="alignleft"><s:select name="archivesTypeValue" list="archivesTypes" listKey="value" listValue="name"></s:select></td>
		    		</tr>
		    		<tr><td class="alignright"><span class="red spanmargin">*</span><span>保存路径：</span></td><td class="alignleft"><input type="text" name="archives.path" value="${archives.path}"  maxlength="30"/></td></tr>
		    		<tr>
		    			<td class="alignright">
		    				<span class="red spanmargin">*</span><span>是否有电子版：</span>
		    			</td>
		    			<td class="alignleft"><s:select name="isDigitalValue" list="yesNoStatuses" listKey="value" listValue="name"></s:select></td>
		    		</tr>
		    		<tr><td class="alignright"><span>备注：</span></td><td class="alignleft"><textarea name="archives.memo" cols="30" rows="5">${archives.memo}</textarea></td></tr>
	    		</table>
    		</div>
    		<div id="foot"><center><input id="sub" type="submit" value="提交 "/></center></div>
    		<div class="margin_10"><center><a href="${ctx}/archives/archives.do" class="easyui-linkbutton" iconCls="icon-reload">返回列表</a></center></div>
    	</form>
    	</div>
    </div>
  </body>
</html>
