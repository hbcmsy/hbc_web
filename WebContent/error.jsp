<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>联系我们</title>
	<%@ include file="/construct/keyWord.html"%>
    <link href="/css/root.css" type="text/css" rel="stylesheet" />
    <link href="/css/main.css" type="text/css" rel="stylesheet" />
    <link href="/css/menu.css" type="text/css" rel="stylesheet" />
    <link href="/css/column.css" type="text/css" rel="stylesheet"/>

    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <!--tab用 JavaScript-->
    <script src="/construct/tab.js"></script>
    <script src="/js/foot.js"></script>    
    <script type="text/javascript" >
    	$(document).ready(function(e) {
            if($("#main_left").height() >= $("#main_right").height())
				$("#main").css("height",$("#main_left").height());
			else
				$("#main").css("height",$("#main_right").height());			
        });
    </script>
</head>
<body>
	<div id="root">
		<%@ include file="/construct/header.html"%>
		<div id="main" >
        	<p style="color: #FFF; opacity: 1; text-align: center;">Oh! 出错了 回到首页吧!</p>
        </div>
		<div style="clear: both"></div>
		<%@ include file="/construct/foot.html"%>
	</div>
</body>

</html>
