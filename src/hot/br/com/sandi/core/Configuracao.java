package br.com.sandi.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 class ConfiguracaoBak {
	private Properties prop;
	private static ConfiguracaoBak instance = null;
	
	public static ConfiguracaoBak getInstance(){
		return instance;
	}
	public static ConfiguracaoBak init(String file) throws IOException{
		instance = new ConfiguracaoBak();
		instance.prop = new Properties();
		InputStream in = new FileInputStream(file);
		instance.prop.load(in);
		in.close();
		return instance;
	}

	protected ConfiguracaoBak(){}
	
	public String getConf(String key){
		return prop.getProperty(key);
	}
	
	
	
	
}
