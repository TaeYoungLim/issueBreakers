<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<c:set var="boardVo" value="${requestScope.boardVoList[0]}"/>
	<div class="container content">
		<div class="line"></div>
		<h1 class="no-margin">글수정</h1>
		
		<form action="/front/board/update.do" method="post" class="form-horizontal">
			<input type="hidden" id="boardId" name="boardId" value="${param.boardId}">
			<input type="hidden" id="boardCategoryId" name="boardCategoryId" value="${param.boardCategoryId}">
		
			<div class="form-group">
				<label for="boardSubject" class="col-sm-1 control-label">제목</label>
				<div class="col-sm-10">
					 <input type="text" id="boardTitle" name="boardTitle" value="${boardVo.boardTitle}" class="form-control" placeholder="title">
				</div>
			</div>
			<div class="form-group">
				<label for="boardContent" class="col-sm-1 control-label">내용</label>
				<div class="col-sm-10">
					 <textarea id="boardContent" name="boardContent" class="form-control" placeholder="content" rows="10">${boardVo.boardContent}</textarea>
				</div>
			</div>
			
			<input type="submit" value="수정" class="btn btn-primary">
			<input type="reset" value="취소" class="btn btn-danger">
		</form>
	</div>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>