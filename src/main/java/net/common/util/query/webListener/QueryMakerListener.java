package net.common.util.query.webListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@WebListener
public class QueryMakerListener implements ServletContextListener {
	
	/**
	 * key, value Example
	 * key = /net/front/member/test.xml/select
	 * 		- file명 : "/net/front/member/test.xml"
	 * 		- 구분자 : "/" 
	 * 		- xmlDom명 : "select"
	 * value = select * from test
	 */
	public static Map<String, String> _QueryMap = new HashMap<String, String>(); 
	
	/**
	 * JDBC 쿼리를 사용하기 위해 생성한 xml을 모두 읽어들여
	 * query String을 읽어 _QueryMap에 put한다.
	 */
	private void createQueryMap(Properties properties) {
		String scanPackage = properties.getProperty("scanPackage");
		String fileRegExp = properties.getProperty("fileRegExp");
		
		//scan urls that contain 'net', include inputs starting with 'net', use the ResourcesScanner scanners
		Reflections reflections = new Reflections(
										new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(scanPackage))
												.setScanners(new ResourcesScanner())
												.filterInputsBy(new FilterBuilder().includePackage(scanPackage))
			    					);
			     
		// use ResourcesScanner get queryXml Set
		Set<String> querXmlSet = reflections.getResources(Pattern.compile(fileRegExp));
		Iterator<String> iterator =  querXmlSet.iterator();
		
		while(iterator.hasNext()) {
			String resourceName = iterator.next();
			setXmlToMap("/" + resourceName);
		}
	}
	
	/**
	 * getResourceAsStream을 이용하여 
	 * xml을 읽어들인 후  _QueryMap에 put
	 * @param resourceName
	 */
	private void setXmlToMap(String resourceName) {
		InputStream inputStream = this.getClass().getResourceAsStream(resourceName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc;
		
		NodeList nodeList = null;
		
		try {
			// build
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputStream);
			doc.getDocumentElement().normalize();
			
			// rootElement에서 tag List를 담는다.
			nodeList = doc.getDocumentElement().getElementsByTagName("*");
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(nodeList != null) {
			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				
				Node node = nodeList.item(temp);
				
				if(_QueryMap.get(resourceName + "/" + node.getNodeName()) == null)
					_QueryMap.put(resourceName + "/" + node.getNodeName(), node.getTextContent());
				else
					throw new RuntimeException("filePath : " + resourceName + " dom : " +node.getNodeName() + " Are duplicated.");
			}
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		String queryConfig = sce.getServletContext().getInitParameter("queryConfig");
		
		Properties properties = new Properties();
		
		try {
			properties.load(new StringReader(queryConfig));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		createQueryMap(properties);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
