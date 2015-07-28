<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
	<title>JSP Page</title>
	<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="/ckeditor/ckeditor.js" charset="UTF-8"></script>
	<script type="text/javascript" charset="UTF-8">
        $(document).ready(function(e) {
			var edit_category = '${edit_category}';
			switch(edit_category){
				case 'g':
					$("#select").val('g');
				break;
				case 'd':
					$("#select").val('d');
				break;
				case 't':
					$("#select").val('t');
				break;
				case 'l':
					$("#select").val('l');
				break;
			
			}
			var value = select.options[select.selectedIndex].value;		
        });
        function changeImage(){
        	var article_ID = '${article_ID}';
        	var image_url = $("#image_url").val();
			//window.alert(image_url);
			image_url="/articleImage/"+"/"+article_ID+"/"+image_url;
			$("#image").attr("src",image_url);
        }
	</script>
</head>
<body>
	<form method="post"
		action="/action/EditAction!save.action?article_ID=${article_ID}">
		<p>
			<input name="edit_title"    value="${edit_title}">
			<select id="select" name="edit_category" >
            			<option value="g">公告</option>
                		<option value="t">拓展区</option>
                		<option value="l">历史展区</option>
                		<option value="d">动物园</option>
       		</select>
       		<input id = "image_url"name="edit_image" value="${edit_image}" onchange="javascript:changeImage()">
            <img  id="image" src="/articleImage/${article_ID}/${edit_image}" width="100px" >
			<textarea cols="150" name="edit_text" rows="10">${edit_text}</textarea>
		</p>
		<button name="save">上传</button>
		<script type="text/javascript">
			var article_ID = '${article_ID}';	
			var url = '/actions/UploadAction!image.action?article_ID='+article_ID;
	        
			if ( CKEDITOR.env.ie )
		       CKEDITOR.replace( 'edit_text',{filebrowserUploadUrl:"/actions/UploadAction!image.action?article_ID=${article_ID}",
		    language: 'en'} );
		    else
		        CKEDITOR.replace('edit_text',{filebrowserUploadUrl:"/actions/UploadAction!image.action?article_ID=${article_ID}"});
		</script>
	</form>
    <iframe class="list" id="list1" frameborder="0" width="100%" height="500px"src="/action/UploadAction!listImage.action?article_ID=${article_ID}"> </iframe>
</body>
</html>