<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dlo新闻${title }</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/home/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/home/css/nprogress.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/home/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/home/css/font-awesome.min.css">
<link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/static/home/images/icon.png">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/home/images/favicon.ico">
<script src="${pageContext.request.contextPath}/static/home/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/static/home/js/nprogress.js"></script>
<script src="${pageContext.request.contextPath}/static/home/js/jquery.lazyload.min.js"></script>
<!--[if gte IE 9]>
  <script src="/static/home/js/jquery-1.11.1.min.js" type="text/javascript"></script>
  <script src="/static/home/js/html5shiv.min.js" type="text/javascript"></script>
  <script src="/static/home/js/respond.min.js" type="text/javascript"></script>
  <script src="/static/home/js/selectivizr-min.js" type="text/javascript"></script>
<![endif]-->
<script>
// function addFavorite(url, title) {
// 	try {
// 		window.external.addFavorite(url, title);
// 	} catch (e){
// 		try {
// 			window.sidebar.addPanel(title, url, '');
//         	} catch (e) {
// 			alert("请按 Ctrl+D 键添加到收藏夹", 'notice');
// 		}
// 	}
// }
// $(function(){
// 	$(".btn").dropdown();
// });


</script>
</head>
<body class="user-select">
<header class="header">
<nav class="navbar navbar-default" id="navbar">
<div class="container">
  <div class="header-topbar hidden-xs link-border">
	<ul class="site-nav topmenu">
	</ul>
			</div>
  <div class="navbar-header">
	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header-navbar" aria-expanded="false"> <span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
	<h1 class="logo hvr-bounce-in"><a href="/index/index" title="Dlo新闻网站"><img src="../static/home/images/logo.png" alt="Dlo新闻网站"></a></h1>
  </div>
  <div class="collapse navbar-collapse" id="header-navbar">
	<form class="navbar-form visible-xs" action="../news/search_list" method="get">
	  <div class="input-group">
		<input type="text" name="keyword" class="form-control" placeholder="请输入关键字" maxlength="20" autocomplete="off" value="${keyword }">
		<span class="input-group-btn">
		<button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
		</span> </div>
	</form>
	<ul class="nav navbar-nav navbar-right">
<%--	  <li><a data-cont="Dlo新闻网站" title="Dlo新闻网站" href="index/index">首页</a></li>--%>
	  <c:forEach items="${newsCategoryList }" var="newsCategory">
	  <li><a data-cont="${newsCategory.name }" title="${newsCategory.name }" href="/news/category_list?cid=${newsCategory.id }">${newsCategory.name }</a></li>
	  </c:forEach>

	<div class="nav navbar-nav navbar-right dropdown">
		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			个人 <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu" >
			<li role="presentation">
				<a role="menuitem" tabindex="-1" href="/system/index">用户后台</a>
			</li>
<%--			<li role="presentation">--%>
<%--				<a role="menuitem" tabindex="-1" href="#">我的评论</a>--%>
<%--			</li>--%>
		</ul>
	</div>
	</ul>




  </div>
</div>
</nav>
</header>