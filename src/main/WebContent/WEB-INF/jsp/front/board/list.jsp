<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="container content">
		<div class="line"></div>
		<h1 class="no-margin">목록</h1>
		<table class="table table-hover">
			<thead>
				<tr>
					<th class="text-center">title</th>
					<th class="text-center">comment</th>
					<c:choose>
						<c:when test="${param.boardCategoryId eq 'issue'}">
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
										<c:when test="${param.boardCategoryId eq 'issue'}">
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
	<script type="text/javascript">
		function click_regist() {
			location.href = "/front/board/regist.do?boardCategoryId=${param.boardCategoryId}";
		}
		
		function click_detail(boardId) {
			location.href = "/front/board/detail.do?boardId=" + boardId + "&boardCategoryId=${param.boardCategoryId}";
		}
		
		// 페이지 번호
		$(".pageMove").click(function() {
			if (!$(this).hasClass("on")) { 
				location.href = "/front/board/list.do?boardCategoryId=${param.boardCategoryId}&pageNum=" + $(this).attr("data-page-no");
			}
		});
	</script>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>