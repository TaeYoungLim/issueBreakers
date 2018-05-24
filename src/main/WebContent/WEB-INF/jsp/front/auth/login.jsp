<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="container content">
		<div class="line"></div>
		<h1 class="no-margin">로그인</h1>
		
		<c:if test="${!empty requestScope.msg}">
			${requestScope.msg}
		</c:if>
		
		<form action="/front/auth/login.do" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="memberEmail" class="col-sm-1 control-label">이메일</label>
				<div class="col-sm-10">
					<input type="text" id="memberEmail" name="memberEmail" value="" class="form-control" placeholder="email">
				</div>
			</div>
			<div class="form-group">
				<label for="memberPassword" class="col-sm-1 control-label">비밀번호</label>
				<div class="col-sm-10">
					<input type="password" id="memberPassword" name="memberPassword" value="" class="form-control" placeholder="password">
				</div>
			</div>
			<input type="submit" value="로그인" class="btn btn-primary">
		</form>
		
		<a href="/front/auth/find.do">회원정보를 잊어버리셨나요?</a>
	</div>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>