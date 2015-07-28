
/*    imagesLoaded( $('.item:last'), function( instance ) {
    	isLoad=true;	
    });
    var iterator = 0;
    var syn = true;//同步,防止在执行添加的过程中又执行一个添加,导致页面出问题
    var isLoad = false;
    var Json;
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
	$(function(){ 
		var $container = $('#container');    
		$container.masonry({  
				itemSelector : '.item',  
				isAnimated : true  
        });
		$.getJSON("/action/ShowAction!ListAnimal.action",function(json){ 
			//对返回JSON里面的list数据遍历 并添加图片 加载后删除
			
			json = JSON.parse(json);
			
			while(iterator<json.length&&isFull()){
				if(!isLoad){
					imagesLoaded( $('.item:last'), function( instance ) {
				    	isLoad=true;	
				    });
					continue;
				}
				var div = $("<div class = \"item\"></div>");
				var img = $("<img alt=\"/articleImage/" + json[iterator].ID + "/" + json[iterator].img + "\"" + "src=\"/articleImage/"+json[iterator].ID+"/"+json[iterator].img+"\">");
				var p = $("<p>"+json[iterator].title+"</p>")
				div.append(img);
				div.append(p);
				$container.append(div).masonry( 'appended', div);
				imagesLoaded( $('.item:last'), function( instance ) {
			    	isLoad=true;	
			    });
				isLoad=false;
				iterator++;
			}
			$(window).scroll(function(){
				if(!syn)
					return;
				else
					syn = false;
				if(iterator>=json.length-1)
					return;	
				for(var i = iterator;i<json.length&&isFull();i++){
					var div = $("<div class = \"item\"></div>");
					var immg = "<img alt=\"/articleImage/" + json[i].ID + "/" + json[i].img + "\"" + "src=\"/articleImage/"+json[i].ID+"/"+json[i].img+"\">";
					var img = $(immg);
					div.append(img);
					$container.append(div).masonry( 'appended', div);
					iterator++;
				}
				syn = true;
			});
		});
	});
*/