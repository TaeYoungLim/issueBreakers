package net.front.search.servlet;

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

@WebServlet(urlPatterns = {"/front/search/*"})
public class SearchServlet extends HttpServletManager {

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
		
		String searchWord = request.getParameter("searchWord");
		
		String pageNum = request.getParameter("pageNum");
		
		BoardVo boardParameterVo = new BoardVo();
		boardParameterVo.setSearchWord(searchWord);
		
		int listCount = boardDao.listCountSearch(boardParameterVo);
		
		if(pageNum != null) {
			boardParameterVo.setPageNum(Integer.parseInt(pageNum));
		}
		
		boardParameterVo.setCount(listCount);
		boardParameterVo.setPageData();
		
		List<Object> boardVoList = boardDao.listSearch(boardParameterVo);
		
		request.setAttribute("boardParameterVo", boardParameterVo);
		request.setAttribute("count", listCount);
		request.setAttribute("boardVoList", boardVoList);
		request.setAttribute("paging", boardParameterVo.getPageHtml());
		
		String fullPath = "/front/search" + path + ".jsp";
		
		super.dispatcherForward(request, response, fullPath);
	}
	
}
