package net.common.util.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletManager extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String viewSuffix = "/WEB-INF/jsp";
	
	public void dispatcherForward(HttpServletRequest request, HttpServletResponse response, String viewPath) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewSuffix + viewPath);
		dispatcher.forward(request, response);
	}
	
	/**
	 * 서버 시간을 가져온다
	 * @return String
	 */
	protected String getServerTime() {
		// 현재 시간
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 두번째 local 인자 세팅해도 안됨, client로 사용될때만 가능: new Locale("ko","KOREA")  or  Locale.KOREA
		Date date = new Date();
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul"); 

        // 서울시간,  표준시간:GMT, 자바 서버에서는 이렇게 Zone을 설정함. 
		simpleDateFormat.setTimeZone(timeZone);
		String currentTime = simpleDateFormat.format(date);
		
		return currentTime;
	}
}
