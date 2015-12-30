/**
 * 
 */
package com.miao.xy2.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.miao.test.Log4jTest;

/**
* @Description: ��־������
* @author Miao
* @date 2015��12��15�� ����10:32:54
*
*/
public class Log {
	/**��־������*/
	private static Logger logger;

	static {
		// ���������ļ�
		PropertyConfigurator.configure("log4j.properties");
	}

	/**
	 * ����
	 * @param name ����
	 * @param message ����
	 */
	public static void error(String name, String message) {
		logger = logger.getLogger(name);
		logger.error(message);
	}

	/**
	 * ����
	 * @param name ����
	 * @param message ����
	 */
	public static void warn(String name, String message) {
		logger = logger.getLogger(name);
		logger.warn(message);
	}

	/**
	 * ��Ϣ
	 * @param name ����
	 * @param message ����
	 */
	public static void info(String name, String message) {
		logger = logger.getLogger(name);
		logger.info(message);
	}

	/**
	 * ����
	 * @param name ����
	 * @param message ����
	 */
	public static void debug(String name, String message) {
		logger = logger.getLogger(name);
		logger.debug(message);
	}

}
