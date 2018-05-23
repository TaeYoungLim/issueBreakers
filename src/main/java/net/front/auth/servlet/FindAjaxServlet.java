package net.front.auth.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.common.util.mail.GoogleSmtp;
import net.common.util.mail.Mail;
import net.common.util.servlet.HttpServletManager;
import net.front.member.dao.MemberDao;
import net.front.member.dao.MemberDaoImpl;
import net.front.member.vo.MemberVo;

@WebServlet(urlPatterns = {"/front/auth/find.ajax"})
public class FindAjaxServlet extends HttpServletManager {

	private static final long serialVersionUID = 1L;

	private MemberDao memberDao = new MemberDaoImpl();
	private Mail mail = new GoogleSmtp();
	
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
		try {
			String type = request.getParameter("type");
			
			if(type == null || (!type.equals("id") && type.equals("password"))) {
				request.setAttribute("result", "비정상적인 접근입니다.");
			}
			
			String memberNick = request.getParameter("memberNick");
			String memberEmail = request.getParameter("memberEmail");
			
			MemberVo memberVo = new MemberVo();
			
			if(type.equals("id")) {	// 아이디 조회
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
			} else { // 비밀번호 조회
				memberVo.setMemberEmail(memberEmail);
				List<Object> memberVoList = memberDao.findPassword(memberVo);
				
				for(Object object : memberVoList) {
					memberVo = (MemberVo) object;
				}
				
				mail.send(memberVo.getMemberEmail(), "요청하신 비밀번호 입니다.", "비밀번호는 " + memberVo.getMemberPassword() + "입니다.");
				
				request.setAttribute("result", "해당 메일로  비밀번호를 전송했습니다.");
			}
			
		} catch (Exception e) {
			request.setAttribute("result", "처리 중 문제가 발생했습니다. 관리자에게 문의해주세요.");
		}
		
		super.dispatcherForward(request, response, "/front/auth/findAjax.jsp");
	}
}
