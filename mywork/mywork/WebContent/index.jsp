<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <title>主页</title>
  <link rel="stylesheet" href="<c:url value='/css/sui.min.css'/>">
  <script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value="/js/sui.min.js" />" charset="UTF-8"></script>
</head>
<body>
	<div class="sui-navbar">
	  <div class="navbar-inner"><a href="#" class="sui-brand">我的小站</a>
	    <ul class="sui-nav">
	      <li class="active"><a href="#">首页</a></li>
	      <li><a href="#">组件</a></li>
	      <li><a href="${pageContext.request.contextPath}/login.jsp">登录</a></li>
	    </ul>
	  </div>
	</div>
	<div class="grid-demo">
	  <div class="sui-row-fluid">
	  <div class="span1"></div>
	    <div class="span10">
	    	<img alt="" src="<c:url value='/img/bg.jpg'/>">
	    </div>
	  </div>
  	</div>
</body>
</html>