<%@page import="net.front.boardComment.vo.BoardCommentVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<BoardCommentVo> boardCommentVoList = (List<BoardCommentVo>) request.getAttribute("boardCommentVoList");

	if(boardCommentVoList.size() > 0) {
		int i=0;
		for(Object object : boardCommentVoList) {
			BoardCommentVo boardCommentVo = (BoardCommentVo) object;
	
%>
		<tr>
			<th>
				<span>제목 : <%=boardCommentVo.getBoardCommentContent()%></span>
				<span style="margin-left:30px;">작성자 : <%=boardCommentVo.getBoardCommentWriter()%></span>
				<span style="margin-left:30px;">날짜 : <%=boardCommentVo.getBoardCommentDate()%></span> 
			</th>
		</tr>
		<tr>
			<th>내용</td>
		</tr>
		<tr style="height:200px;">
			<td><textarea id="content<%out.print(i++);%>" class="enable"><%=boardCommentVo.getBoardCommentContent()%></textarea></td>
		</tr>
<%
		}
	}
%>    