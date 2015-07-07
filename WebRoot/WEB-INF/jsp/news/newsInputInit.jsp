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
   			<c:when test="${news.id == null}">
   			<div class="titlediv">您所在的位置：新闻管理->新闻管理->新闻添加</div>
   			</c:when>
   			<c:otherwise>
   			<div class="titlediv">您所在的位置：新闻管理->新闻管理->新闻修改</div>
   			</c:otherwise>
   		</c:choose>  	
    	<div id="content" class="margin_10">
    	<form id="theform" action="${ctx}/news/news.do" method="post">
    		<input type="hidden" name="action" value="init"/>
    		<div id="form">
	    		<table cellpadding="0" cellspacing="0" border="0" style="width:50%" class="querytab">
		    		<tr><td class="alignright" ><span class="red spanmargin">*</span><span>标题：</span></td><td class="alignleft"><input type="text" name="news.title" value="${news.title}"  maxlength="255"/></td></tr>
		    		<tr><td class="alignright" ><span class="red spanmargin">*</span><span>浏览次数：</span></td><td class="alignleft"><input type="text" name="news.viewTimes" value="${news.viewTimes}"  maxlength="255"/></td></tr>
		    		<tr>
		    			<td class="alignright">
		    				<span class="red spanmargin">*</span><span>新闻类型：</span>
		    			</td>
		    			<td class="alignleft"><s:select name="newsTypeValue" list="newsTypes" listKey="value" listValue="name"></s:select></td>
		    		</tr>
		    		<tr>
		    			<td class="alignright">
		    				<span class="red spanmargin">*</span><span>发布时间：</span>
		    			</td>
		    			<td class="alignleft"><input type="text" name="news.memo" value="${news.memo}"  maxlength="255"/></td>
		    		</tr>
		    		<tr>
		    			<td class="alignright">
		    				<span class="red spanmargin">*</span><span>是否设为首页图片新闻：</span>
		    			</td>
		    			<td class="alignleft"><s:select name="isImageNewsValue" list="yesNoStatuses" listKey="value" listValue="name"></s:select></td>
		    		</tr>
		    		
		    		<tr><td colspan="2">
		    			<input id="imagePath" name="news.imagePath" value="${news.imagePath}" type="hidden"/>
	    				<textarea name="news.content" cols="100" rows="80"  id="myEditor">${news.content}</textarea>  
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
	    		</table>
    		</div>
    		<div id="foot"><center><input id="sub" type="button" value="提交 "/></center></div>
    		<div class="margin_10"><center><a href="${ctx}/news/news.do" class="easyui-linkbutton" iconCls="icon-reload">返回列表</a></center></div>
    	</form>
    	</div>
    </div>
  </body>
</html>
