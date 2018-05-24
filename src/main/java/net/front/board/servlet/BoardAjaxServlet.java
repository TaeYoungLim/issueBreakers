package net.front.board.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.board.vo.BoardVo;
import net.common.util.servlet.HttpServletManager;
import net.front.board.dao.BoardDao;
import net.front.board.dao.BoardDaoImpl;
import net.front.boardComment.dao.BoardCommentDao;
import net.front.boardComment.dao.BoardCommentDaoImpl;
import net.front.member.vo.MemberVo;

/**
 * @author Administrator
 * 게시판 아작스 관련 서블릿
 */
@WebServlet(urlPatterns = {"/front/board/updateSelection.ajax", "/front/board/updateVote.ajax"})
public class BoardAjaxServlet extends HttpServletManager {

	private BoardDao boardDao = new BoardDaoImpl();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response, "get");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response, "post");
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException {
		String path = request.getServletPath().replaceAll(".ajax", "");
		String boardId = request.getParameter("boardId");
		String boardCategoryId = request.getParameter("boardCategoryId");
		
		if(boardId == null || boardId.equals("")) {
			request.setAttribute("result", -2);
		}
		
		if(boardCategoryId == null || boardCategoryId.equals("")) {
			request.setAttribute("result", -1);
		}
		
		MemberVo memberVo = (MemberVo) request.getSession().getAttribute("memberVo");
		
		switch (path) {
			case "/front/board/updateSelection":
				String boardCommentId = request.getParameter("boardCommentId");
				
				if(boardCommentId == null || boardCommentId.equals("")) {
					request.setAttribute("result", -2);
					return;
				}

				if(memberVo == null) {
					request.setAttribute("result", -2);
					return;
				}
				
				BoardVo boardVo = new BoardVo(Integer.parseInt(boardId), boardCategoryId, Integer.parseInt(boardCommentId), memberVo.getMemberId());
				
				request.setAttribute("result", updateSelection(request, response, boardVo));
				
				break;
			case "/front/board/updateVote":
				boardVo = new BoardVo(Integer.parseInt(boardId), boardCategoryId);
				
				request.setAttribute("result", updateVote(request, response, boardVo));
				
				break;
		}
				
		String fullPath = path + "Ajax.jsp";
		
		super.dispatcherForward(request, response, fullPath);
	}
	
	/**
	 * 채택 수정 관련 데이터 처리
	 * @param request
	 * @param response
	 * @return
	 */
	private int updateSelection(HttpServletRequest request, HttpServletResponse response, BoardVo boardParameterVo) {
		int result = -1;
		
		result = boardDao.updateSelection(boardParameterVo);
		
		return result;
	}
	
	/**
	 * 투표 수정 관련 데이터 처리
	 * 쿠키를 이용해 투표 체크 및 처리 
	 * @param request
	 * @param response
	 * @return
	 */
	private int updateVote(HttpServletRequest request, HttpServletResponse response, BoardVo boardParameterVo) {
		int result = -1;

		Cookie[] cookies = request.getCookies();

		String value = null;
		if(cookies != null && cookies.length > 0) { // 쿠키 있을때
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("boardIds")) {
					value = cookie.getValue();
				}
			}
		} 
		
		boolean hasBoardId = false; 
		
		String boardData = "|" + boardParameterVo.getBoardId() + "|";
		
		if(value != null) {
			
			if(value.indexOf(boardData) > -1)
				hasBoardId = true;
			else
				value += boardData;
			
		} else
			value = boardData;
		
		
		if(!hasBoardId) {
			result = boardDao.updateVote(boardParameterVo);
			Cookie cookie = new Cookie("boardIds", value);
			response.addCookie(cookie);
		}
		
		return result;
	}
}
