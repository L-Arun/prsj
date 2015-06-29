// JavaScript Document
var NEWS_URL_CONSTANT = "/prsj/forhead/news.html?newsId=";

var NEWS_SIZE_6 = 6;
var NEWS_SIZE_9 = 9;
var NEWS_SIZE_13 = 13;

var NEWS_TYPE_ALL = -1;
var NEWS_TYPE_ZHENG_CE = 1;
var NEWS_TYPE_ZHENG_WU = 2;
var NEWS_TYPE_JIU_YE = 3;
var NEWS_TYPE_SHE_BAO = 4;
var NEWS_TYPE_REN_SHI = 5;
var NEWS_TYPE_ZHONG_CAI = 6;
var NEWS_TYPE_TONG_ZHI = 7;
var NEWS_TYPE_JI_GOU = 8;
var NEWS_TYPE_ZHAO_PIN = 9;
var NEWS_TYPE_WEN_DA = 10;

//图片新闻
$.post("/prsj/news/pictureNewsListForJson.do",
	{
		"newsSize" : NEWS_SIZE_9,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "";
			for (var i = 0; i < data.length; i = i + 1) {
				if ( i == 0) {
					str += "<div style='opacity: 0; z-index: 0;'><a href='" + NEWS_URL_CONSTANT + data[i].key + "' target='_blank'><img src='" + data[i].imagePath + "' title='" + data[i].name + "'></a></div>";
				} else {
					str += "<div class='smask'  style='opacity: 0; z-index: 0;'><a href='" + NEWS_URL_CONSTANT + data[i].key + "' target='_blank'><img src='" + data[i].imagePath + "' title='" + data[i].name + "'></a></div>";
				}
			}
			$("#myjQueryContent").append(str);
		}
	},"json");
var url = "/prsj/news/firstPageNewsListForJson.do";
//最新动态
$.post(url,
	{
		"listTypeValue" : NEWS_TYPE_ALL,
		"newsSize" : NEWS_SIZE_9,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "<ul>";
			for (var i = 0; i < data.length; i = i + 1) {
				str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
			}
			str = str + "</ul>";
			$("#xinxi_zuixindongtai").append(str);
		}
	},"json");
//通知公告
$.post(url,
	{
		"listTypeValue" : NEWS_TYPE_TONG_ZHI,
		"newsSize" : NEWS_SIZE_9,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "<ul>";
			for (var i = 0; i < data.length; i = i + 1) {
				str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
			}
			str = str + "</ul>";
			$("#xinxi_tongzhigonggao").append(str);
		}
	},"json");
//政务动态
$.post(url,
	{
		"listTypeValue" : NEWS_TYPE_ZHENG_WU,
		"newsSize" : NEWS_SIZE_6,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "<ul>";
			for (var i = 0; i < data.length; i = i + 1) {
    			str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
			}
			str = str + "</ul>";
			$("#xinxi_zhengwudongtai").append(str);
		}
	},"json");
//政策法规
$.post(url,
		{
			"listTypeValue" : NEWS_TYPE_ZHENG_CE,
			"newsSize" : NEWS_SIZE_6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
	    			str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_zhengcefagui").append(str);
			}
		},"json");
//人事人才
$.post(url,
		{
			"listTypeValue" : NEWS_TYPE_REN_SHI,
			"newsSize" : NEWS_SIZE_6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_renshirencai").append(str);
			}
		},"json");
//就业创业
$.post(url,
		{
			"listTypeValue" : NEWS_TYPE_JIU_YE,
			"newsSize" : NEWS_SIZE_6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_jiuyechuangye").append(str);
			}
		},"json");
//社会保障
$.post(url,
		{
			"listTypeValue" : NEWS_TYPE_SHE_BAO,
			"newsSize" : NEWS_SIZE_6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_shehuibaozhang").append(str);
			}
		},"json");
//仲裁监察
$.post(url,
		{
			"listTypeValue" : NEWS_TYPE_ZHONG_CAI,
			"newsSize" : NEWS_SIZE_6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_zhongcaijiancha").append(str);
			}
		},"json");
//机构设置
$.post(url,
		{
			"listTypeValue" : NEWS_TYPE_JI_GOU,
			"newsSize" : NEWS_SIZE_13,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_jigoushezhi").append(str);
			}
		},"json");
//政策问答
$.post(url,
		{
			"listTypeValue" : NEWS_TYPE_WEN_DA,
			"newsSize" : NEWS_SIZE_6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_zhengcewenda").append(str);
			}
		},"json");
//机构设置
$.post(url,
		{
			"listTypeValue" : NEWS_TYPE_ZHAO_PIN,
			"newsSize" : NEWS_SIZE_6,
		},
		function(object){
			if (object.code == 0) {
				var data = object.data;
				var str = "<ul>";
				for (var i = 0; i < data.length; i = i + 1) {
					str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
				}
				str = str + "</ul>";
				$("#xinxi_zhaopinxinxi").append(str);
			}
		},"json");
