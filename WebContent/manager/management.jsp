<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title>后台编辑</title>
	<link rel="stylesheet" type="text/css" href="../css/managment.css" />
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			jQuery.jqtab = function(tabtit,tab_conbox,shijian) {
				$(tab_conbox).find("li").hide();
				$(tabtit).find("li:first").addClass("thistab").show(); 
				$(tab_conbox).find("li:first").show();
			
				$(tabtit).find("li").bind(shijian,function(){
				  $(this).addClass("thistab").siblings("li").removeClass("thistab"); 
					var activeindex = $(tabtit).find("li").index(this);
					$(tab_conbox).children().eq(activeindex).show().siblings().hide();
					return false;
				});
			
			};
			/*调用方法如下：*/
			$.jqtab("#tabs","#tab_conbox","click");
			//$.jqtab("#tabs","#tab_conbox","mouseenter");
			
		});
        function load(id){
        	//id = "list";
       		//var e = window.document.getElementById(id);
       		$("#main-content").load(url+state+category+selected);
        	//e.src = e.src;
        }
        var url = "/action/EditAction!list.action?" 
        var state = "STATE=U";
        var category = "&CATEGORY=g";
        var selected = "&usr=a";
        function changeState(s){
        	switch(s){
        	case 'U':
        		state = "STATE=U";
        		break;
        	case 'E':
        		state = "STATE=E";
        		break;
        	case 'D':
        		state = "STATE=D";
        		break;
        	case 'A':
        		state = "STATE=A";
        		break;
        	case 'g':
        		category = "&CATEGORY=g";
        		state = "STATE=U";
        		break;
        	case 'l':
        		category = "&CATEGORY=l";
        		state = "STATE=U";
        		break;
        	case 'd':
        		category = "&CATEGORY=d";
        		state = "STATE=U";
        		break;
        	case 't':
        		category = "&CATEGORY=t";
        		state = "STATE=U";
        		break;	
       		default:
       			break;
        	}
        	$("#main-content").load(url+state+category+selected);
        	//document.getElementById('list').src = url+state+category+selected;
        }
        function changeSelect(){
        	var value = select.options[select.selectedIndex].value;
        	switch(value){
        	case "all":
        		selected = "&usr=a";
        		break;
        	case "author":
        		selected = "&usr=u";
        		break;
        	case "editor":
        		selected = "&usr=e";
        		break;
       		default:
       			break;
        	}
        	//window.alert(url+state+category+selected);
        	//document.getElementById('list').src = url+state+category+selected;
        	//document.getElementById('list').contentWindow.location.reload();
        	$("#main-content").load(url+state+category+selected);
        }
	</script>

</head>
<body>
<div id="root">
	<div id="header">
		<div id="header_left">
			<a class="header-button" href="/manager/management.jsp">Back Home</a>		
		</div>
		<div id="header_right">
			<iframe id="userbar" onload="load('list')" src="/action/UserAction!show.action" 
			scrolling="no" frameborder="0"
					width="500"></iframe>
		</div>
	</div>
	<div id="main">
    	<div id="top-sidebar">
	        <ul>
	        
    	        <li><button onclick="javascript:changeState('U');">已发布</button></li>
        	    <li><button onclick="changeState('E')">编辑中</button></li>
            	<li><button onclick="changeState('D')">回收站</button></li>
	            <li><button onclick="changeState('A')">全部</button></li>
	            <li>
	            	<select id="select" name="select" onchange="javascript:changeSelect()" >
            			<option value="all">所有</option>
                		<option value="author">作为作者</option>
                		<option value="editor">作为编辑</option>
            		</select>
           		</li>
            </ul>
            
        </div>
       <div style="clear: both;"></div>
        <div id="left-sidebar">
            <ul>
            <li><button onclick="$('#main-content').load('/manager/gallery.html')">首页编辑</button></li>
            <li><button onclick="changeState('g')">公告</button></li>
            <li><button onclick="changeState('l')">历史展区</button></li>
            <li><button onclick="changeState('d')">动物园</button></li>
            <li><button onclick="changeState('t')">拓展区</button></li>
            </ul>
        </div>
        <div id="main-content">
             
            	<iframe class="list" id="list" frameborder="0" src="/action/EditAction!list.action?STATE=ALL"> </iframe>
            
        </div>
	</div>
	<div style="clear: both;"></div>
	<div id="footer">
		<p>Footer stuff.</p>
	</div>
</div>
</body>
</html>


