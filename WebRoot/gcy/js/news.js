// JavaScript Document
var NEWS_URL_CONSTANT = "/prsj/gcy/news.html?newsId=";
var NEWS_SIZE_8 = 8;
var NEWS_TYPE_ALL = -1;
var NEWS_TYPE_DONG_TAI = 2;
var NEWS_TYPE_TONG_ZHI = 7;

//园区动态
$.post(url,
	{
		"listTypeValue" : NEWS_TYPE_DONG_TAI,
		"newsSize" : NEWS_SIZE_8,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "<ul>";
			for (var i = 0; i < data.length; i = i + 1) {
				str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
			}
			str = str + "</ul>";
			$("#yuanqudongtai").append(str);
		}
	},"json");
//通知公告
$.post(url,
	{
		"listTypeValue" : NEWS_TYPE_TONG_ZHI,
		"newsSize" : NEWS_SIZE_8,
	},
	function(object){
		if (object.code == 0) {
			var data = object.data;
			var str = "<ul>";
			for (var i = 0; i < data.length; i = i + 1) {
				str += "<li><a href='" + NEWS_URL_CONSTANT + data[i].key + "'>" + data[i].name + "</a></li>";
			}
			str = str + "</ul>";
			$("#tongzhigonggao").append(str);
		}
	},"json");

