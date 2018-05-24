package net.front.boardComment.servlet;

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
import net.front.boardComment.dao.BoardCommentDao;
import net.front.boardComment.dao.BoardCommentDaoImpl;
import net.front.boardComment.vo.BoardCommentVo;
import net.front.member.vo.MemberVo;

@WebServlet(urlPatterns = {"/front/boardComment/*"})
public class BoardCommentAjaxServlet extends HttpServletManager {

	private BoardCommentDao boardCommentDao = new BoardCommentDaoImpl();
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
		String path = request.getPathInfo().replaceAll(".ajax", "");
		String boardCategoryId = request.getParameter("boardCategoryId");
		
		if(boardCategoryId == null || boardCategoryId.equals("")) {
			response.sendRedirect("/");
			return;
		}
		
		String boardId = request.getParameter("boardId");
		
		if(boardId == null || boardId.equals("")) {
			response.sendRedirect("/");
			return;
		}
		
		MemberVo memberVo = (MemberVo) request.getSession().getAttribute("memberVo");
		
		switch (path) {
			case "/list":
				BoardCommentVo boardCommentVo = new BoardCommentVo(boardCategoryId, Integer.parseInt(boardId));
				list(request, response, boardCommentVo);
				break;
				
			case "/regist":
				if(memberVo == null) {
					request.setAttribute("msg", "로그인 후 이용해주세요.");
					return;
				} 
				
				String boardCommentContent = request.getParameter("boardCommentContent");
				
				boardCommentVo = new BoardCommentVo(boardCategoryId, Integer.parseInt(boardId), boardCommentContent, memberVo.getMemberId(), super.getServerTime());
				
				if(regist(request, response, boardCommentVo) > 0) {
					request.setAttribute("msg", "등록되었습니다.");
				} else {
					request.setAttribute("msg", "등록에 실패했습니다.");
				}
				
				break;
		}
		
		String fullPath = "/front/boardComment" + path + "Ajax.jsp";
		
		super.dispatcherForward(request, response, fullPath);
	}
	
	/**
	 * 목록 관련 데이터 처리
	 * @param request
	 * @param response
	 * @return
	 */
	private void list(HttpServletRequest request, HttpServletResponse response, BoardCommentVo boardCommentParameterVo) {
		List<Object> boardCommentVoList = boardCommentDao.list(boardCommentParameterVo);
		request.setAttribute("boardCommentVoList", boardCommentVoList);
		
		BoardCommentVo boardCommentVo = null;
		
		for(Object object : boardCommentVoList) {
			boardCommentVo = (BoardCommentVo) object;
		}
		
		if(boardCommentVo != null) {
			BoardVo boardVo = new BoardVo(boardCommentVo.getBoardId(), boardCommentVo.getBoardCategoryId());
			List<Object> boardVoList = boardDao.selectOne(boardVo);
			request.setAttribute("boardVoList", boardVoList);
		}
	}
	
	/**
	 * 등록 관련 데이터 처리
	 * @param request
	 * @param response
	 * @return
	 */
	private int regist(HttpServletRequest request, HttpServletResponse response, BoardCommentVo boardCommentParameterVo) {
		int result = -1;
		
		result = boardCommentDao.insert(boardCommentParameterVo);
		
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
