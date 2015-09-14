<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<table border="1"  style="margin:auto;border-collapse: collapse">
	<tr bgcolor="#FFFFFF">
		<td width="829" align="center">
		<table  border="1"  style="border-collapse: collapse">
				<tr bgcolor="#FFFFFF">
					<td width="204" align="center">标题</td>
					<td width=141 align="center">作者</td>
					<td width="133" align="center">最后修改</td>
					<td width="125" align="center">创建日期</td>
					<td width="39" align="center">状态</td>
					<td width="39" align="center">删除</td>
					<td width="39" align="center">发布</td>
					<td width="39" align="center">编辑</td>
				</tr>
				<c:forEach var="c" items="${plist.list}">
					<tr>
						<td align="center">
							<a href="javascript:load('/action/ShowAction!show.action?article_ID=${c.article_ID}')">${c.article_title}</a></td>
						<td align="center">${c.article_author}</td>
						<td align="center">${c.article_creat_timestamp}</td>
						<td align="center">${c.article_edite_timestamp}</td>
						<td align="center">${c.article_state}</td>
						<td align="center"><a
							href="javascript:load('/action/EditAction!delete.action?article_ID=${c.article_ID}')">${c.article_state}</a></td>
						<td align="center"><a
							href="javascript:load('/action/EditAction!release.action?article_ID=${c.article_ID}')">${c.article_state}</a></td>
						<td align="center"><a
							href="javascript:load('/action/EditAction!edit.action?article_ID=${c.article_ID}')">${c.article_state}</a></td>
					</tr>
				</c:forEach>
		</table>
		</td>
	</tr>
</table>
<p align="center">
	<a href="javascript:load('/action/EditAction!add.action')">添加</a>${plist.pageBar}<br>
	<a href="/manager/management.jsp">返回首页</a>
</p>
</html>
