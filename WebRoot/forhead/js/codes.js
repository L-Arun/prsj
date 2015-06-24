// JavaScript Document
function setTab(name,cursel,n){
	for(i=1;i<n;i++){
		var menu=document.getElementById(name+i);
		var con=document.getElementById("con_"+name+"_"+i);
		menu.className=(i==cursel?"hover":"");
		con.style.display=i==cursel?"block":"none";
	}
}
$(function(){
	$(".headline, .headtitle").css("display","none");
	$(".headline, .headtitle").fadeIn(3000);
	
	function rigScroll(){
		var winWidth=$(window).width();
		if(winWidth<1024){
			$(".weixin_QR_Code").css({"right":10})
		}else{      
			var winRig=(winWidth-980)/2-110;
			$(".weixin_QR_Code").css({"right":winRig})
		}
		$(".closeBtn").click(function(){
			$(".weixin_QR_Code").hide()
		})	
	}
	rigScroll()
	$(window).scroll(function() {
		rigScroll()
	});
 
	$(window).resize(function() {
		rigScroll()
	});
})