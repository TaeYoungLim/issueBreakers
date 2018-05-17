package common.util;

public class ServletMappingVo {
	
	String sufix = "Servlet"; // 생성한 class에 대한 sufix xxxxServlet.java
	String path = ""; // request path
	
	public String getSufix() {
		return sufix;
	}
	public void setSufix(String sufix) {
		this.sufix = sufix;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
