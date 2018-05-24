<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<c:set var="boardVo" value="${requestScope.boardVoList[0]}"/>
	
	<div class="container content">
		<div class="line"></div>
		<h1 class="no-margin">상세</h1>
		<table class="table table-hover" style="table-layout: fixed;">
			<colgroup>
				<col width="9%">
				<col width="91%">
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td>${boardVo.boardTitle}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${boardVo.memberName}</td>
				</tr>
				<tr>
					<th>날짜</th>
					<td>
						${boardVo.boardDate}
					</td>
				</tr>
				<c:if test="${param.boardCategoryId eq 'information'}">
					<tr>
						<th>투표수</th>
						<td class="relative">
							${boardVo.boardVote}
							<a class="vote" href="javascript:void(0);" onclick="vote('${param.boardId}','${param.boardCategoryId}'); return false;" data-toggle="tooltip" data-placement="top" title="투표!"><i class="far fa-heart"></i></a>
						</td>
					</tr>
				</c:if>
				<tr>
					<th colspan="2">내용</th>
				</tr>
				<tr>
					<td colspan="2">
						<textarea id="content" class="enable" style="display:none;">${boardVo.boardContent}</textarea>
					</td>
				</tr>
				<tr>
					<th>태그</th>
					<td>
						<div id="tagWrapper" class="tagWrapper">
							<c:forEach var="result" items="${requestScope.tagVoList}" varStatus="status">
									<div class="tagSet">
										<p class="btn btn-primary tag">${result.tagValue}</p>
									</div>
							</c:forEach>
						</div>
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
		<h4 class="no-margin">댓글</h4>
		<a href="/front/auth/login.do">댓글을 작성하려면 로그인하세요.</a>
		<table id="comment" class="table table-bordered" style="margin-top:50px; table-layout: fixed;">
			<tbody>
			</tbody>
		</table>
		
		<c:if test="${!empty sessionScope.memberVo}">
			<div class="commentWrapper" style="padding:35px; background:#efefefef;">
				<div class="" style="font-size:20px; font-weight:bold; margin-bottom:20px;">
					<i class="far fa-edit"></i> 글작성
				</div>
				<textarea id="boardCommentContent" name="boardCommentContent" class="form-control margin_top_30" placeholder="content" rows="2" style="display:none;"></textarea>
			</div>
			<input type="button" value="등록" class="btn btn-primary margin_top_30" onclick="registComment()">
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
							var codeMirror = CodeMirror.fromTextArea($(this)[0], {
								lineNumbers: true
								,readOnly: true
								,mode : "javascript"
							    ,scrollbarStyle: "simple"
							});
							
							$(this).removeClass("enable");
						});
					}
				});
			}
			
			function registComment() {
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
						,mode : "javascript"
					    ,htmlMode: true
					    ,scrollbarStyle: "simple"
					});
				}
			});
			
			function selection(boardCommentId, boardId, boardCategoryId) {
				$.ajax({
					url:"/front/board/updateSelection.ajax"
					,data:{
						boardId : boardId
						,boardCategoryId : boardCategoryId
						,boardCommentId : boardCommentId
					}
					,success:function(res) {
						if(res == -1)
							alert("문제가 발생했습니다.");
						else
							location.href="/front/board/detail.do?boardId=${param.boardId}&boardCategoryId=${param.boardCategoryId}";
					}
				});
			}
			
			function vote(boardId, boardCategoryId) {
				$.ajax({
					url:"/front/board/updateVote.ajax"
					,data:{
						boardId : boardId
						,boardCategoryId : boardCategoryId
					}
					,success:function(res) {
						if(res == -1)
							alert("이미 투표 했거나\n문제가 발생했습니다.");
						else 
							location.href="/front/board/detail.do?boardId=${param.boardId}&boardCategoryId=${param.boardCategoryId}";
					}
				});
			}
		</script>
	</div>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>