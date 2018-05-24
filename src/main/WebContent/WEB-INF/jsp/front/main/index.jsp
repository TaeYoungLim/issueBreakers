<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="banner">
		<div class="container">
			<div class="text">
				<h2>	
					Sharing knowledge, information, and issues
				</h2>
				<h2>
					Let's Build a Better Development Eco system
				</h2>
				<h2>
					Join the development community!
				</h2>
			</div>
		</div>
	</div>
	<div class="container content index">
		<div class="line"></div>
		<h3 class="no-margin">Issue</h3>
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
					<c:when test="${!empty requestScope.issueBoardVoList}">
						<c:forEach var="result" items="${requestScope.issueBoardVoList}" varStatus="status">
							<tr onclick="click_detail('${result.boardId}', '${result.boardCategoryId}')">
								<td>${result.boardTitle}</td>
								<td class="text-center">${result.commentCount}</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${result.boardSelection eq 'Y'}">
											<i class="fas fa-check color_green"></i>
										</c:when>
										<c:otherwise>
											<i class="fas fa-times"></i>
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
		
		<div class="line"></div>
		<h3 class="no-margin">Information</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th class="text-center">title</th>
					<th class="text-center">comment</th>
					<th class="text-center">vote</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.informationBoardVoList}">
						<c:forEach var="result" items="${requestScope.informationBoardVoList}" varStatus="status">
							<tr onclick="click_detail('${result.boardId}', '${result.boardCategoryId}')">
								<td>${result.boardTitle}</td>
								<td class="text-center">${result.commentCount}</td>
								<td class="text-center">
									${result.boardVote}
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
	</div>
	<script>
		function click_detail(boardId, boardCategoryId) {
			location.href = "/front/board/detail.do?boardId=" + boardId + "&boardCategoryId=" + boardCategoryId;
		}
	</script>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>