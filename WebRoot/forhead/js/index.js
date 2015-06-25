// JavaScript Document
//图片新闻
$.post("/prsj/news/pictureNewsListForJson.do",
	{
		"newsSize" : 9,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "";
			for (var i = 0; i < data.length; i = i + 1) {
				if ( i == 0) {
					str += "<div style='opacity: 0; z-index: 0;'><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + " target='_blank'><img src='" + data[i].imagePath + "' title='" + data[i].name + "'></a></div>";
				} else {
					str += "<div class='smask'  style='opacity: 0; z-index: 0;'><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + " target='_blank'><img src='" + data[i].imagePath + "' title='" + data[i].name + "'></a></div>";
				}
			}
			$("#myjQueryContent").append(str);
		}
	},"json");
var url = "/prsj/news/firstPageNewsListForJson.do";
//最新动态
$.post(url,
	{
		"listTypeValue" : 0,
		"newsSize" : 9,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "<ul>";
			for (var i = 0; i < data.length; i = i + 1) {
				str += "<li><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + "'>" + data[i].name + "</a></li>";
			}
			str = str + "</ul>";
			$("#xinxi_zuixindongtai").append(str);
		}
	},"json");
//通知公告
$.post(url,
	{
		"listTypeValue" : 7,
		"newsSize" : 9,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "<ul>";
			for (var i = 0; i < data.length; i = i + 1) {
				str += "<li><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + "'>" + data[i].name + "</a></li>";
			}
			str = str + "</ul>";
			$("#xinxi_tongzhigonggao").append(str);
		}
	},"json");
//政务动态
$.post(url,
	{
		"listTypeValue" : 2,
		"newsSize" : 6,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "<ul>";
			for (var i = 0; i < data.length; i = i + 1) {
    			str += "<li><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + "'>" + data[i].name + "</a></li>";
			}
			str = str + "</ul>";
			$("#xinxi_zhengwudongtai").append(str);
		}
	},"json");
//政策法规
$.post(url,
		{
			"listTypeValue" : 1,
			"newsSize" : 6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
	    			str += "<li><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_zhengcefagui").append(str);
			}
		},"json");
//人事人才
$.post(url,
		{
			"listTypeValue" : 5,
			"newsSize" : 6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_renshirencai").append(str);
			}
		},"json");
//就业创业
$.post(url,
		{
			"listTypeValue" : 3,
			"newsSize" : 6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_jiuyechuangye").append(str);
			}
		},"json");
//社会保障
$.post(url,
		{
			"listTypeValue" : 4,
			"newsSize" : 6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_shehuibaozhang").append(str);
			}
		},"json");
//仲裁监察
$.post(url,
		{
			"listTypeValue" : 6,
			"newsSize" : 6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='/prsj/news/newsForJson.do?newsId=" + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_zhongcaijiancha").append(str);
			}
		},"json");
