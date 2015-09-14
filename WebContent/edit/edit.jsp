<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<form method="post" onsubmit="return false;">
	<p>
		<input  id="edit_title" name="edit_title"    value="${edit_title}">
		<select id="edit_category" name="edit_category" >
           			<option value="g">公告</option>
               		<option value="t">拓展区</option>
               		<option value="l">历史展区</option>
               		<option value="d">动物园</option>
      		</select>
      		<input id = "edit_image"name="edit_image" value="${edit_image}" onchange="javascript:changeImage()">
           <img  id="image" src="/articleImage/${article_ID}/${edit_image}" width="100px" >
		<textarea cols="150" id = "edit_text" name="edit_text" rows="10">${edit_text}</textarea>
	</p>
	<button name="save" onclick="javascript:saveArticle('/action/EditAction!save.action',${article_ID},$('#edit_title'),$('#edit_image'),$('#edit_text'),$('#edit_category'))">上传</button>
	<script type="text/javascript">
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
		//var value = select.options[select.selectedIndex].value;		
		function changeImage(){
       		var article_ID = '${article_ID}';
       		var image_url = $("#image_url").val();
			//window.alert(image_url);
			image_url="/articleImage/"+"/"+article_ID+"/"+image_url;
			$("#image").attr("src",image_url);
		}
		function reloadImage(){
			var src="/action/UploadAction!listImage.action?article_ID=${article_ID}"
			$("#imageList").load(src);					 
		}
		function deleteImage(article_ID,image_ID){
			$.post("/action/UploadAction!deleteImage.action?image_ID="+image_ID+"&article_ID="+article_ID);
			reloadImage()
		}
	
		var article_ID = '${article_ID}';	
		//var url = '/actions/UploadAction!image.action?article_ID='+article_ID;
        
		//editor = CKEDITOR.appendTo( 'edit_text', config);

		if ( CKEDITOR.env.ie )
			editor = CKEDITOR.replace( 'edit_text',{filebrowserUploadUrl:"/actions/UploadAction!image.action?article_ID=${article_ID}",
	    language: 'en'} );
	    else
	    	editor = CKEDITOR.replace('edit_text',{filebrowserUploadUrl:"/actions/UploadAction!image.action?article_ID=${article_ID}"} );
	</script>
</form>
<div id=imageList> </div>

