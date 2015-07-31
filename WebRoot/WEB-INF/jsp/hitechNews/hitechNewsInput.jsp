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
	
   
    <!-- ueditor -->
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
	<!--end ueditor -->
	
	<script type="text/javascript">
		var checkUrl = "${ctx}/user/user.do?action=check";
		$(function() {
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
		    $('#sub').click(function(){
				var arr = [];
        		arr.push(UE.getEditor('myEditor').getContent());
		        var iPosC = 0; 
		        var iPosE = 0; 
		        var str = "";
		        iPosC = arr.toString().indexOf("<img"); 
		        if(iPosC != -1 ) {
		        	str = arr.toString().substr(iPosC); 
			        iPosE = str.indexOf("/>"); 
			        str = str.substr(0, iPosE + 2);  
			        
			        iPosC = str.indexOf('src="'); 
			        str = str.substr(iPosC + 5); 
			        iPosE = str.indexOf('"'); 
			        str = str.substr(0, iPosE);
			        document.getElementById("imagePath").value = str;
		        }
		        
		        
		        
		        $("#theform").submit();
				
			});
		});	
	</script>
  </head>
  <body>
  	
  	<div id="main">
 		<c:choose>
   			<c:when test="${hitechNews.newsId == null}">
   			<div class="titlediv">您所在的位置：新闻管理->新闻管理->新闻添加</div>
   			</c:when>
   			<c:otherwise>
   			<div class="titlediv">您所在的位置：新闻管理->新闻管理->新闻修改</div>
   			</c:otherwise>
   		</c:choose>  	
    	<div id="content" class="margin_10">
    	<form id="theform" action="${ctx}/hitechNews/hitechNews.do" method="post">
    		<input type="hidden" name="action" value="manage"/>
     		<input type="hidden" name="hitechNews.newsId" value="${hitechNews.newsId}"/>
     		<input type="hidden" name="hitechNews.username" value="${hitechNews.username}"/>
     		<input type="hidden" name="hitechNews.viewTimes" value="${hitechNews.viewTimes}"/>
    		<div id="form">
	    		<table cellpadding="0" cellspacing="0" border="0" style="width:50%" class="querytab">
		    		<c:if test="${hitechNews.newsId != null}">
		    			<tr id="libtn"><td class="alignright"><span>新闻ID：</span></td><td class="alignleft">${hitechNews.newsId}</td></tr>
		    		</c:if>
		    		<tr><td class="alignright" width="20%"><span class="red spanmargin">*</span><span>标题：</span></td><td class="alignleft"><input type="text" name="hitechNews.title" value="${hitechNews.title}"  maxlength="255"/></td></tr>
		    		<tr>
		    			<td class="alignright">
		    				<span class="red spanmargin">*</span><span>新闻类型：</span>
		    			</td>
		    			<td class="alignleft"><s:select name="hitechNewsTypeValue" list="hitechNewsTypes" listKey="value" listValue="name"></s:select></td>
		    		</tr>
		    		<c:if test="${hitechNews.newsId != null}">
 		    			<tr id="libtn"><td class="alignright"><span>新闻创建：</span></td><td class="alignleft">${hitechNews.username}(${hitechNews.createTime})</td></tr>
  		    		</c:if>
 		    		<c:if test="${hitechNews.newsId != null}">
 		    			<tr id="libtn"><td class="alignright"><span>最后修改：</span></td><td class="alignleft">${hitechNews.updateUsername}(${hitechNews.updateTime})</td></tr>
 		    		</c:if>
		    		<tr>
		    			<td class="alignright">
		    				<span class="red spanmargin">*</span><span>是否设为首页图片新闻：</span>
		    			</td>
		    			<td class="alignleft"><s:select name="isImageNewsValue" list="yesNoStatuses" listKey="value" listValue="name"></s:select></td>
		    		</tr>
		    		<c:if test="${hitechNews.newsId != null}">
 			    		<tr><td class="alignright"><span>审核状态：</span></td><td class="alignleft">${hitechNews.isApply.name}</td></tr>
 			    		<tr><td class="alignright"><span>浏览次数：</span></td><td class="alignleft">${hitechNews.viewTimes}</td></tr>
 		    		</c:if>
		    		<tr><td colspan="2">
		    			<input id="imagePath" name="hitechNews.imagePath" value="${hitechNews.imagePath}" type="hidden"/>
	    				<textarea name="hitechNews.content"  id="myEditor">${hitechNews.content}</textarea>  
						<script type="text/javascript"> 
						    var editor = new UE.ui.Editor({
						    	initialFrameWidth:1300, 
						    	initialFrameHeight:1000 
						    }); 
						    
						    editor.render("myEditor"); 
						    //1.2.4以后可以使用一下代码实例化编辑器 
						    //UE.getEditor(’myEditor’) 
						</script> 
	    			</td></tr>
		    		<tr><td class="alignright"><span>备注：</span></td><td class="alignleft"><textarea name="hitechNews.memo" cols="60" rows="5">${hitechNews.memo}</textarea></td></tr>
	    		</table>
    		</div>
    		<div id="foot"><center><input id="sub" type="button" value="提交 "/></center></div>
    		<div class="margin_10"><center><a href="${ctx}/hitechNews/hitechNews.do" class="easyui-linkbutton" iconCls="icon-reload">返回列表</a></center></div>
 	    </form>
    	</div>
    </div>
  </body>
</html>
