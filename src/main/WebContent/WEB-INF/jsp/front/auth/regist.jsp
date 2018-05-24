<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="container content">
		<div class="line"></div>
		<h3 class="no-margin">회원가입</h3>
		
		<form action="/front/auth/regist.do" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="memberEmail" class="col-sm-1 control-label">이메일</label>
				<div class="col-sm-10">
					 <input type="text" id="memberEmail" name="memberEmail" value="" class="form-control" placeholder="email">
				</div>
			</div>
			<div class="form-group">
				<label for="memberEmail" class="col-sm-1 control-label">비밀번호</label>
				<div class="col-sm-10">
					 <input type="password" id="memberPassword" name="memberPassword" value="" class="form-control" placeholder="password">
				</div>
			</div>
			<div class="form-group">
				<label for="memberEmail" class="col-sm-1 control-label">닉네임</label>
				<div class="col-sm-10">
					 <input type="text" id="memberNick" name="memberNick" value="" class="form-control" placeholder="nick">
				</div>
			</div>
			<input type="submit" value="등록" class="btn btn-primary">
			<input type="reset" value="취소" class="btn btn-danger">
		</form>
	</div>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>