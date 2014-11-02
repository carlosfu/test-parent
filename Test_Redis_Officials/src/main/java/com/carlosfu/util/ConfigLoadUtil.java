package com.carlosfu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 配置文件加载工具
 */
public class ConfigLoadUtil {
	/**
	 * 优先从class path加载配置文件，class path不存在从cls同级目录加载，都不存在返回null
	 * @param fileName
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Properties loadConnectString(String fileName, Class cls) {
		//优先读取外部配置文件
		InputStream is = cls.getClassLoader().getResourceAsStream(fileName);
		if(is == null) {
			is = cls.getResourceAsStream(fileName);
		}
		try {
			Properties prop = new Properties();
			prop.load(is);
			return prop;
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {is.close();} catch (IOException e) {}
			}
		}
		return null;
	}
}
