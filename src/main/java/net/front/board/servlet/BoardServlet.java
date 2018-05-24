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
import net.front.tag.dao.TagDao;
import net.front.tag.dao.TagDaoImpl;
import net.front.tag.vo.TagVo;

/**
 * @author Administrator
 * 게시판 관련 서블릿
 */
@WebServlet(urlPatterns = {"/front/board/*"})
public class BoardServlet extends HttpServletManager {

	private BoardDao boardDao = new BoardDaoImpl();
	private TagDao tagDao = new TagDaoImpl();
	
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
			case "/list": // 목록
				list(request, response);
				break;
				
			case "/regist": // 등록
				if(type.equals("post")) {
					if(memberVo == null) {
						response.sendRedirect("/front/auth/login.do");
						return;
					}
					// 게시글 등록
					int result = regist(request, response, memberVo);
					
					if(result > 0) {
						response.sendRedirect("/front/board/list.do?boardCategoryId=" + boardCategoryId);
						return;
					} else {
						response.sendRedirect("/front/board/regist.do?boardCategoryId=" + boardCategoryId);
						return;
					}
				}
				
				break;
				
			case "/update": // 수정
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
			
			case "/detail": // 상세
				String boardId = request.getParameter("boardId");
				
				if(boardId == null || boardId.equals("")) {
					response.sendRedirect("/front/board/list.do?boardCategoryId=" + boardCategoryId);
					return;
				}
				
				BoardVo boardParameterVo = new BoardVo(Integer.parseInt(boardId), boardCategoryId);
				
				detail(request, response, boardParameterVo);
				break;	
				
			case "/delete": // 삭제
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
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null) {
			boardParameterVo.setPageNum(Integer.parseInt(pageNum));
		}
		
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
		// 게시글 조회
		List<Object> boardVoList = boardDao.selectOne(boardParameterVo);
		request.setAttribute("boardVoList", boardVoList);
		
		BoardVo boardVo = null;
		
		for(Object object : boardVoList) {
			boardVo = (BoardVo) object;
		}
		
		if(boardVo != null) {
			TagVo tagVo = new TagVo(boardVo.getBoardId(), "2");
			
			// 태그 조회
			List<Object> tagVoList = tagDao.listByBoardId(tagVo);
			
			request.setAttribute("tagVoList", tagVoList);
		}
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
		
		/*****************************************************
		 * 등록된 게시글의 generateId를 반환한다. 
		 ***************************************************/
		// 게시글 등록
		result = boardDao.insert(boardParameterVo);

		if(result > 0) { // 게시글이 정상적으로 등록된 경우
			String [] tags = request.getParameterValues("tag");
			
			if(tags != null && tags.length > 0){ // 파라메터에 태그가 있는경우
				TagVo tagVo = new TagVo();
				tagVo.setTagRefferenceId(result);
				tagVo.setTagType("2");
				
				for(String string : tags) {
					tagVo.setTagValue(string);
					 
					// 태그 등록
					tagDao.insert(tagVo);
				}
			}
		}
		
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
		
		if(result > 0) { // 게시글이 정상적으로 등록된 경우
			String [] tags = request.getParameterValues("tag");
			
			TagVo tagVo = new TagVo();
			tagVo.setTagRefferenceId(boardParameterVo.getBoardId());
			tagVo.setTagType("2");

			tagDao.delete(tagVo);
			
			for(String string : tags) {
				tagVo.setTagValue(string);
				 
				// 태그 등록
				tagDao.insert(tagVo);
			}
		}
		
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
