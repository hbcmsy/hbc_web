<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>${article.article_title}</title>
    <%@ include file="/construct/keyWord.html"%>
    <link href="/css/root.css" type="text/css" rel="stylesheet" />
    <link href="/css/main.css" type="text/css" rel="stylesheet" />
    <link href="/css/menu.css" type="text/css" rel="stylesheet" />
    <link href="/css/column.css" type="text/css" rel="stylesheet"/>

    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <!--tab用 JavaScript-->
    <script src="/construct/tab.js"></script>
    <script src="/js/foot.js"></script>    
	<style type="text/css">
		#main img{
			width:100%
		}
	</style>
</head>
<body>
	<div id="root">
		<%@ include file="/construct/header.html"%>
	  	<div id="main" style="color: #FFF;" >
        	<h1 style="text-alig: center">${article.article_title}</h1>
        	<p style="text-align: right;">Author:${article.article_author_name}<p>
        	<p style="text-align: right;">创建于:${article.article_creat_timestamp}<p>
        	${article.article_text}
      	</div>
		<div style="clear: both"></div>
		<%@ include file="/construct/foot.html"%>
	</div>
</body>

</html>
