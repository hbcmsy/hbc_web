<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>民俗园介绍</title>
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
			<h1>园区简介</h1>
	    	<br clear="ALL">
			<p>&nbsp;&nbsp;河北村民俗文化有限公司于2012年初投资2000万元建设河北村民俗文化体验园。体验园园区占地2000亩，是一所集传统教育、体验拓展、休闲娱乐为主的田园体验式游览场所，
				2014年6月1日投入运营。园区突出&ldquo;弘扬北京精神，推进首都公民道德建设，促进文化大发展、大繁荣&rdquo;，促进传统民俗文化的发展与都市现代农业的有机结合，
				使传统民俗文化更具特色、更有品位、更多受众，利于构建和谐社会、提高公民素质，从而服务于社会、服务于大众。
			  </p>			
				  <h2>园区主要分为五大功能区域：</h2>
			  <p>
				  &nbsp;&nbsp;<strong>一:</strong>&nbsp;手工作坊传统农产品加工区，包括：传统香油制作、粗粮加工、盐卤豆腐坊、传统小吃制作等；<br>
				  &nbsp;&nbsp;<strong>二:</strong>&nbsp;小动物养殖观赏区，包括：有豪猪、四角羊、狍子、猴、孔雀、鸵鸟、獭兔和一些其它类的动物；<br>
				  &nbsp;&nbsp;<strong>三:</strong>&nbsp;民俗文化与村史博物馆，基本上还原了我们过去农户家庭生产、生活所需的用品、生产队办公、耕种所需所用的物品、老物件的收藏展示，反映了社会的变迁与发展<br>
				  &nbsp;&nbsp;<strong>四:</strong>&nbsp;青少年拓展健身区域：有很多适合儿童和青少年加强锻炼的项目；<br>
				  &nbsp;&nbsp;<strong>五:</strong>&nbsp;司法教育基地：包括道德传统教育漫画长廊，司法制度沿袭变革的图表，最大程度的还原了旧时的老县衙审案情景等等。<br>
			  </p>
			  <h2> 园区特色 
			  </h2>
			  <p>
				  &nbsp;&nbsp;一条经污水处理站引出的循环水造就的金鱼池、金鱼小溪、天鹅湖将整个民俗文化体验园串联起来，园区内遍布多名讲解员，青少年可以边看、边听、边体验。
					园区内设有民俗文化展馆、农耕农事体验园、红色教育基地，复原了生产队、婆媳农家、老行当等时代场景、存留物品；
					司法教育基地、传统农产品加工基地、青少年拓展健身基地、动物养殖体验基地项目，不仅可以使游客体验县衙的威严、传统工艺加工的乐趣、拓展项目的刺激，还可以体验亲自喂养小动物的欢乐；
					此外，双河果园三季有果、四季常绿，包含有机樱桃、草莓、杏、桃、葡萄等采摘林，供游客采摘。 <br>
			  </p>
			  <h1>&nbsp;</h1>
			  <h3 style="text-align: center">团队组成</h3>
			  <p>&nbsp;&nbsp;河北村民俗文化体验园拥有强大的解说团队、安全保障团队和专业培训团队。
					解说团队中包括经历本村变迁的往届及当届村党支部、村委会工作人员、中青年教师和大学生村官，特别聘请了清华大学等国内著名高校研究民俗文化的专家学者；
					针对青少年拓展项目及水上项目，体验园成立了专业的安全保障团队，聘请了来自全国各地的受过专业紧急救助训练并拥有专业工作经历的拓展培训师和应急救生员；
					此外，体验园聘请了当地从事农耕劳作的农民、从事过手工行当的手工从业人员、养殖场的专业饲养员及司法工作人员，成立了一支专业培训团队，确保各项体验项目达到预期效果。
			</p>
        <!--百度地图容器-->
		<div style="width:100%;height:550px;border:#ccc solid 1px;" id="dituContent"></div>
        <script src="/js/baidumap.js"></script>
		<div style="clear: both"></div>
        </div>
		<%@ include file="/construct/foot.html"%>
	</div>
</body>

</html>
