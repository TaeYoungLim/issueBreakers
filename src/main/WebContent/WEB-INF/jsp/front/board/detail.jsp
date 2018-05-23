<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<c:set var="boardVo" value="${requestScope.boardVoList[0]}"/>
	<div class="container content">
		<div class="line"></div>
		<h1 class="no-margin">상세</h1>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>제목</th>
					<td>${boardVo.boardTitle}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${boardVo.boardWriter}</td>
				</tr>
				<tr>
					<th>날짜</th>
					<td>${boardVo.boardDate}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${boardVo.boardContent}</td>
				</tr>
			</tbody>
		</table>
		
		<c:if test="${sessionScope.memberVo.memberId eq boardVo.boardWriter}">
			<div>
				<input type="button" value="수정" class="btn btn-primary" onclick="click_update()">
				<input type="button" value="삭제" class="btn btn-primary" onclick="click_delete()">
			</div>
			<script type="text/javascript">
				function click_update() {
					location.href = "/front/board/update.do?boardId=${param.boardId}&boardCategoryId=${param.boardCategoryId}";
				}
				
				function click_delete() {
					location.href = "/front/board/delete.do?boardId=${param.boardId}&boardCategoryId=${param.boardCategoryId}";
				}
			</script>
		</c:if>	
		
		<div class="line"></div>
		<h4 class="no-margin">댓글</h3>
		<table id="comment" class="table table-hover">
			<tbody>
			</tbody>
		</table>
		<textarea id="boardContent" name="boardContent" class="form-control margin_top_30" placeholder="content" rows="2"></textarea>
		<input type="submit" value="등록" class="btn btn-primary">
		
		<script>
			function getCommentList() {
				$.ajax({
					url:"/front/boardComment/list.ajax"
					,data:{
						boardId : "${param.boardId}"
						boardCategoryId : "${param.boardCategoryId}"
					}
					,success:function(res) {
						$("#comment tbody").html(res);
					}
				});
			}
			
			$(document).ready(function() {
				getCommentList();
			});
		</script>
	</div>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>