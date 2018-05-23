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
					<th class="text-center">selection</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.boardVoList}">
						<c:forEach var="result" items="${requestScope.boardVoList}" varStatus="status">
							<tr onclick="click_detail('${result.boardId}')">
								<td>${result.boardTitle}</td>
								<td class="text-center"></td>
								<td class="text-center"><i class="fas fa-times"></i></td>
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
		
		${requestScope.paging}
		<input type="button" value="글쓰기" class="btn btn-primary" onclick="click_regist()">		
	</div>
	<script type="text/javascript">
		function click_regist() {
			location.href = "/front/board/regist.do?boardCategoryId=${param.boardCategoryId}";
		}
		
		function click_detail(boardId) {
			location.href = "/front/board/detail.do?boardId=" + boardId + "&boardCategoryId=${param.boardCategoryId}";
		}
	</script>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>