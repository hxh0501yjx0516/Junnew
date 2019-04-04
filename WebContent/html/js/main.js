// JavaScript Document
//TAB切换
function changeTAB(id,len,obj,class1,class2){
	for(var i = 1;	 i <= len; i++){
		if(i == id){
			document.getElementById(obj+i).className = class1;
			} else {
			document.getElementById(obj+i).className = class2;
			}
		}
	}

function changeStyle(div){
	if ( div == 'a1' ){
		document.getElementById("a1").className = "s1";
		document.getElementById("a2").className = "s2";
		} else {
		document.getElementById("a1").className = "tab1";
		document.getElementById("a2").className = "tab2";
		}
	}
 function getRandCode(img){
 var imgSrc = $("#"+img);  
 var src = imgSrc.attr("src");  
 imgSrc.attr("src",chgUrl(src));  
}  
//时间戳  
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳  
function chgUrl(url){  
 var timestamp = (new Date()).valueOf();  
 if((url.indexOf("timestamp")>=0)){
  urlurl = url.substring(0,url.indexOf("?")) + "?timestamp=" + timestamp;  
 }else{  
  urlurl = url + "?timestamp=" + timestamp;  
 }  
 return urlurl;  
}
//左侧选项卡切换
function changeLeftTab(obj){
	var divs = document.getElementById("leftmain").getElementsByTagName("div");
	for(var i=0;i<divs.length;i++){
		if(divs[i].className == "button2"){
		divs[i].className = "button";
		break;
		}
	}
	if(typeof obj ==  "string"){
		document.getElementById(obj).className = "button2";
	}else{
		obj.className = "button2";
	}
}
//iframe自适应高度
function iFrameHeight(iframe,div,height) {
		iframe = iframe || "listFrame";
		div = div || "rightmain";
		var ifm= document.getElementById(iframe);
		var temp =  height || 500;
		ifm.height = temp +"px";
		var subWeb = document.frames ? document.frames[iframe].document : ifm.contentDocument;   
		if(ifm != null && subWeb != null) {
		   ifm.height = subWeb.body.scrollHeight>=temp? subWeb.body.scrollHeight+"px":temp+"px";
		}
		 document.getElementById(div).style.height= ifm.height ;
	} 

