<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" style="border-collapse: collapse">
		<tr bgcolor="#FFFFFF">
			<td width="829" align="center"><table width="835" border="1"
					align="center" style="border-collapse: collapse">
					<tr bgcolor="#FFFFFF">
						<td width="204" align="center">图片地址</td>
						<td width=141 align="center">图片</td>
						<td width="142" align="center">删除</td>
					</tr>
					<c:forEach var="c" items="${imagesList.list}">
						<tr>
							<td align="center">${articleImage}/${article_ID}/${c}</td>
							<td align="center">
								<img width = "400px" src="/articleImage/${article_ID}/${c}"/>
								
							</td>
							<td align="center"><a href=/action/UploadAction!deleteImage.action?image_ID=${c}&article_ID=${article_ID}>删除</a></td>
							
						</tr>
					</c:forEach>
				</table></td>
		</tr>
	</table>
	<p>${plist.pageBar}</p>
	
</body>
</html>