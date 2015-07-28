<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>UserLogin</title>
<style type="text/css">
body, td, th {
	font-size: 12px;
}
</style>
<script type="text/javascript">
    function reload(id){
        //alert(id);
        //调用reload方法，_refAdvancedPage为局部刷新部分div的id 
       top.frames[id].location.reload();
        //location.reload('List', appendsearch({ 'page': pagex })); 
    }
</script>
</head>
<form method="post" action="/action/UserAction!login.action">
	<span style="color: #FFF">用户名</span>
	<input type="text" id="username" name="username" />
	<span style="color: #FFF">密码</span>
	<input
		type="password" name="userpwd" id="userpwd" />
	<button name="userlogin" type="submit" value="true"
		onclick="reload('list')">登录</button>
</form>
</html>