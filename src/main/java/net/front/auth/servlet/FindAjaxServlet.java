package net.front.auth.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.common.util.servlet.HttpServletManager;
import net.front.member.dao.MemberDao;
import net.front.member.dao.MemberDaoImpl;
import net.front.member.vo.MemberVo;

@WebServlet(urlPatterns = {"/front/auth/find.ajax"})
public class FindAjaxServlet extends HttpServletManager {

	private static final long serialVersionUID = 1L;

	private MemberDao memberDao = new MemberDaoImpl();
	
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
		String type = request.getParameter("type");
		
		if(type == null || (!type.equals("id") && type.equals("password"))) {
			request.setAttribute("result", "비정상적인 접근입니다.");
		}
		
		String memberNick = request.getParameter("memberNick");
		String memberEmail = request.getParameter("memberEmail");
		
		if(type.equals("id")) {
			MemberVo memberVo = new MemberVo();
			memberVo.setMemberNick(memberNick);
			List<Object> memberVoList = memberDao.findEmail(memberVo);
			
			memberVo = null;
			
			for(Object object : memberVoList) {
				memberVo = (MemberVo) object;
			}
			
			if(memberVo == null) {
				request.setAttribute("result", "없는 회원 입니다.");
			} else {
				request.setAttribute("result", memberVo.getMemberEmail() + " 입니다.");
			}
		} else{
			request.setAttribute("result", "준비중.....");
		}
		
		
		super.dispatcherForward(request, response, "/front/auth/findAjax.jsp");
	}
}
