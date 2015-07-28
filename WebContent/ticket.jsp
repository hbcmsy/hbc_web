<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>门票价格</title>
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
    		var h1 = $("h1").height();
            if($("#main_left").height() >= $("#main_right").height())
				$("#main").css("height",$("#main_left").height()+2*h1);
			else
				$("#main").css("height",$("#main_right").height()+2*h1);			
        });
    </script>
</head>
<body>
	<div id="root">
		<%@ include file="/construct/header.html"%>
	  	<div id="main" style="color: #FFF ;text-align: center;" >
			<h1>门票价格</h1>
			<div id="main_left"style="float:left;width:25%; text-align: left;width:10%;padding-left: 20%;">
				<p>成人 :</p>
        		<p>儿童 :</p>
        		<p>&nbsp;</p>
				<p>军残 :</p>
				<p>老人 :</p>
			</div>
			<div id="main_right"style="float:right;width:25% ;text-align:left;width:60%; padding-right: 10%;">
				<p>60元/人</p>
        		<p>1米2以下 免费  1米4至1米4 30元/人</p>
        		<p>注:儿童需有家长陪同入园</p>
				<p>持有效证件 30元/人</p>
				<p>30元/人  60岁及以上</p>
			</div>
      	</div>
		<div style="clear: both"></div>
		<%@ include file="/construct/foot.html"%>
	</div>
</body>

</html>
