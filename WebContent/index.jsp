<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>北京顺义河北村民俗园</title>
<%@ include file="/construct/keyWord.html"%>
<link href="/css/root.css" type="text/css" rel="stylesheet" />
<link href="/css/main.css" type="text/css" rel="stylesheet" />
<link href="/css/menu.css" type="text/css" rel="stylesheet" />
<link href="/css/tab.css" type="text/css" rel="stylesheet" />
<link href="/css/preview.css" type="text/css" rel="stylesheet" />

<!--tab用 JavaScript-->
<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="/js/imagesloaded.pkgd.min.js"></script>
<script src="/construct/tab.js"></script>
<script src="/js/foot.js"></script>
<script src="js/spacegallery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#gallery").spacegallery({
			loadingClass : "loading",
			perspective : 100
		});
		var v = $("#main").css("margin-right");
		var b = $("#main").css("border-top-width");
		b = parseInt(b);
        v=parseInt(v)+63;
		$("#sub_col").css("right",v-b).css("width","1000px");		
	});
	var Json;
	var iterator = 0;
	$.getJSON("/action/GalleryAction!showGalleries.action",function(json){ 
		//对返回JSON里面的list数据遍历 并添加图片 加载后删除
		Json = JSON.parse(json);
		console.log(Json);
		console.log(Json[iterator]);
		console.log(Json[iterator].gallery_href);
		//iterator = json.length;
		addGallery();
    });
	function addGallery(){
		if(iterator == Json.length)
			return;
		else{
			console.log(iterator);
			var img = $("<img class=ID"+Json[iterator].gallery_ID+" src="+Json[iterator].gallery_href+"\>");
			var title = $("<div class=ID"+Json[iterator].gallery_ID+"><h2 onclick='openUrl("+iterator+")'>"+Json[iterator].gallery_title+"</h2><p></p></div>");
			//title.hide();
			console.log(Json[iterator].gallery_href);
			iterator++;
			$("#gallery").prepend(img);
			$("#sub_col").prepend(title);
			imagesLoaded( $('gallery:last'), function( instance ) {
		    	//console.log('img loaded');
		    	$("#gallery a").remove();//清楚<a> 每次绑定会产生一个a 但是旧的a不能去除
		    	$("#gallery").spacegallery({
					loadingClass : "loading",
					perspective : 100
				});
		    	bindClick();
		    	if(iterator==1){
		    		x = $("#gallery img:last").attr("class");
		    		$("#sub_col div." + x).show();
				}
		    	addGallery();
		    });
		}
	}
	function bindClick(){
		//因为a是每次添加图片之后生成的,所以需要每次绑定插件一次之后重新绑定
		$("#gallery a").click(function() {
			$("#sub_col div").hide();
			x = $(this).prev("img").prev("img").attr("class");
			$("#sub_col div." + x).fadeIn();
		});
	}
	function openUrl(iterator){
		window.open(Json[iterator].gallery_url);
		
	}
</script>
</head>
<body>
	<div id="root">
		<%@ include file="/construct/header.html"%>
		<div id="main" style="border: 7px solid rgba(255,255,255,0.6)">
			<div class="preview">
				<div id="sub_col"></div>
				<div id="main_col">
					<div id="gallery" class="spacegallery"></div>
				</div>
			</div>
		</div>
		<div style="clear: both"></div>
		<%@ include file="/construct/foot.html"%>
	</div>
</body>

</html>