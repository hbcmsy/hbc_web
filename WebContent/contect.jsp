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
            <div id="main_left"style="width: 45%; float: left; color: #FFF; opacity: 1; text-align: left; padding-left: 10%;">
    	        <h1>联系电话	            </h1>
    	        <p>售票处:010-60418580            	   </p>
	            <p>民俗园管理处:010-89473638</p>
            	<p>&nbsp;</p>
            	<p>&nbsp;</p>
            	<h1>官方邮箱    	        </h1>
            	<p>售票处:shoupiao@hbcmsy.com.cn</p>
	            <p>新浪微博:weibo@hbcmsy.com.cn</p>
          		<p>腾讯微信:weixin@hbcmsy.com.cn</p>
          		<p>&nbsp;</p>
                <h1 style="font-size: large">网站Bug联系</h1>
                <p>technology@hbcmsy.com.cn</p>
            </div>
          	<div id="main_right" style="width: 45%; float: right; color: #FFF; opacity: 1; text-align: center;">
       		  <h1>官方微博</h1>
            		<a href="http://weibo.com/u/5230081849">
               		<img src="/images/sina_weibo.png" alt="微博" width="150" height="150"/></a>
           		<h1>官方微信</h1>
       	      	<p>
                	<img src="/images/qrcode_wx.png" alt="hbcmstyy" width="150" height="150"/>
   	        	</p>
       	      	<p>hbcmstyy</p>
          	</div>
        </div>
		<div style="clear: both"></div>
		<%@ include file="/construct/foot.html"%>
	</div>
</body>

</html>
