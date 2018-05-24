<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<!-- javascript -->
<script src="/js/jquery/jquery-3.3.1.js"></script>
<script src="/util/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script src="/util/codemirror-5.38.0/codemirror.js"></script>
<script src="/util/codemirror-5.38.0/simplescrollbars.js"></script>

<!-- css -->
<link rel="stylesheet" href="/util/bootstrap-3.3.2-dist/css/bootstrap.css">
<link rel="stylesheet" href="/util/bootstrap-3.3.2-dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/util/fontawesome-free-5.0.13/web-fonts-with-css/css/fontawesome-all.min.css">
<link rel="stylesheet" href="/util/codemirror-5.38.0/codemirror.css">
<link rel="stylesheet" href="/util/codemirror-5.38.0/simplescrollbars.css">

<!-- Fixed navbar -->
<nav class="navbar">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">IssueBreakers</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<%
				String menuActive1 = "";
				String menuActive2 = "";
				String menuActive3 = "";
				String menuActive4 = "";
				
				String path = request.getServletPath();
				if(path.equals("/admin/categoryInput.jsp")) {
					menuActive1 = "active";
				} else if (path.equals("/admin/categoryList.jsp")) {
					menuActive2 = "active";
				} else if (path.equals("/admin/productInput.jsp")) {
					menuActive3 = "active";
				} else if (path.equals("/admin/productList.jsp")) {
					menuActive4 = "active";
				}
			%>
			<ul class="nav navbar-nav">
				<li class="<%=menuActive1%>">
					<a href="/front/board/list.do?boardCategoryId=issue">Issue</a>
				</li>
				<li class="<%=menuActive2%>">
					<a href="/front/tag/list.do">Tag</a>
				</li>
				<li class="<%=menuActive3%>">
					<a href="/front/board/list.do?boardCategoryId=information">Information</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${!empty sessionScope.memberVo}">
					<li>
						<a href="/front/myPage/myPage.do">${sessionScope.memberVo.memberNick} 님</a>
					</li>
					<li>
						<a href="/front/auth/logout.do">로그아웃</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="<%=menuActive1%>">
						<a href="/front/auth/login.do">로그인</a>
					</li>
					<li class="<%=menuActive2%>">
						<a href="/front/auth/regist.do">회원가입</a>
					</li>
				</c:otherwise>
			</c:choose>
			<li>
				<a href="javascript:void(0);" class="search"><i class="fas fa-search"></i>검색</a>
			</li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>

<form id="searchForm" action="/front/search/search.do" style="display:none; position:absolute; top:50px; right:50px; width: 500px; z-index:9999999999;">
	<div class="form-group" style="float:left; width:500px;">
		<div class="col-sm-10">
			<input type="text" id="searchWord" name="searchWord" value="${param.searchWord}" class="form-control" placeholder="search word" style="width:350px; float:left;">
			<button onclick="$('#searchForm').action();" style="background:none; color:#888888; border:0px; float:left; height:34px;"><i class="fas fa-search"></i></button>
		</div>
	</div>
</form>

<script type="text/javascript">
	$(document).ready(function() {
		$(".search").click(function() {
			var obj = $("#searchForm");
			
			if(obj.hasClass("active")) {
				$("#searchForm").hide().removeClass("active");	
			} else {
				$("#searchForm").show().addClass("active");
			}
		});	
	});
</script>

</head>
<body>