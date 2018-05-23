package net.common.util.dao.webListener;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

@WebListener
public class DBCPListener implements ServletContextListener {
	private String url;
	private String username;
	private String password;
	private String poolName;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
		
		Properties properties = new Properties();
		
		try {
			properties.load(new StringReader(poolConfig));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		loadJDBCDriver(properties);
		initConnectionPool(properties);
	}
	
	private void loadJDBCDriver(Properties properties) {
		String driverClass = properties.getProperty("driver");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}
	
    public void contextDestroyed(ServletContextEvent sce)  {}
	
	private void initConnectionPool(Properties properties) {
		url = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
		poolName = properties.getProperty("poolname");
		
		
		try {
			// A ConnectionFactory that the pool will use to create Connections.
			ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, username, password);
			
			// PoolableConnectionFactory wraps the real Connections with the
			// classes that implement the pooling functionality.
			PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
					null);
			poolableConnectionFactory.setValidationQuery("SELECT 1");

			GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
			genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			genericObjectPoolConfig.setTestWhileIdle(true);
			genericObjectPoolConfig.setMinIdle(4);
			genericObjectPoolConfig.setMaxTotal(50);

			// Actual pool of connections.
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory,
					genericObjectPoolConfig);

			Class.forName("org.apache.commons.dbcp2.PoolingDriver");

			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool(poolName, connectionPool);
			
			// Set the factory's pool property to the owning pool.
			poolableConnectionFactory.setPool(connectionPool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
