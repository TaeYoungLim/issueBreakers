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
					<th colspan="2">내용</th>
				</tr>
				<tr>
					<td colspan="2">
						<textarea id="content" class="enable" style="display:none;">${boardVo.boardContent}</textarea>
					</td>
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
		
		<c:if test="${!empty sessionScope.memberVo}">
			<textarea id="boardCommentContent" name="boardCommentContent" class="form-control margin_top_30" placeholder="content" rows="2" style="display:none;"></textarea>
			<input type="button" value="등록" class="btn btn-primary" onclick="registComment()">
		</c:if>
		
		<script>
			function getCommentList() {
				$.ajax({
					url:"/front/boardComment/list.ajax"
					,data:{
						boardId : "${param.boardId}"
						,boardCategoryId : "${param.boardCategoryId}"
					}
					,success:function(res) {
						$("#comment tbody").html(res);
						$("textarea.enable").not($("#boardCommentContent")).each(function (){
							CodeMirror.fromTextArea($(this)[0], {
								height: "350px"						
								,lineNumbers: true
								,readOnly: true
								,mode : "javascript"
							    ,htmlMode: true
							});
						});
					}
				});
			}
			
			function registComment() {
				var boardCommentContent = $("#boardCommentContent");
				$.ajax({
					url:"/front/boardComment/regist.ajax"
					,data:{
						boardId : "${param.boardId}"
						,boardCategoryId : "${param.boardCategoryId}"
						,boardCommentContent : commentEditor.getValue()
					}
					,success:function(res) {
						alert(res);
						commentEditor.setValue("");
						getCommentList();
					}
				});
			}
			
			var commentEditor = null;
			
			$(document).ready(function() {
				getCommentList();
				
				if($("#boardCommentContent").length > 0) {
					commentEditor = CodeMirror.fromTextArea($("#boardCommentContent")[0], {
						lineNumbers: true
						,height: "150"
						,mode : "javascript"
					    ,htmlMode: true
					});
				}
			});
		</script>
	</div>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>