<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="container content">
		<div class="line"></div>
		<h3 class="no-margin">통합 검색</h3>
		
		<p class="text-info"><i class="fab fa-searchengin"></i> 검색어 : ${requestScope.boardParameterVo.searchWord}</p>
		<p class="text-info"><i class="fas fa-list-ol"></i> 총 검색 건수  : ${requestScope.count} 건</p>
		<table class="table table-hover">
			<thead>
				<tr>
					<th class="text-center">title</th>
					<th class="text-center">comment</th>
					<th class="text-center">type</th>
					<th class="text-center">tag</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.boardVoList}">
						<c:forEach var="result" items="${requestScope.boardVoList}" varStatus="status">
							<tr onclick="click_detail('${result.boardId}', '${result.boardCategoryId}')">
								<td>${result.boardTitle}</td>
								<td class="text-center">${result.commentCount}</td>
								<td class="text-center">${result.boardCategoryId}</td>
								<td class="text-center">${result.tag}</td>
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
	</div>
	<script>
		function click_detail(boardId, boardCategoryId) {
			location.href = "/front/board/detail.do?boardId=" + boardId + "&boardCategoryId=" + boardCategoryId;
		}
		
		// 페이지 번호
		$(".pageMove").click(function() {
			if (!$(this).hasClass("on")) { 
				location.href = "/front/search/search.do?searchWord=${requestScope.boardParameterVo.searchWord}&pageNum=" + $(this).attr("data-page-no");
			}
		});
	</script>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>