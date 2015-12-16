/**
 * 
 */
package com.miao.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
* @Description: 
* @author Miao
* @date 2015年12月15日 上午10:43:46
*
*/
public class Log4jTest {
	private static Logger logger = Logger.getLogger("abc");

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		logger.debug("debug");
		logger.info("info");
		logger.error("error");
	}
}
