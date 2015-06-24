// JavaScript Document
var url = "/prsj/news/newsListForJson.do";
$.post(url,
	{
		"listTypeValue" : 1,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "<ul>";
			for (var i = 0; i < data.length && i<6; i++) {
    			str += "<li><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + "'>" + data[i].name + "</a></li>";
			}
			str = str + "</ul>";
			$("#xinxi_zhengwudongtai").append(str);
			alert(data.toString());
			alert("aa");
		}
	},"json");
