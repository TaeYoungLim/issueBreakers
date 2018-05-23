<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="container content">
		<div class="line"></div>
		<h1 class="no-margin">글쓰기</h1>
		
		<form action="/front/board/regist.do" method="post" class="form-horizontal" onsubmit="moveData();">
			<input type="hidden" id="boardCategoryId" name="boardCategoryId" value="${param.boardCategoryId}">
			<input type="hidden" id="boardContent" name="boardContent" value="">
			<div class="form-group">
				<label for="boardSubject" class="col-sm-1 control-label">제목</label>
				<div class="col-sm-10">
					 <input type="text" id="boardTitle" name="boardTitle" value="" class="form-control" placeholder="title">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">내용</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					 <textarea id="content" name="content" class="form-control" placeholder="content" rows="10"></textarea>
				</div>
			</div>
			<input type="submit" value="등록" class="btn btn-primary">
			<input type="reset" value="취소" class="btn btn-danger">
		</form>
	</div>
	
	<script>
		var editor = null;
		
		$(document).ready(function() {
			if($("textarea").length > 0){
				editor = CodeMirror.fromTextArea($("textarea")[0], {
					lineNumbers: true
				});	
			}
		});
		
		function moveData() {
			$("#boardContent").val(editor.getValue());
		}
	</script>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>