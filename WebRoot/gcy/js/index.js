// JavaScript Document
var NEWS_URL_CONSTANT = "/prsj/gcy/news.html?newsId=";

var NEWS_SIZE_6 = 6;
var NEWS_SIZE_9 = 9;
var NEWS_SIZE_13 = 13;

var NEWS_TYPE_ALL = -1;
var NEWS_TYPE_ZHENG_CE = 1;
var NEWS_TYPE_DONG_TAI = 2;
var NEWS_TYPE_JIAN_SHE = 3;
var NEWS_TYPE_WEN_DA = 4;
var NEWS_TYPE_TONG_ZHI = 5;
var NEWS_TYPE_TUAN_DUI = 6;

//图片新闻
$.post("/prsj/hitechNews/pictureHitechNewsListForJson.do",
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
var url = "/prsj/hitechNews/firstPageHitechNewsListForJson.do";
//园区动态
$.post(url,
	{
		"listTypeValue" : NEWS_TYPE_DONG_TAI,
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
			$("#xinxi_yuanqudongtai").append(str);
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
//扶持政策
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
			$("#xinxi_zhengcefuchi").append(str);
		}
	},"json");
//入驻团队
$.post(url,
		{
			"listTypeValue" : NEWS_TYPE_TUAN_DUI,
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
				$("#xinxi_ruzhutuandui").append(str);
			}
		},"json");
//业务问答
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
				$("#xinxi_yewuwenda").append(str);
			}
		},"json");

