package net.common.util.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonDao {
	
	protected Connection connection;
	protected PreparedStatement preparedStatement;

	private static CommonDao commonDao;
	
	private CommonDao() {
		setConnection();
	}
	
	public static CommonDao getInstance() {
		if(commonDao == null) {
			commonDao = new CommonDao();
		}
		
		return commonDao;
	}
	
	private void setConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:apache:commons:dbcp:issueBreakers");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("fail to get connection", e);
		}
	}
	
	/**
	 * preparedStatement를 만든다.
	 * query에 해당하는 ${value} 형태를 placeholder로 치환하여 생성
	 * 
	 * ---------------------------------------------------
	 * -
	 * -	현재는 setInt, setString에 관한 데이터만 처리하고있다.
	 * -	이외 데이터는 필요에 따라서 추가할 예정
	 * - 
	 * ---------------------------------------------------
	 * 
	 * @param sql - String query
	 * @param vo - Object valueObject
	 */
	public  void setPreparedStatement(String sql, Object vo, String getGeneratedKeyName) {
		if(vo != null) {
			ArrayList<String> parameterNames = new ArrayList<>();
			String startLineSeparator = "${";
			String endLineSeparator = "}";
			
			String tempSql = sql;
			
			while(true) {
				int startIndex = sql.indexOf(startLineSeparator);
				int endIndex = sql.indexOf(endLineSeparator);
				
				if(startIndex >= 0) {
					parameterNames.add(sql.substring(startIndex + 2, endIndex));
				} else {
					break;
				}
				
				sql = sql.substring(endIndex + 1, sql.length());
			}
			
			// replaceAll ${} -> ?
			Pattern pattern = Pattern.compile("(\\$\\{[a-zA-Z0-9_]+\\})+");
			Matcher matcher = pattern.matcher(tempSql);
			
			if(matcher.find()) { 
				tempSql = matcher.replaceAll("?");
			}
			
			// xml은 '<', '>' 문자를 태그로 인식 
			tempSql.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
			
			// set preparedStatement
			try {
				// 생성키가 필요한 경우
				if(getGeneratedKeyName != null && !getGeneratedKeyName.equals(""))
					preparedStatement = connection.prepareStatement(tempSql, new String[]{getGeneratedKeyName});
				else
					preparedStatement = connection.prepareStatement(tempSql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/**
			 * vo 객체를 읽어들여
			 * ${name1}, ${name2}에 해당하는 파라메터를 생성한다.
			 */
			Class<?> clazz = null;
			
			clazz = vo.getClass();
			
			int i = 1;
			
			for(String parameterName : parameterNames) {
				try {
					Field field = clazz.getDeclaredField(parameterName);
					field.setAccessible(true);
					
					if(field.getType().toString().equals("int")) {
						preparedStatement.setInt(i++, field.getInt(vo));
					} else {
						preparedStatement.setString(i++, (String) field.get(vo));
					}
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					Field field;
					try {
						field = clazz.getSuperclass().getDeclaredField(parameterName);
						field.setAccessible(true);
						
						if(field.getType().toString().equals("int")) {
							preparedStatement.setInt(i++, field.getInt(vo));
						} else {
							preparedStatement.setString(i++, (String) field.get(vo));
						}
					} catch (NoSuchFieldException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			// set preparedStatement
			try {
				preparedStatement = connection.prepareStatement(sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void setPreparedStatement(String sql, Object vo) {
		setPreparedStatement(sql, vo, null);
	}
	
	public void setPreparedStatement(String sql) {
		setPreparedStatement(sql, null, null);
	}
	/**
	 * getter, setter는 한개의 argument만 처리중이다.
	 * 필요에따라 추가 할 예정 
	 * @param className
	 * @return
	 */
	public ArrayList<Object> getResult(String className) {
		
		ResultSet resultSet = null;
		ArrayList<Object> list = new ArrayList<>();
		
		if(preparedStatement != null) {
			try {
				Class<?> clazz = null;
				
				clazz = Class.forName(className);
				
				Method[] methods = clazz.getMethods();
				
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					
					int count = resultSetMetaData.getColumnCount();
					
					Object object = clazz.newInstance();
					
					for(int i = 1; i <= count; i++) {
						for(Method method : methods){
							if(method.getName().toUpperCase().equals("SET" + resultSetMetaData.getColumnName(i))) {
								Class<?>[] argTypes = method.getParameterTypes();
								
								if("java.lang.String".equals(argTypes[0].getName())) {
									method.invoke(object, resultSet.getString(i));
								} else if("int".equals(argTypes[0].getName())) {
									method.invoke(object, resultSet.getInt(i));
								}
								
								break;
							}
						}
					}
					
					list.add(object);
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * getter, setter는 한개의 argument만 처리중이다.
	 * 필요에따라 추가 할 예정 
	 * @param columnName
	 * @return
	 */
	public int getResultOneInt(String columnName) {
		
		ResultSet resultSet = null;
		ArrayList<Object> list = new ArrayList<>();
		
		int resultValue = 0;
		
		if(preparedStatement != null) {
			try {
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					resultValue = resultSet.getInt(columnName);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultValue;
	}
	
	/**
	 * 처리 후 결과 count를 반환한다.
	 * getter, setter는 한개의 argument만 처리중이다.
	 * 필요에따라 추가 할 예정 
	 * @return
	 */
	public int getResultUpdate() {
		int resultCount = -1;
		
		if(preparedStatement != null) {
			try {
				resultCount = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultCount;
	}
	
	/**
	 * 처리 후 추가적으로 생성된 키를 반환한다.
	 * getter, setter는 한개의 argument만 처리중이다.
	 * 필요에따라 추가 할 예정 
	 * @return
	 */
	public int getResultUpdateForKey() {
		int resultCount = -1;
		
		if(preparedStatement != null) {
			try {
				resultCount = preparedStatement.executeUpdate();
				
				if(resultCount <= 0)
					return -1;
				
				ResultSet resultSet =  preparedStatement.getGeneratedKeys();
				
				while(resultSet.next()) {
					return resultSet.getInt(1); 
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultCount;
	}
}
