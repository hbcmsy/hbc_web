<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>拓展区</title>
	<%@ include file="/construct/keyWord.html"%>
    <link href="/css/root.css" type="text/css" rel="stylesheet" />
    <link href="/css/main.css" type="text/css" rel="stylesheet" />
    <link href="/css/menu.css" type="text/css" rel="stylesheet" />
    <link href="/css/column.css" type="text/css" rel="stylesheet"/>
    <link href="/css/listImg.css" type="text/css" rel="stylesheet"/> 
    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="/js/masonry.pkgd.min.js"></script>
	<script src="/js/imagesloaded.pkgd.min.js"></script>
    <!--tab用 JavaScript-->
    <script src="/construct/tab.js"></script>
    <script src="/js/foot.js"></script>
       <!-- 瀑布流 Ajax下json异步获取 -->
    <script src="/js/listImg.js"></script>
    <script type="text/javascript">
    var iterator = 0;//遍历索引,保证在整个遍历JSON数据
    var synLock = false;//同步锁,保证滑动事件激活的时候没有重复和漏掉的图片没有显示
    var Json;//Json数据
    var startFull = false;//初始满屏flag,在次flag为假时,滑动事件不会导致元素添加,直到整个页面满
    $.getJSON("/action/ShowAction!ListExpand.action",function(json){ 
		//对返回JSON里面的list数据遍历 并添加图片 加载后删除
		json = JSON.parse(json);
		Json = json;
		$container = $("#container");
    	var div = $("<div class = \"item\"></div>");
		var img = $("<img alt=\"/articleImage/" + Json[iterator].ID + "/" + Json[iterator].img + "\"" + "src=\"/articleImage/"+Json[iterator].ID+"/"+Json[iterator].img+"\">");
		var p = $("<p>"+Json[iterator].title+"</p>");
		$container.append(div);
		iterator++;
		div.append(img);
		div.append(p);
		imagesLoaded( $('.item:last'), function( instance ) {
	    	var $container = $('#container');    
			$container.masonry({  
					itemSelector : '.item',  
					isAnimated : true  
	        });
			add();
	    });
    });
    $(window).scroll(function(){
    	if(synLock){
    		return;
    	}
    	if(iterator>Json.length-1){
			return;	
    	}
		if(!startFull){
			return;
		}
		if(synLock){
    		return;
		}
		//console.log("scroll:"+iterator+"add");
		add();
	    
	});
    function add(){
    	//console.log("add:"+iterator+"in");
    	if(synLock)
    		return;
    	//console.log("add:"+iterator+"Lock");
    	synLock=true;
    	if(!isFull()){
    		//console.log("add:"+iterator+"Full");
    		startFull = true;
    		synLock = false;
    		return;
    		
    	}
    	$container = $("#container");
    	var div = $("<div class = \"item\"></div>");
		var img = $("<img alt=\"/articleImage/" + Json[iterator].ID + "/" + Json[iterator].img + "\"" + "src=\"/articleImage/"+Json[iterator].ID+"/"+Json[iterator].img+"\">");
		var p = $("<p>"+Json[iterator].title+"</p>")
		div.append(img);
		div.append(p);
		img.attr("src", "/articleImage/"+Json[iterator].ID+"/"+Json[iterator].img).load(function() {
			var rat = img.css("width")/this.width;
			img.css("height",this.height*rat);
			$container.append(div).masonry( 'appended', div);
			//console.log("add:"+iterator+"appended");
			iterator++;
			//console.log("add:"+(iterator-1)+"iterator++");
			synLock = false;
			//console.log("add:"+(iterator-1)+"unLock");
			if(iterator<Json.length){
				//console.log("add:"+iterator+"add");
				add();
			}
	    });
		
    }
    function isFull(){ 
		var lastDiv = $(".item:last"); 
		//浏览器可视区域的高度 
		var see = document.documentElement.clientHeight; 
		//滚动条滑动的距离 
		var winScroll = $(this).scrollTop(); 
		//每个的最后一个LI，距离浏览器顶部的 
		var lastsee = lastDiv.offset().top;
		return lastsee < (see+winScroll)?true:false; 
	} 
    </script>
</head>
<body>
	<div id="root">
		<%@ include file="/construct/header.html"%>
		<div id="main" style="color:#FFF;">
          	<div id="container">
        	</div>
        	<div id="test"></div>
		</div>
		<div style="clear: both"></div>
		<%@ include file="/construct/foot.html"%>
	</div>	 
</body>
</html>	  

