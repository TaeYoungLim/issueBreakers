<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="container content">
		<div class="line"></div>
		<h3 class="no-margin">태그</h3>
		<div id="tagWrapper" class="tagWrapper">
			<c:forEach var="result" items="${requestScope.tagVoList}" varStatus="status">
				<div class="tagSet">
					<a href="javascript:void(0);" onclick="find('${result.tagValue}'); return false;" class="btn btn-primary tag">${result.tagValue} : ${result.count}</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript">
		function find(searchWord) {
			$("#searchWord").val(searchWord);
			$("#searchForm").submit();
		}
	</script>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>