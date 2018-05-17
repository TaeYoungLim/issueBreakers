package common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author 임태영
 * 각 servlet name과 request에 대한 contextPath를 매칭하여 서블릿을 사용하기 위한 클래스
 * ex) http://localhost:8080/test -> call.. -> testServlet
 */
public class ServletController extends HttpServlet {
	public void init() throws ServletException {
		// 설정 파일 명을 가져온다.
		String servletMappingConfig = getInitParameter("servletMappingConfig");
		
		Reader reader = null;
		
		try {
			// gson read.....
			reader = new InputStreamReader(ServletController.class.getResourceAsStream(servletMappingConfig), "UTF-8");
			Gson gson = new GsonBuilder().create();
			
			// 읽어온 gson객체를 vo에 매핑 시킨다.
			ServletMappingVo servletMappingVo = gson.fromJson(reader, ServletMappingVo.class);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
		
	}
}
