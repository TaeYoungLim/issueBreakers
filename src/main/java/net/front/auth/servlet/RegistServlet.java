package net.front.auth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.common.util.servlet.HttpServletManager;
import net.front.member.dao.MemberDao;
import net.front.member.dao.MemberDaoImpl;
import net.front.member.vo.MemberVo;

@WebServlet(urlPatterns = {"/front/auth/regist.do"})
public class RegistServlet extends HttpServletManager {

	private static final long serialVersionUID = 1L;

	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processPost(request, response);
	}
	
	private void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.dispatcherForward(request, response, "/front/auth/regist.jsp");
	}
	
	private void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		String memberPassword = request.getParameter("memberPassword");
		String memberNick = request.getParameter("memberNick");
		String memberDate = super.getServerTime();
		
		int result = memberDao.insert(new MemberVo(memberEmail, memberPassword, memberNick, memberDate));
		
		if(result > 0) {
			response.sendRedirect("/front/auth/login.do");
		} else {
			request.setAttribute("msg", "가입중 문제가 발생했습니다.");
			super.dispatcherForward(request, response, "/front/auth/login.jsp");
		}
	}
}
