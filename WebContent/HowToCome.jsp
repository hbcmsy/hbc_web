<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
	<title>乘车路线</title>
    <%@ include file="/construct/keyWord.html"%>
    <link href="/css/root.css" type="text/css" rel="stylesheet" />
    <link href="/css/menu.css" type="text/css" rel="stylesheet" />
    <link href="/css/tab.css" type="text/css" rel="stylesheet" />
    <link href="/css/column.css" type="text/css" rel="stylesheet" />
    <!--引用百度地图API-->
    <script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
    
    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <!--tab用 JavaScript-->
    <script src="/construct/tab.js"></script>
    <script src="/js/foot.js"></script>
	<script type="text/javascript" >
    	$(document).ready(function(e) {
			
			
        });
    </script>

</head>
<body>
	<div id="root">
		<%@ include file="/construct/header.html"%>
	  	<div id="main" style="color: #FFF;opacity:1;">
        <h1>乘车路线</h1>
        <p>可乘坐以下公交车到达河北村公交车站后,按照下图路线到达民俗园</p>
        <p>856路、915路、915路快、918路、918路快、923路、924路、945路、郊100路、顺15路、顺18路、顺19路、顺20路、顺23路、顺24路、顺28路、顺33路、顺39路、顺40路、顺41路、顺43路。</p>
		<!--<h1><strong style="color: #F00">注意!现在正门正在重新装修!请绕行正门东面的双河果园!</strong></h1>-->
		
        <!--百度地图容器-->
		<div style="width:100%;height:550px;border:#ccc solid 1px;" id="dituContent"></div>
        <script src="/js/baidumap.js"></script>
        <!--  -->
		<div style="clear: both"></div>
        </div>
        <!-- 地图位置二维码 -->
		<div id = "qr_map" style="position:absolute;z-index:10;left:0px;background:#FFF;text-align:center;">
        	<img id= "qr_map_img" alt="map_qr" src="/images/qrcode.map.baidu.png">
        	<h1 style="color:#000;margin:0;">扫我导航</h1>
        </div>
        <script type="text/javascript">
        	var main = $("#main");
        	var $top = main.height()/5*2;
        	var $width = parseInt(main.css("margin-left"));
        	$("#qr_map_img").width($width/7*5);
        	$("#qr_map").css("top",$top);
        </script>
        <!--  -->
		<%@ include file="/construct/foot.html"%>
	</div>
</body>

</html>
