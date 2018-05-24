<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="container content index">
		<div class="line"></div>
		<h3 class="no-margin">아이디 찾기</h3>
		
		<form method="post" onsubmit="return false" class="form-horizontal">
			<div class="form-group">
				<label for="memberEmail" class="col-sm-1 control-label">닉네임</label>
				<div class="col-sm-10">
					 <input type="text" id="memberNick" name="memberNick" value="" class="form-control" placeholder="nick">
				</div>
			</div>
			<input type="button" id="findId" value="아이디 찾기" onclick="test('id')" class="btn btn-primary">
		</form>
		
		<div class="line"></div>
		<h3 class="no-margin">비밀번호 찾기</h3>
		
		<form method="post" onsubmit="return false" class="form-horizontal">
			<div class="form-group">
				<label for="memberEmail" class="col-sm-1 control-label">이메일</label>
				<div class="col-sm-10">
					 <input type="text" id="memberEmail" name="memberEmail" value="" class="form-control" placeholder="email">
				</div>
			</div>
			<input type="button" id="findPassword" value="비밀번호 찾기" onclick="test('password')" class="btn btn-primary">
		</form>
	</div>
	
	<script>
		function test(type) {
			var data = {};
			if(type == "id") {
				data = {
					type : type	
					,memberNick : $("#memberNick").val()	
				};
			} else {
				data = {
					type : type
					,memberEmail : $("#memberEmail").val()
				};
			}
			$.ajax({
				url:"/front/auth/find.ajax"
				,data:data
				,success:function(res) {
					alert(res);
				}
			});	
		}
	</script>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>