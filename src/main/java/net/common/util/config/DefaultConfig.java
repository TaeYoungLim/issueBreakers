package net.common.util.config;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.common.util.config.vo.DefaultConfigVo;

public class DefaultConfig {
	
	private DefaultConfigVo defaultConfigVo;
	private static DefaultConfig defaultConfig;
	
	private DefaultConfig() {
		loadConfig();
	}
		
	public static DefaultConfig getInstance() {
		if(defaultConfig == null) 
			defaultConfig = new DefaultConfig();
		
		return defaultConfig;
	}
	
	private void loadConfig() {
		Reader reader = null;
		
		try {
			// gson read.....
			reader = new InputStreamReader(DefaultConfigVo.class.getResourceAsStream("/net/common/config/defaultConfig.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			
			// 읽어온 gson객체를 vo에 매핑 시킨다.
			defaultConfigVo = (DefaultConfigVo) gson.fromJson(reader, DefaultConfigVo.class);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DefaultConfigVo getDefaultConfigVo() {
		return defaultConfigVo;
	}

	public void setDefaultConfigVo(DefaultConfigVo defaultConfigVo) {
		this.defaultConfigVo = defaultConfigVo;
	}
	
}
