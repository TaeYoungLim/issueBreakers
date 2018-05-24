package net.front.tag.servlet;

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
@WebServlet(urlPatterns = {"/front/tag/*"})
public class TagServlet extends HttpServletManager {

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
		
		switch (path) {
			case "/list": // 목록
				list(request, response);
				break;
		}

		String fullPath = "/front/tag" + path + ".jsp";
		
		super.dispatcherForward(request, response, fullPath);
	}
	
	/**
	 * 목록 관련 데이터 처리
	 * @param request
	 * @param response
	 * @return
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("tagVoList", tagDao.list());
	}
}
