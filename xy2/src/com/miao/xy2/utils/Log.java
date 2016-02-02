/**
 * 
 */
package com.miao.xy2.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.miao.test.Log4jTest;

/**
* @Description: 日志管理工具
* @author Miao
* @date 2015年12月15日 上午10:32:54
*
*/
public class Log {
	/**日志操作类*/
	private static Logger logger;

	static {
		// 加载配置文件
		PropertyConfigurator.configure("log4j.properties");
	}

	/**
	 * 错误
	 * @param name 名称
	 * @param message 内容
	 */
	public static void error(String name, String message) {
		logger = logger.getLogger(name);
		logger.error(message);
	}

	/**
	 * 警告
	 * @param name 名称
	 * @param message 内容
	 */
	public static void warn(String name, String message) {
		logger = logger.getLogger(name);
		logger.warn(message);
	}

	/**
	 * 信息
	 * @param name 名称
	 * @param message 内容
	 */
	public static void info(String name, String message) {
		logger = logger.getLogger(name);
		logger.info(message);
	}

	/**
	 * 测试
	 * @param name 名称
	 * @param message 内容
	 */
	public static void debug(String name, String message) {
		logger = logger.getLogger(name);
		logger.debug(message);
	}

}
