<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>3D街景</title>
	<%@ include file="/construct/keyWord.html"%>
    <link href="/css/root.css" type="text/css" rel="stylesheet" />
    <link href="/css/main.css" type="text/css" rel="stylesheet" />
    <link href="/css/menu.css" type="text/css" rel="stylesheet" />
    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="/panorama/panoStudioViewerInRoot.js"></script>
    <style type="text/css">
		#main{
			text-align:center;
			//width:80%;
		}
		.image{
			//position:absolute;
			width:200px;
			z-index:10000;
		}
	</style>
    <script type="text/javascript">
		$(document).ready(function(){
    		var width = $("#main").width()
    		//$("#main").children("img").width(width);
    		$w = $('.image').width();
   			$h = $('.image').height();
   			$w2 = $w + 50;
   			$h2 = $h + 50;
   			$('.image').hover(function(){
   				 $(this).stop().animate({height:$h2,width:$w2},800);
   			},function(){
   				 $(this).stop().animate({height:$h,width:$w},800);
   			});
    	});
		//,left:"-10px",top:"-10px"
		//,left:"0px",top:"0px"
		function showPanorama(a){
			//$.get('/panorama/1.html',function(result){
				//var div = $("<div align='center' style='height: 100%; overflow:hidden;'><div id='panoStudioViewerID'></div></div>");
				//$("#main").append(div);
				//panoStudioViewer.embed("panoStudioViewerID","100%","100%","/panorama/3.xml","true","false");	
			//});
			var src = "/panorama/"+a+".html";  					
			if($("#panorama").length > 0){
				$("#panorama").attr("src",src); 
			}else{
				var iframe = $("<iframe id='panorama'"+src+"></>");						//构造iframe放街景
				iframe.attr("src",src);
				var width = $("#main").width();  							//获取宽高数据
				var height = $("#main").height();
				
				$("#thumb").css("float","left");     						//设置thumb属性
				$("#thumb").css("width",width*0.25);
				
				
				$(".image").css("width","100px");							//设置image,缩小图片大小 重新设置动画
				$(".image").css("height","100px");
				$w = $('.image').width();
	   			$h = $('.image').height();
	   			$w2 = $w + 5;
	   			$h2 = $h + 5;
	   			$(".image").animate({height:$h,width:$w},800);
				$('.image').hover(function(){
	  				 $(this).stop().animate({height:$h2,width:$w2},300);
	  			},function(){
	  				 $(this).stop().animate({height:$h,width:$w},300);
	  			});
				
				iframe.width(width*0.65);									//设置框架大小
				iframe.height(height);
				//$("#main").empty();
				$("#main").append(iframe);
			}
			
		}
	</script>
	
</head>
<body>
	<div id="root">
		<%@ include file="/construct/header.html"%>
		<div id="main" style="color:#FFF;">
        	<!-- 
        		<img alt="园区地图" src="/panorama/root/map.png"> 
       			<img alt="园区地图" src="/panorama/root/panorama-map.png"  style="top:170px; left:70px;position:absolute;z-index:10000;">
        	-->
        	<div id="thumb">
	       		<img src="panorama/root/1-1.png" alt=""  class="image" onclick="javascript:showPanorama('1')">
	       		<img src="panorama/root/2-1.png" alt=""  class="image" onclick="javascript:showPanorama('2')">
	       		<img src="panorama/root/3-1.png" alt=""  class="image" onclick="javascript:showPanorama('3')">
	       		<img src="panorama/root/7-1.png" alt=""  class="image" onclick="javascript:showPanorama('7')">
	       		<img src="panorama/root/8-1.png" alt=""  class="image" onclick="javascript:showPanorama('8')">
	       		<img src="panorama/root/9-1.png" alt=""  class="image" onclick="javascript:showPanorama('9')">
	       		<img src="panorama/root/10-1.png" alt=""  class="image" onclick="javascript:showPanorama('10')">
	       		<img src="panorama/root/117-1.png" alt=""  class="image" onclick="javascript:showPanorama('117')">
	       		<img src="panorama/root/118-1.png" alt=""  class="image" onclick="javascript:showPanorama('118')">
       		</div>
        </div>
		<div style="clear: both"></div>
		
<%@ include file="/construct/foot.html"%>
	</div>	 
</body>
</html>	  

