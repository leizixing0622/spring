<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <title>登录</title>
  <link rel="stylesheet" href="<c:url value='/css/sui.min.css'/>">
  <script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value="/js/sui.min.js" />" charset="UTF-8"></script>
</head>
<body>
	<div class="grid-demo" style="margin-top:300px;">
		<div class="sui-row">
		    <div class="span6"></div>
		    <div class="span2">
				<form class="sui-form form-horizontal sui-validate" method="POST" action="${pageContext.request.contextPath}/login/doLogin">
				  <div class="control-group">
				    <label for="inputEname" class="control-label">邮箱：</label>
				    <div class="controls">
				      <input type="text" id="ename" name="ename" placeholder="用户名" data-rules="required">
				    </div>
				  </div>
				  <div class="control-group">
				    <label for="inputPassword" class="control-label">密码：</label>
				    <div class="controls">
				      <input type="password" id="password" name="password" placeholder="密码" data-rules="required" title="密码">
				    </div>
				  </div>
				  <div class="control-group">
				    <label class="control-label"></label>
				    <div class="controls">
				      <button type="submit" class="sui-btn btn-primary">立即登录</button>
				    </div>
				  </div>
				  <p>${error}</p>
				</form>
			</div>
		    <div class="span5"></div>
		  </div>
	</div>	

<script type="text/javascript">
</script>	
</body>
</html>