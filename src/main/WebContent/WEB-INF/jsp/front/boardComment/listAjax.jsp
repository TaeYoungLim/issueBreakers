<%@page import="net.admin.board.vo.BoardVo"%>
<%@page import="net.front.member.vo.MemberVo"%>
<%@page import="net.front.boardComment.vo.BoardCommentVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<BoardCommentVo> boardCommentVoList = (List<BoardCommentVo>) request.getAttribute("boardCommentVoList");
	List<BoardVo> boardVoList = (List<BoardVo>) request.getAttribute("boardVoList");
	
	MemberVo memberVo = (MemberVo) request.getSession().getAttribute("memberVo");
	
	String boardCategoryId = request.getParameter("boardCategoryId");
	
	if(boardCommentVoList.size() > 0) {
		int i=0;
		for(Object object : boardCommentVoList) {
			BoardCommentVo boardCommentVo = (BoardCommentVo) object;
%>
		<tr>
			<th class="relative">
				<span>작성자 : <%=boardCommentVo.getMemberName()%></span>
				<span style="margin-left:30px;">날짜 : <%=boardCommentVo.getBoardCommentDate()%></span>
				<%
					if(boardCategoryId!= null && boardCategoryId.equals("issue")) {
						if(boardVoList != null && boardVoList.get(0).getBoardSelection().equals("Y")) {
							if(boardVoList.get(0).getBoardSelectionCommentId() == boardCommentVo.getBoardCommentId()) {
				%>
							<span class="selection" data-toggle="tooltip" data-placement="top" title="채택된 답변"><i class="fas fa-user-check"></i></span>
				<% 
							}
						} else {
							if(memberVo != null && boardVoList != null && memberVo.getMemberId() == boardVoList.get(0).getBoardWriter()) {
				%>
							<a class="selectionBtn" href="javascript:void(0);" onclick="selection('<%=boardCommentVo.getBoardCommentId()%>', '<%=boardCommentVo.getBoardId()%>', '<%=boardCommentVo.getBoardCategoryId()%>');return false;" data-toggle="tooltip" data-placement="top" title="채택"><i class="fas fa-check-circle"></i></a>
				<% 
							}
						}
					}
				%>
			</th>
		</tr>
		<tr>
			<th>내용</td>
		</tr>
		<tr style="padding-bottom:50px;">
			<td><textarea id="content<%out.print(i++);%>" class="enable"><%=boardCommentVo.getBoardCommentContent()%></textarea></td>
		</tr>
<%
		}
	}
%>    
<script>
	$(function () {
	  $('[data-toggle="tooltip"]').tooltip();
	})
</script>