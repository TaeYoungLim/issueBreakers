<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="container content">
		<div role="tabpanel" class="margin_top_30">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li id="issue" role="presentation" class="<c:if test="${empty requestScope.boardCategoryId || requestScope.boardCategoryId eq 'issue'}">active</c:if>">
					<a href="/front/myPage/myPage.do" aria-controls="home" role="tab" data-toggle="tab">내가 작성한 질문</a>
				</li>
				<li id="information" role="presentation" class="<c:if test="${requestScope.boardCategoryId eq 'information'}">active</c:if>">
					<a href="/front/myPage/myPage.do" aria-controls="profile" role="tab" data-toggle="tab">내가 작성한 정보</a>
				</li>
			</ul>
		</div>
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th class="text-center">title</th>
					<th class="text-center">comment</th>
					<c:choose>
						<c:when test="${requestScope.boardCategoryId eq 'issue'}">
							<th class="text-center">selection</th>
						</c:when>
						<c:otherwise>
							<th class="text-center">vote</th>
						</c:otherwise>
					</c:choose>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.boardVoList}">
						<c:forEach var="result" items="${requestScope.boardVoList}" varStatus="status">
							<tr onclick="click_detail('${result.boardId}')">
								<td>${result.boardTitle}</td>
								<td class="text-center">${result.commentCount}</td>
								<td class="text-center">
									
									<c:choose>
										<c:when test="${requestScope.boardCategoryId eq 'issue'}">
											<c:choose>
												<c:when test="${result.boardSelection eq 'Y'}">
													<i class="fas fa-check color_green"></i>
												</c:when>
												<c:otherwise>
													<i class="fas fa-times"></i>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											${result.boardVote}
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3" class="text-center">데이터 없음</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div>
			${requestScope.paging}
		</div>
		<div class="margin_bottom_30" style="clear:both;"></div>
		
		<c:if test="${!empty sessionScope.memberVo}">
			<input type="button" value="글쓰기" class="btn btn-primary" onclick="click_regist()">
		</c:if>
	</div>
	<script>
		$('#issue a').click(function (e) {
			e.preventDefault();
			location.href="/front/myPage/myPage.do?boardCategoryId=issue";
		});

		$('#information a').click(function(e) {
			e.preventDefault();
			location.href="/front/myPage/myPage.do?boardCategoryId=information";
		});
		
		function click_regist() {
			location.href = "/front/board/regist.do?boardCategoryId=${requestScope.boardCategoryId}";
		}
		
		function click_detail(boardId) {
			location.href = "/front/board/detail.do?boardId=" + boardId + "&boardCategoryId=${requestScope.boardCategoryId}";
		}
		
		// 페이지 번호
		$(".pageMove").click(function() {
			if (!$(this).hasClass("on")) { 
				location.href = "/front/board/list.do?boardCategoryId=${requestScope.boardCategoryId}&pageNum=" + $(this).attr("data-page-no");
			}
		});
	</script>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>