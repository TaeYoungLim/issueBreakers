package net.front.board.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.board.vo.BoardVo;
import net.common.util.servlet.HttpServletManager;
import net.front.board.dao.BoardDao;
import net.front.board.dao.BoardDaoImpl;
import net.front.member.vo.MemberVo;

@WebServlet(urlPatterns = {"/front/board/*"})
public class BoardServlet extends HttpServletManager {

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
		String path = request.getPathInfo().replaceAll(".do", "");
		String boardCategoryId = request.getParameter("boardCategoryId");
		
		if(boardCategoryId == null || boardCategoryId.equals("")) {
			response.sendRedirect("/");
			return;
		}
		
		MemberVo memberVo = (MemberVo) request.getSession().getAttribute("memberVo");
		
		switch (path) {
			case "/list":
				list(request, response);
				break;
				
			case "/regist":
				if(type.equals("post")) {
					if(memberVo == null) {
						response.sendRedirect("/front/auth/login.do");
						return;
					}
						
					if(regist(request, response, memberVo) > 0) {
						response.sendRedirect("/front/board/list.do?boardCategoryId=" + boardCategoryId);
						return;
					} else {
						response.sendRedirect("/front/board/regist.do?boardCategoryId=" + boardCategoryId);
						return;
					}
				}
				
				break;
				
			case "/update":
				if(type.equals("post")) {
					if(memberVo == null) {
						response.sendRedirect("/front/auth/login.do");
						return;
					}
					
					String boardId = request.getParameter("boardId");
					
					if(boardId == null || boardId.equals("")) {
						response.sendRedirect("/front/board/list.do?boardCategoryId=" + boardCategoryId);
						return;
					}
					
					boardCategoryId = request.getParameter("boardCategoryId");
					String boardContent = request.getParameter("boardContent");
					String boardTitle = request.getParameter("boardTitle");
					
					BoardVo boardParameterVo = new BoardVo(Integer.parseInt(boardId) , boardCategoryId, boardTitle, boardContent, memberVo.getMemberId());
					
					if(update(request, response, memberVo, boardParameterVo) > 0) {
						response.sendRedirect("/front/board/detail.do?boardId="+boardId+"&boardCategoryId=" + boardCategoryId);
						return;
					} else {
						response.sendRedirect("/front/board/update.do?boardId="+boardId+"&boardCategoryId=" + boardCategoryId);
						return;
					}
				} else {
					String boardId = request.getParameter("boardId");
					
					if(boardId == null || boardId.equals("")) {
						response.sendRedirect("/front/board/list.do?boardCategoryId=" + boardCategoryId);
						return;
					}
					
					BoardVo boardParameterVo = new BoardVo(Integer.parseInt(boardId), boardCategoryId);
					
					detail(request, response, boardParameterVo);
				}
				
				break;
			
			case "/detail":
				String boardId = request.getParameter("boardId");
				
				if(boardId == null || boardId.equals("")) {
					response.sendRedirect("/front/board/list.do?boardCategoryId=" + boardCategoryId);
					return;
				}
				
				BoardVo boardParameterVo = new BoardVo(Integer.parseInt(boardId), boardCategoryId);
				
				detail(request, response, boardParameterVo);
				break;	
				
			case "/delete":
				if(memberVo == null) {
					response.sendRedirect("/front/auth/login.do");
					return;
				}
				
				boardId = request.getParameter("boardId");
				
				if(boardId == null || boardId.equals("")) {
					response.sendRedirect("/front/board/list.do?boardCategoryId=" + boardCategoryId);
					return;
				}
				
				boardParameterVo = new BoardVo(Integer.parseInt(boardId), boardCategoryId, memberVo.getMemberId());
				
				if(delete(request, response, memberVo, boardParameterVo) > 0) {
					response.sendRedirect("/front/board/list.do?boardCategoryId=" + boardCategoryId);
					return;
				} else {
					response.sendRedirect("/front/board/detail.do?boardId="+boardId+"&boardCategoryId=" + boardCategoryId);
					return;
				}
		}
		
		if(path.equals("/delete"))
			response.sendRedirect("/front/board/list.do?boardCategoryId=" + boardCategoryId);

		String fullPath = "/front/board" + path + ".jsp";
		
		super.dispatcherForward(request, response, fullPath);
	}
	
	/**
	 * 목록 관련 데이터 처리
	 * @param request
	 * @param response
	 * @return
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) {
		String boardCategoryId = request.getParameter("boardCategoryId");
		
		BoardVo boardParameterVo = new BoardVo(boardCategoryId);
		
		int listCount = boardDao.listCount(boardParameterVo);
		
		boardParameterVo.setCount(listCount);
		boardParameterVo.setPageData();
		
		List<Object> boardVoList = boardDao.list(boardParameterVo);
		
		request.setAttribute("boardVoList", boardVoList);
		request.setAttribute("paging", boardParameterVo.getPageHtml());
	}
	
	/**
	 * 상세 관련 데이터 처리
	 * @param request
	 * @param response
	 * @return
	 */
	private void detail(HttpServletRequest request, HttpServletResponse response, BoardVo boardParameterVo) {
		List<Object> boardVoList = boardDao.selectOne(boardParameterVo);
		request.setAttribute("boardVoList", boardVoList);
	}
	
	/**
	 * 등록 관련 데이터 처리
	 * @param request
	 * @param response
	 * @return
	 */
	private int regist(HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) {
		String boardCategoryId = request.getParameter("boardCategoryId");
		String boardContent = request.getParameter("boardContent");
		String boardTitle = request.getParameter("boardTitle");
		
		BoardVo boardParameterVo = new BoardVo(boardCategoryId, boardTitle, boardContent, memberVo.getMemberId(), super.getServerTime());
		
		int result = -1;
		
		result = boardDao.insert(boardParameterVo);
		
		return result;
	}
	
	/**
	 * 수정 관련 데이터 처리
	 * @param request
	 * @param response
	 * @return
	 */
	private int update(HttpServletRequest request, HttpServletResponse response, MemberVo memberVo, BoardVo boardParameterVo) {
		int result = -1;
		
		result = boardDao.update(boardParameterVo);
		
		return result;
	}
	
	/**
	 * 삭제 관련 데이터 처리
	 * @param request
	 * @param response
	 * @return
	 */
	private int delete(HttpServletRequest request, HttpServletResponse response, MemberVo memberVo, BoardVo boardParameterVo) {
		int result = -1;
		
		result = boardDao.delete(boardParameterVo);
		
		return result;
	}
}
