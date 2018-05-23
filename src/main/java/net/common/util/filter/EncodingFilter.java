package net.common.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


/**
 * @author Administrator
 * 필터를 이용하여 utf-8 형식으로 인코딩한다.
 */
@WebFilter(
	filterName="encodingFilter"
	,urlPatterns = "/*"
    ,initParams = @WebInitParam(name = "encoding", value = "utf-8")
)
public class EncodingFilter implements Filter {

	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String encoding = filterConfig.getInitParameter("encoding");
        if (request.getCharacterEncoding() == null) {
            if (encoding != null) 
                request.setCharacterEncoding(encoding);
        }
        
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
