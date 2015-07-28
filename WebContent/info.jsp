<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>公告信息</title>
    <%@ include file="/construct/keyWord.html"%>
    <link href="/css/root.css" type="text/css" rel="stylesheet" />
    <link href="/css/main.css" type="text/css" rel="stylesheet" />
    <link href="/css/menu.css" type="text/css" rel="stylesheet" />
    <link href="/css/column.css" type="text/css" rel="stylesheet"/>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <!--tab用 JavaScript-->
    <script src="/construct/tab.js"></script>
    <script src="/js/foot.js"></script>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	var width = $("#main").css("width");
			var l1 = parseInt(width)/2;
			var l2 = parseInt(width)/5;
	    	$(".l1").css("width",l1);
	    	$(".l2").css("width",l2);
	    });
    </script>
    <style type="text/css">
    	#main ul li{
    		list-style: none;
    		float: left;
    	}
    	#main{
    		min-height: 300px;
    		position:relative;
    	}
    	.l1{
    		text-align: left;
    	}
    	.l2{
    		text-align: left;
    	}
    	#pageBar{
    		position:absolute;
    		bottom: 10px;
    		width:100%
    	}
    </style>    
</head>
<body>
	<div id="root">
		<%@ include file="/construct/header.html"%>
	  	<div id="main" style="color: #FFF ;text-align: center;" >
			<ul>
				<li>
					<ul>
						<li class="l1"> 标题</li>
						<li class="l2">时间</li>
					</ul>
					<div  style="clear: both;"></div>
				</li>
				<c:forEach var="c" items="${plist.list}">
					<li>
						<ul>
							<li class="l1"><a href="/info/${c.article_ID}">${c.article_title}</a></li>
							<li class="l2">${c.article_edite_timestamp}</li>
						</ul>
						<div  style="clear: both;"></div>
					</li>
				</c:forEach>
			</ul>

			<div id="pageBar">${plist.pageBar}</div>
	  	</div>
		<div style="clear: both"></div>
		<%@ include file="/construct/foot.html"%>
	</div>
</body>
</html>
