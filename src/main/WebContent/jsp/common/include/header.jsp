<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<!-- css -->
<link rel="stylesheet" href="/util/bootstrap-3.3.2-dist/css/bootstrap.css">
<link rel="stylesheet" href="/util/bootstrap-3.3.2-dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/util/fontawesome-free-5.0.13/web-fonts-with-css/css/fontawesome-all.min.css">

<!-- javascript -->
<script src="/js/jquery/jquery-3.3.1.js"></script>
<script src="/util/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>

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
			<a class="navbar-brand" href="<%= request.getContextPath()%>/admin/main.jsp">IssueBreakers</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<%
				String menuActive1 = "";
				String menuActive2 = "";
				String menuActive3 = "";
				String menuActive4 = "";
				
				String path = request.getServletPath();
				if(path.equals("/admin/categoryInput.jsp") || path.equals("/main.jsp")) {
					menuActive1 = "active";
				} else if (path.equals("/admin/categoryList.jsp") || path.equals("/myshop/member/register.jsp")) {
					menuActive2 = "active";
				} else if (path.equals("/admin/productInput.jsp")) {
					menuActive3 = "active";
				} else if (path.equals("/admin/productList.jsp")) {
					menuActive4 = "active";
				}
				
				if(1 == 1) {
			%>
				<ul class="nav navbar-nav">
					<li class="<%=menuActive1%>">
						<a href="<%= request.getContextPath()%>/admin/categoryInput.jsp">Issue</a>
					</li>
					<li class="<%=menuActive2%>">
						<a href="<%= request.getContextPath()%>/admin/categoryList.jsp">Tag</a>
					</li>
					<li class="<%=menuActive3%>">
						<a href="<%= request.getContextPath()%>/admin/productInput.jsp">Information</a>
					</li>
					<li class="<%=menuActive4%>">
						<a href="<%= request.getContextPath()%>/admin/productList.jsp">rank</a>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<form class="navbar-form navbar-left" action="/action_page.php">
							<div class="input-group" style="width:200px; margin-top:8px;">
							    <span class="input-group-addon"><i class="fas fa-search"></i></span>
							    <input id="text" type="text" class="form-control" name="text" placeholder="write">
							</div>
						</form>
					</li>
					<li>
						<a href="#"> 님</a>
					</li>
					<li>
						<a href="<%= request.getContextPath()%>/logout.jsp">로그아웃</a>
					</li>
				</ul>
			<%
				} else {
			%>
					<ul class="nav navbar-nav navbar-right">
						<li class="<%=menuActive1%>">
							<a href="<%= request.getContextPath()%>/main.jsp">로그인</a>
						</li>
						<li class="<%=menuActive2%>">
							<a href="<%= request.getContextPath()%>/myshop/member/register.jsp">회원가입</a>
						</li>
					</ul>
			<%
				}
			%>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>

</head>

<body>