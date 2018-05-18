package net.front.auth.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.common.util.servlet.HttpServletManager;
import net.front.member.dao.MemberDao;
import net.front.member.dao.MemberDaoImpl;
import net.front.member.vo.MemberVo;

@WebServlet(urlPatterns = {"/front/auth/login.do"})
public class LoginServlet extends HttpServletManager {

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
		super.dispatcherForward(request, response, "/front/auth/login.jsp");
	}
	
	private void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		String memberPassword = request.getParameter("memberPassword");
		
		List<Object> memberVoList = memberDao.login(new MemberVo(memberEmail, memberPassword));
		
		MemberVo memberVo = null;
		for(Object object : memberVoList) {
			memberVo = (MemberVo) object;
		}
		
		if(memberVo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("memberVo", memberVo);
			
			response.sendRedirect("/");
		} else {
			request.setAttribute("msg", "존재하지 않는 회원입니다.");
			super.dispatcherForward(request, response, "/front/auth/login.jsp");
		}
	}
}
