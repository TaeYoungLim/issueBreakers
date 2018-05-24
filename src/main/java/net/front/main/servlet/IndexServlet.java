package net.front.main.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.board.vo.BoardVo;
import net.common.util.servlet.HttpServletManager;
import net.front.board.dao.BoardDao;
import net.front.board.dao.BoardDaoImpl;

@WebServlet(urlPatterns = {"/index.do"})
public class IndexServlet extends HttpServletManager {
	
	private BoardDao boardDao = new BoardDaoImpl();
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("issueBoardVoList", boardDao.list(new BoardVo("issue", 1, 20)));
		request.setAttribute("informationBoardVoList", boardDao.list(new BoardVo("information", 1, 20)));
		super.dispatcherForward(request, response, "/front/main/index.jsp");
	}
}
